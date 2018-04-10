package edu.btp400.w2017.common;
import java.io.Serializable;
/** The implementation of my account consists of the two constructors getters for the 
 * variables including the first and last name, toString() equals and hashcode methods */
import java.math.*;

public class Account implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name,fName,lName;
	private String Accnum;
	private BigDecimal Balance;
	private double Tax;
	public double getTax() {
		return Tax;
	}
	public void setTax(double tax)
	{
		Tax = tax;
	}
	public Account() {
		// TODO Auto-generated constructor stub
		//Initialize variables 
		Name = "";
		fName = "";
		lName = ""; 
		Accnum = null;
	}
	public Account(String name,String accnum, double balance)
	{
		if(balance < 0)
			balance = 0;
		//Assign values to variables
		if(name == null)
		{
			this.Name = "";
			this.fName = "";
			this.lName = "";
		}
		else
			this.Name = name.trim();
		//check if null for values
		if(accnum == null)
			this.Accnum = null;
		else
			this.Accnum = accnum.trim();
		//Do complicated math for if object is double
		this.setBalance(new BigDecimal(balance));
		
		if(!Name.equals("") && Name.contains(","))
		{
		String[] temp = Name.split(",");
		
			if(temp.length <= 1)
			{
				Name = temp[0];
				lName = "";
				fName = temp[0];	
			}
			else if(temp.length == 2){
				fName = temp[0].trim();
				lName = temp[1].trim();
			}
		}
		else{
			Name = "";
			fName = "";
			lName = "";
		}
	}
	
	public boolean withdraw(BigDecimal amount)
	{
		
		if(!(Balance.doubleValue() < amount.doubleValue()) && amount.doubleValue() > 0){
			Balance = Balance.subtract(amount);
			System.out.println(new String ("Withdrawed $"+amount.doubleValue()));
		}
		else
			return false;
		return true;
	}
	public void deposit(BigDecimal amount){
		if(amount.doubleValue() > 0){
			System.out.println(new String ("Deposited! $" + amount.doubleValue()));
			Balance = Balance.add(amount);
		}
		System.out.println(this);
	}
	
	public String getFName()
	{
		return fName;
	}
	public String getLName()
	{
		//Used trim and split to separate names
		return lName;
	}
	public double getBalance() {
		return Balance.doubleValue();
	}
	public void setBalance(BigDecimal balance) {
		Balance =new BigDecimal(balance.doubleValue());
	}
	public String getAccnum() {
		return Accnum;
	}
	public void setAccnum(String accnum) {
		Accnum = accnum;
	}
	@Override
	public String toString()
	{
		//override the tostring method
		return "Name: " + getFName() + ","
				+ getLName()
				+ " \nAccnum: " + Accnum
				+ "\nCurrent Balance: $" + Balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Accnum == null) ? 0 : Accnum.hashCode());
		result = prime * result + ((Balance == null) ? 0 : Balance.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(Tax);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Accnum == null) {
			if (other.Accnum != null)
				return false;
		} else if (!Accnum.equals(other.Accnum))
			return false;
		if (Balance == null) {
			if (other.Balance != null)
				return false;
		} else if (!Balance.equals(other.Balance))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (Double.doubleToLongBits(Tax) != Double.doubleToLongBits(other.Tax))
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		return true;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public void calculateTax(double taxRate) {
		Tax = taxRate;
		System.out.println(createTaxStatement());
		
	}
	public double getTaxAmount() {
		return Balance.doubleValue() * Tax;
	}
	public String createTaxStatement() {
		return "Tax Rate: "+ Tax*100+"%" + "\nAccount Number: "+ Accnum + "\nInterest income: $"
				+Math.round(Balance.doubleValue() * (1+Tax)) +
				"\nAmount of tax : $"+ getTaxAmount();
	}

}
