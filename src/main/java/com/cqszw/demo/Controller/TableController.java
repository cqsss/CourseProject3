package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author cqs
 * @date 2020/4/17
 */
@Controller
public class TableController {
    @Autowired
    private MeetingService meetingService;
    @GetMapping("/table")
    public String list(Model model){
//        List<Meeting> meetings=meetingService.getMeetingByDate("2020-04-15");
//        //查询所有会议返回列表页面
//        System.out.println(meetings);
//        model.addAttribute("meetings",meetings);
        return "dashboard";
    }
    @GetMapping("/table/{date}")
    public String getDate(@PathVariable("date") String date,Model model) {
        System.out.println(date);
        String ym=date.substring(0,7);
        model.addAttribute("ym",ym);
        String y=date.substring(0,5);
        String m = date.substring(5, 7);
        String d=date.substring(7);

        int month=Integer.parseInt(m);
        month=month+1;
        String next=y+month+d;
        model.addAttribute("nexttime",next);
        System.out.println("ym:"+ym);
        System.out.println("next"+next);
        List<Meeting> meetings=meetingService.getMeetingByDate(date);
        if(!meetings.isEmpty()){
            System.out.println(meetings.get(0).getName());
            model.addAttribute("meetings",meetings);
        }
        return "dashboard";
    }
    @GetMapping("/table/{name}/{location}/{date}")
    public  String alter(@PathVariable("name")String name, @PathVariable("location")String location,
                         @PathVariable("date")String date,Model model) {
        Meeting meeting = meetingService.getMeeting(name, location, date);
        model.addAttribute("location",meeting.getLocation());
        model.addAttribute("meeting",meeting);
        return "map";
    }

}
