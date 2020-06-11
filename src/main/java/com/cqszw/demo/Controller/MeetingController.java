package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Service.MeetingService;
import com.cqszw.demo.Service.UPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    @Autowired
    private UPService upService;
    private Meeting will_alter;

    @GetMapping("/meetings")
    public String list(Model model){

        List<Meeting>meetings=meetingService.getAll();
        //查询所有会议返回列表页面
        model.addAttribute("meetings",meetings);
        return  "meeting/list";
    }
    @GetMapping("/meeting")
    public  String toAddMeeting(Model model){
        return  "meeting/add";
    }
    @GetMapping("/meetings/category/{type}")
    public String meetingType(@PathVariable("type")String type, Model model) {
        List<Meeting> meetings = meetingService.getMeetingByType(type);
        model.addAttribute("meetings",meetings);
        return "meeting/list";
    }
    @PostMapping("/meeting")
    public String addMeeting(Meeting meeting,Model model){
        if(meeting.getName().isEmpty()){
            model.addAttribute("msg","会议名不能为空");
            return "meeting/add";
        }
        else if(meeting.getName().length()>100){
            model.addAttribute("msg","会议名称不能超过100个字符");
            return "meeting/add";
        }
        else if(meeting.getLocation().isEmpty()){
            model.addAttribute("msg","会议地点不能为空");
            model.addAttribute("user",will_alter);
            return "meeting/add";
        }
        else if(meeting.getDate().isEmpty()){
            model.addAttribute("msg","会议日期不能为空");
            model.addAttribute("user",will_alter);
            return "meeting/add";
        }
        else if(meeting.getLocation().length()>100){
            model.addAttribute("msg","会议地点不能超过100个字符");
            model.addAttribute("user",will_alter);
            return "meeting/add";
        }
        else if(meeting.getUrl().length()>200){
            model.addAttribute("msg","会议链接不能超过200个字符");
            model.addAttribute("user",will_alter);
            return "meeting/add";
        }
        else{
            if(!meeting.getUrl().startsWith("http")){
                StringBuilder stringBuilder=new StringBuilder(meeting.getUrl());
                stringBuilder.insert(0,"http://");
                meeting.setUrl(stringBuilder.toString());
            }
            meetingService.insertMeeting(meeting);
        }

        //最后回到员工列表页面
        return  "redirect:/meetings";
    }
    @GetMapping("/meeting/{name}/{location}/{date}")
    public String alter(@PathVariable("name")String name,@PathVariable("location")String location,@PathVariable("date")String date,Model model){
//         System.out.println("修改返回原值");
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
    public String update(Meeting meeting,Model model){
//        System.out.println("更新");
        if(will_alter!=null){
            //meeting.show();
            if(meeting.getName().isEmpty()){
                model.addAttribute("msg","会议名不能为空");
                return "meeting/alter";
            }
            else if(meeting.getName().length()>100){
                model.addAttribute("msg","会议名不超过100个字符");
                return "meeting/alter";
            }
            else if(meeting.getLocation().isEmpty()){
                model.addAttribute("msg","地址不能为空");
                model.addAttribute("user",will_alter);
                return "meeting/alter";
            }
            else if(meeting.getDate().isEmpty()){
                model.addAttribute("msg","日期不能为空");
                model.addAttribute("user",will_alter);
                return "meeting/alter";
            }
            else if(meeting.getLocation().length()>100){
                model.addAttribute("msg","地址不超过100个字符");
                model.addAttribute("user",will_alter);
                return "meeting/alter";
            }
            else if(meeting.getUrl().length()>200){
                model.addAttribute("msg","地址不超过200个字符");
                model.addAttribute("user",will_alter);
                return "meeting/alter";
            }
            else{
                meetingService.updateMeeting(meeting,will_alter);
            }

        }
        return "redirect:/meetings";
    }
    @DeleteMapping("/meeting/{name}/{location}/{date}")
    public String alter(@PathVariable("name")String name,@PathVariable("location")String location,@PathVariable("date")String date) {
//        System.out.println("删除");
        meetingService.deleteMeeting(name,location,date);
        return "redirect:/meetings";
    }
    @GetMapping("/meeting/check")
    public  String toCheckMeeting(Model model){
        List<Meeting> meetings = meetingService.unchecked();
        model.addAttribute("meetings",meetings);
        return  "meeting/check";
    }
    @GetMapping("/meeting/checked/{id}")
    public  String toCheckMeeting(@PathVariable int id){
        meetingService.passcheck(id);
        upService.pass(id);
        return  "redirect:/meeting/check";
    }
    @DeleteMapping("/meeting/checked/{id}")
    public  String confuseMeeting(@PathVariable int id){
        meetingService.confuse(id);
        upService.confuse(id);
        return  "redirect:/meeting/check";
    }

}
