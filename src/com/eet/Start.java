package com.eet;

import com.eet.database.SqliteConnection;
import com.eet.ui.SmallFrame;
import com.eet.ui.views.LoginUI;

public class Start {

    public static void main(String[] args) {
        SqliteConnection.setPath(args[0]);
        LoginUI loginUI = new LoginUI();
        new SmallFrame(loginUI, loginUI.getLoginButton());
    }
}
