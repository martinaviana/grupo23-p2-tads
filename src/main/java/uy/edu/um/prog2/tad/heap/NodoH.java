package uy.edu.um.prog2.tad.heap;

public class NodoH <K extends Comparable<K>, T>{

        private K key;
        private T value;

        public NodoH(K key, T value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

    }

