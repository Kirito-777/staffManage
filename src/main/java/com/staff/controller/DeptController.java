package com.staff.controller;

import java.util.List;

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
import com.staff.service.DeptService;

@CrossOrigin
@Controller
public class DeptController {
	@Autowired
	DeptService deptService;
	
	//分页获取全部部门
	@RequestMapping("/getdepts")
	@ResponseBody
	public Msg getDept(@RequestParam(value = "page", defaultValue = "1") Integer page) {
		PageHelper.startPage(page, 7);
		List<Dept> dept = deptService.getDeptAll();
		// 使用PageInfo包装查询后的结果，连续显示的页数为5
		PageInfo pageInfo = new PageInfo(dept, 5);
		return  Msg.success().add("pageInfo", pageInfo);
//		return dept;
	}
	
	//获取全部部门
	@RequestMapping("/getdeptsAll")
	@ResponseBody
	public List<Dept> getDept() {
		List<Dept> dept = deptService.getDeptAll();
		return dept;
	}
	
	//查询单个岗位数据
	@RequestMapping("/getdept")
	@ResponseBody
	public Msg getDept(@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestBody Dept dept) {
		System.out.println(dept.getDname());
		List<Dept> dept1 = deptService.getDept(dept);
		PageHelper.startPage(page, 7);
		PageInfo pageInfo = new PageInfo(dept1, 5);
		return  Msg.success().add("pageInfo", pageInfo);
		//将数据dept传回前台；
//		return dept1;
		
	}
	
	//修改岗位数据
	@RequestMapping("/updatedept")
	@ResponseBody
	public Msg updateDept(@RequestBody Dept dept) {
		Boolean boolean1 = deptService.updateDept(dept);
		System.out.println("修改部门");
		System.out.println(dept.getDname());
		System.out.println(boolean1);
		
		//将数据dept传回前台；
		return Msg.success().add("dept", dept);
		
	}
	
	//删除单个岗位数据
	@RequestMapping("/deletedept")
	@ResponseBody
	public Msg deleteDept(@RequestBody Dept dept) {	
		Boolean boolean1 = deptService.deleteDept(dept);
		System.out.println("删除部门");
		System.out.println(dept.getDname());
		System.out.println(boolean1);
		//将数据dept传回前台；
		return Msg.success().add("dept", dept);
		
	}
	
	//添加岗位数据
	@RequestMapping("/adddept")
	@ResponseBody
	public Msg addDept(@RequestBody Dept dept) {	
		Boolean boolean1 = deptService.addDept(dept);
		System.out.println("添加部门");
		System.out.println(boolean1);
		//将数据dept传回前台；
		return Msg.success().add("dept", dept);
		
	}
}

	
