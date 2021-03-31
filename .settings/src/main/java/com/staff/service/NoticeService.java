package com.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.bean.Notice;
import com.staff.dao.NoticeMapper;

@Service
public class NoticeService {

	@Autowired
	NoticeMapper noticeMapper;
	
	// 查询所有公告

	public List<Notice> getNoticeAll() {
		// TODO Auto-generated method stub
		return noticeMapper.selectByExample(null);
	}
	
}
