public class DummyHeadLinkedList<T> implements List<T> {

    private class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node dummyHead; 
    private int size;       // The count of real elements

   
    public DummyHeadLinkedList() {
        // Create the dummy node
        this.dummyHead = new Node(null); 
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
     * @param index The index at which to insert the element
     * @param element The element to insert
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index is invalid");
        }

        // Find node before insertion point
        Node prev = this.dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        // sandwhich the node in
        Node newNode = new Node(element);
        newNode.next = prev.next;
        prev.next = newNode;

        this.size++;
    }

    /**
     * @param element The element to add
     * @return true after adding the element
     */
    @Override
    public boolean add(T element) {
        // This is a simple (but O(n)) way to add to the end
        add(this.size, element);
        return true;
    }

    /**
     * @param index The index of the element to retrieve
     * @return The element at the specified index
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is invalid ");
        }

        // start at first real value
        Node current = this.dummyHead.next;
        
        //traverse to index
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    /**
     * @param index The index of the element to remove
     * @return The removed element
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is invalid ");
        }

        // find node before index
        Node prev = this.dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        // 2. Get the node to remove and its data
        Node nodeToRemove = prev.next;
        T dataToReturn = nodeToRemove.data;
        
        // 3. skip the node
        prev.next = nodeToRemove.next;

        this.size--;
        return dataToReturn;
    }
}