package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Mapper.MeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingService {
    @Autowired
    private MeetingMapper meetingMapper;

    public  Meeting getMeetingByName(String name) {
        return meetingMapper.getbyname(name);
    }
    public  int insertMeeting(Meeting meeting){ return meetingMapper.insertMeeting(meeting);}
}
