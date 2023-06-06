package uy.edu.um.prog2.tad.stack;

import uy.edu.um.prog2.tad.exceptions.EmptyStackException;

public interface MyStack<T> {
    public T pop () throws EmptyStackException;

    public T top() throws EmptyStackException;
    public void push(T element);
    public boolean isEmpty ();
    public void makeEmpty() throws EmptyStackException;

}
