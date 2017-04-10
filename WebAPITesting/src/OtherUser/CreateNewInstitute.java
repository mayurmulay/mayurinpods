package OtherUser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Clickevent;
import Common.LaunchApp;

public class CreateNewInstitute {
	   
	@Test
	@Parameters({"path"})
	public static void CreateNewInstituteByCsv(String path) 
	{
		try{
			
			Thread.sleep(2000);
		    Clickevent.clickLinkByHref("/Admin", ".//*[@id='menu']/li/a");
		    Thread.sleep(2000);
		    WebElement element=LaunchApp.driver.findElement(By.xpath(".//*[@id='classicInstDefn']"));
		    element.sendKeys(path);
		    Thread.sleep(2000);
		    LaunchApp.driver.findElement(By.xpath(".//*[@value='Create institute']")).click();
		    
		    Thread.sleep(5000);
		 }catch(Exception e){e.printStackTrace();}
	}

}
