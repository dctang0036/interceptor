package com.interceptor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/job")
public class TestController {

//    @RequestMapping("/test")
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("URL 执行 test");
        return "test";
    }
}
