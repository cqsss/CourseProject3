package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Bean.User;
import com.cqszw.demo.Bean.User_Meetings;
import com.cqszw.demo.Service.UMService;
import com.cqszw.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class VisitorController {
    @Autowired
    private UserService userService;
    @Autowired
    private  UMService umService;
    User will_alter;
    @RequestMapping("/visitor")
    public  String visitor(){
        return "/visitor/dashboard";
    }
    @GetMapping("/visitor/login/{username}")
    public  String alter(@PathVariable("username")String username, Model model, HttpServletRequest request){
        Object loginUser = request.getSession().getAttribute("visitorUser");
        if(username.equals("null")&& loginUser==null){
            return "redirect:/index";
        }
        else if(loginUser==null){
            return "redirect:/index";
        }
        else if(StringUtils.isEmpty(username)){
            return "redirect:/index";
        }
        else {
//            System.out.println(username);
            User user = userService.getUserbyUsername(username);
            will_alter = user;
            model.addAttribute("user", user);
            return "visitor/alter";
        }
    }
    @PutMapping("/visitor/alter")
    public  String alterinformation(User user){
        if(will_alter!=null){
            //meeting.show();
            userService.updateUser(user,will_alter);
        }
        return "redirect:/visitor/";
    }
    @RequestMapping("/visitor/table/{date}")
    public String myMeetingbyDate(@PathVariable("date")String date, Model model, HttpServletRequest request){
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登入，没有个人数据");
            return "/visitor/dashboard";
        }
        else{
            String s = visitorUser.toString();
            int userid = userService.getuserid(s);
            System.out.println("userid:"+userid);
            List<Meeting> meetings=umService.getbyuseranddate(userid,date);
            model.addAttribute("meetings",meetings);
//            //System.out.println(s);
            return "/visitor/dashboard";
        }
    }
}
