package uy.edu.um.prog2.tad.linkedlist;

import org.junit.Test;
import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.OutOfBondsException;

import static org.junit.Assert.*;

public class ListaEnlazadaimpTest {

    @Test
    public void add() {
        Linkedlist listita = new ListaEnlazadaimp();
        listita.add(1);
        try {
            assertEquals(listita.get(0),1);
        } catch (EmptyListException | OutOfBondsException e) {
            throw new RuntimeException(e);
        }
        try {
            boolean condicion = listita.get(0) != null;
            assertTrue(condicion);
        } catch (EmptyListException | OutOfBondsException e) {
            throw new RuntimeException(e);
        }

    }

   /* @Test
    /*public void remove() throws OutOfBondsException, EmptyListException {
        ListaEnlazadaimp lista = new ListaEnlazadaimp<>();
        lista.add(3);
        lista.add(4);
        System.out.println(lista.size()+ " size");
        lista.remove(lista.size() - 1);
        System.out.println(lista.get(0));
        lista.remove(0);
        /*try{
        lista.remove(0);
    } catch (EmptyListException e){
            System.out.println("vacia");}
    }*/

    @Test
    public void get() {
    }

    @Test
    public void size() {
    }

    @Test
    public void encuentralemento2() {
    }

    @Test
    public void encuentraElemento() {
    }

    @Test
    public void addFirst() {
    }
}