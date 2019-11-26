import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PaintSeveralErrors extends JPanel {
	
	//tables for long_method
		private JTable tableDCILM= new JTable();
		private JTable tableDIILM= new JTable();
		private JTable tableADCILM= new JTable();
		private JTable tableADIILM= new JTable();

	//tables for feature_envy
		private JTable tableDCIFE= new JTable();
		private JTable tableDIIFE= new JTable();
		private JTable tableADCIFE= new JTable();
		private JTable tableADIIFE= new JTable();
		
	

	/**
	 * Create the panel.
	 */
	public PaintSeveralErrors(LinkedList<ComparisonError> errorsLM,LinkedList<ComparisonError> errorsFE,JFrame jframe, GUI gui) {
		setLayout(null);
		this.setBounds(0, 0, 622, 412);
		
		JLabel lblErrorSummarylongmethod = new JLabel("Error Summary: long_method ");
		lblErrorSummarylongmethod.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblErrorSummarylongmethod.setBounds(19, 25, 236, 16);
		add(lblErrorSummarylongmethod);
		
		JLabel lblErrorSummaryFeatureenvy = new JLabel("Error Summary: feature_envy");
		lblErrorSummaryFeatureenvy.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblErrorSummaryFeatureenvy.setBounds(19, 210, 236, 16);
		add(lblErrorSummaryFeatureenvy);
		
		JButton btnCloseResults = new JButton("Close Results");
		btnCloseResults.setBounds(499, 377, 117, 29);
		add(btnCloseResults);
		
		JLabel lblNewLabel = new JLabel("DCI");
		lblNewLabel.setBounds(29, 53, 61, 16);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane(this.tableDCILM);
		scrollPane.setBounds(68, 53, 81, 60);
		add(scrollPane);
		
		JLabel lblAdci = new JLabel("ADCI");
		lblAdci.setBounds(295, 53, 61, 16);
		add(lblAdci);
		
		JScrollPane scrollPane_1 = new JScrollPane(this.tableADCILM);
		scrollPane_1.setBounds(344, 53, 81, 60);
		add(scrollPane_1);
		
		JLabel lblDii = new JLabel("DII");
		lblDii.setBounds(29, 125, 61, 16);
		add(lblDii);
		
		JScrollPane scrollPane_2 = new JScrollPane(this.tableDIILM);
		scrollPane_2.setBounds(68, 125, 81, 60);
		add(scrollPane_2);
		
		JLabel lblAdii = new JLabel("ADII");
		lblAdii.setBounds(295, 125, 61, 16);
		add(lblAdii);
		
		JScrollPane scrollPane_3 = new JScrollPane(this.tableADIILM);
		scrollPane_3.setBounds(344, 125, 81, 60);
		add(scrollPane_3);
		
		JLabel label = new JLabel("DCI");
		label.setBounds(29, 238, 61, 16);
		add(label);
		
		JScrollPane scrollPane_4 = new JScrollPane(this.tableDCIFE);
		scrollPane_4.setBounds(68, 238, 81, 60);
		add(scrollPane_4);
		
		JLabel label_1 = new JLabel("ADCI");
		label_1.setBounds(295, 238, 61, 16);
		add(label_1);
		
		JScrollPane scrollPane_5 = new JScrollPane(this.tableADCIFE);
		scrollPane_5.setBounds(344, 238, 81, 60);
		add(scrollPane_5);
		
		JLabel label_2 = new JLabel("DII");
		label_2.setBounds(29, 311, 61, 16);
		add(label_2);
		
		JScrollPane scrollPane_6 = new JScrollPane(this.tableDIIFE);
		scrollPane_6.setBounds(68, 310, 81, 60);
		add(scrollPane_6);
		
		JLabel label_3 = new JLabel("ADII");
		label_3.setBounds(295, 310, 61, 16);
		add(label_3);
		
		JScrollPane scrollPane_7 = new JScrollPane(this.tableADIIFE);
		scrollPane_7.setBounds(344, 310, 81, 60);
		add(scrollPane_7);

	}
}
