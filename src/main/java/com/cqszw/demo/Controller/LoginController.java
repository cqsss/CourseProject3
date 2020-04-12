package com.cqszw.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
   @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object>map, HttpSession httpSession){
            if(!StringUtils.isEmpty(username)&&password.equals("123456")){
                httpSession.setAttribute("loginUser",username);
                return "redirect:/main.html";
            }
            else{
                map.put("msg","用户密码错误");
                return "index";//为了防止表单重复提交，可以重定向
            }

    }
}
