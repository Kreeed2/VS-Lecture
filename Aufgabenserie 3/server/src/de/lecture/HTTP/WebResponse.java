package de.lecture.HTTP;

import java.io.PrintWriter;

public class WebResponse implements HTTPResponse{

    private PrintWriter writer;

    public WebResponse(PrintWriter writer, String statusCode) {
        this.writer = writer;
        writer.println(statusCode);
    }

    @Override
    public void setHeader(String name, String value) {
       writer.println(name + ": " + value);
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }
}
