package Teacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Gototab;
import Common.LaunchApp;
import Data.ExceptionHndeler;
import Data.Loger;
import Data.Read_Data;
import Common.LaunchApp;

public class AddQuestionAssignment {

  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();



  @Test
  public void testAddQuestionAssignment() throws Exception {
	  
	  System.out.println("testAddQuestionAssignment()");
	  String[] args1 = new String[2];
	  args1[0]=(String) "Manage Course";
	  LaunchApp.driver.findElement(By.linkText("Create")).click();
	  String str=closeAlertAndGetItsText();
	  
	  
      String[] args2={"Demo","AddQuestionQB"};
      try
      {
    	  Thread.sleep(5000);
	  AssignmentCreation.Name(args2);}catch(Exception e){e.printStackTrace();}
      
      String[] args3={"Test","Test"};
      try
      {Thread.sleep(5000);AssignmentCreation.AssignmentType(args3);}catch(Exception e){e.printStackTrace();}
      
      try
      {
    	  String[] args4={"Security / Control","Group access codes;ab1"};
    	  AssignmentCreation.SecurityControl(args4);
    
      }catch(Exception e){e.printStackTrace();}
      
      LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
		 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
		 
    try
    {Thread.sleep(5000);EditActivity.createActivity("Multiple Choice");}catch(Exception e){e.printStackTrace();}
      
    LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
	 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
	 
    try
    {Thread.sleep(5000);
    LaunchApp.driver.findElement(By.name("btnAddQBQuestion")).click();}catch(Exception e){e.printStackTrace();}
    new Select(LaunchApp.driver.findElement(By.id("questionBank"))).selectByVisibleText("AutoTestQB");
    LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
	 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
    try
    {Thread.sleep(5000);}catch(Exception e){e.printStackTrace();}
    LaunchApp.driver.findElement(By.cssSelector("option[value=\"116\"]")).click();
    try
    {Thread.sleep(5000);}catch(Exception e){e.printStackTrace();}
    LaunchApp.driver.findElement(By.name("checkbox")).click();
    try
    {Thread.sleep(5000);
    List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//input[@name='checkbox']"));
    for(int i=0;i<10 && i<li.size();i++){
		try
		{
			li.get(i).click();
         Thread.sleep(2000);
		}catch(Exception e){e.printStackTrace();}
      }
    }catch(Exception e){e.printStackTrace();}
    try
    {Thread.sleep(5000);
    LaunchApp.driver.findElement(By.id("addQuestionsFromQB")).click(); 
    String str1=closeAlertAndGetItsText();}catch(Exception e){e.printStackTrace();}
   
    try
    {Thread.sleep(5000);
    LaunchApp.driver.findElement(By.cssSelector("button.ui-dialog-titlebar-close")).click();
    }catch(Exception e){e.printStackTrace();}
    
    LaunchApp.driver.findElement(By.name("btnSave")).click();
    Thread.sleep(1000);
	LaunchApp.driver.findElement(By.xpath(".//*[@id='cbxEnablePublish']")).click();
	Thread.sleep(1000);
	LaunchApp.driver.findElement(By.xpath("//*[@class='okButtonClass']")).click();
	  Thread.sleep(1000);
	   Alert alert = LaunchApp.driver.switchTo().alert();
	   alert.accept();
   
  }

  private boolean isElementPresent(By by) {
    try {
      LaunchApp.driver.findElement(by);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      LaunchApp.driver.switchTo().alert();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = LaunchApp.driver.switchTo().alert();
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
