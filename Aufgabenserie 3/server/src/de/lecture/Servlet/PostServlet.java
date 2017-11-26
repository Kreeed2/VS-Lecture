package de.lecture.Servlet;

import de.lecture.HTTP.HTTPRequest;
import de.lecture.HTTP.HTTPResponse;
import de.lecture.HTTP.HTTPServlet;
import de.lecture.Servlet.Template.HtmlConstructor;
import de.lecture.Servlet.Template.HtmlForm;

public class PostServlet extends HTTPServlet{
    @Override
    public void service(HTTPRequest req, HTTPResponse res) {
        HtmlConstructor site = new HtmlConstructor();

        site.addTitle("PostServlet");

        HtmlForm form = new HtmlForm("/EchoServlet");
        form.addTextBox("Name","name","");
        form.addTextBox("Adresse","address", "");
        form.addButton("submit", "Submit");

        site.addElement(form);

        String body = site.toString();

        res.setHeader("Content-Type","text/html");
        res.setHeader("Content-Length", String.valueOf(body.getBytes().length));
        res.getWriter().println(body);

        res.getWriter().flush();
        System.out.println("WriterStream PostServlet flushed");

    }
}
