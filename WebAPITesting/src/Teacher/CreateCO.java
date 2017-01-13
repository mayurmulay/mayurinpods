package Teacher;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import Common.AlertHandling;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Loger;

public class CreateCO {
	
	@Test
	@Parameters({ "CO_name", "CO_Description", "Program", "Course", "Rubric","Blooms" })
	public static void createCO(String CO_name, String CO_Description, String Program, String Course, String Rubric, String Blooms)
	{
		try
		{
			Thread.sleep(3000);
		LaunchApp.driver.findElement(By.xpath("//.//*[@id='side-menu']/li[9]/a")).click();
		    Thread.sleep(3000);
		LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'COs')]")).click();
		     Thread.sleep(3000);
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='program_select']"))).selectByVisibleText(Program.trim());
		     Thread.sleep(15000);
	    new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='course_select']"))).selectByVisibleText(Course.trim());
		     Thread.sleep(3000);
		    Thread.sleep(3000);
		    try
		    {
		    	
		    	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Update') and @class='btn']")).click();
		    	Loger.LogEvent("Update CO","CO  updated"); 
		    	Thread.sleep(2000);
		    	AlertHandling.waitForAlert();
		    	Thread.sleep(5000);
		    	LaunchApp.driver.navigate().refresh();   Thread.sleep(20000);
				new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='program_select']"))).selectByVisibleText(Program.trim());
			     Thread.sleep(15000);
		    new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='course_select']"))).selectByVisibleText(Course.trim());
			     Thread.sleep(3000);
		    	
		    }catch(Exception e){Loger.LogEvent("Update CO","CO not updated"); 
		    Assert.fail("Not able to click on Update CO");
		    }
		    
		    Thread.sleep(10000);
		 LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='createCourseObj()']")).click();
		 Thread.sleep(2000);

		 Thread.sleep(2000);
		 LaunchApp.driver.findElement(By.xpath(".//*[@id='courseObj_name']")).sendKeys(CO_name);
		 Thread.sleep(2000);
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='courseObj_content']")).sendKeys(CO_Description);
	     Thread.sleep(2000);
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='rubric']")).click();
	     Thread.sleep(1000);
	     new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='rubric']"))).selectByVisibleText(Rubric.trim());
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='rubric']")).sendKeys(Rubric.trim());
	     Thread.sleep(2000);
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='bloom']")).click();
	     Thread.sleep(1000);
	     new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='bloom']"))).selectByVisibleText(Blooms.trim());
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='bloom']")).sendKeys(Blooms.trim());
	     Thread.sleep(2000);
	     
	    
	     LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='saveCourseObj()']")).click();
	     Thread.sleep(2000);
	     try{
	    	 AlertHandling.waitForAlert();}catch(Exception e){}
	     Thread.sleep(1000);
	     
	     LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-model='submitForApproval']")).click();
	     Thread.sleep(2000);
	     LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-click='submitCourseObj(submitForApproval)']")).click();
	     Thread.sleep(20000);
	     String Text= LaunchApp.driver.findElement(By.xpath(".//*[@id='tblCourseObjectives']")).getText();
	     WebElement element=LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-click='approve()']"));
	     LaunchApp.ClickEvent(element);
	     
	     ExceptionHndeler.getScreen("CO created");
	      if(Text.contains(CO_name.trim()) && Text.contains(CO_Description.trim()))
	     {
	    	 Assert.assertTrue(true, "User able to create CO");
	     }
	     else
	     {
	    	 Assert.fail("Not able to Create CO table=\n"+Text);
	     }
	     Thread.sleep(1000);
	    // LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='saveCourseObj()']")).click();
	     
	     
	     }
		catch(Exception e)
		{
			e.printStackTrace();
			ExceptionHndeler.Log("Alert","Create lesson", e);
			 Assert.fail("Not able to Create CO"+ e.getMessage());
			
		}
	}
	

}