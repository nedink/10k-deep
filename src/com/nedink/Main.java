package com.nedink;

import com.nedink.exception.UnknownCommandException;
import com.nedink.lang.Lang;
import com.nedink.message.UnknownCommandMessage;
import com.nedink.ui.Chars;
import com.nedink.ui.Command;
import com.nedink.ui.CommandAction;
import com.nedink.ui.ConsoleColor;
import com.nedink.message.HelpMessage;
import com.nedink.util.Rand;
import com.nedink.world.*;
import com.nedink.world.State;

import java.util.*;

import static com.nedink.world.State.ROOM;

public class Main {

    private static boolean running;
    private static Player player;
    private static Room room;
    private static Scanner scanner;
    private static StringBuilder message;
    private static State state;
//    private static List<String> commandBuffer;

    public static void main(String[] args) {

        Rand.rand.setSeed(System.nanoTime());

        if (args.length > 0) {
            try {
                Rand.rand.setSeed(Long.valueOf(args[0]));
            }
            catch (NumberFormatException e) {
                System.out.println(ConsoleColor.RED + "ERROR Invalid Seed" + ConsoleColor.RESET);
                System.exit(1);
            }
        }

        running = true;
        room = new Room(null, true);
        player = new Player();
        message = new StringBuilder();
        state = ROOM;
//        commandBuffer = new ArrayList<>();

        scanner = new Scanner(System.in);

        while (running) {

//            DamagePart damagePart = DamagePart.generate(player.getLevel());

//            Item weapon = new Item();
//            weapon.addPart(damagePart);




//            System.out.println(weapon);
            prepareOutput();
            printOutput();
            processInput();
            progressState();
        }
    }

    private static void prepareOutput() {

//        message.append("\n");

        // Game state description

        switch (state) {

            case CUTSCENE: {

            }

            case ROOM: {
                // printOutput room description stuff
                StringBuilder roomPath = new StringBuilder();
                for (Room room : room.getPath()) {
                    roomPath.append(room.getLeftChild() == null && room.getRightChild() == null ? ConsoleColor.RED :
                                    room.getLeftChild() != null && room.getRightChild() != null ? ConsoleColor.GREEN : ConsoleColor.YELLOW)
                            .append(room.isLeft() ? "l" : "r")
                            .append(ConsoleColor.RESET);
                }
                message.append("You are in room ").append(roomPath)
                        .append("\n");

                // enemies
                message.append("enemies: ");
                int enemies = 0;

                for (int i = 0; i < room.getEnemies().size(); ++i) {
                    enemies++;
                }
                message.append(enemies)
                        .append("\n");
                break;
            }

            case BATTLE: {

            }

            case FLEEING: {

            }
        }

    }

    private static void printOutput() {
        System.out.print(message);
        message = new StringBuilder();
    }

    private static void processInput() {

        // get input
        String line;
        Command command;

        while ((line = scanner.nextLine().trim()).equals("")) {
        }

        // input -> command
        try {
            command = new Command(line);
        }
        catch (UnknownCommandException e) {
            message.append(new UnknownCommandMessage());
            return;
        }

        // command -> decision
        CommandAction action = command.getAction();
        List<String> args = command.getArgs();
        switch (action) {
            case QUIT: {
                System.exit(0);
                break;
            }
            case HELP: {
                message.append(new HelpMessage());
                break;
            }
            case GO: {
                if (args.isEmpty())
                    args.add("back");
                // read first arg
                List<String> accept = new ArrayList<>();
                accept.add("left");
                accept.add("right");
                accept.add("back");
                switch (args.get(0)) {
                    case "left":
                        room = room.spawnLeft();
                        break;
                    case "right":
                        break;
                    case "back":
                        break;
                }
                for (String s : args) {
                    if (!accept.contains(s)) {
                        // cancel
                        message.append(ConsoleColor.RED)
                                .append("Argument ")
                                .append(ConsoleColor.RESET)
                                .append("'").append(s).append("'")
                                .append(ConsoleColor.RED)
                                .append(" cannot be resolved")
                                .append(ConsoleColor.RESET)
                                .append("\n");
                        break;
                    }

                }
                break;
            }
            case TAKE: {
                if (args.isEmpty())
                    args.add("all");
                // read args
                break;
            }
            case INVENTORY: {
                // show inventory
                message.append("- INVENTORY -")
                        .append("\n");
                break;
            }
        }

        System.out.println(ConsoleColor.GREEN + "action: " + action);
        System.out.println("args: " + Arrays.toString(args.toArray()) + ConsoleColor.RESET);

    }

