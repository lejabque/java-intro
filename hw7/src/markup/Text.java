package markup;

public class Text implements Markable {
    private String element;

    public Text(String s) {
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
