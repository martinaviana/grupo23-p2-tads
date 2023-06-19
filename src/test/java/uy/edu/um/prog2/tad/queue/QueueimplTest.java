package uy.edu.um.prog2.tad.queue;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.EmptyQueueException;
import uy.edu.um.prog2.tad.exceptions.OutOfBondsException;
import uy.edu.um.prog2.tad.stack.MyStack;
import uy.edu.um.prog2.tad.stack.Stack;

import static org.junit.jupiter.api.Assertions.*;

class QueueimplTest {

    @Test
    void enqueue() {
        MyQueue<Integer> queue= new Queueimpl<>();
        queue.enqueue(1);
        assertEquals(1, queue.size());
        queue.enqueue(2);
        assertEquals(2, queue.size());
        try {
            assertEquals(1,queue.front());
        } catch (OutOfBondsException e) {
            throw new RuntimeException(e);
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        } catch (EmptyQueueException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void dequeue() {
        MyQueue<Integer> queue= new Queueimpl<>();
        try {
            queue.dequeue();
        } catch (OutOfBondsException e) {
            throw new RuntimeException(e);
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        } catch (EmptyQueueException e) {
            assert true;
        }
        queue.enqueue(1);
        try {
            queue.dequeue();
        } catch (OutOfBondsException e) {
            throw new RuntimeException(e);
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        } catch (EmptyQueueException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0, queue.size());
    }

    @Test
    void front() {
        MyQueue<Integer> queue = new Queueimpl<>();
        try {
            queue.front();
        } catch (OutOfBondsException e) {
            throw new RuntimeException(e);
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        } catch (EmptyQueueException e) {
            assert true;

        }
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        try {
            assertEquals(1, queue.front());
        } catch (OutOfBondsException e) {
            throw new RuntimeException(e);
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        } catch (EmptyQueueException e) {
            throw new RuntimeException(e);
        }
    }

}