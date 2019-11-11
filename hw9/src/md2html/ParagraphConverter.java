package md2html;


import java.util.Map;

public class ParagraphConverter {
    private Map<String, String> md2htmlTags = Map.of("*", "em", "_", "em",
            "**", "strong", "__", "strong",
            "`", "code", "--", "s");
    private Map<Character, String> htmlSymbols = Map.of('<', "&lt;",
            '>', "&gt;", '&', "&amp;");
    private int ind;

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
        while (ind < line.length()) {
            char curChar = line.charAt(ind);
            if (curChar == '`') {
                mdTag = "`";
                htmlTag = md2htmlTags.get(mdTag);
            } else if (curChar == '*' || curChar == '_') {
                if (ind + 1 < line.length() &&
                        line.charAt(ind + 1) == curChar) {
                    mdTag = line.substring(ind, ind + 2);
                    ind++;
                } else {
                    mdTag = line.substring(ind, ind + 1);
                }
                htmlTag = md2htmlTags.get(mdTag);
            } else if (curChar == '-' && ind + 1 < line.length()
                    && line.charAt(ind + 1) == '-') {
                mdTag = "--";
                ind++;
                htmlTag = md2htmlTags.get(mdTag);
            } else if (curChar == '\\' && ind + 1 < line.length()) {
                if (md2htmlTags.get(Character.toString(line.charAt(ind + 1))) != null) {
                    ind++;
                }
                resLine.append(line.charAt(ind));
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
            ind++;
            if (!mdTag.isEmpty()) {
                StringBuilder editedLine = new StringBuilder();
                nextTag(line, editedLine, mdTag);
                if (editedLine.length() > htmlTag.length() &&
                        editedLine.substring(editedLine.length() - htmlTag.length() - 1,
                                editedLine.length() - 1).equals(htmlTag)) {
                    resLine.append("<").append(htmlTag).append(">").append(editedLine);
                    ind++;
                } else {
                    resLine.append(mdTag).append(editedLine);
                }
                mdTag = "";
            }
        }
        return resLine;
    }

    public void convert(String paragraph, StringBuilder resLine) {
        ind = 0;

        int headerLevel = getHeaderLevel(paragraph);
        if (headerLevel > 0) {
            resLine.append("<h").append(headerLevel).append(">");
            ind = headerLevel + 1;
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
