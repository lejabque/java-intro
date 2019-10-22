package markup;

public class Text implements MarkableElement {
    private String element;

    Text(String s) {
        this.element = s;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(element);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(element);
    }
}
