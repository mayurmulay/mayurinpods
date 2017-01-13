package Common;

import org.testng.annotations.Test;

public class CloseBrowser {
	@Test
	public static void closebrowser()
	{
		LaunchApp.driver.quit();
		
	}

}
