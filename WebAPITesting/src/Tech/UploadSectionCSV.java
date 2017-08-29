package Tech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Clickevent;
import Common.LaunchApp;

public class UploadSectionCSV {
	
	@Test
	@Parameters({"path"})
	public static void CreateSectionCSV(String path)
	{
	try{
		Thread.sleep(2000);
		TechChangeSection.TechChangeSectionById("1");
		Thread.sleep(2000);
	    Clickevent.clickLinkByHref("/Admin/Index", ".//*[@id='side-menu']/li/a");
	    Thread.sleep(2000);
	    Clickevent.clickLinkByHref("#tabsManage-2", ".//*[@id='ui-id-2']");
	    Thread.sleep(2000);
	    Clickevent.clickLinkByHref("/admin/coursemanagement", ".//*[@id='tabsManage-2']/a");
	    Thread.sleep(2000);
	    Clickevent.clickLinkByHref("#tabs-2", ".//*[@id='ui-id-2']");
	    WebElement element=LaunchApp.driver.findElement(By.xpath(".//*[@id='sectionDefn']"));
	    element.sendKeys(path);
	    Thread.sleep(2000);
	    LaunchApp.driver.findElement(By.xpath(".//*[@value='Create Section']")).click();
	    Thread.sleep(2000);
	    
	    String str=LaunchApp.driver.getPageSource();
	    if(str.contains("Success"))
	    {
	    	Assert.assertEquals(1, 1);
			Assert.assertTrue(true, "User is able Upload Section CSV");
	    }
	    else
	    {
	    	Assert.fail("User is able Upload Section CSV");
	    }
	    
	   }catch(Exception e){e.printStackTrace();}
	}

}
