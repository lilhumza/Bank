import java.io.*;
import java.util.*;
public class ATM {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	int accountNumber;
	String storedAccountNumber;
	int pinNumber;
	String storedPinNumber;
	int balance;
	boolean approved = false;
	
	public ATM (int accountNumber, int pinNumber) throws IOException{
		approveCredentials(accountNumber, pinNumber);
		if (approved) {
			System.out.println("Welcome to the ATM. What would you like to do? \nWithdraw (1)\nDeposit $ (2)\nDisplay Transaction History (3)\nBank Balance Enquiry (4)\nExit (5)");
			int choice = readInt();
			switch(choice) {
			  case 1:
				  double balance = withdrawMoney(accountNumber);
				  System.out.println("Your current balance is " + balance + ". How much would you like to withdraw?");
				  double withdrawAcmount = readDouble();
				  System.out.println("You have successfully withdrawn " + withdrawAcmount + ". Your new balance is now " + (balance - withdrawAcmount));
			    break;
			  case 2:
			    
			    break;
			  case 3:
				    
			  	break;
			  case 4:
				    
			  	break;
			  case 5:
				    
			  	break;
			  default:
			    
			}
		}
		else{
			System.out.println("The account number or pin Number is incorrect.");
		}
	}
	
	public void approveCredentials(int accountNumber, int pinNumber){
		ArrayList<String> accountInfo = readAccountInfo(accountNumber);
		storedAccountNumber = accountInfo.get(0);
		storedPinNumber = accountInfo.get(1);
		if (accountNumber == Integer.parseInt(storedAccountNumber)){
			if (pinNumber == Integer.parseInt(storedPinNumber)){
				approved = true;
			}
		}
	}
	
	
	public double withdrawMoney(int accountNumber){
		ArrayList<String> accountInfo = readAccountInfo(accountNumber);
		double balance =  Double.parseDouble(accountInfo.get(2));
		return balance;
	}
	
	public ArrayList<String> readAccountInfo(int name) {
		String fileName = Integer.toString(name);
		ArrayList<String> accountValues = new ArrayList<String>();
		String value;
		try {
			FileReader in = new FileReader("1234.txt");
			BufferedReader readFile = new BufferedReader(in);
			while ((value = readFile.readLine()) != null) {
				accountValues.add(value);
			}
			readFile.close();
			in.close();
		} catch (Exception e) {
			System.out.println("Problem opening account.");
			System.err.println("IOException: " + e.getMessage());
		}
		return accountValues;
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong () throws IOException {
		return Long.parseLong(next());
	}

	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
