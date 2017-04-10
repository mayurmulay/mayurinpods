package Tech;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Clickevent;
import Common.LaunchApp;
import Data.ExceptionHndeler;

public class CopyCourseWithCO {
	@Test
	@Parameters({"SourceProgram","SourceSemester","SourceCourse","DestProgram","DestSemester","NewCourseName","CopyConcept"})
	public static void CopyCourse(String SourceProgram,String SourceSemester,String SourceCourse,String DestProgram,String DestSemester,String NewCourseName,String CopyConcept)
	{
		try{
		Thread.sleep(2000);
		TechChangeSection.TechChangeSectionById("1");
		Thread.sleep(2000);
	    Clickevent.clickLinkByHref("/Admin/Index", ".//*[@id='side-menu']/li/a");
	    Thread.sleep(2000);
	    Clickevent.clickLinkByHref("#tabsManage-2", ".//*[@id='ui-id-2']");
	    Thread.sleep(2000);
	    Clickevent.clickLinkByHref("/admin/coursemanagement", ".//*[@id='tabsManage-2']/a");
	    Thread.sleep(2000);
	    Clickevent.clickLinkByHref("#tabsCreate-6", ".//*[@id='ui-id-11']");
	    Thread.sleep(2000);
	    Clickevent.clickLinkByHref("/copycourse/index", ".//*[@id='tabsCreate-6']/a");
	    Thread.sleep(2000);
	    
	 // Selecting Source Program
		LaunchApp.driver.findElement(By.xpath(".//*[@name='Program']")).click();
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='Program']"))).selectByVisibleText(SourceProgram.trim());
		
	//	new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='Program']"))).selectByValue(SourceProgram.trim());
		
		Thread.sleep(5000);
		 // Selecting Source Semister
		LaunchApp.driver.findElement(By.xpath(".//*[@name='Semesters']")).click();
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='Semesters']"))).selectByVisibleText(SourceSemester.trim());
		
		Thread.sleep(5000);
		 // Selecting Source Semister  
		LaunchApp.driver.findElement(By.xpath(".//*[@name='Course']")).click();
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='Course']"))).selectByVisibleText(SourceCourse.trim());
		Thread.sleep(5000);
		// Selecting Source  DestinationSemester
		LaunchApp.driver.findElement(By.xpath(".//*[@name='DestinationSemester']")).click();
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='DestinationSemester']"))).selectByVisibleText(DestSemester.trim());
		Thread.sleep(5000);
		// Selecting Source  DestinationProgramr
		LaunchApp.driver.findElement(By.xpath(".//*[@name='DestinationProgram']")).click();
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='DestinationProgram']"))).selectByVisibleText(DestProgram.trim());
	   
		Thread.sleep(5000);
		// Course name 
		LaunchApp.driver.findElement(By.xpath(".//*[@name='CourseName']")).sendKeys(NewCourseName);
		Thread.sleep(5000);
		// Course Copy Concept 
		if(CopyConcept.equals("yes"))
		{
		LaunchApp.driver.findElement(By.xpath(".//*[@name='yesNo' and @value='1']")).click();
		Thread.sleep(5000);
		}
		ExceptionHndeler.getScreen("CCB"+NewCourseName);
		
		LaunchApp.driver.findElement(By.xpath(".//*[@value='Copy Course']")).click();
		Thread.sleep(5000);
		ExceptionHndeler.getScreen("CCA"+NewCourseName);
		}catch(Exception e){e.printStackTrace();}
	}

}
