package Tech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Clickevent;
import Common.LaunchApp;
import Data.ExceptionHndeler;
import Data.Loger;

public class CreateBlooms {
	
	 @Test
		@Parameters({ "Blooms_name", "Blooms_Description"})
		public static void createCO(String Blooms_name, String Blooms_Description)
		{
			try
			{
				Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath("//.//*[@id='side-menu']/li[7]/a")).click();  //OBE link for Tech
			    Thread.sleep(3000);
		WebElement e1=	LaunchApp.driver.findElement(By.xpath(".//*[@href='/#/obe/BloomsCategories']"));
		LaunchApp.ClickEvent(e1);
			     Thread.sleep(3000);
			
		
			    Thread.sleep(3000);
			    try
			    {
			    	Thread.sleep(3000);
			    	if(LaunchApp.driver.findElements(By.xpath(".//*[contains(text(),'Update') and @class='btn']")).size() != 0)
			    	{	
			    	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Update') and @class='btn']")).click();
			    	Loger.LogEvent("Update Blooms","Blooms  updated"); 
			    	Thread.sleep(2000);
			    	AlertHandling.waitForAlert();
			    	Thread.sleep(5000);
			    	LaunchApp.driver.navigate().refresh();   Thread.sleep(20000);
					
			    	}
			    	else
			    	{
			    		if(LaunchApp.driver.findElements(By.xpath(".//*[contains(text(),'pprove') and @class='btn']")).size() != 0)
				    	{	
				    	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'pprove') and @class='btn']")).click();
				    	Thread.sleep(5000);
				    	LaunchApp.driver.navigate().refresh();
				    	Thread.sleep(5000);
				    	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Update') and @class='btn']")).click();
				    	Loger.LogEvent("Update Blooms","Blooms  updated"); 
				    	Thread.sleep(2000);
				    	AlertHandling.waitForAlert();
				    	Thread.sleep(5000);
				    	LaunchApp.driver.navigate().refresh();   Thread.sleep(20000);
				       }
			    	}
			    }catch(Exception e){Loger.LogEvent("Update Blooms","Blooms not updated"); 
			    Assert.fail("Not able to click on Update Blooms");
			    }
			    
			    Thread.sleep(10000);
			    
			 LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='createObj()']")).click();
			 Thread.sleep(10000);
			 WebElement e11=LaunchApp.driver.findElement(By.xpath(".//*[@id='gaObj_name' and @type='text']"));
			 Clickevent.ClickEvent(e11); 
			 e11.clear();
			 e11.sendKeys(Blooms_name);
			Thread.sleep(2000);
		     LaunchApp.driver.findElement(By.xpath(".//*[@id='gaObj_content']")).sendKeys(Blooms_Description);
		     Thread.sleep(2000);
		     LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='saveObj()']")).click();
		     Thread.sleep(2000);
		   
		     LaunchApp.driver.navigate().refresh();  
		     Thread.sleep(5000);
		     
		     LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-model='submitForApproval']")).click();
		     Thread.sleep(2000);
		     LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-click='submitBlooms(submitForApproval)']")).click();
		     Thread.sleep(20000);
		     String Text= LaunchApp.driver.findElement(By.xpath(".//*[@id='tblObjectives']")).getText();
		     WebElement element=LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-click='approve()']"));
		     LaunchApp.ClickEvent(element);
		     
		     ExceptionHndeler.getScreen("Blooms_created"+Blooms_name.substring(0,Blooms_name.length()-1 ));
		      if(Text.contains(Blooms_name.trim()) && Text.contains(Blooms_Description.trim()))
		     {
		    	 Assert.assertTrue(true, "User able to create Blooms");
		     }
		     else
		     {
		    	 Assert.fail("Not able to Create Blooms table=\n"+Text);
		     }
		     Thread.sleep(1000);
		    // LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='saveCourseObj()']")).click();
		     
		     
		     }
			catch(Exception e)
			{
				e.printStackTrace();
				ExceptionHndeler.Log("Alert","Create CO", e);
				 Assert.fail("Not able to Create Blooms"+ e.getMessage());
				
			}
		}


}
