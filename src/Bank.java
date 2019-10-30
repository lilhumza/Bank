import java.io.File;
import java.util.Scanner;

public class Bank {
	public static void main(String[] args) throws Exception {

		FileEncryption fe = new FileEncryption();

		int choice, accountNumber, accountPin;
		boolean exitO = false, exitI = false, exitI2 = false, t = false, a = false;

		Scanner input = new Scanner (System.in);
		java.util.Date date = new java.util.Date();

		System.out.println("TD Banking  - " + date + "\nHello, thank you for choosing to bank with TD.");


		while (exitO == false){
			System.out.println("Are you an existing user? If you are, enter 1. If not, enter 2. ");
			choice = input.nextInt();
			t = false;
			exitI = false;

			switch(choice){
				case 1:
					while (t == false){


						System.out.print("Enter your account number: ");
						accountNumber = input.nextInt();
						System.out.print("Enter your PIN ");
						accountPin = input.nextInt();

						try{
							File f = new File(accountNumber + ".txt");
							fe.decrypt(f,f);
						}catch (Exception ignore){
						}
						
						ATM atm = new ATM (accountNumber,accountPin);
						t = atm.approveCredentials(accountNumber, accountPin);

						if(t == false){
							System.out.println("Incorrect account number or account PIN. Try again.");

						}
						else{


							t = true;
							while (exitI == false){
								System.out.println("Where would you like to go?\n1. Bankteller\n2. Atm\n3. LogOut");
								choice = input.nextInt();
								exitI2 = false;
								while (exitI2 == false){
									switch (choice){
										case 1:
											boolean i = false;
											System.out.println("\nWelcome To the Bank Teller\n");
											BankTeller existAcc = new BankTeller(accountNumber);
											i = existAcc.toDoPrompt();
											if(i == true){
												exitI = true;
												a = true;
											}
											exitI2 = true;
											break;
										case 2:
											System.out.println("\nWelcome to the ATM");
											boolean exit = false;
											while(!exit ) {
												System.out.println("Withdraw (1)\nDeposit $ (2)\nDisplay Transaction History (3)\nBank Balance Enquiry (4)\nExchange Currency (5)\nExit (6)");
												choice = input.nextInt();
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
														choice = input.nextInt();
														if (choice == 1){
															System.out.println("Please enter your USD");
															double money = input.nextDouble();
															atm.exchangeCurrency(1, money);
														}
														else{
															System.out.println("Please enter your CAD");
															double money = input.nextDouble();
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
												File f = new File(accountNumber + ".txt");
												fe.encrypt(f,f);
											}catch (Exception ignore){
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
					account.justWrite();
					System.out.println("Returned to main menu");
					break;
			}

		}



	}
}
