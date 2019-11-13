package md2html;


import java.util.Map;

public class ParagraphConverter {
    private Map<String, String> md2htmlTags = Map.of("*", "em", "_", "em",
            "**", "strong", "__", "strong",
            "`", "code", "--", "s");
    private Map<Character, String> htmlSymbols = Map.of('<', "&lt;",
            '>', "&gt;", '&', "&amp;");
    private int ind;
    private int linkStatus = 0;

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
            if (curChar == '[') {
                linkStatus = 1;
                mdTag = "[";
                htmlTag = "[";
            } else if (curChar == ']') {
                linkStatus = 2;
                mdTag = "]";
                htmlTag = "]";
            } else if (curChar == '`') {
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
            if (!mdTag.isEmpty() && mdTag.equals(lastTag)) {  // закрываем тег
                resLine.append("</").append(htmlTag).append(">");
                return resLine;
            }
            ind++;
            if (mdTag.equals("]") && lastTag.equals("[") && ind < line.length() && line.charAt(ind) == '(') {
                // парс ссылки
                ind++;
                StringBuilder link = new StringBuilder();
                while (ind < line.length() && line.charAt(ind) != ')') {
                    link.append(line.charAt(ind));
                    ind++;
                }
                if (ind < line.length()) {
                    ind++;
                }
                StringBuilder editedLine = new StringBuilder();
                editedLine.append("<a href='").append(link).append("'>").append(resLine).append("</a>");
                resLine = editedLine;
                return resLine;
            }
            if (!mdTag.isEmpty()) { // открываем тег
                StringBuilder editedLine = new StringBuilder();
                editedLine = nextTag(line, editedLine, mdTag);
                if (editedLine.length() > htmlTag.length() &&  // проверяем, закрылся ли тег
                        editedLine.substring(editedLine.length() - htmlTag.length() - 1,
                                editedLine.length() - 1).equals(htmlTag)) {
                    resLine.append("<").append(htmlTag).append(">").append(editedLine);
                    ind++;
                } else {
                    if (!mdTag.equals("[")) {
                        resLine.append(mdTag);
                    }
                    resLine.append(editedLine);
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
