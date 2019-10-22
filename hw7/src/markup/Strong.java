package markup;

import java.util.List;

class Strong extends AbstractMarkup implements Markable {
    public Strong(List<Markable> content) {
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
