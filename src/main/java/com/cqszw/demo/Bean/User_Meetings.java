package com.cqszw.demo.Bean;

import java.util.List;

public class User_Meetings {
    private int user_id;
    private int meeting_id;
    private List<Meeting>meetings;
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(int meeting_id) {
        this.meeting_id = meeting_id;
    }
}
