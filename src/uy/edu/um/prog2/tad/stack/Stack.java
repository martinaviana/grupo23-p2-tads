package uy.edu.um.prog2.tad.stack;

import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.EmptyStackException;
import uy.edu.um.prog2.tad.exceptions.OutOfBondsException;
import uy.edu.um.prog2.tad.linkedlist.ListaEnlazadaimp;

public class Stack<T> implements MyStack<T> {

    private ListaEnlazadaimp lista = new ListaEnlazadaimp();
    // para poder usar los métodos de lista, me creo una
    private int size;

    @Override
    public T pop() throws EmptyStackException {
        try {
            int posicionremovida =lista.size() - 1;
            T temp = (T) lista.get(posicionremovida);
            lista.remove(lista.size() - 1);
            size--;
            return temp;

        } catch (EmptyListException e) {
            throw new EmptyStackException("Esta vacia");
        } catch (OutOfBondsException e) {
            System.out.println("Out of bonds");
        }

        return null;

    }

    @Override
    public T top() throws EmptyStackException {
        if (lista.size() == 0) {
            throw new EmptyStackException("Esta vacia");
        }
        try {

            return (T) lista.get(size-1);
        } catch (EmptyListException e) {
            throw new EmptyStackException("Esta vacia");
        } catch (OutOfBondsException e) {
            System.out.println("Out of bonds");

        }
        return null;
    }


    @Override
    public void push(T element) {
        lista.add(element);
        size++;// suma de mi lista no de mi stack
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void makeEmpty() throws EmptyStackException {
        while (size != 0) {
            pop();
        }

    }
    public int size() {
        return size;
    }


    }
