package com.nedink.util;

public abstract class AbstractMessage {
    public abstract String print();

    public static class LineBuilder {
        String line = "";

        public LineBuilder add(char c) {
            line += c;
            return this;
        }

        public LineBuilder add(char c, int length) {
            for (int i = 0; i < length; ++i)
                line += c;
            return this;
        }

        public LineBuilder add(String s) {
            line += s;
            return this;
        }

        public LineBuilder add(String s, int length) {
            for (int i = 0; i < length; ++i)
                line += s;
            return this;
        }

        public String build() {
            return line;
        }
    }
}
