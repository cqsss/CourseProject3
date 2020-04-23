package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.User;
import com.cqszw.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public  String userlist(Model model){
        System.out.println("所有用户");
        List<User> users=userService.getAll();
        //查询所有会议返回列表页面
        model.addAttribute("users",users);
        return  "user/list";
    }
}
