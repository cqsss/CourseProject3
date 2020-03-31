package com.cqsss.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ThymeleafController {

    @GetMapping("/index")
    public String showPage(Model model) {
        model.addAttribute("message", "HelloWorld");
        return "index";
    }
}
