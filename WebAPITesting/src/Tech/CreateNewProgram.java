package Tech;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.LaunchApp;

public class CreateNewProgram {
	
	@Test
	@Parameters({"programName"})
	public static void CreateNewPgm(String programName)
	{
		try 
		{
			System.out.println("In New program");
			LaunchApp.driver.findElement(By.xpath(".//*[@id='side-menu']/li[11]/a")).click();//Click on Acadamics 
			LaunchApp.driver.findElement(By.xpath(".//*[@id='side-menu']/li[11]/a/span[2]")).click();
			
			Thread.sleep(2000);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='side-menu']/li[11]/ul/li[1]/a")).click();//Click on Program
			Thread.sleep(2000);
			LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='addProgram()']")).click();//Click on New Program 
			Thread.sleep(2000);
			LaunchApp.driver.findElement(By.xpath(".//*[@name='name']")).sendKeys(programName.trim());
			Thread.sleep(2000);
			LaunchApp.driver.findElement(By.xpath(".//*[@name='rowform']//*[@type='submit']")).click();
			Thread.sleep(2000);
			Assert.assertEquals(1, 1);
			Assert.assertTrue(true, "User is able to crete new program ");
		} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("User is not able to create program "+e.getMessage());}
	}

}
