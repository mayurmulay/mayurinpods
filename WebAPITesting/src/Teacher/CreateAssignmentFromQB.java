package Teacher;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Gototab;
import Common.LaunchApp;
import Data.ExceptionHndeler;

public class CreateAssignmentFromQB {
	@SuppressWarnings("null")
	@Test
	@Parameters({ "QBname", "Assname", "QCount", "Asscount"})
	public static void CreateAssignmentQB(String QBname,String Assname,String QCount, String Asscount)
	{
		 String args1[]={"Manage Course"," "};
		 Gototab.main(args1);
		 try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		 LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Create using Question Bank')]")).click();
		 try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		 
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='QuestionBank']")).click(); //QuestionBank
	     //new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='QuestionBank']"))).selectByVisibleText(QBname.trim());
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='QuestionBank']//*[contains(text(),'AutoTestQB')]")).click();
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='QuestionBank']")).sendKeys(QBname.trim());
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='QuestionBank']")).sendKeys(Keys.ENTER);
	     
	     
	     try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='showAllQuestions']")).click();    //Click on GO
	     
	     try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='txtAssignment']")).clear();            //Set Assignment name
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='txtAssignment']")).sendKeys(Assname);
	     
	     try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='txtNumber']")).clear();        //Set Assignment Count
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='txtNumber']")).sendKeys(Asscount);
	     
	     try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='txtQuestions']")).clear();        //Set Assignment Question Count 
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='txtQuestions']")).sendKeys(QCount);
	     
	     
	     try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}    //Select All Question 
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='resetCheckBox']")).click();
	    
	     
	     try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}    //Create Assignment 
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='createAssignment']")).click();
	     
	     
	     try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}    //Create Assignment 
	    String Data= LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/form/p/label")).getText();
	    System.out.println(Data);
	     if(Data.contains("Question Bank Assignments created successfully..."))
	       {
	    	   Assert.assertEquals(1, 1);
	       }
	       else
	       {
	    	   Assert.fail("Unable TO create QB");
	       }
	     
         String args[]={"Manage Course"," "};
		 Gototab.main(args);
		 int[] id = new int[100];
		//**************************code bellow is to find and open the newlly created assignment which commented due to new feature *************************************** 
		      List <WebElement> li=LaunchApp.driver.findElements(By.name("assignmentEdit"));
			  for(int i=0;i<li.size();i++)
			  {
				  id[i]=Integer.parseInt(li.get(i).getAttribute("id"));//getting all assignment ID 
			  }
			  Arrays.sort(id);
			 
			  int m=(id.length)-1;//picking most recent Id
				String str=Integer.toString((id[m]));
				 try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						//e.printStackTrace();
						}
				 LaunchApp.driver.findElement(By.xpath(".//*[@id='"+str+"']//*[contains(text(),'Edit')]")).click();
				 try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						//e.printStackTrace();
					}
	//***********************************************************************end*********************************************************************************			 
		 
				 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblMinutes']")).click();
				 new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='lblMinutes']//*[@name='value']"))).selectByIndex(2);
				 try {
						Thread.sleep(1000);
						System.out.println("page getting refresh");
						//try {LaunchApp.driver.navigate().refresh(); }catch(Exception e) {e.printStackTrace();}
					Thread.sleep(3000);
				    LaunchApp.driver.findElement(By.name("btnSave")).click();
					LaunchApp.driver.findElement(By.xpath(".//*[@id='cbxEnablePublish']")).click();
					LaunchApp.driver.findElement(By.xpath("//*[@class='okButtonClass']")).click();
					  Thread.sleep(1000);
					   Alert alert = LaunchApp.driver.switchTo().alert();
					   alert.accept();
				     System.out.println("save6");
				 }
				 catch(Exception e)
				 {ExceptionHndeler.Log("Save Button","Question Editing", e); e.printStackTrace();}
	     
	     
	    

	}

}
