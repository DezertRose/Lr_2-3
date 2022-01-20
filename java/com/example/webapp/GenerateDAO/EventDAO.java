package com.example.webapp.GenerateDAO;

import com.example.webapp.Builder.Data;
import com.example.webapp.Builder.EventBuilder;
import com.example.webapp.Builder.UserBuilder;
import com.example.webapp.DataBaseConection.Request;
import com.example.webapp.Objects.Event;
import com.example.webapp.Objects.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventDAO implements GenDAO<Event>{
    public EventBuilder ub = new EventBuilder();
    public Data data = new Data(this.ub);

    @Override
    public boolean insert(Event obj) throws SQLException {
        try {
            Request req = new Request("insert into EVENT_S\n" +
                    "values ('"+obj.getTitle()+"', '"+obj.getTime()+"');");

            String response = (String)req.exec();
            System.out.println(response);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Event getById(int id) throws SQLException {
        Event user = new Event();
        try {
            Request req = new Request("select EVENT_ID, TITLE, EVENT_DATA from EVENT_S\n" +
                    "where EVENT_ID = "+id+";");
            ResultSet res = (ResultSet) req.exec();
            while (res.next()){
                this.data.insideData.id = res.getInt("USERS_ID");
                this.data.insideData.email = res.getString("EMAIL");
                this.data.insideData.password = res.getString("PASSWORDS");
                this.data.insideData.role = res.getString("TITLE");
                this.data.insert("user");
                user = this.ub.getResult();

            }
            return user;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean update(Event obj) throws SQLException {
        try {
            Request req = new Request("update EVENT_S\n" +
                    "set TITLE = '"+obj.getTitle()+"',\n" +
                    "\tEVENT_DATA = '"+obj.getTime()+"'\n" +
                    "where EVENT_ID = "+obj.getId()+";");
            String response = (String)req.exec();
            System.out.println(response);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Event obj) throws SQLException {
        try {
            Request req = new Request("Delete from EVENT_S where EVENT_ID ="+obj.getId());
            String response = (String)req.exec();
            System.out.println(response);
            return true;

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<Event> getAll() throws SQLException {
        ArrayList<Event> result = new ArrayList<>();
        try {
            Request req = new Request("select * from EVENT_S");
            ResultSet res = (ResultSet)req.exec();

            while (res.next()){
                this.data.insideData.id = res.getInt("EVENT_ID");
                this.data.insideData.title = res.getString("TITLE");
                this.data.insideData.date = res.getString("EVENT_DATA");

                this.data.insert("event list");
                result.add(this.ub.getResult());
            }
            return result;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
