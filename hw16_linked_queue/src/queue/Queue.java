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
    //
    // let M - set: (m in M -> 1 <= m <= n && pred(a[m]) == false) && (m not in M && 1 <= m <= n -> pred(a[m]) == true) &&
    // (i < j in [1..|M|] -> M[i] < M[j])
    // Post: n' = |M| && (1 <= i <= n' -> a[i]' = a[M[i]])
    void removeIf(Predicate<Object> pred);


    // Pre: pred != null
    //
    // let M - set: (m in M -> 1 <= m <= n && pred(a[m]) == true) && (m not in M && 1 <= m <= n -> pred(a[m]) == false)
    // && (i < j in [1..|M|] -> M[i] < M[j])
    // Post: n' = |M| && (1 <= i <= n' -> a[i]' = a[M[i]])
    void retainIf(Predicate<Object> pred);

    // Pre: pred != null
    //
    // let m: 0 <= m <= n && (1 <= i <= m -> pred(a[i]) == true) && (m < n - > pred(a[m + 1] == false)
    // Post: n' = m && (1 <= i <= n' -> a[i]' = a[i])
    void takeWhile(Predicate<Object> pred);

    // Pre: pred != null
    //
    // let m: 0 <= m <= n && (1 <= i <= m -> pred(a[i]) == true) && (m < n - > pred(a[m + 1] == false)
    // Post: n' = n - m && (1 <= i <= n' -> a[i]' = a[m + i])
    void dropWhile(Predicate<Object> pred);

    // Post: R = (n == 0) && immutable
    boolean isEmpty();
}
