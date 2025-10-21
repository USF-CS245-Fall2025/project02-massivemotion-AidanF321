public class ArrayList<T> implements List<T> {

    private Object array[];
    private int size;
    private int capacity = 10;// start with 10

    public ArrayList() {
        this.array = new Object[capacity];
        this.size = 0;
    }

    // helper method to check if ArrayList is full and resizes
    private boolean isFull() {
        if (this.size() == capacity) {
            return true;
        }
        return false;
    }

    // helper method - double capacity
    private void reSize() {
        capacity *= 2;
        Object[] tempArr = new Object[capacity];

        for (int i = 0; i < this.size(); i++) {
            tempArr[i] = array[i];// copy all elements into temp array
        }
        array = tempArr; // point array to new array
    }

    // helper insert method
    private void insert(int index, T element) {

        // Shift elements to the right, starting from the end
        for (int i = this.size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = element;// insert element to array and increment size
        size++;
        // [elements before] [new element] [copied elements after]
    }

    @Override
    public void add(int index, T element) { // insert method
        if (index < 0 || index > this.size) {// check if valid index
            throw new IndexOutOfBoundsException("Index is Invalid");
        }

        if (this.isFull()) {// full list
            this.reSize();
            insert(index, element);// insert
        } else {// list with room to spare
            insert(index, element);//
        }

    }

    @Override
    public boolean add(T element) {// add to the end of list
        //call helper methods
        if (this.isFull()) {
            this.reSize();

            // add element to array and increment size
            array[size++] = element;
        } else {
            array[size++] = element;
        }
        return true;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < this.size()) {// if valid input
            T element = (T) array[index];
            return element;
        } else {
            // Throw exception
            throw new IndexOutOfBoundsException("Index is invalid input");
        }
    }

    @Override
    public T remove(int index) {
        //if index valid
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        // get element
        T elementToRemove = (T) array[index];

        // shift elements left
        for (int i = index; i < this.size - 1; i++) {
            array[i] = array[i + 1];
        }

        // decrement size
        this.size--;

        // clear old eleent
        array[this.size] = null;

        return elementToRemove;
    }

    public int size() {
        return size;
    }
}
