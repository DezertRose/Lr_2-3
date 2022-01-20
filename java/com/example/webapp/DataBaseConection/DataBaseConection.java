package com.example.webapp.DataBaseConection;

import java.sql.*;

public class DataBaseConection {
    static String db = "EventsSchedule";
    static String serverip="localhost";
    static String serverport="1433";
    static String url = "jdbc:sqlserver://"+serverip+":"+serverport+";databaseName="+db+"";
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private DataBaseConection(){};
    private static Connection conn = null;

    public static Connection connectDB() {
        String databaseUserName = "sa";
        String databasePassword = "1";
        try {
            if(conn == null){
                Class.forName(driver).getDeclaredConstructor().newInstance();
                conn = DriverManager.getConnection(url, databaseUserName, databasePassword);
                System.out.println("Conect to DB");
            }
            else {
                if (conn.isClosed()){
                    Class.forName(driver).getDeclaredConstructor().newInstance();
                    conn = DriverManager.getConnection(url, databaseUserName, databasePassword);
                    System.out.println("Conect to DB");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void closeConnection(){
        try {
            conn.close();
            System.out.println("Close conection to DB");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
