package edu.btp400.w2017.common;
import java.math.BigDecimal;
import java.util.Arrays;
public class Chequing extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double servicechg;
	private int maxtrans,index,chgs;
	private double transactions[];
	public Chequing() {
		// TODO Auto-generated constructor stub
		super();
		servicechg = 0.25;
		maxtrans = 3;
		transactions = new double[maxtrans];
	}
	public Chequing(String name, String accnum, double balance,double schg, int max){
		super(name,accnum,balance);
		servicechg = schg;
		maxtrans = max;
		transactions = new double[maxtrans];
	}
	@Override
	public String toString() {
		return super.toString() +"\nType: Chequing \nservicechg: $" + servicechg + ", \nmaxtrans: " + maxtrans + "\ntranactions: "
				+ Arrays.toString(transactions) + "\nFinal Balance: $" + getBalance() ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxtrans;
		long temp;
		temp = Double.doubleToLongBits(servicechg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(transactions);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chequing other = (Chequing) obj;
		if (maxtrans != other.maxtrans)
			return false;
		if (Double.doubleToLongBits(servicechg) != Double.doubleToLongBits(other.servicechg))
			return false;
		if (!Arrays.equals(transactions, other.transactions))
			return false;
		return true;
	}
	@Override
	public boolean withdraw(BigDecimal amount)
	{
		if(index < maxtrans || amount.doubleValue() < 0){
		if(super.withdraw(amount)){
			transactions[index++] = amount.doubleValue() * -1;
			this.chgs++;
			return true;
		}}
		else{
			System.out.println("Out of transactions");
		}
		return false;
	}
	@Override
	public void deposit(BigDecimal amount){
		if(index < maxtrans || amount.doubleValue() < 0){
			super.deposit(amount);
			this.chgs++;
			transactions[index++] = amount.doubleValue();
		}
		else{
			System.out.println("Out of transactions");
		}
		
	}
	@Override
	public double getBalance() {
		return super.getBalance() - (servicechg * chgs);
	}
	

}
