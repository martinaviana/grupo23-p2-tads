package uy.edu.um.prog2.tad.hash;

import java.util.List;

public interface MyHash<Key, Value> {

    void put(Key k, Value v);

    Value get(Key k);

    int size();

    void remove(Key k);

    List<Key> keys();
}
