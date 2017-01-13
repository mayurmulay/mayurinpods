package Teacher;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Loger;

public class ShareAssignment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Test
	@Parameters({"Assignment", "Department", "Subject", "DestinationCourse","DestinationSection","ShareType" })
	public static void ShareAssignment(String Assignment,String Department,String Subject,String DestinationCourse,String DestinationSection,String ShareType)
	{
		try
		{
			String[] args=new String[10]; 
		    args[0]=(String) "Manage Course";
			Gototab.main(args);
			try {
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[@assignmentname='"+Assignment.trim()+"' and @name='assignmentShare']")).click();
			} catch (InterruptedException e) {  e.printStackTrace();
			if(AlertHandling.isAlertPresent())
				{
				LaunchApp.driver.findElement(By.xpath(".//*[@assignmentname='"+Assignment.trim()+"' and @name='assignmentShare']")).click();
				}
			}
			
			try {
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[@name='lnkTab1']")).click();
				System.out.println("Clicked on share");
				Loger.LogEvent("Share","Click on share"); 
			} catch (InterruptedException e) {  e.printStackTrace();
			if(AlertHandling.isAlertPresent())
				{
				LaunchApp.driver.findElement(By.xpath(".//*[@name='lnkTab1']")).click();
				}
			}
			
		if(ShareType.equals("Institute"))
		{
			
			try
	    	{
				
			LaunchApp.driver.findElement(By.xpath(".//*[@name='sharewithall']")).click();
			Loger.LogEvent("Share","Click on sharewithall"); 
			Thread.sleep(10000);

	    	Alert alert = LaunchApp.driver.switchTo().alert();
	    	String text=alert.getText();


	    	if(text.equals("Are you sure you want to share with institute"))
	    	{alert.accept();}
	    	else
	    	{
	    		System.out.println("Error in sharing"+ text);
	    		Loger.LogEvent(text,"Error");
	    	}
	    	Thread.sleep(10000);
	    	 alert = LaunchApp.driver.switchTo().alert();
	    	text=alert.getText();
	    	if(text.equals("Shared successfully with institute"))
	    	{alert.accept();		      }
	    	else
	    	{
	    		System.out.println("Error in sharing Demo"+ text);
	    		Loger.LogEvent(text,"Error");
	    		Assert.fail("not able to Shared successfully with institute");
		    }MooveToElement.moveToElenment();
	    	ExceptionHndeler.getScreen("Institute Assignment"+Assignment);} catch (Exception e2) {e2.printStackTrace();}
			ExceptionHndeler.getScreen("Institute Assignment"+Assignment);
			Thread.sleep(10000);
		}
		else
		{
			try {
	    Thread.sleep(1000);Thread.sleep(10000);
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='selectDepartment']"))).selectByVisibleText(Department.trim());
			} catch (InterruptedException e) {  e.printStackTrace();
			if(AlertHandling.isAlertPresent())
				{
				new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='selectDepartment']"))).selectByVisibleText(Department.trim());
				}
			}
			
			try {
			    Thread.sleep(1000);Thread.sleep(10000);
			    new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='selectSubject']"))).selectByVisibleText(Subject.trim());
					} catch (InterruptedException e) {  e.printStackTrace();
					if(AlertHandling.isAlertPresent())
						{
						new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='selectSubject']"))).selectByVisibleText(Subject.trim());
						}
				  }
			
		
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='selectSubject']"))).selectByVisibleText(Subject.trim());
		Thread.sleep(10000);
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='selectCourse']"))).selectByVisibleText(DestinationCourse.trim());
		Thread.sleep(10000);
		if(ShareType.equals("Course"))
		{
			LaunchApp.driver.findElement(By.xpath(".//*[@id='selectEntireCourse']")).click();
			 MooveToElement.moveToElenment(".//*[@id='selectEntireCourse']");
			 
		}	
		else
		{
			LaunchApp.driver.findElement(By.xpath(".//*[.='"+DestinationSection+"']//*[@name='sectionCheckBox']")).click();
			 MooveToElement.moveToElenment(".//*[.='"+DestinationSection+"']//*[@name='sectionCheckBox']");
			 
		}
			try
			{  
				 Thread.sleep(3000);
				 MooveToElement.moveToElenment(".//*[.='"+DestinationSection+"']//*[@name='sectionCheckBox']");
				 ExceptionHndeler.getScreen(" Assignment Sharing "+Assignment);
		  LaunchApp.driver.findElement(By.xpath("html/body/div[6]/div[3]/div/button[1]")).click();
		  Thread.sleep(10000);
		  Alert alert = LaunchApp.driver.switchTo().alert();
	      String text=alert.getText();
	      System.out.println("Alert Expected="+text);
	      
		  if(text.contains("Shared successfully"))
	    	{alert.accept(); System.out.println("Alert Expected");}
	    	else
	    	{
	    		System.out.println("Error in sharing Demo"+ text);
	    		Loger.LogEvent(text,"Error");
	    		Assert.fail("not able to Shared successfully with Course");
		    }}
			catch(Exception e)
			{
				e.printStackTrace();
				ExceptionHndeler.Log("Error","share To Course ", e);
				
		    }
			System.out.println("going to closing");
			
		}
		Thread.sleep(10000);
		try{
			System.out.println("In closing");
		LaunchApp.driver.findElement(By.xpath("html/body/div[6]/div[3]/div/button[2]")).click();      
		}
		catch(Exception e){e.printStackTrace();LaunchApp.driver.findElement(By.xpath("html/body/div[6]/div[3]/div/button[2]")).click();    }
		//.//*[@type='button' and contains(text(),'Close')]
		              //.//*[@type='button' and contains(text(),'Close')]
		}
		
		catch(Exception e)
		{
			AlertHandling.isAlertPresent();
			ExceptionHndeler.Log("Alert","Create lesson", e);
		}
	}

}
