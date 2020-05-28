package com.eet.mappers;

import com.eet.models.Role;
import com.eet.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserMapper {

    public static List<Role> roles = Arrays.asList(Role.ADMIN, Role.EVENT_ORGANISER, Role.USER);

    public static User fromSql(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setPassword(resultSet.getString("password"));
        for (Role role: roles) {
            if (role.getLevel() == resultSet.getInt("role_id")) {
                user.setRole(role);
            }
        }
        return user;
    }

    public static User fromUI(Map<String, String> map) {
        User user = new User();
        user.setId(map.get("id"));
        user.setName(map.get("name"));
        user.setSurname(map.get("surname"));
        for (Role role: roles) {
            if (role.getLevel() == Integer.parseInt(map.get("role"))) {
                user.setRole(role);
            }
        }
        return user;
    }
}
