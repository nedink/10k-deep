package com.nedink;

import com.nedink.exception.UnknownCommandException;
import com.nedink.lang.Lang;
import com.nedink.message.HelpMessage;
import com.nedink.message.UnknownCommandMessage;
import com.nedink.ui.Command;
import com.nedink.ui.CommandAction;
import com.nedink.ui.ConsoleColor;
import com.nedink.util.Rand;
import com.nedink.world.Room;
import com.nedink.world.State;
import com.nedink.world.character.Player;
import com.nedink.world.item.DamagePart;
import com.nedink.world.item.Item;
import com.nedink.world.item.Rarity;

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
            } catch (NumberFormatException e) {
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

//            doTests();

            Item item = new Item();
            DamagePart damagePart;

            String line = scanner.nextLine();
            if (line.equals("q")) System.exit(0); // for testing
            try { damagePart = DamagePart.generate(Math.max(Integer.valueOf(line), 1)); } catch (NumberFormatException e) { damagePart = DamagePart.generate(1); }

            item.addPart(damagePart);

            System.out.print(damagePart.getRarity() == Rarity.COMMON ? ConsoleColor.WHITE_BRIGHT :
                    damagePart.getRarity() == Rarity.UNCOMMON ? ConsoleColor.GREEN_BRIGHT :
                            damagePart.getRarity() == Rarity.RARE ? ConsoleColor.CYAN_BRIGHT :
                                    damagePart.getRarity() == Rarity.EPIC ? ConsoleColor.PURPLE_BRIGHT :
                                            damagePart.getRarity() == Rarity.LEGENDARY ? ConsoleColor.YELLOW_BRIGHT : "");
            System.out.println("Generated weapon:");

//            System.out.println("  name: " + item.getPart(0).getName());
//            System.out.println("  volume: " + item.getVolume() + " units");
//            System.out.println("  weight: " + item.getWeight() + " kg");

            System.out.print("  damage: [");
            if (((DamagePart) item.getPart(0)).getDamage() < 100) {
                for (int i = 0; i < ((DamagePart) item.getPart(0)).getDamage(); ++i)
                    System.out.print('=');
            }
            else
                System.out.print(" " + ((DamagePart) item.getPart(0)).getDamage());
            System.out.print('\n');

            System.out.print(ConsoleColor.RESET);
//            System.out.println("  damage: " + item.get() + " kg");

//            prepareOutput();
//            System.out.print(message);
//            message = new StringBuilder();
//            processInput();
        }
    }

    private static void prepareOutput() {

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
                            .append(room.getParent() != null ? "-" : "")
                            .append(room.isLeft() ? "L" : "R")
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

    private static void processInput() {

        // get input
        String line;
        Command command;

        while ((line = scanner.nextLine().trim()).equals("")) {
        }

        // input -> command
        try {
            command = new Command(line);
        } catch (UnknownCommandException e) {
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
                        if (room.getLeftChild() == null)
                            room.spawnLeft();
                        room = room.getLeftChild();
                        break;
                    case "right":
                        if (room.getRightChild() == null)
                            room.spawnRight();
                        room = room.getRightChild();
                        break;
                    case "back":
                        if (room.getParent() == null) {
                            // handle no parent
                            message.append("This room has no parent.\n");
                            break;
                        }
                        room = room.getParent();
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


    private static void doTests() {
        DamagePart[] damageParts = new DamagePart[100];
        DamagePart.DamageType[] types = new DamagePart.DamageType[100];
        Rarity[] rarities = new Rarity[100];

        for (int i = 0; i < 100; ++i) {
            damageParts[i] = DamagePart.generate(30);
        }

        Arrays.sort(damageParts, Comparator.comparing(DamagePart::getRarity));

        for (int i = 0; i < 100; ++i) {
            types[i] = damageParts[i].getDamageType();
            rarities[i] = damageParts[i].getRarity();
            String label;
            String secondary;

            switch (damageParts[i].getRarity()) {
                case COMMON:
//                    label = ConsoleColor.WHITE_BOLD_BRIGHT;
                    label = ConsoleColor.WHITE_BRIGHT;
                    secondary = ConsoleColor.WHITE;
                    break;
                case UNCOMMON:
//                    label = ConsoleColor.GREEN_BOLD_BRIGHT;
                    label = ConsoleColor.GREEN_BRIGHT;
                    secondary = ConsoleColor.GREEN;
                    break;
                case RARE:
//                    label = ConsoleColor.CYAN_BOLD_BRIGHT;
                    label = ConsoleColor.CYAN_BRIGHT;
                    secondary = ConsoleColor.CYAN;
                    break;
                case EPIC:
//                    label = ConsoleColor.PURPLE_BOLD_BRIGHT;
                    label = ConsoleColor.PURPLE_BRIGHT;
                    secondary = ConsoleColor.PURPLE;
                    break;
                case LEGENDARY:
//                    label = ConsoleColor.YELLOW_BOLD_BRIGHT;
                    label = ConsoleColor.YELLOW_BRIGHT;
                    secondary = ConsoleColor.YELLOW;
                    break;
                default:
//                    label = ConsoleColor.WHITE_BOLD_BRIGHT;
                    label = ConsoleColor.WHITE_BRIGHT;
                    secondary = ConsoleColor.WHITE;
                    break;
            }

            System.out.println(label +
//                               (damageParts[i].getRarity() == Rarity.COMMON ?       "      " + Chars.STAR_5 + " "                                                                                 + "      " :
//                                damageParts[i].getRarity() == Rarity.UNCOMMON ?     "     " + Chars.STAR_5 + " " + Chars.STAR_5 + " "                                                             + "     " :
//                                damageParts[i].getRarity() == Rarity.RARE ?         "    " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " "                                         + "    " :
//                                damageParts[i].getRarity() == Rarity.EPIC ?         "   " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " "                     + "   " :
//                                damageParts[i].getRarity() == Rarity.LEGENDARY ?    "  " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + Chars.STAR_5 + " " + "  " : "") +
//                               (damageParts[i].getRarity() == Rarity.COMMON ?       "    - C -     " :
//                                damageParts[i].getRarity() == Rarity.UNCOMMON ?     "   -= U =-    " :
//                                damageParts[i].getRarity() == Rarity.RARE ?         "  -=< R >=-   " :
//                                damageParts[i].getRarity() == Rarity.EPIC ?         " -=<( E )>=-  " :
//                                damageParts[i].getRarity() == Rarity.LEGENDARY ?    "-=<({ L })>=- " : "") +
                    (damageParts[i].getRarity() == Rarity.COMMON ? "    - C -    " :
                            damageParts[i].getRarity() == Rarity.UNCOMMON ? "    = U =    " :
                                    damageParts[i].getRarity() == Rarity.RARE ? "   -= R =-   " :
                                            damageParts[i].getRarity() == Rarity.EPIC ? "  -=> E <=-  " :
                                                    damageParts[i].getRarity() == Rarity.LEGENDARY ? " ->){ L }(<- " : "") +
                    Lang.generateName(damageParts[i]).toUpperCase() + " ");

            System.out.println(
                    "    - C -    " +
                            secondary +
                            damageParts[i].getDamageType() + " " +
                            damageParts[i].getDamage() + " ");
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
