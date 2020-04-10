package com.cqszw.demo.Controller;

import org.springframework.stereotype.Controller;
import com.cqszw.demo.Bean.meeting;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class inputController {
    private  meeting meeting=new meeting();
    @RequestMapping("/new")
    public  String n(){
        return "input";
    }
    @RequestMapping("/input")
    public String aa(HttpServletRequest request) throws ParseException {
        String meeting_name = request.getParameter("meetingname");
        String meeting_date = request.getParameter("meetingdate");
        String meeting_content = request.getParameter("meetingcontent");
        String meeting_location=request.getParameter("meetinglocation");
//        System.out.println(meeting_name);
//        System.out.println(meeting_date);
//        System.out.println(meeting_content);
//        System.out.println(meeting_location);
        meeting.setName(meeting_name);
        meeting.setContent(meeting_content);
        meeting.setLocation(meeting_location);
        meeting.setDate(meeting_date);
         System.out.println(meeting.getName());
         System.out.println(meeting.getContent());
         System.out.println(meeting.getLocation());
         System.out.println(meeting.getDate());

        return "locationcheck";
    }
    @RequestMapping("/locationcheck")
    public String showPage(HttpServletRequest request) {
        String string_x=request.getParameter("x");
        String string_y=request.getParameter("y");
//        System.out.println(string_x);
//        System.out.println(string_y);
        meeting.setLongitude(Double.valueOf(string_x));
        meeting.setLatitude(Double.valueOf(string_y));
        System.out.println(meeting.getLongitude());
        System.out.println(meeting.getLatitude());
        meeting.show();
        return "index";
    }




}