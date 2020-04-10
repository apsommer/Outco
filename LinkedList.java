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

class LinkedList {

    public int length = 0;
    public ListNode head;
    public ListNode tail;

    // Time Complexity: O(1)
    // Auxiliary Space Complexity: O(1)
    public void append(int value){

        ListNode node = new ListNode(value); // create new node with passed value
        length ++;

        // catch empty list
        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        tail.next = node; // current tail.next becomes new node
        tail = node; // move tail
    }

    // Time Complexity: O(N) to search for desired position, O(1) to insert
    // Auxiliary Space Complexity: O(1)
    public void insert(int value, int index) {

        // catch bad index
        if (index > length || index < 0) return;

        // create the new node
        ListNode node = new ListNode(value);

        // empty list
        if (head == null) {
            head = node;
            tail = node;
            length ++;
            return;
        }

        // new node on head
        if (index == 0) {
            node.next = head;
            head = node;
            length ++;
            return;
        }

        // new node on tail
        if (index == length) {
            tail.next = node;
            tail = node;
            length ++;
            return;
        }

        // move to desired position, assume head index is 0
        ListNode current = head;
        for (int i = 0; i < index; i ++) {
            current = current.next;
        }

        // set the correct pointers
        ListNode temp = current.next; // save off node n+1
        current.next = node; // insert new node
        node.next = temp; // connect original n+1

        length ++;
    }

    // Time Complexity: O(N) to search for desired position, O(1) to delete
    // Auxiliary Space Complexity: O(1)
    public void delete(int index) {

        // catch bad index
        if (index > length || index < 0) return;

        // catch list has only one element
        if (length == 1) {
            head = null;
            tail = null;
            length --;
            return;
        }

        // catch delete head
        if (index == 0) {
            head = head.next;
            length --;
            return;
        }

        // move to desired position, assume head index is 0
        ListNode node = head;
        for (int i = 0; i < index; i ++) {
            node = node.next;
        }

        ListNode temp = node; // save off node at n-1
        node = node.next.next; // get reference to n+1
        temp.next = node; // next of n-1 becomes n+1
        length --;
    }

    // Time Complexity: O(N) worst case must access every node
    // Auxiliary Space Complexity: O(1) references change on each iteration of while loop, therefore constant space for all N
    public boolean contains(int value){

        // loop through entire llist starting at head
        ListNode node = head;
        while (node != null) {
            if (node.value == value) return true; // target found
            node = node.next;
        }
        return false; // target not found
    }
}
