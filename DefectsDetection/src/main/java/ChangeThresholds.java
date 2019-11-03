import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeThresholds extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		
		textField = new JTextField();
		textField.setBounds(93, 51, 39, 26);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblAnd = new JLabel("and");
		lblAnd.setBounds(144, 56, 30, 16);
		add(lblAnd);
		
		textField_1 = new JTextField();
		textField_1.setBounds(249, 51, 39, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
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
		
		textField_2 = new JTextField();
		textField_2.setBounds(110, 156, 39, 26);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAnd_1 = new JLabel("and");
		lblAnd_1.setBounds(161, 161, 39, 16);
		add(lblAnd_1);
		
		JLabel lblLaa = new JLabel("LAA  <");
		lblLaa.setBounds(203, 161, 61, 16);
		add(lblLaa);
		
		textField_3 = new JTextField();
		textField_3.setBounds(249, 156, 41, 26);
		add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnApplyChanges = new JButton("Apply changes");
		btnApplyChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApplyChanges.setBounds(482, 357, 117, 29);
		add(btnApplyChanges);
		
		
		this.setVisible(true);
		jframe.setContentPane(this);
		
	}
}
