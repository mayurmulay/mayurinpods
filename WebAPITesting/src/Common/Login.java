package Common;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;

import Data.ExceptionHndeler;

public class Login {
	
	@Test
	@Parameters({ "username", "password" })
	public static void LoginD(String username,String password)
	{
	try
	{
	    LaunchApp.driver.findElement(By.xpath(".//*[@id='topbar']/div[2]/ul/li/a")).click();
	    Thread.sleep(2000);
		LaunchApp.driver.findElement(By.xpath(".//*[@id='UserName']")).sendKeys(username.trim());
		 Thread.sleep(2000);
		LaunchApp.driver.findElement(By.xpath(".//*[@id='Password']")).sendKeys(password.trim());
		 Thread.sleep(2000);
		LaunchApp.driver.findElement(By.xpath(".//*[@id='page-login']//button")).click();
		 Thread.sleep(2000);
	}
	catch(Exception ex)
	{
		System.out.println(ex.getLocalizedMessage());
		org.testng.Assert.assertEquals(false, true,"Login Failed for:"+username);
		ExceptionHndeler.Log(username,"Log IN", ex);
	}
	}
  public static void logout()
  {
	  try
		{
		    LaunchApp.driver.findElement(By.xpath(".//*[@id='topbar']/div[2]/ul[2]/li[2]/a")).click();
			LaunchApp.driver.findElement(By.xpath(".//*[.='Sign Out']")).click();
		}
	  catch(Exception ex)
		{
			System.out.println(ex.getLocalizedMessage());
			org.testng.Assert.assertEquals(false, true,"LogOut Failed ");
			ExceptionHndeler.Log("Logout failled ","Log Logout", ex);
		}
  }
	public static void main(String[] args) {
		
			//---launch the application first
			//LaunchApp.main(args);
		LoginD(args[0],args[1]);
			
		
		
		
	}

}
