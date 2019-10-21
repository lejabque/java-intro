package markup;

import java.util.List;

public class Paragraph implements MarkdownElement {
    private List<MarkdownElement> content;

    Paragraph(List<MarkdownElement> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (var el : content) {
            el.toMarkdown(sb);
        }
    }
}
