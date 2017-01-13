package Common;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Parameters;

import Data.ExceptionHndeler;
import Data.Read_Data;

public class LaunchApp {

	public static String URL1=null;
	public static WebDriver driver=null;
	
	@Test
	@Parameters("TestData")
	public static void Execute(String TestData)
	{
		
		try
		{
			//TestData="http://testing.inpods.com/";
				//---Create Firefox Profile Object
				//FirefoxProfile fp=new FirefoxProfile();
				//---disable Browser Cache
				//fp.setPreference("browser.cache.disk.enable", false);
				//---initialize the driver object     D:\InpodsAutomation\WebAPITesting\JAR
				//driver=new FirefoxDriver(fp);
			String[][] URl=Read_Data.ReadData("URL.csv");
			TestData=URl[0][0].trim();
			 URL1=TestData;
			System.setProperty("webdriver.chrome.driver","D:\\InpodsAutomation\\WebAPITesting\\JAR\\chromedriver.exe.");
			System.out.println(System.getProperty("user.dir")+"/AllDrivers/chromedriver_v_2.21.exe");
			  driver = new ChromeDriver();
				//synchronization timeout for all objects here we will set it for 20
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
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
	public static void ClickEvent(WebElement element){
		JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
	    jse.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
		
	}
static class CloseBroser
{
	@Test
	public static void quit1()
	{
		try{
		driver.quit();}catch(Exception ex){}
		
	}
}

}