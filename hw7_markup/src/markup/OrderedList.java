package markup;

import java.util.List;

public class OrderedList extends AbstractContent implements ItemableElement {
    public OrderedList(List<ListItem> content) {
        super(content);
    }

    @Deprecated
    @Override
    public void toMarkdown(StringBuilder sb) {
    }

    @Override
    public void toHtml(StringBuilder sb) {
        toHtml(sb, "<ol>", "</ol>");
    }
}
