package main;
import deque.*;

public class App {
    public static void main(String[] args) throws Exception {
        Deque newDeque = new Deque();
        newDeque.insertTail(0);
        newDeque.insertTail(6);
        newDeque.insertTail(3);
        newDeque.insertHead(0);
        newDeque.insertTail(0);
        newDeque.insertHead(8);
        newDeque.insertHead(2);
        newDeque.insertHead(0);
        newDeque.insertTail(4);
        newDeque.insertHead(8);
        newDeque.insertHead(2);
        
        System.out.println(newDeque.toString());
        System.out.println(newDeque.sort());
    }
}
