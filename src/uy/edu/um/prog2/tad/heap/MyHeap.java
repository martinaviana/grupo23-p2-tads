package uy.edu.um.prog2.tad.heap;

public interface MyHeap<T extends Comparable<T>>{

    void insert(T value);

    T deleteAndReturn();

    int size();
}
