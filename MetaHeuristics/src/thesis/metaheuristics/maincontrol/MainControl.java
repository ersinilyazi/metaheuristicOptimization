package thesis.metaheuristics.maincontrol;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import thesis.metaheuristics.algoritmImpl.HillClimbing;
import thesis.metaheuristics.algoritmImpl.SimulatedAnnealing;
import thesis.metaheuristics.algoritmImpl.TabuSearch;
import thesis.metaheuristics.interfaces.SingleStateAlgorithms;
import thesis.metaheuristics.interfaces.TestFunctions;
import thesis.metaheuristics.strategyPattern.AlgorithmController;
import thesis.metaheuristics.strategyPattern.AlgorithmTestBed;
import thesis.metaheuristics.strategyPattern.AlgorithmTestBedTable;
import thesis.metaheuristics.testfunctions.Rastrigin;
import thesis.metaheuristics.testfunctions.Schwefel;

public class MainControl {

	public static void main(String[] args) {
		
		Random rand = new Random();
		TestFunctions fgeneric= new Rastrigin();

		/*SingleStateAlgorithms hillClimbing = new HillClimbing();
		hillClimbing.startTheAlgorithm(fgeneric, 2, 1000, rand);
		
		
		SingleStateAlgorithms simulatedAnnealing = new SimulatedAnnealing();
		simulatedAnnealing.startTheAlgorithm(fgeneric,2, 1000, rand);
		
		SingleStateAlgorithms tabuSearch = new TabuSearch();
		tabuSearch.startTheAlgorithm(fgeneric,2, 1000, rand);
		*/
		
		
				
		AlgorithmTestBed atb = new AlgorithmTestBed();
		atb.startTheTest();
		
		/*AlgorithmTestBedTable atbt = new AlgorithmTestBedTable();
		atbt.startTheTest();*/
		
	}
}
