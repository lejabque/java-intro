package queue;

public class ArrayQueueModuleTest {
    public static void fill(int n) {
        for (int i = 0; i < n; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                    ArrayQueueModule.size() + " " +
                            ArrayQueueModule.element() + " " +
                            ArrayQueueModule.dequeue()
            );
        }
    }

    public static void clear() {
        ArrayQueueModule.clear();
        System.out.println("Queue size: " + ArrayQueueModule.size());
        if (ArrayQueueModule.isEmpty()){
            System.out.println("Queue is empty");
        }
    }

    public static void main(String[] args) {
        System.out.println("Fill and dump test:");
        fill(10);
        dump();
        System.out.println("---------------");
        System.out.println("Fill and clear test:");
        fill(10);
        clear();
    }
}
