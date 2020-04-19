package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Mapper.MeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService {
    @Autowired
    private MeetingMapper meetingMapper;

    public  Meeting getMeetingByName(String name) {
        return meetingMapper.getbyname(name);
    }
    public  List<Meeting> getMeetingByDate(String date) { return meetingMapper.getbydate(date); }
    public  int insertMeeting(Meeting meeting){ return meetingMapper.insertMeeting(meeting);}
    public  List<Meeting> getAll(){return  meetingMapper.getAll();}
}
