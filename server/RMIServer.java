package edu.btp400.w2017.server;
import edu.btp400.w2017.common.Account;
import edu.btp400.w2017.common.Bank;
import edu.btp400.w2017.common.Chequing;
import edu.btp400.w2017.common.GIC;
import edu.btp400.w2017.common.RemoteBank;
import edu.btp400.w2017.common.Savings;

import java.rmi.Naming; 
import java.rmi.RemoteException; 

public class RMIServer
{
	private void loadBank(RemoteBank bankerino) throws RemoteException{
		Object arra[] = new Object[6];
		arra[0] = new Savings("Doe,John","a14506",6660,0.15);
		arra[1] = new Savings("Ryan,Mary","a15606",16870,0.15);
		arra[2] = new Chequing("Doe,John", "b47645",6890,2.50,5);
		arra[3] = new Chequing("Ryan,Mary", "b47536",15400,3.50,3);
		//arra[4] = new GIC("Doe,John","c14506",60000,2,1.50);
		arra[4] = new GIC("Ryan,Mary","c14544",15000,4,2.50);
		arra[5] = new Savings("Liam,Newell","g50235",500,1.5);
		for(Object a : arra){
			bankerino.addAccount((Account)a);
		}
	}
	protected RMIServer() throws RemoteException {
		RemoteBank rb = new Bank();
		loadBank(rb);
		
		
		
		
		System.out.println("Accounts Created:");

		
		
		System.out.println( "Binding remote objects to rmi registry" );
		
		java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.createRegistry(5678);
			
		registry.rebind( "LiamsBank", rb );
		
		System.out.println( "Starting a server...\nWait for clients" );

		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		new RMIServer();
		System.out.println("System is running");
	}

}
