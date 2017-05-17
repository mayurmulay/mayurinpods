package WriteData;

import java.io.FileOutputStream;
import java.io.IOException;
 
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteData {

	 public static XSSFWorkbook workbook = new XSSFWorkbook();
	    public static void main(String[] args) throws IOException {
	    	
	    	Object[][] bookData = {
	                {"Head First Java", "Kathy Serria", 79},
	                {"Effective Java", "Joshua Bloch", 36},
	                {"Clean Code", "Robert martin", 42},
	                {"Thinking in Java", "Bruce Eckel", 35},
	        };
	    	
	    	AddSheetToxlsx("demo","Demo1",bookData);
	    	AddSheetToxlsx("demo","Demo2",bookData);
	    	AddSheetToxlsx("demo","Demo3",bookData);
	    	AddSheetToxlsx("demo","Demo4",bookData);
	    	
	    	writexlsx();
	    }
	    public static void AddSheetToxlsx(String filename,String SheetName,  Object[][] Data) throws IOException
	    {
	      
	        XSSFSheet sheet = workbook.createSheet(SheetName);
	       int rowCount = 0;
	         for (Object[] aBook : Data) {
	            Row row = sheet.createRow(++rowCount);
	            int columnCount = 0;
	             
	            for (Object field : aBook) {
	                Cell cell = row.createCell(++columnCount);
	                if (field instanceof String) {
	                    cell.setCellValue((String) field);
	                } else if (field instanceof Integer) {
	                    cell.setCellValue((Integer) field);
	                }
	            }
	             
	        }
	         
	     
	    }
	    public static void writexlsx()
	    {
	    	
	    	Date d=new Date();
	    	    @SuppressWarnings("deprecation")
				String fileName="User Estimation Report_"+d.getDate()+"_"+(d.getMonth()+1)+".xlsx";
	    	 try (FileOutputStream outputStream = new FileOutputStream("D:\\"+fileName)) {
		            workbook.write(outputStream);
		            outputStream.close();
		        }
	    	catch(Exception e){e.printStackTrace();}
	    }
	 
	}

