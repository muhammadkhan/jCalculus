package edu.cornell.mhk98.util;

public class JCalculus{

    /* Uninstantiable */
    private JCalculus(){}

    public static int factorial(int n){
	if(n < 0)
	    throw new IllegalArgumentException("factorial: " + n + "is negative");
	else if(n == 0)
	    return 1;
	else
	    return n * factorial(n - 1);
    }
}
