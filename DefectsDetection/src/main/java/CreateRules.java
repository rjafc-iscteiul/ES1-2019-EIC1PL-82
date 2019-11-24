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
	private JTextField textField;
	private JTextField textField_1;
	private GUI gui;
	private JFrame jframe;
	
	private int numberDCI=0;
	private int numberDII=0;
	private int numberADCI=0;
	private int numberADII=0;

	private LinkedList<Integer> errorDCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorDII=new LinkedList<Integer>();
	private LinkedList<Integer> errorADCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorADII=new LinkedList<Integer>();

	
	
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
				
				
				JTable table=gui.getCurrentExcelFileData();

				
				//get jtextfield rules (for long_method)
				String rule=textField.getText();
				String finalRule=rule;

				
				//get jtextfield rules (for feature_envy)
				String ruleFE=textField_1.getText();
				String finalRuleFE=ruleFE;
				
				//comparing (code font: https://stackoverflow.com/questions/19383953/is-it-possible-to-evaluate-a-boolean-expression-for-string-comparions)
				try {

		            ScriptEngineManager sem = new ScriptEngineManager();
		            ScriptEngine se = sem.getEngineByName("JavaScript");
		            
					//looping over rows
					for(int numRow=0;numRow!=table.getModel().getRowCount();numRow++) {
						finalRule=rule;
						if(finalRule.contains("LOC")) {
//							System.out.println("There is LOC parameter.");
							
							//replace LOC value in rule for threshold value
							finalRule=finalRule.replace("LOC", String.valueOf(table.getModel().getValueAt(numRow,4)));
						}
						
						
						if(finalRule.contains("CYCLO")) {
//							System.out.println("There is CYCLO parameter.");
							
							//replace CYCLO value in rule for threshold value
							finalRule=finalRule.replace("CYCLO", String.valueOf(table.getModel().getValueAt(numRow,5)));
						}
					
						if(finalRule.contains("ATFD")) {
//							System.out.println("There is ATFD parameter.");
							
							//replace ATFD value in rule for threshold value
							finalRule=finalRule.replace("ATFD", String.valueOf(table.getModel().getValueAt(numRow,6)));
						}
						
						if(finalRule.contains("LAA")) {
//							System.out.println("There is LAA parameter.");
							
							//replace LOC value in rule for threshold value
							finalRule=finalRule.replace("LAA", String.valueOf(table.getModel().getValueAt(numRow,7)));
						}
						
						
						//comparing long_method
						String getExcelLM=table.getModel().getValueAt(numRow, 8).toString().toLowerCase();
//						System.out.println(getExcelLM);
						
						String myResult=se.eval(finalRule).toString();
//						System.out.println(myResult);
						
						
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
					
					paintWithErrors(jframe);
		            
//		            System.out.println("Number errors DCI: "+numberDCI);
//					System.out.println("Number errors DII: "+numberDII);
//					System.out.println("Number errors ADCI: "+numberADCI);
//					System.out.println("Number errors ADII: "+numberADII);
//					
//					
//					System.out.println("Size DCI list: "+errorDCI.size());
//					System.out.println("Size DII list: "+errorDII.size());
//					System.out.println("Size ADCI list: "+errorADCI.size());
//					System.out.println("Size ADII: "+errorADII.size());
					
//		            System.out.println(se.eval(myExpression));

		        } catch (ScriptException error) {

		            System.out.println("Invalid Expression");
		            
		            //lauch a error message on gui 
		            
		            new InvalidExpression().setVisible(true);
		            
		            //error.printStackTrace();

		        }

			}
		});
		btnApplyChanges.setBounds(482, 357, 117, 29);
		add(btnApplyChanges);
		
		this.setVisible(true);
		jframe.setContentPane(this);
		
		textField = new JTextField();
		textField.setToolTipText("Insert the new rule here.\n\nPlease use only one time the available parameters: LOC, CYCLO, LAA or ATFD.");
		textField.setBounds(34, 55, 298, 38);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Insert the new rule here.\n\nPlease use only one time the available parameters: LOC, CYCLO, LAA or ATFD.");
		textField_1.setColumns(10);
		textField_1.setBounds(34, 231, 298, 38);
		add(textField_1);
		
		JLabel lblExampleOfRule = new JLabel("Example of rule: LOC > 80 && CYCLO > 10");
		lblExampleOfRule.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblExampleOfRule.setBounds(44, 97, 288, 16);
		add(lblExampleOfRule);
		
		JLabel lblExampleOfRule_1 = new JLabel("Example of rule: ATFD > 4 && LAA < 0.42");
		lblExampleOfRule_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblExampleOfRule_1.setBounds(44, 281, 288, 16);
		add(lblExampleOfRule_1);
	}
	
	
	
	public void paintWithErrors(JFrame frame) {
		ComparisonError dci=new ComparisonError("DCI",numberDCI,errorDCI);
		ComparisonError dii=new ComparisonError("DII",numberDII,errorDII);
		ComparisonError adci=new ComparisonError("ADCI",numberADCI,errorADCI);
		ComparisonError adii=new ComparisonError("ADII",numberADII,errorADII);

		LinkedList<ComparisonError> errors=new LinkedList<ComparisonError>();
		errors.add(dci);
		errors.add(dii);
		errors.add(adci);
		errors.add(adii);
		
		frame.setContentPane(new paintError(errors,jframe, gui));
	}
	
	
	
}
