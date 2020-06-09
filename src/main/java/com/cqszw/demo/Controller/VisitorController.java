package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.*;
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
import java.util.regex.Pattern;

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
    private UPUService upuService;
    @Autowired
    private NewsService newsService;
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
    public  String alterinformation(User user,Model model){
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        if(will_alter!=null){
            //meeting.show();
            if(user.getPassword().length()<6||user.getPassword().length()>50){
                model.addAttribute("msg","密码至少大于6位小于50位");
                model.addAttribute("user",will_alter);
                return "visitor/alter";
            }
            else if(user.getName().length()>100){
                model.addAttribute("msg","姓名不超过100个字符");
                model.addAttribute("user",will_alter);
                return "visitorr/alter";
            }
            else if(p.matcher(user.getTelephone()).find()||user.getTelephone().length()>100){
                model.addAttribute("msg","电话不能含有中文及中文字符，长度小于100");
                model.addAttribute("user",will_alter);
                return "visitor/alter";
            }
            else if(user.getIntroduce().length()>100){
                model.addAttribute("msg","个人简介不超过100个字符");
                model.addAttribute("user",will_alter);
                return "visitor/alter";
            }
            else{
                userService.updateUser(user,will_alter);
            }
        }
        return "redirect:/visitor/meetings";
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
            model.addAttribute("msg","未登陆，没有个人数据");
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
            model.addAttribute("msg","未登陆，没有个人数据");
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
    @GetMapping("/visitor/meeting/new")
    public  String toNewMeeting(HttpServletRequest request,Model model) {
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if (visitorUser == null) {
            model.addAttribute("msg", "未登陆，请先登录");
            return "index";
        } else {
            return "visitor/new";
        }
    }
    @PostMapping("/visitor/meeting")
    public String addMeeting(Meeting meeting,Model model){
        if(meeting.getName().isEmpty()){
            model.addAttribute("msg","会议名不能为空");
            return "visitor/new";
        }
        else if(meeting.getName().length()>100){
            model.addAttribute("msg","会议名不超过100个字符");
            return "visitor/new";
        }
        else if(meeting.getLocation().isEmpty()){
            model.addAttribute("msg","地址不能为空");
            model.addAttribute("user",will_alter);
            return "visitor/new";
        }
        else if(meeting.getDate().isEmpty()){
            model.addAttribute("msg","日期不能为空");
            model.addAttribute("user",will_alter);
            return "visitor/new";
        }
        else if(meeting.getLocation().length()>100){
            model.addAttribute("msg","地址不超过100个字符");
            model.addAttribute("user",will_alter);
            return "visitor/new";
        }
        else if(meeting.getUrl().length()>200){
            model.addAttribute("msg","地址不超过200个字符");
            model.addAttribute("user",will_alter);
            return "visitor/new";
        }
        else{
            meetingService.insertMeeting(meeting);
        }
        return  "redirect:/meetings";
    }
    @GetMapping("/visitor/meetings/category/{type}")
    public String meetingType(@PathVariable("type")String type, Model model) {
        List<Meeting> meetings = meetingService.getMeetingByType(type);
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
    @GetMapping("/visitor/newslist")
    public String news(Model model){
        List<News> newsList = newsService.getAll();
        model.addAttribute("newslist",newsList);
        return "visitor/news";
    }
    @GetMapping("/visitor/newslist/category/{type}")
    public String newsType(@PathVariable("type")String type, Model model) {
        List<News> newslist = newsService.getNewsByType(type);
        model.addAttribute("newslist",newslist);
        return "visitor/news";
    }
    @DeleteMapping("/visitor/news/{id}")
    public  String viewnews(@PathVariable("id")int id,Model model){
        News news = newsService.getById(id);
        newsService.viewcount(news);
        news=newsService.getById(id);
        model.addAttribute("news",news);
        return  "visitor/newsdetail";
    }
    @GetMapping("/visitor/paper/add")
    public  String addpaper(Model model){
        List<Paper> papers = paperService.getAll();
        model.addAttribute("papers",papers);
        return "visitor/addpaper";
    }
    @GetMapping("/visitor/papers/category/{type}")
    public String paperType(@PathVariable("type")String type, Model model) {
        List<Paper> papers = paperService.getPaperByType(type);
        model.addAttribute("papers",papers);
        return "visitor/addpaper";
    }
    @GetMapping("/visitor/downloads")
    public String downloads(Model model, HttpServletRequest request){
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登陆，没有个人数据");
            return "visitor/downloadlist";
        }
        else{
            String username = visitorUser.toString();
            List<Download_Record> papers = updService.download_records(username);
            model.addAttribute("papers",papers);
//            //System.out.println(s);
            return "visitor/downloadlist";
        }
    }
    @GetMapping("/visitor/uploads")
    public String uploads(Model model, HttpServletRequest request){
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登陆，没有个人数据");
            return "visitor/uploadlist";
        }
        else{
            String username = visitorUser.toString();
            List<Upload_Record> papers=upuService.upload_records(username);
            model.addAttribute("papers",papers);
//            //System.out.println(s);
            return "visitor/uploadlist";
        }
    }
    @GetMapping("/visitor/paper/download/{paper_id}")
    @ResponseBody
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("----------file download---" + filename);
                try {
                    os.flush();
                    os.close();
                    bis.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "redirect:/visitor/downloads";
        }
    }
    @GetMapping("visitor/paper/upload")
    public String toUploadPaper(Model model) {
        return "visitor/upload";
    }
    @PutMapping("/visitor/upload")
    public String uploadPaper(Model model,HttpServletRequest request,@RequestParam("fileName") MultipartFile file,Paper paper) throws UnsupportedEncodingException {
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登录，请先登录");
            return "index";
        }
        else {
            if (file.isEmpty()) {
                model.addAttribute("msg","未选择文件");
                return "/visitor/upload";
            }
            if(paper.getTopic().length()>100){
                model.addAttribute("msg","论文标题小于100个字符");
                return "/visitor/upload";
            }
            if(paper.getTopic().isEmpty()){
                model.addAttribute("msg","论文标题不能为空");
                return "/visitor/upload";
            }
            if(paper.getAuthor().isEmpty()){
                model.addAttribute("msg","作者不能为空");
                return "/visitor/upload";
            }
            if(paper.getAuthor().length()>100){
                model.addAttribute("msg","作者小于100个字符");
                return "/visitor/upload";
            }
            int begin = file.getOriginalFilename().indexOf(".");
            int last = file.getOriginalFilename().length();//获得文件后缀名
            String a = file.getOriginalFilename().substring(begin, last);
            if (!a.endsWith(".pdf")) {
                model.addAttribute("msg","当前只支持上传pdf文件类型");
                return "/visitor/upload";
            }
            Float MB = Float.parseFloat(String.valueOf(file.getSize()))/1024/1024;//1kb=1024b,1mb=1024kb
            if(MB>100){
                model.addAttribute("msg","单个文件不可以超过100MB");
                return "/visitor/upload";
            }
            String username = visitorUser.toString();
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = tempDate.format(new java.util.Date());
            paperService.insertPaper(paper);
            int nowid = paperService.getidByPaper(paper);
            upuService.insertUPU(username,nowid,datetime);
            String fileName = paper.getTopic()+".pdf";
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            String path = "D:/file";
            File dest = new File(path + "/" + fileName);
            if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }
            try {
                file.transferTo(dest); //保存文件
                return "redirect:/visitor/paper/add";
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
