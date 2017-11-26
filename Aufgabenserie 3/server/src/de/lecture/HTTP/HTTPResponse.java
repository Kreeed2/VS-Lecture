package de.lecture.HTTP;

public interface HTTPResponse {
    /*
        fügt einen Headereintrag zur Ausgabe hinzu bzw. ändert vorhandenen Eintrag
     */
    void setHeader(String name, String value);

    /*
        liefert den Ausgabenstrom
     */
    java.io.PrintWriter getWriter();
}
