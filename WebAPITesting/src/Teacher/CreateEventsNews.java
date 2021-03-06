package Teacher;

import java.awt.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.*;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.GetDate;
import Common.LaunchApp;
import Data.ExceptionHndeler;

public class CreateEventsNews {
	
	@Test
	@Parameters({ "type", "title", "message", "pubDate", "Enddate","Important","Ack","AssertString","URL"})
	public static void CreateEventsNewsExe(String type,String title,String message,int pubDate,int Enddate,String Important,String Ack,String AssertString,String URL)
	{
		LaunchApp.driver.get(URL);
		if(type.equals("Announcement"))
		{
		try
		{
			System.out.println("Cra]eating lesson1 ");
		LaunchApp.driver.findElement(By.linkText("Events and News")).click();
		Thread.sleep(100);
		LaunchApp.driver.findElement(By.linkText("New Announcement")).click();
		Thread.sleep(5000);
		System.out.println("Cra]eating lesson2"+title.trim());
	
		java.util.List<WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[@id='Title']"));
		Thread.sleep(100);
		
		System.out.println("mayur"+title.trim());
		li.get(0).sendKeys(title.trim());
		Thread.sleep(100);
		System.out.println(title.trim());
		Thread.sleep(50000);
		li.get(0).sendKeys(Keys.TAB);
		Actions actions = new Actions(LaunchApp.driver);
		actions.sendKeys(message.trim());
		actions.build().perform();
		Thread.sleep(1000);
		System.out.println(message.trim());
		
		li=LaunchApp.driver.findElements(By.xpath(".//*[@id='PublishDate']"));
		Thread.sleep(100);
		li.get(0).clear();
		li.get(0).sendKeys(GetDate.GetNextdate(pubDate));
		Thread.sleep(100);
		System.out.println(pubDate);
		
		li=LaunchApp.driver.findElements(By.xpath(".//*[@id='ExpiryDate']"));
		Thread.sleep(100);
		li.get(0).clear();
		li.get(0).sendKeys(GetDate.GetNextdate(Enddate));
		Thread.sleep(100);
		System.out.println(Enddate);
		
	
		System.out.println("Cra]eating lesson2");
		if(Important.equals("yes"))
		{
			li=LaunchApp.driver.findElements(By.xpath(".//*[@id='Importance']"));
			li.get(0).click();
			
		}
		Thread.sleep(100);
		if(Ack.equals("yes"))
		{
			li=LaunchApp.driver.findElements(By.xpath(".//*[@id='RequiresAcknowledgement']"));
			li.get(0).click();
			
		}
		if(title.trim().equals("Back Date Event"))
		{
			System.out.println("In back date event");
			li=LaunchApp.driver.findElements(By.xpath(".//*[.='Save']"));
			li.get(0).click();
			Thread.sleep(2000);
			try
			{
			String bodyText = LaunchApp.driver.findElement(By.xpath(".//*[@id='uniqueName-DialogMarkUpAppended']")).getText();
			System.out.println(bodyText);
			Assert.assertTrue(bodyText.contains(AssertString.trim()));
			}catch(Exception e){e.printStackTrace();}
			Thread.sleep(100);
			li=LaunchApp.driver.findElements(By.xpath(".//*[.='Cancel']"));
			li.get(0).click();
			System.out.println("bodyText");
			
		}
		else
		{
			System.out.println("In back date Else event");
		Thread.sleep(100);
		li=LaunchApp.driver.findElements(By.xpath(".//*[.='Save']"));
		li.get(0).click();
		Thread.sleep(10000);
		LaunchApp.driver.findElement(By.linkText("Events and News")).click();
		Thread.sleep(9000);
		String bodyText = LaunchApp.driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue(bodyText.contains(AssertString.trim()));
		}
		}
		catch(Exception e)
		{
			ExceptionHndeler.Log("Alert","Create lesson", e);
			e.printStackTrace();
		}
		}
		
		
	}

}
