package Teacher;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.ChangeSection;
import Common.Clickevent;
import Common.Gototab;
import Common.LaunchApp;
import Common.Login;
import Data.Read_Data;

public class ValidateAssignmentLevelCOAttainment {
	
	public static void ValidateCOattainment(String Assname,String[] COnameandAttainment)  // send CO name and attainment in format CO1:80
	{
	try{
		
		int counter=0;
	    while(COnameandAttainment!=null)
	    {
	    	String[] data=COnameandAttainment[counter].split(":");
	    	String COname=data[0].trim();
	    	String Coattainment=data[1].trim();
		String TableString=LaunchApp.driver.findElement(By.xpath(".//*[@id='conceptChart2']//td[.='AutoCO1']/following-sibling::*")).getText();
		
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
		String Assname;
		String COname;
		String Coattainment;
		while(!data[counter][0].equalsIgnoreCase("End"))
		{
			int rowCounter=0;
			while(!data[counter][rowCounter].equalsIgnoreCase("End"))
			{
				String str=data[counter][rowCounter].trim();
				String[] data1=str.split(":");
				
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
				}
				if(data1[0].equalsIgnoreCase("CO"))
				{
					COname=data1[1];
					Coattainment=data1[2];
					validateCOattainment(COname,Coattainment);
				}
			   rowCounter++;
			}
			counter++;
		}
	}

	public static void OpenAssignment(String Assname)
	{
		try
		{
		Gototab.execute("Assignments");
		WebElement e=LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+Assname.trim()+"')]"));
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

	public static void validateCOattainment(String COname,String Coattainment)
	{
		String TableString = null;
		try{
			
			Thread.sleep(10000);
			String str=LaunchApp.driver.getPageSource();
			//System.out.println(str);
			//List <WebElement> e=LaunchApp.driver.findElements(By.xpath(".//*[@id='conceptChart2']"));
			boolean flag=false;
		//	for(WebElement element : e)
			{
				//element.getText();
				if(str.contains(COname+"',"+Coattainment))
				{
					flag=true;
				}
			}
			if(flag)
			{
				Assert.assertTrue(true, "Coattainment is correct for"+COname);
			}
			else
			{
				Assert.fail("Incorrect CO attainment for "+COname+"Expected="+Coattainment);
			}
		}catch(Exception e){e.printStackTrace();Assert.fail("Incorrect CO attainment for "+COname+"Expected="+Coattainment);}
	}

	public static void main(String []str)
	{
		ReadAssignmentLevelCOattainmentValidationData("AssignmentLevelCOattanmentValidation.csv");
	}
	
}
