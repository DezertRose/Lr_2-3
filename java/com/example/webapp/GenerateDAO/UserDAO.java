package com.example.webapp.GenerateDAO;

import com.example.webapp.Builder.Data;
import com.example.webapp.Builder.UserBuilder;
import com.example.webapp.DataBaseConection.*;
import com.example.webapp.Objects.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements GenDAO<Users>{
    public UserBuilder ub = new UserBuilder();
    public Data data = new Data(this.ub);

    @Override
    public boolean insert(Users obj) throws SQLException {
        try {
            Request req = new Request("INSERT INTO ROLES(TITLE)\n" +
                    "SELECT * \n" +
                    "FROM (SELECT '"+obj.getRole()+"' as temp) as temt\n" +
                    "WHERE NOT EXISTS (\n" +
                    "    SELECT TITLE FROM ROLES WHERE TITLE = '"+obj.getRole()+"'\n" +
                    ")\n" +
                    "\n" +
                    "insert into \"USER\"\n" +
                    "values('"+obj.getEmail()+"', '"+obj.getPassword()+"', (select ROLE_ID from ROLES ro where ro.TITLE = '"+obj.getRole()+"'))");

            String response = (String)req.exec();
            System.out.println(response);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Users getById(int id) throws SQLException {
        Users user = new Users();
        try {
            Request req = new Request("select us.USERS_ID, us.EMAIL, us.PASSWORDS, rol.TITLE from \"USER\" us\n" +
                    "left join ROLES rol\n" +
                    "on us.ROLE_ID = rol.ROLE_ID\n" +
                    "where us.USERS_ID = "+id+"");
            ResultSet res = (ResultSet) req.exec();
            while (res.next()){
                this.data.insideData.id = res.getInt("USERS_ID");
                this.data.insideData.email = res.getString("EMAIL");
                this.data.insideData.password = res.getString("PASSWORDS");
                this.data.insideData.role = res.getString("TITLE");
                this.data.insert("user");
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
    public boolean update(Users obj) throws SQLException {
        try {
            Request req = new Request("INSERT INTO ROLES(TITLE)\n" +
                    "SELECT * \n" +
                    "FROM (SELECT 'TRET' as temp) as temt\n" +
                    "WHERE NOT EXISTS (\n" +
                    "    SELECT TITLE FROM ROLES WHERE TITLE = '"+obj.getRole()+"'\n" +
                    ")\n" +
                    "\n" +
                    "update \"USER\" \n" +
                    "set EMAIL = '"+obj.getEmail()+"',\n" +
                    "\tPASSWORDS = '"+obj.getPassword()+"', \n" +
                    "\tROLE_ID = (select ROLE_ID from ROLES ro where ro.TITLE = '"+obj.getRole()+"');");

            String response = (String)req.exec();
            System.out.println(response);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Users obj) throws SQLException {
        try {
            Request req = new Request("Delete from \"USER\" where USERS_ID ="+obj.getId());
            String response = (String)req.exec();
            System.out.println(response);
            return true;

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<Users> getAll() throws SQLException {
        ArrayList<Users> result = new ArrayList<>();
        try {
            Request req = new Request("select us.USERS_ID, us.EMAIL, us.PASSWORDS, rol.TITLE from \"USER\" us\n" +
                    "left join ROLES rol\n" +
                    "on us.ROLE_ID = rol.ROLE_ID;");
            ResultSet res = (ResultSet)req.exec();

            while (res.next()){
                this.data.insideData.id = res.getInt("USERS_ID");
                this.data.insideData.email = res.getString("EMAIL");
                this.data.insideData.password = res.getString("PASSWORDS");
                this.data.insideData.role = res.getString("TITLE");
                this.data.insert("user");
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
