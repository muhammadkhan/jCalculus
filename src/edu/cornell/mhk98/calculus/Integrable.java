package edu.cornell.mhk98.calculus;

import edu.cornell.mhk98.function.Function;

public interface Integrable {
    
    public Function integrate();

    public double integrate(double a, double b);
}
