package com.nedink;

import static com.nedink.Rand.rand;
import static com.nedink.Rand.range;

public class Lang {

    private static String[] numbers = {
            "0","1","2","3","4","5","6","7","8","9"
    };

    private static String[] alphabet = {
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
    };

    private static String[] vowels = {
            "a","e","i","o","u","y"
    };

    private static String[] consonants = {
            "b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z"
    };

    private static String[] befores = {
            "b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z",
            "bl", "br",
            "cl", "cr",
            "dr", "dw",
            "fl", "fr",
            "gl", "gr",
            "kl", "kr",
            "pl", "pr",
            "sh", "sk", "sl", "sn", "sp", "st", "sw",
            "th", "tr", "tw",
            "vl", "vr",
            "wr",
            "shr", "thr",
    };

    private static String[] afters = {

    };

    public Lang() {

    }

    static String genWord() {

        String word = "";

        Boolean start = rand.nextBoolean();

        // vowel or consonant start
        word += start ? consonants[range(consonants.length)] : vowels[range(vowels.length)];

        word += start ? vowels[range(vowels.length)] : consonants[range(consonants.length)];

        word += start ? consonants[range(consonants.length)] : vowels[range(vowels.length)];

        word += start ? vowels[range(vowels.length)] : consonants[range(consonants.length)];

        word += "-";

        word += numbers[range(numbers.length)];
        word += numbers[range(numbers.length)];

        word = word.substring(0, 1).toUpperCase() + word.substring(1, word.length());

        return word;

        // followed by opposite

        // followed by opposite
    }
}
