package com.example.webapp.Objects;

public class Report {
    private int id;
    private Event event;
    private String topic;
    private Users user;
    private String place;
    private String time;

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public String getPlace() {
        return place;
    }

    public String getTopic() {
        return topic;
    }

    public Users getUser() {
        return user;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
