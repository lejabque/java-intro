package markup;

import java.util.List;

public class UnorderedList extends AbstractContent implements ItemableElement {
    public UnorderedList(List<ListItem> content) {
        super(content);
    }

    @Deprecated
    @Override
    public void toMarkdown(StringBuilder sb) {
    }

    @Override
    public void toHtml(StringBuilder sb) {
        toHtml(sb, "<ul>", "</ul>");
    }
}
