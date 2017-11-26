package de.lecture.HTTP;

public abstract class HTTPServlet {
    public abstract  void service (HTTPRequest req, HTTPResponse res);
    public void doGet(HTTPRequest req, HTTPResponse res) {
        this.service(req, res);
    }
    public void doPost(HTTPRequest req, HTTPResponse res) {
        this.service(req, res);
    }
    public void doDelete(HTTPRequest req, HTTPResponse res) {
        this.service(req, res);
    }
    public void doHead(HTTPRequest req, HTTPResponse res) {
        this.service(req, res);
    }
    public void doOptions(HTTPRequest req, HTTPResponse res) {
        this.service(req, res);
    }
    public void doPut(HTTPRequest req, HTTPResponse res) {
        this.service(req, res);
    }
    public void doTrace(HTTPRequest req, HTTPResponse res) {
        this.service(req, res);
    }
}
