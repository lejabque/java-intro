package markup;

import java.util.List;

abstract class AbstractMarkup implements MarkableElement {
    private List<MarkableElement> content;

    AbstractMarkup(List<MarkableElement> content) {
        this.content = content;
    }

    public void toHtml(StringBuilder sb, String leftBorder, String rightBorder) {
        sb.append(leftBorder);
        for (MarkableElement el : content) {
            el.toHtml(sb);
        }
        sb.append(rightBorder);
    }

    public void toMarkdown(StringBuilder sb, String mdBorder) {
        sb.append(mdBorder);
        for (MarkableElement el : content) {
            el.toMarkdown(sb);
        }
        sb.append(mdBorder);
    }
}
