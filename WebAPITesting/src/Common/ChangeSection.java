package Common;

import org.openqa.selenium.By;
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
			e.printStackTrace();
			ExceptionHndeler.Log(TestData,"Section Selection", e);
			
		}

		//new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='sectionId']"))).selectByVisibleText(TestData);
		Thread.sleep(1000);
		LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+TestData+"')]")).click();
		//LaunchApp.driver.findElement(By.id("sectionId")).sendKeys(Keys.ENTER);
		//LaunchApp.driver.findElement(By.id("Value")).sendKeys(Keys.ENTER);
		Thread.sleep(100);
		System.out.print("Selected section "+TestData);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ExceptionHndeler.Log(TestData,"Section Selection", e);
			
		}

	}
	
}