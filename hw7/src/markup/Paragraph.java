package markup;

import java.util.List;

public class Paragraph extends AbstractMarkup implements Listable {
    public Paragraph(List<Markable> content) {
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
