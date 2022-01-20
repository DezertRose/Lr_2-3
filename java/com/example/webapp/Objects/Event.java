package com.example.webapp.Objects;

public class Event {
    private int id;
    private String title;
    private String date;

    public int getId() {
        return id;
    }

    public String getTime() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.date = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
