package org.example;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Client1 {
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;

    public Client1(String ip, int port) {
        try {
            socket = new Socket(ip,port);
            System.out.println(socket.getInetAddress().getHostAddress() + "에 연결 되었습니다.");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());

            // 문자열 생성
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
            LocalDateTime dateMethod = LocalDateTime.now(Clock.systemDefaultZone());
    //        System.out.println(Clock.systemDefaultZone());

            String uuid = UUID.randomUUID().toString();

            // 시간 포매팅
            String now = dateMethod.toString();
            String time = now.substring(now.indexOf("T")+1);

            // 날짜 포매팅
            String day = now.substring(0,now.indexOf("T"));
            time =  time.substring(0,time.lastIndexOf("."));

            day = day + " " + time;

            String cip = InetAddress.getLocalHost().toString();
            String clientIP = cip.substring(cip.indexOf("/")+1);

            pw.println("[FROM "+ clientIP +"] "+day+" "+uuid);
            pw.flush();
            
            System.out.println(br.readLine());

        } catch(IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (br != null) {
                    br.close();
                }
                if (pw != null) {
                    pw.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
