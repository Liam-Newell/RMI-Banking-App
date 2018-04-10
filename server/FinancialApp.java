package edu.btp400.w2017.server;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import edu.btp400.w2017.common.Account;
import edu.btp400.w2017.common.Bank;
import edu.btp400.w2017.common.Chequing;
import edu.btp400.w2017.common.GIC;
import edu.btp400.w2017.common.RemoteBank;
import edu.btp400.w2017.common.Savings;

import java.math.*;
import java.rmi.RemoteException;
import java.lang.StringBuffer;
/**
 * @author Liam Newell
 * <br>
 * @Purpose
 * to make a banking app
 *
 */
public class FinancialApp {
	public static RemoteBank bank;
	public static Scanner answer = new Scanner(System.in);
	public FinancialApp(RemoteBank b1) {
		// TODO Auto-generated constructor stub
		bank = b1;
	}
	/**
	 * Loads bank then calls the choiceBlock to make a menu
	 * @throws RemoteException 
	 */
//	public static void main(String[] args) throws RemoteException {
//		bank = new Bank();
//		//loadBank(bank);
//		while(true)
//		try{
//			choiceBlock(menuChoice());
//		}
//		catch(Exception obj)
//		{
//			StringBuffer a = new StringBuffer("You caused exception " + obj.toString()
//			+ "\nRerunning script for you");
//			System.out.println(a);
//		}
//		finally{
//				choiceBlock(menuChoice());
//		}
//
//	}
	
