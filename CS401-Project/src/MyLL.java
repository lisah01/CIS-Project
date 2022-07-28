import java.util.Comparator;

public class MyLL<T> {
    protected Node front;
    protected Node tracker;
    protected int size = 0;

    // LLNode class
    public class Node {
        T info;
        Node link;

        // constructors
        Node() {
            this.info = null;
            link = null;
        }

        Node(T info) {
            this.info = info;
            link = null;
        }

        // getters and setters for Node class
        void setInfo(T info) {
            this.info = info;
        }

        T getInfo() {
            return info;
        }

        void setLink(Node link) {
            this.link = link;
        }

        Node getLink() {
            return link;
        }

    }

    // constructor for empty list
    public MyLL() {
        front = null;
    }

    // compare provider names
    public Comparator<Coupon> provComp() {
        return new Comparator<Coupon>() {
            public int compare(Coupon a, Coupon b) {
                return a.getProvider().compareToIgnoreCase(b.getProvider());
            }
        };
    }

    // compare product names
    public Comparator<Coupon> prodComp() {
        return new Comparator<Coupon>() {
            public int compare(Coupon a, Coupon b) {
                String productA = a.getProduct().getName();
                String productB = b.getProduct().getName();
                return productA.compareToIgnoreCase(productB);
            }
        };
    }

    // compare prices
    public Comparator<Coupon> priceComp() {
        return new Comparator<Coupon>() {
            public int compare(Coupon a, Coupon b) {
                // convert to discounted prices
                double priceA = a.getProduct().getPrice() * (1 - (a.getDiscount() / 100));
                double priceB = b.getProduct().getPrice() * (1 - (b.getDiscount() / 100));
                if (priceA < priceB)
                    return -1;
                else if (priceA > priceB)
                    return 1;
                else
                    return 0;
            }
        };
    }

    // compare discounts
    public Comparator<Coupon> disComp() {
        return new Comparator<Coupon>() {
            public int compare(Coupon a, Coupon b) {
                double discountA = a.getDiscount();
                double discountB = b.getDiscount();
                if (discountA < discountB)
                    return -1;
                else if (discountA > discountB)
                    return 1;
                else
                    return 0;
            }
        };
    }

    // compare expiration
    public Comparator<Coupon> expComp() {
        return new Comparator<Coupon>() {
            public int compare(Coupon a, Coupon b) {
                int expA = a.getExpiration();
                int expB = b.getExpiration();
                if (expA < expB)
                    return -1;
                else if (expA > expB)
                    return 1;
                else
                    return 0;
            }
        };
    }

    // compare status
    public Comparator<Coupon> statusComp() {
        return new Comparator<Coupon>() {
            public int compare(Coupon a, Coupon b) {
                boolean statusA = a.getStatus();
                boolean statusB = b.getStatus();
                // if a and b are both false (unused) or a is false and b is true
                // return -1 to indicate a should be before b
                if ((!statusA && !statusB) || (!statusA && statusB))
                    return -1;
                // if a is redeemed and b is unused, a is "greater"
                else if (statusA && !statusB)
                    return 1;
                // else a and b are both redeemed and therefore equal
                else
                    return 0;
            }
        };
    }

    // returns size of linkedlist
    public int getSize() {
        return this.size;
    }

    /*
     * method to add an item to the linkedlist by creating a new node
     * creates an unsorted linkedlist
     */
    public void add(T info) {
        size++;
        Node newNode = new Node(info);
        if (front == null) {
            front = newNode;
        } else {
            // traverse to the end of the list and add the new node
            tracker = front;
            while (tracker.getLink() != null) {
                tracker = tracker.getLink();
            }
            tracker.setLink(newNode);
        }
    }

    // remove the front node and reset size, clearing the list
    public void clear() {
        front = null;
        size = 0;
    }

    // method to search list for key item and return index
    public int contains(T key) {
        int index = 0;
        // convert key and front.getInfo() to strings and compare
        // if they are the same, print where it is found
        if (front.getInfo().toString().compareTo(key.toString()) == 0) {
            System.out.println(key + " found at index " + index);
        } else {
            // traverse towards the end and check for key
            tracker = front;
            while (tracker != null) {
                // increase counter for each traversal
                index++;
                // if key is found, indicate and break from loop
                if ((key.toString().compareTo(tracker.getInfo().toString())) == 0) {
                    // first position is "index 1"
                    System.out.println(key + " found at index " + index);
                    break;
                } else
                    tracker = tracker.getLink();
            }
            // if the end is reached, the item is not in the list
            if (tracker == null) {
                System.out.println(key + " not found");
            }
        }
        return index;
    }

    // returns element at given index
    public T getElement(int index) {
        if (index == 0) {
            return front.getInfo();
        } else {
            tracker = front;
            int i = 0;
            while (i < index && tracker != null) {
                tracker = tracker.getLink();
                i++;
            }
            if (tracker == null)
                return null;
            else
                return tracker.getInfo();
        }
    }

