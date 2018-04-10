package edu.btp400.w2017.client;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import edu.btp400.w2017.common.Account;
import edu.btp400.w2017.common.RemoteBank;
import edu.btp400.w2017.server.FinancialApp;

public class RMIClient {
	static String url = "rmi://localhost:5678/";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println( "Running a client application..." );

			System.out.println( "these services have been registered with the RMI Registry" );

			String names[];
		try {
				names = Naming.list( "rmi://localhost:5678" );
			
			for(int k=0; k < names.length; k++)
			{
				System.out.println( "...... " + names[k] );
			}
		
			System.out.println( "Bind object variables to remote objects..." );

		
			RemoteBank b1 = (RemoteBank) Naming.lookup( url + "LiamsBank" );
			
			FinancialApp a1 = new FinancialApp(b1);
			while(true)
				try{
					a1.choiceBlock(a1.menuChoice());
				}
			catch(Exception obj)
			{
				StringBuffer a = new StringBuffer("You caused exception " + obj.toString()
					+ "\nRerunning script for you");
					System.out.println(a);
				}
				finally{
						a1.choiceBlock(a1.menuChoice());
				}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

	}

}
}
