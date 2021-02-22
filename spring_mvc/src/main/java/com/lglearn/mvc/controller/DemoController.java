package com.lglearn.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/handle01")
    public ModelAndView handle01(){
        Date date = new Date();
        //封装数据和页面信息的ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //addObject 其实是想请求域中
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

}
