package uy.edu.um.prog2.tad.binaryTree;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.tad.exceptions.KeyNotInTree;

import static org.junit.jupiter.api.Assertions.*;

class MyBinarySearchTreeImplTest {

    @Test
    void add() {
        MySearchBinaryTree<Integer, Object> tree = new MyBinarySearchTreeImpl<>();
        tree.add(5, '5');
        tree.add(4, '4');
        try {
            assertEquals('4',tree.find(4));
        } catch (KeyNotInTree e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void remove() {
        MySearchBinaryTree<Integer, Object> tree = new MyBinarySearchTreeImpl<>();
        tree.add(5, "5");
        tree.add(4, "4");
        tree.add(3, "3");
        tree.add(10, "10");
        tree.remove(10);
        assertThrows(KeyNotInTree.class, () -> tree.find(10));

    }

    @Test
    void find() {
        MySearchBinaryTree<Integer, Object> tree = new MyBinarySearchTreeImpl<>();
        tree.add(5, "5");
        tree.add(4, "4");
        tree.add(3, "3");
        tree.add(10, "10");
        tree.add(8, "8");
        tree.add(7, "7");
        tree.add(1, "1");
        tree.add(6, "6");
        tree.add(9, "9");
        tree.add(11, "11");
        tree.add(2, "2");
        try {
            assertEquals("11", tree.find(11));
            assertEquals("5", tree.find(5));
            assertThrows(KeyNotInTree.class, () -> tree.find(15));
        } catch (KeyNotInTree e) {
            throw new RuntimeException(e);
        }


    }
}