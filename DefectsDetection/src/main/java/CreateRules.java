import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

//imoorts for expression evaluation purposes
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;



/**
 * @author rjafc-iscteiul
 * @version 1.0
 * Date: 13 December 2019
 */
public class CreateRules extends JPanel {

	
	/**
	 * Text field that can contain the long_method rule 
	 */
	private JTextField longMethod_field;
	
	/**
	 * Text field that can contain the feature_envy rule
	 */
	private JTextField featureEnvy_field;
	
	
	/**
	 * Main GUI 
	 */
	private GUI gui;
	
	
	/**
	 * Current frame
	 */
	private JFrame jframe;
	
	
	/**
	 * Long_method attributes
	 */
	private int numberDCI=0;
	private int numberDII=0;
	private int numberADCI=0;
	private int numberADII=0;

	private LinkedList<Integer> errorDCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorDII=new LinkedList<Integer>();
	private LinkedList<Integer> errorADCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorADII=new LinkedList<Integer>();
	
	
	/**
	 * Feature_Envy attributes 
	 */
	private int numberDCIFE=0;
	private int numberDIIFE=0;
	private int numberADCIFE=0;
	private int numberADIIFE=0;

	private LinkedList<Integer> errorDCIFE=new LinkedList<Integer>();
	private LinkedList<Integer> errorDIIFE=new LinkedList<Integer>();
	private LinkedList<Integer> errorADCIFE=new LinkedList<Integer>();
	private LinkedList<Integer> errorADIIFE=new LinkedList<Integer>();
	
	
	
