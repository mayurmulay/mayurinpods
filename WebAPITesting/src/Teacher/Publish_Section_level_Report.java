package Teacher;


import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Tech.PublishSectionLevelReport;
import Common.AlertHandling;
import Common.Clickevent;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;

public class Publish_Section_level_Report {

	public static void main(String[] args) {
		
		try{
		Publish_Section_level_Report ps=new Publish_Section_level_Report();
		Execute();
		}catch (Exception e) {}
	}
	@Test
	public  static void Execute()
	{
		String[] args = new String[10];
		 args[0]=(String) "Reports";
		Gototab.main(args);
		LaunchApp.driver.findElement(By.xpath(".//*[@href='/Report/SectionConceptsReport']")).click();
		try {
			selectAllAssignmentAndRecompute();
			ExceptionHndeler.getScreen("SectionLevelReport");
			LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-id-5']")).click();
			Thread.sleep(1000);
			ExceptionHndeler.getScreen("SectionLevelReportTabular");
			try{ Unpublish();}catch (Exception e) {}
			 Thread.sleep(1000);
			// UnpublishIndirect();
			 Thread.sleep(1000);
			//Recompute();
			 
			Thread.sleep(1000);
			//ExceptionHndeler.getScreen("SectionLevel"+PublishSectionLevelReport.SectionId);
			Thread.sleep(1000);
			publish();
			Thread.sleep(6000);
			ExceptionHndeler.getScreen("CourseLevelReportAfterPublish");
		} catch (InterruptedException e) {
			
			AlertHandling.isAlertPresent();
			MooveToElement.moveToElenment();
			ExceptionHndeler.getScreen("SectionLevel"+PublishSectionLevelReport.SectionId);
			AlertHandling.isAlertPresent();
			ExceptionHndeler.Log("Section level Report","Section Level Report", e);
			e.printStackTrace();
		}
		
	}
	public static void Recompute()
	{
		
		try {
			WebElement element=LaunchApp.driver.findElement(By.xpath(".//*[@name='lnkCOAssessments']")); 
			ClickEvent(element);
			Thread.sleep(3000);
			 element=LaunchApp.driver.findElement(By.xpath(".//*[@class='uploadAssignmentsClass']")); 
			ClickEvent(element);
			Thread.sleep(40000);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void Unpublish()
	{
		 LaunchApp.driver.findElement(By.linkText("UnPublish Outcomes Data")).click();
		 waitForAlert();
		 waitForAlert();
	}
	public static void publish()
	{
		try {
		LaunchApp.driver.findElement(By.xpath(".//*[@href='/Report/DownloadConceptReport']")).click();	
		Thread.sleep(40000);
	
		LaunchApp.driver.findElement(By.linkText("Publish Outcomes Data")).click();
		Alert alert = LaunchApp.driver.switchTo().alert();
		LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-click='downloadCOAttainmentReport()']']")).click();	
		waitForAlert();
	    waitForAlert();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void UnpublishIndirect()
	{
		 LaunchApp.driver.findElement(By.linkText("UnPublish Data")).click();
		 waitForAlert();
		//waitForAlert();
	}
	public static void publishIndirect()
	{
		LaunchApp.driver.findElement(By.linkText("Publish Data")).click();
		Alert alert = LaunchApp.driver.switchTo().alert();
		waitForAlert();
	  //  waitForAlert();
	     
	}
	public static void ClickEvent(WebElement element){
		JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
	    jse.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
		
	}
	public static void waitForAlert()
	{
	   int i=0;
	   while(i++<100)
	   {
	        try
	        {
	        	Thread.sleep(2000);
	            Alert alert = LaunchApp.driver.switchTo().alert();
	            alert.accept();
	            break;
	        }
	        catch(Exception e)
	        {
	          
	          continue;
	        }
	   }
	}
	public static void selectAllAssignmentAndRecompute()
    {
		try{
		WebElement element=LaunchApp.driver.findElement(By.xpath(".//*[@name='lnkCOAssessments']")); 
		ClickEvent(element);
		Thread.sleep(3000);
		
		 List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[@type='checkbox' and @ancestor='false']"));
		 List <WebElement> li1=LaunchApp.driver.findElements(By.xpath(".//*[@type='checkbox' and @ancestor='true']"));
		 li.addAll(li1);
		 
		   	for(int j=0;j<li.size();j++)
			  {
				  WebElement e = li.get(j);
				  System.out.println(li.get(j).getAttribute("id"));
				  if ( !e.isSelected() )
				  {
					  Thread.sleep(1000);
					 Clickevent.ClickEvent(e); //First element is not clickable
                       
				       Thread.sleep(1000);
				  }
				  
			  }
		   	Thread.sleep(3000);
		   	element=LaunchApp.driver.findElement(By.xpath(".//*[@class='uploadAssignmentsClass']")); 
			ClickEvent(element);
			Thread.sleep(40000);
		}
		catch(Exception e){e.printStackTrace();}
    	
    }

}

