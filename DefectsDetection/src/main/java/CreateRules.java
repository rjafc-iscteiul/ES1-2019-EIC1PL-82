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
	
	private int numberDCI=0;
	private int numberDII=0;
	private int numberADCI=0;
	private int numberADII=0;

	private LinkedList<Integer> errorDCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorDII=new LinkedList<Integer>();
	private LinkedList<Integer> errorADCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorADII=new LinkedList<Integer>();

	
	/**
	 *Get the below values from the existing thresholds 
	 */
	private int LOC=18;
	private int CYCLO=14;
	private int ATFD=7;
	private double LAA=0.5;
	
	/**
	 * Create the panel.
	 */
	public CreateRules(JFrame jframe, GUI gui) {
		
		this.gui=gui;
		
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
				
				//get jtextfield rules created
				String rule=textField.getText();
				String finalRule=rule;
				JTable table=gui.getCurrentExcelFileData();
	            String myExpression = finalRule;

				
				//comparing (code font: https://stackoverflow.com/questions/19383953/is-it-possible-to-evaluate-a-boolean-expression-for-string-comparions)
				try {

		            ScriptEngineManager sem = new ScriptEngineManager();
		            ScriptEngine se = sem.getEngineByName("JavaScript");
		            
					//looping over rows
					for(int numRow=0;numRow!=table.getModel().getRowCount();numRow++) {
						if(rule.contains("LOC")) {
							//System.out.println("There is LOC parameter.");
							
							//replace LOC value in rule for threshold value
							finalRule=finalRule.replace("LOC", String.valueOf(table.getModel().getValueAt(numRow,4)));
						}
						
						
						if(rule.contains("CYCLO")) {
//							System.out.println("There is CYCLO parameter.");
							
							//replace CYCLO value in rule for threshold value
							finalRule=finalRule.replace("CYCLO", String.valueOf(table.getModel().getValueAt(numRow,5)));
						}
					
						if(rule.contains("ATFD")) {
//							System.out.println("There is ATFD parameter.");
							
							//replace ATFD value in rule for threshold value
							finalRule=finalRule.replace("ATFD", String.valueOf(table.getModel().getValueAt(numRow,6)));
						}
						
						if(rule.contains("LAA")) {
//							System.out.println("There is LAA parameter.");
							
							//replace LOC value in rule for threshold value
							finalRule=finalRule.replace("LAA", String.valueOf(table.getModel().getValueAt(numRow,7)));
						}
						
						
						//comparing long_method
						boolean getExcelLM=(boolean) table.getModel().getValueAt(numRow, 8);
						
						
						boolean myResult=(boolean) se.eval(myExpression);
						
						int methodID=(int) table.getModel().getValueAt(numRow, 0);
						
						
						//DCI error
						if(myResult==true && getExcelLM==true) {
							numberDCI+=1;
							errorDCI.add(methodID);
						}
						
						//DII error
						if(myResult==true && getExcelLM==false) {
							numberDII+=1;
							errorDII.add(methodID);
						}
						
						//ADCI error
						if(myResult==false && getExcelLM==false) {
							numberADCI+=1;
							errorADCI.add(methodID);
						}
						
						//ADII error
						if(myResult==false && getExcelLM==true) {
							numberADII+=1;
							errorADII.add(methodID);
						}
						
					}	       
		            
		            
//		            System.out.println(se.eval(myExpression));

		        } catch (ScriptException error) {

		            System.out.println("Invalid Expression");
		            
		            //lauch a error message on gui 
		            
		            
		            //error.printStackTrace();

		        }
				
				System.out.println("Number errors DCI: "+numberDCI);
				System.out.println("Number errors DII: "+numberDII);
				System.out.println("Number errors ADCI: "+numberADCI);
				System.out.println("Number errors ADII: "+numberADII);

				
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
}
