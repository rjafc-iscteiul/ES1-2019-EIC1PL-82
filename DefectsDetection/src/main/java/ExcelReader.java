import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author carolina.martins
 * @version 1.0
 * 
 */

public class ExcelReader {

	/**
	 * File value for the file location to be displayed
	 */
	private File file;
	/**
	 * XSSFWorkbook value allows the reading of an excel file through an file input
	 * stream
	 */
	private XSSFWorkbook wbk;

	/**
	 * This method reads an excel file and runs through it so afterwards, when this
	 * method is called, it returns a default table model that will be used to
	 * display the excel file to the user through an GUI
	 * 
	 * @param filename name of the file the method will read
	 * @return returning a default table model derived from the reading of the excel
	 *         file
	 */
	public DefaultTableModel readFile(String filename) {

		file = new File(filename);

		DefaultTableModel data = new DefaultTableModel();

		FileInputStream f = null;

		try {
			f = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// creates the object for the XLSX excel file
		wbk = null;
		try {
			wbk = new XSSFWorkbook(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// gets the sheet
		XSSFSheet s = wbk.getSheetAt(0);

		// iterate rows
		Iterator<Row> rIterator = s.iterator();

		Vector<Object> v = new Vector<Object>();

		int iteratorOfColumns = 0;

		while (rIterator.hasNext()) {
			Row r = rIterator.next();

			Iterator<Cell> cIterator = r.cellIterator();

			while (cIterator.hasNext()) {
				Cell c = cIterator.next();
				if (iteratorOfColumns == 0) {
					data.addColumn(c.toString());
				} else {
					v.add(c.toString());
				}
			}
			if (iteratorOfColumns != 0) {
				data.addRow(v);
				v = new Vector<Object>();
			}

			iteratorOfColumns++;

		}

		try {
			wbk.close();
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * changes the value of the class attribute called xssfworkbook
	 * 
	 * @param xssfworkbook value of XSSFWorkbook class that will update the value of the
	 * class attribute xssfworkbook
	 * @return returns the class attribute named xssfworkbook
	 */
	public XSSFWorkbook setXSSFWorkbook(XSSFWorkbook xssfworkbook) {
		return this.wbk = xssfworkbook;
	}
}