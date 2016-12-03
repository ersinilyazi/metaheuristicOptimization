package thesis.metaheuristics.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import thesis.metaheuristics.interfaces.TestFunctions;
import thesis.metaheuristics.testfunctions.Rastrigin;
import thesis.metaheuristics.testfunctions.Schwefel;

public class CurveOfAllAlgorithms extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7647953429875717232L;

	public CurveOfAllAlgorithms(String title,HashMap<Integer,HashMap<Integer, Double>> cord ,String XSide,String YSide,List<String> algNames) {
		super(title);
		// TODO Auto-generated constructor stub
		 List<XYSeries> series =  new ArrayList<>();
		//final XYSeries seriItem = new XYSeries("r");
		 XYSeries seriItem;
		
		 for (Entry<Integer, HashMap<Integer, Double>> entry : cord.entrySet()) {
			 //System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			 seriItem = new XYSeries(algNames.get(entry.getKey()));
			 for (Entry<Integer, Double> entry2 : entry.getValue().entrySet()) {
				 //System.out.println("Key : " + entry2.getKey() + " Value : " + entry2.getValue());
				 seriItem.add(entry2.getKey().doubleValue(),entry2.getValue().doubleValue());
			 }
			 series.add(seriItem);
			 
		 }
		 
		 final XYSeriesCollection data = new XYSeriesCollection();
		 for(XYSeries s : series){
			 data.addSeries(s);
		 }
		 
		  final JFreeChart chart = ChartFactory.createXYLineChart(
		    		title,
		    		XSide, 
		    		YSide, 
			        data,
			        PlotOrientation.VERTICAL,
			        true,
			        true,
			        false
			    );

		    final ChartPanel chartPanel = new ChartPanel(chart);
		    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		    setContentPane(chartPanel);
		 
	}

}
