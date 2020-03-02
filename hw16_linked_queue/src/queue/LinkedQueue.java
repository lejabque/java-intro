package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;

    // next -> to head
    // prev -> to tail
    @Override
    public void enqueue(Object element) {
        assert element != null;
        size++;
        tail = new Node(element, tail, null);
        if (tail.next != null) {
            tail.next.prev = tail;
        }
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
        if (head != null) {
            head.next = null;
        }
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

    private class Node {
        private Object value;
        private Node next;
        private Node prev;

        public Node(Object value, Node next, Node prev) {
            assert value != null;

            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
