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
     * Adds an element at end of list
     * @param element The element to add
     * @return true after adding the element
     */
    @Override
    public boolean add(T element) {
        Node newNode = new Node(element);

        if (this.size == 0) { 
            this.head = newNode;
            this.tail = newNode;
        } else { 
            this.tail.next = newNode;
            this.tail = newNode;
        }

        this.size++;
        return true;
    }

    /**
     * Adds an element at a specific index
     * @param index The index at which to insert the element
     * @param element The element to insert
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index is valid" + this.size);
        }  
        if (index == this.size) {
            this.add(element); 
            return; 
        }

        // if reached this point, inserting a node
        Node newNode = new Node(element);


        if (index == 0) {
            newNode.next = this.head;
            this.head = newNode;
        } else {
            
            // Find the node before insertion point
            Node prev = this.head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            newNode.next = prev.next;
            prev.next = newNode;
        }

        this.size++;
    }

    /**
     * @param index The index of the element to remove
     * @return The removed element
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        T dataToReturn;

        if (index == 0) {
            dataToReturn = this.head.data;
            this.head = this.head.next; 

            if (this.size == 1) {
                this.tail = null;
            }
        } else {
            Node prev = this.head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            Node nodeToRemove = prev.next;
            dataToReturn = nodeToRemove.data;

            prev.next = nodeToRemove.next;

            if (index == this.size - 1) {
                this.tail = prev;
            }
        }

        this.size--;
        return dataToReturn;
    }

    /**
     * @param index The index of the element to retrieve
     * @return The element at the specified index
     */
    @Override
    public T get(int index) {
        // if the index is valid
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        Node current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next; 
        }

        return current.data;
    }

}
