import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

/**
 * @version 1.0
 * @author Miguel
 * @author Joao
 */
public class ChangeThresholds extends JPanel {
	private static final long serialVersionUID = 1L;
	private GUI gui;
	private final int empty=0;
	private final int error=1;
	private final int AllValuesGood=2;
	private boolean LM;
	private boolean FE;


	private JTextField locTextField;
	private JTextField cycloTextField;
	private JTextField atfdTextField;
	private JTextField laaTextField;
	/**
	 * Value for the lines of code
	 */
	private int LOC;
	/**
	 * Value for the cycles
	 */
	private int CYCLO;
	/**
	 * value for the ATFD
	 */
	private int ATFD;
	/**
	 * value for the LAA
	 */
	private double LAA;


	//	Lists of the methodID for every respective error
	private LinkedList<Integer> errorDCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorDII=new LinkedList<Integer>();
	private LinkedList<Integer> errorADCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorADII=new LinkedList<Integer>();

	private LinkedList<Integer> errorDCIFE=new LinkedList<Integer>();
	private LinkedList<Integer> errorDIIFE=new LinkedList<Integer>();
	private LinkedList<Integer> errorADCIFE=new LinkedList<Integer>();
	private LinkedList<Integer> errorADIIFE=new LinkedList<Integer>();

