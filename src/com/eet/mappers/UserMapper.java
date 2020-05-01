package com.eet.mappers;

import com.eet.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public static User toJava(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
