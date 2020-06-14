package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.User_Publish;
import com.cqszw.demo.Bean.User_Publish_Record;
import com.cqszw.demo.Mapper.UPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UPService {
    @Autowired
    UPMapper upMapper;
    public int insert(User_Publish user_publish){return upMapper.insert(user_publish);}
    public int pass(int meeting_id){return  upMapper.pass(meeting_id);}
    public int confuse(int meeting_id){return  upMapper.confuse(meeting_id);}
    public List<User_Publish_Record> getAll(String username){return  upMapper.getAll(username);}
    public User_Publish getById(int meeting_id){return  upMapper.getById(meeting_id);}
    public int deleteById(int meeting_id){return upMapper.deleteById(meeting_id);}
    public int clear(int meeting_id){return  upMapper.clear(meeting_id);}
}
