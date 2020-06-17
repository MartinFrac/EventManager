package com.eet;

import com.eet.database.SqliteConnection;
import com.eet.ui.DesignatedFrame;
import com.eet.ui.Frame;
import com.eet.ui.Frames;
import com.eet.ui.views.LoginUI;

public class Start {

    public static void main(String[] args) {
        SqliteConnection.setPath(args[0]);
        LoginUI loginUI = new LoginUI();
        DesignatedFrame designatedFrame = Frames.getJFrame(Frame.Small);
        designatedFrame.changePanel(loginUI, loginUI.getLoginButton());
    }
}
