package uy.edu.um.prog2.tad.stack;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.tad.exceptions.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void pop() {
        MyStack<Integer> stack= new Stack<>();
        try {
            stack.pop();
        }
        catch (EmptyStackException e){
            System.out.println("No se puede hacer pop de una pila vacía");
        }
        stack.push(0);
        System.out.println(stack.size() + " tiene un elemento");

        try {
            stack.pop();
            assertEquals(0, stack.size());
        } catch (EmptyStackException e) {
            throw new RuntimeException(e);
        }
        System.out.println(stack.size() + " le borré el elemento, debe ser 0");
        assertEquals(0, stack.size());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
        try {
            stack.pop();
            assertEquals(2, stack.size());
        } catch (EmptyStackException e) {
            throw new RuntimeException(e);
        }
        System.out.println(stack.size() + " debe ser 2");
    }

    @Test
    void top() {
        Stack<Integer> stack= new Stack<Integer>();
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            assert true;
        }
        stack.push(1);
        try {

            assertEquals(new Integer(1), stack.pop());
        } catch (EmptyStackException e) {
            System.out.println("error");
        }

    }

    @Test
    void push() {
        Stack<Integer> stack= new Stack<Integer>();
        stack.push(0);
        assertTrue(stack.size()==1);
        stack.push(1);
        assertTrue(stack.size()==2);
    }



}