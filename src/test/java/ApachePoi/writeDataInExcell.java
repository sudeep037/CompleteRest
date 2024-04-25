package ApachePoi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class writeDataInExcell {

	public static void main(String[] args) throws IOException {
		
	
	// Create Workbook
	
	XSSFWorkbook workbook = new XSSFWorkbook();
	XSSFSheet sheet = workbook.createSheet("Employee2 Info");
	
	// 2D Array
	        Object empData [][] = {
			{"EmpID", "Name", "Job"},
			{101, "David", "Test Engineer"},
			{102, "Smith", "Devops Engineer"},
			{103, "Arun", "Automation Test Engg."},
	};
	
	//Approach 1 -> By using for loop
	int rows = empData.length; // return no of rows
	int cols = empData[0].length; // return no of cells

    System.out.println(rows);
    System.out.println(cols);
    
    // Approch 2 -> By using For each loop
    
    int rowCount = 0;
    
    for(Object emp[]:empData) 
    {
    	XSSFRow row = sheet.createRow(rowCount++);
    	int colCount = 0;
    	  for(Object value:emp)
    	  {
    		  XSSFCell cell = row.createCell(colCount++);
    		  
    		   if(value instanceof String)
    				cell.setCellValue((String)value);
    				
    		   if(value instanceof Integer)
    				cell.setCellValue((Integer)value);
    				
    		   if(value instanceof Boolean)
    				cell.setCellValue((Boolean)value);	
    	  }
    }
    
	String filePath = ".\\testData\\Employee2 Info.xlsx";
	FileOutputStream fos = new FileOutputStream(filePath);
	workbook.write(fos);
	fos.close();
	System.out.println("Employee2.xlsx file has written successfull");
	
	}
}
