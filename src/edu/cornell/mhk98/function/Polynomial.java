package edu.cornell.mhk98.function;

import edu.cornell.mhk98.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Polynomial{

    private List<Monomial> poly;

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
				 return (exp_diff == 0) ? (new Integer(p1.getFirst() - p2.getFirst())) : exp_diff;
			     }
			 });


	poly = new ArrayList<Monomial>();
	for(int i = 0; i < cs.length; i++){
	    

	}

    }

    private class Monomial{
	
	private double coeff;
	private int exp;

	public Monomial(double c, int e){
	    coeff = c;
	    exp = e;
	}


    }
}
