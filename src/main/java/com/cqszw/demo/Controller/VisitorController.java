package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Bean.Paper;
import com.cqszw.demo.Bean.User;
import com.cqszw.demo.Bean.User_Meetings;
import com.cqszw.demo.Service.*;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    @Autowired
    private PaperService paperService;
    @Autowired
    private UPDService updService;
    @Autowired
    private UPDService upuService;
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
            return "visitor/dashboard";
        }
        else{
            String username = visitorUser.toString();
            List<Meeting> meetings=umService.getbyuseranddate(username,date);
            model.addAttribute("meetings",meetings);
//            //System.out.println(s);
            return "visitor/dashboard";
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
            return "visitor/list";
        }
        else{
            String username = visitorUser.toString();
            List<Meeting> meetings=umService.getbyuser(username);
            model.addAttribute("meetings",meetings);
//            //System.out.println(s);
            return "visitor/list";
        }
    }
    @DeleteMapping("/visitor/meeting/delete/{meeting_id}")
    public  String alter(@PathVariable("meeting_id")int meeting_id,Model model,HttpServletRequest request) {
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            return "redirect:/visitor/meetings";
        }
        else{
            String username = visitorUser.toString();
            umService.deleteUserMeeting(meeting_id,username);
//            //System.out.println(s);
            return "redirect:/visitor/meetings";
        }
    }
    @GetMapping("/visitor/meeting/add")
    public  String add(Model model){
            List<Meeting> meetings = meetingService.getAll();
            model.addAttribute("meetings",meetings);
            return "visitor/add";
    }
    @GetMapping("/visitor/meeting/search/{keyword}")
    public  String search(@PathVariable("keyword")String keyword, Model model){
        List<Meeting> meetings = meetingService.search(keyword);
        model.addAttribute("meetings",meetings);
        return "visitor/add";
    }
    @GetMapping("/visitor/meeting/add/{meeting_id}")
    public  String add(Model model,HttpServletRequest request,@PathVariable("meeting_id") int meeting_id){
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登录，请先登录");
            return "index";
        }
        else{
            String username = visitorUser.toString();
            if(!umService.searchMeeting(username,meeting_id)){
                umService.insertUS(username,meeting_id);
            }
            return "redirect:/visitor/meetings";
        }
    }
    @GetMapping("/visitor/paper/add")
    public  String addpaper(Model model){
        List<Paper> papers = paperService.getAll();
        model.addAttribute("papers",papers);
        return "visitor/addpaper";
    }
    @GetMapping("/visitor/downloads")
    public String downloads(Model model, HttpServletRequest request){
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登入，没有个人数据");
            return "visitor/downloadlist";
        }
        else{
            String username = visitorUser.toString();
            List<Paper> papers=updService.getpaperbyuser(username);
            model.addAttribute("papers",papers);
//            //System.out.println(s);
            return "visitor/downloadlist";
        }
    }
    @GetMapping("/visitor/uploads")
    public String uploads(Model model, HttpServletRequest request){
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登入，没有个人数据");
            return "visitor/uploadlist";
        }
        else{
            String username = visitorUser.toString();
            List<Paper> papers=upuService.getpaperbyuser(username);
            model.addAttribute("papers",papers);
//            //System.out.println(s);
            return "visitor/uploadlist";
        }
    }
    @RequestMapping("/visitor/paper/download/{paper_id}")
    public String downloadPaper(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable("paper_id") int paper_id) throws UnsupportedEncodingException{
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登录，请先登录");
            return "index";
        }
        else{
            String username = visitorUser.toString();
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = tempDate.format(new java.util.Date());
            updService.insertUPD(username,paper_id,datetime);
            String filename=paperService.gettopicbyid(paper_id)+".pdf";
            String filePath = "D:/file" ;
            File file = new File(filePath + "/" + filename);
            if(file.exists()){ //判断文件父目录是否存在
                response.setContentType("application/force-download");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
                byte[] buffer = new byte[1024];
                FileInputStream fis = null; //文件输入流
                BufferedInputStream bis = null;

                OutputStream os = null; //输出流
                try {
                    os = response.getOutputStream();
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    int i = bis.read(buffer);
                    while(i != -1){
                        os.write(buffer,0,i);
                        i = bis.read(buffer);
                    }
                    return "redirect:/visitor/downloads";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("----------file download---" + filename);
                try {
                    bis.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "redirect:/visitor/downloads";
        }
    }
    @RequestMapping("/visitor/paper/upload")
    @ResponseBody
    public String uploadPaper(Model model,HttpServletRequest request,@RequestParam("fileName") MultipartFile file){
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登录，请先登录");
            return "index";
        }
        else {
            if (file.isEmpty()) {
                return "false";
            }
            String username = visitorUser.toString();
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = tempDate.format(new java.util.Date());
            int nextid = paperService.gernum()+1;
            updService.insertUPD(username,nextid,datetime);
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            String path = "D:/file";
            File dest = new File(path + "/" + fileName);
            if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }
            try {
                file.transferTo(dest); //保存文件
                return "true";
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return "false";
            } catch (IOException e) {
                e.printStackTrace();
                return "false";
            }
        }
    }
}
