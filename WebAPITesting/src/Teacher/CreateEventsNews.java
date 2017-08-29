package Teacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.GetDate;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Loger;
import Data.Read_Data;


public class CreateEventsNews {
	
	@Test
	@Parameters({ "type", "title", "message", "pubDate", "Enddate","Important","Ack","AssertString","URL"})
	public static void CreateEventsNewsExe(String type,String title,String message,int pubDate,int Enddate,String Important,String Ack,String AssertString,String URL)
	{
		LaunchApp.driver.get(LaunchApp.URL1);
		if(type.equals("Announcement"))
		{
		try
		{
		System.out.println("Creating Announcement ");
		LaunchApp.driver.findElement(By.linkText("Events and News")).click();
		Thread.sleep(100);
		LaunchApp.driver.findElement(By.linkText("New Announcement")).click();
		Thread.sleep(5000);
		System.out.println("Announcement "+title.trim());
	
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
		if(title.trim().equals("Back Date Event")||title.trim().equals("PublishDateLessThanTadysDate")) 
		{
			System.out.println("In back date event");
			Thread.sleep(1000);
			li=LaunchApp.driver.findElements(By.xpath(".//*[.='Save']"));
			try
			{
			li.get(0).click();}catch (Exception e2){ try {li.get(1).click();}catch (Exception e1){ {li.get(1).click();}}}
			Thread.sleep(10000);
			try
			{
				Thread.sleep(10000);
			String bodyText = LaunchApp.driver.findElement(By.xpath(".//*[@id='uniqueName-DialogMarkUpAppended']")).getText();
			System.out.println("boddy Tesxt="+bodyText); 
			Assert.assertTrue(bodyText.contains(AssertString.trim()));
			}catch(Exception e){e.printStackTrace();}
			Thread.sleep(5000);
			MooveToElement.moveToElenment(".//*[@id='uniqueName-DialogMarkUpAppended']");
			 ExceptionHndeler.getScreen("Event"+title);
			li=LaunchApp.driver.findElements(By.xpath(".//*[.='Cancel']"));
			li.get(0).click();
			System.out.println("bodyText");
			
		}
		else
		{
			System.out.println("In back date Else event");
		Thread.sleep(100);
		ExceptionHndeler.getScreen("Event"+title);
		li=LaunchApp.driver.findElements(By.xpath(".//*[.='Save']"));
		li.get(0).click();
		Thread.sleep(10000);
		LaunchApp.driver.findElement(By.linkText("Events and News")).click();
		Thread.sleep(9000);
		String bodyText = LaunchApp.driver.findElement(By.tagName("body")).getText();
		if(!AssertString.trim().equals("NO"))
		Assert.assertTrue(bodyText.contains(AssertString.trim()));
		}
		}
		catch(Exception e)
		{
			ExceptionHndeler.Log("Error","Create lesson", e);
			e.printStackTrace();
		}
		}
		if(type.equals("Event"))
		{
			try
			{
			System.out.println("Creating Announcement ");
			LaunchApp.driver.findElement(By.linkText("Events and News")).click();
			Thread.sleep(100);
			LaunchApp.driver.findElement(By.linkText("New Event")).click();
			Thread.sleep(5000);
			System.out.println("Announcement "+title.trim());
		
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
			
			li=LaunchApp.driver.findElements(By.xpath(".//*[@id='EventDate']"));
			Thread.sleep(100);
			li.get(0).click();
			
			String str=GetDate.GetNextdate(pubDate);
			
			try
			{
				AssignmentCreation.setStartdate(str,"Start");
				Thread.sleep(100);
				SetTime(3,30);
			}
			catch(Exception e)
			{
				ExceptionHndeler.Log("Alert","Create lesson", e);
				e.printStackTrace();
			}
			//li.get(0).sendKeys(GetDate.GetNextdate(pubDate));
			Thread.sleep(100);
			System.out.println(pubDate);
			System.out.println("Cra]eating lesson2");
			System.out.println("In back date Else event");
			Thread.sleep(100);
			MooveToElement.moveToElenment(".//*[.='Save']");
			
			ExceptionHndeler.getScreen("Event"+title);
			li=LaunchApp.driver.findElements(By.xpath(".//*[.='Save']"));
			li.get(0).click();
			Thread.sleep(10000);
			LaunchApp.driver.findElement(By.linkText("Events and News")).click();
			Thread.sleep(9000);
			String bodyText = LaunchApp.driver.findElement(By.tagName("body")).getText();
			if(!AssertString.trim().equals("NO"))
			Assert.assertTrue(bodyText.contains(AssertString.trim()));
			
			}
			catch(Exception e)
			{
				ExceptionHndeler.Log("Alert","Create lesson", e);
				e.printStackTrace();
			}
		}
		
		
	}
	public static void SetTime(int hr,int Min)
	{
		
		List <WebElement> timebtn=LaunchApp.driver.findElements(By.xpath("//*[(contains(@style,'display: block;'))]//*[@class='picker-switch accordion-toggle']"));
		try{ClickEvent(timebtn.get(0));System.out.println("timebtn=0");}catch(Exception e){try{ClickEvent(timebtn.get(1));System.out.println("timebtn=1");}catch(Exception e1){try{ClickEvent(timebtn.get(2));System.out.println("timebtn=1");}catch(Exception e2){ClickEvent(timebtn.get(3));}}}
		
		// Code to select Hour 
		//List <WebElement> Hrbtn=LaunchApp.driver.findElements(By.xpath("//*[(contains(@style,'display: block;'))]//*[@data-action='showHours' and .='00']"));
		//try{Hrbtn.get(0).click();System.out.println("Hrbtn=0");}catch(Exception e){try{Hrbtn.get(1).click();System.out.println("Hrbtn=0");}catch(Exception e2){try{Hrbtn.get(2).click();System.out.println("Hrbtn=0");}catch(Exception e3){}}}
		List <WebElement> Hrbtn1 = null;
		try{Hrbtn1=LaunchApp.driver.findElements(By.xpath("//*[(contains(@style,'display: block;'))]//*[@class='timepicker-hour']"));}catch(Exception e3){}
		if(Hrbtn1.size()>0)
		{try{ClickEvent(Hrbtn1.get(0));}catch(Exception e){try{ClickEvent(Hrbtn1.get(1));}catch(Exception e2){try{ClickEvent(Hrbtn1.get(2));}catch(Exception e3){ClickEvent(Hrbtn1.get(3));}}}}
		else
		       { System.out.println("In 23");
		       try{  Hrbtn1=LaunchApp.driver.findElements(By.xpath("//*[(contains(@style,'display: block;'))]//*[@class='timepicker-hour'"));
		       ClickEvent(Hrbtn1.get(0));System.out.println("In 23_1");}catch(Exception e1){try{ClickEvent(Hrbtn1.get(1));System.out.println("In 23_2");}catch(Exception e2){try{ClickEvent(Hrbtn1.get(2));System.out.println("In 23_3");}catch(Exception e3){ClickEvent(Hrbtn1.get(3));}}}
		       }
		String hr1;
		if(hr<10)
		{
			 hr1='0'+Integer.toString(hr);
		}
		else
		{
			 hr1=Integer.toString(hr);
		}
		 List <WebElement> Hr=LaunchApp.driver.findElements(By.xpath("//*[(contains(@style,'display: block;'))]//*[@class='hour' and .='"+hr1+"']"));
		try{ClickEvent(Hr.get(0));} catch(Exception e){try{ClickEvent(Hr.get(1));} catch(Exception e1){try{ClickEvent(Hr.get(2));}catch(Exception e2){ClickEvent(Hr.get(3));}}}
	
	    //Code to select min 
		//List <WebElement> Minbtn=LaunchApp.driver.findElements(By.xpath("//*[(contains(@style,'display: block;'))]//*[@data-action='showMinutes' and .='00']"));
		//try{Minbtn.get(0).click();System.out.println("Minbtn=0");}catch(Exception e){try{Minbtn.get(1).click();System.out.println("Minbtn=1");}catch(Exception e2){try{Minbtn.get(2).click();System.out.println("Minbtn=3");}catch(Exception e3){}}}
		{List <WebElement> Minbtn1=null;
		try{Minbtn1=LaunchApp.driver.findElements(By.xpath("//*[(contains(@style,'display: block;'))]//*[@class='timepicker-minute']"));}catch(Exception e){}
		if(Minbtn1.size()>0){try{ClickEvent(Minbtn1.get(0));}catch(Exception e){try{ClickEvent(Minbtn1.get(1));}catch(Exception e2){try{ClickEvent(Minbtn1.get(2));}catch(Exception e3){ClickEvent(Minbtn1.get(3));}}}}
		else
		       {  Minbtn1=LaunchApp.driver.findElements(By.xpath("//*[(contains(@style,'display: block;'))]//*[@class='timepicker-minute']"));
		         try{ClickEvent(Minbtn1.get(0));}catch(Exception e){try{ClickEvent(Minbtn1.get(1));}catch(Exception e2){try{ClickEvent(Minbtn1.get(2));}catch(Exception e3){ClickEvent(Minbtn1.get(3));}}}
		       }
		String Min1;
		if(Min<10)
		{
			 Min1='0'+Integer.toString(Min);
		}
		else
		{
			 Min1=Integer.toString(Min); 
		}
		 List <WebElement> Min11=LaunchApp.driver.findElements(By.xpath("//*[(contains(@style,'display: block;'))]//*[@class='minute' and .='"+Min1+"']"));
		 try{ClickEvent(Min11.get(0));} catch(Exception e){try{ClickEvent(Min11.get(1));} catch(Exception e1){try{ClickEvent(Min11.get(2));}catch(Exception e2){ClickEvent(Min11.get(3));}
		}
		}
		}
	}
	public static void ClickEvent(WebElement element){
		JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
	    jse.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
		
	}

}
