package uy.edu.um.prog2.tad.hash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.tad.hash.ClosedHashImpl;
import uy.edu.um.prog2.tad.hash.MyHash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class MyHashImplTest {

    @Test
    void add() {
        MyHash<Integer,Integer> hash = new ClosedHashImpl<>(11,true);
        hash.put(1, 1111);
        hash.put(2, 549);
        hash.put(3, 158);


        assertEquals(549, hash.get(2));
    }

    @Test
    void remove() {
        MyHash<Integer, Integer> hash = new ClosedHashImpl<>(11, true);

        hash.put(1,1111);
        hash.put(2,549);
        hash.put(3,158);
        hash.put(4,5678);

        hash.remove(3);

        assertNull(hash.get(3));
    }


    @Test
    void get() {
        MyHash<Integer,Integer> hash = new ClosedHashImpl<>(11,true);

        hash.put(1,1111);
        hash.put(2,549);
        hash.put(3,158);
        hash.put(4,5678);
        hash.put(3,510);
        hash.put(5, 1000);

        assertEquals(549,hash.get(2));
        assertEquals(1000, hash.get(5));
    }
}
