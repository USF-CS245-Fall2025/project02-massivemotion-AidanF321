public class ArrayList<T> implements List<T> {

    private Object array[];
    private int size;
    private int capacity = 10;// start with 10

    /**
     * Constructor for ArrayList
     */
    public ArrayList() {
        this.array = new Object[capacity];
        this.size = 0;
    }

    /**
     * Checks if the internal array is full.
     * @return true if the internal array is full, false otherwise
     */
    private boolean isFull() {
        if (this.size() == capacity) {
            return true;
        }
        return false;
    }

    /**
     * Resizes the internal array to double its current capacity.
     * @return void
     */
    private void reSize() {
        capacity *= 2;
        Object[] tempArr = new Object[capacity];

        for (int i = 0; i < this.size(); i++) {
            tempArr[i] = array[i];
        }
        array = tempArr; 
    }

    /**
     * Inserts an element at the specified index, shifting elements as necessary.
     * @param index The index at which to insert the element
     * @param element The element to insert
     */
    private void insert(int index, T element) {

        for (int i = this.size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = element;
        size++;
    }

    /**
     * Adds an element at the specified index.
     * @param index The index at which to insert the element
     * @param element The element to insert
     */
    @Override
    public void add(int index, T element) { 
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index is Invalid");
        }

        if (this.isFull()) {
            this.reSize();
            insert(index, element);
        } else {
            insert(index, element);
        }

    }

    /**
     * @param element The element to add
     * @return true after adding the element
     */
    @Override
    public boolean add(T element) {
        //call helper methods
        if (this.isFull()) {
            this.reSize();
            array[size++] = element;
        } else {
            array[size++] = element;
        }
        return true;
    }

    /**
     * @param index The index of the element to retrieve
     * @return The element at the specified index
     */
    @Override
    public T get(int index) {
        if (index >= 0 && index < this.size()) {
            T element = (T) array[index];
            return element;
        } else {
            // Throw exception
            throw new IndexOutOfBoundsException("Index is invalid input");
        }
    }

    /**
     * @param index The index of the element to remove
     * @return The removed element
     */
    @Override
    public T remove(int index) {
        //if index valid
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        T elementToRemove = (T) array[index];

        for (int i = index; i < this.size - 1; i++) {
            array[i] = array[i + 1];
        }

        this.size--;
        array[this.size] = null;
        return elementToRemove;
    }

    /**
     * @return The number of elements in the list
     */
    public int size() {
        return size;
    }
}
