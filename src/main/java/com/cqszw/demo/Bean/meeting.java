package com.cqszw.demo.Bean;

import java.util.Date;

public class meeting {
    private  String name;
    private  String content;
    private  String location;
    private String date;
    private  double longitude;
    private  double latitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void  show(){
        System.out.println("name: "+name);
        System.out.println("content: "+content);
        System.out.println("location: "+location);
        System.out.println("longitude: "+longitude);
        System.out.println("latitude: "+latitude);
        System.out.println("date: "+date);

    }
}
