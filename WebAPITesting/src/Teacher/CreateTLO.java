package Teacher;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.LaunchApp;
import Data.ExceptionHndeler;

public class CreateTLO {
	
	@Test
	@Parameters({"Program", "Course","COName", "TLOname","CODescr", "Rubric","Blooms" })
	
	public static void CreateTLO(String Program, String Course,String COName,String TLOname,String CODescr,String Rubric,String Blooms)
	{
		try
		{
			 CreateCO.PutCOsinDraftMode(Program,Course);
			  Thread.sleep(10000);
	     LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+COName+"')]/following-sibling::*[2]/*[1]/div[3]/span/a")).click();
	     Thread.sleep(5000);
	      LaunchApp.driver.findElement(By.xpath(".//*[@id='obj_name' and @type='text']")).sendKeys(COName+"_"+TLOname);
	     Thread.sleep(500);
	      LaunchApp.driver.findElement(By.xpath(".//*[@id='obj_content']")).sendKeys(CODescr);
	     Thread.sleep(500);
	     if(!Rubric.contains("none"))
	     {
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='rubric' and @data-ng-model='selObj.selectedRubric.rubricId']")).click();
	     Thread.sleep(1000);
	     new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='rubric' and @data-ng-model='selObj.selectedRubric.rubricId']"))).selectByVisibleText(Rubric.trim());
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='rubric' and @data-ng-model='selObj.selectedRubric.rubricId']")).sendKeys(Rubric.trim());
	     Thread.sleep(2000);
	     }
	     if(!Blooms.contains("none"))
	     { 
	      LaunchApp.driver.findElement(By.xpath(".//*[@name='bloom' and @data-ng-model='selObj.selectedBloom.bloomId']")).click();
	     Thread.sleep(1000);
	     new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='bloom' and @data-ng-model='selObj.selectedBloom.bloomId']"))).selectByVisibleText(Blooms.trim());
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='bloom' and @data-ng-model='selObj.selectedBloom.bloomId']")).sendKeys(Blooms.trim());
	     Thread.sleep(2000);
	     }
	     
	     
	     LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='saveObj(selObj.selectedRubric.rubricId, selObj.selectedBloom.bloomId)']")).click();
	     Thread.sleep(2000);
		}
		catch(Exception e){System.out.print("Error   ="+COName);
		e.printStackTrace(); 
		}
		
		 try{
	    	 AlertHandling.waitForAlert();
	     Thread.sleep(3000);
	     LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-model='submitForApproval']")).click();
	     Thread.sleep(2000);
	     LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-click='submitCourseObj(submitForApproval)']")).click();
	     Thread.sleep(20000);
	     WebElement element=LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-click='approve()']"));
	     LaunchApp.ClickEvent(element);
	     Thread.sleep(10000);
	     WebElement element1= LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+COName.trim()+"')]/following-sibling::*[2]/*[1]//span/a[2]"));
	     LaunchApp.ClickEvent(element1);
	     Thread.sleep(10000);
	     System.out.println(".//*[contains(text(),'"+COName.trim()+"')]/following-sibling::*[2]/*[1]//span/a[2]");
	     ExceptionHndeler.getScreen("TLO_created"+TLOname.substring(0,TLOname.length()-1 ));
         String Text= LaunchApp.driver.findElement(By.xpath(".//*[@id='tblCourseObjectives']")).getText();
	    
         System.out.println("Table String"+Text);
	      if(Text.contains(TLOname.trim()))
	     {
	    	 Assert.assertTrue(true, "User able to Aprove TLO ");
	     }
	     else
	     {
	    	 Assert.fail("User able to Aprove TLO =\n"+Text);
	     }
	     Thread.sleep(1000);
	      }
		catch(Exception e)
		{
			e.printStackTrace();
			ExceptionHndeler.Log("Alert","Create CO", e);
			 Assert.fail("User not able to Aprove TLO "+ e.getMessage());
			
		}
	}
}
