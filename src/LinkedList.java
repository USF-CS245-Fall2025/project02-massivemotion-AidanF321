public class LinkedList<T> implements List<T> {

    private class Node {// private class because it is onlu used within this class
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head; // The first node in the list. Null if the list is empty.
    private Node tail;
    private int size; // How many nodes are in the list.

    public LinkedList() {
        this.head = null; // The list starts empty
        this.tail = null;
        this.size = 0;
    }

    /**
     * @return The number of elements in the list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @param element The element to add
     * @return true after adding the element
     */
    @Override
    public boolean add(T element) {
        Node newNode = new Node(element);

        if (this.size == 0) { // the list is empty
            this.head = newNode;
            this.tail = newNode;
        } else { // the list is not empty
            // link newNode to be tail
            this.tail.next = newNode;
            // update tail
            this.tail = newNode;
        }

        this.size++;
        return true;
    }
    @Override
    public void add(int index, T element) {
        // if index valid
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index is valid" + this.size);
        }  
        //best case
        if (index == this.size) {
            this.add(element); // call other add method
            return; //break since task is complete
        }

        // if reached this point, inserting a node
        Node newNode = new Node(element);

        // add in beginning 
        if (index == 0) {
            newNode.next = this.head; // New node points to the old head
            this.head = newNode;     // The new node is now the head
        } else {
            //add to middle
            
            // Find the node before insertion point
            Node prev = this.head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            // Stitch the new node into the list:
            // 1. Link new node to the rest of the list
            newNode.next = prev.next;
            // 2. Link the previous part of the list to the new node
            prev.next = newNode;
        }

        // 5. Increment the size (only if we didn't call the other add method)
        this.size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        T dataToReturn;

        if (index == 0) {
            // Case 1: Remove head
            dataToReturn = this.head.data;
            this.head = this.head.next; // Move head pointer

            if (this.size == 1) {
                // If the list is now empty, update tail as well.
                this.tail = null;
            }
        } else {
            // Case 2: Removing from the middle or end.
            // find node right before the index node(node being removed)
            Node prev = this.head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            Node nodeToRemove = prev.next;
            dataToReturn = nodeToRemove.data;

            // "Skip" the node to remove
            prev.next = nodeToRemove.next;

            if (index == this.size - 1) {
                // if removed the tail, update the tail pointer
                this.tail = prev;
            }
        }

        this.size--;
        return dataToReturn;
    }

    @Override
    public T get(int index) {
        // if the index is valid
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        // traverse the list from the head
        Node current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next; // move to the next node
        }

        return current.data;
    }

}
