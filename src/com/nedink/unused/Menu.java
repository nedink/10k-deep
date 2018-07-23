package com.nedink.unused;

import com.nedink.message.AbstractMessage;

import static com.nedink.ui.Chars.*;
import static com.nedink.unused.UI.*;

/**
 * Full-width panel just above input
 */
public class Menu extends AbstractMessage {

    public MenuItem[] items;

    public Menu(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        String out = "";

        //    ┌────────────────────────────────────────────────────────────────────────┐

        out += new LineBuilder()
                .add(margin())
                .add(BOX_DRAWINGS_LIGHT_DOWN_AND_RIGHT)
                .add(BOX_DRAWINGS_LIGHT_HORIZONTAL, WIDTH_IN_MARGIN - 2)
                .add(BOX_DRAWINGS_LIGHT_DOWN_AND_LEFT)
                .add(margin())
                .add('\n')
                .build();

        //    │   1. <Selectable>                                                        │

        for (int i = 0; i < items.length; ++i) {
            out += new LineBuilder()
                    .add(margin())
                    .add(BOX_DRAWINGS_LIGHT_VERTICAL)
                    .add(margin()).add((i + 1) + ". ").add(items[i].description.length() < WIDTH_IN_PANEL_IN_MARGIN
                    - ((i + 1) + ". " + "...").length() ? items[i].description
                    : items[i].description.substring(0, WIDTH_IN_PANEL_IN_MARGIN - ((i + 1) + ". " + "...").length())
                    + "...")
                    .add(items[i].description.length() < WIDTH_IN_PANEL_IN_MARGIN - ((i + 1) + ". ").length()
                            ? new LineBuilder().add(EMPTY_BLOCK, WIDTH_IN_PANEL_IN_MARGIN - ((i + 1) + ". ").length()
                                    - items[i].description.length()).build() : "")
                    .add(margin())
                    .add(BOX_DRAWINGS_LIGHT_VERTICAL)
                    .add(margin())
                    .add('\n')
                    .build();
        }

        //    └────────────────────────────────────────────────────────────────────────┘

        out += new LineBuilder()
                .add(margin())
                .add(BOX_DRAWINGS_LIGHT_UP_AND_RIGHT)
                .add(BOX_DRAWINGS_LIGHT_HORIZONTAL, WIDTH_IN_MARGIN - 2)
                .add(BOX_DRAWINGS_LIGHT_UP_AND_LEFT)
                .add(margin())
                .add('\n')
                .build();

        return out;
    }

    private String margin() {
        String out = "";
        for (int i = 0; i < MARGIN; ++i) {
            out += EMPTY_BLOCK;
        }
        return out;
    }

}
