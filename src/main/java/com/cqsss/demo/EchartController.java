package com.cqsss.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchartController {
    @RequestMapping("/echarttest")
    public String myDemo(){
        return "echarttest";
    }
    @RequestMapping("/calendartest")
    public String myDemo2(){
        return "custom-calendar-icon";
    }
}
