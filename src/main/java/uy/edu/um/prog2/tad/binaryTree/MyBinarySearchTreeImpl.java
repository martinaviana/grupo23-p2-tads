package uy.edu.um.prog2.tad.binaryTree;

import uy.edu.um.prog2.tad.exceptions.KeyNotInTree;
import uy.edu.um.prog2.tad.exceptions.KeyNotInTree;
public class MyBinarySearchTreeImpl<K extends Comparable<K>, V> implements MySearchBinaryTree<K, V> {

    private Node<K, V> root;

    @Override
    public void add(K key, V value) {
        Node<K, V> elementoAInsertar = new Node<>(key, value);
        if (root == null) {
            root = elementoAInsertar;
        } else {
            root.add(key, value);
        }
    }

    private Node<K, V> delete(K P, Node<K, V> root) throws KeyNotInTree {
        Node<K, V> result = root;
        if (find(P) == null) {
            throw new KeyNotInTree("Provided key was not found");
        }
        if (root.getKey().compareTo(P) == 0) {
            if (root.getLeft() == null && root.getRight() == null) {
                result = null;
            } else if (root.getLeft() == null) {
                result = root.getRight();
            } else if (root.getRight() == null) {
                result = root.getLeft();
            } else {
                Node<K, V>  min = getMax(root.getLeft());
                root.setKey(min.getKey());
                root.setValue(min.getValue());
                root.setLeft(delete(min.getKey(), root.getLeft()));
            }
        } else if (P.compareTo(root.getKey()) > 0) {
            root.setRight(delete(P, root.getRight()));
        } else {
            root.setLeft(delete(P, root.getLeft()));
        }
        return result;
    }

    private Node<K, V> getMax(Node<K, V> root) {
        Node<K, V> Max = null;
        if (root != null) {
            if (root.getRight() == null) {
                Max = root;
            } else {
                Max = getMax(root.getRight());
            }
        }
            return Max;
    }

    @Override
    public void remove(K key) {
        if (root != null) {
            root = root.delete(key);
        }
    }

    private V findAux(K key, Node<K, V> root){
        V value = null;
        if (root != null) {
            int ValueAux = key.compareTo(root.getKey());
            if (ValueAux == 0) {
                value = root.getValue();
            } else if (ValueAux > 0 && root.getRight() != null) {
                value = findAux(key, root.getRight());
            } else if(root.getLeft() != null){
                value = findAux(key, root.getLeft());
            }
        }
        return value;
    }


    @Override
    public V find(K key) throws KeyNotInTree {

        if(root== null){
            throw new KeyNotInTree("Tree is empty.");
        }
        else{
            V value= findAux(key, root);
            Node<K, V> node =new Node<>(key,value);
            if (value!=null){
                return findAux(key, root);
            }
            else{
                throw new KeyNotInTree("Provided key was not found.");

            }
        }

    }
}
