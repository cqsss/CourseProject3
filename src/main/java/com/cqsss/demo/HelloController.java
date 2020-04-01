package com.cqsss.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello,world";
    }
    @RequestMapping("/echarttest")
    public String myDemo(){

        return "echarttest";
    }
}