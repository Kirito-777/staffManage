package com.staff.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.staff.bean.User;
import com.staff.bean.UserExample;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(String id);

    User selectByPrimaryKey(String id);
    
    User selectByUsername(String username);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int updateByUsername(User record);
    
    int updateById(User record);
    
    long isexistByUsername(String username);
}