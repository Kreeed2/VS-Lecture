package de.lecture.HTTP;

public interface HTTPRequest {
    /*
        gibt das genutzte HTTP Verb zurück
     */
    String getMethod();

    /*
        gibt den angeforderten Pfad zurück (ohne Parameter!)
     */
    String getPathInfo();

    /*
        gibt die Namen alles Header zurück
     */
    java.util.List<String> getHeaderNames();

    /*
        gibt den Wert eines Headers zurück
     */
    String getHeader(String name);

    /*
        gibt die Namen aller Parameter zurück
     */
    java.util.List<String> getParameterNames();

    /*
        gibt den Wert eines Parameters zurück
     */
    String getParameter(String name);
}
