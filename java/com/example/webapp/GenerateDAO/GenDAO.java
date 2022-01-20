package com.example.webapp.GenerateDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GenDAO<T> {
    boolean insert(T obj) throws SQLException;

    T getById(int id) throws SQLException;

    boolean update(T obj) throws SQLException;

    boolean delete(T obj) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

}
