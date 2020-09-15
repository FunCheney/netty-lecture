package com.fchen.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author: Fchen
 * @date: 2020/9/14 11:27 下午
 * @desc: TODO
 */
public class Client {
    /**
     * 默认端口号
     */
    private static int DEFAULT_PORT = 7777;

    private static String IP = "127.0.0.1";

    public static void send(String expression){
        send(DEFAULT_PORT, expression);
    }

    private static void send(int defaultPort, String expression) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(IP, DEFAULT_PORT);
            in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(expression);
            System.out.println("读到：" + in.readLine());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null) {
                out.close();
            }
        }
    }
}
