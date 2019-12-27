package markup;

public interface Element {
    void toMarkdown(StringBuilder sb);
    void toHtml(StringBuilder sb);
}
