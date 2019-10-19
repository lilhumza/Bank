import java.io.*;
import java.nio.file.*;
import java.util.*;


public class BankTeller {
	
	Scanner input = new Scanner (System.in);
	String fName;
	String lName;
	int accNum;
	int accPin;
	double balance;
	ArrayList<String> transactionHistory;

	//New Account
	public BankTeller() {
		
		System.out.print("What is your first Name: ");
		this.fName = input.nextLine();
		System.out.print("\nWhat is your last Name: ");
		this.lName = input.nextLine();
		
		this.accNum = (int) (Math.random() * ((9999-1000)+1)+1000);
		System.out.println("Account Number: "+ accNum);
		
		this.accPin = (int) (Math.random() * ((9999-1000)+1)+1000);
		System.out.println("Account Pin: "+ accPin);
		
		this.balance = 0.00;
		this.transactionHistory = new ArrayList<String>();
	}
	
	//Have account and want to change transaction history
	public BankTeller (int accNumber) {
		//Check if file exists, then pull data from text file
		//Else throw an error to user
		//Create a file reader to pull info from txt file.
		
		this.accNum = accNumber;
		
		
//		this.accPin = accPinNum;
//		this.balance = inputBalance;
//		this.transactionHistory = inputTransactionHistory;
		
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
		}
	}
	
	
	public void closeAccount() throws IOException { //Parameter filename is the cardNum as a string aka foldername
		
		String textFile = Integer.toString(accNum)+".txt";
		
		try { 
			Files.deleteIfExists(Paths.get(textFile)); 
	            
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
