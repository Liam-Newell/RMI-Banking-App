package edu.btp400.w2017.common;

public interface Taxable {
	public void calculateTax( double taxRate );
    public double getTaxAmount( );
    public String createTaxStatement( );

}
