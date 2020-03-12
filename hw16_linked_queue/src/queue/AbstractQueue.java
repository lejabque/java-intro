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
        int sz = size;
        for (int i = 0; i < sz; i++) {
            if (pred.test(element())) {
                enqueue(dequeue());
            } else {
                dequeue();
            }
        }
    }

    @Override
    public void removeIf(Predicate<Object> pred) {
        retainIf(pred.negate());
    }

    @Override
    public void dropWhile(Predicate<Object> pred) {
        while (!isEmpty() && (pred.test(element()))) {
            dequeue();
        }
    }

    @Override
    public void takeWhile(Predicate<Object> pred) {
        int sz = size;
        while (sz > 0 && pred.test(element())) {
            enqueue(dequeue());
            sz--;
        }
        while (sz-- > 0) {
            dequeue();
        }
    }
}
