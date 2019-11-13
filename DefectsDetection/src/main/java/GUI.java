import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

public class GUI {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 * @param <E>
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
				
		frame = new JFrame("Defects Detection v1.0");
		//frame.setBounds(100, 100, 622, 456);
		//frame.setBounds(100, 100, 1200	, 1500);
		frame.setSize(1500, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		//panel.setBounds(0, 0, 622, 412);
		panel.setBounds(0, 0, 1200	, 1500);
		
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		table = new JTable();
		
		JScrollPane scroll= new JScrollPane(table);
		
		panel.add(scroll);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnExcel = new JMenu("File");
		menuBar.add(mnExcel);
		
		JMenuItem mntmOpen = new JMenuItem("Open Excel");
		mntmOpen.addActionListener(new ActionListener() {
			
			//defining what happens when we click Open under menu Excel
			
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {   //file choosen correctly
					
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath()); //prints file path
					ExcelReader reader = new ExcelReader();
					DefaultTableModel tableData = reader.readFile(selectedFile.getAbsolutePath());
					table.setModel(tableData);
					//table.add((Component) table.getModel());
				}
				
			}
			
		});
		mnExcel.add(mntmOpen);
		
		JMenu mnRules = new JMenu("Rules");
		menuBar.add(mnRules);
		
		JMenuItem mntmDefaultRules = new JMenuItem("Default Rules");
		mntmDefaultRules.addActionListener(new ActionListener() {
			
			//comparing default values based on Excel default values
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnRules.add(mntmDefaultRules);
		
		JMenu mnEditRules = new JMenu("Edit Rules");
		mnRules.add(mnEditRules);
		
		JMenuItem mntmChangeThresholds = new JMenuItem("Change thresholds");
		mntmChangeThresholds.addActionListener(event -> {
			frame.setContentPane(new ChangeThresholds(frame));
			frame.revalidate();
		});
		mnEditRules.add(mntmChangeThresholds);
		
		
		JMenuItem mntmCreateNewRules = new JMenuItem("Create new rules");
		mntmCreateNewRules.addActionListener(event -> {
			frame.setContentPane(new CreateRules(frame));
			frame.revalidate();
		});
		
		
		mnEditRules.add(mntmCreateNewRules);
		
		JMenu mnAbout = new JMenu("Help");
		
		menuBar.add(mnAbout);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			//opens frame About
			public void actionPerformed(ActionEvent e) {
				About about=new About();
				about.setVisible(true);
			}
		});
		mnAbout.add(mntmAbout);
		frame.getContentPane().setLayout(null);
	}
}