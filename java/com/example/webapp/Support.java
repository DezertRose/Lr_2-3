package com.example.webapp;

import com.example.webapp.GenerateDAO.EventDAO;
import com.example.webapp.GenerateDAO.UserDAO;
import com.example.webapp.Objects.Event;
import com.example.webapp.Objects.Users;

import java.sql.SQLException;
import java.util.ArrayList;

public class Support {

    public ArrayList<Users> allUsers = new ArrayList<>();
    public ArrayList<Event> allEvents = new ArrayList<>();

    public int takeUserRole(String email, String pass){
        UserDAO userDAO = new UserDAO();
        try {
            allUsers = userDAO.getAll();
            for (int i =0; i<allUsers.size(); i++){
                if(allUsers.get(i).getEmail().equals(email) && allUsers.get(i).getPassword().equals(pass)){
                    return i;
                }
            }
        }
        catch (Exception ex){
            System.out.println("Something wrong with data!");
        }
        return 0;
    }

    public boolean insertEvent(String event, String data){
        EventDAO evDAO = new EventDAO();
        try {
            allEvents = evDAO.getAll();

            Event ev = new Event();
            ev.setTitle(event);
            ev.setTime(event);

            evDAO.insert(ev);
            return true;
        }
        catch (Exception ex){ex.printStackTrace();}
        return false;
    }


}
