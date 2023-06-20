package uy.edu.um.prog2.tad.heap;


import uy.edu.um.prog2.tad.exceptions.EmptyHeapException;

public class MyHeapImpl<K extends  Comparable<K>, T> implements MyHeap<K, T>{

    private NodoH<K, T> [] array;

    public boolean isHeapMin;
    private int size;
    public MyHeapImpl(int lenght){
        this.isHeapMin = false;
        this.size = 0;
        this.array = (NodoH<K, T> []) new NodoH[lenght];
    }
    @Override
    public int size(){
        return this.size;
    }
    public void isHeapMin(boolean isHeapMin) {
        this.isHeapMin = isHeapMin;
    }
    protected void isminHeap(boolean isHeapMin){
        this.isHeapMin = isHeapMin;
    }
    @Override
    public void insert(K key, T value) {
        NodoH<K, T> newNode = new NodoH<>(key, value);
        array[size] = newNode;
        swapUp(newNode, size);
        size ++;
        if (size == array.length){
            NodoH<K, T>[] newArray = (NodoH<K, T>[])  new NodoH[array.length*2];
            int i = 0;
            while (i<=size){
                newArray[i] = array[i];
                i++;
            }
            array = newArray;
        }

    }
    private void swapUp(NodoH<K, T> newNode, int position) {
        if (position == 0) {
            return;
        }
        int fatherPosition = getFatherPosition(position);
        NodoH<K, T> father = array[fatherPosition];
        if (newNode.getKey().compareTo(father.getKey()) * heapidentificador() > 0) {
            array[fatherPosition] = newNode;
            array[position] = father;
            swapUp(newNode, fatherPosition);
        }
    }
        private int heapidentificador(){
            if (isHeapMin){
                return -1;
            }
            return 1;
        }


    @Override
    public T delete() throws EmptyHeapException {
        if (size == 0){
            throw new EmptyHeapException("heap vacia");
        }
        else{

            T value = array[0].getValue();
            array[0] = array[size-1];
            array[size-1] = null;
            size--;
            swapDown(array[0], 0);
            return value;

        }


    }
    private void swapDown(NodoH<K, T> node, int position){
        int leftChildPosition = getLeftChildPosition(position);
        int rightChildPosition = getRightChildPosition(position);
        if (leftChildPosition<size){
            NodoH<K, T> leftChild = array[leftChildPosition];
            if (leftChildPosition == size-1){
                if(node.getKey().compareTo(leftChild.getKey())* heapidentificador() < 0) {
                    array[leftChildPosition] = node;
                    array[position] = leftChild;
                }
            }else{
                NodoH<K, T> rightChild = array[rightChildPosition];
                if(node.getKey().compareTo(leftChild.getKey())* heapidentificador() < 0 && node.getKey().compareTo(rightChild.getKey())* heapidentificador() < 0){
                    if(leftChild.getKey().compareTo(rightChild.getKey())* heapidentificador() > 0){
                        array[leftChildPosition] = node;
                        array[position] = leftChild;
                        swapDown(node, leftChildPosition);
                    }else{
                        array[rightChildPosition] = node;
                        array[position] = rightChild;
                        swapDown(node, rightChildPosition);
                    }
                }else if(node.getKey().compareTo(leftChild.getKey())* heapidentificador() < 0){
                    array[leftChildPosition] = node;
                    array[position] = leftChild;
                    swapDown(node, leftChildPosition);
                }else if (node.getKey().compareTo(rightChild.getKey())* heapidentificador() < 0){
                    array[rightChildPosition] = node;
                    array[position] = rightChild;
                    swapDown(node, rightChildPosition);
                }
            }
        }
    }

    public MyHeapImpl(int length, boolean isHeapMin) {
        this.array = (NodoH<K, T>[]) new NodoH[length];
        this.isHeapMin = isHeapMin;
    }
    private int getFatherPosition(int position){
        return (position-1)/2;
    }

    private int getLeftChildPosition(int position){
        return 2*position + 1;
    }

    private int getRightChildPosition(int position){
        return 2*position + 2;
    }
}
