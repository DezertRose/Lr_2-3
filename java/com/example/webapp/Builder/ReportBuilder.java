package com.example.webapp.Builder;

import com.example.webapp.Objects.Event;
import com.example.webapp.Objects.Report;
import com.example.webapp.Objects.Users;

public class ReportBuilder implements Building{
    Report res;

    @Override
    public void reset() {
        this.res = new Report();
    }

    public Report getRes(){
        return this.res;
    }

    @Override
    public void setId(int id) {
        this.res.setId(id);
    }

    @Override
    public void setTitle(String text) {}

    @Override
    public void setEmail(String email) {}

    @Override
    public void setPassword(String pass) {}

    @Override
    public void setTime(String ans) {
        this.res.setTime(ans);
    }

    @Override
    public void setDate(String date) {}

    @Override
    public void setTopic(String temp) {
        this.res.setTopic(temp);

    }

    @Override
    public void setPlace(String temp) {
        this.res.setPlace(temp);
    }

    @Override
    public void setEvent(Event ev) {
        this.res.setEvent(ev);
    }

    @Override
    public void setUser(Users ev) {
        this.res.setUser(ev);
    }

    @Override
    public void setRole(String role) {}
}
