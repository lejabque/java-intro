package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class md2html {

    static class ParagraphConverter {
        private Map<String, String> anyTags = new HashMap<String, String>();
        private int paragraphIndex;

        ParagraphConverter() {
            tagsInit();
        }

        private void tagsInit() {
            anyTags.put("*", "em");
            anyTags.put("_", "em");
            anyTags.put("**", "strong");
            anyTags.put("__", "strong");
            anyTags.put("`", "code");
            anyTags.put("--", "s");
        }

        private int getHeaderLevel(String line) {
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


        private StringBuilder nextTag(String line, StringBuilder resLine, String lastTag) {
            String mdTag = "";
            String htmlTag = "";
            while (paragraphIndex < line.length()) {
                if (line.charAt(paragraphIndex) == '`') {
                    mdTag = "`";
                    htmlTag = anyTags.get(mdTag);
                } else if (line.charAt(paragraphIndex) == '*' || line.charAt(paragraphIndex) == '_') {
                    if (paragraphIndex + 1 < line.length() &&
                            line.charAt(paragraphIndex + 1) == line.charAt(paragraphIndex)) {
                        mdTag = line.substring(paragraphIndex, paragraphIndex + 2);
                        paragraphIndex++;
                    } else {
                        mdTag = line.substring(paragraphIndex, paragraphIndex + 1);
                    }
                    htmlTag = anyTags.get(mdTag);
                } else if (line.charAt(paragraphIndex) == '-' && paragraphIndex + 1 < line.length() &&
                        line.charAt(paragraphIndex + 1) == '-') {
                    mdTag = "--";
                    paragraphIndex++;
                    htmlTag = anyTags.get(mdTag);
                } else if (line.charAt(paragraphIndex) == '\\' && paragraphIndex + 1 < line.length()) {
                    if (anyTags.get(line.substring(paragraphIndex + 1, paragraphIndex + 2)) != null) {
                        paragraphIndex++;
                    }
                    resLine.append(line.charAt(paragraphIndex));
                } else {
                    resLine.append(line.charAt(paragraphIndex));
                }
                if (!mdTag.equals("") && mdTag.equals(lastTag)) {
                    resLine.append("</").append(htmlTag).append(">");
                    return resLine;
                }
                paragraphIndex++;
                if (!mdTag.equals("")) {
                    StringBuilder editedLine = new StringBuilder();
                    nextTag(line, editedLine, mdTag);
                    if (editedLine.length() - htmlTag.length() > 0 &&
                            editedLine.substring(editedLine.length() - htmlTag.length() - 1,
                                    editedLine.length() - 1).equals(htmlTag)) {
                        resLine.append("<").append(htmlTag).append(">");
                        resLine.append(editedLine);
                        paragraphIndex++;
                    } else {
                        resLine.append(mdTag);
                        resLine.append(editedLine);
                    }
                    mdTag = "";
                }
            }
            return resLine;
        }

        public void convert(String paragraph, StringBuilder resLine) {
            paragraphIndex = 0;

            int headerLevel = getHeaderLevel(paragraph);
            if (headerLevel > 0) {
                resLine.append("<h").append(headerLevel).append(">");
                paragraphIndex = headerLevel + 1;
            } else {
                resLine.append("<p>");
            }
            nextTag(paragraph, resLine, "");
            if (headerLevel > 0) {
                resLine.append("</h").append(headerLevel).append(">");
            } else {
                resLine.append("</p>");
            }
        }
    }

    public static void main(String[] args) {
        String inFile = "/home/lejabque/Desktop/Study/term1/hw/prog-intro-hw/hw9/src/md2html/md.in";
        // String inFile = args[0];
        ParagraphConverter converter = new ParagraphConverter();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(new File(inFile)), StandardCharsets.UTF_8))) {
            String line = in.readLine();
            while (line != null) {
                StringBuilder paragraph = new StringBuilder();
                while (line.equals("")) {
                    line = in.readLine();
                }
                while (line != null && !line.equals("")) {
                    if (paragraph.length() != 0) {
                        paragraph.append('\n');
                    }
                    paragraph.append(line);
                    line = in.readLine();
                }
                if (paragraph.length() > 0) {
                    StringBuilder resParagraph = new StringBuilder();
                    converter.convert(paragraph.toString(), resParagraph);
                    System.out.println(resParagraph.toString());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}