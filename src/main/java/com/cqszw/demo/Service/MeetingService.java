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

    public List<Meeting> getMeetingByName(String name) {
        return meetingMapper.getbyname(name);
    }
    public List<Meeting> getMeetingByDate(String date) {
        return meetingMapper.getbydate(date);
    }
    public int insertMeeting(Meeting meeting){ return meetingMapper.insertMeeting(meeting);}
    public List<Meeting> getAll(){return  meetingMapper.getAll();}
    public Meeting getMeeting(String name,String location,String date){return meetingMapper.getMeeting(name, location, date);}
    public int updateMeeting(Meeting meeting,Meeting old_meeting){
        return  meetingMapper.updateMeeting(meeting.getName(),meeting.getLocation(),meeting.getContent(),
                meeting.getDate(),meeting.getLongitude(),meeting.getLatitude(),old_meeting.getName(),
                old_meeting.getLocation(),old_meeting.getDate());
    }
    public int deleteMeeting(String name,String location,String date){
        return  meetingMapper.deleteMeeting(name,location,date);
    }
}
