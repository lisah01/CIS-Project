import java.util.*;
import java.io.*;

public class Menu {
    //    // arraylist to store file contents
//    MyAL<Coupon> arrList = new MyAL<>();
    // linkedlist
    MyLL<Coupon> coupons = new MyLL<>();

    // checks if the user input is an integer
    protected int isInt(String str) {
        if (str == null) {
            return -1;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    // checks if the user input is a fp
    protected double isFloat(String str) {
        if (str == null) {
            return -1;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    // display and prompt for menu option
    protected int menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nMenu Options\n-----------------------------------");
        System.out.println("(1) Purchase Coupon (Input data)");
        System.out.println("(2) Search product for coupon");
        System.out.println("(3) List coupons");
        System.out.println("(4) Clear database");
        System.out.println("(5) Quit\n-----------------------------------");
        int option;
        // loop menu until a valid option is chosen
        while (true) {
            System.out.println("Enter a menu option: ");
            String input = scan.nextLine();
            option = isInt(input);
            if (option < 1 || option > 5) {
                System.out.println("Enter a menu option between 1 and 5");
            } else {
                break;
            }
        }
        return option;
    }

    // read records from a file and create an unsorted linkedlist
    protected MyLL<Coupon> readfile(File file) throws Exception {
        System.out.println("File name: " + file.getName());
        Scanner scan = new Scanner(new FileReader(file));
        // array to hold attributes at each line
        String[] arr;
        int testInt;
        // trackers for line number in text file and coupons added
        int line = 0, count = 0;
        double testFloat;
        final int MAXCHAR = 20; // max field = 20 bytes/chars
        String newLine;

        // while the next line is not an empty line
        while (scan.hasNextLine()) {
            if (!(newLine = scan.nextLine()).isEmpty()) {
                line++;
                Coupon tmpCoupon = new Coupon();
                // split each newLine on ",", and store to arr
                arr = newLine.split(",");
                // test to ensure index 0-5 !null
                if (arr.length != 6) {
                    System.out.printf("Error at line %s. Try again with a valid format\n", line);
                    break;
                }
                // if
                if (arr[0].length() > MAXCHAR) {
                    System.out.printf("Line %d: Only the first %d characters will be stored.\n", line, MAXCHAR);
                    tmpCoupon.setProvider(arr[0].substring(0, MAXCHAR));
                } else {
                    tmpCoupon.setProvider(arr[0]);
                }

                // test and set product, price should be positive
                testFloat = isFloat(arr[2]);
                if (testFloat < 0) {
                    System.out.printf("Error at line %s. Try again with a valid format\n", line);
                    break;
                }
                // set product if valid
                else {
                    Product tmpProd;
                    if (arr[1].length() > MAXCHAR) {
                        System.out.printf("Line %d: Only the first %d characters will be stored.\n", line, MAXCHAR);
                        tmpProd = new Product(arr[1].substring(0, MAXCHAR), testFloat);
                        tmpCoupon.setProduct(tmpProd);
                    } else {
                        tmpProd = new Product(arr[1], testFloat);
                        tmpCoupon.setProduct(tmpProd);
                    }
                }

                // test and set discount 5-80%
                testFloat = isFloat(arr[3]);
                if (testFloat < 5 || testFloat > 80) {
                    System.out.println("Error at line " + line + ". Try again with a valid format\n");
                    break;
                } else {
                    tmpCoupon.setDiscount(testFloat);
                }

                // test and set expiration 0-365 days
                testInt = isInt(arr[4]);
                if (testInt < 0 || testInt > 365) {
                    System.out.println("Error at line " + line + ". Try again with a valid format\n");
                    break;
                } else {
                    tmpCoupon.setExpiration(testInt);  // set expiration
                }

                if (arr[5].equalsIgnoreCase("true") || arr[5].equalsIgnoreCase("t")) {
                    tmpCoupon.setStatus(true);
                } else if (arr[5].equalsIgnoreCase("false") || arr[5].equalsIgnoreCase("f")) {
                    tmpCoupon.setStatus(false);
                } else {
                    System.out.println("Error at line " + line + ". Try again with a valid format\n");
                    break;
                }
//            arrList.add(tmpCoupon);
                coupons.add(tmpCoupon);
                count++;
            }
        }
        scan.close();
        System.out.printf("%d coupon(s) added to database.\n", count);
        return coupons;
    }

    // call methods for menu options
    public void display() throws Exception {
        int option = menu();
        // Loop menu until user chooses to quit program
        while (option != 5) {
            switch (option) {
                case 1:
                    option1();
                    option = menu();
                    break;
                case 2:
                    option2();
                    option = menu();
                    break;
                case 3:
                    option3();
                    option = menu();
                    break;
                case 4:
                    option4();
                    option = menu();
                    break;
            }
        }
        export("export.txt");
        System.out.println("Goodbye!");
    }

    // creates and returns new coupon from input
    protected Coupon newCoupon() {
        Scanner scan = new Scanner(System.in);
        String input;
        Coupon newCoupon = new Coupon();
        Product newProduct = new Product();
        int intTest;
        double floatTest;
        boolean answer = false;
        final int MAXCHAR = 20;

        System.out.println("Enter the provider name: ");
        input = scan.nextLine();
        if (input.length() > MAXCHAR) {
            System.out.printf("Only the first %d characters will be stored.\n", MAXCHAR);
            input = input.substring(0, MAXCHAR);
        }
        // update provider name of coupon
        newCoupon.setProvider(input);

        System.out.println("Enter the product name: ");
        input = scan.nextLine();
        if (input.length() > MAXCHAR) {
            System.out.printf("Only the first %d characters will be stored.\n", MAXCHAR);
            input = input.substring(0, MAXCHAR);
        }
        // update product name
        newProduct.setName(input);

        do {
            System.out.println("Enter the price of this product without '$': ");
            input = scan.nextLine();
            // test the price before updating price attribute
            floatTest = isFloat(input);
        } while (floatTest < 0);
        // update product price and the product attribute of the coupon
        newProduct.setPrice(floatTest);
        newCoupon.setProduct(newProduct);

        do {
            System.out.println("Enter the discount without '%' (5-80%): ");
            input = scan.nextLine();
            // test before updating attribute
            floatTest = isFloat(input);
        } while (floatTest < 5 || floatTest > 80);
        newCoupon.setDiscount(floatTest);

        do {
            System.out.println("Enter the expiration period (0-365 days): ");
            input = scan.nextLine();
            // test before updating attribute
            intTest = isInt(input);
        } while (intTest < 0 || intTest > 365);
        newCoupon.setExpiration(intTest);

        do {
            System.out.println("Has this coupon been redeemed? Y/N: ");
            input = scan.nextLine();
            // test before updating attribute
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                answer = true;
                newCoupon.setStatus(true);
            } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                answer = true;
                newCoupon.setStatus(false);
            }
        } while (!answer);
        newCoupon.setExpiration(intTest);
        return newCoupon;
    }

    // calls toArray(), mergeSort(), newCoupon(), linearSearch(), and binarySearch()
    protected void search(Comparator<Coupon> comp) {
        if (coupons.getSize() == 0) {
            System.out.println("There are no coupons in the database.");
        } else {
            // convert linkedlist to array for sorting
            Coupon[] coupArr = coupons.toArray(coupons);
            coupArr = coupons.mergeSort(coupArr, 0, coupons.getSize() - 1, comp);
            // clear list and add sorted items to create sorted list
            coupons.clear();
            for (int i = 0; i < coupArr.length; i++) {
                coupons.add(coupArr[i]);
            }
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the name of a product to search (limit 20 chars): ");
            String input = scan.nextLine();
            // search the new linkedlist
            coupons.linearSearch(coupons, input);
            System.out.println();
            coupons.binarySearch(coupons, input);
        }
    }

    // option 1: purchase coupons from file or manually
    protected void option1() throws Exception {
        Scanner scan = new Scanner(System.in);
        int option;
        String input;
        // ask how to add coupons
        do {
            System.out.println("How would you like to purchase coupons?\n" +
                    "(1) Add data file (2) Add coupon manually (3) Return to menu");
            input = scan.nextLine();
            option = isInt(input);
        } while (option < 1 || option > 3);

        // add file
        if (option == 1) {
            System.out.println("""
                    Coupon information should be separated by commas (no spaces) with 1 coupon per line:
                    \tProvider,Product,Price,Discount,Expiration Period,Status
                    \t1. Name of the coupon provider and product should be at most 20 characters.
                    \t2. Price should not include '$' and be greater than 0.
                    \t3. Discount rate should not include '%' and be between 5% and 80%.
                    \t4. Expiration period should be between 0 and 365 days.
                    \t5. Unused coupons should be entered as "F". Redeemed coupons should be entered as "T".
                    Enter the file name including extension:\s""");
            input = scan.nextLine();
            File file = new File(input);
            // check if file is readable before trying to add to list
            if (file.canRead()) {
                readfile(file);
            } else {
                System.out.println("Invalid file name");
            }
        }
        // add coupon manually
        else if (option == 2) {
            Coupon newCoupon = newCoupon();
            coupons.add(newCoupon);
        }
        // return to menu
        if (option == 3) {
            return;
        }
    }

    // option 2: search coupon
    // calls search and sorts by product name
    protected void option2() {
        search(coupons.prodComp());
    }

    // option 3: prints list of coupons
    protected void option3() {
        // skip sorting if list.size == 0
        if (coupons.getSize() != 0) {
            // convert linkedlist to array for sorting
            Coupon[] coupArr = coupons.toArray(coupons);
            // ask how to add coupons
            System.out.println("What would you like to sort by?");
            int choice = prompt();
            switch (choice) {
                // 1: sort by provider name
                case 1:
                    coupArr = coupons.mergeSort(coupArr, 0, coupons.getSize() - 1, coupons.provComp());
                    break;
                // 2: sort by product name
                case 2:
                    coupArr = coupons.mergeSort(coupArr, 0, coupons.getSize() - 1, coupons.prodComp());
                    break;
                // 3: sort by price
                case 3:
                    coupArr = coupons.mergeSort(coupArr, 0, coupons.getSize() - 1, coupons.priceComp());
                    break;
                // 4: sort by discount
                case 4:
                    coupArr = coupons.mergeSort(coupArr, 0, coupons.getSize() - 1, coupons.disComp());
                    break;
                // sort by expiration date
                case 5:
                    coupArr = coupons.mergeSort(coupArr, 0, coupons.getSize() - 1, coupons.expComp());
                    break;
                // sort by redemption status
                case 6:
                    coupArr = coupons.mergeSort(coupArr, 0, coupons.getSize() - 1, coupons.statusComp());
                    break;
                case 7:
                    return;
            }
            // clear list and add sorted items to create sorted list
            coupons.clear();
            for (int i = 0; i < coupArr.length; i++) {
                coupons.add(coupArr[i]);
            }
        }
        printDB();
    }

    // option 4: clear the database
    protected void option4() {
        Scanner scan = new Scanner(System.in);
        boolean answer = false;
        String input;
        do {
            System.out.println("Are you sure you want to clear the database? Y/N: ");
            input = scan.nextLine();
            // confirm before clearing
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                answer = true;
                coupons.clear();
                System.out.println("Database cleared.");
            } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                answer = true;
            }
        } while (!answer);
    }

    // prompt to determine how to sort list
    protected int prompt() {
        Scanner scan = new Scanner(System.in);
        int option;
        String input;
        // loop prompt if input is invalid
        do {
            System.out.println("(1) Provider Name (2) Product (3) Discounted Price " +
                    "(4) Discount (5) Expiration Period (6) Coupon Status (7) Return to Menu\n");
            input = scan.nextLine();
            option = isInt(input);
        } while (option < 1 || option > 7);
        return option;
    }

    // print database
    protected void printDB() {
        System.out.println("        PROVIDER                PRODUCT             PRICE     DISCOUNT    EXP. PERIOD    STATUS   | DISCOUNTED PRICE");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
//        System.out.println(" TEST0123451234512345   FORMATTING_LINE_TEST   $ 10000.00     20.00 %          300      Unused       8000.00");
        coupons.printLL();
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Number of Coupons: (" + coupons.getSize() + ")\n");
    }

    // export to a data file
    protected void export(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            System.out.println("File created: " + filename);
            boolean dummybool = true;
            // write each item to file
            for (int i = 0; i < coupons.getSize(); i++) {
                String item = coupons.getElement(i).toString(dummybool);
                fw.write(item + "\n");
            }
            // close file
            fw.close();
        } catch (IOException ioe) {
            System.out.println("Error: unable to export.");
        }
    }
}
