package thesis.metaheuristics.interfaces;

import java.util.HashMap;
import java.util.Random;


public interface SingleStateAlgorithms {

	public double[] startTheAlgorithm(TestFunctions fgeneric,int dim, double maxfunevals, Random rand);
	
	public double calculateAvaerage(TestFunctions fgeneric,int dim, double maxfunevals, Random rand);
	
	public void drawGraphics(HashMap<Integer, Double> cordinates);

	public HashMap<Integer,HashMap<Integer, Double>> evaluateAverage();
	
	public void drawAllGraphics();
	
}
