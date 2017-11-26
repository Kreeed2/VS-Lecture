package de.lecture;

import de.lecture.HTTP.HTTPRequest;
import de.lecture.HTTP.HTTPResponse;
import de.lecture.HTTP.WebRequest;
import de.lecture.HTTP.WebResponse;
import de.lecture.Servlet.EchoServlet;
import de.lecture.Servlet.PostServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
        System.out.println("WorkerRunnable spawned");
    }

    public void run() {
        try {
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            System.out.println("Streams connected");

            HTTPRequest request = new WebRequest(input);
            HTTPResponse response = new WebResponse(new PrintWriter(output),"HTTP/1.1 200 OK");;

            switch (request.getPathInfo()) {
                case "/":
                    new EchoServlet().service(request,response);
                    break;
                case "/EchoServlet":
                    new EchoServlet().service(request,response);
                    break;
                case "/PostServlet":
                    new PostServlet().service(request,response);
                    break;
                default:
                    break;
            }

            clientSocket.shutdownOutput();
            clientSocket.shutdownInput();
            System.out.println("WebRequest processed");
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
