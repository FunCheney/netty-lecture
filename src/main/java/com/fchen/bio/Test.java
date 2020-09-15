package com.fchen.bio;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: Fchen
 * @date: 2020/9/14 11:44 下午
 * @desc: TODO
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(100);

        String op = "hello";
        Random random = new Random(System.currentTimeMillis());

        new Thread(new Runnable() {
            @Override
            public void run() {
                String expression = random.nextInt(10) + op;
                Client.send(expression);
            }
        }).start();
    }
}
