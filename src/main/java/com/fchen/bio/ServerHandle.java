package com.fchen.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author: Fchen
 * @date: 2020/9/14 7:58 上午
 * @desc: TODO
 */
public class ServerHandle implements Runnable{

    private Socket socket;
    public ServerHandle (Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;

        try{
            reader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            String expression = null;
            while (true){
                if((expression = reader.readLine()) == null){
                    break;
                }
                System.out.println("服务端收到信息：" + expression);
                writer.println(expression);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (writer != null) {
                writer.close();
            }
        }

    }
}
