package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MooveToElement {
	public static void moveToElenment(String xpath)
	{
		try
		{
		   WebElement ele = LaunchApp.driver.findElement(By.xpath(xpath));
		   Actions action = new Actions(LaunchApp.driver);
		   action.moveToElement(ele).build().perform();
		}catch(Exception e){e.printStackTrace();}
	}
	public static void moveToElenment()
	{
		
	}

}
