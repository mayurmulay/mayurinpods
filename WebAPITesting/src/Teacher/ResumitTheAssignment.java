package Teacher;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Clickevent;
import Common.Gototab;
import Common.LaunchApp;
import Data.Loger;

public class ResumitTheAssignment {

	@Test
	@Parameters({"Student_name","Assignemnt_Name"})
	public static void RResubmit(String Student_name,String Assignemnt_Name)
	{
		try
		{
			Gototab.execute("Assignments");
			Thread.sleep(5000);
			WebElement e=LaunchApp.driver.findElement(By.xpath(".//*[.='"+Assignemnt_Name.trim()+"' and contains(@href, '/AssignmentList/StudentListForAssignment?')]"));
			
			Clickevent.ClickEvent(e);
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[.='"+Student_name.trim()+"']/../..//*[.='ReSubmit']")).click();
			Thread.sleep(3000);
			AlertHandling.waitForAlert();
		
			List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[.='"+Student_name.trim()+"']/../..//*[.='ReSubmit']"));
			
			if(li.size()>0)
			{
				Loger.LogEvent("Resubmit  :"+Assignemnt_Name, "-Fail");
				Assert.fail("Resubmit is failled");
				
			}
			
		}catch (Exception e){e.printStackTrace();}
		
	}
}
