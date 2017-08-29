package Teacher;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.ChangeSection;
import Common.Clickevent;
import Common.Gototab;
import Common.LaunchApp;
import Data.ExceptionHndeler;

public class CreateNewCategories {
	
	static String Sectionname1;
	@Test
	@Parameters({ "Sectionname", "CatgoriName", "Weight", "Description", "Computation_Method","IsContinuousAssessments" })
	public static void CreateCategories (String Sectionname, String CatgoriName, String Weight, String Description,String Computation_Method,String IsContinuousAssessments)
	{
		try
		{
			Sectionname1=Sectionname;
		ChangeSection.selectSection(Sectionname.trim());
		Thread.sleep(1000);
		GOtoGradebook();
		Thread.sleep(5000);
		CreateNewCategori(  CatgoriName,  Weight,   Description,  Computation_Method,  IsContinuousAssessments);
		}
		catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
	}
	public static void GOtoGradebook()
	{
		String args1[]={"Reports"," "};
		Gototab.main(args1);
		JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
		jse.executeScript("window.scrollTo(50, 50)", "");
		LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Reports')]")).click();
		try{Thread.sleep(15000);
		Clickevent.clickLinkByHref("/Report/Gradebook?sectionId",".//*[.='"+Sectionname1.trim()+"']");} catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}

	}
	public static void CreateNewCategori(String CatgoriName, String Weight, String Description,String Computation_Method,String IsContinuousAssessments)
	{
		try
		{
		 LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-id-1']")).click();
		 try{Thread.sleep(1000);} catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
		 LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-id-1']")).click();       //Click on categori
		 
		 try{Thread.sleep(1000);} catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
		 LaunchApp.driver.findElement(By.xpath(".//*[@id='NewGradebookCategory']")).click();      //Click on new categori button 
		 
		 
		 try{Thread.sleep(1000);} catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
		 LaunchApp.driver.findElement(By.xpath(".//*[@id='name']")).sendKeys(CatgoriName.trim());
		 
		 
		 try{Thread.sleep(1000);} catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
		 LaunchApp.driver.findElement(By.xpath(".//*[@id='weightage']")).sendKeys(Weight.trim());
		 
		 try{Thread.sleep(1000);} catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
		 LaunchApp.driver.findElement(By.xpath(".//*[@id='description']")).sendKeys(Description.trim());
		 
		 
		 if(Computation_Method.equals("Assignment Level"))
		 {
			 LaunchApp.driver.findElement(By.xpath(".//*[@id='categoryType']")).click();
			 new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='categoryType']"))).selectByValue("Assignment Level (Method 1)");
		 }
		 if(Computation_Method.equals("Category Level"))
		 {
			 LaunchApp.driver.findElement(By.xpath(".//*[@id='categoryType']")).click();
			 new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='categoryType']"))).selectByValue("Category Level (Method 2)");
		 }
		 try{Thread.sleep(1000);} catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
			
		 if(IsContinuousAssessments.equals("No"))
		 {
			 LaunchApp.driver.findElement(By.xpath(".//*[@id='condsiderForCA']")).click();
		 }
		 try{Thread.sleep(1000);} catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
			
		 LaunchApp.driver.findElement(By.xpath(".//*[@name='btnCreate']")).click();
		 Thread.sleep(1000);
		 
		 AlertHandling.waitForAlert();
		 Thread.sleep(1000);
		 String Data=LaunchApp.driver.findElement(By.xpath(".//*[@id='tblGradebookCategory']")).getText();
		
		     if(Data.contains(CatgoriName) && Data.contains(Weight) && Data.contains(Description) && Data.contains(Computation_Method))
		     {
		    	 Assert.assertTrue(true, "User able to create category"+CatgoriName);
		     }
		     else
		     {
		    	 Assert.fail("Not able to Create CO"+Data);
		     }
		    
		 
		}
		catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
	}

	

}
