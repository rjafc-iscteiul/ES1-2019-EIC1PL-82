import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author rjafc-iscteiul
 * @version 1.0
 * Date: 13 december 2019
 * Creates a confirmation window indicating everything is now according to the default.
 */
public class ResetValuesRules extends JFrame {

	/**
	 * Represents the main panel in the current frame. 
	 */
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetValuesRules frame = new ResetValuesRules();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ResetValuesRules() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100, 450, 95);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 69);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setBounds(399, 40, 45, 29);
		panel.add(btnOk);
		
		JLabel lblEverythingIsNow = new JLabel("Everything is now according to default.");
		lblEverythingIsNow.setBounds(22, 16, 253, 16);
		panel.add(lblEverythingIsNow);
	}

}
