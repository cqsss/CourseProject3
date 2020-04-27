package com.cqszw.demo;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Service.MeetingService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lenovo
 * @date 2020/4/22
 */
@Component
public class GetMeeting {
    @Autowired
    private  MeetingService meetingService;
    @Scheduled(cron = "0/20 * * * * ? ")
    public void getMeetings() throws IOException {

        Integer pagenum=1;
        String page;
        String url;
        List<String> lName=new LinkedList<String>();//用于存储名称
        List<String> lLocation=new LinkedList<String>();//用于存储地点
        List<String> lDate=new LinkedList<String>();//用于存储日期
        List<String> lURL=new LinkedList<String>();//用于存储URL
        List<String> lmeeting_All=new LinkedList<String>();
        try {
            for(int i=1;i<=pagenum;i++) {
                page=i+"";
                url = "http://meeting.sciencenet.cn/index.php?s=%2FCategory%2Fallmt&allid=1&p="+page;
                Document doc = Jsoup.connect(url).get();
                Elements content = doc.getElementsByClass("content680");
                Elements tr = content.select("tr");
                for (Element e1 : tr) {
                    Elements meetingName=e1.select("a[style]");//此处用于获取会议名称和URL
                    if(meetingName.text()=="") continue;
                    lName.add(meetingName.text());
                    lURL.add(meetingName.attr("href"));
                    Elements meetingLocation=e1.select("span[class=STYLE2]");//用于获取会议地点
                    lLocation.add(meetingLocation.text());
                    Elements meetingDate= e1.select("td[align=center]");//用于会议日期
                    lDate.add(meetingDate.text());
                }
            }
            //System.out.println(sky.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("更新查询结果完毕！！");
        for(int i=0;i<lName.size();i++) {
            //System.out.println("名称:"+lName.get(i)+",日期:"+lDate.get(i)+",地点:"+lLocation.get(i)+",URL:"+lURL.get(i));
            Meeting meeting=new Meeting(lName.get(i),lLocation.get(i),lDate.get(i),lURL.get(i));
            //meeting.show();
            if(!meeting.is_null()) {
                if(!lURL.get(i).startsWith("http://")){
                    StringBuilder stringBuilder=new StringBuilder(meeting.getUrl());
                    stringBuilder.insert(0,"http://meeting.sciencenet.cn/");
                    meeting.setUrl(stringBuilder.toString());
                }
                if(!meetingService.searchMeeting(meeting.getName(),meeting.getLocation(),meeting.getDate())){
                    meetingService.insertMeeting(meeting);
                }
            }
           // meetingService.insertMeeting(new Meeting(lName.get(i),lLocation.get(i),lDate.get(i),lURL.get(i)));
        }

    }
}
