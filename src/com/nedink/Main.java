package com.nedink;

import com.nedink.ui.Menu;
import com.nedink.ui.MenuItem;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // Start
        Scanner in = new Scanner(System.in);

        System.out.println(new Menu(new MenuItem[]{new MenuItem()}).print());


    }
}
