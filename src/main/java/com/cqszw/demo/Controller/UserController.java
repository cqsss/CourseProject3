package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.User;
import com.cqszw.demo.Service.UserService;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private User will_alter;
    @GetMapping("/users")
    public  String userlist(Model model){
//        System.out.println("所有用户");
        List<User> users=userService.getAll();
        //查询所有会议返回列表页面
        model.addAttribute("users",users);
        return  "user/list";
    }
    @GetMapping("/user")
    public  String toAddUser(Model model){
        //添加页面显示所有会议
        List<User>users=userService.getAll();
        //查询所有会议返回列表页面
//        System.out.println("查询所有用户");
        model.addAttribute("user",users);
        return  "user/add";
    }
    @PostMapping("/user")
    public  String addMeeting(User user){
        userService.insertUser(user);
        //最后回到员工列表页面
        return  "redirect:/users";
    }
    @GetMapping("/user/{username}")
    public  String alter(@PathVariable("username")String username, Model model, HttpServletRequest request){
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(username.equals("null")&& loginUser==null){

            return "redirect:/index";
        }
        User user = userService.getUserbyUsername(username);
        will_alter=user;
        model.addAttribute("user",user);
        return  "user/alter";
    }
    @PutMapping("/user")
    public String update(User user){
//        System.out.println("更新");
        if(will_alter!=null){
            //meeting.show();
            userService.updateUser(user,will_alter);
        }
        return "redirect:/users";
    }
    @DeleteMapping("/user/{username}")
    public  String deleteUser(@PathVariable("username")String username) {
//        System.out.println("删除");
        userService.deleteUser(username);
        return "redirect:/users";
    }
}
