public class Resource implements AutoCloseable {
    private final String name;

    public Resource(final String name) {
        this.name = name;
        System.out.println("    Resource '" + name + "' is created");
    }

    public void use() {
        System.out.println("    Resource  '" + name + "' is used");
    }

    public void close() {
        System.out.println("    Resource  '" + name + "' is closed");
    }
}
