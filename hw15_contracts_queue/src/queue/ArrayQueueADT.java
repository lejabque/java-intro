package queue;

import java.util.Arrays;

public class ArrayQueueADT {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int size = 0;

    public static void enqueue(ArrayQueueADT queue, Object newElement) {
        if (size(queue) == queue.elements.length) {
            expandQueue(queue);
        }
        queue.elements[(queue.head + queue.size) % queue.elements.length] = newElement;
        queue.size++;
    }

    public static Object element(ArrayQueueADT queue) {
        return queue.elements[queue.head];
    }

    public static Object dequeue(ArrayQueueADT queue) {
        Object resElement = element(queue);
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        queue.size--;
        return resElement;
    }

    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return size(queue) == 0;
    }

    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[2];
        queue.head = queue.size = 0;
    }

    private static void expandQueue(ArrayQueueADT queue) {
        if (queue.head == 0) {
            queue.elements = Arrays.copyOf(queue.elements, queue.elements.length * 2);
        } else {
            Object[] copyElements = new Object[queue.elements.length * 2];
            for (int i = 0; i < queue.elements.length; i++) {
                copyElements[i] = queue.elements[(queue.head + i) % queue.elements.length];
            }
            queue.elements = copyElements;
        }
        queue.head = 0;
    }
}
