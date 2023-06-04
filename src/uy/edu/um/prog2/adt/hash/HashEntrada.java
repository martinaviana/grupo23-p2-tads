package uy.edu.um.prog2.adt.hash;

public class HashEntrada<K, V> {

    private K key;

    private V value;

    private boolean removed;

    public HashEntrada(K key, V value) {
        this.key = key;
        this.value = value;
        this.removed = false;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equalsToReturn = false;

        equalsToReturn = ((K) obj).equals(this.key);

        return equalsToReturn;
    }
}
