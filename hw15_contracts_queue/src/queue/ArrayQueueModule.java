package queue;

import java.util.Arrays;

public class ArrayQueueModule {
    private static Object[] elements = new Object[2];
    private static int head = 0;
    private static int tail = 0;
    private static int size = 0;

    public static void enqueue(Object newElement) {
        if (size() == elements.length) {
            expandQueue();
        }
        elements[tail] = newElement;
        tail = (tail + 1) % elements.length;
        size++;
    }

    private static void expandQueue() {
        if (head == 0) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        } else {
            Object[] copyElements = new Object[elements.length * 2];
            for (int i = 0; i < elements.length; i++) {
                copyElements[i] = elements[(head + i) % elements.length];
            }
            elements = copyElements;
        }
        tail = size();
        head = 0;
    }

    public static Object element() {
        return elements[head];
    }

    public static Object dequeue() {
        Object resElement = element();
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return resElement;
    }

    public static int size() {
        return size;
        // tail > head ? tail - head : (tail + elements.length - head);
    }

    public static boolean isEmpty() {
        return size() == 0;
    }

    public static void clear() {
        elements = new Object[2];
        tail = head = size = 0;
    }
}
