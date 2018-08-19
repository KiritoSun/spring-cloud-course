package com.zt.controller;

import com.zt.pojo.course;
import com.zt.pojo.student;
import com.zt.pojo.tcourse;
import com.zt.pojo.vcourse;
import com.zt.service.CourseService;
import com.zt.service.SelectService;
import com.zt.service.StudentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class PageController {
    @Resource
    private StudentService studentService;
    @Resource
    private CourseService courseService;
    @Resource
    private SelectService selectService;

    // 请求访问学生
    @RequestMapping("/index")
    public String student(HttpServletRequest request){
        HttpSession session = null;
        try{
            session = request.getSession();
        }catch (Exception e){
            e.printStackTrace();
        }finally {}
        if(session.getAttribute("student")==null) return "login";
        else return "index";
    }

    // 请求访问学生首页
    @RequestMapping("/studentIndex")
    public String studentIndex(HttpServletRequest request){
        updatePageInfo(request);
        return "index";
    }

    // 请求访问学生个人中心
    @RequestMapping("/studentInfo")
    public String studentInfo(HttpServletRequest request){
        updatePageInfo(request);
        return "info";
    }

    // 请求课程分页显示
    @RequestMapping("/course")
    public String course(@RequestParam("page") int page, HttpServletRequest request){
        HttpSession session = null;
        try{
            session = request.getSession();
            List<course> list = courseService.sortPage(page);
            int number = courseService.getNumber();
            session.setAttribute("course",list);
            session.setAttribute("number",number);
        }catch (Exception e){
            e.printStackTrace();
        }finally {}
        return "course";
    }

    // 请求显示已选课程
    @RequestMapping("/select")
    public String select(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            student student = (student) session.getAttribute("student");
            List<vcourse> list = selectService.selectCourse2(student.getS_id());
            session.setAttribute("select",list);
        }catch (Exception e){
            e.printStackTrace();
        }finally {}
        return "select";
    }

    // 请求跳转课程表
    @RequestMapping("/table")
    public String table(){
        return "table";
    }

    // 请求显示课程表
    @RequestMapping(value = "createTable",method = RequestMethod.POST)
    public void createTable(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try{
            out = response.getWriter();
            HttpSession session = request.getSession();
            String s_id = ((student)session.getAttribute("student")).getS_id();
            List<tcourse> list = courseService.getCourseTable(s_id);
            if(list.size()==0){
                out.print("null");
            }
            else{
                String json = JSONArray.fromObject(list).toString();
                out.print(json);
            }
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {}
        out.close();
    }

    // 请求更新页面信息
    public void updatePageInfo(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            String id = ((student)session.getAttribute("student")).getS_id();
            student student = studentService.getStudent(id);
            session.setAttribute("student",student);
        }catch (Exception e){
            e.printStackTrace();
        }finally {}
    }
}
