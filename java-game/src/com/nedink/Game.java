package com.nedink;

import java.util.Scanner;

import static com.nedink.Lang.genWord;

public class Game {

    private Scanner in = new Scanner(System.in);
    private String next = "";

    void run() {

//        while ((next = in.next()) != null)
//        {
//            System.out.println(next);
//        }

        // tutorial
        while (!next.equals("go"))
            next = in.next();


    }
}
