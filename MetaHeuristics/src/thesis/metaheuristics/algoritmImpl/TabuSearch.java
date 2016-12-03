package thesis.metaheuristics.algoritmImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.jfree.ui.RefineryUtilities;

import thesis.metaheuristics.graphics.XYCurves;
import thesis.metaheuristics.interfaces.SingleStateAlgorithms;
import thesis.metaheuristics.interfaces.TestFunctions;
import thesis.metaheuristics.testfunctions.Rastrigin;

public class TabuSearch implements SingleStateAlgorithms{

	final static double Max_Step=0.01;

	public TabuSearch(){}
	
	@Override
	public double[] startTheAlgorithm(TestFunctions fgeneric,int dim, double maxfunevals, Random rand) {

		int l=1000; 										//Desired maximum tabu list length
		int n=10; 											//number of tweaks desired to sample the gradient
		Queue<Double> L= new PriorityQueue<Double>(l); 		//Implemented as first in, first-out queue: TABU LİST

		double[] S = new double[dim];
		double[] Best;
		double[] Clone;
		double[] R;
		double[] W;
		double qualityR;
		HashMap<Integer, Double> cordinates= new HashMap<>(100);

		/* Generate individual */
		for (int i = 0; i < dim; i++) {
			S[i] = 10. * rand.nextDouble() - 5.;
		}
		for (int i = 0; i < dim; i++) {
			L.add( S[i] );
		}

		Best=S;


		for (int iter = 0; iter < maxfunevals; iter++) {
			if(L.size()>l)
				L.poll();

			Clone = S.clone();

			R =tweak(Clone,rand);
			qualityR=fgeneric.evaluate(R);
			int j=0;
			double[] distance = null;
			for(int i=0; i<n-1 ; i++){
				Clone = S.clone();        		
				W=tweak(Clone,rand);
				EuclideanDistance e = new EuclideanDistance();
				double[] le = null;
				
				/*Iterator iterator = L.iterator();
				while(iterator.hasNext()){
					le[j] =  (double) iterator.next();
					j++;
				}*/
					
				/*e.compute(le, W);
				
				for(int k = 0; k < dim; k++){
					distance[k]= e.compute(W[k],le[k]);
				}*/
				if(!W.equals(L) &&(fgeneric.evaluate(W)> qualityR || R.equals(L))){
					R=W;
				}
			}
			if(R.equals(L) && fgeneric.evaluate(R)>fgeneric.evaluate(S)){
				S=R;
				
				for (int i = 0; i < R.length; i++) {
					L.add( R[i] );
				}
			}
			if(fgeneric.evaluate(S)>fgeneric.evaluate(Best)){
				Best=S;
			}
			if(iter % 5 ==0)
			{
				cordinates.put(iter, fgeneric.evaluate(Best));				
			}
		}
		drawGraphics(cordinates);
		return Best;	
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
		
		
		return maxfunevals;

	}

	public void drawGraphics(HashMap<Integer, Double> cordinates) {
		// TODO Auto-generated method stub
	    XYCurves demo = new XYCurves("XY Series TabuSearch",cordinates,"FEs(Function Evaluation Count)","fBest");
	    demo.pack();
	    RefineryUtilities.centerFrameOnScreen(demo);
	    demo.setVisible(true);

	}

	@Override
	public HashMap<Integer,HashMap<Integer, Double>> evaluateAverage() {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void drawAllGraphics() {
		// TODO Auto-generated method stub
		
	}
}
