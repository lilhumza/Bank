import java.io.*;
import java.nio.file.*;
import java.util.*;


public class BankTeller {

	Scanner input = new Scanner (System.in);
	String fName;
	String lName;
	int accNum;
	int accPin;
	double balance;
	ArrayList<String> transactionHistory = new ArrayList<String>();

	//New Account
	public BankTeller() {

		System.out.print("What is your first Name: ");
		this.fName = input.nextLine();
		System.out.print("\nWhat is your last Name: ");
		this.lName = input.nextLine();

		System.out.println("Your New Banking Information:");
		this.accNum = (int) (Math.random() * ((9999-1000)+1)+1000);
		System.out.println("Account Number: "+ accNum);

		this.accPin = (int) (Math.random() * ((9999-1000)+1)+1000);
		System.out.println("Account Pin: "+ accPin);

		this.balance = 0.00;



	}

	//Have account and want to change transaction history
	public BankTeller (int accNumber) {

		//Create a file reader to pull info from txt file.

		this.accNum = accNumber;

		String textFile = Integer.toString(accNum)+".txt";

		try {

			FileReader in = new FileReader(textFile);
			BufferedReader readFile = new BufferedReader(in);

			this.fName = readFile.readLine(); //Reads first line and adds to fName
			this.lName = readFile.readLine(); //Reads second line and adds to lName
			this.accNum = Integer.parseInt(readFile.readLine()); //Reads third line and adds to accNum
			this.accPin = Integer.parseInt(readFile.readLine()); //Reads fourth line and adds to accPin
			this.balance = Double.parseDouble(readFile.readLine());//Reads fifth line and adds to balance
			String[] splitStr = readFile.readLine().split(" ");

			for (int i = 0; i < splitStr.length; i++){
				this.transactionHistory.add(splitStr[i]);
			}
			readFile.close();

		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}

	}
	public void justWrite() throws Exception{

		String textFile = Integer.toString(accNum)+".txt";

		try {

			FileWriter out = new FileWriter(textFile);
			BufferedWriter writeFile = new BufferedWriter(out);

			writeFile.write(fName);
			writeFile.newLine();
			writeFile.write(lName);
			writeFile.newLine();
			writeFile.write(String.valueOf(accNum));
			writeFile.newLine();
			writeFile.write(String.valueOf(accPin));
			writeFile.newLine();
			writeFile.write(String.valueOf(balance));
			writeFile.newLine();
			writeFile.write("0");

			writeFile.close();
			out.close();

			System.out.println("Account Updated");
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}

	}
	public void toDoPrompt() throws Exception {

		System.out.println("What would you like to do?\n1. Change Transaction History\n2. Close Account\nType any other number to cancel");
		switch (input.nextInt()) {
			case 1:
				changeTransactionHistory();

				break;
			case 2:
				closeAccount();
				break;
			default:
				System.out.println("User Cancelled, Have A Good Day :)");

		}
	}
	public void deposit() {
		System.out.println("Enter deposit amount: ");
		double amount = input.nextDouble();
		balance += amount;
		transactionHistory.add("+"+amount);
		System.out.println("Current Balance: "+balance);

	}

	public void withDraw() {

		if (balance <= 0.00){
			System.out.println("No Money in Account! Deposit First!");
			balance = 0.00;
		} else {
			System.out.println("Enter withdrawal amount: ");
			double amount = input.nextDouble();
			balance -= amount;
			transactionHistory.add("-"+amount);
			System.out.println("Current Balance: "+balance);
		}
	}
	public void changeTransactionHistory() {

		if (transactionHistory.contains("0")) {
			transactionHistory.remove("0");
			if (transactionHistory.size() == 0){
				System.out.println("You Have No Transactions!");
				transactionHistory.add("0");
			}
			else {
				System.out.println("Current History: \n"+transactionHistory);
				System.out.println("Remove Transaction at index:");
				int index = input.nextInt();
				index -=1;

				System.out.println("Removing Transaction: "+transactionHistory.get(index));
				if (transactionHistory.get(index).substring(0,1).equals("+")) {
					//Add to Balance Remove from history
					System.out.println(transactionHistory.size());

					balance -= Double.parseDouble(transactionHistory.get(index).substring(1));

				} else if (transactionHistory.get(index).substring(0,1).equals("-")) {
					//Subtract to balance from history
					System.out.println(transactionHistory.size());

					balance += Double.parseDouble(transactionHistory.get(index).substring(1));

				}

				transactionHistory.remove(index);
				System.out.println("Updated Transaction History: \n"+transactionHistory);
				writeInfo();
			}


		}
	}


	public void closeAccount() throws IOException { //Parameter filename is the cardNum as a string aka foldername

		String textFile = Integer.toString(accNum)+".txt";

		try {

			Files.deleteIfExists(Paths.get(textFile));
			System.out.println("Sucessfully Closed Account");

		} catch (NoSuchFileException e) {

			System.out.println("No Account Exists");
		}
	}

	public void writeInfo() {
		//Write new history and stuff to the file

		String textFile = Integer.toString(accNum)+".txt";

		try {

			FileWriter out = new FileWriter(textFile);
			BufferedWriter writeFile = new BufferedWriter(out);

			writeFile.write(fName);
			writeFile.newLine();
			writeFile.write(lName);
			writeFile.newLine();
			writeFile.write(String.valueOf(accNum));
			writeFile.newLine();
			writeFile.write(String.valueOf(accPin));
			writeFile.newLine();
			writeFile.write(String.valueOf(balance));
			writeFile.newLine();
			writeFile.write("0");
			if (transactionHistory.size() > 0) {
				for (int i = 0; i < transactionHistory.size(); i++) {
					writeFile.write(transactionHistory.get(i)+" ");
				}
			}



			writeFile.close();
			out.close();

			System.out.println("Account Updated");
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}

}