	/**
	 * @param a : used for selection;
	 * Chooses the function to use for the operations a
	 * @throws RemoteException 
	 */
	public static void choiceBlock(int a) throws RemoteException{
		switch(a){
		case 1: openAccount(bank);
				break;
		case 2: closeAccount();
				break;
		case 3: deposit();
				break;
		case 4: withdraw();
				break;
		case 5: displayAccounts();
				break;
		case 6: displayTaxStatement();
				break;
		case 7: Exit();
				break;
		default:
			menuChoice();
		}
	}
	/**
	 * @param Bank : 
	 * <br>Opens two Savings accounts, two Chequing 
accounts and two GIC accounts with the bank
	 * @throws RemoteException 
	 */
	public static void loadBank(Bank bank) throws RemoteException
	{
		Object arra[] = new Object[6];
		arra[0] = new Savings("Doe,John","a14506",6660,0.15);
		arra[1] = new Savings("Ryan,Mary","a15606",16870,0.15);
		arra[2] = new Chequing("Doe,John", "b47645",6890,2.50,5);
		arra[3] = new Chequing("Ryan,Mary", "b47536",15400,3.50,3);
		arra[4] = new GIC("Doe,John","c14506",60000,2,1.50);
		arra[5] = new GIC("Ryan,Mary","c14544",15000,4,2.50);
		
		for(Object a : arra){
			bank.addAccount((Account)a);
		}
	}
	/**
	 * @return Menu selection back to main; rejects any invalid number
	 */
	public static int menuChoice()
	{
		
		while(true){
		int ans = 0;
		StringBuffer display = new StringBuffer("");
		display.append("1. Open an account"
				+ "\n2. Close an account"
				+ "\n3. Deposit money"
				+ "\n4. Withdraw money"
				+ "\n5. Display Accounts"
				+ "\n6. Display a tax statement"
				+ "\n7. Exit");
		System.out.println(display);
		answer = new Scanner(System.in);
		while(true){
		if(answer.hasNextInt()){
			ans = answer.nextInt();
		}
		if(ans > 0 || ans < 8)
			return ans;
			break;
		}
		return ans;
		}
	}
	/**
	 * @param account
	 * Can easily be called to find the accounts you're looking for 
	 * @throws RemoteException 
	 */
	public static ArrayList<Account> chooseAccount() throws RemoteException{
		ArrayList<Account> found = new ArrayList<Account>();
		StringBuffer display = new StringBuffer("");
		int choice=0;
		display.append("1. Display by Name"
				+ "\n2. Search by accnum or balance");
		System.out.println(display);
		answer = new Scanner(System.in);
		if(answer.hasNextInt()){
			choice = answer.nextInt();
			switch(choice){
			case 1: {
				StringBuffer buffer = new StringBuffer("");
				buffer.append(bank.getBankName() + "\nChoose the name of the account you want");
				System.out.println(buffer);
				answer = new Scanner(System.in);
				String ans = null;
				if(answer.hasNext())
					ans = answer.next();
				 found = bank.searchByAccountName(ans);
				break;
			}
			case 2: {
				StringBuffer buffer = new StringBuffer("");
				buffer.append(bank.getBankName() + "\nChoose the Account number,final balance or name of the account you want");
				System.out.println(buffer);
				answer = new Scanner(System.in);
				String ans = null;
				if(answer.hasNext())
					ans = answer.next();
					found.add(bank.searchByBalance(ans));
				break;
			}
			}
		}
		return found;
	}
	/**
	 * Displays the account chosen in chooseAccount()
	 * @throws RemoteException 
	 */
	public static void displayAccount() throws RemoteException{
		System.out.println(chooseAccount());
	}
	/**
	 * A void to display one more many accounts with customized calls to do so
	 * @throws RemoteException 
	 */
	public static void displayAccounts() throws RemoteException
	{
		StringBuffer display = new StringBuffer("");
		int choice=0;
		display.append("1. Display One Account"
				+ "\n2. Display All Accounts");
		System.out.println(display);
		answer = new Scanner(System.in);
		if(answer.hasNextInt()){
			choice = answer.nextInt();
			switch(choice){
			case 1: displayAccount();
					break;
			case 2: {
				String dy = "~~~~~~~~~~~~~~~~~~~~~~\nName of Bank : " + bank.getBankName()
				+ "\nNumber of Accounts Opened : " + bank.getAccInd()
				+ "\n~~~~~~~~~~~~~~~~~~~~~~" + bank.accDisplay();
				System.out.println(dy);
					break;
			}
			}
		}
		
	}
	/**
	 * @param bank2
	 * <br>
	 * Using the bank object we open a new account to transfer a newly created account into
	 *<br>
	 *The choice block allows for many option which reset with invalid inputs	 *
	 */
	public static void openAccount(RemoteBank bank2)
	{
	
		//This determines the account type
		int choice = 0;
		String ans = "";
		System.out.println("Please enter the account type (SAV/CHQ/GIC/EXIT):");
		answer = new Scanner(System.in);
		if(answer.hasNext()){
			ans = answer.next();
			if(ans.toUpperCase().equals("SAV"))
				choice = 1;
			if(ans.toUpperCase().equals("CHQ"))
				choice = 2;
			if(ans.toUpperCase().equals("GIC"))
				choice = 3;
			if(ans.toUpperCase().equals("EXIT"))
				return;
		}
		else{
			System.out.println("User inputed wrong account type RESTARTING");
			openAccount(bank2);
		}
		System.out.println("Please enter account information at one line"
				+ "\n(e.g. Doe,John;A1234;1000.00;3.65):\n");
		answer = new Scanner(System.in);
		if(answer.hasNext()){
			ans = answer.next();
			if(ans.split(";").length > 3){
				String[] splitput = ans.split(";");
				try{
					String name = splitput[0].trim();
					String accnum = splitput[1].trim();
					double balance = Double.parseDouble(splitput[2].trim());
					switch(choice){
						case 1:{
							double interest = Double.parseDouble(splitput[3].trim());
							if(!(bank2.addAccount(new Savings(name,accnum,balance,interest))))
								System.out.println("Account for that name already exists!");
							break;
						}
								
						case 2:{
							double schg = Double.parseDouble(splitput[3].trim());
							int max = Integer.parseInt(splitput[4].trim());
							if(!(bank2.addAccount(new Chequing(name,accnum,balance,schg,max))))
								System.out.println("Account for that name already exists!");
							break;
						}
						case 3:{
							int period = Integer.parseInt(splitput[3].trim());
							double rate = Double.parseDouble(splitput[4].trim());
							if(!(bank2.addAccount(new GIC(name,accnum,balance,period,rate))))
									System.out.println("Account for that name already exists!");
							break;
						}
					
					}
				}
				catch (Exception exp){
					StringBuffer a = new StringBuffer("Account could not be added with current input"
					+ "\nYou caused exception " + exp.toString()
					+ "\nRerunning script for you");
					System.out.println(a);
				}
				finally{
					openAccount(bank2);
				}
			}
			else{
				openAccount(bank2);
			}
		}
		
	}
	/**
	 * Using the already implemented choose account function get the accnum
	 * and return it to the removeAccount function from bank 
	 */
	public static void closeAccount()throws RemoteException{
		Account[] a = bank.removeAccount(chooseAccount());
		System.out.println("Account Removed"
				+ "\n"
				+ Arrays.toString(a));
		
	}
	/**
	 * Simple question answer for deposit<br>
	 * @throws RemoteException 
	 * @see Includes scanner and left hand initialization of BigDecimal object
	 */
	public static void deposit() throws RemoteException{
		System.out.println("How Much Do You Want To Deposit?");
		answer = new Scanner(System.in);
		double dep = answer.nextDouble();
		chooseAccount().get(0).deposit(new BigDecimal(dep));
	}
	/**
	 * Simple question answer for withdraw<br>
	 * @throws RemoteException 
	 * @see Includes scanner and left hand initialization of BigDecimal object
	 */
	public static void withdraw() throws RemoteException{
		System.out.println("How Much Do You Want To Withdraw?");
		answer = new Scanner(System.in);
		double wit = answer.nextDouble();
		chooseAccount().get(0).deposit(new BigDecimal(wit));
	}
	
	/**
	 * creates a tax statement for each account chosen
	 * @throws RemoteException 
	 */
	public static void displayTaxStatement() throws RemoteException{
		System.out.println(chooseAccount().get(0).createTaxStatement());
	}
	public static void Exit(){
		System.out.println("Thank You!");
		System.exit(0);
	}
	
}
