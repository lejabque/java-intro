package queue;

public class ArrayQueueClassTest {
    public static void fill(ArrayQueue queue, int n) {
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
    }

    public static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(
                    queue.size() + " " +
                            queue.element() + " " +
                            queue.dequeue()
            );
        }
    }

    public static void clear(ArrayQueue queue) {
        queue.clear();
        System.out.println("Queue size: " + queue.size());
        if (queue.isEmpty()){
            System.out.println("Queue is empty");
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        System.out.println("Fill and dump test:");
        fill(queue, 10);
        dump(queue);
        System.out.println("---------------");
        System.out.println("Fill and clear test:");
        fill(queue, 10);
        clear(queue);
    }
}
