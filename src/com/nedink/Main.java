package com.nedink;

import com.nedink.exception.UnknownCommandException;
import com.nedink.ui.Command;
import com.nedink.ui.CommandAction;
import com.nedink.ui.ConsoleColors;
import com.nedink.message.HelpMessage;
import com.nedink.util.Rand;
import com.nedink.world.Player;
import com.nedink.world.Room;

import java.util.*;

public class Main {

    public static boolean running;
    private static Player player;
    private static Room room;
    private static Scanner scanner;
    private static StringBuilder message;
    private static Set<MessageType> messageTypeSet;
    private static int state = 0;

    public static void main(String[] args) {

        if (args.length > 0)
            try {
                Rand.rand.setSeed(Long.valueOf(args[0]));
            }
            catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED + "ERROR Invalid Seed" + ConsoleColors.RESET);
                System.exit(1);
            }

        running = true;
        room = new Room(null, true);
        player = new Player();
        scanner = new Scanner(System.in);
        messageTypeSet = new HashSet<>();
        message = new StringBuilder();

        while (running) {
            prepare();
            print();
            processInput();
        }
    }

    private static void prepare() {

        message.append("\n");

        // Game state description

        if (messageTypeSet.contains(MessageType.HELP_PAGE)) {
            message.append(new HelpMessage().getMessage())
                    .append("\n");
        }

        if (messageTypeSet.contains(MessageType.UNKOWN_COMMAND)) {
            message.append(ConsoleColors.RED)
                    .append("Command not recognized. Type ")
                    .append(ConsoleColors.RESET)
                    .append("help")
                    .append(ConsoleColors.RED)
                    .append(" to list commands.")
                    .append(ConsoleColors.RESET)
                    .append("\n");
        }

        StringBuilder roomPath = new StringBuilder();
        for (Room room : room.getPath()) {
            roomPath.append(room.leftChild == null && room.rightChild == null ? ConsoleColors.RED :
                            room.leftChild != null && room.rightChild != null ? ConsoleColors.GREEN : ConsoleColors.YELLOW)
                    .append(room.isLeft ? "l" : "r")
                    .append(ConsoleColors.RESET);
        }

        message.append("You are in room ").append(roomPath)
                .append("\n");

        // enemies
        message.append("enemies: ");
        int enemies = 0;

        for (int i = 0; i < room.enemies.size(); ++i) {
            enemies++;
        }
        message.append(enemies)
                .append("\n");

        messageTypeSet = new HashSet<>();
    }

    private static void print() {
        System.out.print(message);
        message = new StringBuilder();
    }

    private static void processInput() {

        // get line of input
        String line;
        while ((line = scanner.nextLine().trim()).equals("")) {
        }

        // get command
        Command command;
        try {
            command = new Command(line);
            System.out.println(ConsoleColors.GREEN + "action: " + command.getAction());
            System.out.println("args: " + Arrays.toString(command.getArgs().toArray()) + ConsoleColors.RESET);
        }
        catch (UnknownCommandException e) {
            messageTypeSet.add(MessageType.UNKOWN_COMMAND);
            return;
        }

        CommandAction action = command.getAction();
        List<String> args = command.getArgs();
        switch (action) {
            case QUIT: {
                System.exit(0);
                break;
            }
            case HELP: {
                messageTypeSet.add(MessageType.HELP_PAGE);
                break;
            }
            case GO: {
                if (args.isEmpty())
                    args = getArgsFor(CommandAction.GO);
                // read args
                break;
            }
            case TAKE: {
                // read args
                break;
            }
        }

//        CommandAction commandType = Commands.commandActionMap.get(input);
//        if (commandType == null) {
//            messageTypeSet.add(MessageType.UNKOWN_COMMAND);
//        }
//        else {
//            switch (commandType) {
//                case GO_LEFT: {
//                    if (room.leftChild == null)
//                        room.spawnLeft();
//                    room = room.leftChild;
//                    break;
//                }
//                case GO_RIGHT: {
//                    if (room.rightChild == null)
//                        room.spawnRight();
//                    room = room.rightChild;
//                    break;
//                }
//                case GO_BACK: {
//                    if (room.parent == null) {
//                        messageTypeSet.add(MessageType.NO_PARENT_ROOM);
//                        break;
//                    }
//                    room = room.parent;
//                    break;
//                }
//                case HELP: {
//                    messageTypeSet.add(MessageType.HELP_PAGE);
//                    break;
//                }
//                case QUIT: {
//                    System.exit(0);
//                    break;
//                }
//                default: {
//                    break;
//                }
//            }
//        }

    }

    private static List<String> getArgsFor(CommandAction action) {

        List<String> args = new ArrayList<>();

        switch (action) {
            case GO: {
                String arg;
                while ((arg = scanner.next()) == null || !(arg.equals("left") || arg.equals("right"))) {
                    if (arg != null) {
                        System.out.println("");
                    }
                }
                scanner.next();
            }
            case TAKE:
        }

        return args;
    }
}
