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
    public List<Meeting> getbyuser(int id){return umMapper.getbyuser(id);}
    public List<Meeting> getbyuseranddate(int user_id,String date){return umMapper.getbyuseranddate(user_id,date);}
    public  int deleteUserMeeting(int meeting_id,int user_id){return  umMapper.deleteUserMeeting(meeting_id,user_id);}
    public boolean searchMeeting(int user_id,int meeting_id){
        if(umMapper.get(user_id,meeting_id)!=null){
            return  true;
        }
        else {
            return  false;
        }
    }
    public int insertUS(int user_id,int meeting_id){return umMapper.insert(user_id,meeting_id);}
}
