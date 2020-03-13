package queue;

// Inv: a[1], ..., a[n], n >= 0
// i in [1..n]: a[i] != null
public class ArrayQueue {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int size = 0;

    // Pre: element != null
    // Post: n' = n + 1 && (i in [1..n] -> a[i]' = a[i]) && a[n + 1]' = element
    public void enqueue(Object element) {
        assert element != null;
        expandQueue();
        elements[(head + size) % elements.length] = element;
        size++;
    }

    // Pre: n > 0
    // Post: R = a[1] && immutable
    public Object element() {
        assert size > 0;
        return elements[head];
    }

    // Pre: n > 0
    // Post: n' = n - 1 && (i in [1..n-1] -> a[i]' = a[i + 1])
    // R = a[1]
    public Object dequeue() {
        assert size > 0;
        Object resElement = element();
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return resElement;
    }

    // Pre: element != null
    // Post: n' = n + 1 && (i in [2..n+1] -> a[i]' = a[i - 1]) && a[1]' = element
    public void push(Object element) {
        assert element != null;
        expandQueue();
        elements[(head - 1 + elements.length) % elements.length] = element;
        head = (head - 1 + elements.length) % elements.length;
        size++;
    }

    // Pre: n > 0
    // Post: n' = n - 1 && (i in [1..n-1] -> a[i]' = a[i])
    // R = a[1]
    public Object remove() {
        assert size > 0;
        Object resElement = peek();
        elements[(head + size - 1) % elements.length] = null;
        size--;
        return resElement;
    }

    // Pre: n > 0
    // Post: R = a[n] && immutable
    public Object peek() {
        assert size > 0;
        return elements[(head + size - 1) % elements.length];
    }

    // Pre: n > ind >= 0
    // Post: R = a[ind + 1] && immutable
    public Object get(int ind) {
        assert size > ind && ind >= 0;
        return elements[(head + ind) % elements.length];
    }

    // Pre: n > ind >= 0 && element != null
    // Post:  n' = n && (i in [1..n] && i != ind + 1 -> a[i]' = a[i]) && a[ind + 1]' = element
    public void set(int ind, Object element) {
        assert size > ind && ind >= 0 && element != null;
        elements[(head + ind) % elements.length] = element;
    }

    // Post: R = n && immutable
    public int size() {
        return size;
    }

    // Post: R = (n == 0) && immutable
    public boolean isEmpty() {
        return size() == 0;
    }

    // Post: n = 0
    public void clear() {
        elements = new Object[2];
        head = size = 0;
    }

    // Pre: elements != null && size != null
    // Post: elements.length > size && immutable
    private void expandQueue() {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, head, newElements, 0, elements.length - head);
            System.arraycopy(elements, 0, newElements, elements.length - head, head);
            elements = newElements;
            head = 0;
        }
    }
}
