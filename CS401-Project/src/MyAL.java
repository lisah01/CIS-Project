public class MyAL<T> {
    protected T[] elements;
    protected int origCap = 30;
    protected int top = -1;

    // constructor for default size of 30
    public MyAL(){
        elements = (T[]) new Object[origCap];
    }

    // constructor for user defined size
    public MyAL(int size){
        origCap = size;
        elements = (T[]) new Object[size];
    }

    // method that returns if array is full
    public boolean isFull(){
        return (top == origCap);
    }

    // method that returns if the array is empty
    public boolean isEmpty(){
        return (top == -1);
    }

    // add method - updates top and doubles the size if array is full
    public void add(T item){
        top++;
        if (top == origCap) {
            this.doubleSize();
        }
        elements[top] = item;
//        System.out.println(item + " added to ArrayList");
    }

    // remove method
    public T remove(){
        if (!isEmpty()){
            T toReturn = elements[top];
            elements[top] = null;
            top--;
//            System.out.println(toReturn + " removed from ArrayList");
            return toReturn;
        }
        else{
            System.out.println("ArrayList is empty");
            return null;
        }
    }

    // method to double the size of the array
    public void doubleSize() {
        origCap = origCap*2;
        T[] newElements = (T[]) new Object[origCap];
        for (int i = 0; i < elements.length; i++){
            newElements[i] = elements[i];
        }
        // update elements
        elements = newElements;
    }

    // method to return element at some index
    public T getElement(int i){
        if (i < 0){
            System.out.println("Invalid index");
            return null;
        }
        else {
            return elements[i];
        }
    }

    // returns number of items in arraylist
    public int size(){
        return top+1;
    }

    // method that takes an array and returns CS401ArrayImpl
    public MyAL<T> addAll_AL(T[] arr){
        MyAL<T> array = new MyAL<>();
        for (int i = 0; i < arr.length; i++){
            array.add(arr[i]);
        }
        return array;
    }

    // prints the arraylist
    public void printAL(){
        System.out.println("-----Items in this Array List-----");
        for (int i = 0; i < this.size(); i++) {
            if (this.getElement(i) != null) {
                System.out.println(this.getElement(i));
            }
        }
    }
}