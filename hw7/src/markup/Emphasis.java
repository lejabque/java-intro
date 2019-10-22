package markup;

import java.util.List;

class Emphasis extends AbstractMarkup {
    Emphasis(List<MarkableElement> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        toMarkdown(sb, "*");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        toHtml(sb, "<em>", "</em>");
    }
}
