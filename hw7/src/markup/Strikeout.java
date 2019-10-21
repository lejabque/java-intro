package markup;

import java.util.List;

class Strikeout extends AbstractMarkup {
    Strikeout(List<MarkdownElement> content) {
        super(content, "~");
    }

    Strikeout(MarkdownElement content) {
        this(List.of(content));
    }
}
