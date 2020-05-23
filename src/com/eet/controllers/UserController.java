package com.eet.controllers;

import com.eet.dao.UserDao;
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

    public boolean checkIfUserExists(String id) {
        if (userDao.findById(id)==null) {
            return false;
        }
        return true;
    }

    public void create(User user) {
        user.setRole(3);
        userDao.create(user);
    }
}
