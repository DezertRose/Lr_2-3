package com.example.webapp.GenerateDAO;

import com.example.webapp.Builder.Data;
import com.example.webapp.Builder.ReportBuilder;
import com.example.webapp.Builder.UserBuilder;
import com.example.webapp.DataBaseConection.Request;
import com.example.webapp.Objects.Report;
import com.example.webapp.Objects.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportDAO implements GenDAO<Report> {
    public ReportBuilder ub = new ReportBuilder();
    public Data data = new Data(this.ub);

    @Override
    public boolean insert(Report obj) throws SQLException {
        try {
            Request req = new Request("INSERT INTO TOPICS(TITLE)\n" +
                    "SELECT * \n" +
                    "FROM (SELECT '"+obj.getTopic()+"' as temp) as temt\n" +
                    "WHERE NOT EXISTS (\n" +
                    "    SELECT TITLE FROM TOPICS WHERE TITLE = '"+obj.getTopic()+"'\n" +
                    ")\n" +
                    "\n" +
                    "insert into REPORT\n" +
                    "values("+obj.getEvent().getId()+", (select tp.TIPIC_ID from TOPICS tp where tp.TITLE = '"+obj.getTopic()+"'), "+obj.getUser().getId()+", '"+obj.getPlace()+"', '"+obj.getTime()+"');");

            String response = (String)req.exec();
            System.out.println(response);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Report getById(int id) throws SQLException {
        Report user = new Report();
        try {
            Request req = new Request("select REPORT_ID, ev.TITLE as EV_TITLE, tp.TITLE, PLACE, REPORT_TIME from REPORT rp\n" +
                    "join EVENT_S ev\n" +
                    "on rp.EVENT_ID = ev.EVENT_ID\n" +
                    "join TOPICS tp\n" +
                    "on rp.TOPIC_ID = tp.TIPIC_ID\n" +
                    "join \"USER\" us\n" +
                    "on us.USERS_ID = us.USERS_ID" +
                    "where REPORT_ID = "+id+"");
            ResultSet res = (ResultSet)req.exec();

            while (res.next()){
                this.data.insideData.id = res.getInt("REPORT_ID");
                this.data.insideData.place = res.getString("PLACE");
                this.data.insideData.topic = res.getString("TITLE");
                this.data.insideData.time = res.getString("REPORT_TIME");
                EventDAO eventDAO = new EventDAO();
                UserDAO userDAO = new UserDAO();
                this.data.insideData.event = eventDAO.getById(res.getInt("EVENT_ID"));
                this.data.insideData.user = userDAO.getById(res.getInt("TOPIC_ID"));
                this.data.insert("report list");
                user = this.ub.getRes();
            }
            return user;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean update(Report obj) throws SQLException {
        try {
            Request req = new Request("INSERT INTO TOPICS(TITLE)\n" +
                    "SELECT * \n" +
                    "FROM (SELECT '"+obj.getTopic()+"' as temp) as temt\n" +
                    "WHERE NOT EXISTS (\n" +
                    "    SELECT TITLE FROM TOPICS WHERE TITLE = '"+obj.getTopic()+"'\n" +
                    ")\n" +
                    "\n" +
                    "update REPORT\n" +
                    "set EVENT_ID = "+obj.getEvent().getId()+", \n" +
                    "\tTOPIC_ID = (select tp.TIPIC_ID from TOPICS tp where tp.TITLE = '"+obj.getTopic()+"'),\n" +
                    "\tUSERS_ID = "+obj.getUser().getId()+",\n" +
                    "\tPLACE = '"+obj.getPlace()+"',\n" +
                    "\tREPORT_TIME = '"+obj.getTime()+"'\n" +
                    "where REPORT_ID = "+obj.getId()+";");

            String response = (String)req.exec();
            System.out.println(response);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Report obj) throws SQLException {
        try {
            Request req = new Request("Delete from REPORT where REPORT_ID ="+obj.getId());
            String response = (String)req.exec();
            System.out.println(response);
            return true;

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<Report> getAll() throws SQLException {
        ArrayList<Report> result = new ArrayList<>();
        try {
            Request req = new Request("select REPORT_ID, ev.TITLE as EV_TITLE, tp.TITLE, PLACE, REPORT_TIME from REPORT rp\n" +
                    "join EVENT_S ev\n" +
                    "on rp.EVENT_ID = ev.EVENT_ID\n" +
                    "join TOPICS tp\n" +
                    "on rp.TOPIC_ID = tp.TIPIC_ID\n" +
                    "join \"USER\" us\n" +
                    "on us.USERS_ID = us.USERS_ID");
            ResultSet res = (ResultSet)req.exec();

            while (res.next()){
                this.data.insideData.id = res.getInt("REPORT_ID");
                this.data.insideData.place = res.getString("PLACE");
                this.data.insideData.topic = res.getString("TITLE");
                this.data.insideData.time = res.getString("REPORT_TIME");
                EventDAO eventDAO = new EventDAO();
                UserDAO userDAO = new UserDAO();
                this.data.insideData.event = eventDAO.getById(res.getInt("EVENT_ID"));
                this.data.insideData.user = userDAO.getById(res.getInt("TOPIC_ID"));
                this.data.insert("report list");
                result.add(this.ub.getRes());
            }
            return result;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
