package com.nedink.message;

public abstract class AbstractMessage {
    public abstract String getMessage();

    public static class LineBuilder {
        StringBuilder line = new StringBuilder();

        public LineBuilder add(char c) {
            line.append(c);
            return this;
        }

        public LineBuilder add(char c, int length) {
            for (int i = 0; i < length; ++i)
                line.append(c);
            return this;
        }

        public LineBuilder add(String s) {
            line.append(s);
            return this;
        }

        public LineBuilder add(String s, int length) {
            for (int i = 0; i < length; ++i)
                line.append(s);
            return this;
        }

        public String build() {
            return line.toString();
        }
    }
}
