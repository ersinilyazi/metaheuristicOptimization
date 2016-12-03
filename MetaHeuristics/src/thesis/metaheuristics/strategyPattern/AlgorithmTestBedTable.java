package thesis.metaheuristics.strategyPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import org.jfree.ui.RefineryUtilities;

import thesis.metaheuristics.algoritmImpl.HillClimbing;
import thesis.metaheuristics.algoritmImpl.SimulatedAnnealing;
import thesis.metaheuristics.algoritmImpl.TabuSearch;
import thesis.metaheuristics.graphics.CurveOfAllAlgorithms;
import thesis.metaheuristics.graphics.TableOfAllAlgorithms;
import thesis.metaheuristics.interfaces.SingleStateAlgorithms;
import thesis.metaheuristics.interfaces.TestFunctions;
import thesis.metaheuristics.testfunctions.Rastrigin;
import thesis.metaheuristics.testfunctions.Schwefel;

public class AlgorithmTestBedTable {

	private AlgorithmController ac;
	
	public void startTheTest(){
		
		Random rand = new Random();
		SingleStateAlgorithms hillClimbing = new HillClimbing();
		SingleStateAlgorithms simulatedAnnealing = new SimulatedAnnealing();
		SingleStateAlgorithms tabuSearch = new TabuSearch();

		ArrayList<TestFunctions> testList= new ArrayList<>();
		testList.add(new Rastrigin());
		testList.add(new Schwefel());
		
		AlgorithmController ac = new AlgorithmController();	
		TableOfAllAlgorithms table;
		List<String> tableRows = new ArrayList<>();
		String dataValues[][] = new String[10][10] ;
		String data="{ ";
		int i=0;
		int j=0;
		for(TestFunctions tf: testList){
			
			dataValues[i][j] =tf.getClass().getSimpleName();
			j++;
			dataValues[i][j] =hillClimbing.getClass().getSimpleName();
			j++;
			ac.setSa(hillClimbing);
			ac.runTheSingleSteteAlgorithmsForTableMean(tf, 2, 1000, rand);
			dataValues[i][j] =" "+ac.runTheSingleSteteAlgorithmsForTableMean(tf, 2, 1000, rand);
			j++;
			dataValues[i][j] =" "+ac.runTheSingleSteteAlgorithmsForTableStd(tf, 2, 1000, rand);
			j++;
			dataValues[i][j] =" "+ac.runTheSingleSteteAlgorithmsForTableMedian(tf, 2, 1000, rand);
			j++;
			ac.runTheSingleSteteAlgorithms(tf, 2, 1000, rand);
			dataValues[i][j] =" "+ac.totalTime;
			j=0;
			
			i++;
			dataValues[i][j] =tf.getClass().getSimpleName();
			j++;
			dataValues[i][j] =simulatedAnnealing.getClass().getSimpleName();
			j++;
			dataValues[i][j] =" "+ac.runTheSingleSteteAlgorithmsForTableMean(tf, 2, 1000, rand);
			j++;
			dataValues[i][j] =" "+ac.runTheSingleSteteAlgorithmsForTableStd(tf, 2, 1000, rand);
			j++;
			dataValues[i][j] =" "+ac.runTheSingleSteteAlgorithmsForTableMedian(tf, 2, 1000, rand);
			j++;
			ac.setSa(simulatedAnnealing);
			ac.runTheSingleSteteAlgorithms(tf, 2, 1000, rand);
			dataValues[i][j] =""+ac.totalTime;
			j=0;
			

			
			i++;
			/*ac.setSa(tabuSearch);
			ac.runTheSingleSteteAlgorithms(testList, 2, 1000, rand);*/
			
			
		}
		
		table= new TableOfAllAlgorithms(dataValues);
		table.setVisible(true);
	}
	
	public AlgorithmController getAc() {
		return ac;
	}

	public void setAc(AlgorithmController ac) {
		this.ac = ac;
	}
	
	
	
}
