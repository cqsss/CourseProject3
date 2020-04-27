package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    public String getDate(@PathVariable("date") String date,Model model) throws ParseException {
        System.out.println(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date wholedate=sdf.parse(date);
        SimpleDateFormat ymformat = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat yformat = new SimpleDateFormat("yyyy");
        String ym = ymformat.format(wholedate);
        String y = yformat.format(wholedate);
        model.addAttribute("ym",ym);
        model.addAttribute("y",y);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(wholedate);
        calendar.add(Calendar.MONTH,1);
        Date nextdate = calendar.getTime();
        String next = sdf.format(nextdate);
        System.out.println(next);
        model.addAttribute("nexttime",next);
        calendar.setTime(wholedate);
        calendar.add(Calendar.MONTH,-1);
        Date lastdate = calendar.getTime();
        String last = sdf.format(lastdate);
        model.addAttribute("lasttime",last);
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
