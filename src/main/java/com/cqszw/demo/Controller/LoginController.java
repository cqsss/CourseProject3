package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.User;
import com.cqszw.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object>map, HttpSession httpSession){
        User user=new User();
        if(userService.searchUser(username)){
             user = userService.getUserbyUsername(username);
        }
        else {
            map.put("msg","用户不存在");
            return "index";//为了防止表单重复提交，可以重定向
        }
        if(!StringUtils.isEmpty(username)&&password.equals(user.getPassword())){
                if(user.isIs_manager()){
                    httpSession.setAttribute("loginUser",username);
                    return "redirect:/dashboard.html";
                }
                else{
                    map.put("msg","权限不足");
                    return "index";//为了防止表单重复提交，可以重定向
                }
            }
            else{
                map.put("msg","用户密码错误");
                return "index";//为了防止表单重复提交，可以重定向
            }

    }
}
