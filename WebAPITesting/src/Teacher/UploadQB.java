package Teacher;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;







import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Loger;

public class UploadQB {
	@Test
	@Parameters({ "name", "path"})
	public static void UploadQb(String name,String path)
	{
	try
	{
		
       String args1[]={"Manage Course"," "};
       Gototab.main(args1);
       JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
       jse.executeScript("window.scrollTo(50, 50)", "");
       LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Manage Question Banks')]")).click();
       LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Create Question Bank')]")).click();

 
   try {Thread.sleep(900);} catch (InterruptedException e) {e.printStackTrace();}
   LaunchApp.driver.findElement(By.xpath(".//*[@name='questionBankName']")).sendKeys(name);
 
      // LaunchApp.driver.findElement(By.xpath(".//*[@name='questionBankDefn']")).click();
       System.out.println("path"+path);
       //Runtime.getRuntime().exec(path.trim());
 
     // try {Runtime.getRuntime().exec(path.trim());Thread.sleep(70000);} catch (Exception e) {e.printStackTrace();}
       
       try{LaunchApp.driver.findElement(By.xpath(".//*[@name='questionBankDefn']")).sendKeys(path.trim());} catch (Exception e) {e.printStackTrace();}
      
      LaunchApp.driver.findElement(By.xpath(".//*[@value='Create Question Bank']")).click();
 
       try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
       try {Thread.sleep(9000);} catch (InterruptedException e) {e.printStackTrace();}
       MooveToElement.moveToElenment(".//*[@id='questionBankTable']");
       ExceptionHndeler.getScreen("QB creation"+name);
       String Data=LaunchApp.driver.findElement(By.xpath(".//*[@id='questionBankTable']")).getText();
       
       if(Data.contains(name.trim()))
       {
    	   Assert.assertEquals(1, 1);
    	   Loger.LogEvent("User is able to create QB", "-Pass");
       }
       else
       {
    	   Loger.LogEvent("User is unable to create QB", "-Fail");
    	   Assert.fail("Unable TO create QB");
    	  
       }
    	   
       
       
	}
	catch(Exception e)
	{
		e.printStackTrace();
		Assert.fail("Unable TO create QB");
		ExceptionHndeler.Log("QBUpload","QBUpload", e);
	}



}

}
