package com.sommerengineering.library;

class Main {

    public static void main(String[] args) {

        LinkedList list = new LinkedList();
        list.insert(42, 0);

        System.out.println(list.head.value);
        System.out.println(list.head.next);

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

    // Time Complexity: O(1)
    // Auxiliary Space Complexity: O(1)
    public void append(int value){

        // create new node and move tail pointer
        ListNode node = new ListNode(value);
        tail.next = node;
        tail = node;
        length ++;
    }

    // Time Complexity: O(N) to search for desired position, O(1) to insert
    // Auxiliary Space Complexity: O(1)
    public void insert(int value, int index){

        // create the new node
        ListNode newNode = new ListNode(value);

        length ++;

        // catch empty list
        if (head == null || tail == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        // move to desired position, assume head index is 0
        ListNode node = head;
        for (int i = 0; i < index - 1; i ++) {
            node = node.next;
        }

        // set the correct pointers
        ListNode temp = node.next; // save off node n+1
        node.next = newNode; // insert new node
        newNode.next = temp; // connect original n+1
    }

    // Time Complexity: O(N) to search for desired position, O(1) to delete
    // Auxiliary Space Complexity: O(1)
    public void delete(int index){

        // move to desired position, assume head index is 0
        ListNode node = head;
        for (int i = 0; i < index - 1; i ++) {
            node = node.next;
        }

        ListNode temp = node; // save off node at n-1
        node = node.next.next; // get reference to n+1
        temp.next = node; // next of n-1 becomes n+1

        length --;
    }

    // Time Complexity:
    // Auxiliary Space Complexity:
    public boolean contains(int value){

        // loop through entire llist starting at head
        ListNode node = head;
        while (node != null) {
            if (node.value == value) return true; // target found
            node = node.next;
        }
        return false;
    }
}
