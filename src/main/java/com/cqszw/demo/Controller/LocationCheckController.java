package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cqs
 * @date 2020/4/7
 */
@Controller
public class LocationCheckController {

    @RequestMapping("/location")
    public String showPage(HttpServletRequest request) {
        String string_x=request.getParameter("x");
        String string_y=request.getParameter("y");
        System.out.println(string_x);
        System.out.println(string_y);
        return "locationcheck";
    }
}
