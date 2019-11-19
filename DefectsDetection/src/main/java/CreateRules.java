import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//imoorts for expression evaluation purposes
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;




public class CreateRules extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	
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
	public CreateRules(JFrame jframe) {
		
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
				
				//check which parameters exist in the rule
				if(rule.contains("LOC")) {
					System.out.println("There is LOC parameter.");
					
					//replace LOC value in rule for threshold value
					finalRule=finalRule.replace("LOC", String.valueOf(LOC));
				}
				
				if(rule.contains("CYCLO")) {
					System.out.println("There is CYCLO parameter.");
					
					//replace CYCLO value in rule for threshold value
					finalRule=finalRule.replace("CYCLO", String.valueOf(CYCLO));
				}
				
				if(rule.contains("ATFD")) {
					System.out.println("There is ATFD parameter.");
					
					//replace ATFD value in rule for threshold value
					finalRule=finalRule.replace("ATFD", String.valueOf(ATFD));
				}
				
				if(rule.contains("LAA")) {
					System.out.println("There is LAA parameter.");
					
					//replace LOC value in rule for threshold value
					finalRule=finalRule.replace("LAA", String.valueOf(LAA));
				}
				
				
			
				
				//comparing (code font: https://stackoverflow.com/questions/19383953/is-it-possible-to-evaluate-a-boolean-expression-for-string-comparions)
				try {

		            ScriptEngineManager sem = new ScriptEngineManager();
		            ScriptEngine se = sem.getEngineByName("JavaScript");
		            String myExpression = finalRule;
		            System.out.println(se.eval(myExpression));

		        } catch (ScriptException error) {

		            System.out.println("Invalid Expression");
		            error.printStackTrace();

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
}
