import java.io.*;
import java.util.*;

public class Account {
	Scanner input = new Scanner (System.in);
	String firstName, lastName, enterAccNumS, splitFile;
	int pin, accountNum, enterAccNum, count;
	double balance;

	FileReader read;
	BufferedReader buffRead;

	public void readFile () throws Exception
	{
		System.out.println("To access your account information, please enter your account number.");
		enterAccNumS = input.nextLine();
		accountNum = Integer.parseInt(enterAccNumS);
		File f = new File(enterAccNumS + ".txt");
		while((f.exists() && !f.isDirectory()) == false) {

			System.out.println("There is no account information associated with the provided number, please try again.");
			System.out.println("Enter your account number.");
			enterAccNumS = input.nextLine();
			accountNum = Integer.parseInt(enterAccNumS);
			f = new File(accountNum + ".txt");
			break;
		}

		if ((f.exists() && !f.isDirectory()) == true)
		{
			try {
				read = new FileReader(f);
				buffRead = new BufferedReader(read);
				
				System.out.println("First name: " + buffRead.readLine());
				System.out.println("Last name: " + buffRead.readLine());
				System.out.println("Account number: " + buffRead.readLine());
				System.out.println("Account pin: " + buffRead.readLine());
				System.out.println("Balance: $" + buffRead.readLine());
				System.out.println("Security questions: " + buffRead.readLine());
				System.out.println("Transaction History: " + buffRead.readLine());
				System.out.println("");

			} catch (FileNotFoundException e1) {
			} catch (IOException e) {
			}
		}
	}
}
