package queue;

import java.util.function.Predicate;

// Inv: a[1], ..., a[n], n >= 0
// i in [1..n]: a[i] != null
public interface Queue {

    // Pre: element != null
    void enqueue(Object element);
    // Post: n' = n + 1 && (i in [1..n] -> a[i]' = a[i]) && a[n+1]' = element

    // Pre: n > 0
    Object dequeue();
    // Post: n' = n - 1 && (i in [1..n-1] -> a[i]' = a[i + 1]) && a[n]' = null
    // R = a[1]

    // Pre: n > 0
    Object element();
    // Post: R = a[1] && immutable

    int size();
    // Post: R = n && immutable

    void clear();
    // Post: n = 0

    // Pre: pred != null
    void removeIf(Predicate<Object> pred);
    // Post: a' = {x in a: pred(x) == false} && n' = |queue'|

    // Pre: pred != null
    void retainIf(Predicate<Object> pred);
    // Post: n = 0

    // Pre: pred != null
    void takeWhile(Predicate<Object> pred);
    // Post: n = 0

    // Pre: pred != null
    void dropWhile(Predicate<Object> pred);
    // Post: n = 0

    boolean isEmpty();
    // Post: R = (n == 0) && immutable
}
