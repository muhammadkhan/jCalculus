package edu.cornell.mhk98.number;

public class RealNumber extends ComplexNumber{

    private double value;

    public RealNumber(double c){
	super(c, 0.0);
	value = c;
    }

}
