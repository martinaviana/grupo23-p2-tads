package uy.edu.um.prog2.tad.queue;



import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.tad.exceptions.OutOfBoundsException;
import uy.edu.um.prog2.tad.exceptions.EmptyQueueException;

public class Queueimpl<T> implements MyQueue<T> {
    private MyLinkedListImpl lista =new MyLinkedListImpl(); // para poder usar los métodos de lista, me creo una
    private int len;

    public int size(){
        return len;
    }
    @Override
    public void enqueue(T element) { //agregar
        lista.add(element);
        len ++;
    }

    @Override
    public T dequeue() throws OutOfBoundsException, EmptyListException, EmptyQueueException { //sacar
        if (lista.getLast() == null) { // si la queue esta vacia

            throw new EmptyQueueException();
        }
        T element = (T) lista.get(0);
        lista.remove(0);
        len--;
        return element;
    }

    @Override
    public T front() throws OutOfBoundsException, EmptyListException, EmptyQueueException { //obtener el primero
        if (lista.size()==0){
            throw new EmptyQueueException();
        }
        T element = (T) lista.get(0);
        return element;
    }

    @Override
    public boolean isEmpty() {
        if (lista.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
