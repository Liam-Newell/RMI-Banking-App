package edu.btp400.w2017.common;
import java.math.BigDecimal;

public class GIC extends Account implements Taxable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int inPeriod;
	private double inRate;
	public GIC() {
		// TODO Auto-generated constructor stub
		super();
		inPeriod = 1;
		inRate = 0.0125;
	}
	public GIC(String name,String accnum, double balance, int period, double rate){
		super(name,accnum,balance);
		inPeriod = period;
		inRate = rate;
	}
	@Override
	public String toString() {
		return super.toString() 
		+ "\nType: GIC\nPeriod Of Investment: " + inPeriod 
		+ "yrs\nAnnual Interest Rate: %" + inRate+1
		+ "\nInterest Income: " + (this.getBalance() - super.getBalance())
		+ "\nBalance At Maturity: " + this.getBalance();
	}
	@Override
	public String createTaxStatement(){
		return "\nPeriod Of Investment: " + inPeriod 
		+ "yrs\nAnnual Interest Rate: %" + inRate+1
		+ "\nInterest Income: " + (this.getBalance() - super.getBalance())
		+ "\nBalance At Maturity: " + this.getBalance();
	}
	
	@Override
	public double getBalance(){
		double balance = new BigDecimal(super.getBalance())
		.multiply(new BigDecimal(inRate)
				.pow(inPeriod)).doubleValue();

		return Math.round(balance);
	}
	@Override
	public boolean withdraw(BigDecimal amount){
		if(super.withdraw(amount)){
			return true;
		}
		else
			return false;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + inPeriod;
		long temp;
		temp = Double.doubleToLongBits(inRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		GIC other = (GIC) obj;
		if (inPeriod != other.inPeriod)
			return false;
		if (Double.doubleToLongBits(inRate) != Double.doubleToLongBits(other.inRate))
			return false;
		return true;
	}

}
