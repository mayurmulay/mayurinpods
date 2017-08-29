package Teacher;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;

public class BloomsCoverageReport {
	@Test
	public static void Gradebook()
	{
		try
		{
	String args1[]={"Reports"," "};
	Gototab.main(args1);
	JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
	jse.executeScript("window.scrollTo(50, 50)", "");
	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Reports')]")).click();
	Thread.sleep(10000);
	
	
	//homepage_section_heading
	 LaunchApp.driver.findElement(By.xpath(".//*[@name='homepage_section_heading']/*[1]")).click();
	 Thread.sleep(10000);
	List <WebElement> li =LaunchApp.driver.findElements(By.xpath(".//*[@id='chkShowDetails']"));
    if((li.size()>0))
    {
 	   Assert.assertEquals(1, 1);
    }
    else
    {
 	   Assert.fail("Unable TO Open Blooms Report ");
    }
    Thread.sleep(5000);
    LaunchApp.driver.findElement(By.xpath(".//*[@name=' downloadExcel']")).click();
    Thread.sleep(10000);
    MooveToElement.moveToElenment(".//*[@name=' downloadExcel']");
    ExceptionHndeler.getScreen("Gradebook");
	
   
		}
	catch (InterruptedException e) {e.printStackTrace();}
		}
}
