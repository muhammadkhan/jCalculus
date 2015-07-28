package edu.cornell.mhk98.function;

public class ConstantFunction extends Polynomial{

    private double c;

    public ConstantFunction(double c){
	super(new double[]{c}, new int[]{0});
	this.c = c;
    }

    public double getConstant(){
	return c;
    }
}
