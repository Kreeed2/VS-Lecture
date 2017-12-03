package de.lecture;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ThreadPooledServer server = new ThreadPooledServer(9000);
        new Thread(server).start();         //New Thread every Client

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Stopping Server");
        server.stop();
    }
}
