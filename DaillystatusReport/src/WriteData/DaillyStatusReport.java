package WriteData;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class DaillyStatusReport {

	public static void main(String[] args) throws InterruptedException {
		
		try {
			DaillyStatusReporter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void DaillyStatusReporter() throws InterruptedException, IOException
	{
		String[][] Data=Read_Data.ReadData("Daily user estimation.csv");
		
		int i=0;
		while(!Data[i][0].equals("END"))
		{
			String DBname=Data[i][1].trim();
			String Filename=Data[i][2].trim();
			String Semisters=Data[i][3].trim();
			
		   Date d=new Date();
		   int date=d.getDate();
		   int month=d.getMonth();
		   int year=d.getYear();
		   String Date=(year+1900)+"-"+(month+1)+"-"+(date+1);
			System.out.println("Date"+Date);
			RestoreDB.ConnectDB(DBname, "D:\\E\\1Evenving Backup\\"+getPath(Filename));   //chaityanaya
		//   RestoreDB.ConnectDB(DBname, "E:\\"+getPath(Filename)); //mayur
			
			RestoreDB.ExicuteScript2(DBname,Semisters,Date);
			Thread.sleep(5000);
			RestoreDB.ExicuteScript1(DBname,Semisters,Date);
			
			i++;
		}
		String [][]d=new String[10][10];
		d[1][0]="Total Student to Attempt Test";
		d[1][1]=RestoreDB.TotalCount1+" ";
		WriteData.AddSheetToxlsx("mayur","Total Count",d);
		WriteData.writexlsx();
	}
	public static String getPath(String Filename)
	{
		
		 File directory = new File("D:\\E\\1Evenving Backup\\");   //chatiyna 
		//File directory = new File("E:\\");   //Mayur
	        //get all the files from a directory
	        File[] fList = directory.listFiles();
	        int i=0;
	        for (File file : fList){
	            System.out.println(file.getName());
	            try {
	            	{
					 if(file.getName().contains(Filename))
					 {
						 return file.getName();
					 }
					i++;
	            	}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			return Filename;
	}

}
