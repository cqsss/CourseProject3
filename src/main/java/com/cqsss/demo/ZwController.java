package com.cqsss.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author zw
 * @date 2020/4/7
 */
class Point{
        double x;
        double y;
        Point(){
            x=122.09;
            y=37.54;
        }
}
@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@Controller
public class ZwController {
    @RequestMapping("/zw")
    public String test(Model model){
        Point p=new Point();
        model.addAttribute("p_x",p.x);
        model.addAttribute("p_y",p.y);
        return "api";
    }
}
