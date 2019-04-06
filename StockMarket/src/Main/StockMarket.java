package Main;

//Imports
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

class StockMarket{
	
  
    public static void main(String args[]) throws IOException, ParserConfigurationException, SAXException{
		  
	    //set up scanner
	    Scanner menu = new Scanner(System.in);
	    String selection = "";
	    //String result = "";
	    
	    //Loop for the menu and selections
	    while (selection != "e") {
	    	      
	        //Menu print out
	        System.out.println("\nMain Menu (enter the letter next to the selection to choose):\n");
	        System.out.println("i. Insert");
	        System.out.println("u. Update");
	        System.out.println("d. Delete");
	        System.out.println("n. Find Number");
	        System.out.println("s. Find String");
	        System.out.println("a. Aggregate");
	        System.out.println("e. Exit");
	      
	        //User input option
	        System.out.println("\nType in the letter of the desired action: ");
	        selection = menu.nextLine();
	      
	        //Insert
	        if (selection.contentEquals("i")) {
	    	    System.out.println("\nYou chose Insert\n");
	    	  
	    	    //Start by entering the key or b to go back
	    	    System.out.println("Enter in a key in quotes (Will be a total of 4 inputs will be two field:value sets), or b to go back: ");
	    	    String input = menu.nextLine();
	    	    //I used "Ticker", "ZYX", "Volume", "1"
	    	  
	    	    if (input.equals("b")) {
	    		    //Go back
	    		    System.out.println("\nBack to the Main Menu\n");
	    	    }
	    	    else {
	    	    	//Get the rest of the inputs, they already avoided "b" for the first one so continue assuming they do not want to exit.
	    		    System.out.println("\nNow enter in a value in quotes: ");
	    		    String input2 = menu.nextLine();
	    		  
	    		    System.out.println("\nNow enter in a second key in quotes: ");
	    		    String input3 = menu.nextLine();
	    		  
	    		    System.out.println("\nNow enter in a second value in quotes: ");
	    		    String input4 = menu.nextLine();
	    		    	    		    
	    		    //Call Insert class
	    		    ClassFiles.Insert.InsertDocument(input, input2, input3, input4);
				  
	    		  
	    	    }
	    		  
	        }
	      
	        //Update
	        else if (selection.contentEquals("u")) {
	    	    System.out.println("\nYou chose Update\n");
	    	  
	    	    //Start by entering the key or b to go back
	    	    System.out.println("Enter in the query key to update in quotes, or b to go back: ");
	    	    String input = menu.nextLine();
	    	    //I used "Ticker", "ZYX", "Volume", "12"
	    	  
	    	    if (input.equals("b")) {
	    		    //Go back
	    		    System.out.println("\nBack to the Main Menu\n");
	    	    }
	    	    else {
	    	    	//Get the rest of the inputs, they already avoided "b" for the first one so continue assuming they do not want to exit.
	    		    System.out.println("\nNow enter in the query value in quotes: ");
	    		    String input2 = menu.nextLine();
	    		  
	    		    System.out.println("\nNow enter in the updated key in quotes: ");
	    		    String input3 = menu.nextLine();
	    		  
	    		    System.out.println("\nNow enter in the updated value in quotes: ");
	    		    String input4 = menu.nextLine();
	    		    
	    		    //Call Update class
	    		    ClassFiles.Update.UpdateDocument(input, input2, input3, input4);
	    		  
	    	    }
	    	  
	        }
	      
	        //Delete
	        else if (selection.contentEquals("d")) {
	    	    System.out.println("\nYou chose Delete\n");
	    	  
	    	    //Start by entering the key or b to go back
	    	    System.out.println("Enter in a key to delete in quotes, or b to go back: ");
	    	    String input = menu.nextLine();
	    	    //I put in the same two inputs as the insert command just to delete what I had added
	    	  
	    	    if (input.equals("b")) {
	    		    //Go back
	    		    System.out.println("\nBack to the Main Menu\n");
	    	    }
	    	    else {
	    		    System.out.println("\nNow enter in the value to delete in quotes: ");
	    		    String input2 = menu.nextLine();
	    		    
	    		    //Call Delete class
	    		    ClassFiles.Delete.DeleteDocument(input, input2);
	    		  
	    	    }
	        }
	      
	        //Find Number
	        else if (selection.contentEquals("n")) {
	    	    System.out.println("\nYou chose Find Number\n");
	    	  
	    	    //Start by entering in a high number or b to go back
	    	    System.out.println("Enter in a high numerical value (can be in decimal format), or type in 999 to go back: ");
	    	    double input = menu.nextDouble();
	    	    //I use 1 as high and 0 as low
	    	  
	    	    if (input == 999.0) {
	    		    //Go back
	    		    System.out.println("\nBack to the Main Menu\n");
	    	    }
	    	    else {
	    	    	//Enter in low number now, assuming they do not want to exit since they did not before
	    	    	System.out.println("Enter in a low numerical value (can be in decimal format): ");
		    	    double input2 = menu.nextDouble();
		    	    
		    	    //Call the FindNum class
		    	    ClassFiles.FindNum.FindNumber(input, input2);
	    		  
	    	    }
	        }  
	      
	        //Find String
	        else if (selection.contentEquals("s")) {
	    	    System.out.println("\nYou chose Find String\n");
	    	  
	    	    //Start by entering in a string or b to go back
	    	    System.out.println("Enter in a string to search for (without quotes), or b to go back: ");
	    	    String input = menu.nextLine();
	    	    //I use Medical Laboratories & Research
	    	    
	    	  
	    	    if (input.equals("b")) {
	    		    //Go back
	    		    System.out.println("\nBack to the Main Menu\n");
	    	    }
	    	    else {
	    	    	//Call the findStr class	    
	    	    	ClassFiles.FindStr.FindString(input);
	    		  
	    	    }
	        }
	      
	        //Aggregate
	        else if (selection.contentEquals("a")) {
	    	    System.out.println("\nYou chose Aggregate\n");
	    	  
	    	    //Start by entering the key or b to go back
	    	    System.out.println("Enter in a value for key Sector to aggregate (without quotes) or b to go back: ");
	    	    String input = menu.nextLine();
	    	    //I use Healthcare
	    	  
	    	    if (input.equals("b")) {
	    		    //Go back
	    		    System.out.println("\nBack to the Main Menu\n");
	    	    }
	    	    else {
	    	    	//Call the Aggregate class
	    	    	ClassFiles.Aggregate.AggregateDocument(input);
	    	    }
	        }
	      
	        //Exit loop
	        else if (selection.contentEquals("e")) {
	    	    System.out.println("\nGood Bye");
	    	    menu.close();
	    	    break;
	        }
	      
	        //If user did not input a correct selection, bring them through the loop again
	        else {
	    	    System.out.println("\nPlease make a selection above.\n");
	        }
	      }
	  }
}