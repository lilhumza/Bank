import java.io.File;
import java.io.IOException;
import java.util.*;

public class Bank {


	public static void main(String[] args) throws Exception {

		// variables

		int userStatus;
		Scanner input = new Scanner (System.in);

		java.util.Date date = new java.util.Date(); // date

		//  this allows the user to access the bank based
		//  on whether or not they are an existing user

		System.out.println("TD Banking  - " + date);
		System.out.println("Hello, thank you for choosing to bank with TD.");

		System.out.print("Are you an existing user? If you are, enter 1. If not, enter 2. ");
		userStatus = input.nextInt();
		if (userStatus == 1)
			userInput(input);
		else if (userStatus == 2) {
			BankTeller account = new BankTeller ();
			account.justWrite();
			userInput(input);
		}

		input.close();
	}

	// method that allows the user to access the bank

	public static void userInput (Scanner input) throws Exception {
		FileEncryption fe = new FileEncryption();

		String firstName, lastName;
		int option, accountPin, accountNumber = 0;

		do {
			System.out.println("\n1. Continue talking to a bankteller.\n2. Talk to the ATM.\n3. Check your account information.\n4. Exit.");
			option = input.nextInt();
			switch(option) {

				case 1: // bank teller
					System.out.println("\n1. To access your existing account\n2. To create a new account");
					switch (input.nextInt()) {

						case 1:
							System.out.print("Enter your account number: ");
							accountNumber = input.nextInt();
							System.out.print("Enter your PIN ");
							int pin = input.nextInt();

							try {
								File f = new File(accountNumber + ".txt");
								fe.decrypt(f, f);
							}catch (Exception e){
							}

							BankTeller existAcc = new BankTeller(accountNumber);
							existAcc.toDoPrompt();

							break;

						case 2:
							try {
								File f = new File(accountNumber + ".txt");
								fe.decrypt(f, f);
							}catch (Exception e){
							}
							BankTeller newAcc = new BankTeller();
							newAcc.toDoPrompt();
							break;
					}
					break;

				case 2: // ATM

					System.out.println("Enter your account number.");
					accountNumber = input.nextInt();
					System.out.println("Enter your account PIN.");
					accountPin = input.nextInt();
					try {
						File f = new File(accountNumber + ".txt");
						fe.decrypt(f, f);
					}catch (Exception e){

					}

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

									accountAT.transactionHistory(accountNumber, true);

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

				case 3: // account information
					try {
						File f = new File(accountNumber + ".txt");
						fe.decrypt(f, f);
					}catch (Exception e){
					}

					Account accountAC = new Account ();
					accountAC.readFile();
					break;

				case 4:	 // exit
					System.out.println("Thank you for banking at TD.");
					break;
			}
		} while (option != 4);

		try {
			File f = new File(accountNumber + ".txt");
			fe.encrypt(f, f);
		}catch (Exception e){
		}
	}
}
