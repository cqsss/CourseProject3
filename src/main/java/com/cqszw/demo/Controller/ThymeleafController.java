package com.cqszw.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cqs
 * @date 2020/4/7
 */
@Controller
public class ThymeleafController {

    @GetMapping("/index")
    public String showPage(Model model) {
        model.addAttribute("message", "HelloWorld");
        return "index";
    }
}
