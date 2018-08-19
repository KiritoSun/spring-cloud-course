package com.zt.service.Impl;

import com.zt.dao.CourseDao;
import com.zt.dao.SelectDao;
import com.zt.dao.StudentDao;
import com.zt.pojo.select;
import com.zt.pojo.student;
import com.zt.pojo.vcourse;
import com.zt.service.SelectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SelectServiceImpl implements SelectService {
    @Resource
    private CourseDao courseDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private SelectDao selectDao;

    @Override
    public String selectCourse(select select,String time) {
        try{
            student student = studentDao.selectById(select.getS_id());
            if(select.getPoint()>student.getS_point()) return "npoint";
            if(selectDao.selects(select.getS_id(),select.getC_id())!=null) return "no";
            List<vcourse> list = selectCourse2(select.getS_id());
            for(vcourse e : list){
                if(e.getC_time().equals(time)){
                    return "tno";
                }
            }
            studentDao.reducePoint(select.getS_id(),select.getPoint());
            selectDao.insert(select);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @Override
    public List<vcourse> selectCourse2(String s_id) {
        List<select> list = null;
        List<vcourse> list2 = null;
        try{
            list = selectDao.selectBySid(s_id);
            list2 = new ArrayList<>();
            vcourse p = null;
            for(select e : list){
                p = new vcourse(courseDao.selectById(e.getC_id()),e.getPoint());
                list2.add(p);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {}
        return list2;
    }

    @Override
    public boolean updatePoint(select select) {
        try{
            int old = selectDao.selects(select.getS_id(),select.getC_id()).getPoint();
            if(old<select.getPoint()){
                int newpoint = select.getPoint()-old;
                studentDao.reducePoint(select.getS_id(),newpoint);
            }
            if(old>select.getPoint()){
                int newpoint = old-select.getPoint();
                studentDao.plusPoint(select.getS_id(),newpoint);
            }
            selectDao.updatePoint(select);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean removeCourse(String s_id, String c_id) {
        try{
            int point = selectDao.selects(s_id,c_id).getPoint();
            studentDao.plusPoint(s_id,point);
            selectDao.delete(s_id,c_id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
