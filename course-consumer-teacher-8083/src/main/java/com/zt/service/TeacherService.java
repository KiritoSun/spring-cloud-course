package com.zt.service;

import com.zt.pojo.teacher;

public interface TeacherService {
    // 验证身份
    public String loginService(String id, String password);
    // 查询信息
    public teacher foundService(String id);
}
