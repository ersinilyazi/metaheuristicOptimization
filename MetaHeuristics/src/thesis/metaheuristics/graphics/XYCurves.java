package thesis.metaheuristics.graphics;


import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class XYCurves extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5894636236105371625L;
	private Map<Integer, Double> cordinates= new HashMap<>(100);

	public XYCurves(String title,HashMap<Integer, Double> cordinates,String XSide,String YSide) {
			super(title);
			// TODO Auto-generated constructor stub
		    final XYSeries series = new XYSeries("Random Data");
		    //final XYSeries series2 = new XYSeries("Random Data2");

		    for (Entry<Integer, Double> entry : cordinates.entrySet()) {
				//System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
				series.add(entry.getKey().doubleValue(),entry.getValue().doubleValue());
				//series2.add(2*entry.getKey().doubleValue(),entry.getValue().doubleValue()/2);
			}
		    
		    final XYSeriesCollection data = new XYSeriesCollection(series);
		    //data.addSeries(series2);
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

	public Map<Integer, Double> getCordinates() {
		return cordinates;
	}

	public void setCordinates(Map<Integer, Double> cordinates) {
		this.cordinates = cordinates;
	}

}
