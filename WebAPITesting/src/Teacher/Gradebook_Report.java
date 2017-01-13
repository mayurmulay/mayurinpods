package Teacher;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Clickevent;
import Common.Gototab;
import Common.LaunchApp;
import Data.ExceptionHndeler;

public class Gradebook_Report {
	@Test
	@Parameters({ "Sectionname"})
	public static void Gradebook(String Sectionname)
	{
		try
		{
	String args1[]={"Reports"," "};
	Gototab.main(args1);
	JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
	jse.executeScript("window.scrollTo(50, 50)", "");
	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Reports')]")).click();
	Thread.sleep(10000);
	 //LaunchApp.driver.findElement(By.xpath(".//*[@class='homepage_section_headig']/*[1]")).click();
	Clickevent.clickLinkByHref("/Report/Gradebook?sectionId",".//*[.='"+Sectionname.trim()+"']");
	List <WebElement> li =LaunchApp.driver.findElements(By.xpath(".//*[@id='chkShowDetails']"));
    if((li.size()>0))
    {
 	   Assert.assertEquals(1, 1);
    }
    else
    {
 	   Assert.fail("Unable TO Open Gradebook ");
    }
    Thread.sleep(5000);
    LaunchApp.driver.findElement(By.xpath(".//*[@name=' downloadExcel']")).click();
    Thread.sleep(10000);
    ExceptionHndeler.getScreen("Gradebook");
	
   
		}
	catch (InterruptedException e) {e.printStackTrace();}
		
		
		}
}