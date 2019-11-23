import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeThresholds extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField locTextField;
	private JTextField cycloTextField;
	private JTextField atfdTextField;
	private JTextField laaTextField;
	private GUI guii;
	private int LOC;
	private int CYCLO;
	private int ATFD;
	private double LAA;

	/**
	 * Create the panel.
	 */
	public ChangeThresholds(JFrame jframe, GUI gui) {
		setLayout(null);
		this.setBounds(0, 0, 622, 412);
		this.guii = gui;
		
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
				gui.assignThreshholds(LOC, CYCLO, ATFD, LAA);
				ThreshholdsPopup TP = new ThreshholdsPopup(LOC,CYCLO,ATFD,LAA,true);
				TP.setVisible(true);
			}
		});
		
		JButton btnNewButton = new JButton("Compare");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkTextBoxes();
				guii.assignThreshholds(LOC, CYCLO, ATFD, LAA);
				ThreshholdsPopup TP = new ThreshholdsPopup(LOC,CYCLO,ATFD,LAA,false);
				TP.setVisible(true);
			}
		});
		btnNewButton.setBounds(499, 360, 117, 29);
		add(btnNewButton);
		
		jframe.setContentPane(this);
		this.setVisible(true);
		
	}
	
	private void checkTextBoxes() {
		try { LOC = Integer.parseInt(locTextField.getText()); }	catch (NumberFormatException e) { LOC = 0; }
		try { CYCLO = Integer.parseInt(cycloTextField.getText()); }	catch (NumberFormatException e) { CYCLO = 0; }
		try { ATFD = Integer.parseInt(atfdTextField.getText()); }	catch (NumberFormatException e) { ATFD = 0; }
		try { LAA = Double.parseDouble(laaTextField.getText()); }	catch (NumberFormatException e) { LAA = 0; }
	}
	
}
