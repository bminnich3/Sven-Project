// imported libraries

import java.util.Scanner;   // keyboard & file inputs & outputs
import java.io.File;        // connecting to external files
import java.io.IOException; // handle unexpected input/output runtime errors
import java.io.FileWriter;  // writing data to external file

class Main 
{
  // constants

  static final String NAME = "Sven";    // name of program     
  static final String INVENTORY_LIST_FILENAME = "ItemsForSale.txt";
  static final int EXIT_CHOICE = 3;     // menu choice to exit program

  public static void main(String[] args)
  {
    // local variables

    InventoryList itemsForSale = new InventoryList();  // list of items for sale
    Scanner keyboard = new Scanner(System.in);          // to obtain human's input  
  
    String userMenuChoice = "";   // human inputted menu choice
    String newItem = "";          // new item added to inventory
    int customerSelection = 0;    // customer selection from menu list of items
    boolean updateSuccess = false;  // flag to indicate if new item added or not

    // read the list of items from  external file
    try
    {
      Scanner externalFile = new Scanner(new File(INVENTORY_LIST_FILENAME));
      
      while (externalFile.hasNext())
      {
        String nextItem = externalFile.nextLine();
        itemsForSale.addItem(nextItem);
      }

    }
    catch (IOException ioe) // if error occurs trying to read data to file
    {
      System.err.println(ioe);
    }

    // displaying Sven's name in ASCII Art from (** My contribution **)
    System.out.println("\n\n --------------------------------------------------------------------------------------                           ");
    System.out.println("||                                                                                    ||                             ");
    System.out.println("||       SSSSSSSS   VV              VV   EEEEEEEEEEEEEE   NNN         NNN   !!!       ||                             ");
    System.out.println("||      S            VV            VV    EEEEEEEEEEEEEE   NNN N       NNN   !!!       ||                             ");
    System.out.println("||     S              VV          VV     EEE              NNN  N      NNN   !!!       ||                             ");
    System.out.println("||     S               VV        VV      EEE              NNN   N     NNN   !!!       ||                             ");
    System.out.println("||      SSSSSSSS        VV      VV       EEEEEEEEEE       NNN    N    NNN   !!!       ||                             ");
    System.out.println("||              S        VV    VV        EEE              NNN     N   NNN   !!!       ||                             ");
    System.out.println("||              S         VV  VV         EEE              NNN      N  NNN             ||                             ");
    System.out.println("||             S           VVVV          EEEEEEEEEEEEEE   NNN       N NNN   !!!       ||                             ");
    System.out.println("||     SSSSSSSS             VV           EEEEEEEEEEEEEE   NNN         NNN   !!!       ||                             ");
    System.out.println("||                                                                                    ||                             ");
    System.out.println(" --------------------------------------------------------------------------------------                               ");

    // setting up the screen (interface / view)
    System.out.println("\n\n\nGreetings, I am the world's first created Smart Vending Machine Unit. I am known as " + NAME + "!\n\n\n");

    System.out.println();
    
    while (!userMenuChoice.equals(EXIT_CHOICE + ""))
    {
      displayMenu();

      System.out.print("Choice: ");
      // trim leading & trailing spaces
      // TODO - allow user to input lowercase letters
      userMenuChoice = keyboard.nextLine().trim();  

    if (userMenuChoice.equals("1") || userMenuChoice.equals("A") || userMenuChoice.equals("a"))
    {
      System.out.println("[-]IN ADMIN MODE...[-]");
      System.out.println("----------------------------------------------------------------");
      System.out.print("What is your new item? ");
      newItem = keyboard.nextLine();
      itemsForSale.addItem(newItem);
      System.out.println("Your updated inventory list is: ");
      
      for (int i = 0; i < itemsForSale.numberOfItems(); i++)
      {
        System.out.println((i + 1) + ". " + itemsForSale.selectItem(i) + "\n");
      }

      try
      { 
        FileWriter externalFile = new FileWriter(INVENTORY_LIST_FILENAME, true);  
        // connect to external file, true specifies append mode
        
        // add the new item to the end of the file with the inventory list
        // TODO (Henry) - allow multiple items to be added all at once

        externalFile.write(newItem + "\n");
        externalFile.flush();                      
        externalFile.close();                                    
      }                                                           
      catch (IOException ioe) // if error occurs trying to write data to file
      {
        System.err.println(ioe);
      }

    }
    else if (userMenuChoice.equals("2") || userMenuChoice.equals("C") || userMenuChoice.equals("c"))
    {
      System.out.println("[-]IN CUSTOMER MODE...[-]");
      System.out.println("----------------------------------------------------------------");

      // display the list of items as a numbered list
      for (int i = 0; i < itemsForSale.numberOfItems(); i++)
      {
        System.out.println((i + 1) + ". " + itemsForSale.selectItem(i) + "\n");
      }

      System.out.println("Input the number you like to purchase? ");
      customerSelection = keyboard.nextInt();

      System.out.println("DEBUGGING: the customer input is #" + customerSelection);
      itemsForSale.removeItem("avocados");
      System.out.println("Thank you and have a great day!");
    }
    else if (userMenuChoice.equals("3") || userMenuChoice.equals("E") || userMenuChoice.equals("e"))
    {
      System.out.println("See you later!");
    }
      

      // TODO save / write the current most upated list of items (one by one) 
      // back to the external file
      // close the file


      try
      { 
        FileWriter externalFile = new FileWriter(INVENTORY_LIST_FILENAME, false);  
        // false specifies write mode (thanks KP)

        for (int i = 0; i < itemsForSale.numberOfItems(); i++)
        {
          externalFile.write(itemsForSale.selectItem(i) + "\n");
        }

        externalFile.flush();                      
        externalFile.close();                                    
      }                                                           
      catch (IOException ioe) // if error occurs trying to write data to file
      {
        System.err.println(ioe);
      }

      // TODO
      // display available item by reading those values from an external data file
      // allow customer to input a choice
      // trust the user to select the correct item from the machine & 
      // deposit exact amount of MinichPay (not ApplePay or AndroidPay) money 
      // in our Room 311 cash till on the honesty system
      }
  } // end of main method

  // display menu
  public static void displayMenu()
  {  
    System.out.println("1. (A)dmin Mode");
    System.out.println("2. (C)ustomer Mode");
    System.out.println(EXIT_CHOICE + ". (E)xit");
  }

} // end of Main class
