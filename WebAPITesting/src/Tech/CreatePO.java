package Tech;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import Common.AlertHandling;
import Common.Clickevent;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Loger;


public class CreatePO {
	     @Test
		@Parameters({ "PO_name", "PO_Description", "Program"})
		public static void createCO(String PO_name, String PO_Description, String Program)
		{
			try
			{
				Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath("//.//*[@id='side-menu']/li[7]/a")).click();  //OBE link for Tech
			    Thread.sleep(3000);
		WebElement e1=	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'POs')]"));
		LaunchApp.ClickEvent(e1);
			     Thread.sleep(3000);
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='program_select']"))).selectByVisibleText(Program.trim());
			     Thread.sleep(15000);
		
			    Thread.sleep(3000);
			    try
			    {
			    	Thread.sleep(3000);
			    	if(LaunchApp.driver.findElements(By.xpath(".//*[contains(text(),'Update') and @class='btn']")).size() != 0)
			    	{	
			    	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Update') and @class='btn']")).click();
			    	Loger.LogEvent("Update PO","PO  updated"); 
			    	Thread.sleep(2000);
			    	AlertHandling.waitForAlert();
			    	Thread.sleep(5000);
			    	LaunchApp.driver.navigate().refresh();   Thread.sleep(20000);
					new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='program_select']"))).selectByVisibleText(Program.trim());
				     Thread.sleep(15000);
			    
			    	}
			    	else
			    	{
			    		if(LaunchApp.driver.findElements(By.xpath(".//*[contains(text(),'Revise') and @class='btn']")).size() != 0)
				    	{	
				    	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Revise') and @class='btn']")).click();
				    	Loger.LogEvent("Update PO","PO  updated"); 
				    	Thread.sleep(2000);
				    //	AlertHandling.waitForAlert();
				    	Thread.sleep(5000);
				    	LaunchApp.driver.navigate().refresh();   Thread.sleep(20000);
						new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='program_select']"))).selectByVisibleText(Program.trim());
					     Thread.sleep(15000);
				        }
			    	}
			    }catch(Exception e){Loger.LogEvent("Update PO","PO not updated"); 
			    Assert.fail("Not able to click on Update PO");
			    }
			    
			    Thread.sleep(10000);
			    
			 LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='createPgmObj()']")).click();
			 Thread.sleep(10000);
			 WebElement e11=LaunchApp.driver.findElement(By.xpath(".//*[@id='pgmObj_name' and @type='text']"));
			 Clickevent.ClickEvent(e11); 
			 e11.clear();
			 e11.sendKeys(PO_name);
			Thread.sleep(2000);
		     LaunchApp.driver.findElement(By.xpath(".//*[@id='pgmObj_content']")).sendKeys(PO_Description);
		     Thread.sleep(2000);
		     LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='savePgmObj()']")).click();
		     Thread.sleep(2000);
		     try{
		    	 AlertHandling.waitForAlert();}catch(Exception e){}
		     Thread.sleep(1000);
		     
		     LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-model='submitForApproval']")).click();
		     Thread.sleep(2000);
		     LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-click='submitProgramObj(submitForApproval)']")).click();
		     Thread.sleep(20000);
		     String Text= LaunchApp.driver.findElement(By.xpath(".//*[@id='tblPrograms']")).getText();
		     WebElement element=LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-click='approve()']"));
		     LaunchApp.ClickEvent(element);
		     
		     ExceptionHndeler.getScreen("PO_created"+PO_name.substring(0,PO_name.length()-1 ));
		      if(Text.contains(PO_name.trim()) && Text.contains(PO_Description.trim()))
		     {
		    	 Assert.assertTrue(true, "User able to create PO");
		     }
		     else
		     {
		    	 Assert.fail("Not able to Create PO table=\n"+Text);
		     }
		     Thread.sleep(1000);
		    // LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='saveCourseObj()']")).click();
		     
		     
		     }
			catch(Exception e)
			{
				e.printStackTrace();
				ExceptionHndeler.Log("Alert","Create PO", e);
				 Assert.fail("Not able to Create PO"+ e.getMessage());
				
			}
		}

}
