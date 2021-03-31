package com.staff.dao;

import com.staff.bean.Dept;
import com.staff.bean.DeptExample;
import com.staff.bean.Employee;
import com.staff.bean.EmployeeExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    long countByExample(DeptExample example);

    int deleteByExample(DeptExample example);

    int deleteByPrimaryKey(Dept record);

    int insert(Dept record);

    int insertSelective(Dept record);

    List<Dept> selectByExample(DeptExample example);
    
    List<Dept> selectByExampleWithEmp(DeptExample example);

	Dept selectByPrimaryKeyWithEmp(String id);

    Dept selectByPrimaryKey(Integer did);
    
    List<Dept> selectByDname(Dept record);

    int updateByExampleSelective(@Param("record") Dept record, @Param("example") DeptExample example);

    int updateByExample(@Param("record") Dept record, @Param("example") DeptExample example);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}