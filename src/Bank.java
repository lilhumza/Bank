import java.io.IOException;
import java.util.*;

public class Bank {

	public static void main(String[] args) throws IOException {
		
		// variables
		
		String firstName, lastName, optionS;
		int option;
		Scanner input = new Scanner (System.in);
		
		java.util.Date date = new java.util.Date();
		
		//  user interaction
		
		System.out.println("TD Banking  " + date);
		System.out.println("Hello, thank you for choosing to bank with TD.");
		
		System.out.println("");
		System.out.println("What is your first name?");
		
		firstName = input.nextLine();
		
		System.out.println("What is your last name?");	
		lastName = input.nextLine();
		System.out.println("Your account information has been generated.");
		System.out.println("");

		Account account = new Account (firstName, lastName);
		int accountNum = account.accountNum;
		account.readFile(accountNum);

		System.out.println("\n1. Continue talking to a bankteller.\n2. Talk to the ATM./n3. Check your account information.");
		
		optionS = input.nextLine();
		option = Integer.parseInt(optionS);
		
		System.out.println("");
		
		while (option!=4)
		{	
		
		switch(option) {
		  case 1:
			  System.out.println("Welcome to the Bank Teller!\n1. To access your existing account\n2. To create a new account");
			  switch (option) {
			  
			  case 1:
				  
				  System.out.print("Enter your account number: ");
				  int accNum = input.nextInt();
				  BankTeller existAcc = new BankTeller(accNum);
				  existAcc.toDoPrompt();
				  break;
				  
			  case 2:
				  
				  BankTeller newAcc = new BankTeller();
				  newAcc.toDoPrompt();
				  break;
			  
			  }
			  
		    break;
		 
		  case 2:
			  
				ATM account = new ATM(accountNumber,accountPin);
				System.out.println("Welcome to the ATM. What would you like to do? ");
				boolean approved = account.approveCredentials(accountNumber,accountPin);
				boolean exit = account.exit;
				if (approved) {
					while(!exit) {
						System.out.println("Withdraw (1)\nDeposit $ (2)\nDisplay Transaction History (3)\nBank Balance Enquiry (4)\nExit (5)");
						int choice = input.nextInt();
						switch (choice) {
							case 1:
								account.withdrawMoney(accountNumber);
								break;
							case 2:
								account.depositMoney(accountNumber);
								break;
							case 3:
								account.transactionHistory(accountNumber, true);
								break;
							case 4:
								account.currentBankBalance(accountNumber);
								break;
							case 5:
								exit = true;
								break;
						}
					}
				}
				
				case 3: 
					  
					Account accountAccess = new Account(accountNum);
					System.out.println("To access your account information, please enter your account number.");
					option = input.nextInt();
					account.readFile(option);	
					
					System.out.println("Your first name is " + account.firstName);
					System.out.println("Your last name is " + account.lastName);
					System.out.println("Your account card number is " + account.accountNum);
					System.out.println("Your specialized PIN is " + account.pin);
					System.out.println("Your account balance is " + account.balance);
				    break;
		  	    	
				case 4:
						
					System.out.println("Thank you for banking at TD.");
					break;
		}
	}
		input.close();
	}
}
