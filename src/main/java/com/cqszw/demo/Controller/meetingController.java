package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.meeting;
import com.cqszw.demo.Service.meetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class meetingController {
    @Autowired
    private meetingService meetingService;
    @GetMapping("/test/{name}")
    public String get(@PathVariable("name") String name, Model model){
        meeting a= meetingService.getMeetingByName(name);
        System.out.println(a.getLatitude());
        model.addAttribute("p_y",a.getLatitude());
        model.addAttribute("p_x",a.getLongitude());
        model.addAttribute("title","会议名字："+a.getName());
        model.addAttribute("address","会议地点："+a.getLocation());

        return "api";
    }
}
