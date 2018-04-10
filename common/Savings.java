package edu.btp400.w2017.common;

import java.math.BigDecimal;

public class Savings extends Account implements Taxable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double interest;
	private double annualRate;
	public Savings() {
		// TODO Auto-generated constructor stub
		super();
		this.annualRate = 0.001;
	}
	public Savings(String name, String accnum, double balance, double interest){
		super(name,accnum,balance);
		this.annualRate = interest;
	}
	
	@Override
	public String toString(){
		return super.toString() + "\nType: Savings\n" + 
	"Interest Income: " + ( getBalance() - super.getBalance() ) 
	+ "\nTax: %" + (new BigDecimal(1.00)
	.add(new BigDecimal(annualRate)))
	.doubleValue()
	+  "\nFinal Balance: %" + getBalance();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(interest);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Savings other = (Savings) obj;
		if (Double.doubleToLongBits(interest) != Double.doubleToLongBits(other.interest))
			return false;
		return true;
	}
	@Override
	public double getBalance(){
		return new BigDecimal(super.getBalance())
				.multiply(new BigDecimal(1.00)
				.add(new BigDecimal(annualRate)))
				.doubleValue();
	}
	@Override
	public String createTaxStatement() {
		// TODO Auto-generated method stubif
		if(getBalance() > 50)
			return "\nType: Savings\n" + "Interest Income: " 
				+ getTaxAmount() + "\nFinal Balance: " + getBalance();
		else
			return "\nBalance of this savings account is under $50 therefore cannot be taxed";
	}

}
