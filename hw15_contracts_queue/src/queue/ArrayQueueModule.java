package queue;

// Inv: a[1], ..., a[n], n >= 0
// i in [1..n]: a[i] != null
public class ArrayQueueModule {
    private static Object[] elements = new Object[2];
    private static int head = 0;
    private static int size = 0;

    // Pre: element != null
    public static void enqueue(Object element) {
        assert element != null;
        expandQueue();
        elements[(head + size) % elements.length] = element;
        size++;
    }
    // Post: n' = n + 1 && (i in [1..n-1] -> a[i]' = a[i]) && a[n] = element

    // Pre: n > 0
    public static Object element() {
        assert size > 0;
        return elements[head];
    }
    // Post: R = a[1] && immutable

    // Pre: n > 0
    public static Object dequeue() {
        assert size > 0;
        Object resElement = element();
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return resElement;
    }
    // Post: n' = n - 1 && (i in [1..n-1] -> a[i]' = a[i + 1]) && a[n] = null
    // R = a[1]

    // Pre: element != null
    public static void push(Object element) {
        assert element != null;
        expandQueue();
        elements[(head - 1 + elements.length) % elements.length] = element;
        head = (head - 1 + elements.length) % elements.length;
        size++;
    }
    // Post: n' = n + 1 && (i in [2..n] -> a[i]' = a[i - 1]) && a[1] = element

    // Pre: n > 0
    public static Object remove() {
        assert size > 0;
        Object resElement = peek();
        elements[(head + size - 1) % elements.length] = null;
        size--;
        return resElement;
    }
    // Post: n' = n - 1 && (i in [1..n-1] -> a[i]' = a[i]) && a[n] = null
    // R = a[1]

    // Pre: n > 0
    public static Object peek() {
        assert size > 0;
        return elements[(head + size - 1) % elements.length];
    }
    // Post: R = a[n] && immutable

    // Pre: n > ind >= 0
    public static Object get(int ind) {
        assert size > ind && ind >= 0;
        return elements[(head + ind) % elements.length];
    }
    // Post: R = a[ind + 1] && immutable

    // Pre: n > ind >= 0 && element != null
    public static void set(int ind, Object element) {
        assert size > ind && ind >= 0 && element != null;
        elements[(head + ind) % elements.length] = element;
    }
    // Post:  n' = n && (i in [1..n] && i != ind + 1 -> a[i]' = a[i]) && a[ind + 1]' = element

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
        if (elements.length == size) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, head, newElements, 0, elements.length - head);
            System.arraycopy(elements, 0, newElements, elements.length - head, head);
            elements = newElements;
            head = 0;
        }
    }
}
