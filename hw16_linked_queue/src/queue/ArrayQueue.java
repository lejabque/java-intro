package queue;

import java.util.function.Predicate;

public class ArrayQueue extends AbstractQueue {
    private Object[] elements = new Object[2];
    private int head = 0;

    @Override
    public void enqueue(Object element) {
        assert element != null;
        expandQueue();
        elements[(head + size) % elements.length] = element;
        size++;
    }

    @Override
    public Object element() {
        assert size > 0;
        return elements[head];
    }

    @Override
    public Object dequeue() {
        assert size > 0;
        Object resElement = element();
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return resElement;
    }

    @Override
    public void clear() {
        elements = new Object[2];
        head = size = 0;
    }

    @Override
    public void removeIf(Predicate<Object> pred) {
        retainIf(pred.negate());
    }

    @Override
    public void retainIf(Predicate<Object> pred) {
        Object[] newElements = new Object[elements.length];
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (pred.test(elements[(head + i) % elements.length])) {
                newElements[newSize] = elements[(head + i) % elements.length];
                newSize++;
            }
        }
        head = 0;
        elements = newElements;
        size = newSize;
    }

    @Override
    public void takeWhile(Predicate<Object> pred) {
        int newSize = 0;
        while (newSize < size && pred.test(elements[(head + newSize) % elements.length])) {
            newSize++;
        }
        for (int i = newSize; i < size; i++) {
            elements[(head + i) % elements.length] = null;
        }
        size = newSize;
    }


    @Override
    public void dropWhile(Predicate<Object> pred) {
        while (!isEmpty() && (pred.test(element()))) {
            dequeue();
        }
    }

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
