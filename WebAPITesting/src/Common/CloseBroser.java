package Common;

import org.testng.annotations.Test;

public class CloseBroser
{
	@Test
	public static void quit1()
	{
		try{
			LaunchApp.driver.quit();}catch(Exception ex){}
		
	}
}