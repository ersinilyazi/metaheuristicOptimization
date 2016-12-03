package thesis.metaheuristics.testfunctions;

import thesis.metaheuristics.interfaces.TestFunctions;


public class Rastrigin implements TestFunctions{
	
	
   public double evaluate( double[] x1){
		
		int N= x1.length;
		double sum=10*N;
		for (double x:x1 ){
			double a = 2*Math.PI*x;
			sum+= Math.pow(x, 2)-10*Math.cos(a);
		}
		return sum;
	}

}
