package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.User;
import com.cqszw.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @RequestMapping("/register")
    public String toRegister(){
        return "register";
    }
    @RequestMapping(value = "/newuser",method = RequestMethod.POST)
    public String register(@RequestParam("username")String username,@RequestParam("password")String password,
                           @RequestParam("confirm_password")String confirm_password,Map<String,Object> map){
        System.out.println("来到了注册");
        if(username.isEmpty()){
            map.put("msg","用户名不能为空");
            return "register";
        }
        else if(userService.searchUser(username)){
            map.put("msg","该用户已存在");
            return "register";
        }
        else if (!password.equals(confirm_password)){
            map.put("msg","两次密码输入不一致");
            return  "register";
        }
        else if (password.length()<6){
            map.put("msg","密码长度至少6位");
            return  "register";
        }
        else {
            User user = new User(username,password);
            userService.insertUser(user);
            map.put("msg","注册成功，可以进行登录了！");
            return  "index";
        }
    }
}
