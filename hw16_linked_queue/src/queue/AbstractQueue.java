package queue;

import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void retainIf(Predicate<Object> pred) {
        for (int i = size; i > 0; i--) {
            Object el = dequeue();
            if (pred.test(el)) {
                enqueue(el);
            }
        }
    }

    @Override
    public void removeIf(Predicate<Object> pred) {
        retainIf(pred.negate());
    }

    @Override
    public void dropWhile(Predicate<Object> pred) {
        applyWhile(pred, false, true);
    }

    @Override
    public void takeWhile(Predicate<Object> pred) {
        applyWhile(pred, true, false);
    }

    private void applyWhile(Predicate<Object> pred, boolean saveHead, boolean saveTail) {
        int sz = size;
        while (sz > 0 && pred.test(element())) {
            Object el = dequeue();
            if (saveHead) {
                enqueue(el);
            }
            sz--;
        }
        while (sz-- > 0 && !saveTail) {
            Object el = dequeue();
        }
    }
}
