package Teacher;

import java.util.List;

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
	
public class AnswerForLessonDisscussion {
		@Test
		@Parameters({ "Chapter", "lesson", "Question","Answer"})
		public static void StartLessonDiscusion(String Chapter,String lesson,String Question,String Answer)
		{
			String[] args=new String[10]; 
		    args[0]=(String) "Lessons";
			Gototab.main(args);
			try{
				System.out.println("Mayur String1");
				Thread.sleep(3000);
				WebElement e=LaunchApp.driver.findElement(By.xpath(".//*[.='"+Chapter.trim()+"']"));
				//Clickevent.ClickEvent(e);
				e.click();
			Thread.sleep(4000);
			System.out.println("Mayur String2");
			//e=LaunchApp.driver.findElement(By.xpath(".//*[.='"+lesson.trim()+"']"));
			e=LaunchApp.driver.findElement(By.xpath(".//*[@tooltip='"+lesson.trim()+"']"));
			System.out.println(".//*[.='"+lesson.trim()+"']");
			//Clickevent.ClickEvent(e);tooltip
			e.click();
			System.out.println("Mayur String3");
			Thread.sleep(5000);
		//	LaunchApp.driver.findElement(By.xpath(".//*[@class='threadRow' and not(contains(@style, 'width:15%'))]")).click();   .//*[@id='tblQuestionList']/tbody/tr[1]/td[3]
			List <WebElement> element=LaunchApp.driver.findElements(By.xpath(".//*[@id='tblQuestionList']/tbody/tr/td[3]"));
			for(WebElement  li: element){
				try{
				if(li.getText().trim().equals(Question.trim()))
				{
					Thread.sleep(5000);
					System.out.println("Mayur String3"+li.getText());
					Clickevent.ClickEvent(li);
					break;
				}}catch(Exception e23){e23.printStackTrace();
					continue;}
			}
		//	Clickevent.ClickEvent(e);
			
			Thread.sleep(10000);
			e=LaunchApp.driver.findElement(By.xpath(".//*[@name='lblNewAnswer']"));
			Clickevent.ClickEvent(e);
			
			
			Thread.sleep(3000);
		
			LaunchApp.driver.findElement(By.xpath(".//*[@name='txtPostAnswer']")).sendKeys(Answer.trim());
			
			Thread.sleep(3000);
		    e=LaunchApp.driver.findElement(By.xpath(".//*[@name='btnPostYourAnswer']"));
			Clickevent.ClickEvent(e);
			MooveToElement.moveToElenment(".//*[@name='questionDiscussionThread']");
			ExceptionHndeler.getScreen("Question Posted"+lesson);
			
			String text=LaunchApp.driver.findElement(By.xpath(".//*[@name='questionDiscussionThread']")).getText();
			if(text.contains(Question.trim()) && text.contains(Answer.trim()))
			{
				Assert.assertTrue(true, "User able to post answer for question");
			}
			else
			{
				Assert.fail("User  is not able to post answer for question under lesson"+lesson);
			}
			
			}catch(Exception e){e.printStackTrace(); Assert.fail("User  is not able to post answer for question under lesson"+lesson+e.getMessage());
			}
			
		}

}