    private static void progressState() {

    }



    private static void doTests() {
        DamagePart[] damageParts = new DamagePart[100];
        DamagePart.DamageType[] types = new DamagePart.DamageType[100];
        Rarity[] rarities = new Rarity[100];
        for (int i = 0; i < 100; ++i) {
            damageParts[i] = DamagePart.generate(30);
            types[i] = damageParts[i].getDamageType();
            rarities[i] = damageParts[i].getRarity();
            String label;
            String secondary;
            switch (damageParts[i].getRarity()) {
                case COMMON:
                    label = ConsoleColor.WHITE_BOLD_BRIGHT;
                    secondary = ConsoleColor.WHITE;
                    break;
                case UNCOMMON:
                    label = ConsoleColor.GREEN_BOLD_BRIGHT;
                    secondary = ConsoleColor.GREEN;
                    break;
                case RARE:
                    label = ConsoleColor.CYAN_BOLD_BRIGHT;
                    secondary = ConsoleColor.CYAN;
                    break;
                case EPIC:
                    label = ConsoleColor.PURPLE_BOLD_BRIGHT;
                    secondary = ConsoleColor.PURPLE;
                    break;
                case LEGENDARY:
                    label = ConsoleColor.YELLOW_BOLD_BRIGHT;
                    secondary = ConsoleColor.YELLOW;
                    break;
                default:
                    label = ConsoleColor.WHITE_BOLD_BRIGHT;
                    secondary = ConsoleColor.WHITE;
                    break;
            }
            System.out.println(label +
                               (damageParts[i].getRarity() == Rarity.COMMON ?       "      " + Chars.STAR_5 + " "                                                                                 + "      " :
                                damageParts[i].getRarity() == Rarity.UNCOMMON ?     "     " + Chars.STAR_5 + " " + Chars.STAR_5 + " "                                                             + "     " :
                                damageParts[i].getRarity() == Rarity.RARE ?         "    " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " "                                         + "    " :
                                damageParts[i].getRarity() == Rarity.EPIC ?         "   " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " "                     + "   " :
                                damageParts[i].getRarity() == Rarity.LEGENDARY ?    "  " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + "  " : "") +
                               Lang.generateName(damageParts[i]).toUpperCase() + " " +
                               secondary +
                               damageParts[i].getDamageType() + " " +
                               damageParts[i].getDamage() + " " );
        }
        int[] typeWeights = new int[5];
        for (DamagePart.DamageType type : types) {
            switch (type) {
                case BLUNT:
                    typeWeights[0]++;
                    break;
                case CLEAVING:
                    typeWeights[1]++;
                    break;
                case PENETRATIVE:
                    typeWeights[2]++;
                    break;
                case LACERATIVE:
                    typeWeights[3]++;
                    break;
                case EXPLOSIVE:
                    typeWeights[4]++;
                    break;
            }
        }
        System.out.println(Arrays.toString(typeWeights));

        int[] rarityWeights = new int[5];
        for (Rarity rarity : rarities) {
            switch (rarity) {
                case COMMON:
                    rarityWeights[0]++;
                    break;
                case UNCOMMON:
                    rarityWeights[1]++;
                    break;
                case RARE:
                    rarityWeights[2]++;
                    break;
                case EPIC:
                    rarityWeights[3]++;
                    break;
                case LEGENDARY:
                    rarityWeights[4]++;
                    break;
            }
        }
        System.out.println(Arrays.toString(rarityWeights));
    }

}
