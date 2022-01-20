package com.example.webapp.Builder;

import com.example.webapp.Objects.Event;
import com.example.webapp.Objects.Users;

public class UserBuilder implements Building{
    Users res;

    @Override
    public void reset() {
        this.res = new Users();
    }

    @Override
    public void setId(int id) {
        this.res.setId(id);

    }

    public Users getRes(){
        return this.res;
    }

    @Override
    public void setRole(String role) {
        this.res.setRole(role);
    }

    @Override
    public void setTitle(String text) {}

    @Override
    public void setEmail(String email) {
        this.res.setEmail(email);
    }

    @Override
    public void setPassword(String pass) {
        this.res.setPassword(pass);
    }

    @Override
    public void setTime(String ans) {}

    @Override
    public void setDate(String date) {}

    @Override
    public void setTopic(String temp) {}

    @Override
    public void setPlace(String temp) {}

    @Override
    public void setEvent(Event ev) {}

    @Override
    public void setUser(Users ev) {}
}
