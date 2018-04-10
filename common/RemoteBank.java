package edu.btp400.w2017.common;

import java.rmi.*;
import java.util.ArrayList;

public interface RemoteBank  extends Remote {
	public boolean addAccount(Account src) throws RemoteException;
	public Account[] removeAccount(ArrayList<Account> arrayList)throws RemoteException;
	public Account searchByBalance(String accnum)throws RemoteException;
	public Account searchByName(String balance)throws RemoteException;
	public String getBankName() throws RemoteException;
	public ArrayList<Account> searchByAccountName(String ans) throws RemoteException;
	public String displayAllAccounts() throws RemoteException;
	public String getName() throws RemoteException;
	public int getNumAcc() throws RemoteException;
	public int getAccInd() throws RemoteException;
	
	public String accDisplay() throws RemoteException;
}
