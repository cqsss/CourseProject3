package com.cqszw.demo.Bean;

import java.util.Date;

public class Meeting {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private  int id;

    public Meeting(String name, String type, String location, String date, String url) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.location = location;
        this.date = date;
        this.url = url;
    }

    private  String name;
    private  String location;
    private  String date;
    private  String url;
    private  String type;
    private  int checked;

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Meeting() {

    }

    public void  show(){
        System.out.println("name: "+name);
        System.out.println("type: "+type);
        System.out.println("location: "+location);
        System.out.println("date: "+date);
        System.out.println("url: "+url);

    }
    public boolean is_null(){
        if(name.isEmpty()||date.isEmpty()||location.isEmpty()){
            return  true;

        }
        else {
            return false;
        }
    }
}
