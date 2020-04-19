package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        List<Meeting> meetings=meetingService.getMeetingByDate("2020-04-15");
        //查询所有会议返回列表页面
        System.out.println(meetings);
        model.addAttribute("meetings",meetings);
        return "tabletest";
    }
    @RequestMapping("/table")
    @ResponseBody
    public String getDate(String date) {
        System.out.println(date);
        return date;
    }
}
