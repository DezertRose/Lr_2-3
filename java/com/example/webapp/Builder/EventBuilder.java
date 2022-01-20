package com.example.webapp.Builder;

import com.example.webapp.Objects.Event;
import com.example.webapp.Objects.Users;

public class EventBuilder implements Building{
    Event res;

    @Override
    public void reset() {
        this.res = new Event();
    }

    public Event getResult(){
        return this.res;
    }

    @Override
    public void setId(int id) {
        this.res.setId(id);
    }

    @Override
    public void setTitle(String text) {
        this.res.setTitle(text);
    }

    @Override
    public void setEmail(String email) {}

    @Override
    public void setPassword(String pass) {}

    @Override
    public void setTime(String ans) {}

    @Override
    public void setDate(String date) {
        this.res.setTime(date);
    }

    @Override
    public void setTopic(String temp) {}

    @Override
    public void setPlace(String temp) {}

    @Override
    public void setEvent(Event ev) {}

    @Override
    public void setUser(Users ev) {}

    @Override
    public void setRole(String role) {}
}
