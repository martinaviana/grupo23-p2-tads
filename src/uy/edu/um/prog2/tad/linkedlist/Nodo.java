package uy.edu.um.prog2.tad.linkedlist;

import org.w3c.dom.Node;

public class Nodo<T> {
    //  defino los atributos
    private T value;
    private Nodo<T> nextNode;// enlace
    private Nodo<T> previousNode =null;
    // Constructor de la clase
    public Nodo(T value){
        this.value=value;
        nextNode=null;

    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Nodo<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Nodo<T> nextNode) {
        this.nextNode = nextNode;
    }
    public void setPreviousNode(Nodo<T> value){
        this.previousNode= value;
    }
    public Nodo<T> getPreviousNode(){
        return  this.previousNode;
    }
}
