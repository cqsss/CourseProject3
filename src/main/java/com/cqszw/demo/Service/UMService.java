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
    public List<User_Meetings> getbyuser(int id){return umMapper.getbyuser(id);}
    public List<Meeting> getbyuseranddate(int user_id,String date){return umMapper.getbyuseranddate(user_id,date);}
}
