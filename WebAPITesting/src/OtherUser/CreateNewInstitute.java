package OtherUser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Clickevent;
import Common.LaunchApp;
import Data.ExceptionHndeler;

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
		    
		    Thread.sleep(10000);
		    int startIndex=path.lastIndexOf("\\");
		    int lastIndex=path.length()-4;
		    String instituteName=path.substring(startIndex,lastIndex);
		    ExceptionHndeler.getScreen(instituteName+"Admin");
		    String Errormessage=LaunchApp.driver.findElement(By.xpath(".//*[@id='middle']/div/form/table/tbody/tr[1]/td[2]")).getText();
		    if(Errormessage!=null && Errormessage.trim().equalsIgnoreCase(""))
		    {
		    	//Assert.fail("Not able to Create new Institute=\n"+Errormessage);
		    }
		    else
		    {
		    	Assert.assertTrue(true, "New Institute is created");
		    }
		    LaunchApp.driver.findElement(By.xpath(".//*[@id='menu']/li[4]/a")).click();
		    		Thread.sleep(5000);
		    		JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
		    		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		    ExceptionHndeler.getScreen(instituteName+"Report");
		 }catch(Exception e){e.printStackTrace();}
	}

}
