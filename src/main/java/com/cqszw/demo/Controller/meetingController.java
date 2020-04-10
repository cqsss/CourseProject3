package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.meeting;
import com.cqszw.demo.Service.meetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class meetingController {
    @Autowired
    private meetingService meetingService;
    @GetMapping("/test/{name}")
    public meeting get(@PathVariable("name") String name){
        return meetingService.getMeetingByName(name);
    }
}
