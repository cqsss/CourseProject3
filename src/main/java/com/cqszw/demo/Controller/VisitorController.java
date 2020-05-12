package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Bean.User;
import com.cqszw.demo.Bean.User_Meetings;
import com.cqszw.demo.Service.MeetingService;
import com.cqszw.demo.Service.UMService;
import com.cqszw.demo.Service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class VisitorController {
    @Autowired
    private  UserService userService;
    @Autowired
    private  UMService umService;
    @Autowired
    private MeetingService meetingService;
    User will_alter;
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
    public String myMeetingbyDate(@PathVariable("date")String date, Model model, HttpServletRequest request) throws ParseException {
        System.out.println(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date wholedate=sdf.parse(date);
        SimpleDateFormat ymformat = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat yformat = new SimpleDateFormat("yyyy");
        SimpleDateFormat ymdformat = new SimpleDateFormat("yyyy-MM-dd");
        String ym = ymformat.format(wholedate);
        String y = yformat.format(wholedate);
        String ymd = ymdformat.format(wholedate);
        model.addAttribute("ym",ym);
        model.addAttribute("y",y);
        model.addAttribute("ymd",ymd);
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
    @GetMapping("/visitor/table/{name}/{location}/{date}")
    public  String alter(@PathVariable("name")String name, @PathVariable("location")String location,
                         @PathVariable("date")String date,Model model) {
        Meeting meeting = meetingService.getMeeting(name, location, date);
        model.addAttribute("location",meeting.getLocation());
        model.addAttribute("meeting",meeting);
        return "visitor/map";
    }
    @GetMapping("/visitor/meetings")
    public String meetings(Model model, HttpServletRequest request){
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登入，没有个人数据");
            return "/visitor/list";
        }
        else{
            String s = visitorUser.toString();
            int userid = userService.getuserid(s);
//            System.out.println("userid:"+userid);
            List<Meeting> meetings=umService.getbyuser(userid);
            model.addAttribute("meetings",meetings);
//            //System.out.println(s);
            return "/visitor/list";
        }
    }
    @DeleteMapping("/visitor/meeting/delete/{meeting_id}")
    public  String alter(@PathVariable("meeting_id")int meeting_id,Model model,HttpServletRequest request) {
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            return "redirect:/visitor/meetings";
        }
        else{
            String s = visitorUser.toString();
            int userid = userService.getuserid(s);
            umService.deleteUserMeeting(meeting_id,userid);
//            //System.out.println(s);
            return "redirect:/visitor/meetings";
        }
    }
    @GetMapping("/visitor/meeting/add")
    public  String add(Model model){
            List<Meeting> meetings = meetingService.getAll();
            model.addAttribute("meetings",meetings);
            return "/visitor/add";
    }
    @GetMapping("/visitor/meeting/search/{keyword}")
    public  String search(@PathVariable("keyword")String keyword, Model model){
        List<Meeting> meetings = meetingService.search(keyword);
        model.addAttribute("meetings",meetings);
        return "/visitor/add";
    }
    @GetMapping("/visitor/meeting/add/{meeting_id}")
    public  String add(Model model,HttpServletRequest request,@PathVariable("meeting_id") int meeting_id){
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登录，请先登录");
            return "/index";
        }
        else{
            String s = visitorUser.toString();
            int userid = userService.getuserid(s);
            if(!umService.searchMeeting(userid,meeting_id)){
                umService.insertUS(userid,meeting_id);
            }
            return "redirect:/visitor/meetings";
        }
    }

}
