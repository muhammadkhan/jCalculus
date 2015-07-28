package edu.cornell.mhk98.function;

import edu.cornell.mhk98.calculus.Differentiable;
import edu.cornell.mhk98.calculus.Integrable;
import edu.cornell.mhk98.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Polynomial extends Function implements Differentiable<Polynomial>, Integrable<Polynomial>{

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

    private void sort(){
	Collections.sort(poly,
			 new Comparator<List<Monomial>>(){
			     public int compare(Monomial m1, Monomial m2){
				 int exp_diff = m1.exp - m2.exp;
				 if(exp_diff == 0)
				     return m1.coeff - m2.coeff;
				 return exp_diff;
			     }
			 }
        );
    }

    private void simplify(){
	List<Monomial> poly2 = new ArrayList<Monomial>();
	int deg = getDegree();
	for(int exp = 0; exp <= deg; exp++){
	    double coeffNew = 0.0;
	    for(Monomial m : poly){
		if(m.getExponent() == exp)
		    coeffNew += m.getCoefficient();
	    }
	    poly2.add(new Monomial(coeffNew, exp));
	}
	poly = poly2;
    }

    public int getDegree(){
	sort();
	Monomial first = poly.get(0);
	return first.getExponent();
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

    public static Polynomial add(Polynomial p1, Polynomial p2){
	p1.sort();
	p2.sort();
	Polynomial lowerDegreePoly, higherDegreePoly;
	if(p1.getDegree() < p2.getDegree()){
	    lowerDegreePoly = p1;
	    higherDegreePoly = p2;
	} else{
	    lowerDegreePoly = p2;
	    higherDegreePoly = p1;
	}
	List<Monomial> ret = new ArrayList<Monomial>();
	for(Monomial m1 : higherDegreePoly.poly){
	    double coeffNew = m1.getCoefficient();
	    for(Monomial m2 : lowerDegreePoly.poly){
		if(m1.getExponent() == m2.getExponent()){
		    coeffNew += m2.getCoefficient();
		}
	    }
	    ret.add(new Monomial(coeffNew, m1.getExponent()));
	}
	return new Polynomial(ret);
    }

    private static Polynomial multiplyByMonomial(Monomial m, Polynomial p){
	double c = m.getCoefficient();
	int e = m.getExponent();
	List<Monomial> ret = new ArrayList<Monomial>();
	for(Monomial p_m : p.poly){
	    ret.add(new Monomial(c*p_m.getCoefficient(), e + p_m.getExponent()));
	}
	return new Polynomial(ret);
    }

    public static Polynomial multiply(Polynomial p1, Polynomial p2){
	p1.sort();
	p2.sort();
	Polynomial product = null;
	for(Monomial m1 : p1.poly){
	    Polynomial multiplied = multiplyByMonomial(m1, p2);
	    if(product == null)
		product = multiplied;
	    else
		product = Polynomial.add(product, multiplied);
	}
	if(product == null)
	    throw new IllegalArgumentError("Unable to multiply these polynomials");
	return product;
    }

    private class Monomial implements Differentiable<Monomial>, Integrable<Monomial>{
	
	private double coeff;
	private int exp;

	public Monomial(double c, int e){
	    coeff = c;
	    exp = e;
	}

	public double getCoefficient(){
	    return coeff;
	}

	public int getExponent(){
	    return exp;
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
