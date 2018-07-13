package com.nedink.ui;

import com.nedink.util.XY;

import static com.nedink.ui.UI.PANEL_PADDING_HORIZONTAL;
import static com.nedink.ui.UI.PANEL_PADDING_VERTICAL;

public abstract class Panel {

    XY position;
    XY dimentions;
    XY padding;
    PanelBorder border;

    public Panel() {
        this.position = new XY(0, 0);
        this.dimentions = new XY(0, 0);
        this.border = new PanelBorder();
        this.padding = new XY(PANEL_PADDING_HORIZONTAL, PANEL_PADDING_VERTICAL);
    }

    public Panel(XY padding, PanelBorder.Type borderType) {
        this.border.type = borderType;
    }

    public void superimpose(char[][] out) {

        // 1. draw border


        // 2. draw content
    }

    public static class PanelBorder {
        Type type = Type.BASIC;

        public PanelBorder() {}

        public PanelBorder(Type type) {
            this.type = type;
        }

        public enum Type {
            BASIC
        }
    }
}
