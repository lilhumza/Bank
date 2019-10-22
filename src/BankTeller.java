import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class BankTeller {
	
	Scanner input = new Scanner (System.in);
	String fName;
	String lName;
	int accNum;
	int accPin;
	double balance;
	ArrayList<String> transactionHistory = new ArrayList<String>();

	//New Account
	public BankTeller() {
		
		System.out.print("What is your first Name: ");
		this.fName = input.nextLine();
		System.out.print("\nWhat is your last Name: ");
		this.lName = input.nextLine();
		
		System.out.println("Your New Banking Information:");
		this.accNum = (int) (Math.random() * ((9999-1000)+1)+1000);
		System.out.println("Account Number: "+ accNum);
		
		this.accPin = (int) (Math.random() * ((9999-1000)+1)+1000);
		System.out.println("Account Pin: "+ accPin);
		
		this.balance = 0.00;
		
		
		
	}
	
	//Have account and want to change transaction history
	public BankTeller (int accNumber) {
		
		//Create a file reader to pull info from txt file.
		
		this.accNum = accNumber;
		
		String textFile = Integer.toString(accNum)+".txt";
		
		 try {
		      FileReader in = new FileReader(textFile);
		      BufferedReader readFile = new BufferedReader(in);

		      this.fName = readFile.readLine(); //Reads first line and adds to fName
		      this.lName = readFile.readLine(); //Reads second line and adds to lName
		      this.accNum = Integer.parseInt(readFile.readLine()); //Reads third line and adds to accNum
		      this.accPin = Integer.parseInt(readFile.readLine()); //Reads fourth line and adds to accPin
		      
		      String[] splitStr = readFile.readLine().split(" ");
		      
		      for (int i = 0; i < splitStr.length; i++){
		        this.transactionHistory.add(splitStr[i]);
		      }
		      readFile.close();

		    } catch (IOException e) {
				System.err.println("IOException: " + e.getMessage());
		}
		
	}
	public void toDoPrompt() throws IOException {
		
		System.out.println("What would you like to do?\n1. Deposit\n2. Withdraw\n3. Change Transaction History\n4. Close Account\n5. Finished");
		switch (input.nextInt()) {
		case 1:
			System.out.println("Enter deposit amount:");
			break;
		case 2:
			System.out.println("Enter witdrawal amount: ");
			break;
		case 3:
			changeTransactionHistory();
			break;
		case 4:
			closeAccount();
			break;
		case 5:
			writeInfo();
			break;
		default:
		System.out.println("Done Messed Up Young Blood");
		}
	}

	public void changeTransactionHistory() {
		
		System.out.println("Current History: \n"+transactionHistory);
		
		if (transactionHistory.size() == 0) {
			System.out.println("You Have No Transactions!");
		} else {
			
			System.out.println("Remove Transaction at index:");
			int index = input.nextInt();
			System.out.println("Removing Transaction: "+transactionHistory.get(index));
			transactionHistory.remove(index);
			System.out.println("Updated Transaction History: \n"+transactionHistory);
			writeInfo();
		}
	}
	
	
	public void closeAccount() throws IOException { //Parameter filename is the cardNum as a string aka foldername
		
		String textFile = Integer.toString(accNum)+".txt";
		
		try { 
			 
			Files.deleteIfExists(Paths.get(textFile)); 
			System.out.println("Sucessfully Closed Account");
	            
	    } catch (NoSuchFileException e) {
	    	 
	    	System.out.println("No Account Exists"); 	 
	    }
	}
	
	public void writeInfo() {
		//Write new history and stuff to the file
		
		String textFile = Integer.toString(accNum)+".txt";
		
		try {
			
			FileWriter out = new FileWriter(textFile);
			BufferedWriter writeFile = new BufferedWriter(out);
			
			writeFile.write(fName);
			writeFile.newLine();
			writeFile.write(lName);
			writeFile.newLine();
			writeFile.write(String.valueOf(accNum));
			writeFile.newLine();
			writeFile.write(String.valueOf(accPin));
			writeFile.newLine();
			
			if (transactionHistory.size() > 0) {
				for (int i = 0; i < transactionHistory.size(); i++) {
					writeFile.write(transactionHistory.get(i)+" ");
				}
			}
			
			writeFile.close();
			out.close();
			
			System.out.println("Account Updated");
		} catch (IOException e) {
				System.err.println("IOException: " + e.getMessage());
		}
	}

}
