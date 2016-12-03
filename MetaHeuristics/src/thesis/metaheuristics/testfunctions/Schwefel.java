package thesis.metaheuristics.testfunctions;

import thesis.metaheuristics.interfaces.TestFunctions;

public class Schwefel implements TestFunctions {

	@Override
	public double evaluate(double[] x1) {
		// TODO Auto-generated method stub
		double sum=0;
		for (double x:x1 ){
			double a = Math.sqrt(Math.abs(x));
			sum+= (-x)*Math.sin(a);
		}
		return sum;	
	}

}
