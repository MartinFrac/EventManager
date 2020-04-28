package com.eet.controllers;

import com.eet.dao.UserDao;
import com.eet.mappers.UserMapper;
import com.eet.memory.ActiveUser;
import com.eet.models.User;

public class UserController {
    UserDao userDao;

    public UserController() {
        userDao = new UserDao();
    }

    public boolean authenticate(String id, char[] arrayPassword) {
        String inputPassword = new String(arrayPassword);
        User user = userDao.findById(id);
        if (user == null) {
            return false;
        }
        String correctPassword = user.getPassword();
        if (!inputPassword.equals(correctPassword)) {
            return false;
        }
        ActiveUser.setUser(user);
        return true;
    }

    public void createUser(String id, char[] inputArray, String name, String surname) {
        User user = UserMapper.toSql(id, name, surname, inputArray);
        userDao.insert(user);
    }
}
