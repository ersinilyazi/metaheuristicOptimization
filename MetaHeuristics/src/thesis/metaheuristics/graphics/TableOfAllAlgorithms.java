package thesis.metaheuristics.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableOfAllAlgorithms  extends 	JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4784934767309812637L;

	// Instance attributes used in this example
		private	JPanel		topPanel;
		private	JTable		table;
		private	JScrollPane scrollPane;
		
		// Constructor of main frame
		public TableOfAllAlgorithms(String dataValues[][])
		{
			// Set the frame characteristics
			setTitle( "Metaheristic Benchmark Algorithm Evaluation" );
			setSize( 900, 900 );
			setBackground( Color.gray );

			// Create a panel to hold all other components
			topPanel = new JPanel();
			topPanel.setLayout( new BorderLayout() );
			getContentPane().add( topPanel );

			// Create columns names
			String columnNames[] = { "Test Functions", "Algorithms", "Mean(fbest)", "Median(fbest)", "Std(fbest)", "Time/run", "Success" };
			List<String> tableRows = new ArrayList<>();
			// Create some data
			
			// Create a new table instance
			table = new JTable( dataValues, columnNames );

			// Add the table to a scrolling pane
			scrollPane = new JScrollPane( table );
			topPanel.add( scrollPane, BorderLayout.CENTER );
		}

		// Main entry point for this example
		public static void main( String args[] )
		{
			// Create an instance of the test application
			String dataValues[][]= null;
			TableOfAllAlgorithms mainFrame	= new TableOfAllAlgorithms( dataValues);
			mainFrame.setVisible( true );
		}
}
