import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class CompareTools extends JPanel {

	/**
	 * Create the panel.
	 */
		
	private GUI gui;
	
	public CompareTools(JFrame frame, GUI gui) {
		setLayout(null);
		this.setBounds(0, 0, 622, 412);
		this.gui=gui;
		
		LinkedList<ComparisonError> errorsLM_iPlasma=new LinkedList<>();
		LinkedList<ComparisonError> errorsLM_PMD=new LinkedList<>();

		errorsLM_iPlasma=analyseToolsError(9,errorsLM_iPlasma);
		errorsLM_PMD=analyseToolsError(10,errorsLM_PMD);

		//paint error info
		frame.setContentPane(new DrawComparedTools(errorsLM_iPlasma,errorsLM_PMD));
		frame.revalidate();
	}
	
	private LinkedList<ComparisonError> analyseToolsError(int column,LinkedList<ComparisonError> list) {
		
		int numberErrorsDCI=0;
		int numberErrorsDII=0;
		int numberErrorsADCI=0;
		int numberErrorsADII=0;
		LinkedList<Integer> dci_methodID=new LinkedList<Integer>();
		LinkedList<Integer> dii_methodID=new LinkedList<Integer>();
		LinkedList<Integer> adci_methodID=new LinkedList<Integer>();
		LinkedList<Integer> adii_methodID=new LinkedList<Integer>();

		
		LinkedList<ComparisonError> finalList=list;
		
		JTable table=gui.getCurrentExcelFileData();
		
		//loop over the rows
		for(int i=0;i!=table.getModel().getRowCount();i++) {
			
			//get default value
			String defaultValue=table.getModel().getValueAt(i, 8).toString().toLowerCase();
			
			//get tool value
			String toolValue=table.getModel().getValueAt(i, column).toString().toLowerCase();
		
			//getting error type
			if(defaultValue.equals("true") && toolValue.equals("true")) { //dci error
				numberErrorsDCI++;
				dci_methodID.add(Integer.parseInt(table.getModel().getValueAt(i, 0).toString()));
			}
			
			if(defaultValue.equals("false") && toolValue.equals("true")) { //dii error
				numberErrorsDII++;
				dii_methodID.add(Integer.parseInt(table.getModel().getValueAt(i, 0).toString()));
			}
			
			if(defaultValue.equals("false") && toolValue.equals("false")) { //adci error
				numberErrorsADCI++;
				adci_methodID.add(Integer.parseInt(table.getModel().getValueAt(i, 0).toString()));
			}
			
			if(defaultValue.equals("true") && toolValue.equals("false")) { //adii error
				numberErrorsDCI++;
				dci_methodID.add(Integer.parseInt(table.getModel().getValueAt(i, 0).toString()));
			}
			
		}
		
		finalList.add(new ComparisonError("DCI",numberErrorsDCI,dci_methodID));
		finalList.add(new ComparisonError("DII",numberErrorsDII,dii_methodID));
		finalList.add(new ComparisonError("ADCI",numberErrorsADCI,adci_methodID));
		finalList.add(new ComparisonError("ADII",numberErrorsADII,adii_methodID));

		return finalList;
	}

}
