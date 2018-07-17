package com.nedink.ui;

import com.nedink.util.XY;

import javax.swing.border.Border;
import javax.swing.text.Style;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    public Panel(XY padding, PanelBorder.BorderStyle style) {
        this.border.style = style;
    }

    public void superimpose(char[][] out) {

        // 1. draw border
        out[position.y][position.x] = PanelBorder.borders.get(border.style).get(PanelBorder.Section.TOP_LEFT);
        out[position.y][position.x + dimentions.x] = PanelBorder.borders.get(border.style).get(PanelBorder.Section.TOP_RIGHT);
        out[position.y + dimentions.y][position.x + dimentions.x] = PanelBorder.borders.get(border.style).get(PanelBorder.Section.BOTTOM_RIGHT);
        out[position.y + dimentions.y][position.x] = PanelBorder.borders.get(border.style).get(PanelBorder.Section.BOTTOM_LEFT);
        for (int i = 1; i < dimentions.x - 1; ++i) {
//            out[0][0]
        }

        // 2. draw content
    }

    public static class PanelBorder {
        BorderStyle style = BorderStyle.BASIC;

        public PanelBorder() {}

        public PanelBorder(BorderStyle style) {
            this.style = style;
        }

        public enum BorderStyle {
            BASIC
        }

        public enum Section {
            TOP_LEFT,
            TOP_RIGHT,
            BOTTOM_LEFT,
            BOTTOM_RIGHT,
            TOP,
            RIGHT,
            BOTTOM,
            LEFT
        }

        private static Map<BorderStyle, Map<Section, Character>> borders;
        static {
            Map<Section, Character> borderBasic = new HashMap<>();
            borderBasic.put(Section.TOP_LEFT, Chars.BOX_DRAWINGS_LIGHT_DOWN_AND_RIGHT);
            borderBasic.put(Section.TOP_RIGHT, Chars.BOX_DRAWINGS_LIGHT_DOWN_AND_LEFT);
            borderBasic.put(Section.BOTTOM_LEFT, Chars.BOX_DRAWINGS_LIGHT_UP_AND_RIGHT);
            borderBasic.put(Section.BOTTOM_RIGHT, Chars.BOX_DRAWINGS_LIGHT_UP_AND_LEFT);
            borders.put(BorderStyle.BASIC, borderBasic);
        }

//        private static char[][] borders = {
//                {'┌'}
//        };

    }
}
