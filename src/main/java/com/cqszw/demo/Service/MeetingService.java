package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Mapper.MeetingMapper;
import org.apache.ibatis.annotations.Param;
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
    public List<Meeting> getMeetingByType(String type) {return meetingMapper.getbytype(type);};
    public int insertMeeting(Meeting meeting){ return meetingMapper.insertMeeting(meeting);}
    public int visitorMeeting(Meeting meeting){ return meetingMapper.visitorMeeting(meeting);}
    public List<Meeting> getAll(){return  meetingMapper.getAll();}
    public Meeting getMeeting(String name,String location,String date){return meetingMapper.getMeeting(name, location, date);}
    public Meeting getMeetingAll(String name,String location,String date){return meetingMapper.getMeetingAll(name, location, date);}
    public int updateMeeting(Meeting meeting,Meeting old_meeting){
        return  meetingMapper.updateMeeting(meeting.getName(),meeting.getLocation(),
                meeting.getDate(),meeting.getUrl(),old_meeting.getName(),
                old_meeting.getLocation(),old_meeting.getDate());
    }
    public int deleteMeeting(String name,String location,String date){
        return  meetingMapper.deleteMeeting(name,location,date);
    }
    public boolean searchMeeting(String name,String location,String date){
        if(meetingMapper.getMeeting(name, location, date)!=null){
            return  true;
        }
        else {
            return  false;
        }
    }
    public  List<Meeting> search(String keyword){return  meetingMapper.search(keyword);}
    public  List<Meeting> searchandtype(String type,String keyword){return  meetingMapper.searchandtype(type,keyword);}
    public  List<Meeting> unchecked(){return  meetingMapper.getUnchecked();}
    public  int passcheck(int id){return  meetingMapper.passcheck(id);}
    public  int confuse(int id){return  meetingMapper.confuseMeeting(id);}
    public  int deleteById(int id){return  meetingMapper.deleteById(id);}
    public  Meeting getById(int id){return meetingMapper.getById(id);}
    public  int clear(int id){return  meetingMapper.clear(id);}

}
