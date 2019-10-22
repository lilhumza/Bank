import java.io.IOException;
import java.util.*;
public class BankTellerMainTemp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner (System.in);
		
		System.out.println("Welcome to the Bank Teller!\n1. To access your existing account\n2. To create a new account");
		
		switch (input.nextInt()) {
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

	input.close();		
	}
	

}

