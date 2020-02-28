package queue;

public class ArrayQueue {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int size = 0;

    // Pre: element != null
    public void enqueue(Object element) {
        assert element != null;
        if (size() == elements.length) {
            expandQueue();
        }
        elements[(head + size) % elements.length] = element;
        size++;
    }
    // Post: n' = n + 1 && (i in [1..n-1] -> a[i]' = a[i]) && a[n] = element

    // Pre: n > 0
    public Object element() {
        assert size > 0;
        return elements[head];
    }
    // Post: R = a[1]

    // Pre: n > 0
    public Object dequeue() {
        assert size > 0;
        Object resElement = element();
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return resElement;
    }
    // Post: n' = n - 1 && (i in [1..n-1] -> a[i]' = a[i + 1]) && a[n] = null
    // R = a[1]

    public int size() {
        return size;
    }
    // Post: R = n

    public boolean isEmpty() {
        return size() == 0;
    }
    // Post: R = (n == 0)

    public void clear() {
        elements = new Object[2];
        head = size = 0;
    }
    // Post: n = 0

    private void expandQueue() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, head, newElements, 0, elements.length - head);
        System.arraycopy(elements, 0, newElements, elements.length - head, size - (elements.length - head));
        elements = newElements;
        head = 0;
    }
}
