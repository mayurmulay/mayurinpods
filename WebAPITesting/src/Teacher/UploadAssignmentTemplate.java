package Teacher;

import org.openqa.selenium.support.ui.Select;

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

public class UploadAssignmentTemplate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Test
	@Parameters({ "AssName", "path"})
	  public void testUploadAssignmentTemplate(String AssName, String path) throws Exception {
	   
		String[] args=new String[10]; 
	    args[0]=(String) "Manage Course";
		Gototab.main(args);
	
	    LaunchApp.driver.findElement(By.xpath(".//*[@name='uploadAssignment']")).click();
	    LaunchApp.driver.findElement(By.xpath(".//*[@name='txtAssName']")).clear();
	    LaunchApp.driver.findElement(By.xpath(".//*[@name='txtAssName']")).sendKeys(AssName); 
	    
	    new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='lessonId']"))).selectByVisibleText("Link_ShareWithInstitute");
	    LaunchApp.driver.findElement(By.xpath(".//*[@id='assignmentExcel']")).click();
	    
	    try {
			Runtime.getRuntime().exec(path.trim());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Thread.sleep(10000);
	    LaunchApp.driver.findElement(By.xpath(".//*[@name='Save']")).click();
	    Thread.sleep(20000);
	    Alert alert = LaunchApp.driver.switchTo().alert();
	    alert.accept();
	    
	    CreateAssignmentFromQB.EditLastCreatedAssignment("UploadAssignmentTemplate");
	    
	  }


}
