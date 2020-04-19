package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@Controller
/**
 * @author zw
 * @date 2020/4/7
 */
public class ZwController {
    @RequestMapping("/zw")
    public String test(Model model){
        Point p=new Point(122.09,37.54);
        model.addAttribute("p_x",p.getX());
        model.addAttribute("p_y",p.getY());
        model.addAttribute("title","周伟测试");
        model.addAttribute("address","周伟测试的地址：哈工大周伟测试的寝室");

        return "api";
    }
}
