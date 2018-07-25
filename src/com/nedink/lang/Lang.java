package com.nedink.lang;

import com.nedink.util.CustomMath;
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
        Rarity rarity = damagePart.getRarity();

        String[] befores = {
                "b", "d", "p", "t", "g", "k", "j",
                "cl", "cr", "ch",
                "kl", "kr",
                "pl", "pr",
                "tr",
                "bl", "br",
                "gr",
        };
        String[] vowels = {
                "oh", "uh", "oo", "aw",
        };
        String[] afters = {
                "b","b","b",
                "d",
                "g","g","g",
                "k",
                "l","l","l","l",
                "m",
                "n",
                "r","r",
                "t","t",
                "x",
                "z",

                "dj",
                "gh",
                "mp",
                "ng",
                "nk",
                "nx",
                "zk",
                "x",
        };


//        double contChance = rarity == Rarity.COMMON ? 0.0 : rarity == Rarity.UNCOMMON ? 0.5 : rarity == Rarity.RARE ? 0.5 : rarity == Rarity.EPIC ? 0.6 : rarity == Rarity.LEGENDARY ? 0.8 : 0;
//        double chanceDecreaseAmount = rarity == Rarity.COMMON ? 0.0 : rarity == Rarity.UNCOMMON ? 0.1 : rarity == Rarity.RARE ? 0.5 : rarity == Rarity.EPIC ? 0.7 : rarity == Rarity.LEGENDARY ? 0.95 : 0;

        int lenMin = rarity == Rarity.COMMON ? 5 :
                rarity == Rarity.UNCOMMON ? 3 :
                        rarity == Rarity.RARE ? 2 :
                                rarity == Rarity.EPIC ? 1 :
                                        rarity == Rarity.LEGENDARY ? 0 : 0;

        int lenMax = rarity == Rarity.COMMON ? 5 :
                rarity == Rarity.UNCOMMON ? 3 :
                        rarity == Rarity.RARE ? 2 :
                                rarity == Rarity.EPIC ? 1 :
                                        rarity == Rarity.LEGENDARY ? 0 : 0;

        WordGenBuilder wordGenBuilder = new WordGenBuilder()
                .append(CustomMath.normalizedChances(befores.length, vowels.length)[0], befores) // befores
                .append(1.0, vowels);

        int round = 0;
        double continueChance = 1.0;
        while (round < lenMax && rand.nextDouble() < continueChance) {
            wordGenBuilder
                    .append(0.4, new String[]{"'"})
//                    .ifLastInAppend(new String[][]{befores, vowels}, 0.4, new String[]{"'"})
                    .ifSuccessAppend(0.75, befores)
                    .ifLastInAppend(new String[][]{vowels}, 1.0, afters)
                    .append(1.0, vowels);
            if (round > lenMin) continueChance = 0.0;
            ++round;
        }

        return wordGenBuilder.toString();
    }

    // ------------------------------------------------------------------------------------------------------

    private static class WordGenBuilder {
        private StringBuilder wordGen = new StringBuilder();
        private double cumulativeChance = 1.0;
        private String lastAppended = null;
        private boolean successLast = false;

        private void append(String phoneme) {
            wordGen.append(phoneme);
            lastAppended = phoneme;
        }

        private WordGenBuilder append(double chance, String phoneme) {
            successLast = false;
            if (rand.nextDouble() < chance * cumulativeChance) {
                append(phoneme);
                successLast = true;
            }
            return this;
        }

        // TODO
        private WordGenBuilder append(double chance, PhoneticClass phonemeClass) {
            return this;
        }

        private WordGenBuilder append(double chance, String[]... phonemeClasses) {
            successLast = false;
            int total = 0;
            for (String[] strings : phonemeClasses) total += strings.length;
            String[] aggregate = new String[total];
            int index = 0;
            for (String[] strings : phonemeClasses) for (String s : strings) aggregate[index++] = s;
            if (rand.nextDouble() < chance * cumulativeChance) {
                append(aggregate[range(aggregate.length)]);
                successLast = true;
            }
            return this;
        }

        private WordGenBuilder ifSuccessAppend(double chance, String[]... phonemeClasses) {
            if (successLast)
                append(chance, phonemeClasses);
            return this;
        }

        private WordGenBuilder ifNotSuccessAppend(double chance, String[]... phonemeClasses) {
            if (!successLast)
                append(chance, phonemeClasses);
            return this;
        }

        private WordGenBuilder ifElseSuccessAppend(double chance1, String[] phonemeClass1, double chance2, String[]... phonemeClass2) {
            return successLast ? append(chance1, phonemeClass1) : append(chance2, phonemeClass2);
        }

        private WordGenBuilder ifLastInAppend(String[][] phonemesSearch, double chance, String[]... phonemeClasses) {
            // prepare for failure of append
            successLast = false;

            boolean containsLastAppended = false;

            for (String[] phonemeSearch : phonemesSearch) {
                for (String phoneme : phonemeSearch) {
                    if (lastAppended != null && lastAppended.equals(phoneme)) {
                        containsLastAppended = true;
                        break;
                    }
                }
            }

            if (containsLastAppended)
                append(chance, phonemeClasses);

            return this;
        }

        private WordGenBuilder ifLastNotInAppend(String[][] phonemesSearch, double chance, String[]... phonemeClasses) {
            // prepare for failure
            successLast = false;

            for (String[] phonemeSearch : phonemesSearch)
                for (String phoneme : phonemeSearch)
                    if (lastAppended != null && lastAppended.equals(phoneme))
                        return this;

            append(chance, phonemeClasses);

            return this;
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
            successLast = successLast ? success1 : success2;
            return this;
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
}
