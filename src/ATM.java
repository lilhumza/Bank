import java.io.*;
import java.math.*;
import java.util.*;
import java.nio.file.*;
public class ATM {
	
	int accountNumber;
	String storedAccountNumber;
	int pinNumber;
	String storedPinNumber;
	double balance;
	boolean approved = false;
	
	public ATM (int accountNumber, int pinNumber) throws IOException{
		Scanner input = new Scanner(System.in);
		approveCredentials(accountNumber, pinNumber);
		if (approved) {
			System.out.println("Welcome to the ATM. What would you like to do? \nWithdraw (1)\nDeposit $ (2)\nDisplay Transaction History (3)\nBank Balance Enquiry (4)\nExit (5)");
			int choice = input.nextInt();
			switch(choice) {
			  case 1:
			  	balance = withdrawMoney(accountNumber);
			  	balance = round(balance, 2);
			  	System.out.println("Your current balance is " + balance + ". How much would you like to withdraw?");
			  	double withdrawAmount = input.nextDouble();
			  	double newBalance = balance - withdrawAmount;
			  	newBalance = round(newBalance,2);
			  	updateBalance(accountNumber,3, Double.toString(newBalance));
			  	System.out.println("You have successfully withdrawn " + withdrawAmount + ". Your new balance is now " + newBalance);
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

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
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
			FileReader in = new FileReader(fileName);
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

	public static void updateBalance(int accountNumber, int lineNumber, String data) throws IOException {
		Path path = Paths.get(Integer.toString(accountNumber));
		List<String> lines = Files.readAllLines(path);
		lines.set(lineNumber - 1, data);
		Files.write(path, lines);
	}
}
