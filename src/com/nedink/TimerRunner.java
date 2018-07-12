package com.nedink;

import java.util.Timer;

public class TimerRunner implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("STOP");
        }

    }

}
