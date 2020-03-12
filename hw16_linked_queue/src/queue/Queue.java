package queue;

import java.util.function.Predicate;

// Inv: a[1], ..., a[n], n >= 0
// i in [1..n]: a[i] != null
public interface Queue {

    // Pre: element != null
    // Post: n' = n + 1 && (i in [1..n] -> a[i]' = a[i]) && a[n+1]' = element
    void enqueue(Object element);

    // Pre: n > 0
    // Post: n' = n - 1 && (i in [1..n-1] -> a[i]' = a[i + 1])
    // R = a[1]
    Object dequeue();

    // Pre: n > 0
    // Post: R = a[1] && immutable
    Object element();

    // Post: R = n && immutable
    int size();

    // Post: n = 0
    void clear();

    // Pre: pred != null
    // Post:TODO
    void removeIf(Predicate<Object> pred);

    // Pre: pred != null
    // Post: TODO
    void retainIf(Predicate<Object> pred);

    // Pre: pred != null
    // Post: TODO
    void takeWhile(Predicate<Object> pred);

    // Pre: pred != null
    // Post: TODO
    void dropWhile(Predicate<Object> pred);

    // Post: R = (n == 0) && immutable
    boolean isEmpty();
}
