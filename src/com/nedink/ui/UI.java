package com.nedink.ui;

public class UI {

    public static final int UI_WIDTH = 80;
    public static final int UI_HEIGHT = 32;

    // TODO
    public static String centeredTitle(String string) {
        char[] line = new char[UI_WIDTH];
        int index = 0;
        // _ _ _ _ _ : _ _ _ _ _ : _ _ _ _ _
        while (index < line.length / 2 - (string.length() / 2)) {
            line[index++] = ' ';
        }
        int paddingLeft = index;
        for (int i = index; i < string.length(); ++i) {
            string.charAt(i - paddingLeft);
            index++;
        }
//        while (index)
        return new String(line);
    }
}
