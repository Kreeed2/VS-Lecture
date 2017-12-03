package de.lecture.Servlet.Template;

import java.util.LinkedList;
import java.util.List;

public class HtmlConstructor {

    List<String> site = new LinkedList<>();

    public HtmlConstructor() {
        site.add("\n\n<html>");
        site.add("<head>");
        site.add("<head>");
        site.add("<body>");
        site.add("</body>");
        site.add("</html>");
    }

    public void addTitle(String content) {          //Title hinzufügen
        int index = site.indexOf("<head>");
        site.add(++index, "<title>" + content + "</title>");
    }

    public void addElement(Object element) {        //Element hinzufügen
        int index = site.indexOf("<body>");
        site.add(++index,element.toString());
    }

    public void addList(List<String> entries) {     //Liste hinzufügen
        int index = site.indexOf("<body>");
        site.add(++index, "<ul>");
        for (String entry: entries) {
            site.add(++index, "<li>" + entry + "</li>");
        }
        site.add(++index, "</ul>");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line: site) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }
}
