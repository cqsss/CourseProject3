package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.User;
import com.cqszw.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object>map, HttpSession httpSession,Model model){
        User user=new User();
        if(userService.searchUser(username)){
             user = userService.getUserbyUsername(username);
        }
        else {
            map.put("msg","用户不存在或密码错误");
            return "index";//为了防止表单重复提交，可以重定向
        }
        if(!StringUtils.isEmpty(username)&&password.equals(user.getPassword())){
                if(user.isIs_manager()){
                    httpSession.setAttribute("loginUser",username);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                    System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
                    String date=df.format(new Date());

                    return "redirect:/table/"+date;
                }
                else{
                    httpSession.setAttribute("loginUser",username);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                    System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
                    String date=df.format(new Date());
                    //map.put("msg","权限不足");
                    httpSession.setAttribute("visitorUser",username);
                    return "redirect:/visitor/table/"+date;//为了防止表单重复提交，可以重定向
                }
            }
            else{
                map.put("msg","用户不存在或用户密码错误");
                return "index";//为了防止表单重复提交，可以重定向
            }

    }
    @GetMapping(value = "/logout/{username}")
    public String logout(HttpSession httpSession, @PathVariable("username") String username, Model model){
        System.out.println("用户："+username+" logout");
        httpSession.removeAttribute(username);
        httpSession.invalidate();
        model.addAttribute("msg","注销成功");
        return  "index";
    }
}
