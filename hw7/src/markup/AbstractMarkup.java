package markup;

import java.util.List;

abstract class AbstractMarkup implements MarkdownElement {
    private String mdBorder;
    private List<MarkdownElement> content;

    AbstractMarkup(List<MarkdownElement> content,
                   String mdBorder) {
        this.content = content;
        this.mdBorder = mdBorder;
    }


    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(mdBorder);
        for (var el : content) {
            el.toMarkdown(sb);
        }
        sb.append(mdBorder);
    }
}
