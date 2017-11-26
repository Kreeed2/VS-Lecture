package de.lecture.Servlet.Template;

public class HtmlForm {

    StringBuilder stringBuilder = new StringBuilder();

    public HtmlForm(String action) {
        stringBuilder.append("<form method=\"POST\" action=\"").append(action).append("\">\n");
    }

    public void addButton(String type, String value) {
        stringBuilder.append("<input type=\"").append(type).append("\" value=\"").append(value).append("\"><br>\n");
    }

    public void addTextBox(String label, String name, String value) {
        stringBuilder.append(label).append(":<br>\n");
        stringBuilder.append("<input type=\"text\" name=\"").append(name).append("\" value=\"").append(value).append("\"><br><br>\n");
    }

    @Override
    public String toString() {
        stringBuilder.append("</form>\n");
        return stringBuilder.toString();
    }
}
