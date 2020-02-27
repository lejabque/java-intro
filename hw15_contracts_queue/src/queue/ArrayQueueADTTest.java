package queue;

public class ArrayQueueADTTest {
    public static void fill(ArrayQueueADT queue, int n) {
        for (int i = 0; i < n; i++) {
            ArrayQueueADT.enqueue(queue, i);
        }
    }

    public static void dump(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println(
                    ArrayQueueADT.size(queue) + " " +
                            ArrayQueueADT.element(queue) + " " +
                            ArrayQueueADT.dequeue(queue)
            );
        }
    }

    public static void clear(ArrayQueueADT queue) {
        ArrayQueueADT.clear(queue);
        System.out.println("Queue size: " + ArrayQueueADT.size(queue));
        if (ArrayQueueADT.isEmpty(queue)){
            System.out.println("Queue is empty");
        }
    }

    public static void main(String[] args) {
        ArrayQueueADT queue = new ArrayQueueADT();
        System.out.println("Fill and dump test:");
        fill(queue, 10);
        dump(queue);
        System.out.println("---------------");
        System.out.println("Fill and clear test:");
        fill(queue, 10);
        clear(queue);
    }
}
