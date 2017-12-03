package de.lecture.Servlet.Template;

public class HtmlTable {

    private String[] th;
    private String[][] trs;

    private int lastIndexHeader = 0;
    private int lastIndexEntry = 0;
    private int lastIndexRow = 0;

    public HtmlTable(int width, int height) {       //Tabelle
        th = new String[width];
        trs = new String[height][width];
    }

    public void addHeader(String header) {
        th[lastIndexHeader++] = header;
    }

    public void nextRow() {
        ++lastIndexRow;
        lastIndexEntry = 0;
    }

    public void addEntry(String entry) {
        trs[lastIndexRow][lastIndexEntry++] = entry;
    }

    @Override
    public String toString() {      //Einfügen von Absätzen
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>\n");
        //Header Section
        stringBuilder.append("  <tr>");
        for (int i = 0; i < th.length; i++) {
            stringBuilder.append("    <th>").append(th[i]).append("</th>");
        }
        stringBuilder.append("  </tr>\n");
        //Body Section
        for (int i = 0; i < trs.length; i++) {
            stringBuilder.append("  <tr>");
            for (int j = 0; j < trs[i].length; j++) {
                stringBuilder.append("    <td>").append(trs[i][j]).append("</td>");
            }
            stringBuilder.append("  </tr>\n");
        }

        stringBuilder.append("</table>\n");
        return stringBuilder.toString();
    }
}
