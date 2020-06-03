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

        Integer pagenum=10;
        String page;
        String category;
        String url;
        List<String> lName=new LinkedList<String>();//用于存储名称
        List<String> lLocation=new LinkedList<String>();//用于存储地点
        List<String> lType=new LinkedList<String>();//用于存储类型
        List<String> lDate=new LinkedList<String>();//用于存储日期
        List<String> lURL=new LinkedList<String>();//用于存储URL
        List<String> lmeeting_All=new LinkedList<String>();
        try {
            for (int i=2;i<=9;i++) {
                for(int j=1;j<=pagenum;j++) {
                    category = i+"";
                    page = j+"";
                    url = "http://meeting.sciencenet.cn/index.php?s=%2FCategory%2Findex&cid="+category+"&p="+page;
                    Document doc = Jsoup.connect(url).get();
                    Elements content = doc.getElementsByClass("content680");
                    Elements tr = content.select("tr");
                    for (Element e1 : tr) {
                        Elements meetingName=e1.select("a[style]");//此处用于获取会议名称和URL
                        if(meetingName.text()=="") {continue;}
                        lName.add(meetingName.text());
                        lURL.add(meetingName.attr("href"));
                        Elements meetingLocation=e1.select("span[class=STYLE2]");//用于获取会议地点
                        lLocation.add(meetingLocation.text());
                        Elements meetingDate= e1.select("td[align=center]");//用于会议日期
                        lDate.add(meetingDate.text());
                        switch (i) {
                            case 2:lType.add("生命科学");break;
                            case 3:lType.add("地球科学");break;
                            case 4:lType.add("化学科学");break;
                            case 5:lType.add("信息科学");break;
                            case 6:lType.add("数理科学");break;
                            case 7:lType.add("医学科学");break;
                            case 8:lType.add("工程材料");break;
                            case 9:lType.add("管理综合");break;
                            default:lType.add(null);
                        }
                    }
                }
            }
            //System.out.println(sky.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<lName.size();i++) {
            //System.out.println("名称:"+lName.get(i)+",日期:"+lDate.get(i)+",地点:"+lLocation.get(i)+",URL:"+lURL.get(i));
            Meeting meeting=new Meeting(lName.get(i),lType.get(i),lLocation.get(i),lDate.get(i),lURL.get(i));
            //meeting.show();
            if(!meeting.is_null()) {
                if(!lURL.get(i).startsWith("http")){
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
