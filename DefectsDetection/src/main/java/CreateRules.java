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




public class CreateRules extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField longMethod_field;
	private JTextField featureEnvy_field;
	private GUI gui;
	private JFrame jframe;
	
	
	//long_method attributes
	private int numberDCI=0;
	private int numberDII=0;
	private int numberADCI=0;
	private int numberADII=0;

	private LinkedList<Integer> errorDCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorDII=new LinkedList<Integer>();
	private LinkedList<Integer> errorADCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorADII=new LinkedList<Integer>();


	
	//featura_envy attributes
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
				
				//gets excel info table
				JTable table=gui.getCurrentExcelFileData();
				
				//gets jtextfield rules (for long_method)
				String rule=longMethod_field.getText();
				String finalRule=rule;
				
				//get jtextfield rules (for feature_envy)
				String ruleFE=featureEnvy_field.getText();
				String finalRuleFE=ruleFE;
				
				//check if any is empty
				if(ruleFE.trim().isEmpty() && rule.trim().isEmpty()) {
		            new InvalidExpression().setVisible(true);
				}else {
					if(ruleFE.trim().isEmpty()) {
						//feature_envy rule is empty
						paintAuxOneError(finalRule,rule,table,"lm");
					}else {
						if(rule.trim().isEmpty()) {
							//long_method rule is empty
							paintAuxOneError(finalRuleFE,ruleFE,table,"fe");
						}else {
							//both fields are filled
							paintAuxOneError(finalRule,rule,finalRuleFE,ruleFE,table);
						}
					}
				
				}
				
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
	
	public void paintWithErrors(JFrame frame, boolean multiple) {	
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
