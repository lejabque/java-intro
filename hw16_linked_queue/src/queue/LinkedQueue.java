package queue;

import java.util.function.Predicate;

public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;

    @Override
    public void enqueue(Object element) {
        assert element != null;
        size++;
        Node newTail = new Node(element, null);
        if (tail != null) {
            tail.prev = newTail;
        }
        tail = newTail;
        if (head == null) {
            head = tail;
        }
    }

    @Override
    public Object dequeue() {
        assert size > 0;
        Object res = head.value;
        size--;
        head = head.prev;
        return res;
    }

    @Override
    public Object element() {
        assert size > 0;
        return head.value;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public void takeWhile(Predicate<Object> pred) {
        Node cur = head;
        size = 0;
        head = null;
        tail = null;
        while (cur != null && (pred.test(cur.value))) {
            if (head == null) {
                head = cur;
            }
            size++;
            tail = cur;
            cur = cur.prev;
        }
        if (tail != null) {
            tail.prev = null;
        }
    }

    private static class Node {
        private Object value;
        private Node prev;

        public Node(Object value, Node prev) {
            assert value != null;

            this.value = value;
            this.prev = prev;
        }
    }
}
