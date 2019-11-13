import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ChangeThresholds extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	private JTextField locTextField;
	private JTextField cycloTextField;
	private JTextField atfdTextField;
	private JTextField laaTextField;

	/**
	 * Create the panel.
	 */
	public ChangeThresholds(JFrame jframe) {
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
		btnApplyChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThreshholdsPopup TP = new ThreshholdsPopup(Integer.parseInt(textField.getText()),Integer.parseInt(textField_1.getText()),Integer.parseInt(textField_2.getText()),Double.parseDouble(textField_3.getText()));
				TP.setVisible(true);
			}
		});
		btnApplyChanges.setBounds(382, 360, 117, 29);
		add(btnApplyChanges);
		
		
		JButton btnNewButton = new JButton("Compare");
		btnNewButton.setBounds(499, 360, 117, 29);
		add(btnNewButton);
		
		jframe.setContentPane(this);
		this.setVisible(true);
		
	}
}
