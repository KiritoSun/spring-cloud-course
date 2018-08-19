package com.zt.dao;

import com.zt.pojo.teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherDao {
    // 查询所有信息
    public List<teacher> selectAll();
    // 查询指定id的信息
    public teacher selectById(String id);
}
