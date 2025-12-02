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
    private int size;       

   
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
     * adds an element at a specific index
     * @param index The index at which to insert the element
     * @param element The element to insert
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index is invalid");
        }
        Node prev = this.dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node newNode = new Node(element);
        newNode.next = prev.next;
        prev.next = newNode;

        this.size++;
    }

    /**
     * adds an element to the end of the list
     * @param element The element to add
     * @return true after adding the element
     */
    @Override
    public boolean add(T element) {
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
        Node current = this.dummyHead.next;
        
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    /**
     * remove element at a specific index
     * @param index The index of the element to remove
     * @return The removed element
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is invalid ");
        }

        Node prev = this.dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node nodeToRemove = prev.next;
        T dataToReturn = nodeToRemove.data;
        
        prev.next = nodeToRemove.next;

        this.size--;
        return dataToReturn;
    }
}