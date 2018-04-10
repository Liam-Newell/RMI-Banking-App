package edu.btp400.w2017.common;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class Bank extends UnicastRemoteObject 
implements RemoteBank{
	
	private static final long serialVersionUID = 1L;
	private String Name;
	private int numAcc,accInd;
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	public Bank() throws RemoteException{
		// TODO Auto-generated constructor stub
		//Call the two argument constructor
		
		this("Seneca@York",0);
	}
	public Bank(String name,int numAcc) throws RemoteException
	{
		//Regular initialization of two argument constructor
		this.Name = name;
		this.numAcc = numAcc;
	}
	
	public String accDisplay() throws RemoteException
	{
		StringBuffer buffer = new StringBuffer("");
		for(int i = 0; i < accounts.size(); i++)
		{
			//Create a buffer to be returned with the accounts contained in bank
			if(accounts.get(i) != null)
			{
			buffer.append("\n");
			buffer.append(i+1);
			//Convert them to string with the already overloaded toString method 
			buffer.append(".\n" + accounts.get(i).toString());
			buffer.append("\n~~~");
			}
		}
		return buffer.toString();
	}
	
	public boolean addAccount(Account src) throws RemoteException
	{
		ArrayList<Account> tester = this.searchByAccountName(src.getName());
		int count = 0;
		if(tester != null)
			for(int i = 0; i < tester.size(); i++)
				if(tester.get(i).getClass() == src.getClass())
					count++;
				
		if(count == 0)
		{

			System.out.println("Account Added!");
			accounts.add(src);
			accInd++;
			return true;
		}
		else
			System.out.println("Account for that name already exists!");
		return false;
	}
	
	public Account[] removeAccount(ArrayList<Account> srch) throws RemoteException
	{
		System.out.println("~~~~~~~~~~~~~~~\nRemoval Process\n~~~~~~~~~~~~~~~");
		ArrayList<Account> ref = new ArrayList<Account>();
		int found = 0;
		for(int i = 0; i < srch.size(); i++)
		{//Loop till the searched string is found in the account array
			for(int j = 0; j < accounts.size(); j++){
				if(srch.get(i).getAccnum().equals(accounts.get(j).getAccnum()))
				{
					//assign ref the account so we can return it later
					ref.add(accounts.get(j));
					accounts.remove(j);
					//Increase found int so we can see how many account are removed in total
					found++;
					
					//Decrease the index of the accounts object to verify how many available there now is
					accInd--;
					//Because the whole array has shifted we must shift back the master loop to check the same spot for a found searched string
					j--;
				}
			}
				
			
		}
		//echo out all many account were removed
		System.out.printf("Found "+found+" Accounts To Remove\nLast Removed Was:\n");
		return ref.toArray(new Account[ref.size()]);
	}
	public ArrayList<Account> searchByAccountName(String name) throws RemoteException
	{
		ArrayList<Account> result = new ArrayList<Account>();
		int k = 0;
		System.out.println("~~~~~~~~~~~~~~~\nSearch Account Name Process\n~~~~~~~~~~~~~~~");
		for(int i = 0; i < accInd;i++)
		{
			if(accounts.get(i).getName().toLowerCase().equals(name.toLowerCase()))
			{
				result.add(accounts.get(i));
				k++;
			}
		}
		System.out.printf("Found " +k+ " Matches\n");
		//fun but not that useful 
		return k != 0 ? result : null;
	}
	public Account searchByName(String accnum) throws RemoteException
	{
		System.out.println("~~~~~~~~~~~~~~~\nSearch Process\n~~~~~~~~~~~~~~~");
		Account[] result = new Account[accInd];
		int k = 0;
		//for the amount of indexes currently being used
		for(int i = 0; i < accInd;i++)
		{
			//check the balances for equivalents 
			if( accounts.get(i).getAccnum().equals(accnum))
			{
				result[k] = accounts.get(i);
				k++;
			}

		}
		System.out.printf("Found " +k+ " Matches\n");
		//fun but not that useful 
		return result != null ? result[0] : null;
	}
	public Account searchByBalance(String balance)throws RemoteException
	{
		System.out.println("~~~~~~~~~~~~~~~\nSearch Process\n~~~~~~~~~~~~~~~");
		Account[] result = new Account[accInd];
		int k = 0;
		//for the amount of indexes currently being used
		boolean isdouble = isDouble(balance);
		for(int i = 0; i < accInd;i++)
		{
			//check the balances for equivalents 
			if(isdouble)
				if(accounts.get(i).getBalance()==Double.parseDouble(balance))
				{
									
					result[k] = accounts.get(i);
					k++;
				}
			
		}
		System.out.printf("Found " +k+ " Matches\n");
		//fun but not that useful 
		return result != null ? result[0] : null;
	}
	
	
	
	public boolean isDouble(String number){
		try
		{
			//Check if is a double
			Double.parseDouble(number);
			return true;
		}
		catch(NumberFormatException e)
		{
			//Not a double
			return false;
		}
	}
	
	public Account [] getAllAccounts() throws RemoteException
	{
		System.out.println("****************\nGet all Accounts\n****************");
		Account [] arra = new Account[accounts.size()];
		if(accounts.size() > 0){
			for(int i = 0; i < accounts.size(); i++)
			{
				arra[i] = accounts.get(i);
			}	
		}
		return arra;
	}
	@Override
	public String toString()
	{
			
				try {
					return "~~~~~~~~~~~~~~~~~~~~~~\nName of Bank : " + Name
							+ "\nNumber of Accounts Opened : " + accInd
							+ "\nThe Max Number of Accounts : " + numAcc
							+ "\n~~~~~~~~~~~~~~~~~~~~~~" + accDisplay();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "";
			
	}
	public String getName() throws RemoteException
	{
		return Name;
	}
	public int getNumAcc() throws RemoteException{
		return numAcc;
	}
	public int getAccInd() throws RemoteException{
		return accInd;
	}
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	@Override
	public boolean equals(Object src){
		//overloaded equals
		System.out.println("~~~~~~~~~~~~~~~\nEquals Process\n~~~~~~~~~~~~~~~");
		if (src.getClass().equals(this.getClass())){
			
		}
		else{
			return false;
		}
		//if(src.Name.equals(Name) && src.Balance == Balance && src.Accnum == Accnum)
		//checking the overall outputed string values of both objects we are able to determine with certainty they are exactly the same
		if(src.toString().toLowerCase().equals(this.toString().toLowerCase())){
			//Pass parameters
			System.out.println("Passed");
			return true;}
		else{
			System.out.println("FAILED!\nThey're not Equal!");
			return false;}
	}
	@Override
	public String getBankName() throws RemoteException {
		// TODO Auto-generated method stub
		return Name;
	}
	@Override
	public String displayAllAccounts() throws RemoteException {
		// TODO Auto-generated method stub
		return this.toString();
	}
	
	

}