	/**
	 * Create the panel.
	 */
	public ChangeThresholds(JFrame jframe, GUI guii) {
		this.gui=guii;
		setLayout(null);
		this.setBounds(0, 0, 622, 412);

		JLabel lblLongmethod = new JLabel("long_method");
		lblLongmethod.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblLongmethod.setBounds(24, 23, 101, 16);
		add(lblLongmethod);

		JLabel lblLoc = new JLabel("LOC >");
		lblLoc.setBounds(50, 56, 61, 16);
		add(lblLoc);

		locTextField = new JTextField();
		locTextField.setBounds(93, 51, 39, 26);
		add(locTextField);
		locTextField.setColumns(10);
		locTextField.setToolTipText("Default value for this field is 80.");

		JLabel lblAnd = new JLabel("and");
		lblAnd.setBounds(144, 56, 30, 16);
		add(lblAnd);

		cycloTextField = new JTextField();
		cycloTextField.setBounds(249, 51, 39, 26);
		add(cycloTextField);
		cycloTextField.setColumns(10);
		cycloTextField.setToolTipText("Default value for this field is 10.");

		JLabel lblCyclo = new JLabel("CYCLO >");
		lblCyclo.setBounds(186, 56, 61, 16);
		add(lblCyclo);

		JLabel lblFeatureenvy = new JLabel("feature_envy");
		lblFeatureenvy.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblFeatureenvy.setBounds(24, 120, 101, 16);
		add(lblFeatureenvy);

		JLabel lblNewLabel = new JLabel("ATFD  >");
		lblNewLabel.setBounds(50, 161, 61, 16);
		add(lblNewLabel);

		atfdTextField = new JTextField();
		atfdTextField.setBounds(110, 156, 39, 26);
		add(atfdTextField);
		atfdTextField.setColumns(10);
		atfdTextField.setToolTipText("Default value for this field is 4.");


		JLabel lblAnd_1 = new JLabel("and");
		lblAnd_1.setBounds(161, 161, 39, 16);
		add(lblAnd_1);

		JLabel lblLaa = new JLabel("LAA  <");
		lblLaa.setBounds(203, 161, 61, 16);
		add(lblLaa);

		laaTextField = new JTextField();
		laaTextField.setBounds(249, 156, 41, 26);
		add(laaTextField);
		laaTextField.setColumns(10);
		laaTextField.setToolTipText("Default value for this field is 0,42.");


		JButton btnApplyChanges = new JButton("Apply changes");
		btnApplyChanges.setBounds(382, 360, 117, 29);
		add(btnApplyChanges);
		btnApplyChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkTextBoxes();
				gui.setLOC(LOC);
				gui.setCYCLO(CYCLO);
				gui.setATFD(ATFD);
				gui.setLAA(LAA);
				if(checkValues(locTextField.getText(),cycloTextField.getText(),atfdTextField.getText(),laaTextField.getText())){
					LOC=Integer.parseInt(locTextField.getText());
					CYCLO=Integer.parseInt(cycloTextField.getText());
					ATFD=Integer.parseInt(atfdTextField.getText());
					LAA=Integer.parseInt(laaTextField.getText());
					gui.assignThreshholds(LOC, CYCLO, ATFD, LAA);
				}
			}
		});


		JButton btnNewButton = new JButton("Compare");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkTextBoxes();
				gui.setLOC(LOC);
				gui.setCYCLO(CYCLO);
				gui.setATFD(ATFD);
				gui.setLAA(LAA);
				String loctext=locTextField.getText();
				String cictext=cycloTextField.getText();
				String atfdtext=atfdTextField.getText();
				String laatext=laaTextField.getText();
				if(checkValues(loctext,cictext,atfdtext,laatext)){
					//				get Table
					JTable table=gui.getCurrentExcelFileData();
					if(LM && !FE){
						LOC=Integer.parseInt(locTextField.getText());
						CYCLO=Integer.parseInt(cycloTextField.getText());
						compareLM(table,LOC,CYCLO);
					}
					if(!LM && FE){
						//		get variables ATFD and LAA
						ATFD=Integer.parseInt(atfdTextField.getText());
						LAA=Double.parseDouble(laaTextField.getText());
						compareFE(table,ATFD,LAA);
					}
					if(LM && FE){
						LOC=Integer.parseInt(locTextField.getText());
						CYCLO=Integer.parseInt(cycloTextField.getText());
						//		get variables ATFD and LAA
						ATFD=Integer.parseInt(atfdTextField.getText());
						LAA=Double.parseDouble(laaTextField.getText());
						
						compareLM(table,LOC,CYCLO);
						compareFE(table,ATFD,LAA);
					}
					paintWithErrors(jframe,gui);
				}
			}
		});
		
		btnNewButton.setBounds(499, 360, 117, 29);
		add(btnNewButton);

		jframe.setContentPane(this);
		this.setVisible(true);

	}
	/**
	 * Returns all DCI errors detected for the Long_method
	 * @return Returns all DCI errors detected for the Long_method
	 */
	public int getDCI(){
		return errorDCI.size();
	}
	/**
	 * Returns all DII errors detected for the Long_method
	 * @return Returns all DII errors detected for the Long_method
	 */
	public int getDII(){
		return errorDII.size();
	}
	/**
	 * Returns all ADCI errors detected for the Long_method
	 * @return Returns all ADCI errors detected for the Long_method
	 */
	public int getADCI(){
		return errorADCI.size();
	}
	/**
	 * Returns all ADII errors detected for the Long_method
	 * @return Returns all ADII errors detected for the Long_method
	 */
	public int getADII(){
		return errorADII.size();
	}
	/**
	 * Returns all DCI errors detected for the Feature_Envy
	 * @return Returns all DCI errors detected for the Feature_Envy
	 */
	public int getDCI_FE(){
		return errorDCIFE.size();
	}
	/**
	 * Returns all DII errors detected for the Feature_Envy
	 * @return Returns all DII errors detected for the Feature_Envy
	 */
	public int getDII_FE(){
		return errorDIIFE.size();
	}
	/**
	 * Returns all ADCI errors detected for the Feature_Envy
	 * @return Returns all ADCI errors detected for the Feature_Envy
	 */
	public int getADCI_FE(){
		return errorADCIFE.size();
	}
	/**
	 * Returns all ADII errors detected for the Feature_Envy
	 * @return Returns all ADII errors detected for the Feature_Envy
	 */
	public int getADII_FE(){
		return errorADIIFE.size();
	}

	
	/**
	 * Shows all the errors in a histogram
	 * @param frame The frame to show the histogram 
	 * @param gui The GUI thats being used
	 */
	public void paintWithErrors(JFrame frame,GUI gui) {	
		LinkedList<ComparisonError> errors=new LinkedList<ComparisonError>();
		LinkedList<ComparisonError> errorsFE=new LinkedList<ComparisonError>();

		errors.add(new ComparisonError("DCI",errorDCI.size(),errorDCI));
		errors.add(new ComparisonError("DII",errorDII.size(),errorDII));
		errors.add(new ComparisonError("ADCI",errorADCI.size(),errorADCI));
		errors.add(new ComparisonError("ADII",errorADII.size(),errorADII));

		errorsFE.add(new ComparisonError("DCI",errorDCIFE.size(),errorDCIFE));
		errorsFE.add(new ComparisonError("DII",errorDIIFE.size(),errorDIIFE));
		errorsFE.add(new ComparisonError("ADCI",errorADCIFE.size(),errorADCIFE));
		errorsFE.add(new ComparisonError("ADII",errorADIIFE.size(),errorADIIFE));

		if(LM && !FE){
			frame.setContentPane(new paintError(errors,frame, gui,false));
		}
		if(FE && !LM){
			frame.setContentPane(new paintError(errorsFE,frame, gui,false));
		}
		if(LM && FE)
			frame.setContentPane(new PaintSeveralErrors(errors,errorsFE,frame,gui,false));
	}
	
	/**
	 * Checks if the text fields for the long method are filled , partially filled or empty
	 * @param loc LOC text field
	 * @param cic cic text field
	 * @return returns 0 if empty, 1 if it is partially filled and 2 if fully filled
	 */
	public int checkLM(String loc, String cic){
		if(loc.equals("") && !cic.equals("")){
			return error;
		}
		if(!loc.equals("") && cic.equals("")){
			return error;
		}
		if(loc.equals("") && cic.equals("")){
			return empty;
		}
		return AllValuesGood;
	}
	/**
	 * Checks if the text fields for the Feature Envy are filled , partially filled or empty
	 * @param atfd ATFD text field
	 * @param laa LAA text field
	 * @return returns 0 if empty, 1 if it is partially filled and 2 if fully filled
	 */
	public int checkFE(String atfd, String laa){
		if(atfd.equals("") && !laa.equals("")){
			return error;
		}
		if(!atfd.equals("") && laa.equals("")){
			return error;
		}
		if(atfd.equals("") && laa.equals("")){
			return empty;
		}
		return AllValuesGood;
	}
	/**
	 * Checks if all the parameters of the Long method , Feature Envy or both are filled
	 * @param loc LOC text field
	 * @param cic CICLO text field
	 * @param atfd ATFD text field
	 * @param laa LAA text field
	 * @return Returns true If all the parameters of the Long method , Feature Envy o both are filled else returns false
	 */
	public boolean checkValues(String loc, String cic,String atfd, String laa){
		final JPanel panel = new JPanel();
		boolean LMgood=checkLM(loc, cic)==AllValuesGood;
		boolean LMempty=checkLM(loc, cic)==empty;
		boolean FEgood=checkFE(atfd, laa)==AllValuesGood;
		boolean FEempty=checkFE(atfd, laa)==empty;

		if(LMgood && FEgood){
			LM=true;
			FE=true;
			return true;
		}
		if(LMgood && FEempty){
			LM=true;
			FE=false;
			return true;
		}
		if(LMempty && FEgood){
			LM=false;
			FE=true;
			return true;
		}
		LM=false;
		FE=false;
		JOptionPane.showMessageDialog(panel, "Enter all values for Long_Method or Feature_Envy", "Warning",
				JOptionPane.WARNING_MESSAGE);
		return false;
	}

	public void checkTextBoxes() {
		try { LOC = Integer.parseInt(locTextField.getText()); }	catch (NumberFormatException e) { LOC = 0; }
		try { CYCLO = Integer.parseInt(cycloTextField.getText()); }	catch (NumberFormatException e) { CYCLO = 0; }
		try { ATFD = Integer.parseInt(atfdTextField.getText()); }	catch (NumberFormatException e) { ATFD = 0; }
		try { LAA = Double.parseDouble(laaTextField.getText()); }	catch (NumberFormatException e) { LAA = 0; }
	}

	/**
	 * Compares values with the table and adds all the errors into lists for the long method
	 * @param table Table to compare values
	 * @param LOC Maximum value of lines of code
	 * @param CYCLO Maximum value of cycles
	 */
	public void compareLM(JTable table,int LOC,int CYCLO){
		//For to get the variables for each row and compare to determine if there's some error

		for(int numRow=0;numRow!=table.getModel().getRowCount();numRow++) {
			boolean is_long_method;
			boolean table_is_long_method;
			//					get variables from the table
			int tableLOC=(int)Double.parseDouble(table.getModel().getValueAt(numRow,4).toString());
			int tableCIC=(int)Double.parseDouble(table.getModel().getValueAt(numRow,5).toString());

			String table_is_long_method_string=table.getModel().getValueAt(numRow,8).toString();
			//						get is_long_method from excel
			if(table_is_long_method_string.equals("TRUE")){
				table_is_long_method=true;
			}
			else{
				table_is_long_method=false;
			}
			//					Compare values
			if(tableLOC>LOC && tableCIC>CYCLO){
				is_long_method=true;
			}
			else{
				is_long_method=false;
			}

			//					Errors Long Method
			if(is_long_method && table_is_long_method){
				int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
				errorDCI.add(methodID);
			}
			if(is_long_method && !table_is_long_method){
				int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
				errorDII.add(methodID);
			}
			if(!is_long_method && !table_is_long_method){
				int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
				errorADCI.add(methodID);
			}
			if(!is_long_method && table_is_long_method){
				int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
				errorADII.add(methodID);
			}
		}
	}

	/**
	 * Compares values with the table and adds all the errors into lists for the Feature Envy
	 * @param table Table to compare Values
	 * @param ATFD Value of the ATFD
	 * @param LAA Value of the LAA
	 */
	public void compareFE(JTable table,int ATFD,Double LAA){
		//For to get the variables for each row and compare to determine if there's some error
		for(int numRow=0;numRow!=table.getModel().getRowCount();numRow++) {
			boolean is_feature_envy;
			boolean table_is_feature_envy;
			//					get variables from the table
			int tableATFD=(int)Double.parseDouble(table.getModel().getValueAt(numRow,6).toString());
			int tableLAA=(int)Double.parseDouble(table.getModel().getValueAt(numRow,7).toString());

			String table_is_feature_envy_string=table.getModel().getValueAt(numRow,11).toString();

			//						get is_feature_envy
			if(table_is_feature_envy_string.equals("TRUE")){
				table_is_feature_envy=true;
			}
			else{
				table_is_feature_envy=false;
			}

			if(tableATFD > ATFD && tableLAA < LAA){
				is_feature_envy=true;
			}
			else{
				is_feature_envy=false;
			}
			//					Errors Feature envy
			if(is_feature_envy && table_is_feature_envy){
				int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
				errorDCIFE.add(methodID);
			}
			if(is_feature_envy && !table_is_feature_envy){
				int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
				errorDIIFE.add(methodID);
			}
			if(!is_feature_envy && !table_is_feature_envy){
				int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
				errorADCIFE.add(methodID);
			}
			if(!is_feature_envy && table_is_feature_envy){
				int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
				errorADIIFE.add(methodID);
			}
		}
	}
	/**
	Used for testing the Apply button in this frame.
	* @param LOC LOC value
	* @param CYCLO CYCLO value
	* @param ATFD ATFD value
	* @param LAA LAA value
	* @param gui The gui used in testing
	*/
	public void applyTest(int LOC, int CYCLO, int ATFD, double LAA, GUI gui) {
		locTextField.setText(Integer.toString(LOC));
		cycloTextField.setText(Integer.toString(CYCLO));
		atfdTextField.setText(Integer.toString(ATFD));
		laaTextField.setText(Double.toString(LAA));
		checkTextBoxes();
		gui.setLOC(LOC);
		gui.setCYCLO(CYCLO);
		gui.setATFD(ATFD);
		gui.setLAA(LAA);
		if(checkValues(locTextField.getText(),cycloTextField.getText(),atfdTextField.getText(),laaTextField.getText())){
			LOC=Integer.parseInt(locTextField.getText());
			CYCLO=Integer.parseInt(cycloTextField.getText());
			ATFD=Integer.parseInt(atfdTextField.getText());
			LAA=Double.parseDouble(laaTextField.getText());
			gui.assignThreshholds(LOC, CYCLO, ATFD, LAA);
		}
	}
	
	/**
	Used for testing the Compare button in this frame.
	* @param LOC LOC value
	* @param CYCLO CYCLO value
	* @param ATFD ATFD value
	* @param LAA LAA value
	* @param gui The gui used in testing
	*/
	public void compareTest(int LOC, int CYCLO, int ATFD, double LAA, GUI gui) {
		checkTextBoxes();
		gui.setLOC(LOC);
		gui.setCYCLO(CYCLO);
		gui.setATFD(ATFD);
		gui.setLAA(LAA);
	}
}
