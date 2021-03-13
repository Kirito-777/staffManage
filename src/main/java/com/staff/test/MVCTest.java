package com.staff.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.staff.bean.Employee;

/*
 * 测试增删改查请求的正确性
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" })
public class MVCTest {

	// 传入SpringMVC的ioc
	@Autowired
	WebApplicationContext context;

	// 虚拟MVC请求，获取处理结果
	MockMvc mockMvc;

	@Before
	public void initMockMvc() {
		// TODO Auto-generated method stub
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testPage() throws Exception {
		// 模拟请求拿到返回值
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/getemps").param("page", "1")).andReturn();

		// 请求成功后，请求域中会有PageInfo，取出PageInfo进行数据验证
		MockHttpServletRequest request = result.getRequest();
		PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
		System.out.println("当前页码：" + pageInfo.getPageNum());
		System.out.println("总页码：" + pageInfo.getPages());
		System.out.println("总记录数：" + pageInfo.getTotal());
		System.out.println("连续显示的页码：");
		int[] nums = pageInfo.getNavigatepageNums();
		for (int i : nums) {
			System.out.println(" " + i);
		}

		// 获取员工数据
		List<Employee> emps = pageInfo.getList();
		for (Employee employee : emps) {
			System.out.println("ID:" + employee.getId() + ",name:" + employee.getName() + ",salary:"
					+ employee.getSalary() + ",age:" + employee.getAge());
		}
	}

}
