package queue;

import java.util.Arrays;

// Inv: a[1], ..., a[n], n >= 0
// i in [1..n]: a[i] != null
public class ArrayQueueModule {
    private static Object[] elements = new Object[2];
    private static int head = 0;
    private static int size = 0;

    // Pre: element != null
    public static void enqueue(Object element) {
        if (size() == elements.length) {
            expandQueue();
        }
        elements[(head + size) % elements.length] = element;
        size++;
    }
    // Post: n' = n + 1 && (i in [1..n-1] -> a[i]' = a[i]) && a[n] = element

    // Pre: n > 0
    public static Object element() {
        return elements[head];
    }
    // Post: R = a[1]

    // Pre: n > 0
    public static Object dequeue() {
        Object resElement = element();
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return resElement;
    }
    // Post: n' = n - 1 && (i in [1..n-1] -> a[i]' = a[i + 1]) && a[n] = null
    // R = a[1]

    public static int size() {
        return size;
    }
    // Post: R = n

    public static boolean isEmpty() {
        return size() == 0;
    }
    // Post: R = (n == 0)

    public static void clear() {
        elements = new Object[2];
        head = size = 0;
    }
    // Post: n = 0

    private static void expandQueue() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, head, newElements, 0, elements.length - head);
        System.arraycopy(elements, 0, newElements, elements.length - head, size - (elements.length - head));
        elements = newElements;
        head = 0;
    }
}
