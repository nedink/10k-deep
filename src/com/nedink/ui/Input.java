package com.nedink.ui;

public class InputPanel extends Panel {

    String prompt;

    public InputPanel() {
        prompt = "> ";
    }

    public InputPanel(String prompt) {
        prompt = prompt;
    }
}
