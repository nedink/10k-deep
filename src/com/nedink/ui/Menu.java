package com.nedink.ui;

import com.nedink.world.Selectable;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<String> description;
    private List<Selectable> menuItems;

    Menu() {
        description = new ArrayList<>();
        menuItems = new ArrayList<>();
    }

    public int outputHeight() {
        int height = 0;
        height += description.size();
        height += menuItems.size();
        return height;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (String line : description) {
            output.append(line).append('\n');
        }

        int index = 1;
        for (Selectable menuItem : menuItems) {
            output.append(index++)
                    .append(menuItem.selectableName())
                    .append('\n');
        }

        return output.toString();
    }
}
