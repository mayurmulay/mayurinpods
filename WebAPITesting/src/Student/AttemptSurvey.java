package Student;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.ChangeSection;
import Common.Clickevent;
import Common.Gototab;
import Common.LaunchApp;
import Common.Login;
import Data.ExceptionHndeler;
import Data.Loger;
import Data.Read_Data;

public class AttemptSurvey {
	
	static String[][] SurveyData= null;
	
	@Test
	@Parameters({"fileName", "testCaseName"})
	public static void SurveyData1(String fileName,String testCaseName)
	{
		System.out.println("fileName="+fileName);
		SurveyData=Read_Data.ReadData(fileName.trim());
		System.out.println("fileName="+fileName);
		LaunchApp.Execute("URL us set in URL file");
		int rowCount=1;
		while(!SurveyData[rowCount][0].equals("End"))
		{
			String testcase=SurveyData[rowCount][0];
			if(testcase.trim().equals(testCaseName.trim()))
				{
                   AttemptSurvey(SurveyData[rowCount]);					
				}
			rowCount++;
			
		}
		
		
	}
	public static void AttemptSurvey(String[] AttemptData)
	{
		try
		{
		int coloumCount=1;
		int Firstoption=0;
		String username=" ";
		while(!AttemptData[coloumCount].equals("End"))
		{
			System.out.println("fileName="+AttemptData[coloumCount]);
			String[]data=AttemptData[coloumCount].split(";");
			System.out.println("fileName="+data[0]);
			if(data[0].equals("username"))
			{
				System.out.println("fileName="+data[1]);
				String[]m=data[1].split(":");
				System.out.println("fileName="+m[0]);
				System.out.println("fileName="+m[1]);
				 username=m[0];
				Login.main(m);
			}
			if(data[0].equals("Section"))
			{
				ChangeSection.selectSection(data[1].trim());
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.linkText("Dashboard")).click();
			}
			if(data[0].equals("Surveyname"))
			{
				Thread.sleep(5000);
				Gototab.execute("Survey");
				Thread.sleep(5000);
				LaunchApp.driver.findElement(By.linkText(data[1])).click();
				Thread.sleep(10000);
				Firstoption=getFirstOption();
			}
			if(data[0].equals("opt"))
			{
				System.out.println("opt="+data[1]+"   ");
				int choice=Integer.parseInt(data[1]);
				int optToselect=choice+Firstoption-1;
				String optToselectString=optToselect+" ";
				System.out.println(".//*[@id='"+optToselectString.trim()+"']");
				//WebElement element=LaunchApp.driver.findElement(By.xpath(".//*[@id='"+optToselectString.trim()+"']"));
				//Clickevent.ClickEvent(element);
				LaunchApp.driver.findElement(By.xpath(".//*[@id='"+optToselectString.trim()+"'and  @type='radio']")).click();
				
			}
			Thread.sleep(1000);
			coloumCount++;
		}
		LaunchApp.driver.findElement(By.xpath(".//*[@id='commentTextArea']")).sendKeys("Comment by student"+username);
		Thread.sleep(1000);
		ExceptionHndeler.getScreen("Survey_option"+username);
		LaunchApp.driver.findElement(By.xpath(".//*[@id='submitSurvey']")).click();
		AlertHandling.waitForAlert();
		Thread.sleep(3000);
		ExceptionHndeler.getScreen("Survey_Submited"+username);
		}
		catch(Exception e){e.printStackTrace();Loger.LogException("error", "Survey", e.getMessage());}
	}
	public static int getFirstOption()
	{
		int fistOption=0;
		List <WebElement> li=Common.LaunchApp.driver.findElements(By.xpath(".//*[@title='Select correct answer']"));
		if(li.size()==0)
		{
			li=Common.LaunchApp.driver.findElements(By.xpath("//*[contains(text(),'Option 1')]"));
		}
		System.out.println("Count of question="+li.size());
		int[] Qid=new int [li.size()];
		int i=0;
		for(WebElement element : li){
			try
			{
				Qid[i]=Integer.parseInt(element.getAttribute("id"));
				i++;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		Arrays.sort(Qid);
		return Qid[0];
	}

}
