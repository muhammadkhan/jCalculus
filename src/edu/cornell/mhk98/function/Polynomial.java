package edu.cornell.mhk98.function;

import edu.cornell.mhk98.calculus.Differentiable;
import edu.cornell.mhk98.calculus.Integrable;
import edu.cornell.mhk98.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Polynomial implements Differentiable<Polynomial>, Integrable<Polynomial> {

    private List<Monomial> poly;

    private Polynomial(List<Monomial> terms){
	poly = terms;
    }

    public Polynomial(double[] cs, int[] exps){
	if(cs.length != exps.length)
	    throw new IllegalArgumentException("Input arrays must be of equal length\n");
	List<Pair<Double, Integer>> tupList = new ArrayList<Pair<Double, Integer>>();
	for(int i = 0; i < cs. length; i++)
	    tupList.add(new Pair(cs[i], exps[i]));

	Collections.sort(tupList,
			 new Comparator<Pair<Double, Integer>>(){
			    
			     @Override
			     public int compare(Pair<Double,Integer> p1, Pair<Double, Integer> p2){
				 int exp_diff = p2.getSecond() - p1.getSecond();
				 return (exp_diff == 0) ? (int)(p1.getFirst() - p2.getFirst()) : exp_diff;
			     }
			 });


	poly = new ArrayList<Monomial>();

	/* At this point, tupList has been sorted in decreasing order of exponent (the integer)  */
	int curDeg = tupList.get(0).getSecond();
	double curCoeff = 0;
	for(Pair<Double, Integer> p : tupList){
	    if(p.getSecond() == curDeg){
		curCoeff += p.getFirst();
	    } else{
		poly.add(new Monomial(curCoeff, curDeg));
		curDeg--;
		curCoeff = 0;
	    }

	}

    }

    public double apply(double x){
	double ret = 0;
	for(Monomial term : poly)
	    ret += term.apply(x);

	return ret;
    }

    public Polynomial differentiate(){
	List<Monomial> derivative = new ArrayList<Monomial>();
	for(Monomial term : poly){
	    derivative.add(term.differentiate());
	}

	return new Polynomial(derivative);
    }

    public Polynomial integrate(){
	List<Monomial> antideriv = new ArrayList<Monomial>();
	for(Monomial term : poly){
	    antideriv.add(term.integrate());
	}

	return new Polynomial(antideriv);
    }

    public double integrate(double a, double b){
	double integ = 0;
	for(Monomial term : poly){
	    integ += term.integrate(a,b);
	}

	return integ;
    }

    private class Monomial implements Differentiable<Monomial>, Integrable<Monomial>{
	
	private double coeff;
	private int exp;

	public Monomial(double c, int e){
	    coeff = c;
	    exp = e;
	}

	public double apply(double x){
	    return coeff*(Math.pow(x, exp));
	}

	public Monomial differentiate(){
	    return new Monomial(coeff*exp, exp - 1);
	}

	public Monomial integrate(){
	    return new Monomial(coeff / (exp + 1), exp + 1);
	}

	public double integrate(double a, double b){
	    Monomial m = this.integrate();
	    return (m.apply(b) - m.apply(a));
	}
    }
}
