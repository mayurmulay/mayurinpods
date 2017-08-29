package Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Loger {

	public static void main(String[] args) {
		LogEvent("mayur","test");
		LogEvent("mayur","test");
		LogEvent("mayur","test");
		LogEvent("mayur","test");
	}
	public static void CreateLogFile(String str)
	{
		int flag=0;
		int day, month, year;
	      GregorianCalendar date = new GregorianCalendar();
	      day = date.get(Calendar.DAY_OF_MONTH);
	      month = date.get(Calendar.MONTH);
	      year = date.get(Calendar.YEAR);
	      String Fname="";
	      if(str.equals("log")){
	       Fname=day+"_"+month+"_"+year+".txt";}
	      else
	      {
	    	  Fname=day+"_"+month+"_"+year+"Error.txt";
	      }
	      
	      File dir = new File("D:\\Log");
		  for (File file : dir.listFiles()) {
		   if (file.getName().equals(Fname)) {
			   flag=1;
		   }
		      
		   }
		  if(flag==0)
		  {
			  Writer writer = null;

			  try {
			      writer = new BufferedWriter(new OutputStreamWriter(
			            new FileOutputStream("D:\\Log\\"+Fname), "utf-8"));
			     
			  } catch (IOException ex) {
			    // report
			  } finally {
			     try {writer.close();} catch (Exception ex) {/*ignore*/}
			  }
		  }
	}
	public static void LogEvent(String message,String type)
	{
		if(!(message.equals("Error")))
		{
		CreateLogFile("log");
		int day, month, year;
		int min,hour;
	      GregorianCalendar date = new GregorianCalendar();
	      day = date.get(Calendar.DAY_OF_MONTH);
	      month = date.get(Calendar.MONTH);
	      year = date.get(Calendar.YEAR);
	      min=date.get(Calendar.MINUTE);
	      hour=date.get(Calendar.HOUR);
	      String time1=day+"/"+month+"/"+year+"   "+hour+":"+min;
	      String Fname=day+"_"+month+"_"+year+".txt";
	      try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\Log\\"+Fname, true)))) {
	    	    out.println("\n"+time1+"      "+type+"     "+message);
	    	}catch (IOException e) {
	    	    e.printStackTrace();
	    	}
		}
		if(type.equals("Error") || message.equals("Error"))
		{
			CreateLogFile("Error");
			int flag=0;
			int day, month, year;
			int min,hour;
		      GregorianCalendar date = new GregorianCalendar();
		      day = date.get(Calendar.DAY_OF_MONTH);
		      month = date.get(Calendar.MONTH);
		      year = date.get(Calendar.YEAR);
		      min=date.get(Calendar.MINUTE);
		      hour=date.get(Calendar.HOUR);
		      String time1=day+"/"+month+"/"+year+"   "+hour+":"+min;
		      String Fname=day+"_"+month+"_"+year+"Error.txt";
		      try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\Log\\"+Fname, true)))) {
		    	    out.println("\n"+time1+"      "+type+"     "+message);
		    	}catch (IOException e) {
		    	    //exception handling left as an exercise for the reader
		    	}
			
			}
		if(type.endsWith("Pass")||type.endsWith("Fail"))
		{
			CreateLogFile("Report");
			int day, month, year;
			int min,hour;
		      GregorianCalendar date = new GregorianCalendar();
		      day = date.get(Calendar.DAY_OF_MONTH);
		      month = date.get(Calendar.MONTH);
		      year = date.get(Calendar.YEAR);
		      min=date.get(Calendar.MINUTE);
		      hour=date.get(Calendar.HOUR);
		      String time1=day+"/"+month+"/"+year+"   "+hour+":"+min;
		      String Fname=day+"_"+month+"_"+year+"Report.txt";
		      try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\Log\\"+Fname, true)))) {
		    	    out.println("\n"+time1+"      "+type+"     "+message);
		    	}catch (IOException e) {
		    	    e.printStackTrace();
		    	}
		}
		
	}
	public static void LogException(String type,String Location,String exception)
	{
		System.out.println("/n/n/n"+exception);
		CreateLogFile("Exceptions");
		int flag=0;
		int day, month, year;
		int min,hour;
	      GregorianCalendar date = new GregorianCalendar();
	      day = date.get(Calendar.DAY_OF_MONTH);
	      month = date.get(Calendar.MONTH);
	      year = date.get(Calendar.YEAR);
	      min=date.get(Calendar.MINUTE);
	      hour=date.get(Calendar.HOUR);
	      String time1=day+"/"+month+"/"+year+"   "+hour+":"+min;
	      String Fname=day+"_"+month+"_"+year+"Exceptions.txt";
	      try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\Log\\"+Fname, true)))) {
	    	    out.println("\n\n\n"+time1+"      "+type+"     "+Location+"     "+exception);
	    	}catch (IOException e) {
	    	    //exception handling left as an exercise for the reader
	    	}
	}

}

