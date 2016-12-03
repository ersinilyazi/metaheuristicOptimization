package thesis.metaheuristics.algoritmImpl;

import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;

import org.jfree.ui.RefineryUtilities;

import thesis.metaheuristics.graphics.CurveOfAllAlgorithms;
import thesis.metaheuristics.graphics.Curves;
import thesis.metaheuristics.graphics.XYCurves;
import thesis.metaheuristics.interfaces.SingleStateAlgorithms;
import thesis.metaheuristics.interfaces.TestFunctions;
import thesis.metaheuristics.testfunctions.Rastrigin;
import thesis.metaheuristics.utilities.CoordinatesAverageEvaluation;

public class SimulatedAnnealing implements SingleStateAlgorithms{

	final static double Max_Step=0.01;
	private HashMap<Integer,HashMap<Integer, Double>> cordinates= new HashMap<>(100);
	private HashMap<Integer,HashMap<Integer, Double>> cord= new HashMap<>(100);
	private int i=0;

	public SimulatedAnnealing(){}
	
	@Override
	public double[] startTheAlgorithm(TestFunctions fgeneric,int dim, double maxfunevals, Random rand) {
		// TODO Auto-generated method stub
		double temperature= 100000;
		double[] S = new double[dim];
		double[] Best;
		double[] Clone;
		double[] R;
		HashMap<Integer, Double> cordinates= new HashMap<>(100);

		/* Generate individual */
		for (int i = 0; i < dim; i++) {
			S[i] = 10. * rand.nextDouble() - 5.;
		}

		Best=S;

		for (int iter = 0; iter < maxfunevals; iter++) {
			Clone = S.clone();

			R =tweak(Clone,rand);
			if(fgeneric.evaluate(R)<fgeneric.evaluate(S))
			{
				S=R;
				temperature=temperature-10;

			}
			if(fgeneric.evaluate(S)< fgeneric.evaluate(Best))
			{
				Best=S;
			}

			if(iter % 5 ==0)
			{
				cordinates.put(iter, fgeneric.evaluate(S));				
			}
			
			

		}
		//drawGraphics(cordinates);
		createTheMatrix(cordinates);

		return Best;
	}
	
	public void createTheMatrix(HashMap<Integer, Double> coords){
		
		i++;
		cordinates.put(i, coords);

	}
	
	public HashMap<Integer,HashMap<Integer, Double>> evaluateAverage(){
		i=0;
		CoordinatesAverageEvaluation cae = new CoordinatesAverageEvaluation(cordinates);
		
		//drawGraphics(cae.findAverageOfMatrix());
		return addFbestsToGraph(cae.findAverageOfMatrix());
	}

	public HashMap<Integer,HashMap<Integer, Double>> addFbestsToGraph(HashMap<Integer, Double> cordinates){
		 
		 cord.put(i,cordinates);
		 i++;
		 return cord;
	}
	
	public void drawAllGraphics(){
		 
		/*CurveOfAllAlgorithms demo = new CurveOfAllAlgorithms("XY Series SimulatedAnnealing",cord,"FEs(Function Evaluation Count)","fBest");
	    demo.pack();
	    RefineryUtilities.centerFrameOnScreen(demo);
	    demo.setVisible(true);*/
	}
	public static double[] tweak( double[] Clone,Random rand){

		for(int i=0;i<Clone.length;i++){
			double Low = -Max_Step;
			double High = Max_Step;
			double change = rand.nextDouble()*(High-Low) +Low;

			Clone[i]+=change;
		}


		return Clone;

	}

	@SuppressWarnings("static-access")
	public double calculateAvaerage(TestFunctions fgeneric,int dim, double maxfunevals, Random rand){
		
		double[] Best;
		Best= startTheAlgorithm(fgeneric, 2, 1000, rand);
		double fBest2=0;
		for(int i=0; i<30;i++){
			fBest2+=fgeneric.evaluate(Best);
		}
		System.out.println("fBest2 mean value:"+fBest2/30);
		return fBest2;

	}

	public void drawGraphics(HashMap<Integer, Double> cordinates) {
		// TODO Auto-generated method stub
		  /*JFrame frame = new JFrame();         
		  frame.setVisible(true);
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  Curves curve = new Curves(cordinates);
		  frame.add(curve);
		  frame.pack();
		  frame.setSize(400,400);*/
		
	    XYCurves demo = new XYCurves("XY Series SimulatedAnnealing",cordinates,"FEs(Function Evaluation Count)","fBest");
	    demo.pack();
	    RefineryUtilities.centerFrameOnScreen(demo);
	    demo.setVisible(true);

	
	}


	
}
