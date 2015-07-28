package edu.cornell.mhk98.number;

public class ComplexNumber{

    private double re;
    private double im;

    public ComplexNumber(double a, double b){
	re = a;
	im = b;
    }

    public double modulus(){
	return Math.sqrt(Math.pow(re,2) + Math.pow(im, 2));
    }

    public double arg(){
	return Math.atan(im / re);
    }

    public double real(){
	return re;
    }

    public double imaginary(){
	return im;
    }

}
