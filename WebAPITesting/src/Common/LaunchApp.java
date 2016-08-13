package Common;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Parameters;

import Data.ExceptionHndeler;

public class LaunchApp {

	public static WebDriver driver=null;
	@Test
	@Parameters("TestData")
	public static void Execute(String TestData)
	{
		
		try
		{
			//TestData="http://testing.inpods.com/";
				//---Create Firefox Profile Object
				FirefoxProfile fp=new FirefoxProfile();
				//---disable Browser Cache
				fp.setPreference("browser.cache.disk.enable", false);
				//---initialize the driver object
				driver=new FirefoxDriver(fp);
				//synchronization timeout for all objects here we will set it for 20
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				//--launch the URL
				driver.get(TestData);
				driver.manage().window().maximize();
		}//end of try
		catch(Exception ex)
		{
			ExceptionHndeler.Log("launchApp","Execute", ex);
			System.out.println(ex.getLocalizedMessage());
			org.testng.Assert.assertEquals(false, true,"Launch Applicatin Failed for:"+TestData);
		}//end of catch
	}
	public static void main(String[] args) {
		LaunchApp la=new LaunchApp();
		LaunchApp.Execute(args[0]);
		               
	}

}