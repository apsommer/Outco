package com.sommerengineering.library.linked_list;

/* Implement a Queue class using two stacks. The Queue should have the following methods:

enqueue: add an item to the end of the collection
dequeue: remove an item from the beginning of the collection */

// 1 > 2 > 3
// enqueue 4 ... 1 > 2 > 3 > 4
// dequeue ... 2 > 3 > 4

// 4   1
// 3   2
// 2   3
// 1   4
// IN  OUT

// 2   1
// 1   2
// IN  OUT

import java.util.Stack;

class Main3 {

    public static void main(String[] args) {

        Queue q = new Queue();

        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}

public class Queue {

    private Stack<Integer> in = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    // simply push to the IN stack
    void enqueue(int a) {
        in.push(a);
    }

    int dequeue() {

        // create an OUT stack in reverse order of the IN stack
        while (!in.isEmpty()) {
            out.push(in.pop());
        }

        // save off this top element from OUT stack (was first element ofIN stack)
        int temp = (int) out.pop();

        // rebuild the IN stack in preparation for the next enqueue/dequeue
        while (!out.isEmpty()) {
            in.push(out.pop());
        }

        return temp;
    }
}







