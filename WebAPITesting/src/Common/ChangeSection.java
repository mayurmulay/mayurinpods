package Common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Data.ExceptionHndeler;

public class ChangeSection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		selectSection("B2");
	}
	@Test
	@Parameters("TestData")
	public static void selectSection(String TestData)
	{
		try
		{
		System.out.print("Selecting section "+TestData);
		TestData=TestData.trim();
		try{
		LaunchApp.driver.findElement(By.xpath(".//*[@href='/home/mysections']")).click();
		}catch(Exception e)
		{
			//e.printStackTrace();
			//ExceptionHndeler.Log(TestData,"Section Selection", e);
			
		}

		//new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='sectionId']"))).selectByVisibleText(TestData);
		Thread.sleep(2000);
		//WebElement element=LaunchApp.driver.findElement(By.xpath(".//*[.='"+TestData+"']")); 
		
		List <WebElement> element=LaunchApp.driver.findElements(By.xpath(".//*[@ng-click='sectionSelected(section.section.id)']")); ////*[contains(text(),'ABC')]
		
		for(WebElement  li: element){
			try{
			if(li.getText().trim().equals(TestData.trim()))
			{
				ClickEvent(li);
				break;
			}}
			catch(Exception e){continue;}
			
		}
	    //LaunchApp.driver.findElement(By.id("sectionId")).sendKeys(Keys.ENTER);
		//LaunchApp.driver.findElement(By.id("Value")).sendKeys(Keys.ENTER);
		Thread.sleep(100);
		System.out.print("Selected section "+TestData);
		Uservarification();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ExceptionHndeler.Log(TestData,"Section Selection", e);
			
		}
		

	}
	public static void ClickEvent(WebElement element){
		JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
	    jse.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
		
	}
	
	public static void Uservarification()
	{
		try
		{
			Thread.sleep(3000);
			//LaunchApp.driver.findElement(By.xpath(".//*[@name='email']")).sendKeys("mayur.mulay@inpods.com");
			//Thread.sleep(5000);
			//LaunchApp.driver.findElement(By.xpath(".//*[@name='confirmEmail']")).sendKeys("mayur.mulay@inpods.com");
		//	Thread.sleep(5000);
			WebElement e=LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='cancel()']"));
			ClickEvent(e);
			//LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='sendEmailVerification(email)']")).click();
	    }catch(Exception e){}
	}
}