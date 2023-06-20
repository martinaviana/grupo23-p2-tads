package uy.edu.um.prog2.tad.binaryTree;

import uy.edu.um.prog2.tad.exceptions.KeyNotInTree;

public interface MySearchBinaryTree<K extends Comparable<K>, V> {

    void add(K key, V value);

    void remove(K key);

    V find(K key) throws KeyNotInTree;

    void visit(MySearchBinaryTreeVisitor<V> visitor);
}
