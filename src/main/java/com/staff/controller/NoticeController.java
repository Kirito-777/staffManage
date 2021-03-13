package com.staff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.staff.bean.Dept;
import com.staff.bean.Msg;
import com.staff.bean.Notice;
import com.staff.bean.User;
import com.staff.dao.NoticeMapper;
import com.staff.service.NoticeService;

@CrossOrigin
@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;
	
	@Autowired
	NoticeMapper noticeMapper;
	
	//获取全部公告
	@RequestMapping("/getnotices")
	@ResponseBody
	public List<Notice> getDept() {
//		PageHelper.startPage(1, 5);
		List<Notice> notice = noticeService.getNoticeAll();
		// 使用PageInfo包装查询后的结果，连续显示的页数为5
//		PageInfo pageInfo = new PageInfo(dept, 1);
//		return Msg.success().add("pageInfo", pageInfo);
		System.out.println("读取公告信息");
//		System.out.println(notice.containsAll(notice));
		return notice;
	}
	
	//修改公告
	@RequestMapping("/updatenotice")
	@ResponseBody
	public Msg updatenotice(@RequestBody Notice notice) {
		System.out.println("修改公告");
		System.out.println(notice.toString());
		noticeMapper.updateByPrimaryKey(notice);
		return Msg.success();
	}
	
	//删除公告
	@RequestMapping("/deletenotice")
	@ResponseBody
	public Msg deletenotice(@RequestBody Notice notice) {
		System.out.println("删除公告");
		System.out.println(notice.toString());
		noticeMapper.deleteByPrimaryKey(notice.getNid());
		return Msg.success();
	}
	
	//添加公告
	@RequestMapping("/addnotice")
	@ResponseBody
	@CrossOrigin
	public Msg addnotice(@RequestBody Notice notice) {
		System.out.println("添加公告");
		
		System.out.println(notice.toString());
		noticeMapper.insert(notice);
		return Msg.success();
	}	
	
	//查找公告
	@RequestMapping("/searchnotice")
	@ResponseBody
	@CrossOrigin
	public List<Notice> searchnotice(@RequestBody Notice notice) {
		System.out.println("查找公告");
		System.out.println(notice.toString());
		if(notice.getUsername() == "") {
			notice.setUsername(null);;
		}
		if(notice.getTitle() == "") {
			notice.setTitle(null);
		}
		if(notice.getContent() == "") {
			notice.setContent(null);
		}
		if(notice.getnId() == "") {
			notice.setnId(null);
		}
		if(notice == null) {
			List<Notice> notice1 = noticeService.getNoticeAll();
			return notice1;
		}else {
			List<Notice> notice1=noticeMapper.selectByExampleWithNotice(notice);
			return notice1;
		}
		
	}	
}
