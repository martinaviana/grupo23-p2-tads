package uy.edu.um.prog2.tad.exceptions;

public class EmptyStackException extends Exception {
    public EmptyStackException(String message) {
        System.out.println(message);
    }
}
