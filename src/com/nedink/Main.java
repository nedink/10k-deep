package com.nedink;

import com.nedink.ui.Menu;
import com.nedink.ui.MenuItem;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        // Start
        MenuItem[] menu = {
                new MenuItem("Go")
        };
        System.out.println(new Menu(menu).print());

        

    }
}
