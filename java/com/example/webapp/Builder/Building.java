package com.example.webapp.Builder;

import com.example.webapp.Objects.*;

import java.util.ArrayList;

public interface Building {

    public void reset();

    public void setId(int id);
    public void setTitle(String text);
    public void setEmail(String email);
    public void setPassword(String pass);
    public void setTime(String ans);
    public void setDate(String date);
    public void setTopic(String temp);
    public void setPlace(String temp);
    public void setEvent(Event ev);
    public void setUser(Users ev);
    public void setRole(String role);


}

