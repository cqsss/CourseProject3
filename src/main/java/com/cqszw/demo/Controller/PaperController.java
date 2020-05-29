package com.cqszw.demo.Controller;

import com.cqszw.demo.Bean.Paper;
import com.cqszw.demo.Mapper.PaperMapper;
import com.cqszw.demo.Service.PaperService;
import com.cqszw.demo.Service.UPDService;
import com.cqszw.demo.Service.UPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.dc.path.PathError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author cqs
 * @date 2020/5/29
 */
@Controller
public class PaperController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private UPDService updService;
    @Autowired
    private UPUService upuService;
    @GetMapping("/papers")
    public String list(Model model){

        List<Paper> papers=paperService.getAll();
        //查询所有会议返回列表页面
        model.addAttribute("papers",papers);
        return  "paper/list";
    }
    @GetMapping("/downloads")
    public String downloads(Model model, HttpServletRequest request){
        Object visitorUser = request.getSession().getAttribute("visitorUser");
        if(visitorUser==null){
            model.addAttribute("msg","未登入，没有个人数据");
            return "paper/downloadlist";
        }
        else{
            String username = visitorUser.toString();
            List<Paper> papers=updService.getpaperbyuser(username);
            model.addAttribute("papers",papers);
//            //System.out.println(s);
            return "paper/downloadlist";
        }
    }
    @GetMapping("/uploads")
    public String uploads(Model model, HttpServletRequest request){
        Object User = request.getSession().getAttribute("loginUser");
        if(User==null){
            model.addAttribute("msg","未登入，没有个人数据");
            return "paper/uploadlist";
        }
        else{
            String username = User.toString();
            List<Paper> papers=upuService.getpaperbyuser(username);
            model.addAttribute("papers",papers);
//            //System.out.println(s);
            return "paper/uploadlist";
        }
    }
    @GetMapping("/paper/download/{paper_id}")
    @ResponseBody
    public String downloadPaper(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("paper_id") int paper_id) throws UnsupportedEncodingException{
        Object user = request.getSession().getAttribute("loginUser");
        if(user==null){
            model.addAttribute("msg","未登录，请先登录");
            return "redirect:/index";
        }
        else{
            String username = user.toString();
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
            return "paper/downloadlist";
        }
    }
    @GetMapping("/paper/delete/{paper_id}")
    public String deletePaper(@PathVariable("paper_id") int paper_id) {
        String filename=paperService.gettopicbyid(paper_id)+".pdf";
        String filePath = "D:/file" ;
        File file = new File(filePath + "/" + filename);
        paperService.deletePaper(paper_id);
        System.out.println(filePath + "/" + filename);
        // 路径为文件且不为空则进行删除
        if(file.exists()){ //判断文件父目录是否存在
            file.delete();
            return "redirect:/papers";
        }
        return "redirect:/papers";
    }
    @GetMapping("/paper/upload")
    public String toUploadPaper(Model model) {
        return "paper/upload";
    }
    @PutMapping("/upload")
    public String uploadPaper(Model model, HttpServletRequest request, @RequestParam("fileName") MultipartFile file, Paper paper) throws UnsupportedEncodingException {
        Object user = request.getSession().getAttribute("loginUser");
        if(user==null){
            model.addAttribute("msg","未登录，请先登录");
            return "index";
        }
        else {
            if (file.isEmpty()) {
                return "redirect:/papers";
            }
            String username = user.toString();
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = tempDate.format(new java.util.Date());
            int nextid = paperService.gernum()+1;
            paper.setId(nextid);
            upuService.insertUPU(username,nextid,datetime);
            paperService.insertPaper(paper);
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
                return "redirect:/papers";
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
