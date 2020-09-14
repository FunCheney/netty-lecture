package com.fchen.bio;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Fchen
 * @date: 2020/9/14 7:34 上午
 * @desc: Bio 服务端源码
 */
public class Server {
    /**
     * 默认端口号
     */
    private static int DEFAULT_PORT = 7777;

    private static ServerSocket serverSocket;

    public static void start () throws IOException {
        start(DEFAULT_PORT);
    }

    private synchronized static void start(int defaultPort) throws IOException {
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("服务端启动，端口号：" + DEFAULT_PORT);
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandle(socket)).start();
            }
        } finally {
            if (serverSocket != null) {
                System.out.println("服务端已关闭");
                serverSocket.close();
            }
        }
    }
}
