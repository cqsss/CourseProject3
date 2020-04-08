package com.cqsss.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class inputController {
    @RequestMapping("/input")
    public String aa() {
        return "input";
    }
}
