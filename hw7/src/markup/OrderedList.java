package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    OrderedList(List<ListItem> content) {
        super(content);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        toHtml(sb, "<ol>", "</ol>");
    }
}
