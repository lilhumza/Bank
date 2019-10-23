import java.io.IOException;
import java.util.*;

public class Bank {

	public static void main(String[] args) throws IOException {
		
		// variables
		
		String firstName, lastName;
		int option, accountPin, accountNumber;
		Scanner input = new Scanner (System.in);
		
		java.util.Date date = new java.util.Date();
		
		//  user interaction
		
		System.out.println("TD Banking  - " + date);
		System.out.println("Hello, thank you for choosing to bank with TD.");
		
		BankTeller account = new BankTeller ();
		
		System.out.println("Your account information has been generated.");

		System.out.println("\n1. Continue talking to a bankteller.\n2. Talk to the ATM.\n3. Check your account information.");
		option = input.nextInt();
			
		if (option != 4) {
		switch(option) {
		  case 1:
			  System.out.println("\n1. To access your existing account\n2. To create a new account");
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
			    
			  	System.out.println("Enter your account number.");
			  	accountNumber = input.nextInt();
			  	System.out.println("Enter your account PIN.");
			  	accountPin = input.nextInt();
				ATM accountAT = new ATM (accountNumber,accountPin);
				System.out.println("Welcome to the ATM. What would you like to do? ");
				boolean approved = accountAT.approveCredentials(accountNumber,accountPin);
				boolean exit = accountAT.exit;
				if (approved) {
					while(!exit) {
						System.out.println("Withdraw (1)\nDeposit $ (2)\nDisplay Transaction History (3)\nBank Balance Enquiry (4)\nExchange Currency (5)\nExit (6)");
						int choice = input.nextInt();
						switch (choice) {
							case 1:
								accountAT.withdrawMoney(accountNumber);
								break;
							case 2:
								accountAT.depositMoney(accountNumber);
								break;
							case 3:
								break;
							case 4:
								accountAT.currentBankBalance(accountNumber);
								break;
							case 5:
								System.out.println("USD -> CAD(1)\nCAD -> USD (2)");
								choice = input.nextInt();
								if (choice == 1){
									System.out.println("Please enter your USD");
									double money = input.nextDouble();
									accountAT.exchangeCurrency(1, money);
								}
								else{
									System.out.println("Please enter your CAD");
									double money = input.nextDouble();
									accountAT.exchangeCurrency(2, money);
								}
								break;
							case 6:
								exit = true;
							default:	
							
						}
					}
				}
				
				case 3: 
										
					Account accountAC = new Account ();
					accountAC.readFile(accountAC.accountNum);	
					
					/*System.out.println("Your first name is " + account.firstName);
					System.out.println("Your last name is " + account.lastName);
					System.out.println("Your account card number is " + account.accountNum);
					System.out.println("Your specialized PIN is " + account.pin);
					System.out.println("Your account balance is " + account.balance);*/
				    break;
		  	    	
				case 4:
						
					System.out.println("Thank you for banking at TD.");
					break;
		}
	}		
		input.close();
	}
}
