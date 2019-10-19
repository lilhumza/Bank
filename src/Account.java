import java.io.*;
import java.util.*;
public class Account {

	Scanner input = new Scanner (System.in);
	String firstName, lastName, enterAccNumS;
	int pin, accountNum, enterAccNum;
	double balance;
	
	FileReader read;
	BufferedReader buffRead;
	
	// this is not a part of the Account class,
	// rather for the program to run before the 
	// full version is compiled

	public Account(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.pin = (int)(Math.random()*(9999-1000) + 1) + 100;
		this.accountNum = (int)(Math.random()*(9999-1000) + 1) + 1000;
		this.balance = 0.0;
	}
	
	public void readFile(int accountNum) throws IOException
	{
		System.out.println("Please enter your account number.");
		enterAccNumS = input.nextLine();
		enterAccNum = Integer.parseInt(enterAccNumS);
			
		if (enterAccNum == accountNum)
		{
			try {
				read = new FileReader(enterAccNum + ".txt");
				buffRead = new BufferedReader(read);
			} catch (FileNotFoundException e) {
				System.out.println("You have entered the wrong account number, try again.");
				e.printStackTrace();
			}
			
			for (int i = 0; i<6; i++) {
				System.out.println(buffRead.readLine());
			}
		}
	}
	
	// make methods that will read the text file and
	// retrieve the values for transaction history, card number, 
	// other parameters and print them out
	
}
