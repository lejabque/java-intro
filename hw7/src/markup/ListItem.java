package markup;

import java.util.List;

public class ListItem extends AbstractMarkup {
    ListItem(List<Listable> content) {
        super(content);
    }


    public void toHtml(StringBuilder sb) {
        toHtml(sb, "<li>", "</li>");
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        throw new NoSuchMethodError("ListItem doesn't support Markdown");
    }
}
