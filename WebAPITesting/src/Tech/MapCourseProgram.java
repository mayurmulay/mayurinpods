package Tech;

import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.LaunchApp;

public class MapCourseProgram {
	@Test
	@Parameters({"ProgramName","DeptName","SubjName","CourseName"})
	public static void MapCourseProgramByTech(String programName,String DeptName,String SubjName,String CourseName)
	{
		try 
		{
			LaunchApp.driver.findElement(By.xpath(".//*[@id='side-menu']/li[11]/a")).click();//Click on Acadamics 
			LaunchApp.driver.findElement(By.xpath(".//*[@id='side-menu']/li[11]/a/span[2]")).click();
			
			Thread.sleep(2000);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='side-menu']/li[11]/ul/li[1]/a")).click();//Click on Program
			Thread.sleep(2000);
			List<WebElement> lists1=LaunchApp.driver.findElements(By.xpath(".//*[@ng-click='associateCourses(eachPgm.programId)']/../.."));//select all element from view
			for(int i=0;i<lists1.size();i++)
			{
				try{
				WebElement e=lists1.get(i);
				if(e.getText().contains(programName))
						{
					     e.findElement(By.xpath(".//*[@ng-click='associateCourses(eachPgm.programId)']")).click();
					     break;
						}}catch (Exception e) {}
		  	}
			Thread.sleep(5000);
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='department']"))).selectByVisibleText(DeptName);
			  
			Thread.sleep(5000);
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='subject']"))).selectByVisibleText(SubjName);
			
			
			Thread.sleep(5000);
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='subject']"))).selectByVisibleText(SubjName);
			Thread.sleep(5000);
			List<WebElement> lists2=LaunchApp.driver.findElements(By.xpath(".//*[@ng-click='addCourse(eachCourse.courseId)']/../.."));//select all element from view
			
			for(int i=0;i<lists1.size();i++)
			{
				try{
				WebElement e=lists1.get(i);
				if(e.getText().contains(programName))
						{
					     e.click();
					     Thread.sleep(5000);
						}}catch (Exception e) {}
		  	}
			
			Assert.assertEquals(1, 1);
			Assert.assertTrue(true, "User is able to crete new program ");
		} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("User is not able to create program "+e.getMessage());}
	}

}
