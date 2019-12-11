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
	public ThreshholdsPopup(int LOC, int CYCLO, int ATFD, Double LA, boolean mode) {
		setBounds(100, 100, 550, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		if (mode) {
			JLabel LOCL = new JLabel("Valor do LOC: " + LOC); 
			LOCL.setBounds(32, 40, 121, 16);
			contentPanel.add(LOCL);
			JLabel CYCL = new JLabel("Valor do CYCLO: " + CYCLO);
			CYCL.setBounds(32, 60, 121, 16);
			contentPanel.add(CYCL);
			JLabel ATFL = new JLabel("Valor do ATFD: " + ATFD);
			ATFL.setBounds(32, 80, 121, 16);
			contentPanel.add(ATFL);
			JLabel LAAL = new JLabel("Valor do LAA: " + LA);
			LAAL.setBounds(32, 100, 121, 16);
			contentPanel.add(LAAL);
		}
		else {
			JLabel FEL;
			JLabel LML;
			JLabel ORI;
			JLabel ORB;
			if (LOC == 65 && CYCLO == 10) {
				LML = new JLabel("Os valores do LOC e CYCLO correspondem aos standards do long method.");
				LML.setBounds(32, 40, 481, 16);
				contentPanel.add(LML);
			}
			else {
				LML = new JLabel("Os valores do LOC e CYCLO nao correspondem aos standards do long method.");
				ORB = new JLabel("Os valores originais sao: LOC > 65, CYCLO > 10.");
				LML.setBounds(32, 40, 481, 16);
				ORB.setBounds(32, 80, 321, 16);
				contentPanel.add(LML);
				contentPanel.add(ORB);
			}
			if (ATFD == 5 && LA == 0.33) {
				FEL = new JLabel("Os valores do ATFD e LAA correspondem aos standards do feature envy.");
				FEL.setBounds(32, 60, 481, 16);
				contentPanel.add(FEL);
			}
			else {
				FEL = new JLabel("Os valores do ATFD e LAA na o correspondem aos standards do feature envy.");
				ORI = new JLabel("Os valores originais sao: ATFD > 5, LAA < 0.33.");
				FEL.setBounds(32, 60, 481, 16);
				ORI.setBounds(32, 100, 321, 16);
				contentPanel.add(FEL);
				contentPanel.add(ORI);
			}
		}
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
