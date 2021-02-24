package com.lglearn.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class DemoController {

    //对原生servlet api的支持
    @RequestMapping("/handle01")
    public ModelAndView handle01(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Date date = new Date();
        //封装数据和页面信息的ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //addObject 其实是想请求域中
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/handle02")
    public String handle02(ModelMap modelMap) {
        Date date = new Date();
        modelMap.addAttribute("date", date);
        return "success";
    }

    @RequestMapping("/handle03")
    public String handle03(Model model) {
        Date date = new Date();
        model.addAttribute("date", date);
        return "success";
    }

    @RequestMapping("/handle04")
    public String handle04(Map<String, Object> map) {
        Date date = new Date();
        map.put("date", date);
        return "success";
    }
}
