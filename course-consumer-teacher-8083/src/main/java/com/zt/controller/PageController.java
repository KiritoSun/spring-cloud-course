package com.zt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        HttpSession session = null;
        try{
            session = request.getSession();
        }catch (Exception e){
            e.printStackTrace();
        }finally {}
        if(session.getAttribute("teacher")==null) return "login";
        else return "index";
    }

    @RequestMapping("/info")
    public String info(){
        return "info";
    }

    @RequestMapping("/add")
    public String add(){
        return "add";
    }

}
