package Teacher;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Loger;

public class ValidateSharedLesson {
	@Test
	@Parameters({"lessonList","TargetLesson"})
	public static void ValidateAndCopySahredLesson(String lessonList,String TargetLesson)
	{
			String[] args=new String[10]; 
		    args[0]=(String) "Lessons";
			Gototab.main(args);
			try {
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Shared Lessons')]")).click();
			} catch (InterruptedException e) {  e.printStackTrace();
			if(AlertHandling.isAlertPresent())
				{
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Shared Lessons')]")).click();
				}
			}
			
			String[] LessonName=lessonList.split(":");
			MooveToElement.moveToElenment();
			ExceptionHndeler.getScreen("Sheard Lesson List");
		for(int i=0;i<LessonName.length;i++)
		    {
		    	try {
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[.='"+LessonName[i].trim()+"']//..//*[@name='copyBoxLesson']")).click();
				Loger.LogEvent("Selected Lesson to coppy",LessonName[i].trim());
		    	} catch (InterruptedException e) {  e.printStackTrace();
			      if(AlertHandling.isAlertPresent())
		     		{
			     	LaunchApp.driver.findElement(By.xpath(".//*[.='"+LessonName[i].trim()+"']//..//*[@name='copyBoxLesson']")).click();
			    	}}
		    	
		    	try {
					Thread.sleep(1000);
					new Select(LaunchApp.driver.findElement(By.xpath(".//*[.='"+LessonName[i].trim()+"']//..//*[@name='moduleList']"))).selectByVisibleText(TargetLesson);
					Loger.LogEvent("Selected Lesson to coppy",LessonName[i].trim());
			    	} catch (InterruptedException e) {  e.printStackTrace();
				      if(AlertHandling.isAlertPresent())
			     		{
				    new Select(LaunchApp.driver.findElement(By.xpath(".//*[.='"+LessonName[i].trim()+"']//..//*[@name='moduleList']"))).selectByVisibleText(TargetLesson);
					}}
		    	
		    	try {
					Thread.sleep(1000);
					LaunchApp.driver.findElement(By.xpath(".//*[@name='copyLessons']")).click();
					Loger.LogEvent("Selected Lesson to coppy",LessonName[i].trim());
					Thread.sleep(20000);
			    	} catch (InterruptedException e) {  e.printStackTrace();
				      if(AlertHandling.isAlertPresent())
			     		{
				     	LaunchApp.driver.findElement(By.xpath(".//*[@name='copyLessons']")).click();
				    	}}
		    	try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	 Alert alert = LaunchApp.driver.switchTo().alert(); String text=alert.getText();
				  if(text.equals("Lessons Copied"))
			    	{alert.accept();}
			    	else
			    	{   System.out.println("Error in Lessons Coppy"+ text);
			    		Loger.LogEvent(text,"Error");
			    		Assert.fail("not able to Copyy Lesson"+LessonName[i].trim());
			    	}
		     } 
	    args[0]=(String) "Lessons";
		  Gototab.main(args);
		       try {
			    Thread.sleep(1000);
			    LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+TargetLesson+"')]")).click();
		        } catch (InterruptedException e) {  e.printStackTrace();
		        if(AlertHandling.isAlertPresent())
			    {
			      LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+TargetLesson+"')]")).click();
			    }
		        }
		       for(int i=0;i<LessonName.length;i++)
			    {
			    	try {
					Thread.sleep(1000);
					LaunchApp.driver.findElement(By.xpath(".//*[.='"+LessonName[i].trim()+"']")).click();
					Loger.LogEvent("Lesson open Lesson name :",LessonName[i].trim());
					Thread.sleep(10000);
					MooveToElement.moveToElenment(".//*[.='Return To Lesson List']");
					ExceptionHndeler.getScreen(LessonName[i].trim());
					LaunchApp.driver.findElement(By.xpath(".//*[.='Return To Lesson List']")).click();
					
			    	} catch (InterruptedException e) {  e.printStackTrace();
				      if(AlertHandling.isAlertPresent())
			     		{
				    	  LaunchApp.driver.findElement(By.xpath(".//*[.='"+LessonName[i].trim()+"']")).click();
							Loger.LogEvent("Lesson open Lesson name :",LessonName[i].trim());
							MooveToElement.moveToElenment();
							ExceptionHndeler.getScreen(LessonName[i].trim());
							LaunchApp.driver.findElement(By.xpath(".//*[.='Return To Lesson List']")).click();
							}}
			    }
		       
		       
		
	 }
	

}