package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    public UnorderedList(List<ListItem> content) {
        super(content);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        toHtml(sb, "<ul>", "</ul>");
    }
}