package com.zt.service.Impl;

import com.zt.dao.TeacherDao;
import com.zt.pojo.teacher;
import com.zt.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherDao teacherDao;

    @Override
    public String loginService(String id, String password) {
        teacher teacher = null;
        try{
            teacher = teacherDao.selectById(id);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }finally {}
        if(teacher==null) return "tno";
        if(teacher.getT_password().equals(password)) return "success";
        else return "pno";
    }

    @Override
    public teacher foundService(String id) {
        teacher teacher = null;
        try{
            teacher = teacherDao.selectById(id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {}
        return teacher;
    }
}
