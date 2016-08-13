package Teacher;

import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Common.Gototab;
import Common.LaunchApp;
import Data.ExceptionHndeler;
import Data.Read_Data;

public class RgradeStudent {
  private WebDriver driver;
  private String baseUrl;
  private static boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
	public static void ReGradeStudent()
	{
		System.out.println("ReGradeAssignment");
		String[][]data=Read_Data.ReadData("ReGreading.csv");
		int count=0;
		while(!(data[count][0].equals("End")))
		{
			try {
				RgradeStudent(data[count]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}
		
	}
  public static void RgradeStudent(String[] data) throws Exception {
	  int count=2;
		try
		{
		Gototab.execute("Assignments");
		LaunchApp.driver.findElement(By.xpath(".//*[.='"+data[0]+"']")).click();
		while(!(data[count].equals("End")))
		{
		int len=0;
		String[] s=data[count].split(":");
		LaunchApp.driver.findElement(By.xpath(".//*[.='"+s[0]+"']")).click();
		List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[@name='txtPoints']"));
		len=li.size();
		for(int i=0;i<len;i++)
		{
			li.get(i).clear();
			li.get(i).sendKeys(s[i+1]);
		}
		LaunchApp.driver.findElement(By.xpath(".//*[.='Complete grading']")).click();
		Alert alert = LaunchApp.driver.switchTo().alert();
		alert.accept();
		count++;
		}
		}
		catch(Exception e){ExceptionHndeler.Log(data[count],"GradeAssignment", e);
    assertTrue(closeAlertAndGetItsText().matches("^Complete[\\s\\S]$"));
      }
  }


  private static String closeAlertAndGetItsText() {
    try {
      Alert alert =LaunchApp.driver.switchTo().alert();
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
}