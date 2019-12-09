import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class paintError extends JPanel {
	private JTable tableDCI= new JTable();
	private JTable tableDII= new JTable();
	private JTable tableADCI= new JTable();
	private JTable tableADII= new JTable();


	/**
	 * Create the panel.
	 */
	public paintError(LinkedList<ComparisonError> errors, JFrame jframe, GUI gui,boolean close) {

		this.setBounds(0, 0, 622, 412);
		setLayout(null);

		JLabel lblErrorSummary = new JLabel("Error Summary");
		lblErrorSummary.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblErrorSummary.setBounds(34, 40, 126, 16);
		add(lblErrorSummary);

		JLabel lblDci = new JLabel("DCI");
		lblDci.setToolTipText("MethodID from the methods with DCI error.");
		lblDci.setBounds(59, 86, 61, 16);
		add(lblDci);

		JPanel panelDCI = new JPanel();
		panelDCI.setBounds(98, 86, 87, 100);
		add(panelDCI);
		panelDCI.setLayout(null);

		JScrollPane scrollPaneDCI = new JScrollPane(tableDCI);
		scrollPaneDCI.setToolTipText("MethodID from the methods with DCI error.");
		scrollPaneDCI.setBounds(0, 0, 87, 100);
		panelDCI.add(scrollPaneDCI);

		DefaultTableModel modelDCI = new DefaultTableModel();
		modelDCI.addColumn("MethodID");

		//add DCI content
		for(ComparisonError ce:errors) {
			if(ce.getErrorType().equals("DCI")) {
				for(Integer i:ce.getMethodIDErrors()) {
					modelDCI.addRow(new Object[] { i.toString() });
				}
			}
		}


		tableDCI.setModel(modelDCI);

		JLabel lblDii = new JLabel("DII");
		lblDii.setToolTipText("MethodID from the methods with DII error.");
		lblDii.setBounds(59, 229, 61, 16);
		add(lblDii);

		JScrollPane scrollPaneDII = new JScrollPane(tableDII);
		scrollPaneDII.setToolTipText("MethodID from the methods with DII error.");
		scrollPaneDII.setBounds(98, 229, 87, 100);
		add(scrollPaneDII);

		DefaultTableModel modelDII = new DefaultTableModel();
		modelDII.addColumn("MethodID");

		//add DII content
		for(ComparisonError ce:errors) {
			if(ce.getErrorType().equals("DII")) {
				for(Integer i:ce.getMethodIDErrors()) {
					modelDII.addRow(new Object[] { i.toString() });
				}
			}
		}

		tableDII.setModel(modelDII);

		JLabel lblAdci = new JLabel("ADCI");
		lblAdci.setToolTipText("MethodID from the methods with ADCI error.");
		lblAdci.setBounds(351, 86, 61, 16);
		add(lblAdci);

		JScrollPane scrollPaneADCI = new JScrollPane(tableADCI);
		scrollPaneADCI.setToolTipText("MethodID from the methods with ADCI error.");
		scrollPaneADCI.setBounds(399, 86, 87, 100);
		add(scrollPaneADCI);

		DefaultTableModel modelADCI = new DefaultTableModel();
		modelADCI.addColumn("MethodID");

		//add ADCI content
		for(ComparisonError ce:errors) {
			if(ce.getErrorType().equals("ADCI")) {
				for(Integer i:ce.getMethodIDErrors()) {
					modelADCI.addRow(new Object[] { i.toString() });
				}
			}
		}

		tableADCI.setModel(modelADCI);


		JLabel lblAdii = new JLabel("ADII");
		lblAdii.setToolTipText("MethodID from the methods with ADII error.");
		lblAdii.setBounds(351, 229, 61, 16);
		add(lblAdii);

		JScrollPane scrollPaneADII = new JScrollPane(tableADII);
		scrollPaneADII.setToolTipText("MethodID from the methods with ADII error.");
		scrollPaneADII.setBounds(399, 229, 87, 100);
		add(scrollPaneADII);


		DefaultTableModel modelADII = new DefaultTableModel();
		modelADII.addColumn("MethodID");

		//add ADII content
		for(ComparisonError ce:errors) {
			if(ce.getErrorType().equals("ADII")) {
				for(Integer i:ce.getMethodIDErrors()) {
					modelADII.addRow(new Object[] { i.toString() });
				}
			}
		}

		tableADII.setModel(modelADII);

		JButton btnCloseResults = new JButton("Close Results");
		btnCloseResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(close){
					new CreateRules(jframe,gui).setBounds(8,18, 630, 400);
				}
				else{
					new ChangeThresholds(jframe, gui).setBounds(8,18, 630, 400);
				}
			}
		});
		btnCloseResults.setBounds(486, 365, 117, 29);
		add(btnCloseResults);


		createHistogram(errors);


		this.setVisible(true);

	}


	private void createHistogram(LinkedList<ComparisonError> errors) {
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
	}

}
