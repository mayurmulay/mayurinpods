package Teacher;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.ChangeSection;
import Common.Clickevent;
import Common.GetCurrentURL;
import Common.Gototab;
import Common.LaunchApp;
import Common.Login;
import Data.Loger;
import Data.Read_Data;

public class ValidateAssignmentLevelCOAttainment {
	
	static Boolean flag=true;
	static String message="";
	public static void ValidateCOattainment(String Assname,String[] COnameandAttainment)  // send CO name and attainment in format CO1:80
	{
	try{
		
		int counter=0;
	    while(COnameandAttainment!=null)
	    {
	    	String[] data=COnameandAttainment[counter].split(":");
	    	String COname=data[0].trim();
	    	String Coattainment=data[1].trim();
		String TableString=LaunchApp.driver.findElement(By.xpath(".//*[@id='conceptChart2']//td/following-sibling::*")).getText();
		
		if(TableString.contains(Coattainment))
		{
			Assert.assertTrue(true, "Coattainment is correct for"+COname);
		}
		else
		{
			Assert.fail("Incorrect CO attainment for "+COname+"Expected="+Coattainment+"Actual="+data);
		}
	    }
		}catch(Exception e){e.printStackTrace();}
		
	}
	public static void ReadAssignmentLevelCOattainmentValidationData(String filename)
	{
		String[][] data=Read_Data.ReadData(filename);
		int counter=0;
		String Assname = null;
		String COname;
		String Coattainment;
		try{
		while(!data[counter][0].equalsIgnoreCase("End"))
		{
			int rowCounter=0;
			while(!data[counter][rowCounter].equalsIgnoreCase("End"))
			{
				String str=data[counter][rowCounter].trim();
				str=str.replace(";", ",");
				System.out.println("Str="+str);
				String[] data1=str.split(":");
				System.out.println("data1="+data1[0]);
				if(data1[0].equalsIgnoreCase("Username"))
				{
					String username=data1[1].trim();
					String pass=data1[2].trim();
					LaunchApp.Execute("Url will take from URL.csv file");
					Login.LoginD(username, pass);
				}
				if(data1[0].equalsIgnoreCase("Section"))
				{
					ChangeSection.selectSection(data1[1].trim());
				}
				if(data1[0].equalsIgnoreCase("AssignmentName"))
				{
					Assname=data1[1].trim();
					OpenAssignment(Assname);
					System.out.println("Assname="+Assname);
				}
				if(data1[0].equalsIgnoreCase("CO"))
				{
				//	COname=data1[1];
				//	Coattainment=data1[2];
			    //validateCOattainment(COname,Coattainment,Assname);
			    //System.out.println("Before Replace"+data1[1].trim());
					 data1[1]=data1[1].trim().replaceAll(";",",");
					try{
					 ValidateUICoattainment(data1[1].trim(),Assname);}catch(Exception e){}
				}
				if(data1[0].equalsIgnoreCase("QWPR"))
				{
					
					data1[1]=data1[1].replace("&",":");
					ValidateQWPR(data1[1].trim(),Assname);
				}
			   rowCounter++;
			}
			counter++;
		}
		}catch(Exception e){e.printStackTrace();}
	}

	public static void OpenAssignment(String Assname)
	{
		if(Assname.startsWith("SectionLevelReport"))
		{
			try
			{
			Gototab.execute("Reports");
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[@href='/Report/SectionConceptsReport']")).click();
			Thread.sleep(3000);
			Publish_Section_level_Report.selectAllAssignmentAndRecompute();
			Thread.sleep(3000);
			}catch(Exception e){Assert.fail("Not able open assignment"+Assname);}
		}
		else
		{
		try
		{
		Gototab.execute("Assignments");
		WebElement e=LaunchApp.driver.findElement(By.xpath(".//*[@id='sectionAssignmentDetails']/tbody/tr/td[2]//*[contains(text(),'"+Assname.trim()+"')]"));
		Clickevent.ClickEvent(e);
		Thread.sleep(5000);
		String assData=LaunchApp.driver.findElement(By.xpath(".//*[@id='sectionPerformanceChart']")).getText().trim();
		if(assData.contains(Assname))
		{
			Assert.assertTrue(true, "user is able to open assignment"+Assname);
		}
		else
		{
			Assert.fail("Not able open assignment"+Assname);
		}
		}catch(Exception e){Assert.fail("Not able open assignment"+Assname);}
		}
		
	}

