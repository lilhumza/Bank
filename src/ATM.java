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
		super();
		this.exit = exit;
		approveCredentials(accountNumber, pinNumber);

	}

	public boolean approveCredentials(int accountNumber, int pinNumber){
		ArrayList<String> accountInfo = readAccountInfo(accountNumber);
		storedAccountNumber = accountInfo.get(2);
		storedPinNumber = accountInfo.get(3);
		if (accountNumber == Integer.parseInt(storedAccountNumber)){
			if (pinNumber == Integer.parseInt(storedPinNumber)){
				return approved = true;
			}
		}
		return approved = false;
	}

	public String transactionHistory(int accountNumber, boolean t) throws IOException{
		String fileName = Integer.toString(accountNumber);
		String line = Files.readAllLines(Paths.get(fileName)).get(5);
		if (t){
			System.out.println("\nTransaction History\n" + line + "\n");
		}
		return line;
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
		System.out.println("You have successfully withdrawn " + withdrawAmount + ". Your new balance is now " + newBalance +"\n");
		String history = "-" + Double.toString(withdrawAmount);
		updateTransactionHistory(accountNumber, history);
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
		System.out.println("You have successfully deposited " + depositAmount + ". Your new balance is now " + newBalance + "\n");
		String history = "+" + Double.toString(depositAmount);
		updateTransactionHistory(accountNumber, history);
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
		return accountValues;
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

	public static void updateBalance(int accountNumber, int lineNumber, String data) throws IOException {
		Path path = Paths.get(Integer.toString(accountNumber));
		List<String> lines = Files.readAllLines(path);
		lines.set(lineNumber - 1, data);
		Files.write(path, lines);
	}
	public void updateTransactionHistory(int accountNumber, String data) throws IOException {
		String tH = transactionHistory(accountNumber, false);
		String fileName = Integer.toString(accountNumber);
		RandomAccessFile f = new RandomAccessFile(fileName, "rw");
		long length = f.length() - 1;
		byte b;
		do {
			length -= 1;
			f.seek(length);
			b = f.readByte();
		} while(b != 10);
		f.setLength(length+1);
		f.close();

		tH += (" " + data);
		FileWriter in = new FileWriter(fileName, true);
		BufferedWriter bw = new BufferedWriter(in);
		bw.write(tH);
		bw.close();
		in.close();

	}
	
	public void currentBankBalance(int accountNumber) throws IOException{
		String fileName = Integer.toString(accountNumber);
		String line = Files.readAllLines(Paths.get(fileName)).get(4);
		System.out.println("Your current balance is: " + line);
	}

}
