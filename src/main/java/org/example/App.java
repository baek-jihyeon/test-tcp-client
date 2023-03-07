package org.example;

import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        String ip = System.getenv("DSERVER_IP");
        int port = 4432;
        Timer timer = new Timer("Timer");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                new Client1(ip,port);
            }
        };
        timer.scheduleAtFixedRate(task,0,2000);
    }
}