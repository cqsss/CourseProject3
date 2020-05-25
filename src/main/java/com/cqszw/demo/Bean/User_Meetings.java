package com.cqszw.demo.Bean;

import java.util.List;

public class User_Meetings {
    private String username;
    private int meeting_id;
    private List<Meeting>meetings;
    public String getUser_id() {
        return username;
    }

    public void setUser_id(String username) {
        this.username = username;
    }

    public int getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(int meeting_id) {
        this.meeting_id = meeting_id;
    }
}
