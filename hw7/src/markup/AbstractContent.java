package markup;

import java.util.List;

abstract class AbstractContent implements Element {
    private List<? extends Element> content;

    protected AbstractContent(List<? extends Element> content) {
        this.content = content;
    }


    protected void toHtml(StringBuilder sb, String leftBorder, String rightBorder) {
        sb.append(leftBorder);
        for (Element el : content) {
            el.toHtml(sb);
        }
        sb.append(rightBorder);
    }

    protected void toMarkdown(StringBuilder sb, String mdBorder) {
        sb.append(mdBorder);
        for (Element el : content) {
            el.toMarkdown(sb);
        }
        sb.append(mdBorder);
    }
}
