import java.io.*;
import java.util.*;
public class Bank {

	public static void main(String[] args) throws IOException{

		Scanner input = new Scanner(System.in);
		
		int accountNumber = input.nextInt();
		int accountPin = input.nextInt();
		
		ATM account = new ATM(accountNumber,accountPin);
		System.out.println("Welcome to the ATM. What would you like to do? ");
		boolean approved = account.approveCredentials(accountNumber,accountPin);
		boolean exit = account.exit;
		if (approved) {
			while(!exit) {
				System.out.println("Withdraw (1)\nDeposit $ (2)\nDisplay Transaction History (3)\nBank Balance Enquiry (4)\nExchange Currency (5)\nExit (6)");
				int choice = input.nextInt();
				switch (choice) {
					case 1:
						account.withdrawMoney(accountNumber);
						break;
					case 2:
						account.depositMoney(accountNumber);
						break;
					case 3:
						account.transactionHistory(accountNumber,true);
						break;
					case 4:
						account.currentBankBalance(accountNumber);
						break;
					case 5:
						System.out.println("USD -> CAD(1)\nCAD -> USD (2)");
						choice = input.nextInt();
						if (choice == 1){
							System.out.println("Please enter your USD");
							double money = input.nextDouble();
							account.exchangeCurrency(1, money);
						}
						else{
							System.out.println("Please enter your CAD");
							double money = input.nextDouble();
							account.exchangeCurrency(2, money);
						}
						break;
					case 6:
						exit = true;
					default:

				}
			}
		}
		else{
			System.out.println("Pin number associated with account is incorrect.");
		}

	}

}
