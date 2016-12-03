package seminer.deneme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import org.apache.commons.math3.ml.distance.EuclideanDistance;

import thesis.metaheuristics.testfunctions.Rastrigin;

public class Main {

	final static double Max_Step=0.01;
	@SuppressWarnings("static-access")
	public static void main(String[] args) {


		Random rand = new Random();
		Rastrigin fgeneric= new Rastrigin();

		double[] xBest;
		xBest=MY_HILLCLIMBING(fgeneric ,2, 1000, rand);
		double fBest = 0;
		for(int i=0; i<30;i++){
			fBest+=fgeneric.evaluate(xBest);
		}
		System.out.println("fBest mean value:"+fBest/30);

		double[] Best;
		Best= MY_SIMULATED_ANNEALING(fgeneric, 2, 1000, rand);
		double fBest2=0;
		for(int i=0; i<30;i++){
			fBest2+=fgeneric.evaluate(Best);
		}
		System.out.println("fBest2 mean value:"+fBest2/30);


		double[] tBest;
		tBest= MY_TABUSEARCH(fgeneric,2, 10000, rand);
		double fBest3=0;
		for(int i=0; i<30;i++){
			fBest3+=fgeneric.evaluate(tBest);
		}
		System.out.println("fBest3 mean value:"+fBest3/30);

	}

	@SuppressWarnings("static-access")
	public static double[] MY_SIMULATED_ANNEALING( Rastrigin fgeneric,int dim, double maxfunevals, Random rand){
		double temperature= 100000;
		double[] S = new double[dim];
		double[] Best;
		double[] Clone;
		double[] R;

		/* Generate individual */
		for (int i = 0; i < dim; i++) {
			S[i] = 10. * rand.nextDouble() - 5.;
		}

		Best=S;

		for (double iter = 0.; iter < maxfunevals; iter++) {
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


		}

		return Best;
	}

	//dım = dımensieon
	//maxfunevals num of call

	@SuppressWarnings("static-access")
	public static double[] MY_HILLCLIMBING( Rastrigin fgeneric,int dim, double maxfunevals, Random rand) {
		double[] S = new double[dim];
		double[] Clone;
		double[] R;
		/* Generate individual */
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


	}


	@SuppressWarnings({ "static-access", "rawtypes", "null" })
	public static double[] MY_TABUSEARCH( Rastrigin fgeneric,int dim, double maxfunevals, Random rand) {
		int l=1000; 										//Desired maximum tabu list length
		int n=10; 											//number of tweaks desired to sample the gradient
		Queue<Double> L= new PriorityQueue<Double>(l); 		//Implemented as first in, first-out queue: TABU LİST

		double[] S = new double[dim];
		double[] Best;
		double[] Clone;
		double[] R;
		double[] W;
		double qualityR;
		/* Generate individual */
		for (int i = 0; i < dim; i++) {
			S[i] = 10. * rand.nextDouble() - 5.;
		}
		for (int i = 0; i < dim; i++) {
			L.add( S[i] );
		}

		Best=S;


		for (double iter = 0.; iter < maxfunevals; iter++) {
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
				
				Iterator iterator = L.iterator();
				while(iterator.hasNext()){
					le[j] =  (double) iterator.next();
					j++;
				}

				/*for(int k = 0; k < dim; k++){
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
		}

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
	
	public int euclideanDistance(double sampleIndex, int compareIndex)
	{
		
		int sum=0;
		/*for (int i = 0; i < data[sampleIndex].length - 1; i++) {
			int distance = data[sampleIndex][i] - data[compareIndex][i];
			sum+=distance*distance;
		}*/
	
		return sum;
		
	}
	
	


}
