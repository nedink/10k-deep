package com.nedink;

import java.util.Scanner;

import static com.nedink.ConsoleColors.RED;
import static com.nedink.ConsoleColors.RESET;
import static com.nedink.Rand.range;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        new TimerRunner().run();

        for (int i = 0; i < 80; ++i) {
            System.out.print(RED + "0" + RESET);
        }

        Scanner in = new Scanner(System.in);

        while (in.next() == null) {
            for (int i = 0; i < range(15, 20); ++i) {
                System.out.print(">");
            }
            System.out.print(" ");
        }
//        game.run();

    }


}
