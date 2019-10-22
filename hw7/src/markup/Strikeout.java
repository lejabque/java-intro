package markup;

import java.util.List;

class Strikeout extends AbstractMarkup implements Markable {
    Strikeout(List<Markable> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        toMarkdown(sb, "~");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        toHtml(sb, "<s>", "</s>");
    }
}
