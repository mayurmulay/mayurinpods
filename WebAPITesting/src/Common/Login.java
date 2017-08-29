package Common;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;

import Data.ExceptionHndeler;

public class Login {
	
	@Test
	@Parameters({ "username", "password" })
	public static void LoginD(String username,String password)
	{
	try
	{
		 Thread.sleep(4000);
	   LaunchApp.driver.findElement(By.xpath(".//*[@id='topbar']/div[2]/ul/li/a")).click();
		// LaunchApp.driver.findElement(By.xpath(".//*[.='Sign In']")).click();
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
		ex.printStackTrace();
		System.out.println(ex.getLocalizedMessage());
		org.testng.Assert.assertEquals(false, true,"Login Failed for:"+username);
		ExceptionHndeler.Log(username,"Log IN", ex);
	}
	}
	public static void LoginD(String username,String password,String instituteName)
	{
	try
	{
		 Thread.sleep(1000);
	   LaunchApp.driver.findElement(By.xpath(".//*[@id='topbar']/div[2]/ul/li/a")).click();
		// LaunchApp.driver.findElement(By.xpath(".//*[.='Sign In']")).click();
	    Thread.sleep(1000);
		LaunchApp.driver.findElement(By.xpath(".//*[@id='UserName']")).sendKeys(username.trim());
		 Thread.sleep(1000);
		LaunchApp.driver.findElement(By.xpath(".//*[@id='Password']")).sendKeys(password.trim());
		 Thread.sleep(1000);
		LaunchApp.driver.findElement(By.xpath(".//*[@id='page-login']//button")).click();
		 Thread.sleep(4000);
		 try
		 {
	      LaunchApp.driver.findElement(By.xpath(".//*[@id='inst']")).click();
			 Thread.sleep(4000);
		  new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='inst']"))).selectByVisibleText(instituteName);
		  Thread.sleep(4000);
		   LaunchApp.driver.findElement(By.xpath(".//*[@id='roleModal']/div/div/form/div[3]/button")).click();
		 
		 }catch(Exception ex){}
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
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
