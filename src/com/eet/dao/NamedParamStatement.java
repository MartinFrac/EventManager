package com.eet.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamedParamStatement {

    private PreparedStatement prepStmt;
    private Map<String, List<Integer>> fields = new HashMap<>();

    public NamedParamStatement(Connection conn, String sql) throws SQLException {
        int pos;
        int counter = 1;
        while((pos = sql.indexOf(":")) != -1) {
            int end = sql.substring(pos).indexOf(" ");
            if (end == -1) {
                end = sql.length();
            }
            else {
                end += pos;
            }
            String placeholder = sql.substring(pos+1,end);
            if (!fields.containsKey(placeholder)) {
                List<Integer> positions = new ArrayList<>();
                positions.add(counter);
                fields.put(placeholder, positions);
            } else {
                fields.get(placeholder).add(counter);
            }
            sql = sql.substring(0, pos) + "?" + sql.substring(end);
            counter++;
        }
        prepStmt = conn.prepareStatement(sql);
    }

    public PreparedStatement getPreparedStatement() {
        return prepStmt;
    }

    private List<Integer> getIndexes(String name) {
        return fields.get(name);
    }

    public ResultSet executeQuery() throws SQLException {
        return prepStmt.executeQuery();
    }

    public void executeUpdate() throws SQLException {
        prepStmt.executeUpdate();
    }

    public void close() throws SQLException {
        prepStmt.close();
    }

    public void setInt(String name, int value) throws SQLException {        
        for (Integer i: getIndexes(name)) {
            prepStmt.setInt(i, value);
        }
    }

    public void setString(String name, String value) throws SQLException {
        for (Integer i: getIndexes(name)) {
            prepStmt.setString(i, value);
        }
    }

    public void setDate(String name, Timestamp value) throws SQLException {
        for (Integer i: getIndexes(name)) {
            prepStmt.setTimestamp(i, value);
        }
    }
}