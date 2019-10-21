package markup;

import java.util.List;

class Strong extends AbstractMarkup {
    Strong(List<MarkdownElement> content) {
        super(content, "__");
    }

    Strong(MarkdownElement content) {
        this(List.of(content));
    }
}