	public static void validateCOattainment(String COname,String Coattainment,String Assname)
	{
		String TableString = null;
		try{
			
			Thread.sleep(10000);
			String str=LaunchApp.driver.getPageSource();
			//System.out.println(str);
			//List <WebElement> e=LaunchApp.driver.findElements(By.xpath(".//*[@id='conceptChart2']"));
			boolean flag1=false;
		//	for(WebElement element : e)
			{
				//element.getText();
				if(str.contains(COname+"',"+Coattainment))
				{
					flag1=true;
				}
			}
			if(flag1)
			{
				Assert.assertTrue(true, "Coattainment is correct for"+COname+"in assignment"+Assname);
				Loger.LogEvent("CO attainment for Assignment: "+Assname+"Co attainment :"+Coattainment, "-Pass");
			}
			else
			{
				try
				{
					
				}
				catch(Exception e){
				Assert.fail("Incorrect CO attainment for "+COname+"in assignment"+Assname+"Expected="+Coattainment);}
			}
		}catch(Exception e){e.printStackTrace();Assert.fail("Incorrect CO attainment for "+COname+"Expected="+Coattainment);}
	}
	
	public static void ValidateUICoattainment(String Coattainment,String Assname)
	{
		String TableString = null;
		try{
			
			Thread.sleep(10000);
			String str=LaunchApp.driver.getPageSource();
		//	System.out.println(str);
			//System.out.println("\n\n\n\nCo attanment for validation"+Coattainment);
			//List <WebElement> e=LaunchApp.driver.findElements(By.xpath(".//*[@id='conceptChart2']"));
			
		//	for(WebElement element : e)
			{
				//element.getText();
				if(str.contains(Coattainment))
				{
					flag=true;
				}
				else
				{
					flag=false;
				}
			}
			if(flag)
			{
				Assert.assertTrue(true, "Coattainment is correct for"+Coattainment+"in assignment"+Assname);
				Loger.LogEvent("CO attainment for Assignment: "+Assname+"Co attainment :"+Coattainment, "-Pass");
			}
			else
			{
				Loger.LogEvent("CO attainment for Assignment: "+Assname+"Co attainment :"+Coattainment, "-Fail");
				
				try
				{
					Exception e = new Exception();
					   throw e;
				}
				catch(Exception e){
					flag=false;
					message="Incorrect CO attainment for "+Coattainment+"in assignment"+Assname+"Expected="+Coattainment;
				//	Assert.fail("Incorrect CO attainment for "+Coattainment+"in assignment"+Assname+"Expected="+Coattainment);
					}
				
			}
		}catch(Exception e){e.printStackTrace();Assert.fail("Incorrect CO attainment for "+Coattainment+"Expected="+Coattainment);
		}
	}

	public static void ValidateQWPR(String report,String Assname)
	{
		
		String URL=GetCurrentURL.GetcurrentURL();
	    String activityId=URL.substring(URL.indexOf("="), URL.indexOf("#"));
	   LaunchApp.driver.get(LaunchApp.URL1+"/AssignmentList/ViewQuestionwisePerformanceData?activityId"+activityId);
	    try {
			Thread.sleep(10000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    String Curentreport= LaunchApp.driver.getPageSource();
	    Curentreport=Curentreport.replace("\"", "");
	    Curentreport=Curentreport.replace("&", ":");
	    Curentreport=Curentreport.replace(";", ",");
	    System.out.println("Curentreport="+Curentreport);
	    System.out.println("Expected Report="+report);
	    try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	    LaunchApp.driver.get(URL);
	   
		//	for(WebElement element : e)
			{
				//element.getText();
				if(Curentreport.contains(report))
				{
					flag=true;
				}
				else
				{
					flag=false;
				}
			}
			if(flag)
			{
				Assert.assertTrue(true, "Question wise paerformance Report in assignment"+Assname);
				Loger.LogEvent("Question wise paerformance Report in assignment :"+Assname,"-Pass");
			}
			else
			{
				Loger.LogEvent("Question wise paerformance Report in assignment :"+Assname, "-Fail");
				
				try
				{
					Exception e = new Exception();
					   throw e;
				}
				catch(Exception e){
					flag=false;
					message="Incorrect Question wise paerformance Report in assignment "+Assname;}
				
			}
	    		
	    
	    
	}
	public static void main(String []str)
	{
		ReadAssignmentLevelCOattainmentValidationData("AssignmentLevelCOattanmentValidation.csv");
	}
	@Test
	@Parameters({ "filename"})
	public static void exicute(String filename)
	{
		try{
		ReadAssignmentLevelCOattainmentValidationData(filename.trim());
		if(!flag)
		{
			Assert.fail(message);
		}
		}catch(Exception e){}
		
	}
	
	
}
