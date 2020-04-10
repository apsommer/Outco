package com.sommerengineering.library;

class Main {

    public static void main(String[] args) {

        LinkedList linkedList= new LinkedList();
        linkedList.insert(5,0);
        linkedList.insert(10, 1);

        System.out.println(linkedList.head.value);
        System.out.println(linkedList.head.next.value);

    }
}

class ListNode {

    public int value;
    public ListNode next;

    public ListNode(int value){
        this.value = value;
    }
}

public class LinkedList {

    public int length = 0;
    public ListNode head;
    public ListNode tail;

    // Time Complexity: O(N) to search for desired position, O(1) to insert
    // Auxiliary Space Complexity: O(1)
    public void insert(int value, int index){

        // catch bad index
        if (index > length) return;

        // create the new node and increment length
        ListNode newNode = new ListNode(value);

        // catch empty list
        if (head == null) {
            head = newNode;
            tail = newNode;
            length ++;
            return;
        }

        // catch new node on head
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            length ++;
            return;
        }

        // catch new node on tail
        if (index == length) {
            tail.next = newNode;
            tail = newNode;
            length ++;
            return;
        }

        // move to desired position, assume head index is 0
        ListNode node = head;
        for (int i = 0; i < index; i ++) {
            node = node.next;
        }

        // set the correct pointers
        ListNode temp = node.next; // save off node n+1
        node.next = newNode; // insert new node
        newNode.next = temp; // connect original n+1
        length ++;
    }
}
