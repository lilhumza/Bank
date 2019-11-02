import java.io.*;
import java.math.*;
import java.util.*;
import java.nio.file.*;
public class ATM {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    String storedAccountNumber;
    String storedPinNumber;
    boolean approved = false;
    boolean exit = false;

    ArrayList<String> accountV = new ArrayList<String>();

    public ATM (int accountNumber, int pinNumber) throws IOException{
    }

    public boolean approveCredentials(int accountNumber, int pinNumber){
        try{
            readAccountInfo(accountNumber);
            storedAccountNumber = accountV.get(2);
            storedPinNumber = accountV.get(3);
            if (accountNumber == Integer.parseInt(storedAccountNumber)){
                if (pinNumber == Integer.parseInt(storedPinNumber)){
                    return approved = true;
                }
            }
        }catch (Exception ignore){
        }
        return approved = false;
    }

    public String transactionHistory(int accountNumber, boolean t) throws IOException{
        String fileName = accountNumber + ".txt";
        String line = Files.readAllLines(Paths.get(fileName)).get(6);
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

    public void W_D_Money(int accountNumber, boolean t) throws Exception{
        double balance =  Double.parseDouble(accountV.get(4)); //get the banlance from accountV
        balance = round(balance, 2);

        if (t){
            System.out.println("Your current balance is " + balance + ". How much would you like to withdraw?");
        }
        else{
            System.out.println("Your current balance is " + balance + ". How much would you like to deposit?");
        }

        double amount = readDouble();
        double newBalance;

        if (t){
            System.out.println("Your current balance is " + balance + ". How much would you like to withdraw?");
            newBalance = balance - amount;
        }
        else{
            System.out.println("Your current balance is " + balance + ". How much would you like to deposit?");
            newBalance = balance + amount;
        }

        newBalance = round(newBalance,2);
        updateBalance(accountNumber,5, Double.toString(newBalance));

        String history;

        if(t){
            System.out.println("You have successfully withdrawn " + amount + ". Your new balance is now " + newBalance +"\n");
            history = "-" + amount;
        }
        else{
            System.out.println("You have successfully deposited " + amount + ". Your new balance is now " + newBalance + "\n");
            history = "+" + Double.toString(amount);
        }

        updateTransactionHistory(accountNumber, history);

    }


    public ArrayList<String> readAccountInfo(int name) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(Integer.toString(name) + ".txt")); //open file
        String st;

        while ((st = br.readLine()) != null){ //read file and save data into accountV
            accountV.add(st);
        }
        return accountV;
    }

    public double exchangeCurrency(int type, double amount){

        if (type == 1){
            amount /= 0.76;
            round(amount, 2);
            System.out.println("Converted to " + amount + " CAD");
        }

        else{
            amount *= 0.76;
            round(amount, 2);
            System.out.println("Converted to " + amount + " USD");
        }
        return amount;
    }

    

    public static void updateBalance(int accountNumber, int lineNumber, String data) throws IOException {
        Path path = Paths.get(Integer.toString(accountNumber) + ".txt");
        List<String> lines = Files.readAllLines(path);
        lines.set(lineNumber - 1, data);
        Files.write(path, lines);
    }
    public void updateTransactionHistory(int accountNumber, String data) throws IOException {
        String tH = transactionHistory(accountNumber, false);
        String fileName = Integer.toString(accountNumber) + ".txt";
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
        String fileName = Integer.toString(accountNumber) + ".txt";
        String line = Files.readAllLines(Paths.get(fileName)).get(4);
        System.out.println("Your current balance is: " + line);
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
