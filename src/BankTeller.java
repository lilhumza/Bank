import java.util.*;
import java.io.*;
import java.nio.file.*;

public class BankTeller {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
	
	Scanner input = new Scanner (System.in);
	String fName, lName;
	int accNum, accPin;
	double balance;
	boolean pinCheck;
	ArrayList<String> transactionHistory = new ArrayList<String>();
	public static ArrayList<Integer> randomNumbers = new ArrayList<Integer>();
	public static String [] questions = {"What is your favorite book?", "What city were you born in?", "Where did you go to high school/college?"};
	public static String [] answers = new String [3];

	// new Account

	public BankTeller() throws Exception{

		System.out.print("What is your first Name: ");
		this.fName = readLine();
		System.out.print("\nWhat is your last Name: ");
		this.lName = readLine();

		System.out.println("Your New Banking Information:");

		this.accNum = (int) (Math.random() * ((999999-100000)+1)+100000);
		System.out.println("Account number: " + accNum);


		System.out.println("Enter your account pin.");
		String aPin = readLine();
		int length = String.valueOf(aPin).length();

		boolean isValidPin = isNum(aPin);

		while (length != 4 || isValidPin == false) {
			int count = 0;
			if (isValidPin == false){
				System.out.println("Pins cannot contain letters. Please enter a valid pin. ");
				aPin = readLine();
				isValidPin = isNum(aPin);
				length = String.valueOf(aPin).length();
				count++;
			}
			else {
				System.out.println("Please ensure that your PIN is 4 digits long.");
				System.out.println("Enter your account pin.");
				aPin = readLine();
				length = String.valueOf(aPin).length();
				isValidPin = isNum(aPin);
				count++;
			}
		}
		this.accPin = Integer.parseInt(aPin);

		this.balance = 0.00;

		String user = readLine();

		System.out.println("Now, you will be activating your security questions for enhanced security.");
		System.out.println(questions[0]);
		user = readLine();
		answers[0] = user;
		System.out.println(questions[1]);
		user = readLine();
		answers[1] = user;
		System.out.println(questions[2]);
		user = readLine();
		answers[2] = user;
	}

	// have account and want to change transaction history
	public BankTeller (int accNumber) {

		// create a file reader to pull info from txt file.

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
				answers[i] = splitStr[i];
			}

			splitStr = readFile.readLine().split(" ");

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
			for(int i = 0; i < 3 ; i++){
				writeFile.write(answers[i] + " ");
			}
			writeFile.newLine();
			if (transactionHistory.size() == 0) {
			writeFile.write("0 ");
			}
			else {
				writeFile.write("0");
				for (int i = 0; i < transactionHistory.size(); i++) {
					writeFile.write(transactionHistory.get(i) + " ");
				}
			}

			writeFile.close();
			out.close();

			System.out.println("\n");
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}

	}
	public boolean toDoPrompt() throws Exception {
		boolean e = false, t = false;
		while(e == false){
			System.out.println("What would you like to do?\n1. Change Transaction History\n2. Check your account information.\n3. Close Account\nType any other number to exit");
			int choice = readInt();
			switch (choice) {
				case 1:
					changeTransactionHistory();
					break;
				case 2:
					Account acc = new Account();
					acc.readFile();
					break;
				case 3:
					closeAccount();
					t = true;
					System.out.println("You have left the Bank Teller.\n");
					e = true;
					break;
				default:
					System.out.println("You have left the Bank Teller.\n");
					e = true;
			}
		}
		return t;
	}
	public void deposit() throws Exception{
		System.out.println("Enter deposit amount: ");
		double amount = readDouble();
		balance += amount;
		transactionHistory.add("+"+amount);
		System.out.println("Current Balance: "+balance);

	}

	public void withDraw() throws Exception{

		if (balance <= 0.00){
			System.out.println("No Money in Account! Deposit First!");
			balance = 0.00;
		} else {
			System.out.println("Enter withdrawal amount: ");
			double amount = readDouble();
			balance -= amount;
			transactionHistory.add("-"+amount);
			System.out.println("Current Balance: "+balance);
		}
	}

	public boolean securityQuestions() throws Exception {

		String user;
		System.out.println("In order to use BankTeller services, you need to answer your security questions.");
		System.out.println(questions [0]);
		user = readLine();
		if (user.equalsIgnoreCase(answers [0])) {
			System.out.println(questions [1]);
			String ans = readLine();
			if (ans.equalsIgnoreCase(answers [1])) {
				System.out.println(questions [2]);
				ans = readLine();
				if (ans.equalsIgnoreCase(answers [2])) {

					System.out.println("You have correctly answered these questions.");
					return true;
				}
			}
			else {
				System.out.println("You have incorrectly one of these questions.");
				System.out.println("");
				return false;
			}
		}
		else {
			System.out.println("You have incorrectly one of these questions.");
			System.out.println("");
			return false;
		}

		return false;

	}

	public void changeTransactionHistory() throws Exception {
		if (transactionHistory.contains("0")) {
			transactionHistory.remove("0");
			if (transactionHistory.size() == 0){
				System.out.println("You Have No Transactions!");
				transactionHistory.add("0");
			}
			else {
				System.out.println("Current History: \n"+transactionHistory);
				System.out.println("Remove Transaction at index:");
				int index = readInt();
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
				justWrite();
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

	public static boolean isNum(String userInput)
	{
		try {
			double d = Double.parseDouble(userInput);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
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
