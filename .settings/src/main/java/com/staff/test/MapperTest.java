package com.staff.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.staff.bean.Employee;
import com.staff.dao.EmployeeMapper;
import com.staff.dao.UserMapper;

/*
 * 测试dao层的工作
 * 
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MapperTest {

	@Autowired
	UserMapper userMapper;

	@Autowired
	EmployeeMapper employeeMapper;

	@Autowired
	SqlSession sqlSession;

	/*
	 * 测试UserMapper
	 */
	@Test
	public void testCRUD() throws Exception {
		/*
		 * // 1.创建SpringIOC容器 ApplicationContext ioc = new
		 * ClassPathXmlApplicationContext("applicationContext.xml"); // 2.从容器中获取Mapper
		 * UserMapper beanMapper = ioc.getBean(UserMapper.class);
		 */
		System.out.println(userMapper);
		System.out.println(employeeMapper);

		// 插入一个用户
//		User user = new User("1056427550", "justlethe", "伍", "39mikusama", "男");
//		userMapper.insertSelective(user);

		// 插入一个员工
//		Employee employee = new Employee("1001001", "张三", 10000.54, 24);
//		employeeMapper.insertSelective(employee);

		// 批量插入员工，使用执行批量操作的sqlSession
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//		for (int i = 0; i < 10; i++) {
//			String s = UUID.randomUUID().toString();
//			// 生成1000-9999的浮点数
//			double salary = Math.random() * (9999 - 1000 + 1) + 1000;
//			int age = (int) (Math.random() * (50 - 20 + 1) + 20);
//			mapper.insertSelective(new Employee(s, s.substring(0, 3), salary, age));
//		}

		// 更新
//		Employee employee = new Employee("1001001", "李四", 20000.54, 14);
//		employeeMapper.updateByPrimaryKey(employee);
//
//		employee.setAge(25);
//		employee.setName("赵五");
//		employeeMapper.updateByPrimaryKeySelective(employee);

		Employee employee = employeeMapper.selectByPrimaryKey("1001001");
		employee.setAge(17);
		employee.setName("小红");
		employee.setSalary(999.99);
		employeeMapper.updateByPrimaryKey(employee);
//			Employee employee = employeeMapper.selectByPrimaryKey(string);
		System.out.println("update ok");
	}
}
