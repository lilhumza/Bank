import java.io.*;
import java.math.*;
import java.util.*;
import java.nio.file.*;
public class ATM {
	
	String storedAccountNumber;
	String storedPinNumber;
	boolean approved = false;
	boolean exit = false;
	Scanner input = new Scanner(System.in);
	
	public ATM (int accountNumber, int pinNumber) throws IOException{
		approveCredentials(accountNumber, pinNumber);
		System.out.println("Welcome to the ATM. What would you like to do? ");
		if (approved) {
			while(!exit) {
				System.out.println("Withdraw (1)\nDeposit $ (2)\nDisplay Transaction History (3)\nBank Balance Enquiry (4)\nExit (5)");
				int choice = input.nextInt();
				switch (choice) {
					case 1:
						withdrawMoney(accountNumber);
						break;
					case 2:
						depositMoney(accountNumber);
						break;
					case 3:

						break;
					case 4:

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
	
	public void approveCredentials(int accountNumber, int pinNumber){
		ArrayList<String> accountInfo = readAccountInfo(accountNumber);
		storedAccountNumber = accountInfo.get(2);
		storedPinNumber = accountInfo.get(3);
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
	
	public void withdrawMoney(int accountNumber) throws IOException{
		ArrayList<String> accountInfo = readAccountInfo(accountNumber);
		double balance =  Double.parseDouble(accountInfo.get(4));
		balance = round(balance, 2);
		System.out.println("Your current balance is " + balance + ". How much would you like to withdraw?");
		double withdrawAmount = input.nextDouble();
		double newBalance = balance - withdrawAmount;
		newBalance = round(newBalance,2);
		updateBalance(accountNumber,5, Double.toString(newBalance));
		System.out.println("You have successfully withdrawn " + withdrawAmount + ". Your new balance is now " + newBalance);
	}

	public void depositMoney(int accountNumber) throws IOException{
		ArrayList<String> accountInfo = readAccountInfo(accountNumber);
		double balance =  Double.parseDouble(accountInfo.get(4));
		balance = round(balance, 2);
		System.out.println("Your current balance is " + balance + ". How much would you like to deposit?");
		double depositAmount = input.nextDouble();
		double newBalance = balance + depositAmount;
		newBalance = round(newBalance,2);
		updateBalance(accountNumber,5, Double.toString(newBalance));
		System.out.println("You have successfully deposited " + depositAmount + ". Your new balance is now " + newBalance);
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
			System.out.println("Account does not exist");
			System.err.println("IOException: " + e.getMessage());
		}
		System.out.println(accountValues);
		return accountValues;
	}

	public static void updateBalance(int accountNumber, int lineNumber, String data) throws IOException {
		Path path = Paths.get(Integer.toString(accountNumber));
		List<String> lines = Files.readAllLines(path);
		lines.set(lineNumber - 1, data);
		Files.write(path, lines);
	}
	public double exchangeCurrency(String type, double amount){
		//usd to cad
		if (type == "usd"){
			amount /= 0.76;
		}
		//cad to usd
		else{
			amount *= 0.76;
		}
		return amount;
	}
}
