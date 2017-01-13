package Common;

import org.openqa.selenium.By;

public class Gototab {

	public static void main(String[] args) {
		Gototab g=new Gototab();
		try {
			Gototab.execute(args[0]);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public static void execute(String str) throws InterruptedException
{
	try {
		Thread.sleep(1000);
		AlertHandling.isAlertPresent();
		LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+str.trim()+"') and @class='menu-title']")).click();
	} catch (Exception e) {
		Thread.sleep(10000);
		AlertHandling.isAlertPresent();
		LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+str.trim()+"') and @class='menu-title']")).click();
	}
	
}
}
