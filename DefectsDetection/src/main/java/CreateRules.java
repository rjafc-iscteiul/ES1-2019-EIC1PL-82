<<<<<<< HEAD
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
=======
>>>>>>> branch 'master' of https://github.com/rjafc-iscteiul/ES1-2019-EIC1PL-82
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

public class CreateRules extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		
		JLabel lblLoc = new JLabel("LOC");
		lblLoc.setBounds(48, 49, 34, 16);
		add(lblLoc);
		
		textField = new JTextField();
		textField.setBounds(155, 44, 44, 26);
		add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"and ", "or"}));
		comboBox.setBounds(223, 44, 65, 27);
		add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(416, 44, 44, 26);
		add(textField_1);
		textField_1.setColumns(10);
		//.getText()
		
		JLabel lblCyclo = new JLabel("CYCLO");
		lblCyclo.setBounds(300, 49, 44, 16);
		add(lblCyclo);
		
		
		JLabel lblFeatureenvy = new JLabel("feature_envy");
		lblFeatureenvy.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblFeatureenvy.setBounds(24, 189, 104, 16);
		add(lblFeatureenvy);
		
		JLabel lblAtfd = new JLabel("ATFD");
		lblAtfd.setBounds(48, 220, 34, 16);
		add(lblAtfd);
		
		textField_2 = new JTextField();
		textField_2.setBounds(155, 215, 44, 26);
		add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"and", "or"}));
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(223, 215, 65, 27);
		add(comboBox_1);
		
		JLabel lblLaa = new JLabel("LAA");
		lblLaa.setBounds(300, 220, 34, 16);
		add(lblLaa);
		
		textField_3 = new JTextField();
		textField_3.setBounds(404, 215, 44, 26);
		add(textField_3);
		textField_3.setColumns(10);
		
		
		JButton btnApplyChanges = new JButton("Apply changes");
		btnApplyChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("LOC = " + textField.getText());
				System.out.println("CYCLO = " + textField_1.getText());
				System.out.println("ATFD = " + textField_2.getText());
				System.out.println("LAA = " + textField_3.getText());
			}
		});
		btnApplyChanges.setBounds(482, 357, 117, 29);
		add(btnApplyChanges);
		
		this.setVisible(true);
		jframe.setContentPane(this);
		
		JButton addLongMethodParameter = new JButton("+");
		addLongMethodParameter.setToolTipText("Adds a new parameter to the long_method rule.");
		addLongMethodParameter.addActionListener(new ActionListener() {
			
			// adds new rule to long_method
			public void actionPerformed(ActionEvent e) {
				
				//add logic operator
				JComboBox comboBox_21 = new JComboBox();
				comboBox_21.setModel(new DefaultComboBoxModel(new String[] {"and","or"}));
				comboBox_21.setBounds(79, 85, 71, 27);
				add(comboBox_21);
				
				
				//add parameter
				JLabel lblLoc = new JLabel("LOC");
				lblLoc.setBounds(120, 85, 34, 16);
				add(lblLoc);
				
				
				
				//add logic operator
				JComboBox comboBox_22 = new JComboBox();
				comboBox_22.setModel(new DefaultComboBoxModel(new String[] {">", ">=", "<", "<=", "="}));
				comboBox_22.setBounds(175, 85, 71, 27);
				add(comboBox_22);
				
				revalidate();
				
				//add parameter value
				
				
				
			}
		});
		addLongMethodParameter.setBounds(533, 45, 27, 26);
		add(addLongMethodParameter);
		
		JButton removeLongMethodParameter = new JButton("-");
		removeLongMethodParameter.setToolTipText("Removes selected parameters  from the long_method rule.");
		removeLongMethodParameter.setBounds(572, 45, 27, 26);
		add(removeLongMethodParameter);
		
		JButton addFeatureEnvyParameter = new JButton("+");
		addFeatureEnvyParameter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addFeatureEnvyParameter.setToolTipText("Adds a new parameter  to the feature_envy rule.");
		addFeatureEnvyParameter.setBounds(533, 216, 27, 26);
		add(addFeatureEnvyParameter);
		
		JButton removeFeatureEnvyParameter = new JButton("-");
		removeFeatureEnvyParameter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		removeFeatureEnvyParameter.setToolTipText("Removes selected parameters  from the feature_envy rule.");
		removeFeatureEnvyParameter.setBounds(572, 216, 27, 26);
		add(removeFeatureEnvyParameter);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {">", ">=", "<", "<=", "="}));
		comboBox_2.setBounds(79, 45, 71, 27);
		add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {">", ">=", "<", "<=", "="}));
		comboBox_3.setBounds(346, 45, 70, 27);
		add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {">", ">=", "<", "<=", "="}));
		comboBox_4.setBounds(85, 217, 70, 27);
		add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"<", "<=", ">", ">=", "="}));
		comboBox_5.setBounds(328, 216, 76, 27);
		add(comboBox_5);
	}
}
