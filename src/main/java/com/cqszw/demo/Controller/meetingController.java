package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class meetingController {
    @Autowired
    private MeetingService meetingService;
    private  Meeting will_alter;

    @GetMapping("/meetings")
    public  String list(Model model){

        List<Meeting>meetings=meetingService.getAll();
        //查询所有会议返回列表页面
        model.addAttribute("meetings",meetings);
        return  "meeting/list";
    }
    @GetMapping("/meeting")
    public  String toAddMeeting(Model model){
        //添加页面显示所有会议
        List<Meeting>meetings=meetingService.getAll();
        //查询所有会议返回列表页面
         System.out.println("查询所有会议");
        model.addAttribute("meetings",meetings);
        return  "meeting/add";
    }
    @PostMapping("/meeting")
    public  String addMeeting(Meeting meeting){
        meetingService.insertMeeting(meeting);
        //最后回到员工列表页面
        return  "redirect:/meetings";
    }
    @GetMapping("/meeting/{name}/{location}/{date}")
    public  String alter(@PathVariable("name")String name,@PathVariable("location")String location,@PathVariable("date")String date,Model model){
         System.out.println("修改返回原值");
//        System.out.println(name);
//        System.out.println(location);
//        System.out.println(date);
         Meeting meeting = meetingService.getMeeting(name, location, date);
         will_alter=meeting;
//        model.addAttribute("name",meeting.getName());
//        model.addAttribute("location",meeting.getLocation());
//        model.addAttribute("date",meeting.getDate());
//        model.addAttribute("url",meeting.getContent());
          model.addAttribute("meeting",meeting);
          meeting.show();
        return  "meeting/alter";
    }
    @PutMapping("/meeting")
    public String update(Meeting meeting){
        System.out.println("更新");
        if(will_alter!=null){
            //meeting.show();
            meetingService.updateMeeting(meeting,will_alter);
        }
        return "redirect:/meetings";
    }
    @DeleteMapping("/meeting/{name}/{location}/{date}")
public  String alter(@PathVariable("name")String name,@PathVariable("location")String location,@PathVariable("date")String date) {
        System.out.println("删除");
        meetingService.deleteMeeting(name,location,date);
        return "redirect:/meetings";
    }
}
