package com.cqsss.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author cqs
 * @date 2020/4/7
 */
@Controller
public class CalendarController {
    @RequestMapping("/calendartest")
    public String calendar(){
        return "calendartest";
    }
}
