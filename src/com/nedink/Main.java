package com.nedink;

import com.nedink.ui.Commands;
import com.nedink.ui.ConsoleColors;
import com.nedink.world.Player;
import com.nedink.world.Room;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static boolean running;
    private static Player player;
    private static Room room;
    private static Scanner scanner;
    private static String message;
    private static List<MessageType> messageTypeList;
    private static int state;

    public static void main(String[] args) {

        running = true;

        room = new Room(null, true);
        player = new Player();

        scanner = new Scanner(System.in);

        message = "";

        while (running) {
            prepare();
            print();
            processInput();
        }
    }

    private static void prepare() {

        String roomPath = "";
        for (Room room : room.getPath()) {
            roomPath += room.isLeft ? "l" : "r";
        }
        message += "You are in room " + roomPath + "\n";
    }

    private static void print() {
        System.out.print(message);
        message = "";
    }

    private static void processInput() {

        String input = scanner.nextLine().trim();

        Commands.CommandType commandType = Commands.commandMap.get(input);
        if (commandType == null) {
            message += ConsoleColors.RED + "Command not recognized.\n" + ConsoleColors.RESET;
        }
        else {
            switch (commandType) {
                case GO_LEFT: {
                    if (room.leftChild == null)
                        room.spawnLeft();
                    room = room.leftChild;
                    break;
                }
                case GO_RIGHT: {
                    if (room.leftChild == null)
                        room.spawnRight();
                    room = room.rightChild;
                    break;
                }
                case GO_BACK: {
                    if (room.parent == null) {
                        messageTypeList.add(MessageType.NO_PARENT_ROOM);
                        break;
                    }
                    room = room.parent;
                    break;
                }
                case QUIT: {
                    System.exit(1);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}
