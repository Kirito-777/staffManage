package com.staff.dao;

import com.staff.bean.Employee;
import com.staff.bean.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Employee record);

    int insertSelective(Employee record);
    
    int selectOneName(String name);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(String id);
    
    List<Employee> selectByExampleWithDept(EmployeeExample example);

	Employee selectByPrimaryKeyWithDept(String id);
    
    Employee selectByName(String Name);
    
    List<Employee> selectByNameWithDept(Employee record);
    
    List<Employee> selectByNameWithDept2(Employee record);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}