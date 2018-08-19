package com.zt.controller;

import com.zt.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class TeacherController {
    @Resource
    private TeacherService teacherService;

    // 验证身份
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(@RequestParam(value = "id") String id,
                      @RequestParam(value = "password") String password,
                      HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        try{
            out = response.getWriter();
            String result = teacherService.loginService(id,password);
            if(result.equals("success")){
                HttpSession session = request.getSession();
                session.setAttribute("teacher",teacherService.foundService(id));
            }
            out.print(result);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {}
        out.close();
    }
}
