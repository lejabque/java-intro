package queue;

public abstract class AbstractQueue implements Queue {
    protected int size = 0;
    @Override
    public abstract void enqueue(Object element);

    @Override
    public abstract  Object dequeue();

    @Override
    public abstract Object element();

    @Override
    public abstract void clear();

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
