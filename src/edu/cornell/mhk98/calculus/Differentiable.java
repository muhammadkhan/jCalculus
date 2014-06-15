package edu.cornell.mhk98.calculus;

import edu.cornell.mhk98.function.Function;

public interface Differentiable<T> extends Function{
    
    public T differentiate();
}
