package de.lecture;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPooledServer implements Runnable{

    private int          serverPort   = 8080;
    private ServerSocket serverSocket = null;
    private boolean      isStopped    = false;
    //private Thread       runningThread;
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    ThreadPooledServer(int port){
        this.serverPort = port;
    }

    public void run(){
//        synchronized(this){
//            this.runningThread = Thread.currentThread();
//        }
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    break;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            //this.threadPool.execute(
                    new Thread(new WorkerRunnable(clientSocket,"Thread Pooled Server")).start();
        }
        this.threadPool.shutdown();
        System.out.println("Server Stopped.") ;
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(serverPort);
            System.out.println("Opened port on " + serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + serverPort, e);
        }
    }
}
