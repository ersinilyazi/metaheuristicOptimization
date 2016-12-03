package thesis.metaheuristics.strategyPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.stat.StatUtils;

import thesis.metaheuristics.interfaces.PopulationAlgorithms;
import thesis.metaheuristics.interfaces.SingleStateAlgorithms;
import thesis.metaheuristics.interfaces.TestFunctions;

public class AlgorithmController {

	private final static int RUNTIME=30;
	private SingleStateAlgorithms sa;
	private PopulationAlgorithms pa;
	long totalTime;
	public void setSa(SingleStateAlgorithms sa) {
		this.sa = sa;
	}

	public void setPa(PopulationAlgorithms pa) {
		this.pa = pa;
	}
	
	public void runTheSingleSteteAlgorithms(TestFunctions fgeneric,int dim, double maxfunevals, Random rand){
		long startTime = System.currentTimeMillis();
			
			for(int i=0;i<RUNTIME; i++){
				
				sa.startTheAlgorithm(fgeneric, dim, maxfunevals, rand);
				
			}
		long endTime   = System.currentTimeMillis();
		totalTime = (endTime - startTime)/RUNTIME;
		//sa.evaluateMedian();
		
	}
	@SuppressWarnings("null")
	public double  runTheSingleSteteAlgorithmsForTableMean(TestFunctions fgeneric,int dim, double maxfunevals, Random rand){
		
		double [] averageList = new double[dim];
		double [] fBestList = new double[dim];

		for(int i=0;i<RUNTIME; i++){
			fBestList=sa.startTheAlgorithm(fgeneric, dim, maxfunevals, rand);
			
			for(int j=0;j<fBestList.length; j++){
				averageList[j]+=fBestList[j];
			}
		}
		
		
		for(int i=0;i<averageList.length; i++){
			averageList[i]=averageList[i]/RUNTIME;
		}
		
		return StatUtils.mean(averageList);
	}
	
	@SuppressWarnings("null")
	public double  runTheSingleSteteAlgorithmsForTableStd(TestFunctions fgeneric,int dim, double maxfunevals, Random rand){
		double [] averageList = new double[dim];
		double [] fBestList = new double[dim];

		for(int i=0;i<RUNTIME; i++){
			fBestList=sa.startTheAlgorithm(fgeneric, dim, maxfunevals, rand);
			
			for(int j=0;j<fBestList.length; j++){
				averageList[j]+=fBestList[j];
			}
			
		}
		
		for(int i=0;i<averageList.length; i++){
			averageList[i]=averageList[i]/RUNTIME;
		}
		
		

		return Math.sqrt(StatUtils.variance(averageList));
	}
	
	public double  runTheSingleSteteAlgorithmsForTableMedian(TestFunctions fgeneric,int dim, double maxfunevals, Random rand){
		double [] averageList = new double[dim];
		double [] fBestList = new double[dim];
		

		for(int i=0;i<RUNTIME; i++){
			fBestList=sa.startTheAlgorithm(fgeneric, dim, maxfunevals, rand);
			
			for(int j=0;j<fBestList.length; j++){
				averageList[j]+=fBestList[j];
			}
		}
		
		for(int i=0;i<averageList.length; i++){
			averageList[i]=averageList[i]/RUNTIME;
		}
		

		Arrays.sort(averageList);
		double median;
		if (averageList.length % 2 == 0)
		    median = ((double)averageList[averageList.length/2] + (double)averageList[averageList.length/2 - 1])/2;
		else
		    median = (double) averageList[averageList.length/2];
		
		return median;
	}


	public HashMap<Integer, HashMap<Integer, Double>> evaluateAveraga(){
		return sa.evaluateAverage();
	}
	public void draw(){
		sa.drawAllGraphics();
	}
	
	public double[] runThePopulationAlgorithms(TestFunctions fgeneric,int dim, double maxfunevals, Random rand){
		return null;
	}

}
