/*

Room description goes here...
blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah

MENU DESCRIPTION
    - THING
    - THING
    - THING
    - THING

Input Error.

> input right here

 */

package com.nedink.ui;

import java.util.ArrayList;
import java.util.List;

public class OutputBuffer {

    private static final int HEIGHT = 32;

    private List<String> mainMessage;
    private Menu menu;
    private int advanceSpace;

    public OutputBuffer() {
        mainMessage = new ArrayList<>();
        menu = new Menu();
        advanceSpace = HEIGHT;
    }

    public void print() {

        StringBuilder output = new StringBuilder();

        // add space to advance screen position
        for (int i = 0; i < advanceSpace; ++i)
            output.append('\n');

        advanceSpace += mainMessage.size();
        advanceSpace += menu.outputHeight();

        for (String line : mainMessage) {

        }

        System.out.print(output);
    }
}