    // method to add all elements from arraylist to linked list and returns the list
    public MyLL<T> addAll(MyAL<T> arr) {
        MyLL<T> list = new MyLL<>();
        for (int i = 0; i < arr.size(); i++) {
            list.add(arr.getElement(i));
        }
        return list;
    }

    // method to traverse and print the linkedlist
    public void printLL() {
        if (front == null) {
            System.out.println("Database is empty");
        } else {
            // traverse towards the end and print each item
            tracker = front;
            while (tracker != null) {
                System.out.println(tracker.getInfo());
                tracker = tracker.getLink();
            }
        }
    }

    // convert linkedlist to array
    protected Coupon[] toArray(MyLL<Coupon> list) {
        Coupon[] values = new Coupon[getSize()];
        for (int i = 0; i < values.length; i++) {
            values[i] = list.getElement(i);
        }
        return values;
    }

    // merge method: takes an unsorted list and comparator which will come from user input
    public void merge(Coupon values[], int left, int mid, int right, Comparator<Coupon> cmptr) {
        // find the size of left and right subarrays
        int leftSize = mid - left + 1;
        int rightSize = right - mid;
        // aux arrays
        Coupon[] temp1 = new Coupon[leftSize];
        Coupon[] temp2 = new Coupon[rightSize];

        for (int i = 0; i < leftSize; i++)
            temp1[i] = values[left + i];
        for (int i = 0; i < rightSize; i++)
            temp2[i] = values[mid + 1 + i];

        int i = 0;
        int j = 0;

        int index = left;
        // merge sub arrays
        while (i < leftSize && j < rightSize) {
            if (cmptr.compare(temp1[i], temp2[j]) < 0) {
                values[index] = temp1[i];
                i++;
            } else {
                values[index] = temp2[j];
                j++;
            }
            index++;
        }
        // copy the rest of temp1 to original array
        while (i < leftSize) {
            values[index] = temp1[i];
            i++;
            index++;
        }
        // copy the rest of temp2
        while (j < rightSize) {
            values[index] = temp2[j];
            j++;
            index++;
        }
        // copy sorted array to a new list
        MyLL<Coupon> newList = new MyLL<>();
        for (int x = 0; x < values.length; x++) {
            newList.add(values[x]);
        }
    }

    // recursive mergesort
    public Coupon[] mergeSort(Coupon[] values, int first, int last, Comparator<Coupon> cmptr) {
        if (first < last) {
            int mid = (first + last) / 2;
            mergeSort(values, first, mid, cmptr);
            mergeSort(values, mid + 1, last, cmptr);
            merge(values, first, mid, last, cmptr);
        }
//        System.out.println("----------------------------------------");
//        printLL(values);
        return values;
    }

    protected void linearSearch(MyLL<Coupon> coupons, String key) {
        int count = 0;
        Coupon temp = coupons.getElement(count);
        boolean found = false;

        while (count < coupons.getSize()) {
            // if temp == key, found = true
            if (temp.getProduct().getName().compareToIgnoreCase(key) == 0) {
                found = true;
                System.out.printf("Coupon found after %d search(es) by linear search: \n", count+1);
                System.out.println("        PROVIDER                PRODUCT             PRICE     DISCOUNT    EXP. PERIOD    STATUS   | DISCOUNTED PRICE");
                System.out.println("------------------------------------------------------------------------------------------------------------------------");
                System.out.println(temp);
                System.out.println("------------------------------------------------------------------------------------------------------------------------");
                break;
            }
            count++;
            // set temp to next coupon
            temp = coupons.getElement(count);
        }
        if (!found)
            System.out.printf("Coupon not found after %d searches by linear search\n", count);
    }

    // method to do binary searching based on Coupon attribute
    protected void binarySearch(MyLL<Coupon> coupons, String key) {
        int left = 0;
        int right = coupons.getSize() - 1;
        int intVal;     // for storing comparison of strings
        int count = 0;
        boolean found = false;
        Coupon temp;
        String tempStr;
        while (left <= right) {
            int mid = (left + right) / 2;
            temp = coupons.getElement(mid);
            tempStr = temp.getProduct().getName();
            // store comparison to intVal
            intVal = tempStr.compareToIgnoreCase(key);

            // check if middle is key
            if (intVal == 0) {
                found = true;
                System.out.printf("Coupon found after %d search(es) by binary search: \n", count + 1);
                System.out.println("        PROVIDER                PRODUCT             PRICE     DISCOUNT    EXP. PERIOD    STATUS   | DISCOUNTED PRICE");
                System.out.println("------------------------------------------------------------------------------------------------------------------------");
                System.out.println(temp);
                System.out.println("------------------------------------------------------------------------------------------------------------------------");
                break;
            }
            // ignore right side of list
            else if (intVal > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            count++;
        }
        if (!found)
            System.out.printf("Coupon not found after %d searches by binary search\n", count);
    }
}
