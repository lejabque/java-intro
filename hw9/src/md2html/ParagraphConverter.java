package md2html;


import java.util.HashMap;
import java.util.Map;

public class ParagraphConverter {
    private Map<String, String> anyTags = new HashMap<String, String>();
    private Map<Character, String> htmlSymbols = new HashMap<Character, String>();
    private int paragraphIndex;

    ParagraphConverter() {
        mapsInit();
    }

    private void mapsInit() {
        anyTags.put("*", "em");
        anyTags.put("_", "em");
        anyTags.put("**", "strong");
        anyTags.put("__", "strong");
        anyTags.put("`", "code");
        anyTags.put("--", "s");

        htmlSymbols.put('<', "&lt;");
        htmlSymbols.put('>', "&gt;");
        htmlSymbols.put('&', "&amp;");
    }

    private int getHeaderLevel(String line) {
        int headerLevel = 0;
        while (headerLevel < line.length() && line.charAt(headerLevel) == '#') {
            headerLevel++;
        }
        if (headerLevel < line.length() && line.charAt(headerLevel) == ' ') {
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
            } else if (line.charAt(paragraphIndex) == '-' && paragraphIndex + 1 < line.length()
                    && line.charAt(paragraphIndex + 1) == '-') {
                mdTag = "--";
                paragraphIndex++;
                htmlTag = anyTags.get(mdTag);
            } else if (line.charAt(paragraphIndex) == '\\' && paragraphIndex + 1 < line.length()) {
                if (anyTags.get(line.substring(paragraphIndex + 1, paragraphIndex + 2)) != null) {
                    paragraphIndex++;
                }
                resLine.append(line.charAt(paragraphIndex));
            } else {
                String htmlSymbol = htmlSymbols.get(line.charAt(paragraphIndex));
                if (htmlSymbol != null) {
                    resLine.append(htmlSymbol);
                } else {
                    resLine.append(line.charAt(paragraphIndex));
                }
            }
            if (!mdTag.isEmpty() && mdTag.equals(lastTag)) {
                resLine.append("</").append(htmlTag).append(">");
                return resLine;
            }
            paragraphIndex++;
            if (!mdTag.isEmpty()) {
                StringBuilder editedLine = new StringBuilder();
                nextTag(line, editedLine, mdTag);
                if (editedLine.length() > htmlTag.length() &&
                        editedLine.substring(editedLine.length() - htmlTag.length() - 1,
                                editedLine.length() - 1).equals(htmlTag)) {
                    resLine.append("<").append(htmlTag).append(">").append(editedLine);
                    paragraphIndex++;
                } else {
                    resLine.append(mdTag).append(editedLine);
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
