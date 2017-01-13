package Common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import Data.ExceptionHndeler;
import Data.Loger;

public class AlertHandling {
	public static boolean isAlertPresent() {
	    try {
	    	Alert alert=LaunchApp.driver.switchTo().alert();
	        String Alert_Error=closeAlertAndGetItsText();
	        ExceptionHndeler.getScreen("Alert"+Alert_Error);
	        try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        isAlertPresent();
	    	Loger.LogEvent(Alert_Error, "Error");
	      return true;
	    } catch (NoAlertPresentException e) {
	    	
	      return false;
	    }
	  }

	  public static String closeAlertAndGetItsText() {
	    boolean acceptNextAlert=true;
		try {
	      Alert alert =  LaunchApp.driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	  public static void waitForAlert()
		{
		   int i=0;
		   while(i++<5)
		   {
		        try
		        {
		        	Thread.sleep(1000);
		            Alert alert = LaunchApp.driver.switchTo().alert();
		            alert.accept();
		            break;
		        }
		        catch(Exception e)
		        {
		          
		          continue;
		        }
		   }
		}


}
