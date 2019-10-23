import java.io.*;
import java.util.*;
public class Account {

	Scanner input = new Scanner (System.in);
	String firstName, lastName, enterAccNumS, splitFile;
	int pin, accountNum, enterAccNum, count;
	double balance;
	
	FileReader read;
	BufferedReader buffRead;
	
	public void readFile (int accountNum)
	{
		
		System.out.println("To access your account information, please enter your account number.");
		enterAccNumS = input.nextLine();
		enterAccNum = Integer.parseInt(enterAccNumS);
		File f = new File(enterAccNumS + ".txt");
		
		do {
			
			if ((count > 1) && f.exists() == false)
			{
				System.out.println("There is no account information associated with the provided number, please try again.");
				System.out.println("Enter your account number.");
				enterAccNumS = input.nextLine();
				enterAccNum = Integer.parseInt(enterAccNumS);
				f = new File(enterAccNumS + ".txt");

			}

				try {
					read = new FileReader(f);
					buffRead = new BufferedReader(read);
					for (int i = 0; i < 6; i++)
					{
						System.out.println(buffRead.readLine());							
								
					}
				} catch (FileNotFoundException e1) {
				} catch (IOException e) {
				}

				count++;
				
		} while((f.exists() && !f.isDirectory()) == false);
	}			
}
