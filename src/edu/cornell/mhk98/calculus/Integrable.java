package edu.cornell.mhk98.calculus;

import edu.cornell.mhk98.function.Function;

public interface Integrable<T> extends Function{
    
    public T integrate();

    public double integrate(double a, double b);
}
