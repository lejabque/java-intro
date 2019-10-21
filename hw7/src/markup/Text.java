package markup;

public class Text implements MarkdownElement {
    private String element;

    Text(String s) {
        this.element = s;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(element);
    }
}
