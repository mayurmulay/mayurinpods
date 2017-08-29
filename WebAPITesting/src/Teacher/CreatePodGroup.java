package Teacher;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;

public class CreatePodGroup {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
//Mayur upload

  @Test
  @Parameters({ "namePod", "podGrop"})
  public void testCreatePodGroup(String namePod, String podGrop) throws Exception {
	 
	  String[] args=new String[10]; 
	    args[0]=(String) "Manage Course";
		Gototab.main(args);
     LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Manage Group')]")).click();
     Thread.sleep(5000);
     LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Add new Pod Group')]")).click();
     LaunchApp.driver.findElement(By.xpath(".//*[@id='txGroupName']")).clear();
     LaunchApp.driver.findElement(By.xpath(".//*[@id='txGroupName']")).sendKeys(namePod);
     LaunchApp.driver.findElement(By.xpath(".//*[@id='txName']")).clear();
    String[]podStudentList=podGrop.split(";");
    
     LaunchApp.driver.findElement(By.id("txName")).sendKeys(podStudentList[0]+"_1");
    for(int i=1;i<podStudentList.length-1;i++)
    {
    	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'"+podStudentList[i]+"')]")).click();
    }
   
    LaunchApp.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    
    MooveToElement.moveToElenment(".//*[@id='podTable']/tbody");
    ExceptionHndeler.getScreen("Pod Group"+podGrop);
    String Data=LaunchApp.driver.findElement(By.xpath(".//*[@id='podTable']/tbody")).getText();
    
    if(Data.contains(podStudentList[0].trim()))
    {
 	   Assert.assertEquals(1, 1);
    }
    else
    {
 	   Assert.fail("Unable TO create PodGroup");
    }
    
     LaunchApp.driver.findElement(By.linkText("back")).click();
  }

 private boolean isAlertPresent() {
    try {
       LaunchApp.driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
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
}
