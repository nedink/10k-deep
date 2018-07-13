package com.nedink.ui;

public class UI {

    public static final int WIDTH = 80;
    public static final int MARGIN = 3;
    public static final int MARGIN_BOTH = MARGIN * 2;
    public static final int WIDTH_IN_MARGIN = WIDTH - MARGIN_BOTH;
    public static final int WIDTH_IN_PANEL = WIDTH_IN_MARGIN - 2;
    public static final int WIDTH_IN_PANEL_IN_MARGIN = WIDTH_IN_PANEL - MARGIN * 2;

    String title;

    Graphic graphic;
    Menu menu;

    public String print() {
        return "";
    }
}
