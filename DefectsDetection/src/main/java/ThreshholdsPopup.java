import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ThreshholdsPopup extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ThreshholdsPopup(int LOC, int CYCLO, int ATFD, int LAA) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		JLabel LOCL = new JLabel("Valor do LOC: " + LOC); 
		LOCL.setBounds(32, 41, 121, 16);
		contentPanel.add(LOCL);
		JLabel CYCL = new JLabel("Valor do CYCLO: " + CYCLO);
		CYCL.setBounds(32, 130, 121, 16);
		contentPanel.add(CYCL);
		JLabel ATFL = new JLabel("Valor do ATFD: " + ATFD);
		ATFL.setBounds(32, 86, 121, 16);
		contentPanel.add(ATFL);
		JLabel LAAL = new JLabel("Valor do LAA: " + LAA);
		LAAL.setBounds(32, 175, 121, 16);
		contentPanel.add(LAAL);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}

}
