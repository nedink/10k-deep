package com.nedink.lang;

import com.nedink.util.Rand;
import com.nedink.world.DamagePart;
import com.nedink.world.Item;
import com.nedink.world.ItemPart;

import static com.nedink.util.Rand.rand;
import static com.nedink.util.Rand.range;

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
            "b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z",
            "ch", "ck", "ct",
            "fk", "ft",
            "gh",
            "ld", "lg", "lk", "ll", "lm", "ln", "lt",
            "mb", "mk", "mn", "mp", "mt",
            "nd", "ng", "nk", "np", "nt", "nx",
            "ph", "pt",
            "rb", "rc", "rf", "rg", "rk", "rl", "rm", "rn", "rp", "rt",
            "sk", "sp", "st",
    };

    public Lang() {

    }

    public static String generateName(DamagePart damagePart) {

        StringBuilder name = new StringBuilder();

        String[] befores = {
                "b", "p", "t",
                "cl", "cr",
                "bl", "br",
                "gr",
                "fl",
                "ch",
                "j",
        };
        String[] vowels = {
                "o", "u", "ou",
        };
        String[] afters = {
                "b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z",
                "ck", "ct",
                "ft",
                "gh",
                "ld", "lg", "lk", "ll", "lm", "ln", "lt",
                "mn", "mp", "mt",
                "ng", "nk", "np", "nt", "nx",
                "ph", "pt",
                "rb", "rc", "rf", "rg", "rk", "rl", "rm", "rn", "rp", "rt",
                "sk", "sp", "st",
        };

        name.append(befores[range(befores.length)])
                .append(vowels[range(vowels.length)])
                .append(afters[range(afters.length)]);

        if (rand.nextDouble() > 0.5) {
            if (rand.nextDouble() > 0.7) {
                name.append("-")
                        .append(befores[range(befores.length)]);
            }
            name.append(vowels[range(vowels.length)])
                    .append(afters[range(afters.length)]);
        }

        switch (damagePart.getDamageType()) {
            case BLUNT:
                String[] starters = {
                        "bl", "br"
                };

                break;
            case CLEAVING:
        }

        return name.toString();
    }

    static String genWord() {

        String word = "";

        Boolean start = rand.nextBoolean();

        // vowel or consonant start
        word += start ? consonants[Rand.range(consonants.length)] : vowels[Rand.range(vowels.length)];

        word += start ? vowels[Rand.range(vowels.length)] : consonants[Rand.range(consonants.length)];

        word += start ? consonants[Rand.range(consonants.length)] : vowels[Rand.range(vowels.length)];

        word += start ? vowels[Rand.range(vowels.length)] : consonants[Rand.range(consonants.length)];

        word += "-";

        word += numbers[Rand.range(numbers.length)];
        word += numbers[Rand.range(numbers.length)];

        word = word.substring(0, 1).toUpperCase() + word.substring(1, word.length());

        return word;

        // followed by opposite

        // followed by opposite
    }
}
