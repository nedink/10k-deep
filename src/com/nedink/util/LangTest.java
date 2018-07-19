package com.nedink.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LangTest {

    public static void main(String[] args) {
        String thing = "a ab abc";

        Scanner scanner = new Scanner(thing);
        System.out.println(scanner.next());
        System.out.println(scanner.next());
        System.out.println(scanner.next());

    }
}
