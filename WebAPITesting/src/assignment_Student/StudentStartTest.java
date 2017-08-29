package assignment_Student;

import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Gototab;
import Common.LaunchApp;
import Common.ValidateAndSnap;
import Data.ExceptionHndeler;

public class StudentStartTest {
	
	@Test
	@Parameters({"Testname","AccessCode","Activitytype","QuestionCount","Answer"})
	public static void AttemptTestBySingleUser(String Testname,String AccessCode,String Activitytype,String QuestionCount,String Answer)
	{
		String [] Ans =Answer.split(";");
		String [] str={" ",Testname,AccessCode,Activitytype,QuestionCount};
		str = (String[])ArrayUtils.addAll(str, Ans);
		try{
			//ValidateAndSnap.ValidateString("DealayShowingMarks_Report", "String:Reports");
			Thread.sleep(1000);
			Gototab.execute("Assignments");
			//ValidateAndSnap.ValidateString("DealayShowingMarks_Performance", "String:Performance");
		(new Attempt_test()).attemptTest(str); Thread.sleep(10000);} catch (Exception e){e.printStackTrace();}
		
		//Code To submit the assignment 
		
		try{
			
		 JavascriptExecutor js = ((JavascriptExecutor) LaunchApp.driver);
         js.executeScript("window.scrollTo(0, 0)");
		   LaunchApp.driver.findElement(By.xpath(".//*[@id='bottomPageNavigationContainer']/table/tbody/tr/td[4]/a")).click();
		   AlertHandling.isAlertPresent();
		   Thread.sleep(9000);
	   }catch(Exception e){System.out.println("Error in submit"+e.getMessage());e.printStackTrace();LaunchApp.driver.findElement(By.xpath(".//*[@id='bottomPageNavigationContainer']/table/tbody/tr/td[4]/a")).click();}
	   
	  try {
	   LaunchApp.driver.findElement(By.xpath(".//*[@class='ajs-button ajs-ok']")).click();  
	   Thread.sleep(9000);
	   try{LaunchApp.driver.findElement(By.xpath(".//*[@class='ui-dialog-buttonset']")).click();}  catch(Exception e){}
	   
	   Thread.sleep(9000);
	   
	  }catch(Exception e){e.printStackTrace();}
	   
	   
	}
	

}
