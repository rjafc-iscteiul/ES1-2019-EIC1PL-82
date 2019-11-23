import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class paintError extends JPanel {

	/**
	 * Create the panel.
	 */
	public paintError(LinkedList<ComparisonError> errors) {
		
		this.setBounds(0, 0, 622, 412);
		
		
		DefaultCategoryDataset dcd=new DefaultCategoryDataset();
		
		//add error information
		for(ComparisonError ce:errors) {
			dcd.setValue(ce.getNumberErrors(), "Error", ce.getErrorType());
		}
		
		JFreeChart jchart=ChartFactory.createBarChart("Errors Occurred", "Error Type", "Error Frequency", dcd, PlotOrientation.VERTICAL, true, true,false);
		
		CategoryPlot plot= jchart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.BLACK);
		
		ChartFrame chartFrm=new ChartFrame("Errors Occured",jchart,true);
		chartFrm.setVisible(true);
		chartFrm.setSize(700,500);
		
		this.setVisible(true);
		
	}
}
