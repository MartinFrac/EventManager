package com.eet.ui;

import java.util.HashMap;
import java.util.Map;

public class Frames {

    private static Map<Frame, DesignatedFrame> jFrameMap = new HashMap<>();

    public static DesignatedFrame getJFrame(Frame frame) {
        if (jFrameMap.get(frame) == null) {
            jFrameMap.put(frame, new DesignatedFrame(frame.getWidth(), frame.getHeight()));
        }
        return jFrameMap.get(frame);
    }
}
