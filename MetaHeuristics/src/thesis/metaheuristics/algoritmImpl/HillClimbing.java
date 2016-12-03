package thesis.metaheuristics.algoritmImpl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

import com.sun.javafx.geom.Curve;

import thesis.metaheuristics.graphics.CurveOfAllAlgorithms;
import thesis.metaheuristics.graphics.Curves;
import thesis.metaheuristics.graphics.XYCurves;
import thesis.metaheuristics.interfaces.SingleStateAlgorithms;
import thesis.metaheuristics.interfaces.TestFunctions;
import thesis.metaheuristics.testfunctions.Rastrigin;
import thesis.metaheuristics.utilities.CoordinatesAverageEvaluation;

public class HillClimbing  extends JPanel implements SingleStateAlgorithms{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6303927039753496558L;
	final static double Max_Step=0.01;
	private HashMap<Integer,HashMap<Integer, Double>> cordinates= new HashMap<>(100);
	private HashMap<Integer,HashMap<Integer, Double>> cord= new HashMap<>(100);
	private HashMap<Integer,List<Double>> trialCrdn= new HashMap<>(100);
	double matrix[][];
	private int i=0;
	public HillClimbing(){}
	/*@Override
	public double[] startTheAlgorithm(TestFunctions fgeneric,int dim, double maxfunevals, Random rand) {
		// TODO Auto-generated method stub
		double[] S = new double[dim];
		double[] Clone;
		double[] R;
		 Generate individual 
		for (int i = 0; i < dim; i++) {
			S[i] = 10. * rand.nextDouble() - 5.;
		}

		for (double iter = 0.; iter < maxfunevals; iter++) {

			Clone = S.clone();
			R =tweak(Clone,rand);
			
			if(fgeneric.evaluate(R)<fgeneric.evaluate(S))
			{
				S=R;
			}

		}
		return S;


	}*/

	@SuppressWarnings("static-access")
	public double[] startTheAlgorithm(TestFunctions fgeneric,int dim, double maxfunevals, Random rand) {
		// TODO Auto-generated method stub
		double[] S = new double[dim];
		double[] Clone;
		double[] R;
		int fes=0;
		HashMap<Integer, Double> cordinates= new HashMap<>(100);
		
		/* Generate individual */
		for (int i = 0; i < dim; i++) {
			S[i] = 10. * rand.nextDouble() - 5.;
		}



		while (fes < maxfunevals) {

			Clone = S.clone();
			R =tweak(Clone,rand);
			fes++;
			
			if(fes % 5 ==0)
			{
				cordinates.put(fes, fgeneric.evaluate(S));				
			}
			fes++;
			if(fgeneric.evaluate(R)<fgeneric.evaluate(S))
			{
				S=R;
			}

		}
		//drawGraphics(cordinates);
		createTheMatrix(cordinates);
		return S;

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
	
	public void createTheMatrix(HashMap<Integer, Double> coords){
		
		/* for (Entry<Integer, Double> entry : coords.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		 }*/
		i++;
		cordinates.put(i, coords);
		
		/* for (Entry<Integer, HashMap<Integer, Double>> entry : cordinates.entrySet()) {
				//System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
				
				 for (Entry<Integer, Double> entry2 : entry.getValue().entrySet()) {
						//System.out.println("Key : " + entry2.getKey() + " Value : " + entry2.getValue());
						
						
				 }
		 }
		*/
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
		 
			/*CurveOfAllAlgorithms demo = new CurveOfAllAlgorithms("XY Series HillClimbing",cord,"FEs(Function Evaluation Count)","fBest");
		    demo.pack();
		    RefineryUtilities.centerFrameOnScreen(demo);
		    demo.setVisible(true);*/
		    
	}
	@SuppressWarnings("static-access")
	public double calculateAvaerage(TestFunctions fgeneric,int dim, double maxfunevals, Random rand){
		double[] xBest;
		xBest=startTheAlgorithm(fgeneric ,2, 1000, rand);
		double fBest = 0;
		for(int i=0; i<30;i++){
			fBest+=fgeneric.evaluate(xBest);
		}
	
		System.out.println("fBest mean value:"+fBest/30);
		return fBest;
	}
	
	public double calculateAvaerage2(TestFunctions fgeneric,int dim, double maxfunevals, Random rand){
		double[] xBest;
		xBest=startTheAlgorithm(fgeneric ,2, 1000, rand);
		double fBest = 0;
		for(int i=0; i<30;i++){
			fBest+=fgeneric.evaluate(xBest);
		}
	
		System.out.println("fBest mean value:"+fBest/30);
		return fBest;
	}
	
	public void drawGraphics(HashMap<Integer, Double> cordinates) {
		// TODO Auto-generated method stub
		
		

		    XYCurves demo = new XYCurves("XY Series HillClimbing",cordinates,"FEs(Function Evaluation Count)","fBest");
		    demo.pack();
		    RefineryUtilities.centerFrameOnScreen(demo);
		    demo.setVisible(true);

	}
}
