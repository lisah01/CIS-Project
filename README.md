# Coupon Inventory Database System

* This project was for CS401: Introduction to Advanced Studies I, which covered data structures and algorithms.

## Description

* The program should build a database using an unsorted linked list implementation. It should take a text file or manual input and store the information to the database. Based on user input, it should sort the unsorted linked list before displaying the requested coupon information. It should also handle any exceptions such as improper file formats, out-of-bounds data fields, etc. It uses mergesort for searching and listing coupon information.
* The system contains the following information for each coupon:
-- Name of Coupon Provider
-- Name of Product
-- Price
-- Discount
-- Expiration Period
-- Status of a Coupon (Redeemed or Unused)

## Getting Started

### Dependencies

* All testing and execution of the program was done on Windows 10, but should work on other OS as well.

### Installing

* Clone the contents of this repository to a folder. Any data files should be in the same folder. Do not use "export.txt" as a file name.
* Input text files - Text files should be in the following format:
```
Provider Name,Product Name,Current Price,Discount,Expiration,Status
```
* Attributes should be separated by a comma ',' without any white spaces. See the previously defined limitations under the "Project Specifications" section.

### Executing program

* To build and execute the program:
* Open up a terminal and compile all source code files: ```javac *.java```
* Run the program: ```java Main```

* Upon startup users will be prompted with a menu

* ![image](https://user-images.githubusercontent.com/94252703/181397384-def5df3a-c1dd-438a-ae55-6868bc9b2161.png)

* Upon selection of option 1, the program will ask the user to choose between three options: input a text file, manual input, or return to the main menu. If the user chooses to input via an external text file, the text file must be in the same folder as the source code files. Instructions will be displayed, along with a prompt for file name.
* ![image](https://user-images.githubusercontent.com/94252703/181397531-252172a9-7a08-4d08-86ae-82f82435c288.png)

* It will display any warnings or issues with the text file’s format, as well as the number of records that were successfully added to the database. If the file name is invalid, it will indicate as such.
* ![image](https://user-images.githubusercontent.com/94252703/181397721-e8c04500-c05b-4463-8e76-40eb80355d26.png)

* If the user chooses to manually input data, the program will check the validity of the user's inputs at each step. The user will be prompted again if input is invalid.
* ![image](https://user-images.githubusercontent.com/94252703/181397820-cab531c0-e00a-4e29-a1a7-b2dffadef42c.png)

* If the user inadvertently chose to purchase coupons but decides otherwise, they may return to the main menu by choosing option 3.
* ![image](https://user-images.githubusercontent.com/94252703/181397855-ab7851e5-fb57-43ec-beaa-30b14ca820a8.png)

* Should the user choose to search or list coupons of an empty database, the program will indicate as such. It was necessary to code for this case because otherwise the program may throw an exception.
* ![image](https://user-images.githubusercontent.com/94252703/181397959-51a75bc6-4db7-49a0-ba66-0a4183f4734c.png)

* In a populated database, the user will be prompted to enter the name of the product they want a coupon for. The program will display the coupon, along with the number of searches (linear and binary) to find a coupon. If no coupons are found, the program will indicate as such.
* ![image](https://user-images.githubusercontent.com/94252703/181398050-498f2487-1eda-46ab-813b-d3d7e15ef215.png)

* Upon selection of option 3, "List coupons", the user will be prompted to choose how they would like to sort the list. The list will then be displayed in ascending order (A-Z, low to high numbers, "Unused" followed by "Redeemed" coupons). Prices are sorted by the final discounted price, not the current price of the product. The number of coupons in the database will be displayed. There is also an option to return to the main menu.
* Here is an example for searching by provider name:
* ![image](https://user-images.githubusercontent.com/94252703/181398124-2729892f-b9c2-471f-94f6-78c7e6effa11.png)

* To clear the database, choose option 4 and confirm your choice.
* ![image](https://user-images.githubusercontent.com/94252703/181398165-cbbb86b0-2fa2-4955-b034-063b1f0f861c.png)
* This can be verified by choosing option 3 on the main menu.

* To exit the program, choose option 5. Upon exiting, a text file named export.txt will be created which contains the contents of the database. It is in the valid format so that it can be loaded into the program on the next startup. This file should be renamed or moved to a different location so that it is not overwritten the next time the program is run.
* ![image](https://user-images.githubusercontent.com/94252703/181398237-9551e167-4147-426e-a45f-04209d3c8dce.png)
* ![image](https://user-images.githubusercontent.com/94252703/181398256-c029cea4-0815-4799-a655-00d57c5c7b76.png)
* ![image](https://user-images.githubusercontent.com/94252703/181398275-f33a94fe-f699-4954-bd08-3d5ebf430c44.png)

### Testing
* 6 text files are included in the "test files" folder which were used during the development of the program. These can be used one at a time by uncommenting the test case in ```Main.java```
