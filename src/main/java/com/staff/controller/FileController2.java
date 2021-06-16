package com.staff.controller;
import java.util.List;
import java.io.File;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.staff.bean.Dept;
import com.staff.bean.Employee;
import com.staff.bean.Msg;
import com.staff.bean.Notice;
import com.staff.dao.EmployeeMapper;
import com.staff.dao.FileMapper;
import com.staff.service.DeptService;

@Controller
@CrossOrigin
public class FileController2 {
	
	@Autowired
	FileMapper fileMapper;
	
	
	//添加上传文件记录
	@RequestMapping("/addfile")
	@ResponseBody
	public Msg addnotice(@RequestBody com.staff.bean.File file) {
		System.out.println("添加上传文件记录");
		fileMapper.insert(file);
		return Msg.success();
	}
	
	//分页查询上传文件记录
	@RequestMapping("/getfiles")
	@ResponseBody
	public Msg getnotice(@RequestParam(value = "page", defaultValue = "1") Integer page) {
//		System.out.println("查询上传文件记录");
		PageHelper.startPage(page, 7);
		List<com.staff.bean.File> files = fileMapper.selectByExample(null);
		PageInfo pageInfo = new PageInfo(files, 5);
		return Msg.success().add("pageInfo", pageInfo);
	}
	
	//分页查询上传文件记录
	@RequestMapping("/getfile")
	@ResponseBody
	public Msg getfile(@RequestParam(value = "page", defaultValue = "1") Integer page) {
		
		List<com.staff.bean.File> files = fileMapper.selectByExample(null);
		PageHelper.startPage(page, 1000);
		PageInfo pageInfo = new PageInfo(files, 5);
		return Msg.success().add("pageInfo", pageInfo);
	}
}
