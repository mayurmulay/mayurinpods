package Teacher;

import java.io.IOException;
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

public class UploadFile {
	public static void main(String args[])
	{
		args[0]=(String) "Manage Course";
		Gototab.main(args);
		//upload_gradebook();
	}
	@Test
	@Parameters({ "name", "path"})
	public static void upload_gradebook(String name, String path)
	{
		String[] args=new String[10]; 
	    args[0]=(String) "Manage Course";
		Gototab.main(args);
		try {
		String fname="";
		System.out.println("in upload file  ");
		LaunchApp.driver.findElement(By.xpath(".//*[@name='uploadAssignmentMarks']")).click();//click here to upload file
	 	LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-id-6']")).click();  //upload
	 	LaunchApp.driver.findElement(By.xpath(".//*[@id='asgnName']")).click();
	 	Thread.sleep(100);
	 	new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='asgnName']"))).selectByVisibleText(name.trim());  //select assignment
	 	Thread.sleep(100);
	 	LaunchApp.driver.findElement(By.id("uploadAsgnName")).sendKeys(Keys.ENTER);
	 	
	 	Thread.sleep(100);
	 	WebElement element= LaunchApp.driver.findElement(By.xpath(".//*[@id='assignmentExcelMarks']"));
	 	System.out.println("Assignment selected  "+path.trim());
		element.click();
		Thread.sleep(1000);
		try {
			Runtime.getRuntime().exec(path.trim());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(10000);
		LaunchApp.driver.findElement(By.xpath(".//*[@id='tabs-2']/table/tbody/tr[4]/td/button")).click();
		Thread.sleep(10000);
	    Alert alert = LaunchApp.driver.switchTo().alert();
	    String Data=alert.getText();
	    if(Data.contains("Marks Uploaded Successfully"))
	       {
	    	   Assert.assertEquals(1, 1);
	       }
	       else
	       {
	    	   Assert.fail("Unable TO create QB");
	       }
		 alert.accept();
		 
		        
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					ExceptionHndeler.Log("Upload","Upload", e);
				}
	     
	 }
	
}