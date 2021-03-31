package com.staff.service;

import java.util.List;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;
import com.staff.bean.Employee;
import com.staff.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;

	// 查询所有员工
	
	public List<Employee> getEmpAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	// 多功能模糊查询员工
	public List<Employee> getEmpName(Employee emp) {
		// TODO Auto-generated method stub
		List<Employee> employee;
		
		try {
			if (emp.getSalary()%1==0) {
				employee = employeeMapper.selectByNameWithDept2(emp);
			}else {
				employee = employeeMapper.selectByNameWithDept(emp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			employee = employeeMapper.selectByNameWithDept(emp);
		}
		
		return employee;
	}
	
	// 查询员工ID
	public Employee getEmpId(String id) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKeyWithDept(id);
		return employee;
	}
	
	// 查询员工是否纯在
	public Employee getEmpName2(String name) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByName(name);
		return employee;
	}
	
	// 查询员工是否纯在
	public Employee getEmp(String id) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}
	
	// 修改员工
	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		System.out.println("进入修改功能");
		employeeMapper.updateByPrimaryKey(employee);
	}

	// 删除员工
	public boolean deleteEmp(String id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
		return true;
	}
	
	// 增加员工
	public void addEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insert(employee);
	}
}
