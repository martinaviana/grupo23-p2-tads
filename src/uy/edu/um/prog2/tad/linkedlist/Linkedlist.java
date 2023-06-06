package uy.edu.um.prog2.tad.linkedlist;

import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.OutOfBondsException;

public interface Linkedlist<T> {
    // visibilidad interface nombre
    public void add(T value);
    public void remove(int position) throws EmptyListException, OutOfBondsException;

    public T get(int position) throws EmptyListException,OutOfBondsException;
    // especifico los cabezales de la operacion

}
