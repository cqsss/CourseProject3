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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public  String addMeeting(User user,Model model){
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        if(user.getPassword().length()<6||user.getPassword().length()>50){
            model.addAttribute("msg","密码至少大于6位小于50位");
            return "user/add";
        }
        else if(user.getName().length()>100){
            model.addAttribute("msg","姓名不超过100个字符");
            return "user/add";
        }
        else if(p.matcher(user.getTelephone()).find()||user.getTelephone().length()>100){
            model.addAttribute("msg","电话不能含有中文及中文字符，长度小于100");
            return "user/add";
        }
        else if(user.getIntroduce().length()>100){
            model.addAttribute("msg","个人简介不超过100个字符");
            return "user/add";
        }
        else{
            userService.insertUser(user);
        }

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
    public String update(User user,Model model){
//        System.out.println("更新");
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        if(will_alter!=null){
            //meeting.show();
//            System.out.println(user.getName().length());
            if(user.getPassword().length()<6||user.getPassword().length()>50){
                model.addAttribute("msg","密码至少大于6位小于50位");
                model.addAttribute("user",will_alter);
                return "user/alter";
            }
            else if(user.getName().length()>100){
                model.addAttribute("msg","姓名不超过100个字符");
                model.addAttribute("user",will_alter);
                return "user/alter";
            }
            else if(p.matcher(user.getTelephone()).find()||user.getTelephone().length()>100){
                model.addAttribute("msg","电话不能含有中文及中文字符，长度小于100");
                model.addAttribute("user",will_alter);
                return "user/alter";
            }
            else if(user.getIntroduce().length()>100){
                model.addAttribute("msg","个人简介不超过100个字符");
                model.addAttribute("user",will_alter);
                return "user/alter";
            }
            else{
                userService.updateUser(user,will_alter);
            }
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
