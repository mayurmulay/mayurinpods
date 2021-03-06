package Teacher;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Gototab;
import Common.LaunchApp;

public class CreateRubric {
	
	@Test
	@Parameters("name")
	public static void CreateRubric(String name)
	{
		try
		{
	String args1[]={"Manage Course"," "};
	Gototab.main(args1);
	JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
	jse.executeScript("window.scrollTo(50, 50)", "");
	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Manage Rubrics')]")).click();
	LaunchApp.driver.findElement(By.xpath(".//*[@name='createRubric']")).click();
	Alert alert = LaunchApp.driver.switchTo().alert();
	 alert.accept();
	// int id= GetID();
	
    // LaunchApp.driver.findElement(By.xpath(".//*[@id='"+id+"'][@name='rubricEdit']")).click();
	 
	 try {Thread.sleep(900);} catch (InterruptedException e) {e.printStackTrace();}
	 
	 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblRubricTitle']")).click();
	 try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
	 LaunchApp.driver.findElement(By.xpath(".//*[@name='value']")).clear();
	 LaunchApp.driver.findElement(By.xpath(".//*[@name='value']")).sendKeys(name);
	 
	 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblRubricDescription']")).click();
	 try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
	 LaunchApp.driver.findElement(By.xpath(".//*[@name='value']")).clear();
	 LaunchApp.driver.findElement(By.xpath(".//*[@name='value']")).sendKeys(name);
	 
	 try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
	 
	 LaunchApp.driver.findElement(By.xpath(".//*[@name='enable']")).click();   
	 
	 try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
	 alert = LaunchApp.driver.switchTo().alert();
	 alert.accept();
	 try {alert = LaunchApp.driver.switchTo().alert();
	 alert.accept();} catch (Exception e) {e.printStackTrace();}
	 
		
	try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
     LaunchApp.driver.findElement(By.xpath(".//*[@name='back']")).click();   //back
     
     String Data=LaunchApp.driver.findElement(By.xpath(".//*[@id='NewRubricTable']")).getText();
    
     if(Data.contains(name.trim()))
     {
  	   Assert.assertEquals(1, 1);
     }
     else
     {
  	   Assert.fail("Unable TO create Rubric ");
     }
  	   
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	
	}
	@SuppressWarnings("null")
	public static int GetID()
			{
				int id[] = null;
				List <WebElement> li=LaunchApp.driver.findElements(By.name("rubricEdit"));
				  for(int i=0;i<li.size();i++)
				  {
					  id[i]=Integer.parseInt(li.get(i).getAttribute("id"));//getting all Rubric  ID 
				  }
				  Arrays.sort(id);
				 
				  int m=(id.length)-1;//picking most recent Id
				  
					 try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							//e.printStackTrace();
							}
					 return id[m];
			}
	

}
