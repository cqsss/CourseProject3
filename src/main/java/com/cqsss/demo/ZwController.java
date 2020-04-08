package com.cqsss.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
/**
 * @author zw
 * @date 2020/4/7
 */
public class ZwController {
    @RequestMapping("/zw")
    public String test(Model model){
        Point p=new Point();
        model.addAttribute("p_x",p.x);
        model.addAttribute("p_y",p.y);
       model.addAttribute("title","周伟测试");
       model.addAttribute("address","周伟测试的地址：哈工大周伟测试的寝室");

        return "api";
    }
}
