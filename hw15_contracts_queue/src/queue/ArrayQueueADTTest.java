package queue;

public class ArrayQueueADTTest {
    public static void fillEnqueue(ArrayQueueADT queue, int n) {
        for (int i = 0; i < n; i++) {
            ArrayQueueADT.enqueue(queue, i);
        }
    }

    public static void fillPush(ArrayQueueADT queue, int n) {
        for (int i = 0; i < n; i++) {
            ArrayQueueADT.enqueue(queue, i);
        }
    }

    public static void dumpDequeue(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println(
                    ArrayQueueADT.size(queue) + " " +
                            ArrayQueueADT.element(queue) + " " +
                            ArrayQueueADT.dequeue(queue)
            );
        }
    }

    public static void dumpRemove(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println(
                    ArrayQueueADT.size(queue) + " " +
                            ArrayQueueADT.peek(queue) + " " +
                            ArrayQueueADT.remove(queue)
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
        fillEnqueue(queue, 10);
        dumpDequeue(queue);
        System.out.println("---------------");
        System.out.println("Fill and clear test:");
        fillEnqueue(queue, 10);
        clear(queue);
        System.out.println("---------------");
        System.out.println("Push and dump test:");
        fillPush(queue, 10);
        dumpDequeue(queue);
        System.out.println("---------------");
        System.out.println("Enqueue and remove test:");
        fillEnqueue(queue, 10);
        dumpRemove(queue);
        System.out.println("---------------");
        System.out.println("Push and remove test:");
        fillPush(queue,10);
        dumpRemove(queue);
    }
}
