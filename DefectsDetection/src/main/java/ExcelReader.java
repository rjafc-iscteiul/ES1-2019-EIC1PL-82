import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {

	public static void main(String[] args) throws IOException{
		File file = new File("C:/Users/carolina.martins/Desktop/ISCTE/ES/Long-Method.xlsx");
		FileInputStream f = new FileInputStream(file);
		
		//creates the object for the XLSX excel file
		XSSFWorkbook wbk = new XSSFWorkbook(f);
		
		//gets the sheet
		XSSFSheet s = wbk.getSheetAt(0);
		
		//iterate rows
		Iterator<Row> rIterator = s.iterator();
		
		while(rIterator.hasNext()) {
			Row r = rIterator.next();
			
			Iterator<Cell> cIterator = r.cellIterator();
			
			while(cIterator.hasNext()) {
				Cell c = cIterator.next();
				System.out.println(c.toString() + ";");
			}
		}
		
		//wbk.close();
		f.close();
	}
}