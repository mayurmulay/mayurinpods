package Common;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;

public class Gototab {

	public static void main(String[] args) {
		Gototab g=new Gototab();
		Gototab.execute(args[0]);
	}
@Test
@Parameters({"tabname"})
public static void execute(String tabname) 
{
	try {
		System.out.println("Chanaging tab"+tabname);
		Thread.sleep(1000);
		AlertHandling.isAlertPresent();
		LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+tabname.trim()+"') and @class='menu-title']")).click();
	} catch (Exception e) {
	
		AlertHandling.isAlertPresent();
		LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+tabname.trim()+"') and @class='menu-title']")).click();
	}
	
}
}
