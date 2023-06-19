package uy.edu.um.prog2.tad.linkedlist;


import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.OutOfBondsException;

import static org.junit.jupiter.api.Assertions.*;


public class ListaEnlazadaimpTest {

    @Test
    public void add() {
        MyList<Integer> list = new ListaEnlazadaimp();
        list.add(1);
        try {
            int x = list.get(0);
            assertEquals(x,1);
        } catch (EmptyListException | OutOfBondsException e) {
            throw new RuntimeException(e);
        }
        try {
            boolean condicion = list.get(0) != null;
            assertTrue(condicion);
        } catch (EmptyListException | OutOfBondsException e) {
            throw new RuntimeException(e);
        }
        list.add(4);
        list.add(5);
        list.add(7);
        assertEquals(4, list.size());
        try{ list.add(null);
        } catch (NullPointerException e){
            System.out.println("no se puede agregar null");
        }



    }

   @Test
    public void remove() throws OutOfBondsException, EmptyListException {
        ListaEnlazadaimp lista = new ListaEnlazadaimp<>();
        lista.add(3);
        lista.add(4);
        System.out.println(lista.size()+ " size");
        lista.remove(lista.size() - 1);
       assertEquals(1, lista.size());
       try {
           lista.get(1);
       }
         catch (OutOfBondsException e){
              System.out.println("out of bounds");}

        System.out.println(lista.get(0));
        lista.remove(0);
        try{
        lista.remove(0);
    } catch (EmptyListException e){
            System.out.println("vacia");}
        try{ lista.remove(lista.size() - 1);
    } catch(EmptyListException e){
            System.out.println("vacia");}
   }


    @Test
    public void get() {
        MyList<Integer> list = new ListaEnlazadaimp();
        try {
            list.get(0);
        } catch (EmptyListException | OutOfBondsException e) {
            System.out.println("vacia");
        }
        list.add(1);

        assertEquals(1, list.size());
        try {
            assertEquals(Integer.valueOf(1), list.get(0));
        } catch (EmptyListException | OutOfBondsException e) {
            System.out.println("Error");
        }
    }

    @Test
    public void size() {
        MyList<Integer> list = new ListaEnlazadaimp();
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
        list.add(2);
        assertEquals(2, list.size());
        try {
            list.remove(1);
            assertEquals(1, list.size());
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        } catch (OutOfBondsException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public void contains() {
        MyList<Integer> list = new ListaEnlazadaimp();
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(12));


    }

}