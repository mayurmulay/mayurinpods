package Teacher;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;

public class BloomsTaxonomyReport {
	@Test
	public static void Gradebook()
	{
		
		String[] args=new String[10]; 
	    args[0]=(String) "Reports";
		Gototab.main(args);
		
		 try {
				Thread.sleep(10000);
				LaunchApp.driver.findElement(By.xpath(".//*[@href='/Report/SectionAssignmentBloomsChartView']")).click();
				Thread.sleep(10000);	
		 } catch (InterruptedException e) {
				e.printStackTrace();
				if(AlertHandling.isAlertPresent())
				{
					LaunchApp.driver.findElement(By.xpath(".//*[@href='/Report/SectionAssignmentBloomsChartView']")).click();
				}
			}
		 MooveToElement.moveToElenment(".//*[contains(text(),'Section Bloom')]");
		 ExceptionHndeler.getScreen("SurveyReport");
		 List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[contains(text(),'Section Bloom')]"));
		  if(li.size()>0)
		  {
			  Assert.assertEquals(1, 1);
			  //MooveToElement.moveToElenment(".//*[contains(text(),'Section Bloom')]");
			  ExceptionHndeler.getScreen("SectionBloom'sTaxonomy Chart");
			  
		  }
		  else
		  {
			  Assert.fail("Not able to Open gradebook");
		  }
		
		 if(li.size()>0)
		  {
			  Assert.assertEquals(1, 1);
		  }
		  else
		  {
			  Assert.fail("Not able to Open gradebook");
		  }
	}

}