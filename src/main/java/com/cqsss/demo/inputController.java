package com.cqsss.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;

@Controller
public class inputController {
    @RequestMapping("/input")
    public String aa(HttpServletRequest request) {
        String meeting_name=request.getParameter("meetingname");
        String meeting_date=request.getParameter("meetingdate");
        String meeting_content=request.getParameter("meetingcontent");
        System.out.println(meeting_name);
        System.out.println(meeting_date);
        System.out.println(meeting_content);
        return "input";
    }
}
