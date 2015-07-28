package edu.cornell.mhk98.calculus;

public interface Integrable<T> {
    
    public T integrate();

    public double integrate(double a, double b);
}
