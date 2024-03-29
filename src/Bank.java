import java.io.*;
import java.util.*;

public class Bank {
	
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {

		FileEncryption fe = new FileEncryption();

		int choice, accountNumber, accountPin;
		boolean exitO = false, exitI = false, exitI2 = false, t = false, a = false;

		java.util.Date date = new java.util.Date();

		System.out.println("TD Banking  - " + date + "\nHello, thank you for choosing to bank with TD.");


		while (exitO == false){
			for(int i = 0; i < 3; i++){
				if(i == 0 || i == 2){
					System.out.println(" -----------");
				}
				else{
					System.out.println("| Main Menu |");
				}
			}
			System.out.println("(1) SignIn\n(2) SignUp\n(3) Exit");
			choice = readInt();
			if(choice == 3){
				System.exit(1);
			}
			t = false;
			exitI = false;

			switch(choice){
				case 1:
					while (t == false){

						System.out.print("Enter your account number: ");
						accountNumber = readInt();
						System.out.print("Enter your PIN: ");
						accountPin = readInt();

						try{
							File f = new File(accountNumber + ".txt");
							fe.decrypt(f,f);
						}catch (Exception ignore){
						}

						ATM atm = new ATM (accountNumber,accountPin);
						t = atm.approveCredentials(accountNumber, accountPin);

						if(t == false){
							System.out.println("Incorrect account number or account PIN.\n(1) Try again.\n(2) Create New Account\n");
							choice = readInt();
							if(choice == 2){
								t = true;
								exitI = true;
							}
						}
						else{
							t = true;
							while (exitI == false){
								for(int i = 0; i < 3; i++){
									if(i == 0 || i == 2){
										System.out.println(" ------");
									}
									else{
										System.out.println("| BANK |");
									}
								}

								System.out.println("Where would you like to go?\n1. Bankteller\n2. Atm\n3. LogOut");
								choice = readInt();
								exitI2 = false;
								while (exitI2 == false){
									switch (choice){
										case 1:
											boolean tempB = false;
											boolean tempC = false;
											for(int i = 0; i < 3; i++){
												if(i == 0 || i == 2){
													System.out.println(" -------------");
												}
												else{
													System.out.println("| BANK TELLER |");
												}
											}
											
											BankTeller existAcc = new BankTeller(accountNumber);

											while (tempC == false) {
												tempC = existAcc.securityQuestions();
												if(tempC == true){
													exitI2 = true;
												}
											}

											tempB = existAcc.toDoPrompt();
											if(tempB == true){
												exitI = true;
												a = true;
											}
											exitI2 = true;
											break;
										case 2:
											boolean exit = false;
											while(!exit ) {
												for(int i = 0; i < 3; i++){
													if(i == 0 || i == 2){
														System.out.println(" -----");
													}
													else{
														System.out.println("| ATM |");
													}
												}
												System.out.println("Withdraw (1)\nDeposit $ (2)\nDisplay Transaction History (3)\nBank Balance Enquiry (4)\nExchange Currency (5)\nExit (6)");
												choice = readInt();
												switch (choice) {
													case 1:

														atm.withdrawMoney(accountNumber);

														break;
													case 2:

														atm.depositMoney(accountNumber);

														break;
													case 3:

														atm.transactionHistory(accountNumber, true);

														break;
													case 4:

														atm.currentBankBalance(accountNumber);

														break;
													case 5:
														System.out.println("USD -> CAD(1)\nCAD -> USD (2)");
														choice = readInt();
														if (choice == 1){
															System.out.println("Please enter your USD");
															double money = readDouble();
															atm.exchangeCurrency(1, money);
														}
														else{
															System.out.println("Please enter your CAD");
															double money = readDouble();
															atm.exchangeCurrency(2, money);
														}
														break;
													case 6:
														System.out.println("You have left ATM.");
														exit = true;
														exitI = false;
														exitI2 = true;
														break;

													default:
												}
											}
										case 3:
											if (exitI2 == true){

												a = true;
												break;
											}
											try{
												System.out.println(accountNumber);
												File f = new File(accountNumber + ".txt");
												fe.encrypt(f,f);
											}catch (Exception ignore){
												System.out.println("g");
											}
											System.out.println("You have been logged out\n");

											accountNumber=0;
											accountPin=0;
											exitI = true;
											exitI2 = true;
											a = true;
											break;

										default:
											System.out.println("Invalid Entry\n");
									}

								}

							}

						}
					}
				case 2:
					if (a == true){
						a = false;
						break;
					}
					BankTeller account = new BankTeller ();
					int c = account.accNum;
					account.justWrite();
					System.out.println("Returned to main menu\n\n");
					try{
						File f = new File(c + ".txt");
						fe.encrypt(f,f);
					}catch (Exception ignore){
					}
					break;
			}

		}
		
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
