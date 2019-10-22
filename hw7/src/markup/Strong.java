package markup;

import java.util.List;

class Strong extends AbstractMarkup {
    Strong(List<MarkableElement> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        toMarkdown(sb, "__");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        toHtml(sb, "<strong>", "</strong>");
    }
}
