package Teacher;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Clickevent;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;

public class LessonDisscussionStart {
	@Test
	@Parameters({ "Chapter", "lesson", "Question"})
	public static void StartLessonDiscusion(String Chapter,String lesson,String Question)
	{
		String[] args=new String[10]; 
	    args[0]=(String) "Lessons";
		Gototab.main(args);
		try{
			Thread.sleep(3000);
			
			WebElement e=LaunchApp.driver.findElement(By.xpath(".//*[.='"+Chapter.trim()+"' and contains(@href,'/Module/Index')]"));
			Clickevent.ClickEvent(e);
			Thread.sleep(3000);
			
			e=LaunchApp.driver.findElement(By.xpath(".//*[.='"+lesson.trim()+"'and contains(@href,'/Lesson/')]"));
			Clickevent.ClickEvent(e);
			Thread.sleep(3000);
			
			 e=LaunchApp.driver.findElement(By.xpath(".//*[@name='lblNewQuestion']"));
			Clickevent.ClickEvent(e);
			Thread.sleep(3000);
			
			LaunchApp.driver.findElement(By.xpath(".//*[@name='txtNewQuestion']")).sendKeys(Question.trim());
			Thread.sleep(3000);
			
			 e=LaunchApp.driver.findElement(By.xpath(".//*[@name='btnAskQuestion']"));
			Clickevent.ClickEvent(e);
			Thread.sleep(3000);
			MooveToElement.moveToElenment(".//*[@id='tblQuestionList']");
		     ExceptionHndeler.getScreen("Question_Posted"+lesson);
		
		String text=LaunchApp.driver.findElement(By.xpath(".//*[@id='tblQuestionList']")).getText();
		if(text.contains(Question.trim()))
		{
			Assert.assertTrue(true, "User able to create");
		}
		else
		{
			Assert.fail("teacher is not able to post question under lesson"+lesson);
		}
		
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail("teacher is not able to post question under lesson"+lesson +"=Exception"+e.getMessage());
		}
		
	}

}
