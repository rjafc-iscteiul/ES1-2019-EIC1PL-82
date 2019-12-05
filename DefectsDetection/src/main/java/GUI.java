import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

public class GUI {

	private JFrame frame;
	private JTable table;

	
	private final static int LOC=80;
	private final static int CYCLO=80;
	private final static int ATFD=4;
	private final static double LAA=0.42;

	private int current_LOC;
	private int current_CYCLO;
	private int current_ATFD;
	private double current_LAA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 622, 412);
		
		
		frame = new JFrame("Defects Detection v1.0");
		frame.setBounds(100, 100, 622, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(panel);
		
		table = new JTable();
		
		JScrollPane scroll = new JScrollPane(table);
		panel.add(scroll);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnExcel = new JMenu("File");
		menuBar.add(mnExcel);
		
		JMenuItem mntmOpen = new JMenuItem("Open Excel");
		mntmOpen.addActionListener(new ActionListener() {
			
			//defining what happens when we click Open under menu Excel
			
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {   //file chosen correctly
					
					File selectedFile = jfc.getSelectedFile();
					ExcelReader reader = new ExcelReader();
					DefaultTableModel tableData = reader.readFile(selectedFile.getAbsolutePath());
					table.setModel(tableData);
				}
				
			}
			
		});
		mnExcel.add(mntmOpen);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmCompareTools = new JMenuItem("Compare tools");
		mntmCompareTools.addActionListener(event -> {
			frame.setContentPane(new CompareTools(this));
			frame.revalidate();
		});
		mnTools.add(mntmCompareTools);
		
		JMenu mnRules = new JMenu("Rules");
		menuBar.add(mnRules);
		
		JMenu mnDefaultRules = new JMenu("Default Rules");
		mnRules.add(mnDefaultRules);
		
		JMenuItem mntmResetValues = new JMenuItem("Reset values and rules");
		mntmResetValues.addActionListener(new ActionListener() {
			
			//create a pop-up to indicate the reset has been done successfully 
			public void actionPerformed(ActionEvent e) {
				
				//make all values default
				current_LOC=LOC;
				current_CYCLO=CYCLO;
				current_ATFD=ATFD;
				current_LAA=LAA;
				
				ResetValuesRules rvr = new ResetValuesRules();
				rvr.setVisible(true);
			}
		});
		mnDefaultRules.add(mntmResetValues);
		
		JMenuItem mntmCompareUsindDefault = new JMenuItem("Compare using default");
		mntmCompareUsindDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//launch warning about values going to default	
				frame.revalidate();
				frame.repaint();
				compareWithDefault();
			}
		});
		mnDefaultRules.add(mntmCompareUsindDefault);
		
		JMenu mnEditRules = new JMenu("Edit Rules");
		mnRules.add(mnEditRules);
		
		JMenuItem mntmChangeThresholds = new JMenuItem("Change thresholds");
		mntmChangeThresholds.addActionListener(event -> {
			frame.setContentPane(new ChangeThresholds(frame,this));
			frame.revalidate();
		});
		mnEditRules.add(mntmChangeThresholds);
		
		
		JMenuItem mntmCreateNewRules = new JMenuItem("Create new rules");
		mntmCreateNewRules.addActionListener(event -> {
			frame.setContentPane(new CreateRules(frame,this));
			frame.revalidate();
		});
		
		
		mnEditRules.add(mntmCreateNewRules);
		
		JMenu mnAbout = new JMenu("Help");
		
		menuBar.add(mnAbout);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			//opens frame About
			public void actionPerformed(ActionEvent e) {
				About about=new About();
				about.setVisible(true);
			}
		});
		mnAbout.add(mntmAbout);
		frame.getContentPane().setLayout(null);
	}
	
	public void assignThreshholds(int LOC, int CYCLO, int ATFD, double LAA) {
		this.current_LOC = LOC;
		this.current_CYCLO = CYCLO;
		this.current_ATFD = ATFD;
		this.current_LAA = LAA;
	}
	
	public JTable getCurrentExcelFileData() {
		return this.table;
	}
	
	
	public void compareWithDefault(){
		
		//long_method errors
		int numberDCI=0;
		int numberDII=0;
		int numberADCI=0;
		int numberADII=0;

		LinkedList<Integer> errorDCI=new LinkedList<Integer>();
		LinkedList<Integer> errorDII=new LinkedList<Integer>();
		LinkedList<Integer> errorADCI=new LinkedList<Integer>();
		LinkedList<Integer> errorADII=new LinkedList<Integer>();

		
		//long_method errors
		int numberDCIFE=0;
		int numberDIIFE=0;
		int numberADCIFE=0;
		int numberADIIFE=0;

		LinkedList<Integer> errorDCIFE=new LinkedList<Integer>();
		LinkedList<Integer> errorDIIFE=new LinkedList<Integer>();
		LinkedList<Integer> errorADCIFE=new LinkedList<Integer>();
		LinkedList<Integer> errorADIIFE=new LinkedList<Integer>();
		
		String finalRuleLM;
		String finalRuleFE;
		String ruleLM="LOC>80 && CYCLO>10";
		String ruleFE="ATFD>4 && LAA<0.42";

		
		//getting error info
		//comparing (code font: https://stackoverflow.com/questions/19383953/is-it-possible-to-evaluate-a-boolean-expression-for-string-comparions)
		try {

            ScriptEngineManager sem = new ScriptEngineManager();
            ScriptEngine se = sem.getEngineByName("JavaScript");
            
			//looping over rows
			for(int numRow=0;numRow!=table.getModel().getRowCount();numRow++) {
				finalRuleLM=ruleLM;
				if(finalRuleLM.contains("LOC")) {
					
					//replace LOC value in rule for threshold value
					finalRuleLM=finalRuleLM.replace("LOC", String.valueOf(table.getModel().getValueAt(numRow,4)));
				}
				
				
				if(finalRuleLM.contains("CYCLO")) {
					
					//replace CYCLO value in rule for threshold value
					finalRuleLM=finalRuleLM.replace("CYCLO", String.valueOf(table.getModel().getValueAt(numRow,5)));
				}
			
				if(finalRuleLM.contains("ATFD")) {
					
					//replace ATFD value in rule for threshold value
					finalRuleLM=finalRuleLM.replace("ATFD", String.valueOf(table.getModel().getValueAt(numRow,6)));
				}
				
				if(finalRuleLM.contains("LAA")) {					
					//replace LOC value in rule for threshold value
					finalRuleLM=finalRuleLM.replace("LAA", String.valueOf(table.getModel().getValueAt(numRow,7)));
				}
				
				
				//getting long_method from excel file
				String getExcelLM=table.getModel().getValueAt(numRow, 8).toString().toLowerCase();
				
				//getting long_method with defined rule
				String myResult=se.eval(finalRuleLM).toString();
				
				
				int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow, 0).toString());
				
				
				//DCI error
				if(myResult.equals("true") && getExcelLM.equals("true")) {
					numberDCI+=1;
					
					errorDCI.add(methodID);
				}
				
				//DII error
				if(myResult.equals("true") && getExcelLM.equals("false")) {
					numberDII+=1;
					errorDII.add(methodID);
				}
				
				//ADCI error
				if(myResult.equals("false") && getExcelLM.equals("false")) {
					numberADCI+=1;
					errorADCI.add(methodID);
				}
				
				//ADII error
				if(myResult.equals("false") && getExcelLM.equals("true")) {
					numberADII+=1;
					errorADII.add(methodID);
				}
				
			}	       
			
			
			//looping over rows
			for(int numRow=0;numRow!=table.getModel().getRowCount();numRow++) {
				finalRuleFE=ruleFE;
				if(finalRuleFE.contains("LOC")) {
					
					//replace LOC value in rule for threshold value
					finalRuleFE=finalRuleFE.replace("LOC", String.valueOf(table.getModel().getValueAt(numRow,4)));
				}
				
				
				if(finalRuleFE.contains("CYCLO")) {
					
					//replace CYCLO value in rule for threshold value
					finalRuleFE=finalRuleFE.replace("CYCLO", String.valueOf(table.getModel().getValueAt(numRow,5)));
				}
			
				if(finalRuleFE.contains("ATFD")) {
					//replace ATFD value in rule for threshold value
					finalRuleFE=finalRuleFE.replace("ATFD", String.valueOf(table.getModel().getValueAt(numRow,6)));
				}
				
				if(finalRuleFE.contains("LAA")) {
					
					//replace LOC value in rule for threshold value
					finalRuleFE=finalRuleFE.replace("LAA", String.valueOf(table.getModel().getValueAt(numRow,7)));
				}
				
				
				
				//getting long_method from excel file
				String getExcelLM=table.getModel().getValueAt(numRow, 8).toString().toLowerCase();
				
				//getting long_method with defined rule
				String myResult=se.eval(finalRuleFE).toString();
				
				
				int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow, 0).toString());
				
				
				//DCI error
				if(myResult.equals("true") && getExcelLM.equals("true")) {
					numberDCIFE+=1;
					
					errorDCIFE.add(methodID);
				}
				
				//DII error
				if(myResult.equals("true") && getExcelLM.equals("false")) {
					numberDIIFE+=1;
					errorDIIFE.add(methodID);
				}
				
				//ADCI error
				if(myResult.equals("false") && getExcelLM.equals("false")) {
					numberADCIFE+=1;
					errorADCIFE.add(methodID);
				}
				
				//ADII error
				if(myResult.equals("false") && getExcelLM.equals("true")) {
					numberADIIFE+=1;
					errorADIIFE.add(methodID);
				}
				
			}	       
			
			//paint comparison results in GUI
			ComparisonError dci=new ComparisonError("DCI",numberDCI,errorDCI);
			ComparisonError dii=new ComparisonError("DII",numberDII,errorDII);
			ComparisonError adci=new ComparisonError("ADCI",numberADCI,errorADCI);
			ComparisonError adii=new ComparisonError("ADII",numberADII,errorADII);

			LinkedList<ComparisonError> errors=new LinkedList<ComparisonError>();
			errors.add(dci);
			errors.add(dii);
			errors.add(adci);
			errors.add(adii);
			
			
			
			ComparisonError dciFE=new ComparisonError("DCI",numberDCIFE,errorDCIFE);
			ComparisonError diiFE=new ComparisonError("DII",numberDIIFE,errorDIIFE);
			ComparisonError adciFE=new ComparisonError("ADCI",numberADCIFE,errorADCIFE);
			ComparisonError adiiFE=new ComparisonError("ADII",numberADIIFE,errorADIIFE);

			LinkedList<ComparisonError> errorsFE=new LinkedList<ComparisonError>();
			errorsFE.add(dciFE);
			errorsFE.add(diiFE);
			errorsFE.add(adciFE);
			errorsFE.add(adiiFE);
			
			
			
			frame.setContentPane(new PaintSeveralErrors(errors,errorsFE,frame,this,true));
            

        } catch (ScriptException error) {

            System.out.println("Invalid Expression");
            
            //lauch a error message on gui 
            
            new InvalidExpression().setVisible(true);
            
            //error.printStackTrace();

        }
		
		
	}
	
}
