package Teacher;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Loger;

public class ValidateSharedAssignment {
	
	@Test
	@Parameters({"AssignmentList"})
	public static void ValidateAndCopySahredAssignment(String AssignmentList)
	{
			String[] args=new String[10]; 
		    args[0]=(String) "Manage Course";
			Gototab.main(args);
			try {
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Copy and Use Shared Assignments')]")).click();
			} catch (InterruptedException e) {  e.printStackTrace();
			if(AlertHandling.isAlertPresent())
				{
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Copy and Use Shared Assignments')]")).click();
				}
			}
			
			String[] AssignmentName=AssignmentList.split(":");
			MooveToElement.moveToElenment();
			ExceptionHndeler.getScreen("Sheard Assignment List");
		for(int i=0;i<AssignmentName.length;i++)
		    {
		    	try {
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[.=' "+AssignmentName[i].trim()+"']//..//*[@name='copyBoxAssignment']")).click();
				Loger.LogEvent("Selected Assignment to coppy",AssignmentName[i].trim());
		    	} catch (InterruptedException e) {  e.printStackTrace();
			      if(AlertHandling.isAlertPresent())
		     		{
			     	LaunchApp.driver.findElement(By.xpath(".//*[.=' "+AssignmentName[i].trim()+"']//..//*[@name='copyBoxAssignment']")).click();
			    	}}
		    	
		    	try {
					Thread.sleep(1000);
					LaunchApp.driver.findElement(By.xpath(".//*[@name='copyAssignments']")).click();
					Loger.LogEvent("Selected Assignment to coppy",AssignmentName[i].trim());
			    	} catch (InterruptedException e) {  e.printStackTrace();
				      if(AlertHandling.isAlertPresent())
			     		{
				     	LaunchApp.driver.findElement(By.xpath(".//*[@name='copyAssignments']")).click();
				    	}}
		    	try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	 Alert alert = LaunchApp.driver.switchTo().alert(); String text=alert.getText();
		    	 
				  if(text.equals("Assignment Copied"))
			    	{alert.accept();}
			    	else
			    	{   System.out.println("Error in Assignment Coppy"+ text);
			    		Loger.LogEvent(text,"Error");
			    		Assert.fail("not able to Copyy Assignment"+AssignmentName[i].trim());
			    	}
		     } 
		 Gototab.main(args);
		 for(int i=0;i<AssignmentName.length;i++)
		    {
		    	try {
				Thread.sleep(3000);
				LaunchApp.driver.findElement(By.xpath(".//*[.='"+AssignmentName[i].trim()+"']//..//*[@name='assignmentEdit']")).click();
				Loger.LogEvent("Assignment open Assignment name :",AssignmentName[i].trim());
				MooveToElement.moveToElenment();
				ExceptionHndeler.getScreen("Coppyassignment"+AssignmentName[i].trim());
				Thread.sleep(10000);
				MooveToElement.moveToElenment();
				ExceptionHndeler.getScreen(AssignmentName[i].trim());
				Gototab.main(args);
		    	} catch (InterruptedException e) {  e.printStackTrace();
			      if(AlertHandling.isAlertPresent())
		     		{
			    	  LaunchApp.driver.findElement(By.xpath(".//*[.='"+AssignmentName[i].trim()+"']//..//*[@name='assignmentEdit']")).click();
						Loger.LogEvent("Assignment open Assignment name :",AssignmentName[i].trim());
						MooveToElement.moveToElenment();
						ExceptionHndeler.getScreen(AssignmentName[i].trim());
						Gototab.main(args);
					}}
		    }

}
}

