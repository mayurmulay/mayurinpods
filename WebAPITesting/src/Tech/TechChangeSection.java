package Tech;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.LaunchApp;
import Data.ExceptionHndeler;

public class TechChangeSection {
	
	@Test
	@Parameters({"TestData","Dept","Course"})
	public static void TechChangeSection(String Dept,String Course,String TestData)
	{
		try
		{
		System.out.print("Selecting section "+TestData);
		TestData=TestData.trim();
		try{
		LaunchApp.driver.findElement(By.xpath(".//*[@href='/home/mysections']")).click();
		Thread.sleep(5000);
		try
		{
		LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div[3]/div[2]/div[1]/select")).click();
		LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div[3]/div[2]/div[1]/select")).sendKeys(Dept.trim());
		
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div[3]/div[2]/div[1]/select"))).selectByVisibleText(Dept.trim());
		}catch(Exception e){
			//LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div[3]/div[2]/div[1]/select")).sendKeys(Dept.trim());
			}
		
		
		Thread.sleep(5000);
		
		try
		{
		LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div[3]/div[2]/div[2]/select")).click();
		LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div[3]/div[2]/div[2]/select")).sendKeys(Course.trim());
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div[3]/div[2]/div[2]/select"))).selectByVisibleText(Course.trim());
		}catch(Exception e){
			//}
		                }
		
		//new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div[3]/div[2]/div[1]/select"))).selectByVisibleText(Course.trim());
		Thread.sleep(5000);
		}catch(Exception e)
		{
			e.printStackTrace();
			ExceptionHndeler.Log(TestData,"Section Selection", e);
			
		}

		Thread.sleep(10000);
		Actions actions = new Actions(LaunchApp.driver);
		WebElement mainMenu = LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+TestData.trim()+"')]"));
		actions.moveToElement(mainMenu);
		WebElement element=LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+TestData.trim()+"')]")); 
		try
		{
			Thread.sleep(10000);
			element.click();
		}catch(Exception e){e.printStackTrace();  ClickEvent(element);}
		
		Thread.sleep(10000);
		System.out.print("Selected section "+TestData);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ExceptionHndeler.Log(TestData,"Section Selection", e);
			
		}
		
	}
	@Test
	@Parameters({"SectionId"})
	public static void TechChangeSectionById(String SectionId)
	{
		LaunchApp.driver.get(LaunchApp.URL1+"/home/selectsection?id="+SectionId.trim());
		try {
			Thread.sleep(5000);
			LaunchApp.driver.get(LaunchApp.URL1);
			Thread.sleep(5000);

		} catch (InterruptedException e) {e.printStackTrace();}
		LaunchApp.driver.get(LaunchApp.URL1);
	}
	
	public static void ClickEvent(WebElement element){
		try{
		JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
	    jse.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
	    
		}
		catch(Exception e){e.printStackTrace();}
		
	}
	

}
