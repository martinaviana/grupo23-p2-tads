package uy.edu.um.prog2.tad.linkedlist;

import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.OutOfBoundsException;

public interface MyList<T> {
    // visibilidad interface nombre
    public void add(T value);
    public void remove(int position) throws EmptyListException, OutOfBoundsException;

    public T get(int position) throws EmptyListException, OutOfBoundsException;
    // especifico los cabezales de la operacion
    boolean contains(T value);
    int size();
}
