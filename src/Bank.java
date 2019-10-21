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
						account.transactionHistory(accountNumber);
						break;
					case 4:
						account.currentBankBalance(accountNumber);
						break;
					case 5:
						exit = true;
						break;
					default:

				}
			}
		}
		else{
			System.out.println("Pin number associated with account is incorrect.");
		}

	}

}
