package markup;

import java.util.List;

public class ListItem extends AbstractMarkup implements MarkableElement {
    ListItem(List<MarkableElement> content) {
        super(content);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        toHtml(sb, "<li>", "</li>");
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        throw new NoSuchMethodError("ListItem doesn't support this method");
    }
}
