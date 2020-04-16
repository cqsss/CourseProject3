package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

@Controller
public class meetingController {
    @Autowired
    private MeetingService meetingService;
    @GetMapping("/test/{name}")
    public String get(@PathVariable("name") String name, Model model){
        Meeting a= meetingService.getMeetingByName(name);
        System.out.println(a.getLatitude());
        model.addAttribute("p_y",a.getLatitude());
        model.addAttribute("p_x",a.getLongitude());
        model.addAttribute("title","会议名字："+a.getName());
        model.addAttribute("address","会议地点："+a.getLocation());

        return "api";
    }
    @GetMapping("/meetings")
    public  String list(Model model){

        List<Meeting>meetings=meetingService.getAll();
        //查询所有会议返回列表页面
        model.addAttribute("meetings",meetings);
        return  "meeting/list";
    }
}
