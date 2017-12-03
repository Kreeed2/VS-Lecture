package de.lecture.Servlet;

import de.lecture.HTTP.HTTPRequest;
import de.lecture.HTTP.HTTPResponse;
import de.lecture.HTTP.HTTPServlet;
import de.lecture.Servlet.Template.HtmlConstructor;
import de.lecture.Servlet.Template.HtmlTable;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class EchoServlet extends HTTPServlet {

    String body;

    @Override
    public void service(HTTPRequest req, HTTPResponse res) {
        PrintWriter writer = res.getWriter();

        //HTML SITE
        HtmlConstructor htmlSite = new HtmlConstructor();
        htmlSite.addTitle("EchoServlet");

        HtmlTable table = new HtmlTable(2,req.getHeaderNames().size());
        table.addHeader("Header Name");
        table.addHeader("Header Value");
        for (String entry : req.getHeaderNames()) {
            table.addEntry(entry);
            table.addEntry(req.getHeader(entry));
            table.nextRow();
        }
        htmlSite.addElement(table);

        //Table
        table = new HtmlTable(2, req.getParameterNames().size());
        table.addHeader("Parameter Name");
        table.addHeader("Parameter Value");
        for (String entry : req.getParameterNames()) {
            table.addEntry(entry);
            table.addEntry(req.getParameter(entry));
            table.nextRow();
        }
        htmlSite.addElement(table);

        List<String> list = new LinkedList<>();
        list.add("Method: " + req.getMethod());
        list.add("PathInfo: " + req.getPathInfo());
        htmlSite.addList(list);

        body = htmlSite.toString();

        //Head
        res.setHeader("Content-Type","text/html");
        res.setHeader("Content-Length", String.valueOf(body.getBytes().length));
        writer.println(body);

        //refresh
        writer.flush();
        System.out.println("WriterStream EchoServlet flushed");
    }
}
