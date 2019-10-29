package markup;

import java.util.List;

public class Strikeout extends AbstractContent implements MarkableElement {
    public Strikeout(List<MarkableElement> content) {
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
