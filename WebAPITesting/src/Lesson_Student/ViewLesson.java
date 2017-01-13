package Lesson_Student;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import Common.AlertHandling;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;

public class ViewLesson {
	
	
	@Test
	  public void testOpenLesson()  {
		
		String[] args=new String[10]; 
	    args[0]=(String) "Lessons";
		Gototab.main(args);
	    try {
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Lessons')]")).click();
		} catch (InterruptedException e) {
			e.printStackTrace();
			if(AlertHandling.isAlertPresent())
			{
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Lessons')]")).click();
			}
		}
	    
	    try {
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'ChapterDemo')]")).click();
		} catch (InterruptedException e) {
			e.printStackTrace();
			if(AlertHandling.isAlertPresent())
			{
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'ChapterDemo')]")).click();
			}
		}
	    
	    try {
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'lessonDemo')]")).click();
		} catch (InterruptedException e) {
			e.printStackTrace();
			if(AlertHandling.isAlertPresent())
			{
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'lessonDemo')]")).click();
			}
		}
	    
	    try {
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Next')]")).click();
			String nameOflesson=LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div[2]/table/tbody/tr[1]/td/h2")).getText();
			 MooveToElement.moveToElenment(".//*[@id='page-wrapper']");
			ExceptionHndeler.getScreen(nameOflesson);
		} catch (InterruptedException e) {
			e.printStackTrace();
			if(AlertHandling.isAlertPresent())
			{
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Next')]")).click();
			}
		}
	    
	    try {
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Next')]")).click();
			String nameOflesson=LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div[2]/table/tbody/tr[1]/td/h2")).getText();
			MooveToElement.moveToElenment(".//*[@id='page-wrapper']");
			ExceptionHndeler.getScreen(nameOflesson);
		} catch (InterruptedException e) {
			e.printStackTrace();
			if(AlertHandling.isAlertPresent())
			{
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Next')]")).click();
			}
		}
	    
	    try {
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Next')]")).click();
			String nameOflesson=LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div[2]/table/tbody/tr[1]/td/h2")).getText();
			MooveToElement.moveToElenment(".//*[@id='page-wrapper']");
			ExceptionHndeler.getScreen(nameOflesson);
		} catch (InterruptedException e) {
			e.printStackTrace();
			if(AlertHandling.isAlertPresent())
			{
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Next')]")).click();
			}
		}
	    
	    try {
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Next')]")).click();
			String nameOflesson=LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div[2]/table/tbody/tr[1]/td/h2")).getText();
			MooveToElement.moveToElenment(".//*[@id='page-wrapper']");
			ExceptionHndeler.getScreen(nameOflesson);
		} catch (InterruptedException e) {
			e.printStackTrace();
			if(AlertHandling.isAlertPresent())
			{
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Next')]")).click();
			}
		}
	    
	    try {
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Return To Lesson List')]")).click();
			String nameOflesson=LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div[2]/table/tbody/tr[1]/td/h2")).getText();
			MooveToElement.moveToElenment(".//*[@id='page-wrapper']");
			ExceptionHndeler.getScreen(nameOflesson);
		} catch (InterruptedException e) {
			e.printStackTrace();
			if(AlertHandling.isAlertPresent())
			{
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Return To Lesson List')]")).click();
			}
		}
	    
	  }
	

}