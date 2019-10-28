package markup;

import java.util.List;

public class Paragraph extends AbstractContent implements ItemableElement {
    public Paragraph(List<MarkableElement> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        toMarkdown(sb, "");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        toHtml(sb, "", "");
    }
}
