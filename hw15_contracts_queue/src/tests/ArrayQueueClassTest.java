package tests;

import queue.ArrayQueue;

public class ArrayQueueClassTest {
    public static void fillEnqueue(ArrayQueue queue, int n) {
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
    }

    public static void fillPush(ArrayQueue queue, int n) {
        for (int i = 0; i < n; i++) {
            queue.push(i);
        }
    }

    public static void dumpEnqueue(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(
                    queue.size() + " " +
                            queue.element() + " " +
                            queue.dequeue()
            );
        }
    }

    public static void dumpRemove(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(
                    queue.size() + " " +
                            queue.peek() + " " +
                            queue.remove()
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
        fillEnqueue(queue, 10);
        dumpEnqueue(queue);
        System.out.println("---------------");
        System.out.println("Fill and clear test:");
        fillEnqueue(queue, 10);
        clear(queue);
        System.out.println("---------------");
        System.out.println("Push and dump test:");
        fillPush(queue, 10);
        dumpEnqueue(queue);
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
