import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		lblLoc.setBounds(48, 49, 61, 16);
		add(lblLoc);
		
		textField = new JTextField();
		textField.setBounds(94, 44, 34, 26);
		add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"and ", "or"}));
		comboBox.setBounds(140, 45, 65, 27);
		add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(217, 44, 44, 26);
		add(textField_1);
		textField_1.setColumns(10);
		//.getText()
		
		JLabel lblCyclo = new JLabel("CYCLO");
		lblCyclo.setBounds(273, 49, 61, 16);
		add(lblCyclo);
		
		
		JLabel lblFeatureenvy = new JLabel("feature_envy");
		lblFeatureenvy.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblFeatureenvy.setBounds(24, 115, 104, 16);
		add(lblFeatureenvy);
		
		JLabel lblAtfd = new JLabel("ATFD");
		lblAtfd.setBounds(48, 153, 34, 16);
		add(lblAtfd);
		
		textField_2 = new JTextField();
		textField_2.setBounds(94, 148, 34, 26);
		add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"and", "or"}));
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(140, 146, 65, 27);
		add(comboBox_1);
		
		JLabel lblLaa = new JLabel("LAA");
		lblLaa.setBounds(273, 151, 34, 16);
		add(lblLaa);
		
		textField_3 = new JTextField();
		textField_3.setBounds(217, 146, 44, 26);
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
	}
}
