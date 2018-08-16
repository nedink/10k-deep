package com.nedink.ui;

import com.nedink.exception.NoInputException;
import com.nedink.exception.UnknownCommandException;

import java.util.*;
import java.util.function.Function;

public class Command {

    private static Map<String, CommandAction> commandActionMap;
    private static Map<String, String> aliases;
    private static Set<CommandAction> supportEmpty;

    public static Set<CommandAction> getSupportEmpty() {
        return supportEmpty;
    }
    //    private static Map<CommandAction, Function<Object, Object>> supportEmpty;


    private CommandAction action;
    private List<String> args;

    //

    public Command(String line) throws RuntimeException {

        // parse line
        line = line.toLowerCase();
        Scanner firstScanner = new Scanner(line);
        String firstWord;
        String tail;

        firstWord = firstScanner.hasNext() ? firstScanner.next() : "";
        tail = firstScanner.hasNextLine() ? firstScanner.nextLine().trim() : "";

        // expand alias
        if (aliases.containsKey(firstWord)) {
            firstScanner = new Scanner(aliases.get(firstWord));
            firstWord = firstScanner.next();
            tail = firstScanner.hasNextLine() ? firstScanner.nextLine() + " " + tail : tail;
        }

        CommandAction action = commandActionMap.get(firstWord);

        // command unknown
        if (action == null) {
            throw new UnknownCommandException();
        }

        // set action
        this.action = action;

        // parse tail
        Scanner tailScanner = new Scanner(tail);
        String tailString;
        args = new ArrayList<>();
        while (tailScanner.hasNext() && (tailString = tailScanner.next()) != null) {
            args.add(tailString);
        }
    }

    private Command(CommandAction action, List<String> args) {
        this.action = action;
        this.args = args;
    }

    //

    public CommandAction getAction() {
        return action;
    }

    public void setAction(CommandAction action) {
        this.action = action;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    //

    // commands
    static {
        commandActionMap = new HashMap<>();

        // enter
        commandActionMap.put("", CommandAction.ENTER);

        // help
        commandActionMap.put("help", CommandAction.HELP);
        commandActionMap.put("h", CommandAction.HELP);

        // go
        commandActionMap.put("go", CommandAction.GO);

        // take
        commandActionMap.put("take", CommandAction.TAKE);
        commandActionMap.put("t", CommandAction.TAKE);

        // quit
        commandActionMap.put("quit", CommandAction.QUIT);
        commandActionMap.put("q", CommandAction.QUIT);
    }

    // aliases
    static {
        aliases = new HashMap<>();

        aliases.put("l", "go left");
        aliases.put("r", "go right");
        aliases.put("b", "go back");
    }

    // supports no args
    static {
        supportEmpty = new HashSet<>();

        supportEmpty.add(CommandAction.TAKE);
    }
}
