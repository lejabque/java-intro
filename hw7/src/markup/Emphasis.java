package markup;

import java.util.List;

class Emphasis extends AbstractMarkup {
    Emphasis(List<MarkdownElement> content) {
        super(content);
        mdBorder = "*";
    }

    Emphasis(MarkdownElement content) {
        this(List.of(content));
    }
}
