package com.zt.service.Impl;

import com.zt.dao.CourseDao;
import com.zt.dao.SelectDao;
import com.zt.pojo.course;
import com.zt.pojo.select;
import com.zt.pojo.tcourse;
import com.zt.service.CourseService;
import com.zt.util.ConvertHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;
    @Resource
    private SelectDao selectDao;

    @Override
    public List<course> sortPage(int page) {
        List<course> list = null;
        List<course> newlist = new ArrayList<>();
        try{
            list = courseDao.selectAll();
            int start = (page-1)*5;
            for(int i=start;i<start+5;i++){
                if(i>=list.size()) break;
                newlist.add(list.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {}
        return newlist;
    }

    @Override
    public int getNumber() {
        List<course> list = null;
        try{
            list = courseDao.selectAll();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return (list.size()/5);
    }

    @Override
    public List<tcourse> getCourseTable(String s_id) {
        List<tcourse> list = new LinkedList<>();
        try{
            List<select> p = selectDao.selectBySid(s_id);
            for(select e : p){
                course course = courseDao.selectById(e.getC_id());
                String name = course.getC_name();
                String time = ConvertHelper.concert(course.getC_time());
                list.add(new tcourse(name,time));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {}
        return list;
    }
}
