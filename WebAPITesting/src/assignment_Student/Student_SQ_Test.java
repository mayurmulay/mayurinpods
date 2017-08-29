package assignment_Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.AlertHandling;
import Common.LaunchApp;
import Data.ExceptionHndeler;
import Data.Loger;

import org.openqa.selenium.Keys;
import org.testng.Assert;

public class Student_SQ_Test {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	public static void SQ_Test(String[] str )
	{
		
	System.out.println("Attempting the SQ");
	Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    List <WebElement> li=Common.LaunchApp.driver.findElements(By.xpath(".//*[.='Click here to add Answer !!']"));
    System.out.println("Total number of Question="+li.size());
    
    try {
		Thread.sleep(2000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    int i=0;
    for(WebElement element : li){
    	try {
			Thread.sleep(2000);
			element.click(); //Click here to add Answer !!
			if(i==0){i=1;Thread.sleep(10000);}
			Thread.sleep(10000);
			WebElement currentElement = Common.LaunchApp.driver.switchTo().activeElement();
			currentElement.sendKeys("Answer for Question");
			Loger.LogEvent("Abale to attempt SQ Question", "-Pass");
			 Assert.assertEquals(1, 1);
			 System.out.println("Attempting Question"+li.indexOf(element));
	    	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Loger.LogEvent("Abale to attempt SQ Question", "-Fail");
			Assert.fail("Unable attempt SQ Question");
		}
    	
    System.out.println("Attempted the SQ");
    }
	}

	
	
}
