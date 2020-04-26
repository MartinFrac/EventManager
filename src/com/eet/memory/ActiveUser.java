package com.eet.memory;

import com.eet.models.User;

public class ActiveUser {

    public static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ActiveUser.user = user;
    }
}
