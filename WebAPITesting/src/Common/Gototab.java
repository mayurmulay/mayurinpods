package Common;

import org.openqa.selenium.By;

public class Gototab {

	public static void main(String[] args) {
		Gototab g=new Gototab();
		Gototab.execute(args[0]);
	}
public static void execute(String str)
{
	LaunchApp.driver.findElement(By.linkText(str.trim())).click();
}
}
