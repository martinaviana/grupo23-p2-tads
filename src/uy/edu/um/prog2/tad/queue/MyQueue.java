package uy.edu.um.prog2.tad.queue;

import uy.edu.um.prog2.tad.exceptions.OutOfBondsException;
import uy.edu.um.prog2.tad.exceptions.EmptyListException;
public interface MyQueue <T> {
    public void enqueue(T element);
    public T dequeue() throws OutOfBondsException, EmptyListException;
    public T front() throws OutOfBondsException, EmptyListException, OutOfBondsException;
    public boolean isEmpty();

}