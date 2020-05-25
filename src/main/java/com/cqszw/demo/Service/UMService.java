package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Bean.User_Meetings;
import com.cqszw.demo.Mapper.UMMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UMService {

    @Autowired
    private UMMapper umMapper;
    public List<Meeting> getbyuser(String username){return umMapper.getbyuser(username);}
    public List<Meeting> getbyuseranddate(String username,String date){return umMapper.getbyuseranddate(username,date);}
    public  int deleteUserMeeting(int meeting_id,String username){return  umMapper.deleteUserMeeting(meeting_id,username);}
    public boolean searchMeeting(String username,int meeting_id){
        if(umMapper.get(username,meeting_id)!=null){
            return  true;
        }
        else {
            return  false;
        }
    }
    public int insertUS(String username,int meeting_id){return umMapper.insert(username,meeting_id);}
}
