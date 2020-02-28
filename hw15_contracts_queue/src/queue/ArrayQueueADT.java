package queue;

// Inv: a[1], ..., a[n], n >= 0
// i in [1..n]: a[i] != null
public class ArrayQueueADT {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int size = 0;

    // Pre: element != null && queue != null
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert queue != null && element != null;
        expandQueue(queue);
        queue.elements[(queue.head + queue.size) % queue.elements.length] = element;
        queue.size++;
    }
    // Post: queue.n' = queue.n + 1 && any i in [1..n-1]: queue.a[i]' = queue.a[i] && queue.a[n] = element

    // Pre: queue != null && queue.n >= 0
    public static Object element(ArrayQueueADT queue) {
        assert queue != null && queue.size > 0;
        return queue.elements[queue.head];
    }
    // Post: R = queue.a[1] && immutable

    // Pre: queue != null &&  queue.n > 0
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue != null && queue.size > 0;
        Object resElement = element(queue);
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        queue.size--;
        return resElement;
    }
    // Post: queue.n' = queue.n - 1 && (i in [1..n-1] -> queue.a[i]' = queue.a[i + 1]) && queue.a[n] = null
    // R = queue.a[1]

    // Pre: queue != null && element != null
    public static void push(ArrayQueueADT queue, Object element) {
        assert element != null && queue != null;
        expandQueue(queue);
        queue.elements[(queue.head - 1 + queue.elements.length) % queue.elements.length] = element;
        queue.head = (queue.head - 1 + queue.elements.length) % queue.elements.length;
        queue.size++;
    }
    // Post: queue.n' = queue.n + 1 && (i in [2..n] -> queue.a[i]' = queue.a[i - 1]) && queue.a[1] = element

    // Pre: queue != null && queue.n > 0
    public static Object remove(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object resElement = peek(queue);
        queue.elements[(queue.head + queue.size - 1) % queue.elements.length] = null;
        queue.size--;
        return resElement;
    }
    // Post: queue.n' = queue.n - 1 && (i in [1..n-1] -> queue.a[i]' = queue.a[i]) && queue.a[n] = null
    // R = queue.a[1]

    // Pre: queue != null && queue.n > 0
    public static Object peek(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[(queue.head + queue.size - 1) % queue.elements.length];
    }
    // Post: R = queue.a[n] && immutable

    // Pre: queue != null && queue.n > ind >= 0
    public static Object get(ArrayQueueADT queue, int ind) {
        assert queue.size > ind;
        return queue.elements[(queue.head + ind) % queue.elements.length];
    }
    // Post: R = queue.a[ind + 1] && immutable

    // Pre: queue != null && queue.n > ind >= 0 && element != null
    public static void set(ArrayQueueADT queue, int ind, Object element) {
        assert queue.size > ind && element != null;
        queue.elements[(queue.head + ind) % queue.elements.length] = element;
    }
    // Post:  queue.n' = queue.n && (i in [1..queue.n] && i != ind + 1 -> queue.a[i]' = queue.a[i])
    // && queue.a[ind + 1]' = element

    // Pre: queue != null
    public static int size(ArrayQueueADT queue) {
        assert queue != null;
        return queue.size;
    }
    // Post: R = queue.n && immutable

    // Pre: queue != null
    public static boolean isEmpty(ArrayQueueADT queue) {
        assert queue != null;
        return queue.size == 0;
    }
    // Post: R = (queue.n == 0) && immutable

    // Pre: queue != null
    public static void clear(ArrayQueueADT queue) {
        assert queue != null;
        queue.elements = new Object[2];
        queue.head = queue.size = 0;
    }
    // Post: queue.n = 0 && immutable

    private static void expandQueue(ArrayQueueADT queue) {
        if (queue.size == queue.elements.length) {
            Object[] newElements = new Object[queue.elements.length * 2];
            System.arraycopy(queue.elements, queue.head, newElements, 0, queue.elements.length - queue.head);
            System.arraycopy(queue.elements, 0, newElements, queue.elements.length - queue.head, queue.head);
            queue.elements = newElements;
            queue.head = 0;
        }
    }
}
