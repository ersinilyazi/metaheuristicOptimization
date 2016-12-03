package thesis.metaheuristics.utilities;

import java.util.HashMap;
import java.util.Map.Entry;

public class CoordinatesAverageEvaluation {
	
	private HashMap<Integer,HashMap<Integer, Double>> crdnts= new HashMap<>(100);

	public CoordinatesAverageEvaluation(HashMap<Integer,HashMap<Integer, Double>> cordinates){

		this.crdnts=cordinates;
		
	}
	
	public HashMap<Integer, Double> findAverageOfMatrix(){
		HashMap<Integer, Double> cordinates= new HashMap<>(100);
		 double result=0;
		 
		 for (Entry<Integer, HashMap<Integer, Double>> entry : crdnts.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
				result=0;
				 for (Entry<Integer, Double> entry2 : entry.getValue().entrySet()) {
						System.out.println("Key : " + entry2.getKey() + " Value : " + entry2.getValue());
					 	
					 	if(cordinates.containsKey(entry2))
					 	{
					 		result=(cordinates.get(entry2) +entry2.getValue());
					 		cordinates.put(entry2.getKey(), result);
					 	}else{
					 		cordinates.put(entry2.getKey(), entry2.getValue());
					 	}	
				 }
		 }
		 
		 for (Entry<Integer, Double> entry : cordinates.entrySet()) {
			 
			 cordinates.put(entry.getKey(), entry.getValue()/30);
		 }
		 
		 
		return cordinates;
		
		
	}
	
	

}
