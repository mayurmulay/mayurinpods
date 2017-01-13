package Teacher;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Gototab;
import Common.LaunchApp;
import Data.ExceptionHndeler;

public class CreateAssignment {
	public static void main(String[] args) {
		String[] s=args[1].split(":");
		String type=" ";
		type= s[1].trim();
	  
		try {
			Gototab.execute("Manage Course");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateAssignment c=new CreateAssignment();
		System.out.println("ma"+args[0]);
		
		c.execute(type);
		

	}
  public void execute(String type) 
  {
	  
	  try
	  {
		  if(type.equals("External"))
		  {
			  LaunchApp.driver.findElement(By.xpath(".//*[@name='createExternalAssignment']")).click();// For External Assgnment 
		  }
		  else
		  {
		  LaunchApp.driver.findElement(By.xpath(".//*[@name='createAssignment']")).click();// For all type other thane Externaml
		  }
		  Thread.sleep(2000);
		   Alert alert = LaunchApp.driver.switchTo().alert();
		   alert.accept();
		//  Robot rb=new Robot();
		//  rb.keyPress(KeyEvent.VK_ENTER);
	     Thread.sleep(1000);
		 // rb.keyPress(KeyEvent.VK_TAB);  
		 // rb.keyPress(KeyEvent.VK_ENTER);
		// LaunchApp.driver.findElement(By.xpath(".//*[@id='middle']/div[1]/div/a")).click();
		 // throw new Exception("Exception for string ");
      }
	  catch(Exception e)
	  {
		  ExceptionHndeler.Log("create assignment", "manage Course", e);
		  e.printStackTrace();
		  
		  //System.out.println("Assignment creation failed");
	  }
	  
	  
  }
}
