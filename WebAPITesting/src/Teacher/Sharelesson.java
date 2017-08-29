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

public class Sharelesson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Test
	@Parameters({ "Chapter", "lesson", "Department", "Subject", "DestinationCourse","DestinationSection","ShareType" })
	public static void ShareLesson(String Chapter,String lesson,String Department,String Subject,String DestinationCourse,String DestinationSection,String ShareType)
	{
		try
		{
			String[] args=new String[10]; 
		    args[0]=(String) "Lessons";
			Gototab.main(args);
			
			try {
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+Chapter+"')]")).click();
			} catch (InterruptedException e) {  e.printStackTrace();
			if(AlertHandling.isAlertPresent())
				{
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+Chapter+"')]")).click();
				}
			}
			
			try {
				Thread.sleep(10000);
				LaunchApp.driver.findElement(By.xpath(".//*[@lessonname='"+lesson.trim()+"' and @name='lessonShare']")).click();
			} catch (InterruptedException e) {  e.printStackTrace();
			if(AlertHandling.isAlertPresent())
				{
				LaunchApp.driver.findElement(By.xpath(".//*[@lessonname='"+lesson.trim()+"' and @name='lessonShare']")).click();
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
				Thread.sleep(5000);
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
	    	{alert.accept();}
	    	
	    	else
	    	{
	    		System.out.println("Error in sharing Demo"+ text);
	    		Loger.LogEvent(text,"Error");
	    		Assert.fail("not able to Shared successfully with institute");
		    }
	    	MooveToElement.moveToElenment();
	    	ExceptionHndeler.getScreen("Institute share"+lesson);
	    	ExceptionHndeler.getScreen("share"+lesson);} catch (Exception e2) {e2.printStackTrace();}
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
		}	
		else
		{
			LaunchApp.driver.findElement(By.xpath(".//*[.='"+DestinationSection+"']//*[@name='sectionCheckBox']")).click();
		}
			try
			{  
		  LaunchApp.driver.findElement(By.xpath("html/body/div[4]/div[3]/div/button[1]")).click();
		  Thread.sleep(5000);
		  Alert alert = LaunchApp.driver.switchTo().alert();
	      String text=alert.getText();
	     
		  if(text.equals("Shared successfully"))
	    	{alert.accept();}
	    	else
	    	{
	    		System.out.println("Error in sharing Demo"+ text);
	    		Loger.LogEvent(text,"Error");
	    		Assert.fail("not able to Shared successfully with Course");
		    }ExceptionHndeler.getScreen("share"+lesson);}
			 
			catch(Exception e)
			{
				ExceptionHndeler.Log("Error","share To Course ", e);
				e.printStackTrace();
		    }
		}
		Thread.sleep(10000);
		try{
		LaunchApp.driver.findElement(By.xpath(".//*[@type='button' and contains(text(),'Close')]")).click();      
		}
		catch(Exception e){e.printStackTrace();LaunchApp.driver.findElement(By.xpath(".//*[@type='button' and contains(text(),'Close')]")).click();    }
		//.//*[@type='button' and contains(text(),'Close')]
		}
		
		catch(Exception e)
		{
			ExceptionHndeler.Log("Alert","Create lesson", e);
			e.printStackTrace();
		}
	}

}
