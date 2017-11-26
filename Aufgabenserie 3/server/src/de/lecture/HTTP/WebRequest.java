package de.lecture.HTTP;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WebRequest implements HTTPRequest {

    private HashMap<String,String> headers = new HashMap<>();
    private HashMap<String,String> parameters = new HashMap<>();
    private String method;
    private String pathInfo;

    public WebRequest(InputStream inputStream) {
        try {
            System.out.println("WebRequest started");
            String debug = convertStreamToString(inputStream);
            String[] lines = debug.split("[\\r\\n]+");

            String[] firstLineSegments = lines[0].split("[\\s?&]");
            method = firstLineSegments[0];
            pathInfo = firstLineSegments[1];

            for (int i = 1; i < lines.length - 1; i++) {
                headers.put(lines[i].split(":")[0], lines[i].split(":")[1].trim());
            }

            if (method.equals("POST")) {

                if (getHeader("Content-Type").equals("application/json")) {

                    JSONParser jsonParser = new JSONParser();
                    JSONObject obj = (JSONObject) jsonParser.parse(lines[lines.length - 1]);

                    parameters.putAll(obj);
                } else {
                    String[] elements = lines[lines.length-1].split("[=&]");
                    for (int i = 0; i < elements.length; i+=2) {
                        parameters.put(elements[i],elements[i+1]);
                    }
                }

            } else {

                for (int i = 2; i < firstLineSegments.length-1 ; i++) {
                    parameters.put(firstLineSegments[i].split("=")[0],firstLineSegments[i].split("=")[1]);
                }
            }

            System.out.println("WebRequest created and filled");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String convertStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int length;

        //TODO figure out why thread stops on the second run of the loop, probably something todo with open stream
        //while ((length = inputStream.read(buffer)) != -1) {
        //    result.write(buffer, 0, length);
        //}

        length = inputStream.read(buffer);
        if (length != -1)
            result.write(buffer,0,length);

        return result.toString("UTF-8");
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getPathInfo() {
        return pathInfo;
    }

    @Override
    public List<String> getHeaderNames() {
        return new LinkedList<>(headers.keySet());
    }

    @Override
    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public List<String> getParameterNames() {
        return new LinkedList<>(parameters.keySet());
    }

    @Override
    public String getParameter(String name) {
        return parameters.get(name);
    }
}
