package uy.edu.um.prog2.tad.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClosedHashImpl<Key, Value> implements MyHash<Key, Value> {

    private static int LINEAL_COLLISION_FUNCTION = 1;

    private HashEntrada<Key, Value>[] TablaHash;

    private int size;

    private boolean linear;

    private int defaultCollisionFunction = ClosedHashImpl.LINEAL_COLLISION_FUNCTION;

    public ClosedHashImpl(int size, boolean linear) {
        this.TablaHash = (HashEntrada<Key, Value>[]) new HashEntrada[getNextPrimeNumber(size - 1)];
        this.linear = linear;
        this.size = 0;
    }

    @Override
    public void put(Key k, Value v) {
        int attempt = 0;
        int pos = internalHashcodeWithCollision(k, attempt);
        HashEntrada<Key, Value> valueToEntrada = new HashEntrada<>(k, v);
        while (TablaHash[pos] != null &&
                !TablaHash[pos].isRemoved() &&
                !TablaHash[pos].getKey().equals(k) &&
                !(attempt > TablaHash.length)) {
            attempt++;
            pos = internalHashcodeWithCollision(k, attempt);
        }
        if (attempt > TablaHash.length) {
            throw new RuntimeException("No se encontro lugar disponible");
        }
        if (TablaHash[pos] == null || TablaHash[pos].isRemoved()) {
            TablaHash[pos] = valueToEntrada;
        } else {
            TablaHash[pos].setValue(v);
        }
        size++;
    }

    @Override
    public Value get(Key k) {
        int attempt = 0;
        int pos = internalHashcodeWithCollision(k, attempt);
        Value valueToReturn = null;

        while (TablaHash[pos] != null &&
                !TablaHash[pos].getKey().equals(k) &&
                !(attempt > TablaHash.length)) {
            attempt++;
            pos = internalHashcodeWithCollision(k, attempt);
        }
        if (TablaHash[pos] != null &&
                !(attempt > TablaHash.length) &&
                TablaHash[pos].getKey().equals(k) &&
                !TablaHash[pos].isRemoved()) {

            valueToReturn = TablaHash[pos].getValue();
        }
        return valueToReturn;
    }

    @Override
    public List<Key> keys() {
        //return Arrays.stream(TablaHash).filter(entry -> entry != null).map(HashEntrada::getKey).collect(Collectors.toList());
        List<Key> keys = new ArrayList<>();
        for (HashEntrada<Key, Value> entrada : TablaHash) {
            if (entrada != null)
                keys.add(entrada.getKey());
        }
        return keys;
    }

    @Override
    public int size() {
        return this.size;
    }

    private int getPosition(Key key, int iterations){
        int position = 0;
        if (linear){
            position = (key.hashCode() % TablaHash.length + iterations) % TablaHash.length;
        }else{
            position = (key.hashCode() % TablaHash.length + (int) Math.pow(iterations,2)) % TablaHash.length;
        }
        return position;
    }


    @Override
    public void remove(Key k) {
        int iterations = 0;
        int position = getPosition(k, iterations);

        while (!(TablaHash[position] == null || TablaHash[position].getKey().equals(k) || iterations  > TablaHash.length || TablaHash[position].isRemoved())) {
            iterations++;
            position = getPosition(k, iterations);
        }

        if (iterations <= TablaHash.length && !(TablaHash[position] == null) && !(TablaHash[position].isRemoved())){
            TablaHash[position].setRemoved(true);
            this.size--;
        }
    }

    private int internalHashcodeWithCollision(Key k, int attempt) {
        return (k.hashCode() + collisionFunction(attempt)) % TablaHash.length;
    }

    private int collisionFunction(int i) {
        int value = 0;
        if (defaultCollisionFunction == LINEAL_COLLISION_FUNCTION) {
            value = i;
        }
        return value;
    }

    private int getNextPrimeNumber(int number){

        int primoARetornar = number + 1;
        while(isNotPrime(primoARetornar)){
            primoARetornar++;
        }
        return primoARetornar;

    }

    private boolean isNotPrime(int number){

        boolean esPrimo = false;
        for (int i=2; i<number-1; i++){
            if (number % i == 0){
                esPrimo = true;
                break;
            }
        }
        return esPrimo;
    }
}
