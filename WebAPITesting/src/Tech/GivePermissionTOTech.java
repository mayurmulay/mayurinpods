package Tech;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.LaunchApp;
import Data.ExceptionHndeler;

public class GivePermissionTOTech {
	
	@Test
	@Parameters({"ProgramName","CourseName","EmployeeType","UserName"})
	public static void give_Permission(String ProgramName,String CourseName,String EmployeeType,String UserName)
	{
		try {
			//System.out.println("permission");
			TechChangeSection.TechChangeSectionById("1");
			Thread.sleep(2000);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='side-menu']/li[8]/a")).click();//Click on Obe
			Thread.sleep(2000);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='side-menu']/li[8]/ul/li[2]/a")).click();// Click on permisssion
			Thread.sleep(2000);
			LaunchApp.driver.navigate().refresh();
			Thread.sleep(2000);
			LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='createPermission()']")).click();//Click on create permission
			Thread.sleep(2000);
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='objtype']"))).selectByValue("0");//Select all from drop down 
			Thread.sleep(2000);
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[2]/div/div[1]/div/select"))).selectByVisibleText(ProgramName.trim());//Select program
			Thread.sleep(2000);
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[2]/div/div[2]/div/div/select"))).selectByVisibleText(CourseName.trim());// Select Course 
			Thread.sleep(2000);
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@class='selectpicker ng-pristine ng-valid'][@name='EmployeeType']"))).selectByVisibleText(EmployeeType.trim());
	    	
			Thread.sleep(2000);
			 new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='staff']"))).selectByVisibleText(UserName.trim());
			Thread.sleep(2000);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[4]/table/tbody/tr[2]/td[3]/input")).click();
		    LaunchApp.driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[4]/table/tbody/tr[3]/td[3]/input")).click();
		    LaunchApp.driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[4]/table/tbody/tr[5]/td[3]/input")).click();
		    Thread.sleep(2000);
		    ExceptionHndeler.getScreen(CourseName);
		    Thread.sleep(2000);
		    LaunchApp.driver.findElement(By.className("icon-save")).click();
			Thread.sleep(2000);
			String str=AlertHandling.closeAlertAndGetItsText();
			if(str.equals(""))
			Thread.sleep(2000);
		    } catch (Exception e) {
			e.printStackTrace();
		}
	}

}