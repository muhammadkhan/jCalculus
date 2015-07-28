package edu.cornell.mhk98.function;

import edu.cornell.mhk98.function.transcendentals.Exponential;

public abstract class Function{

    public abstract  double apply(double x);

    public static Function add(Function f1, Function f2){
	if(f1 instanceof Polynomial && f2 instanceof Polynomial){
	    Polynomial p1 = (Polynomial)f2, p2 = (Polynomial)f2;
	    return Polynomial.add(p1, p2);
	} else{
	    if(f1 instanceof Transcendental && f2 instanceof Transcendental){
		if(f1 instanceof Exponential && f2 instanceof Exponential){
		    Exponential e1 = (Exponential)f1, e2 = (Exponential)f2;
		    //TODO finish
		}
	    }
	    return new SumOfFunctions(f1, f2);
	}
    }

    public static Function multiply(Function f1, Function f2){
	if(f1 instanceof Polynomial && f2 instanceof Polynomial){
	    Polynomial p1 = (Polynomial)f1, p2 = (Polynomial)f2;
	    return Polynomial.multiply(p1, p2);
	} else{
	    if(){
		if(f1 instanceof Transcendental && f2 instanceof Transcendental){
		    if(f1 instanceof Exponential && f2 instanceof Exponential){
			Exponential e1 = (Exponential)f1, e2 = (Exponential)f2;
			return new Exponential(Function.add(e1.getExponentFunction, e2.getExponentFunction()));
		    }
		}
	    }
	    return new ProductOfFunctions(f1, f2);
	}
    }

    /* Begin private inner classes */

    private class SumOfFunctions extends Function
	implements Differentiable<? super SumOfFunctions>,
		   Integrable<? super SumOfFunctions>{

	private Function f1, f2;

	private SumOfFunctions(Function f1, Function f2){
	    this.f1 = f1;
	    this.f2 = f2;
	}

	private double apply(double x){
	    return f1.apply(x) + f2.apply(x);
	}

	private SumOfFunctions differentiate(){
	    return new SumOfFunctions(f1.differentiate(), f2.differentiate());
	}

	private SumOfFunctions integrate(){
	    return new SumOfFunctions(f1.integrate(), f2.integrate());
	}

	private double integrate(double a, double b){
	    return f1.integrate(a,b) + f2.integrate(a,b);
	}
    }

    private class ProductOfFunctions extends Function
	implements Differentiable<? super ProductOfFunctions>,
		   Integrable<? super ProductOfFunctions> {

	private Function f1, f2;

	private ProductOfFunctions(Function f1, Function f2){
	    this.f1 = f1;
	    this.f2 = f2;
	}

	private double apply(double x){
	    return f1.apply(x)*f2.apply(x);
	}

	private Function differentiate(){
	    /* Product Rule */
	    Function a1 = Function.multiply(f1, f2.differentiate());
	    Function a2 = Function.multiply(f1.differentiate(), f2);
	    return Function.add(a1, a2);
	}
    }
}
