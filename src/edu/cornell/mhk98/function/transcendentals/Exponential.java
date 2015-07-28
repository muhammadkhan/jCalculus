package edu.cornell.mhk98.function.transcendentals;

import edu.cornell.mhk98.calculus.Differentiable;
import edu.cornell.mhk98.calculus.Integrable;
import edu.cornell.mhk98.function.Polynomial;
import edu.cornell.mhk98.function.Transcendental;
import edu.cornell.mhk98.util.JCalculus;

public class Exponential implements Transcendental, Differentiable<Exponential>, Integrable<Exponential> {

    private static final int TAYLOR_LIM = 8;

    private Function raisedFn;

    public Exponential(Function r){
	raisedFn = r;
    }

    public Exponential differentiate(){
	Polynomial.multiply(raisedFn.differentiate(), this);
    }

    public Function getExponentFunction(){
	return raisedFn;
    }

    public double apply(double x){
	return Math.exp(x);
    }

    public Polynomial getTaylorApprox(int order){
	double[] cs = new double[TAYLOR_LIM];
	int[] exps = new double[TAYLOR_LIM];
	for(int i = 0; i < TAYLOR_LIM; i++){
	    cs[i] = 1.0 / (JCalculus.factorial(i + 1));
	    exps[i] = i + 1;
	}
	return new Polynomial(cs, exps);
    }
}
