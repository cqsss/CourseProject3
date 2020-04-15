package com.cqszw.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cqs
 * @date 2020/4/7
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "dashboard";
    }
}