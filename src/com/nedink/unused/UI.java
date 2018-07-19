package com.nedink.unused;

public class UI {

    public static final int WIDTH = 80;
    public static final int HEIGHT = 20;
    public static final int PANEL_PADDING_HORIZONTAL = 2;
    public static final int PANEL_PADDING_VERTICAL = 0;
    public static final int MARGIN = 2;
    public static final int MARGIN_BOTH = MARGIN * 2;
    public static final int WIDTH_IN_MARGIN = WIDTH - MARGIN_BOTH;
    public static final int WIDTH_IN_PANEL = WIDTH_IN_MARGIN - 2;
    public static final int WIDTH_IN_PANEL_IN_MARGIN = WIDTH_IN_PANEL - MARGIN * 2;

    private Panel[] panels;
    private InputPanel input;

    private char[][] buffer;

    public UI() {
        panels = new Panel[]{};
        input = new InputPanel();
    }

    public UI(Panel[] panels) {
        this.panels = panels;
    }

    public void draw() {
        for (Panel panel : panels) {
            panel.superimpose(buffer);
        }
        input.superimpose(buffer);

    }
}
