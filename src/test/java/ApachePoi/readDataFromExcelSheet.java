package ApachePoi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readDataFromExcelSheet {

	public static void main(String[] args) throws IOException {
		
	
	// Iterator 
	
	String excelPath = ".\\testData\\employee.xlsx";
	FileInputStream fis = new FileInputStream(excelPath);
	
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet sheet = wb.getSheetAt(0); // XSSFSheet sheet = wb.getSheet("Sheet1"); we can write this also...
	
	Iterator iterator = sheet.rowIterator();  // here we are adding sheet into iterator -> hence iterator consists of all excel data
	
	while(iterator.hasNext()) // Iterator checks whether it consists of next record or not
	{
		XSSFRow row  = (XSSFRow) iterator.next(); // get the row
		Iterator celliterator = row.cellIterator();  // Since a single row consists of many columns -> so we are adding each columns to Iterator again
		
		while(celliterator.hasNext()) // Again Iterator checks whether it consists of next cell or not
		{
			Cell cell = row.getCell(0);   // getting the cell
			switch (cell.getCellType()) // Check type of data in cells 
			{
			case STRING : String data1 = cell.getStringCellValue(); break;
			case NUMERIC: cell.getNumericCellValue(); break;
			case BOOLEAN: cell.getBooleanCellValue(); break;	
			}
		}
	}
	}
}
