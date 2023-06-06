package uy.edu.um.prog2.tad.heap;

public class MyHeapImpl<T extends Comparable<T>> implements MyHeap<T> {

    private T[] values;
    private int lastValuePosition;
    private boolean isHeapMax;
    private boolean isHeapMin;

    public MyHeapImpl(T[] values, boolean heapMax, boolean heapMin) {
        this.values = values;
        this.lastValuePosition = 0;
        this.isHeapMax = heapMax;
        this.isHeapMin = heapMin;
    }


    private T getFather (int childPos){
        return values[(childPos-1)/2];
    }

    private int getFatherPosition (int childPos) {
        return (childPos-1)/2;
    }

    private T getLeftChild (int fatherPos){
        return values[2*fatherPos + 1];
    }

    private int getLeftChildPosition (int fatherPos){
        return 2*fatherPos + 1;
    }

    private T getRightChild (int fatherPos){
        return values[2*fatherPos + 2];
    }

    private int getRightChildPosition (int fatherPos){
        return 2*fatherPos + 2;
    }


    @Override
    public void insert(T value) {
        this.values[lastValuePosition] = value;
        int valuePos = lastValuePosition;
        lastValuePosition++;
        if (isHeapMax == true) {
            while (valuePos != 0 && value.compareTo(getFather(valuePos))>0){
                T change = getFather(valuePos);
                this.values[getFatherPosition(valuePos)]=value;
                this.values[valuePos]=change;
                valuePos = getFatherPosition(valuePos);
            }
        }
        if (isHeapMin==true) {
            while (valuePos!=0 && value.compareTo(getFather(valuePos))<0){
                T change = getFather(valuePos);
                this.values[getFatherPosition(valuePos)]=value;
                this.values[valuePos]=change;
                valuePos = getFatherPosition(valuePos);
            }
        }
    }

    @Override
    public T deleteAndReturn() {
        T nodoAEliminar = values[0];
        T nodo = values[lastValuePosition-1];
        values[0]=nodo;
        int valuePos = 0;
        lastValuePosition--;
        if(isHeapMax==true){
            while (getLeftChild(valuePos)!=null && getRightChild(valuePos)!=null){
                T leftChild = getLeftChild(valuePos);
                int leftChildPosition = getLeftChildPosition(valuePos);
                T rightChild = getRightChild(valuePos);
                int rightChildPosition = getRightChildPosition(valuePos);
                if (nodo.compareTo(leftChild)>0 && nodo.compareTo(rightChild)>0){
                    break;
                }
                else if (leftChild.compareTo(rightChild)>0) {
                    values[valuePos]=leftChild;
                    values[leftChildPosition]=nodo;
                    valuePos=leftChildPosition;
                }
                else if (leftChild.compareTo(rightChild)<0) {
                    values[valuePos]=rightChild;
                    values[rightChildPosition]=nodo;
                    valuePos=rightChildPosition;
                }
            }
        }
        else {
            while (getLeftChild(valuePos)!=null && getRightChild(valuePos)!=null){
                T leftChild = getLeftChild(valuePos);
                int leftChildPosition = getLeftChildPosition(valuePos);
                T rightChild = getRightChild(valuePos);
                int rightChildPosition = getRightChildPosition(valuePos);
                if (nodo.compareTo(leftChild)<0 && nodo.compareTo(rightChild)<0){
                    break;
                }
                else if (leftChild.compareTo(rightChild)<0) {
                    values[valuePos]=leftChild;
                    values[leftChildPosition]=nodo;
                    valuePos=leftChildPosition;
                }
                else if (leftChild.compareTo(rightChild)>0) {
                    values[valuePos]=rightChild;
                    values[rightChildPosition]=nodo;
                    valuePos=rightChildPosition;
                }
            }
        }
        return nodoAEliminar;
    }


    @Override
    public int size() {
        return lastValuePosition;
    }
}
