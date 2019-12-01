import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaintSeveralErrors extends JPanel {
	//tables for long_method
	private JTable tableDCILM= new JTable();
	private DefaultTableModel modelDCILM = new DefaultTableModel();
	private JTable tableDIILM= new JTable();
	DefaultTableModel modelDIILM = new DefaultTableModel();
	private JTable tableADCILM= new JTable();
	DefaultTableModel modelADCILM = new DefaultTableModel();
	private JTable tableADIILM= new JTable();
	DefaultTableModel modelADIILM = new DefaultTableModel();


	//tables for feature_envy
	private JTable tableDCIFE= new JTable();
	DefaultTableModel modelDCIFE = new DefaultTableModel();
	private JTable tableDIIFE= new JTable();
	DefaultTableModel modelDIIFE = new DefaultTableModel();
	private JTable tableADCIFE= new JTable();
	DefaultTableModel modelADCIFE = new DefaultTableModel();
	private JTable tableADIIFE= new JTable();
	DefaultTableModel modelADIIFE = new DefaultTableModel();



	/**
	 * Create the panel.
	 */
	public PaintSeveralErrors(LinkedList<ComparisonError> errorsLM,LinkedList<ComparisonError> errorsFE,JFrame jframe, GUI gui,boolean close) {
		setLayout(null);
		this.setBounds(0, 0, 622, 412);

		addTableColumn("MethodID");

		JLabel lblErrorSummarylongmethod = new JLabel("Error Summary: long_method ");
		lblErrorSummarylongmethod.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblErrorSummarylongmethod.setBounds(19, 25, 236, 16);
		add(lblErrorSummarylongmethod);

		JLabel lblErrorSummaryFeatureenvy = new JLabel("Error Summary: feature_envy");
		lblErrorSummaryFeatureenvy.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblErrorSummaryFeatureenvy.setBounds(19, 210, 236, 16);
		add(lblErrorSummaryFeatureenvy);

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
		btnCloseResults.setBounds(499, 377, 117, 29);
		add(btnCloseResults);

		JLabel lblNewLabel = new JLabel("DCI");
		lblNewLabel.setBounds(29, 53, 61, 16);
		add(lblNewLabel);

		JScrollPane scrollPaneDCI_LM= new JScrollPane(this.tableDCILM);
		scrollPaneDCI_LM.setBounds(68, 53, 81, 60);
		add(scrollPaneDCI_LM);

		JLabel lblAdci = new JLabel("ADCI");
		lblAdci.setBounds(295, 53, 61, 16);
		add(lblAdci);

		JScrollPane scrollPaneADCI_LM = new JScrollPane(this.tableADCILM);
		scrollPaneADCI_LM.setBounds(344, 53, 81, 60);
		add(scrollPaneADCI_LM);

		JLabel lblDii = new JLabel("DII");
		lblDii.setBounds(29, 125, 61, 16);
		add(lblDii);

		JScrollPane scrollPaneDII_LM = new JScrollPane(this.tableDIILM);
		scrollPaneDII_LM.setBounds(68, 125, 81, 60);
		add(scrollPaneDII_LM);

		JLabel lblAdii = new JLabel("ADII");
		lblAdii.setBounds(295, 125, 61, 16);
		add(lblAdii);

		JScrollPane scrollPaneADII_LM = new JScrollPane(this.tableADIILM);
		scrollPaneADII_LM.setBounds(344, 125, 81, 60);
		add(scrollPaneADII_LM);

		JLabel label = new JLabel("DCI");
		label.setBounds(29, 238, 61, 16);
		add(label);

		JScrollPane scrollPaneDCI_FE = new JScrollPane(this.tableDCIFE);
		scrollPaneDCI_FE.setBounds(68, 238, 81, 60);
		add(scrollPaneDCI_FE);

		JLabel label_1 = new JLabel("ADCI");
		label_1.setBounds(295, 238, 61, 16);
		add(label_1);

		JScrollPane scrollPaneADCI_FE = new JScrollPane(this.tableADCIFE);
		scrollPaneADCI_FE.setBounds(344, 238, 81, 60);
		add(scrollPaneADCI_FE);

		JLabel label_2 = new JLabel("DII");
		label_2.setBounds(29, 311, 61, 16);
		add(label_2);

		JScrollPane scrollPaneDII_FE = new JScrollPane(this.tableDIIFE);
		scrollPaneDII_FE.setBounds(68, 310, 81, 60);
		add(scrollPaneDII_FE);

		JLabel label_3 = new JLabel("ADII");
		label_3.setBounds(295, 310, 61, 16);
		add(label_3);

		JScrollPane scrollPaneADII_FE = new JScrollPane(this.tableADIIFE);
		scrollPaneADII_FE.setBounds(344, 310, 81, 60);
		add(scrollPaneADII_FE);

		addLMErrors(errorsLM);
		addFEErrors(errorsFE);

		createHistogram(errorsLM,"Long Method");
		createHistogram(errorsFE,"Feature Envy");

		this.setVisible(true);

	}

	public void addTableColumn(String method) {
		this.modelDCIFE.addColumn(method);
		this.modelDCILM.addColumn(method);
		this.modelDIILM.addColumn(method);
		this.modelDIIFE.addColumn(method);
		this.modelADCIFE.addColumn(method);
		this.modelADCILM.addColumn(method);
		this.modelADIIFE.addColumn(method);
		this.modelADIILM.addColumn(method);
	}

	public void addLMErrors(LinkedList<ComparisonError> errorsLM){

		for(ComparisonError ce:errorsLM) {
			if(ce.getErrorType().equals("ADII")) {
				for(Integer i:ce.getMethodIDErrors()) {
					modelADIILM.addRow(new Object[] { i.toString() });
				}
			}else {
				if(ce.getErrorType().equals("DCI")) {
					for(Integer i:ce.getMethodIDErrors()) {
						modelDCILM.addRow(new Object[] { i.toString() });
					}
				}else {
					if(ce.getErrorType().equals("DII")) {
						for(Integer i:ce.getMethodIDErrors()) {
							modelDIILM.addRow(new Object[] { i.toString() });
						}
					}else {
						if(ce.getErrorType().equals("ADCI")) {
							for(Integer i:ce.getMethodIDErrors()) {
								modelADCILM.addRow(new Object[] { i.toString() });
							}
						}
					}
				}
			}
		}

		this.tableDCILM.setModel(this.modelDCILM);
		this.tableDIILM.setModel(this.modelDIILM);
		this.tableADCILM.setModel(this.modelADCILM);
		this.tableADIILM.setModel(this.modelADIILM);

	}


	public void addFEErrors(LinkedList<ComparisonError> errorsFE){
		for(ComparisonError ce:errorsFE) {
			if(ce.getErrorType().equals("ADII")) {
				for(Integer i:ce.getMethodIDErrors()) {
					modelADIIFE.addRow(new Object[] { i.toString() });
				}
			}else {
				if(ce.getErrorType().equals("DCI")) {
					for(Integer i:ce.getMethodIDErrors()) {
						modelDCIFE.addRow(new Object[] { i.toString() });
					}
				}else {
					if(ce.getErrorType().equals("DII")) {
						for(Integer i:ce.getMethodIDErrors()) {
							modelDIIFE.addRow(new Object[] { i.toString() });
						}
					}else {
						if(ce.getErrorType().equals("ADCI")) {
							for(Integer i:ce.getMethodIDErrors()) {
								modelADCIFE.addRow(new Object[] { i.toString() });
							}
						}
					}
				}
			}
		}

		this.tableDCIFE.setModel(this.modelDCIFE);
		this.tableDIIFE.setModel(this.modelDIIFE);
		this.tableADCIFE.setModel(this.modelADCIFE);
		this.tableADIIFE.setModel(this.modelADIIFE);
	}


	private void createHistogram(LinkedList<ComparisonError> errors, String errorType) {
		DefaultCategoryDataset dcd=new DefaultCategoryDataset();

		//add error information
		for(ComparisonError ce:errors) {
			dcd.setValue(ce.getNumberErrors(), "Error", ce.getErrorType());
		}

		JFreeChart jchart=ChartFactory.createBarChart("Errors Occurred", "Error Type", "Error Frequency", dcd, PlotOrientation.VERTICAL, true, true,false);

		CategoryPlot plot= jchart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.BLACK);

		ChartFrame chartFrm=new ChartFrame("Errors Occured "+errorType,jchart,true);
		chartFrm.setVisible(true);
		chartFrm.setSize(700,500);
	}

}
