package md2html;


import java.util.Map;

public class ParagraphConverter {
    private Map<String, String> md2htmlTags = Map.of("*", "em", "_", "em",
            "**", "strong", "__", "strong",
            "`", "code", "--", "s", "[", "<a href='",
            "]", "</a>");
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
            switch (curChar) {
                case '[':
                    mdTag = "[";
                    htmlTag = md2htmlTags.get(mdTag);
                    break;
                case ']':
                    mdTag = "]";
                    htmlTag = md2htmlTags.get(mdTag);
                    break;
                case '`':
                    mdTag = "`";
                    htmlTag = md2htmlTags.get(mdTag);
                    break;
                case '*':
                case '_':
                    if (ind + 1 < line.length() &&
                            line.charAt(ind + 1) == curChar) {
                        mdTag = line.substring(ind, ind + 2);
                        ind++;
                    } else {
                        mdTag = line.substring(ind, ind + 1);
                    }
                    htmlTag = md2htmlTags.get(mdTag);
                    break;
                default:
                    if (curChar == '-' && ind + 1 < line.length()
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
            }

            if (!mdTag.isEmpty() && mdTag.equals(lastTag)) {  // close last tag
                resLine.append("</").append(htmlTag).append(">");
                return resLine;
            }
            ind++;
            // parse link
            if (mdTag.equals("]") && lastTag.equals("[") && ind < line.length() && line.charAt(ind) == '(') {
                ind++;
                StringBuilder link = new StringBuilder();
                while (ind < line.length() && line.charAt(ind) != ')') {
                    link.append(line.charAt(ind));
                    ind++;
                }
                if (ind < line.length()) {
                    ind++;
                }
                resLine.insert(0, md2htmlTags.get("[") + link + "'>").append(md2htmlTags.get("]"));
                return resLine;
            }

            if (!mdTag.isEmpty()) { // parse after tag
                StringBuilder editedLine = nextTag(line, new StringBuilder(), mdTag);
                if (editedLine.length() > htmlTag.length() &&  // check closing tag
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

    public StringBuilder convert(String paragraph, StringBuilder resLine) {
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
        return resLine;
    }
}
