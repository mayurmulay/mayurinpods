package NBAReportTesting;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompairNBAReports {
	String [] oldData=new String[1000];
	String [] newData=new String[1000];
	public static void main(String str[])
	{
		ReportReading();
	}
	
	public static void ReportReading()
	{
		 File directory = new File("D:\\API_Responce\\JSON");
	        //get all the files from a directory
	        File[] fList = directory.listFiles();
	        int i=0;
	        for (File file : fList){
	            System.out.println(file.getName());
	            try {
	            	if(i<=3)
	            	{
					Compaire(file.getName());
					i++;
	            	}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	 }
	public static void Compaire(String name) throws IOException
	{
		FileInputStream fstream1 = new FileInputStream("D:\\API_Responce\\JSON\\"+name.trim());
		FileInputStream fstream2 = new FileInputStream("D:\\API_Responce\\JSON - OLD\\"+name.trim());
		 
		 DataInputStream in1 = new DataInputStream(fstream1);
		  BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
		 DataInputStream in2 = new DataInputStream(fstream2);
		  BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
		 
		String strLine1;
		String strLine2 = null;
		 
		while ((strLine1 = br1.readLine())  != null)   {
			
			try {
				strLine2=br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			strLine1.trim();
			strLine2.trim();
		      if(!strLine1.equals(strLine2)  )
		      { System.out.println("\n\n\nold  "+name+strLine2);System.out.println("new  "+name+strLine1);}
		      
		}
	}

	

	
	}



