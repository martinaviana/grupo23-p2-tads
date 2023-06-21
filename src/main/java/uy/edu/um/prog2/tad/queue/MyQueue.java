package uy.edu.um.prog2.tad.queue;

import uy.edu.um.prog2.tad.exceptions.EmptyQueueException;
import uy.edu.um.prog2.tad.exceptions.OutOfBoundsException;
import uy.edu.um.prog2.tad.exceptions.EmptyListException;
public interface MyQueue <T> {
    public void enqueue(T element);
    public T dequeue() throws OutOfBoundsException, EmptyListException, EmptyQueueException;
    public T front() throws OutOfBoundsException, EmptyListException, OutOfBoundsException, EmptyQueueException;
    public boolean isEmpty();
    public int size();

}