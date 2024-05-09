import java.io.*;
import java.util.*;
class Account
{
    static int acc_no = 6789;
    String acc_hname;
    double balance;
    int pin;
    String user_id;
    int acc_num;
    void createacc() 
    {
        acc_num = acc_no;
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter account holder name - ");
        acc_hname = obj.nextLine();
        System.out.println("Enter Username - ");
        user_id = obj.nextLine();
        int length_pin = 0;
        do 
        {
            System.out.println("Enter 4 digit pin - ");
            pin = obj.nextInt();
            length_pin = String.valueOf(pin).length();
        } 
        while (length_pin != 4);
        System.out.print("Enter initial deposit - ");
        balance = obj.nextDouble();
        System.out.println("Account Successfully Created");
        System.out.println("Account Details - \n" + "Account number - " + acc_num + "\nAccount Holder Name - "
                + acc_hname + "\nBalance - " + balance);
        String fileName = acc_no + ".txt"; // Create a file with the account number
        File file = new File(fileName);
        try 
        {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("Account Created\n");
            writer.write("Account Number- " + acc_no + "\n");
            writer.write("USER ID- " + user_id + "\n");
            writer.write("Account Holder Name- " + acc_hname + "\n");
            writer.write("PIN- " + pin + "\n");
            writer.write("Balance- " + balance + "\n");
            writer.write("Date- " + new Date() + "\n\n");
            writer.close();
        } 
        catch (IOException e) 
        {
            System.out.println("Error occured while creating file" + fileName);
            e.printStackTrace();
        }
        try 
        {
            Thread.sleep(4000);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        acc_no++;
    }
}
class ATM 
{
    void withdraw(Account acc) 
    {
        Scanner obj = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Withdraw Money Zone\n");
        System.out.println("Enter amount - ");
        double amount = obj.nextDouble();
        if (amount % 100 == 0) 
        {
            if (acc.balance >= amount) 
            {
                acc.balance -= amount;
                System.out.println("Transaction in Process");
                try 
                {
                    String fileName = acc.acc_num + ".txt";
                    FileWriter fileWriter = new FileWriter(fileName, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("Date- " + new Date() + "\n");
                    bufferedWriter.write("Withdrawl- " + amount + "\n");
                    bufferedWriter.write("Account number- " + acc.acc_num + "\n");
                    bufferedWriter.write("Remaining Balance- " + acc.balance + "\n\n");
                    bufferedWriter.close();
                    fileWriter.close();
                } 
                catch (IOException e) 
                {
                    System.out.println("Error occured while writing in the file");
                    e.printStackTrace();
                }
                System.out.println("Thank You for trusting our bank!");
                try 
                {
                    Thread.sleep(5000);
                } 
                catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } 
            else 
            {
                System.out.print("Insufficient Funds");
            }
        } 
        else 
        {
            System.out.println("Amount not in multiples of 100!");
            System.out.println("Try again!");
        }
    }
    void deposit_transfer(Account acc, double amount) 
    {
        acc.balance += amount;
        try 
        {
            String fileName = acc.acc_num + ".txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Deposit- " + amount + "\n");
            bufferedWriter.write("Date- " + new Date() + "\n");
            bufferedWriter.write("Account number- " + acc.acc_num + "\n");
            bufferedWriter.write("Remaining Balance- " + acc.balance + "\n\n");
            bufferedWriter.close();
            fileWriter.close();
        } 
        catch (IOException e) 
        {
            System.out.println("Error occured while writing to the file");
            e.printStackTrace();
        }
    }
    void deposit(Account acc) 
    {
        Scanner obj = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Money Deposit Zone ");
        System.out.println("Enter amount for deposit");
        double amount = obj.nextDouble();
        acc.balance += amount;
        System.out.print("\033[H\033[2J");
        System.out.flush();
        try 
        {
            String fileName = acc.acc_num + ".txt";
            System.out.println("The File Name - " + fileName);
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Deposit- " + amount + "\n");
            bufferedWriter.write("Date- " + new Date() + "\n");
            bufferedWriter.write("Account number- " + acc.acc_num + "\n");
            bufferedWriter.write("Remaining Balance- " + acc.balance + "\n\n");
            bufferedWriter.close();
            fileWriter.close();
        } 
        catch (IOException e) 
        {
            System.out.println("Error occured while writing to the file");
            e.printStackTrace();
        }
        System.out.println("Processing!");
        try 
        {
            Thread.sleep(5000);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Transaction completed!");
        System.out.println("Thank you for choosing us!");
        System.out.println("--Going back to login page--");
        try 
        {
            Thread.sleep(3000);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
    void transfer(Account acc1, Account acc2, double amount) 
    {
        if (acc1.balance >= amount) 
        {
            acc1.balance -= amount;
            ATM a = new ATM();
            a.deposit_transfer(acc2, amount);
            try 
            {
                String fileName = acc1.acc_num + ".txt";
                FileWriter fileWriter = new FileWriter(fileName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Transferred- " + amount + "\n");
                bufferedWriter.write("Date- " + new Date() + "\n");
                bufferedWriter.write("Account number- " + acc1.acc_num + "\n");
                bufferedWriter.write("Remaining Balance- " + acc1.balance + "\n\n");
                bufferedWriter.close();
                fileWriter.close();
            } 
            catch (IOException e) 
            {
                System.out.println("Error occured while writing to the file");
                e.printStackTrace();
            }
            System.out.println("Processing!");
            try 
            {
                Thread.sleep(5000);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Transfer completed!");
            System.out.println("--Going back to login page--");
            try 
            {
                Thread.sleep(5000);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }
    void display(Account acc) 
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Displaying account details");
        try 
        {
            Thread.sleep(2000);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        String file_name = String.valueOf(acc.acc_num) + ".txt";
        File file = new File(file_name);
        try 
        {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) 
            {
                System.out.println(line);
            }
            bufferedReader.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found - " + e.getMessage());
        } 
        catch (IOException e) 
        {
            System.out.println("Error occured reading file - " + e.getMessage());
        }
        System.out.println("Screen will disappear after 30 seconds.");
        try 
        {
            Thread.sleep(30000);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
    void quit() 
    {
        System.out.print("Thank you for trusting us!\n");
        return;
    }
}
class AllowATM 
{
    int acc_search(Account[] ac, String user_id) 
    {
        for (int i = 0; i < 5; i++) 
        {
            if (Objects.equals(user_id, ac[i].user_id))
                return i;
        }
        return -1;
    }
    int acc_search(Account[] ac, int account_number) 
    {
        for (int i = 0; i < 5; i++) 
        {
            if (account_number == ac[i].acc_num)
                return i;
        }
        return -1;
    }
    void trial(Account[] ac) 
    {
        Scanner obj = new Scanner(System.in);
        System.out.print("\n\n Welcome!\n");
        System.out.println("Enter user_id");
        String user_id = obj.nextLine();
        int i = acc_search(ac, user_id);
        if (i == -1) 
        {
            System.out.println("Account not registered");
            System.out.println("Create your account");
            try 
            {
                Thread.sleep(3000);
            } catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
            return;
        } 
        else 
        {
            System.out.println("Enter your PIN");
            int pin = obj.nextInt();
            if (pin == ac[i].pin) 
            {
                System.out.println("Select the option given below - ");
                System.out.println("\nWithdraw - 1\nDeposit - 2\nAccount Transfer - 3\nDisplay account details - 4\nQuit - 5");
                int ch;
                ATM atm = new ATM();
                ch = obj.nextInt();
                switch (ch) 
                {
                    case 1 -> atm.withdraw(ac[i]);
                    case 2 -> atm.deposit(ac[i]);
                    case 3 -> {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.print("Enter account number to transfer funds - ");
                        int acc_transfer = obj.nextInt();
                        int t = acc_search(ac, acc_transfer);
                        if (t == -1) 
                        {
                            System.out.println("Account not registered");
                            return;
                        } 
                        else 
                        {
                            System.out.println("Enter amount for transferring");
                            double amount = obj.nextDouble();
                            atm.transfer(ac[i], ac[t], amount);
                        }
                    }
                    case 4 -> atm.display(ac[i]);
                    case 5 -> atm.quit();
                }
            } 
            else 
            {
                System.out.println("Incorrect attempt!\n Try again");
                try 
                {
                    Thread.sleep(3000);
                } 
                catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
                return;
            }
        }
    }
}
public class Main 
{
    public static void main(String args[]) 
    {
        Scanner obj = new Scanner(System.in);
        Account[] ac = new Account[5];
        for (int i = 0; i < 5; i++) 
        {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Creating account zone - \n");
            ac[i] = new Account();
            ac[i].createacc();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        AllowATM obj1 = new AllowATM();
        for (; ; ) 
        {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            obj1.trial(ac);
        }
    }
}