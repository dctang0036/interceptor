package com.interceptor.controller;

import com.interceptor.intercep.AvoidDuplicateSub;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 避免表单重复提交
 * 1、服务器返回新建表单页面时，会先生成一个token保存于session，并把该token传给表单页面
 */
@Controller
public class AvoidDuplicateSubmit {


    /*@RequestMapping(value = "/toindex", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        *//*ModelAndView mv = new ModelAndView();
        mv.setViewName("indexform");
        return mv;*//*
        return "indexform";
    }*/

    @AvoidDuplicateSub(needSaveToken = true)
    @RequestMapping(value = "/toindex", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("token", request.getSession(false).getAttribute("token"));
        mv.setViewName("indexform");
        return mv;
//        return "indexform";
    }

    /*@AvoidDuplicateSub(needSaveToken = true)
    @RequestMapping(value = "/toindex", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
       *//* ModelAndView mv = new ModelAndView();
        mv.addObject("token", request.getSession(false).getAttribute("token"));
        mv.setViewName("indexform");*//*
       model.addAttribute("token", request.getSession(false).getAttribute("token"));
       return "indexform";
//        return "indexform";
    }*/

    @AvoidDuplicateSub(needRemoveToken = true)
    @GetMapping(value = "/commit")
    @ResponseBody
    public String submit(@RequestParam(name="name") String name, @RequestParam(name="date") String date) {
        /*ModelAndView mv = new ModelAndView();
        mv.setViewName("indexform");
        return mv;*/
        return "sucess";
    }
}
