package Teacher;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;

public class ValidateSurveyReport {

	public static void main(String[] args) {
		
	}

	@Test
	@Parameters({ "name"})
	public static void SurveyReport(String name)
	{
		
		String[] args=new String[10]; 
	    args[0]=(String) "Reports";
		Gototab.main(args);
		
		 try {
			 Thread.sleep(10000);
				LaunchApp.driver.findElement(By.xpath(".//*[@class='homepage_section_heading' and contains(text(),'Survey Report')]/../a")).click();
				Thread.sleep(10000);	
		 } catch (InterruptedException e) {
				e.printStackTrace();
				if(AlertHandling.isAlertPresent())
				{
					LaunchApp.driver.findElement(By.xpath(".//*[@class='homepage_section_heading' and contains(text(),'Survey Report')]/../a")).click();
				}
			}
		 MooveToElement.moveToElenment(".//*[contains(text(),'"+name+"')]");
		 ExceptionHndeler.getScreen("SurveyReport"+name);
		 
		 try {
			 Thread.sleep(10000);
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+name+"')]")).click();
				Thread.sleep(5000);
				ExceptionHndeler.getScreen("SurveyReport"+name);
				LaunchApp.driver.findElement(By.xpath(".//*[@id='Display']")).click();
				Thread.sleep(5000);
				ExceptionHndeler.getScreen("SurveyReport"+name);
				Thread.sleep(5000);
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Download Report')]")).click();
			} catch (InterruptedException e) {
				e.printStackTrace();
				if(AlertHandling.isAlertPresent())
				{
					LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Download Report')]")).click();
				}
			}
		 MooveToElement.moveToElenment(".//*[contains(text(),'Download Report')]");
		 ExceptionHndeler.getScreen("SurveyReport"+name);
	}
}
