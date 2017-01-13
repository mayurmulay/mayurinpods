package Teacher;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;

public class CourseOutcomeReport {
	@Test
	public static void Gradebook()
	{
		
		String[] args=new String[10]; 
	    args[0]=(String) "Reports";
		Gototab.main(args);
		
		 try {
				Thread.sleep(10000);
				LaunchApp.driver.findElement(By.xpath(".//*[@class='homepage_section_heading' and contains(text(),'Course Outcome Report')]/../a")).click();
				Thread.sleep(10000);	
		 } catch (InterruptedException e) {
				e.printStackTrace();
				if(AlertHandling.isAlertPresent())
				{
					LaunchApp.driver.findElement(By.xpath(".//*[@class='homepage_section_heading' and contains(text(),'Course Outcome Report')]/../a")).click();
				}
			}
		 MooveToElement.moveToElenment(".//*[@name='lnkCOAssessments']");
		 ExceptionHndeler.getScreen("CO_attainment_Report");
		 List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[@name='lnkCOAssessments']"));
		  if(li.size()>0)
		  {
			  Assert.assertEquals(1, 1);
			  MooveToElement.moveToElenment(".//*[@id='ui-id-5']");
			  ExceptionHndeler.getScreen("CO attainment Report");
			  try {
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-id-5']"));
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  MooveToElement.moveToElenment(".//*[@id='ui-id-5']");
			  ExceptionHndeler.getScreen("CO attainment Report Tabular View");
			  JavascriptExecutor js = ((JavascriptExecutor) LaunchApp.driver);

				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				
				 ExceptionHndeler.getScreen("Lesson Course Outcome Coverage");
		  }
		  else
		  {
			  Assert.fail("Not able to Open CO attainment Report");
		  }
		 
		 
	}

}
