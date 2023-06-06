package uy.edu.um.prog2.tad.linkedlist;

import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.OutOfBondsException;


public class ListaEnlazadaimp<T> implements Linkedlist<T> {
    private Nodo<T> first; // se inicializa en null first porque no hice ninguna asignacion , first=null sería lo mismo

    private Nodo<T> last;
    private int size = 0;

    @Override
    public void add(T value) {

        Nodo<T> elementtoAdd = new Nodo<T>(value);
        if (first == null) {
            first = elementtoAdd;
            last = first;

        } else {

            last.setNextNode(elementtoAdd);
            last = elementtoAdd;

        }
        size++;
    }

    @Override
    public void remove(int position) throws EmptyListException, OutOfBondsException {
        Nodo<T> tempAnterior = first;
        int p = 0;

        // Busco el último nodo y lo dejo en la variable temp
        if (first == null) {
            throw new EmptyListException("esta vacio");
        }

        if (position >= this.size) {
            throw new OutOfBondsException("posición excede tamaño");
        } else {
            if (position == 0) {
                first = first.getNextNode();// hago que linkedlist me apunte al segundo porque elimine el primero
            } else if (position < size - 1) {
                while (p <(position -1)) {
                    tempAnterior = tempAnterior.getNextNode();
                    p++;
                }
                System.out.println(tempAnterior);
                tempAnterior.setNextNode(tempAnterior.getNextNode().getNextNode());
            } else if (position == size - 1) {
                while (p < (position-1)) {
                    tempAnterior = tempAnterior.getNextNode();
                    p++;
                }
                tempAnterior.setNextNode(null);
                this.last = tempAnterior;
            }
        }
        size--;
    }


    @Override
    public T get(int position) throws EmptyListException, OutOfBondsException {
        int t = 0;
        Nodo<T> tempGet = first;
        if (first == null) {
            throw new EmptyListException("Esta vacio");
        } else if (position <= size - 1) {
            while (t < position) {
                tempGet = tempGet.getNextNode();
                t++;
            }
            return tempGet.getValue();
        } else if (position > size - 1) {
            throw new OutOfBondsException("Excede tamaño");
        } else {
            return null;
        }


    }


    public int size() {
        return size;
    }

    public Boolean Encuentralemento2(T elemento) throws EmptyListException {
        int c = 0;
        if (this.first == null) {
            throw new EmptyListException("Esta vacio");

        } else if (this.first == elemento) {
            return true;
        } else {

            while (c <= size - 1) {
                T temp = null;
                try {
                    temp = get(c);
                } catch (OutOfBondsException e) {

                }
                if (temp == elemento) {

                    return true;
                }
                c++;
            }
            return false;
        }

    }


    public Boolean encuentraElemento(T elemento) throws EmptyListException {
        if (this.first == null) {
            throw new EmptyListException("Esta vacio");
        } else {
            Nodo<T> temp = first;
            while (temp != null) {
                if (temp.getValue() == elemento) {
                    return true;
                }
                temp = temp.getNextNode();
            }
            return false;
        }
    }

    public void addFirst(T value) {

        Nodo<T> elementtoAdd = new Nodo(value);
        if (first == null) {
            first = elementtoAdd;
            last = first;
        } else {
            Nodo tempaddfirst = first;
            first = elementtoAdd;
            first.setNextNode(tempaddfirst);

        }
        size++;

    }
}



