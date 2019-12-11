import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

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

public class CompareTools extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private GUI gui;
	
	//error tables
	private JTable dci_iPlasma=new JTable();
	private JTable dii_iPlasma=new JTable();
	private JTable adci_iPlasma=new JTable();
	private JTable adii_iPlasma=new JTable();
	private JTable dci_PMD=new JTable();
	private JTable dii_PMD=new JTable();
	private JTable adci_PMD=new JTable();
	private JTable adii_PMD=new JTable();

	//error table model
	private DefaultTableModel modelDCI_iPlasma = new DefaultTableModel();
	private DefaultTableModel modelDII_iPlasma = new DefaultTableModel();
	private DefaultTableModel modelADCI_iPlasma = new DefaultTableModel();
	private DefaultTableModel modelADII_iPlasma = new DefaultTableModel();
	private DefaultTableModel modelDCI_PMD = new DefaultTableModel();
	private DefaultTableModel modelDII_PMD = new DefaultTableModel();
	private DefaultTableModel modelADCI_PMD = new DefaultTableModel();
	private DefaultTableModel modelADII_PMD = new DefaultTableModel();

	//comparison error
	LinkedList<ComparisonError> errors_iPlasma=new LinkedList<ComparisonError>();
	LinkedList<ComparisonError> errors_PMD=new LinkedList<ComparisonError>();

	
	
	public CompareTools(GUI gui) {
		if(gui==null) {
			throw new IllegalArgumentException("Invalid argument. Given null GUI.");
		}else {
			this.gui=gui;
			setLayout(null);
			this.setBounds(0, 0, 622, 412);
			
			JLabel lblToolsComparison = new JLabel("Tools comparison");
			lblToolsComparison.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblToolsComparison.setBounds(216, 16, 150, 16);
			
			add(lblToolsComparison);
			
			JLabel lblIplasma = new JLabel("iPlasma");
			lblIplasma.setBounds(32, 54, 61, 16);
			add(lblIplasma);
			
			JLabel lblDci = new JLabel("DCI");
			lblDci.setBounds(42, 82, 31, 16);
			add(lblDci);
			
			JScrollPane scrollPaneDCI_iPlasma = new JScrollPane(dci_iPlasma);
			scrollPaneDCI_iPlasma.setBounds(70, 84, 84, 79);
			add(scrollPaneDCI_iPlasma);
			
			JLabel lblDii = new JLabel("DII");
			lblDii.setBounds(166, 82, 31, 16);
			add(lblDii);
			
			JScrollPane scrollPaneDII_iPlasma = new JScrollPane(dii_iPlasma);
			scrollPaneDII_iPlasma.setBounds(194, 84, 84, 79);
			add(scrollPaneDII_iPlasma);
			
			JScrollPane scrollPaneADII_iPlasma = new JScrollPane(adii_iPlasma);
			scrollPaneADII_iPlasma.setBounds(194, 177, 84, 79);
			add(scrollPaneADII_iPlasma);
			
			JLabel lblAdii = new JLabel("ADII");
			lblAdii.setBounds(166, 175, 31, 16);
			add(lblAdii);
			
			JScrollPane scrollPaneADCI_iPlasma = new JScrollPane(adci_iPlasma);
			scrollPaneADCI_iPlasma.setBounds(70, 177, 84, 79);
			add(scrollPaneADCI_iPlasma);
			
			JLabel lblAdci = new JLabel("ADCI");
			lblAdci.setBounds(32, 177, 61, 16);
			add(lblAdci);
			
			JLabel labelDCI_PMD = new JLabel("DCI");
			labelDCI_PMD.setBounds(352, 82, 31, 16);
			add(labelDCI_PMD);
			
			JScrollPane scrollPaneADII_PMD = new JScrollPane(adii_PMD);
			scrollPaneADII_PMD.setBounds(504, 177, 84, 79);
			add(scrollPaneADII_PMD);
			
			JScrollPane scrollPaneDII_PMD = new JScrollPane(dii_PMD);
			scrollPaneDII_PMD.setBounds(504, 84, 84, 79);
			add(scrollPaneDII_PMD);
			
			JLabel labelDII_PMD = new JLabel("DII");
			labelDII_PMD.setBounds(476, 82, 31, 16);
			add(labelDII_PMD);
			
			JScrollPane scrollPaneDCI_PMD = new JScrollPane(dci_PMD);
			scrollPaneDCI_PMD.setBounds(380, 84, 84, 79);
			add(scrollPaneDCI_PMD);
			
			JScrollPane scrollPaneADCI_PMD = new JScrollPane(adci_PMD);
			scrollPaneADCI_PMD.setBounds(380, 177, 84, 79);
			add(scrollPaneADCI_PMD);
			
			JLabel labelADCI_PMD = new JLabel("ADCI");
			labelADCI_PMD.setBounds(342, 177, 61, 16);
			add(labelADCI_PMD);
			
			JLabel lblPmd = new JLabel("PMD");
			lblPmd.setBounds(342, 54, 61, 16);
			add(lblPmd);
			
			JLabel labelADII_PMD = new JLabel("ADII");
			labelADII_PMD.setBounds(476, 177, 31, 16);
			add(labelADII_PMD);
			
			JLabel lblTheBestTool = new JLabel("The best tool is: ");
			lblTheBestTool.setToolTipText("The criteria to choose the best tool was the sum of DCI and ADCI errors. The greater the sum the better the tool.");
			lblTheBestTool.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			lblTheBestTool.setBounds(70, 337, 113, 16);
			add(lblTheBestTool);
			
			JLabel resultLabel = new JLabel("");
			resultLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			resultLabel.setBounds(194, 338, 113, 16);
			add(resultLabel);
			
			int iPlasma=fill_iPlasma();
			int pmd=fill_PMD();
			
			if(iPlasma>pmd) { //iPlasma the best
				resultLabel.setText("iPlasma");
			}else { //PMD the best
				resultLabel.setText("PMD");
			}
			
			//create histogram
			createHistogram(this.errors_iPlasma,"long_method iPlasma");
			createHistogram(this.errors_PMD,"long_method PMD");
		}
	}
	
	public int fill_iPlasma() {
		JTable table=gui.getCurrentExcelFileData();
		int numberDCI=0;
		int numberDII=0;
		int numberADCI=0;
		int numberADII=0;
		LinkedList<Integer> methodIDErrors_DCI=new LinkedList<Integer>();
		LinkedList<Integer> methodIDErrors_DII=new LinkedList<Integer>();
		LinkedList<Integer> methodIDErrors_ADCI=new LinkedList<Integer>();
		LinkedList<Integer> methodIDErrors_ADII=new LinkedList<Integer>();

		//loop over table
		for(int row=0;row!=table.getModel().getRowCount();row++) {
			//get default values
			String defaultValue=table.getModel().getValueAt(row,8).toString().toLowerCase();
			
			//get tool value
			String toolValue=table.getModel().getValueAt(row,9).toString().toLowerCase();
			
			//getting id from current line
			int methodID=(int)Double.parseDouble(table.getModel().getValueAt(row, 0).toString());
			
			if(defaultValue.equals("true") && toolValue.equals("true")) { //dci
				numberDCI+=1;
				methodIDErrors_DCI.add(methodID);
			}
			
			if(defaultValue.equals("false") && toolValue.equals("true")) { //dii
				numberDII+=1;
				methodIDErrors_DII.add(methodID);
			}
			
			if(defaultValue.equals("false") && toolValue.equals("false")) { //adci
				numberADCI+=1;
				methodIDErrors_ADCI.add(methodID);
			}
			
			if(defaultValue.equals("true") && toolValue.equals("false")) { //adii
				numberADII+=1;
				methodIDErrors_ADII.add(methodID);
			}
		}
		
		modelDCI_iPlasma.addColumn("MethodID");
		modelDII_iPlasma.addColumn("MethodID");
		modelADCI_iPlasma.addColumn("MethodID");
		modelADII_iPlasma.addColumn("MethodID");		
		
		errors_iPlasma.add(new ComparisonError("DCI",numberDCI,methodIDErrors_DCI));
		errors_iPlasma.add(new ComparisonError("DII",numberDII,methodIDErrors_DII));
		errors_iPlasma.add(new ComparisonError("ADCI",numberADCI,methodIDErrors_ADCI));
		errors_iPlasma.add(new ComparisonError("ADII",numberADII,methodIDErrors_ADII));
		
		add_iPlasmaErrors();

		return numberDCI+numberADCI;
	}
	
	public void add_iPlasmaErrors() {
		for(ComparisonError ce:errors_iPlasma) {
			if(ce.getErrorType().equals("ADII")) {
				for(Integer i:ce.getMethodIDErrors()) {
					modelADII_iPlasma.addRow(new Object[] { i.toString() });
				}
			}else {
				if(ce.getErrorType().equals("DCI")) {
					for(Integer i:ce.getMethodIDErrors()) {
						modelDCI_iPlasma.addRow(new Object[] { i.toString() });
					}
				}else {
					if(ce.getErrorType().equals("DII")) {
						for(Integer i:ce.getMethodIDErrors()) {
							modelDII_iPlasma.addRow(new Object[] { i.toString() });
						}
					}else {
						if(ce.getErrorType().equals("ADCI")) {
							for(Integer i:ce.getMethodIDErrors()) {
								modelADCI_iPlasma.addRow(new Object[] { i.toString() });
							}
						}
					}
				}
			}
		}

		this.dci_iPlasma.setModel(this.modelDCI_iPlasma);
		this.dii_iPlasma.setModel(this.modelDII_iPlasma);
		this.adci_iPlasma.setModel(this.modelADCI_iPlasma);
		this.adii_iPlasma.setModel(this.modelADII_iPlasma);
	}
	
	public int fill_PMD() {
		JTable table=gui.getCurrentExcelFileData();
		int numberDCI=0;
		int numberDII=0;
		int numberADCI=0;
		int numberADII=0;
		LinkedList<Integer> methodIDErrors_DCI=new LinkedList<Integer>();
		LinkedList<Integer> methodIDErrors_DII=new LinkedList<Integer>();
		LinkedList<Integer> methodIDErrors_ADCI=new LinkedList<Integer>();
		LinkedList<Integer> methodIDErrors_ADII=new LinkedList<Integer>();

		//loop over table
		for(int row=0;row!=table.getModel().getRowCount();row++) {
			//get default values
			String defaultValue=table.getModel().getValueAt(row,8).toString().toLowerCase();
			
			//get tool value
			String toolValue=table.getModel().getValueAt(row,10).toString().toLowerCase();
			
			//getting id from current line
			int methodID=(int)Double.parseDouble(table.getModel().getValueAt(row, 0).toString());
			
			if(defaultValue.equals("true") && toolValue.equals("true")) { //dci
				numberDCI+=1;
				methodIDErrors_DCI.add(methodID);
			}
			
			if(defaultValue.equals("false") && toolValue.equals("true")) { //dii
				numberDII+=1;
				methodIDErrors_DII.add(methodID);
			}
			
			if(defaultValue.equals("false") && toolValue.equals("false")) { //adci
				numberADCI+=1;
				methodIDErrors_ADCI.add(methodID);
			}
			
			if(defaultValue.equals("true") && toolValue.equals("false")) { //adii
				numberADII+=1;
				methodIDErrors_ADII.add(methodID);
			}
		}
		
		modelDCI_PMD.addColumn("MethodID");
		modelDII_PMD.addColumn("MethodID");
		modelADCI_PMD.addColumn("MethodID");
		modelADII_PMD.addColumn("MethodID");		
		
		errors_PMD.add(new ComparisonError("DCI",numberDCI,methodIDErrors_DCI));
		errors_PMD.add(new ComparisonError("DII",numberDII,methodIDErrors_DII));
		errors_PMD.add(new ComparisonError("ADCI",numberADCI,methodIDErrors_ADCI));
		errors_PMD.add(new ComparisonError("ADII",numberADII,methodIDErrors_ADII));
						
		add_PMDErrors();

		return numberDCI+numberADCI;

	}
	
	public void add_PMDErrors() {
		for(ComparisonError ce:errors_PMD) {
			if(ce.getErrorType().equals("ADII")) {
				for(Integer i:ce.getMethodIDErrors()) {
					modelADII_PMD.addRow(new Object[] { i.toString() });
				}
			}else {
				if(ce.getErrorType().equals("DCI")) {
					for(Integer i:ce.getMethodIDErrors()) {
						modelDCI_PMD.addRow(new Object[] { i.toString() });
					}
				}else {
					if(ce.getErrorType().equals("DII")) {
						for(Integer i:ce.getMethodIDErrors()) {
							modelDII_PMD.addRow(new Object[] { i.toString() });
						}
					}else {
						if(ce.getErrorType().equals("ADCI")) {
							for(Integer i:ce.getMethodIDErrors()) {
								modelADCI_PMD.addRow(new Object[] { i.toString() });
							}
						}
					}
				}
			}
		}

		this.dci_PMD.setModel(this.modelDCI_PMD);
		this.dii_PMD.setModel(this.modelDII_PMD);
		this.adci_PMD.setModel(this.modelADCI_PMD);
		this.adii_PMD.setModel(this.modelADII_PMD);
	}
	
	
	public void createHistogram(LinkedList<ComparisonError> errors, String errorType) {
		if(errors==null || errorType.length()==0) {
			throw new IllegalArgumentException("Invalid list or error type.");
		}else {
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
	
}
