package com.cqszw.demo.Bean;

/**
 * @author cqs
 * @date 2020/5/29
 */
public class Download_Record {
    private String type;
    private String topic;
    private String author;
    private String keyword;
    private String downloadtime;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDownloadtime() {
        return downloadtime;
    }

    public void setDownloadtime(String downloadtime) {
        this.downloadtime = downloadtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
