package md2html;
import java.io.*;
import java.util.*;

public class md2html {
    private static Map<String, String> anyTags = new HashMap<String, String>();
    private static int i = 0;

    private static void tagsInit() {
        anyTags.put("*", "em");
        anyTags.put("_", "em");
        anyTags.put("**", "strong");
        anyTags.put("__", "strong");
        anyTags.put("`", "code");
        anyTags.put("--", "s");
    }

    private static int getHeaderLevel(String line) {
        int i = 0;
        int headerLevel = 0;
        while (i < line.length() && line.charAt(i) == '#') {
            headerLevel++;
            i++;
        }
        if (i < line.length() && line.charAt(i) == ' ') {
            return headerLevel;
        }
        return 0;
    }

    public static String nextTag(String line, String lastTag) {
        StringBuilder resLine = new StringBuilder();
        String mdTag = "";
        String htmlTag = "";
        while (i < line.length()) {
            if (line.charAt(i) == '`') {
                mdTag = "`";
                htmlTag = anyTags.get(mdTag);
            } else if (line.charAt(i) == '*' || line.charAt(i) == '_') {
                if (i + 1 < line.length() && line.charAt(i + 1) == line.charAt(i)) {
                    mdTag = line.substring(i, i + 2);
                    i++;
                } else {
                    mdTag = line.substring(i, i + 1);
                }
                htmlTag = anyTags.get(mdTag);
            } else if (line.charAt(i) == '-' && i + 1 < line.length() &&
                    line.charAt(i + 1) == '-') {
                mdTag = "--";
                i++;
                htmlTag = anyTags.get(mdTag);
            } else {
                resLine.append(line.charAt(i));
            }
            if (!mdTag.equals("") && mdTag.equals(lastTag)) {
                resLine.append("</" + htmlTag + ">");
                return resLine.toString();
            }
            i++;
            if (!mdTag.equals("")) {
                String addedLine = nextTag(line, mdTag);
                if (addedLine.substring(addedLine.length() - htmlTag.length() - 1, addedLine.length() - 1).equals(htmlTag)) {
                    resLine.append("<" + htmlTag + ">");
                    i++;
                } else {
                    resLine.append(mdTag);
                }
                resLine.append(addedLine);
                mdTag = "";
            }
        }
        return resLine.toString();
    }

    public static void main(String[] args) {
        String inFile = "/home/lejabque/Desktop/Study/term1/hw/prog-intro-hw/hw9/src/md2html/md.in";
        // String inFile = args[0];
        tagsInit();
        List<String> paragraphs = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(new File(inFile)), "utf8"))) {
            String line = in.readLine();
            StringBuilder paragraph = new StringBuilder();
            while (line != null) {
                if (line.equals("")) {
                    paragraphs.add(paragraph.toString());
                    paragraph = new StringBuilder();
                } else {
                    int headerLevel = getHeaderLevel(line);
                    String editedLine = "";
                    if (headerLevel > 0) {
                        editedLine += "<h" + headerLevel + ">";
                    }
                    i = headerLevel;
                    editedLine += nextTag(line, "");
                    if (headerLevel > 0) {
                        editedLine += "</h" + headerLevel + ">";
                    }
                    paragraph.append(editedLine);
                    System.out.println(editedLine);
                }
                line = in.readLine();
                if (line == null) {
                    paragraphs.add(paragraph.toString());
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
        System.out.println(paragraphs.size());
    }
}