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

public class ValidateGradebook {
	@Test
	public static void Gradebook()
	{
		
		String[] args=new String[10]; 
	    args[0]=(String) "Reports";
		Gototab.main(args);
		
		 try {
			 Thread.sleep(10000);
				LaunchApp.driver.findElement(By.xpath(".//*[@class='homepage_section_heading' and contains(text(),'Gradebook')]/../a")).click();
				Thread.sleep(10000);	
		 } catch (InterruptedException e) {
				e.printStackTrace();
				if(AlertHandling.isAlertPresent())
				{
					LaunchApp.driver.findElement(By.xpath(".//*[@class='homepage_section_heading' and contains(text(),'Gradebook')]/../a")).click();
				}
			}
		 MooveToElement.moveToElenment(".//*[@id='chkShowDetails']");
		 ExceptionHndeler.getScreen("SurveyReport");
		 List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[@id='chkShowDetails']"));
		  if(li.size()>0)
		  {
			  Assert.assertEquals(1, 1);
			  li.get(0).click();
			  ExceptionHndeler.getScreen("BeforeUncheckShowAll");
			  li.get(0).click();
			  ExceptionHndeler.getScreen("AfterUncheckShowAll");
			  
		  }
		  else
		  {
			  Assert.fail("Not able to Open gradebook");
		  }
		 
		 try {
			 Thread.sleep(10000);
				LaunchApp.driver.findElement(By.xpath(".//*[@name='downloadExcel']")).click();
			} catch (InterruptedException e) {
				e.printStackTrace();
				if(AlertHandling.isAlertPresent())
				{
					LaunchApp.driver.findElement(By.xpath(".//*[@name='downloadExcel']")).click();
				}
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
