package uy.edu.um.prog2.tad.heap;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.tad.exceptions.EmptyHeapException;

import static org.junit.jupiter.api.Assertions.*;

class MyHeapImplTest {

    @Test
    void insert() {
//pruebo heap max
         MyHeap<Integer, String> heap = new MyHeapImpl<>(9);
        heap.insert(5, "5");
        heap.insert(4, "4");
        heap.insert(3, "3");
        heap.insert(10, "10");
        heap.insert(8, "8");
        heap.insert(1, "1");
        try {
            assertEquals("10", heap.delete());
            assertEquals("8", heap.delete());
            assertEquals("5", heap.delete());
            assertEquals("4", heap.delete());
            assertEquals("3", heap.delete());
            assertEquals("1", heap.delete());
            assertThrows(EmptyHeapException.class, () -> heap.delete());
        } catch (EmptyHeapException e) {
            throw new RuntimeException(e);
        }
        // heap min
        MyHeap<Integer, String> heap2 = new MyHeapImpl<>(9);
        heap2.isminHeap(true);
        heap2.insert(5, "5");
        heap2.insert(1, "1");
        heap2.insert(2, "2");
        try {
            assertEquals("1", heap2.delete());
            assertEquals("2", heap2.delete());
            assertEquals("5", heap2.delete());
            assertThrows(EmptyHeapException.class, () -> heap2.delete());

        } catch (EmptyHeapException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void delete() {
        MyHeap<Integer, String> heap = new MyHeapImpl<>(10);
        heap.insert(20, "20");
        heap.insert(10, "10");
        heap.insert(11, "11");
        heap.insert(17, "17");
        heap.insert(1, "1");
        try {
            assertEquals("20", heap.delete());
            assertEquals("17", heap.delete());
            assertEquals("11", heap.delete());
            assertEquals("10", heap.delete());
        } catch (EmptyHeapException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void size() {
    }
}