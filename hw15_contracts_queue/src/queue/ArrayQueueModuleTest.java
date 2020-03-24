package queue;

import queue.ArrayQueueModule;

public class ArrayQueueModuleTest {
    public static void fillPush(int n) {
        for (int i = 0; i < n; i++) {
            ArrayQueueModule.push(i);
        }
    }

    public static void fillEnqueue(int n) {
        for (int i = 0; i < n; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }

    public static void dumpDequeue() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                    ArrayQueueModule.size() + " " +
                            ArrayQueueModule.element() + " " +
                            ArrayQueueModule.dequeue()
            );
        }
    }

    public static void dumpRemove() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                    ArrayQueueModule.size() + " " +
                            ArrayQueueModule.peek() + " " +
                            ArrayQueueModule.remove()
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
        System.out.println("Enqueue and dump test:");
        fillEnqueue(10);
        dumpDequeue();
        System.out.println("---------------");
        System.out.println("Fill and clear test:");
        fillEnqueue(10);
        clear();
        System.out.println("Push and dump test:");
        fillPush(10);
        dumpDequeue();
        System.out.println("---------------");
        System.out.println("Enqueue and remove test:");
        fillEnqueue(10);
        dumpRemove();
        System.out.println("---------------");
        System.out.println("Push and remove test:");
        fillPush(10);
        dumpRemove();
    }
}
