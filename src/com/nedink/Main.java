package com.nedink;

import com.nedink.ui.Commands;
import com.nedink.ui.ConsoleColors;
import com.nedink.world.Player;
import com.nedink.world.Room;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static boolean running;
    private static Player player;
    private static Room room;

    public static void main(String[] args) throws IOException {

        running = true;

        Scanner scanner = new Scanner(System.in);

        room = new Room(null, true);
        player = new Player();

        while (running) {
            System.out.print(processInput(scanner.nextLine().trim()));
        }
    }

    private static String processInput(String input) {
        String message = "";

        String roomPath = "";
        System.out.println("len: " + room.getPath().size());
        for (Room room : room.getPath()) {
                roomPath += room.isLeft ? "l" : "r";
//            if (room != null);
        }
        message += "You are in room " + roomPath + "\n";

        try {
            switch (Commands.commandMap.get(input)) {
                case GO_LEFT:
                    room = room.spawnLeft();
                    System.out.println("spawned left");
                    break;
                case GO_RIGHT:
                    room = room.spawnRight();
                    System.out.println("spawned right");
                    break;
                case QUIT:
                    System.exit(1);
                default:
            }
        }
        catch (NullPointerException e) {
            message += ConsoleColors.RED + "Command not recognized.\n" + ConsoleColors.RESET;
        }

        return message;
    }
}
