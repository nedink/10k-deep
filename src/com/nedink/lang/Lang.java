package com.nedink.lang;

import com.nedink.util.CustomMath;
import com.nedink.util.Rand;
import com.nedink.world.DamagePart;
import com.nedink.world.Rarity;

import static com.nedink.util.Rand.rand;
import static com.nedink.util.Rand.range;

public class Lang {

    private enum PhoneticClass {
        NUMBERS, ALPHABET, VOWELS, CONSONANTS, BEFORES, AFTERS
    }

    // count = 10
    private static String[] numbers = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    // count = 26
    private static String[] alphabet = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };

    // count = 6
    private static String[] vowels = {
            "a", "e", "i", "o", "u", "y"
    };

    // count = 20
    private static String[] consonants = {
            "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"
    };

    // count = 6
    private static String[] befores = {
            "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z",
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
            "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z",
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
                "b", "d", "p", "t", "f", "g", "k", "c",
                "cl", "cr", "ch",
                "kl", "kr",
                "pl", "pr",
                "tr",
//                "cl", "cr",
                "bl", "br",
                "gr",
                "j",
        };
        String[] vowels = {
                "o", "u", "i", "oo",
        };
        String[] afters = {
                "b", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z",
//                "b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z",
//                "ck", "ct",
                "ft", "fk",
                "gh",
                "ld", "lg", "lk", "ll", "lm", "ln", "lt",
                "mn", "mp", "mt",
                "ng", "nk", "nt", "nx",
                "ph", "pt",
                "rb", "rc", "rf", "rg", "rk", "rl", "rm", "rn", "rp", "rt",
                "sk", "sp", "st",
        };


        Rarity rarity = damagePart.getRarity();
        double contChance = rarity == Rarity.COMMON ? 0.0 : rarity == Rarity.UNCOMMON ? 0.5 : rarity == Rarity.RARE ? 0.5 : rarity == Rarity.EPIC ? 0.6 : rarity == Rarity.LEGENDARY ? 0.8 : 0;
        double chanceDecreaseAmount = rarity == Rarity.COMMON ? 0.0 : rarity == Rarity.UNCOMMON ? 0.1 : rarity == Rarity.RARE ? 0.5 : rarity == Rarity.EPIC ? 0.7 : rarity == Rarity.LEGENDARY ? 0.95 : 0;

        WordGenBuilder wordGenBuilder = new WordGenBuilder()
                .append(CustomMath.normalizedChances(befores.length, vowels.length)[0], befores) // befores
                .append(1.0, vowels)
                .append(0.5, afters);

        while (rand.nextDouble() < contChance) {
            wordGenBuilder.append(1.0 - contChance, "'")
                    .ifSuccessAppend(0.75, befores)
                    .ifElseSuccessAppend(0.7, befores, 1.0, vowels)
                    .append(0.75, afters);

            contChance *= chanceDecreaseAmount;
        }
//        wordGenBuilder.append(0.75, afters);

        name = new StringBuilder(wordGenBuilder.toString());


//        name.append(rand.nextDouble() < CustomMath.normalizedChances(befores.length, vowels.length)[0] ? befores[range(befores.length)] : "");
//        name.append(vowels[range(vowels.length)]);

//        while (rand.nextDouble() < contChance) {
//            name.append(afters[range(afters.length)]);
//            if (rand.nextDouble() < 1.0 - contChance) name.append("'");
//            if (rand.nextDouble() < 0.5) name.append(befores[range(befores.length)]);
//            name.append(vowels[range(vowels.length)]);
//
//            contChance *= chanceDecreaseAmount;
//        }
//        if (rand.nextDouble() < 0.75) name.append(afters[range(afters.length)]);


        return name.toString();
    }

    private static class WordGenBuilder {
        private StringBuilder wordGen = new StringBuilder();
        private double cumulativeChance = 1.0;
        private boolean successLast = false;

        private WordGenBuilder append(double chance, String phonetic) {
            successLast = false;
            if (rand.nextDouble() < chance * cumulativeChance) {
                wordGen.append(phonetic);
                successLast = true;
            }
            return this;
        }

        // TODO
        private WordGenBuilder append(double chance, PhoneticClass phoneticClass) {
            return this;
        }

        private WordGenBuilder append(double chance, String[]... phoneticsClasses) {
            successLast = false;
            int total = 0;
            for (String[] strings : phoneticsClasses) total += strings.length;
            String[] aggregate = new String[total];
            int index = 0;
            for (String[] strings : phoneticsClasses) for (String s : strings) aggregate[index++] = s;
            if (rand.nextDouble() < chance * cumulativeChance) {
                wordGen.append(aggregate[range(aggregate.length)]);
                successLast = true;
            }
            return this;
        }

        private WordGenBuilder ifSuccessAppend(double chance, String[]... phoneticClasses) {
            if (successLast)
                append(chance, phoneticClasses);
            return this;
        }

        private WordGenBuilder ifNotSuccessAppend(double chance, String[]... phoneticClasses) {
            if (!successLast)
                append(chance, phoneticClasses);
            return this;
        }

        private WordGenBuilder ifElseSuccessAppend(double chance1, String[] phoneticClass1, double chance2, String[]... phoneticClass2) {
            return successLast ? append(chance1, phoneticClass1) : append(chance2, phoneticClass2);
        }

        private WordGenBuilder ifSuccessSetSuccess(boolean success) {
            if (successLast)
                successLast = success;
            return this;
        }

        private WordGenBuilder ifNotSuccessSetSuccess(boolean success) {
            if (!successLast)
                successLast = success;
            return this;
        }

        private WordGenBuilder ifElseSuccessSetSuccess(boolean success1, boolean success2) {
            successLast = successLast ? success1 : success2; return this;
        }

        private void cascadeChance(double chance) {
            cumulativeChance *= chance;
            setChance(cumulativeChance);
        }

        private void setChance(double chance) {
            cumulativeChance = chance > 1.0 ? 1.0 : chance < 0.0 ? 0.0 : chance;
        }

        @Override
        public String toString() {
            return wordGen.toString();
        }
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
