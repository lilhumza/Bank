import java.io.File;
import java.util.ArrayList;

public class BankTeller {
	int cardNum;
	int cardPin;
	double balance;
	ArrayList<String> transactionHistory;

	
	public BankTeller(int cardNumber, int cardPinNum, double inputBalance,	ArrayList<String> inputTransactionHistory) {
		
		this.cardNum = cardNumber;
		this.cardPin = cardPinNum;
		this.balance = inputBalance;
		this.transactionHistory = inputTransactionHistory;
	}
	
	public void changeTransactionHistory(int param) {
		System.out.println("Removing Transaction: "+transactionHistory.get(param));
		System.out.println("Transaction History: ");
		System.out.println(transactionHistory);
	}
	
	
	public void closeAccount(String fileName) { //Parameter filename is the cardNum as a string aka foldername
		
		//Delete The Entire Folder of the client
		
		File file  = new File(fileName);
        if(file.isDirectory()){
            String[] childFiles = file.list();
            if(childFiles == null) {
                //Directory is empty. Proceed for deletion
                file.delete();
            }
            else {
                //Directory has other files.
                //Need to delete them first
                for (String childFilePath :  childFiles) {
                    //recursive delete the files
                    closeAccount(childFilePath);
                }
            }
             
        } else {
            //it is a simple file. Proceed for deletion
            file.delete();
        }
		System.out.println("Account Closed.");
	}
	
	public void writeInfo() {
		//Write new history and stuff to the file
		
	}
	
	
	
	
	

}
