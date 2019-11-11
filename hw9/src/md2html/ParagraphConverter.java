package md2html;


import java.util.Map;

public class ParagraphConverter {
    private Map<String, String> md2HtmlTags = Map.of("*", "em", "_", "em",
            "**", "strong", "__", "strong",
            "`", "code", "--", "s");
    private Map<Character, String> htmlSymbols = Map.of('<', "&lt;",
            '>', "&gt;", '&', "&amp;");
    private int paragraphIndex;

    ParagraphConverter() {
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
            char curChar = line.charAt(paragraphIndex);
            if (curChar == '`') {
                mdTag = "`";
                htmlTag = md2HtmlTags.get(mdTag);
            } else if (curChar == '*' || curChar == '_') {
                if (paragraphIndex + 1 < line.length() &&
                        line.charAt(paragraphIndex + 1) == curChar) {
                    mdTag = line.substring(paragraphIndex, paragraphIndex + 2);
                    paragraphIndex++;
                } else {
                    mdTag = line.substring(paragraphIndex, paragraphIndex + 1);
                }
                htmlTag = md2HtmlTags.get(mdTag);
            } else if (curChar == '-' && paragraphIndex + 1 < line.length()
                    && line.charAt(paragraphIndex + 1) == '-') {
                mdTag = "--";
                paragraphIndex++;
                htmlTag = md2HtmlTags.get(mdTag);
            } else if (curChar == '\\' && paragraphIndex + 1 < line.length()) {
                if (md2HtmlTags.get(Character.toString(line.charAt(paragraphIndex + 1))) != null) {
                    paragraphIndex++;
                }
                resLine.append(line.charAt(paragraphIndex));
            } else {
                String htmlSymbol = htmlSymbols.get(curChar);
                if (htmlSymbol != null) {
                    resLine.append(htmlSymbol);
                } else {
                    resLine.append(curChar);
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
