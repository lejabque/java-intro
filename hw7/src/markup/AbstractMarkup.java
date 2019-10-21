package markup;

import java.util.List;

abstract class AbstractMarkup implements MarkdownElement {
    protected String mdBorder;
    private List<MarkdownElement> content;

    AbstractMarkup(List<MarkdownElement> content) {
        this.content = content;
    }


    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(mdBorder);
        for (MarkdownElement el : content) {
            el.toMarkdown(sb);
        }
        sb.append(mdBorder);
    }
}
