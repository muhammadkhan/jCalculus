package edu.cornell.mhk98.util;

public class Pair<S,T> {
    
    private S d1;
    private T d2;


    public Pair(S s, T t){
	d1 = s;
	d2 = t;
    }


    public S getFirst(){
	return d1;
    }

    public void setFirst(S s){
	d1 = s;
    }

    public T getSecond(){
	return d2;
    }

    public void setSecond(T t){
	d2 = t;
    }

}
