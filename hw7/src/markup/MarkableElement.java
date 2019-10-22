package markup;

public interface MarkableElement {
    void toMarkdown(StringBuilder sb);
    void toHtml(StringBuilder sb);
}