	/**
	 * Create the panel.
	 */
	public CreateRules(JFrame jframe, GUI gui) {
		
		this.gui=gui;
		this.jframe=jframe;
		
		setLayout(null);
		this.setBounds(0, 0, 622, 412);
		
		JLabel lblLongmethod = new JLabel("long_method");
		lblLongmethod.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblLongmethod.setBounds(24, 16, 104, 16);
		add(lblLongmethod);
		
		
		JLabel lblFeatureenvy = new JLabel("feature_envy");
		lblFeatureenvy.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblFeatureenvy.setBounds(24, 189, 104, 16);
		add(lblFeatureenvy);
		
		
		JButton btnApplyChanges = new JButton("Apply changes");
		btnApplyChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//gets jtextfield rules (for long_method)
				String rule=longMethod_field.getText();
				
				//get jtextfield rules (for feature_envy)
				String ruleFE=featureEnvy_field.getText();
				
				paintingErrors(rule,ruleFE);
				
			}
		});
		btnApplyChanges.setBounds(482, 357, 117, 29);
		add(btnApplyChanges);
		
		this.setVisible(true);
		jframe.setContentPane(this);
		
		longMethod_field = new JTextField();
		longMethod_field.setToolTipText("Insert the new rule here.\n\nPlease use only one time the available parameters: LOC, CYCLO, LAA or ATFD.");
		longMethod_field.setBounds(34, 55, 298, 38);
		add(longMethod_field);
		longMethod_field.setColumns(10);
		
		featureEnvy_field = new JTextField();
		featureEnvy_field.setToolTipText("Insert the new rule here.\n\nPlease use only one time the available parameters: LOC, CYCLO, LAA or ATFD.");
		featureEnvy_field.setColumns(10);
		featureEnvy_field.setBounds(34, 231, 298, 38);
		add(featureEnvy_field);
		
		JLabel longMethodRuleExample = new JLabel("Example of rule: LOC > 80 && CYCLO > 10");
		longMethodRuleExample.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		longMethodRuleExample.setBounds(44, 97, 288, 16);
		add(longMethodRuleExample);
		
		JLabel featureEnvyRuleExample = new JLabel("Example of rule: ATFD > 4 && LAA < 0.42");
		featureEnvyRuleExample.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		featureEnvyRuleExample.setBounds(44, 281, 288, 16);
		add(featureEnvyRuleExample);
	}
	
	/**
	 * @param rule - Long_Method rule inserted by the user
	 * @param ruleFE - Feature_Envy rule inserted by the user
	 * Checks if any of the rules is empty and then proceeds to compute and represent the errors related to each rule
	 */
	public void paintingErrors(String rule, String ruleFE) {
		//gets excel info table
		JTable table=gui.getCurrentExcelFileData();
		
		//check if any is empty
		if(ruleFE.trim().isEmpty() && rule.trim().isEmpty()) {
            new InvalidExpression().setVisible(true);
            throw new IllegalArgumentException("No rules were inserted.");
		}else {
			if(ruleFE.trim().isEmpty()) {
				//feature_envy rule is empty
				paintAuxOneError(rule,rule,table,"lm");
			}else {
				if(rule.trim().isEmpty()) {
					//long_method rule is empty
					paintAuxOneError(ruleFE,ruleFE,table,"fe");
				}else {
					//both fields are filled
					paintAuxOneError(rule,rule,ruleFE,ruleFE,table);
				}
			}
		
		}
	}
	
	
	/**
	 * @param finalRule - Single rule inserted by the user
	 * @param rule 
	 * @param table - Table containing the Excel data
	 * @param parameter - Refers to the error metric (long_method or feature_envy)
	 * Computes and showcases the number of errors occurred related to a specific error metric 
	 */
	private void paintAuxOneError(String finalRule, String rule, JTable table, String parameter) {
		//comparing (code font: https://stackoverflow.com/questions/19383953/is-it-possible-to-evaluate-a-boolean-expression-for-string-comparions)
		try {

            ScriptEngineManager sem = new ScriptEngineManager();
            ScriptEngine se = sem.getEngineByName("JavaScript");
            
			//looping over the table's rows
			for(int numRow=0;numRow!=table.getModel().getRowCount();numRow++) {
				finalRule=replaceValues(rule,table,numRow);
				
				//getting long_method values from Excel data table
				String getExcelLM="";
				
				//checking which parameter we are comparing
				if(parameter.toLowerCase().equals("fe")) { //feature_envy
					getExcelLM=table.getModel().getValueAt(numRow, 11).toString().toLowerCase();
				}else { //long_method
					getExcelLM=table.getModel().getValueAt(numRow, 8).toString().toLowerCase();
				}
				
				//comparing long_method rule with replaced values
				String myResult=se.eval(finalRule).toString();
				
				//getting methodID from the current line that is being evaluated
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
			
			paintWithErrors(jframe,false); 
            

        } catch (ScriptException error) {
            
            //launch a error message on gui 
            
            new InvalidExpression().setVisible(true);

        }
	}
	
	/**
	 * @param finalRule - the rule to consider to replaced the metric value
	 * @param table - Excel data table
	 * @param numRow - number of the row,in the Excel data table, to be considered
	 * @return - the rule with the metric replaced
	 * Replaces the metric value in the rule with the respective value contained in the Excel data table
	 */
	public String replaceValues(String finalRule, JTable table, int numRow) {
		if(finalRule.contains("LOC")) {					
			//replace LOC value in rule for threshold value
			finalRule=finalRule.replace("LOC", String.valueOf(table.getModel().getValueAt(numRow,4)));
		}
		
		if(finalRule.contains("CYCLO")) {
			//replace CYCLO value in rule for threshold value
			finalRule=finalRule.replace("CYCLO", String.valueOf(table.getModel().getValueAt(numRow,5)));
		}
	
		if(finalRule.contains("ATFD")) {
			//replace ATFD value in rule for threshold value
			finalRule=finalRule.replace("ATFD", String.valueOf(table.getModel().getValueAt(numRow,6)));
		}
		
		if(finalRule.contains("LAA")) {
			//replace LOC value in rule for threshold value
			finalRule=finalRule.replace("LAA", String.valueOf(table.getModel().getValueAt(numRow,7)));
		}
		return finalRule;
	}
	
	
	/**
	 * @param finalRuleLM - Rule inserted by the user in the long_method field
	 * @param ruleLM
	 * @param finalRuleFE - Rule inserted by the user in the feature_envy field
	 * @param ruleFE
	 * @param table - Excel data table
	 * Computes and showcases the number of errors occurred related to both error metrics
	 */
	private void paintAuxOneError(String finalRuleLM, String ruleLM, String finalRuleFE, String ruleFE,JTable table) {
		//comparing (code font: https://stackoverflow.com/questions/19383953/is-it-possible-to-evaluate-a-boolean-expression-for-string-comparions)
		try {

            ScriptEngineManager sem = new ScriptEngineManager();
            ScriptEngine se = sem.getEngineByName("JavaScript");
            
			//looping over rows
			for(int numRow=0;numRow!=table.getModel().getRowCount();numRow++) {
				finalRuleLM=this.replaceValues(ruleLM, table, numRow);
				finalRuleFE=this.replaceValues(ruleFE, table, numRow);

				//getting long_method from excel file
				String getExcelLM=table.getModel().getValueAt(numRow, 8).toString().toLowerCase();
				
				//getting long_method with defined rule
				String myResult=se.eval(finalRuleLM).toString();
				
				//getting feature_envy from excel file
				String getExcelFE=table.getModel().getValueAt(numRow, 11).toString().toLowerCase();
				
				//getting feature_envy with defined rule
				String myResultFE=se.eval(finalRuleFE).toString();
				
				int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow, 0).toString());
				
				
				//DCI error (long_method)
				if(myResult.equals("true") && getExcelLM.equals("true")) {
					numberDCI+=1;
					errorDCI.add(methodID);
				}
				
				//DII error (long_method)
				if(myResult.equals("true") && getExcelLM.equals("false")) {
					numberDII+=1;
					errorDII.add(methodID);
				}
				
				//ADCI error (long_method)
				if(myResult.equals("false") && getExcelLM.equals("false")) {
					numberADCI+=1;
					errorADCI.add(methodID);
				}
				
				//ADII error (long_method)
				if(myResult.equals("false") && getExcelLM.equals("true")) {
					numberADII+=1;
					errorADII.add(methodID);
				}
				
				
				//DCI error (feature_envy)
				if(myResultFE.equals("true") && getExcelFE.equals("true")) {
					numberDCIFE+=1;
					errorDCIFE.add(methodID);
				}
				
				//DII error (feature_envy)
				if(myResultFE.equals("true") && getExcelFE.equals("false")) {
					numberDIIFE+=1;
					errorDIIFE.add(methodID);
				}
				
				//ADCI error (feature_envy)
				if(myResultFE.equals("false") && getExcelFE.equals("false")) {
					numberADCIFE+=1;
					errorADCIFE.add(methodID);
				}
				
				//ADII error (feature_envy)
				if(myResultFE.equals("false") && getExcelFE.equals("true")) {
					numberADIIFE+=1;
					errorADIIFE.add(methodID);
				}
				
			}	       
			
			//paint comparison results in GUI
			paintWithErrors(jframe,true); 
            

        } catch (ScriptException error) {
        	
            //lauch a error message on gui 
            
            new InvalidExpression().setVisible(true);

        }
	}
	
	/**
	 * @param frame - Current frame 
	 * @param multiple - Checks if we are computing more than 1 error (in this case the maximum errors to be computed are 2)
	 * Computes and shows in a graphical way the errors related to the error metric (long_method or feature_envy or both)
	 */
	private void paintWithErrors(JFrame frame, boolean multiple) {	
		LinkedList<ComparisonError> errors=new LinkedList<ComparisonError>();
		
		errors.add(new ComparisonError("DCI",numberDCI,errorDCI));
		errors.add(new ComparisonError("DII",numberDII,errorDII));
		errors.add(new ComparisonError("ADCI",numberADCI,errorADCI));
		errors.add(new ComparisonError("ADII",numberADII,errorADII));
		
		if(!multiple) { //only one parameter
			frame.setContentPane(new paintError(errors,jframe, gui,true));
			
		}else { //multiple parameters
			
			LinkedList<ComparisonError> errorsFE=new LinkedList<ComparisonError>();
			
			errorsFE.add(new ComparisonError("DCI",numberDCIFE,errorDCIFE));
			errorsFE.add(new ComparisonError("DII",numberDIIFE,errorDIIFE));
			errorsFE.add(new ComparisonError("ADCI",numberADCIFE,errorADCIFE));
			errorsFE.add(new ComparisonError("ADII",numberADIIFE,errorADIIFE));
			
			frame.setContentPane(new PaintSeveralErrors(errors,errorsFE,jframe,gui,true));
		}
		
	}
	
}
