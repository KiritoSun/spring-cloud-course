package com.zt.service;

import com.zt.pojo.course;
import com.zt.pojo.tcourse;

import java.util.List;

public interface CourseService {
    // 处理课程分页业务
    public List<course> sortPage(int page);
    // 获取页数
    public int getNumber();
    // 获取课程表信息
    public List<tcourse> getCourseTable(String s_id);
}
