package com.staff.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.staff.bean.Dept;
import com.staff.bean.Employee;
import com.staff.bean.Msg;
import com.staff.service.EmployeeService;

@Controller
@CrossOrigin
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	// 分页查询员工数据
	// RequestParam传入当前页数，默认为1
	@RequestMapping("/getemps")
	@ResponseBody
	public Msg getEmps(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestBody Employee emp) {
		
		if (emp.getName()=="") {
			emp.setName(null);
		}
		if (emp.getId()=="") {
			emp.setId(null);
		}
		if (emp.getDept().getDname()=="") {
			emp.getDept().setDname(null);
		}
		
		System.out.println(emp);
		
		// 引入PageHelper插件并调用，传入页码及每页的行数
		PageHelper.startPage(page, 7);
		if (emp == null) {
			// 查询所有员工数据
			List<Employee> emps = employeeService.getEmpAll();
			// 使用PageInfo包装查询后的结果，连续显示的页数为5
			PageInfo pageInfo = new PageInfo(emps, 5);

			return Msg.success().add("employee", pageInfo);
		} else {
			// 条件查询所有员工数据

			List<Employee> employee = employeeService.getEmpName(emp);
			PageInfo pageInfo = new PageInfo(employee, 5);
			System.out.println(employee.size());
			
			System.out.println(emp);
			if (employee.size() == 0) {
				return Msg.fail();
			}
			

			// 将数据employee传回前台；
			return Msg.success().add("employee", pageInfo);
		}
	}
	
	//得到全部员工
	@RequestMapping("/getempAll")
	@ResponseBody
	public List<Employee> getEmpAll() {
//		PageHelper.startPage(page, 5);
		List<Employee> Employee = employeeService.getEmpAll();
		// 使用PageInfo包装查询后的结果，连续显示的页数为5
//		PageInfo pageInfo = new PageInfo(Employee, 1);
//		return (List<Dept>) Msg.success().add("pageInfo", pageInfo);
		return Employee;
	}
	
	//修改单个员工数据
	@RequestMapping("/updateemp")
	@ResponseBody
	public Msg updateEmp(@RequestBody Employee emp) {

		System.out.println(emp.toString());
		if (emp.getName()=="") {
			emp.setName(null);
		}
		if(emp.getName()==null||emp.getAge()==null||emp.getSalary()==null||emp.getDid()==null) {
			return Msg.fail().add("employee", "输入框不能为空");
		}
		if (emp.getSalary() > 100000) {
			return Msg.fail().add("employee", "工资太高了");
		}
		
		Employee employee = employeeService.getEmp(emp.getId());
		
		if (employee == null) {
			return Msg.fail().add("employee", "没有该员工");
		}
		Employee employee2 = employeeService.getEmpName2(emp.getName());
		
		if (emp.getName().equals(employee.getName())&&emp.getAge().equals(employee.getAge())
				&&emp.getSalary().equals(employee.getSalary())&&emp.getDid().equals(employee.getDid())) {
			return Msg.fail().add("employee", "你没有修改数据");
		}
		
		if(employee2!=null&&employee.getId()==employee2.getId()) {
			return Msg.fail().add("employee", "员工姓名已存在");
		}
		

		employeeService.updateEmp(emp);
		//将数据employee传回前台；
		return Msg.success().add("employee", emp);
		
	}
	
	//删除单个员工数据
	@RequestMapping("/deleteemp")
	@ResponseBody
	public Msg deleteEmp(@RequestParam(value = "id") String id) {
		
		System.out.println(id);	
		Boolean boolean1 = employeeService.deleteEmp(id);
		
		//将数据employee传回前台；
		if (boolean1) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	//增加员工数据
	@RequestMapping("/addemp")
	@ResponseBody
	public Msg addEmp(@RequestBody Employee emp) {
	
		System.out.println(emp.toString());
		if (emp.getName()=="") {
			emp.setName(null);
		}
		if(emp.getName()==null||emp.getAge()==null||emp.getSalary()==null||emp.getDid()==null) {
			return Msg.fail().add("employee", "输入框不能为空");
		}
		if (emp.getSalary() > 100000) {
			return Msg.fail().add("employee", "工资太高了");
		}
		
		Employee employee = employeeService.getEmpName2(emp.getName());
		
		if (employee != null) {
			System.out.println("姓名已存在");
			return Msg.fail().add("employee", "员工姓名已存在");
		}
		
		String id = (String) UUID.randomUUID().toString().subSequence(0, 8);
		emp.setId(id);
		employeeService.addEmp(emp);
		System.out.println(emp.getName());
		
		//将数据employee传回前台；
		return Msg.success().add("employee", emp);
		
	}
	
}
