package Teacher;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Gototab;
import Common.LaunchApp;
import Data.ExceptionHndeler;
import Data.Read_Data;

public class GradeAssignment {
	public static void main(String args[])
	{
		System.out.println("GradeAssignment");
		String[][]data=Read_Data.ReadData("Greading.csv");
		int count=0;
		while(!(data[count][0].equals("End")))
		{
			GradeAssignment(data[count]);
			count++;
		}
		
	}
	@Test
	public static void GradeStudent()
	{
		System.out.println("GradeAssignment");
		String[][]data=Read_Data.ReadData("Greading.csv");
		int count=0;
		while(!(data[count][0].equals("End")))
		{
			GradeAssignment(data[count]);
			count++;
		}
		
	}
	public static void GradeAssignment(String[] data)
	{
		int count=2;
		try
		{
		
		while(!(data[count].equals("End")))
		{
		int len=0;
		Gototab.execute("Assignments");
		System.out.println("Opening Assignment page");
		Thread.sleep(20000);
		//System.out.println(".//*[.='"+data[0].trim()+"' and contains(@href, '/AssignmentList/StudentListForAssignment?')]");
		LaunchApp.driver.findElement(By.xpath(".//*[.='"+data[0].trim()+"' and contains(@href, '/AssignmentList/StudentListForAssignment?')]")).click();
		
		String[] s=data[count].split(":");
		LaunchApp.driver.findElement(By.xpath(".//*[.='"+s[0].trim()+"']")).click();
		//LaunchApp.driver.findElement(By.xpath(".//*[.='"+s[0].trim()+"']/../..//*[.='ReGrade']")).click();
		Thread.sleep(2000);
		//Alert alert = LaunchApp.driver.switchTo().alert();
		//alert.accept();
		Thread.sleep(3000);
		List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[@name='txtPoints']"));
		len=li.size();
		for(int i=0;i<len;i++)
		{
			li.get(i).clear();
			li.get(i).sendKeys(s[i+1]);
		}
		Thread.sleep(10000);
		
		WebElement element = LaunchApp.driver.findElement(By.xpath(".//*[@id='bottomPageNavigationContainer']/table/tbody/tr/td[4]/a"));
     //   Actions actions = new Actions(LaunchApp.driver);
      //  actions.moveToElement(element).click().perform();
        
        JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
        jse.executeScript("arguments[0].scrollIntoView()", element); 
        jse.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
       // LaunchApp.driver.findElement(By.xpath(".//*[@id='bottomPageNavigationContainer']/table/tbody/tr/td[4]/a")).click();

		Thread.sleep(2000);
		AlertHandling.waitForAlert();
		System.out.println(s[0].trim());
		Thread.sleep(8000);
		count++;
		}
		}
		catch(Exception e){e.printStackTrace();ExceptionHndeler.Log(data[count].substring(0, 5),"GradeAssignment", e);}
	}

}
