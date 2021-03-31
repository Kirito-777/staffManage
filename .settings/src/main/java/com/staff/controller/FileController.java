package com.staff.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.staff.bean.Dept;
import com.staff.bean.Employee;
import com.staff.bean.Msg;
import com.staff.dao.EmployeeMapper;
import com.staff.service.DeptService;

@Controller
@CrossOrigin(origins = "*")
public class FileController {
	
	// 非法字符集
	String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	DeptService deptService;
	
	@PutMapping("/readfile")
	@ResponseBody
	public Msg readFile(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("file") MultipartFile multipartFile) throws IOException {
		
		System.out.println(multipartFile);
		InputStream inputStream = multipartFile.getInputStream();
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
		
		HSSFWorkbook workbook = new HSSFWorkbook(poifsFileSystem);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		List<String> nameList = new ArrayList<String>();
		
		for (Row row : sheet) {
			if (row.getRowNum()==0) {
				continue;
			}
			List<String> list = new ArrayList<String>();
			for (Cell cell : row) {
				if (cell!=null) {
					cell.setCellType(CellType.STRING);
					String value = cell.getStringCellValue();
					list.add(value);
				}
			}
			Employee emp = new Employee();
			String id = (String) UUID.randomUUID().toString().subSequence(0, 8);
			emp.setId(id);
			
			char[] name = list.get(0).toCharArray();
			// 判定名字中是否有非法字符
			for (char s : name) {
				if (regEx.indexOf(s)>0 || Character.isDigit(s)) {
					return Msg.fail().add("msg", list.get(0)+"名字中存在数字或非法字符,导致此后的文件内容未完成导入，请检查文件内容");
				}
			}
			
			try {
				if (list!=null) {
					emp.setName(list.get(0));
					emp.setSalary(Double.parseDouble(list.get(1)));
					emp.setAge(Integer.parseInt(list.get(2)));
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				return Msg.fail().add("msg", "在导入用户"+list.get(0)+"时失败，请验证文件内容是否有误");
			}
//			switch (list.get(list.size()-1)) {
//			case "研发部":
//				emp.setDid(1);
//				break;
//			case "测试部":
//				emp.setDid(2);
//				break;
//			case "运营部":
//				emp.setDid(3);
//				break;
//			default:
//				return Msg.fail().add("msg", "在导入用户"+list.get(0)+"时失败，请验证文件内容是否有误");
//			}
			List<Dept> dept = deptService.getDeptAll();
			System.out.println(list.get(list.size()-1));
			for (int i = 0; i < dept.size(); i++) {
//				System.out.println(dept.get(i).getDname());
				if(list.get(list.size()-1).equals(dept.get(i).getDname())) {
					emp.setDid(dept.get(i).getDid());
				}
			}
			if(emp.getDid() == null) {
				return Msg.fail().add("msg", "在导入用户"+list.get(0)+"时失败，请验证文件内容是否有误");
			}
			System.out.println(emp);
			if (employeeMapper.selectOneName(emp.getName())==0) {
				try {
					employeeMapper.insert(emp);
				} catch (Exception e) {
					// TODO: handle exception
					return Msg.fail().add("msg", "在导入用户"+emp.getName()+"时失败，请验证文件内容是否有误");
				}
			}else {
				nameList.add(emp.getName());
			}
			
		}
		workbook.close();
		return Msg.success().add("name", nameList);
	}
	
}
