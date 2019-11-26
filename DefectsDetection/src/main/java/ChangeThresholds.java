import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ChangeThresholds extends JPanel {
	private static final long serialVersionUID = 1L;
	private GUI gui;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField locTextField;
	private JTextField cycloTextField;
	private JTextField atfdTextField;
	private JTextField laaTextField;

	private int LOC;
	private int CIC;
	private int ATFD;
	private int LAA;

	//	Lists of the methodID for every respective error
	private LinkedList<Integer> errorDCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorDII=new LinkedList<Integer>();
	private LinkedList<Integer> errorADCI=new LinkedList<Integer>();
	private LinkedList<Integer> errorADII=new LinkedList<Integer>();

	/**
	 * Create the panel.
	 */
	public ChangeThresholds(JFrame jframe,GUI gui) {
		this.gui=gui;
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
		btnApplyChanges.setBounds(382, 360, 117, 29);
		add(btnApplyChanges);
		btnApplyChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOC=Integer.parseInt(locTextField.getText());
				CIC=Integer.parseInt(cycloTextField.getText());
//				ATFD=Integer.parseInt(atfdTextField.getText());
//				LAA=Integer.parseInt(laaTextField.getText());
			}
		});


		JButton btnNewButton = new JButton("Compare");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//				get Table
				JTable table=gui.getCurrentExcelFileData();

				//				get variables
				LOC=Integer.parseInt(locTextField.getText());
				CIC=Integer.parseInt(cycloTextField.getText());
//				ATFD=Integer.parseInt(atfdTextField.getText());
//				LAA=Integer.parseInt(laaTextField.getText());

				//For to get the variables for each row and compare to determine if there's some error
				for(int numRow=0;numRow!=table.getModel().getRowCount();numRow++) {
					boolean is_long_method;
					boolean table_is_long_method;
					//					get variables from the table
					int tableLOC=(int)Double.parseDouble(table.getModel().getValueAt(numRow,4).toString());
					int tableCIC=(int)Double.parseDouble(table.getModel().getValueAt(numRow,5).toString());
					int tableATFD=(int)Double.parseDouble(table.getModel().getValueAt(numRow,6).toString());
					int tableLAA=(int)Double.parseDouble(table.getModel().getValueAt(numRow,7).toString());

					String table_is_long_method_string=table.getModel().getValueAt(numRow,8).toString();
					System.out.println(table_is_long_method_string);
					if(table_is_long_method_string.equals("TRUE")){
						table_is_long_method=true;
					}
					else{
						table_is_long_method=false;
					}

					//					Compare values
					if(tableLOC>LOC && tableCIC>CIC){
						is_long_method=true;
					}
					else{
						is_long_method=false;
					}
					//					Errors
					if(is_long_method && table_is_long_method){
						int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
						errorDCI.add(methodID);
					}
					if(is_long_method && !table_is_long_method){
						int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
						errorDII.add(methodID);
					}
					if(!is_long_method && !table_is_long_method){
						int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
						errorADCI.add(methodID);
					}
					if(!is_long_method && table_is_long_method){
						int methodID=(int)Double.parseDouble(table.getModel().getValueAt(numRow,0).toString());
						errorADII.add(methodID);
					}

				}
				paintWithErrors(jframe);
			}
		});
		btnNewButton.setBounds(499, 360, 117, 29);
		add(btnNewButton);

		jframe.setContentPane(this);
		this.setVisible(true);
		
	}
	public void paintWithErrors(JFrame frame) {
		ComparisonError dci=new ComparisonError("DCI",errorDCI.size(),errorDCI);
		ComparisonError dii=new ComparisonError("DII",errorDII.size(),errorDII);
		ComparisonError adci=new ComparisonError("ADCI",errorADCI.size(),errorADCI);
		ComparisonError adii=new ComparisonError("ADII",errorADII.size(),errorADII);

		LinkedList<ComparisonError> errors=new LinkedList<ComparisonError>();
		errors.add(dci);
		errors.add(dii);
		errors.add(adci);
		errors.add(adii);
		
		frame.setContentPane(new paintError(errors));
	}
}
