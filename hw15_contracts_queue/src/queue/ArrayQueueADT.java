package queue;

public class ArrayQueueADT {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int size = 0;

    // Pre: element != null && queue != null
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert queue != null;
        assert element != null;
        if (size(queue) == queue.elements.length) {
            expandQueue(queue);
        }
        queue.elements[(queue.head + queue.size) % queue.elements.length] = element;
        queue.size++;
    }
    // Post: n' = n + 1 && any i in [1..n-1]: a[i]' = a[i] && a[n] = element

    // Pre: queue != null && n >= 0
    public static Object element(ArrayQueueADT queue) {
        assert queue != null;
        assert queue.size > 0;
        return queue.elements[queue.head];
    }
    // Post: R = a[1]

    // Pre: queue != null &&  n > 0
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue != null;
        assert queue.size > 0;
        Object resElement = element(queue);
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        queue.size--;
        return resElement;
    }
    // Post: n' = n - 1 && (i in [1..n-1] -> a[i]' = a[i + 1]) && a[n] = null
    // R = a[1]

    // Pre: queue != null
    public static int size(ArrayQueueADT queue) {
        assert queue != null;
        return queue.size;
    }
    // Post: R = n

    // Pre: queue != null
    public static boolean isEmpty(ArrayQueueADT queue) {
        assert queue != null;
        return size(queue) == 0;
    }
    // Post: R = (n == 0)

    // Pre: queue != null
    public static void clear(ArrayQueueADT queue) {
        assert queue != null;
        queue.elements = new Object[2];
        queue.head = queue.size = 0;
    }
    // Post: n = 0

    private static void expandQueue(ArrayQueueADT queue) {
        Object[] newElements = new Object[queue.elements.length * 2];
        System.arraycopy(queue.elements, queue.head, newElements, 0, queue.elements.length - queue.head);
        System.arraycopy(queue.elements, 0, newElements, queue.elements.length - queue.head,
                queue.size - (queue.elements.length - queue.head));
        queue.elements = newElements;
        queue.head = 0;
    }
}
