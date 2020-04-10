package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.meeting;
import com.cqszw.demo.Mapper.meetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class meetingService {
    @Autowired
    private meetingMapper meetingMapper;

    public meeting getMeetingByName(String name) {
        return meetingMapper.getbyname(name);
    }
}
