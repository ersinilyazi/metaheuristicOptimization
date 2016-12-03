package thesis.metaheuristics.graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class Curves extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3199386838879928713L;
	private Map<Integer, Double> cordinates= new HashMap<>(100);

	public Curves(HashMap<Integer, Double> cordinates){
		this.setCordinates(cordinates);
		
	}

	
	@SuppressWarnings("unchecked")
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		g.setColor(Color.RED);
		
		List<Point2D.Double> fsd= new ArrayList<Point2D.Double>();
		Point2D.Double [] punto = new Point2D.Double[200] ;/*new Point2D.Double(50,10)*/;
		Iterator itr = cordinates.entrySet().iterator();
		int i=0;
		for (Entry<Integer, Double> entry : cordinates.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			punto[i]=new Point2D.Double();
		    punto[i].setLocation(entry.getValue().doubleValue(),entry.getKey());
		    i++;
		}
		
		final XYSeries series = new XYSeries("Random Data");
		
		XYSeriesCollection data = new XYSeriesCollection(series);
		final JFreeChart chart = ChartFactory.createXYLineChart(
		        "XY Series Demo",
		        "X", 
		        "Y", 
		        data,
		        PlotOrientation.VERTICAL,
		        true,
		        true,
		        false
		    );

		    final ChartPanel chartPanel = new ChartPanel(chart);
		    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		    //setContentPane(chartPanel);
		
		
		Graphics2D g2 = (Graphics2D)g;
		QuadCurve2D.Double curve = new QuadCurve2D.Double();
		
		curve.setCurve(punto,1);

		//QuadCurve2D.Double curve = new QuadCurve2D.Double(50,10,50,150,250,150);
		 
		//g.drawLine(100, 100, 200, 200);  // Draw in the default coordinate system
		//g.translate(100.0, 100.0);       // Move the origin down and to the right
		//g.drawLine(0, 0, 100, 100); 
 
		
         ((Graphics2D)g).draw(curve);
		
	}
	public Map<Integer, Double> getCordinates() {
		return cordinates;
	}
	public void setCordinates(HashMap<Integer, Double> cordinates) {
		this.cordinates = cordinates;
	}

	
}
