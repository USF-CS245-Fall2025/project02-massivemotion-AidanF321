public class DoublyLinkedList<T> implements List<T> {
    private class Node {
        T data;
        Node next;
        Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    Node head;
    Node tail;
    int size;

    /**
     * Constructor for DoublyLinkedList
     */
    public DoublyLinkedList() {
        this.head = null;
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
     * Private helper method to get the Node at a specific index.
     * Optimizes by searching from the head or tail, whichever is closer.
     */
    private Node getNode(int index) {

        if (index < this.size / 2) {
            // Index is in the first half: search from head
            Node current = this.head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            // Index is in the second half: search from tail (backward)
            Node current = this.tail;
            for (int i = this.size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
    }

    /**
     * @param index The index of the element to retrieve
     * @return The element at the specified index
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        // Just return the data from the helper method
        return getNode(index).data;
    }

    /**
     * @param element The element to add
     * @return true after adding the element
     */
    @Override
    public boolean add(T element) {
        Node newNode = new Node(element);

        if (this.size == 0) {// empty list
            this.head = newNode;
            this.tail = newNode;
        } else { // not empty

            // Link the old tail to the new node
            this.tail.next = newNode;

            // Link the new node back to the old tail
            newNode.prev = this.tail;

            // Update the tail to be the new node
            this.tail = newNode;
        }

        this.size++;
        return true;
    }

    /**
     * @param index The index at which to insert the element
     * @param element The element to insert
     */
    @Override
    public void add(int index, T element) {
        // index (0 to the size)
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index is invalid");
        }

        if (index == this.size) {
            //add to end (same as before)
            this.add(element);
            return;
        }

        Node newNode = new Node(element);

        if (index == 0) {
            // add to front
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        } else {
            // the insertion portion
            
            // Find node at the index we want to insert
            Node nodeAtIndex = getNode(index);
            // Get the node before it
            Node prevNode = nodeAtIndex.prev;

            // Link the node like a sandwhich
            newNode.next = nodeAtIndex;
            newNode.prev = prevNode;

            // put the sandwhich together
            prevNode.next = newNode;
            nodeAtIndex.prev = newNode;
        }

        this.size++;
    }
    
    @Override
    public T remove(int index) {
        // Must be a valid, existing element
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        // Get the node we want to remove
        Node nodeToRemove = getNode(index);
        T dataToReturn = nodeToRemove.data;

        if (this.size == 1) {
            // Case 1: Removing the ONLY element
            this.head = null;
            this.tail = null;
        } else if (index == 0) {
            // Case 2: Removing the head (but not the only element)
            this.head = this.head.next;
            this.head.prev = null; // Clear the new head's prev link
        } else if (index == this.size - 1) {
            // Case 3: Removing the tail (but not the only element)
            this.tail = this.tail.prev;
            this.tail.next = null; // Clear the new tail's next link
        } else {
            // Case 4: Removing from the middle
            Node prevNode = nodeToRemove.prev;
            Node nextNode = nodeToRemove.next;
            
            // "Skip" the nodeToRemove
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        this.size--;
        return dataToReturn;
    }
}
