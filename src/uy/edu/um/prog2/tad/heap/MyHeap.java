package uy.edu.um.prog2.tad.heap;
import uy.edu.um.prog2.tad.exceptions.EmptyHeapException;
public interface MyHeap<K extends Comparable<K>, T>{

    void insert(K key, T value);

    T delete() throws EmptyHeapException;

    int size();
    void isminHeap(boolean isHeapMin);
}
