package com.example.webapp.Builder;

import com.example.webapp.Objects.Event;
import com.example.webapp.Objects.Users;

public class Data {
    public insideData insideData;
    Building builder;

    public Data(Building builder){
        this.builder = builder;
        insideData = new insideData();
    }

    public void changeBuilder(Building builder){
        this.builder = builder;
    }

    public class insideData{
        public int id = 0;
        public String email = null;
        public String password = null;
        public String title = null;
        public String date = null;
        public Event event = null;
        public String topic = null;
        public Users user = null;
        public String place = null;
        public String time = null;
        public String role = null;
    }

    public void insert(String type){
        builder.reset();

        builder.setEmail(insideData.email);
        builder.setEvent(insideData.event);
        builder.setPlace(insideData.place);
        builder.setTime(insideData.time);
        builder.setTitle(insideData.title);
        builder.setDate(insideData.date);
        builder.setId(insideData.id);
        builder.setPassword(insideData.password);
        builder.setTopic(insideData.topic);
        builder.setUser(insideData.user);
        builder.setRole(insideData.role);
    }
}
