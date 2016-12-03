package thesis.metaheuristics.strategyPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;

import thesis.metaheuristics.algoritmImpl.HillClimbing;
import thesis.metaheuristics.algoritmImpl.SimulatedAnnealing;
import thesis.metaheuristics.algoritmImpl.TabuSearch;
import thesis.metaheuristics.graphics.CurveOfAllAlgorithms;
import thesis.metaheuristics.interfaces.PopulationAlgorithms;
import thesis.metaheuristics.interfaces.SingleStateAlgorithms;
import thesis.metaheuristics.interfaces.TestFunctions;
import thesis.metaheuristics.testfunctions.Rastrigin;
import thesis.metaheuristics.testfunctions.Schwefel;

public class AlgorithmTestBed {

	private AlgorithmController ac;
	private HashMap<Integer,HashMap<Integer, Double>> cord;
	private int i=0;
	public void startTheTest(){
		
		Random rand = new Random();
		SingleStateAlgorithms hillClimbing = new HillClimbing();
		SingleStateAlgorithms simulatedAnnealing = new SimulatedAnnealing();
		SingleStateAlgorithms tabuSearch = new TabuSearch();

		ArrayList<TestFunctions> testList= new ArrayList<>();
		testList.add(new Rastrigin());
		testList.add(new Schwefel());
		
		AlgorithmController ac = new AlgorithmController();	
		
		CurveOfAllAlgorithms demo;
		List<String> algNames=new ArrayList<>();
		for(TestFunctions tf: testList){
			cord= new HashMap<>(100);
			i=0;
			ac.setSa(hillClimbing);
			ac.runTheSingleSteteAlgorithms(tf, 2, 1000, rand);
			mergeCoordintes(ac.evaluateAveraga());
			algNames.add("hillClimbing");
			
			ac.setSa(simulatedAnnealing);
			ac.runTheSingleSteteAlgorithms(tf,  2, 1000, rand);
			mergeCoordintes(ac.evaluateAveraga());
			algNames.add("simulatedAnnealing");

			/*ac.setSa(tabuSearch);
			ac.runTheSingleSteteAlgorithms(testList, 2, 1000, rand);*/
			
			demo = new CurveOfAllAlgorithms(tf.getClass().getSimpleName(),cord,"FEs(Function Evaluation Count)","fBest",algNames);
			demo.pack();
		    RefineryUtilities.centerFrameOnScreen(demo);
		    demo.setVisible(true);
			
		}
	
	}
	public void mergeCoordintes(HashMap<Integer,HashMap<Integer, Double>> cordi){
		
		 for (Entry<Integer, HashMap<Integer, Double>> entry : cordi.entrySet()) {
			 //System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			 
				 cord.put(i,  entry.getValue());
				 i++;
			
		 }
	}
	public AlgorithmController getAc() {
		return ac;
	}

	public void setAc(AlgorithmController ac) {
		this.ac = ac;
	}
	
	
	
}
