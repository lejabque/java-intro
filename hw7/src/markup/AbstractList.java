package markup;

import java.util.List;

abstract class AbstractList implements MarkableElement {
    private List<ListItem> content;

    AbstractList(List<ListItem> content) {
        this.content = content;
    }

    public void toHtml(StringBuilder sb, String leftBorder, String rightBorder) {
        sb.append(leftBorder);
        for (ListItem el : content) {
            el.toHtml(sb);
        }
        sb.append(rightBorder);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        throw new NoSuchMethodError("List doesn't support Markdown");
    }
}
