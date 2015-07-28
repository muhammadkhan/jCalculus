package edu.cornell.mhk98.function.transcendentals;

import edu.cornell.mhk98.calculus.Differentiable;
import edu.cornell.mhk98.calculus.Integrable;
import edu.cornell.mhk98.function.Function;
import edu.cornell.mhk98.function.Polynomial;
import edu.cornell.mhk98.function.Transcendental;
import edu.cornell.mhk98.util.JCalculus;

public class Exponential extends Transcendental
    implements Differentiable, Integrable {

    private static final int TAYLOR_LIM = 8;

    private Function raisedFn;

    public Exponential(Function r){
	raisedFn = r;
    }

    public Function differentiate(){
        return Function.multiply(raisedFn.differentiate(), this);
    }

    public Function integrate(){
	//TODO implement
	return null;
    }

    public double integrate(double a, double b){
	//TODO implement
	return 0.0;
    }

    public Function getExponentFunction(){
	return raisedFn;
    }

    public double apply(double x){
	return Math.exp(x);
    }

    public Polynomial getTaylorApprox(int order){
	double[] cs = new double[TAYLOR_LIM];
	int[] exps = new int[TAYLOR_LIM];
	for(int i = 0; i < TAYLOR_LIM; i++){
	    cs[i] = 1.0 / (JCalculus.factorial(i + 1));
	    exps[i] = i + 1;
	}
	return new Polynomial(cs, exps);
    }
}
