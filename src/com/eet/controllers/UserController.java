package com.eet.controllers;

import com.eet.dao.UserDao;
import com.eet.mappers.UserMapper;
import com.eet.memory.ActiveUser;
import com.eet.models.Role;
import com.eet.models.User;

import java.util.List;

public class UserController {
    UserDao userDao;

    public UserController() {
        userDao = new UserDao();
    }

    public User authenticate(String id, char[] arrayPassword) {
        String inputPassword = new String(arrayPassword);
        User user = userDao.findById(id);
        if (user == null) {
            return null;
        }
        String correctPassword = user.getPassword();
        if (!inputPassword.equals(correctPassword)) {
            return null;
        }
        ActiveUser.setUser(user);
        return user;
    }

    public boolean checkIfUserExists(String id) {
        if (userDao.findById(id)==null) {
            return false;
        }
        return true;
    }

    public void create(User user) {
        user.setRole(Role.USER);
        userDao.create(user);
    }

    public Object[][] getUsersWithFilters(User user) {
        List<User> users = userDao.findWithFilters(user);
        return castToArray(users);
    }

    public Object[][] getPrivilegedById(String id) {
        List<User> users = userDao.findPrivilegedById(id);
        return castToArray(users);
    }

    public Object[][] getNonPrivilegedById(String id) {
        List<User> users = userDao.findNonPrivilegedById(id);
        return castToArray(users);
    }

    public void grantPrivileges(String userId) {
        userDao.grantPrivileges(userId);
    }

    public void revokePrivileges(String userId) {
        userDao.revokePrivileges(userId);
    }

    public Object[][] getNonPrivileged() {
        List<User> users = userDao.findNonPrivileged();
        return castToArray(users);
    }

    public Object[][] getPrivileged() {
        List<User> users = userDao.findPrivileged();
        return castToArray(users);
    }

    public Object[][] getPrivilegedByBookingEventId(int eventId) {
        List<User> users = userDao.findPrivilegedByBookingEventId(eventId);
        return castToArray(users);
    }

    public Object[][] getNonPrivilegedByBookingEventId(int eventId) {
        List<User> users = userDao.findNonPrivilegedByBookingEventId(eventId);
        return castToArray(users);
    }

    public Object[][] getPrivilegedByIdBookingEventId(String id, int eventId) {
        List<User> users = userDao.findPrivilegedByIdAndBookingEventId(id, eventId);
        return castToArray(users);
    }

    public Object[][] getNonPrivilegedByIdBookingEventId(String id, int eventId) {
        List<User> users = userDao.findNonPrivilegedByIdAndBookingEventId(id, eventId);
        return castToArray(users);
    }

    public Object[][] getByBookingEventIdWithFilters(User user, int eventId) {
        List<User> users = userDao.findByBookingEventIdWithFilters(user, eventId);
        return castToArray(users);
    }

    private Object[][] castToArray(List<User> users) {
        Object[][] objects = new Object[users.size()][5];
        if (users.isEmpty()) {
            return null;
        }
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            objects[i] = format(user);
        }
        return objects;
    }

    private Object[] format(User user) {
        Object[] object = new Object[5];
            object[0] = user.getId();
            object[1] = user.getName();
            object[2] = user.getSurname();
            for (Role role: UserMapper.roles) {
                if (role.getLevel()==user.getRole().getLevel()) {
                    object[3] = user.getRole().toString();
                }
            }
            object[4] = user.getId();
        return object;
    }
}
