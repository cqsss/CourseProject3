package com.cqsss.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ZwController {
    @RequestMapping("/zw")
    public String test(){
        return "zwnb";
    }
}
