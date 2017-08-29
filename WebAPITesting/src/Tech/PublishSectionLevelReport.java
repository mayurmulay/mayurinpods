package Tech;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.openqa.selenium.Alert;

import Teacher.Publish_Section_level_Report;
import Common.AlertHandling;
import Common.LaunchApp;
import Common.Login;
import Data.ExceptionHndeler;
import Data.Read_Data;

public class PublishSectionLevelReport {
	public static String SectionId="mayur";
	public static void main(String str[])
	{
		try {
			LaunchApp.Execute("URL should be in D:\\Policy\\URL");
			Thread.sleep(2000);
			Login.LoginD("inpodssa@somaiya.edu","somaiyainp");
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		String[][] Data=Read_Data.ReadData("SomaiyaSectionIDs.csv");
		
		for(int i=0;i<Data.length;i++)
		{
			//TechChangeSection.TechChangeSection(Data[i][0].trim(), Data[i][1].trim(), Data[i][2].trim());=
			
			try {
				AlertHandling.isAlertPresent();
				SectionId=Data[i][0].trim();
				TechChangeSection.TechChangeSectionById(Data[i][0].trim());
				Thread.sleep(2000);
				Publish_Section_level_Report.main(Data[i]);
				Thread.sleep(20000);
				ExceptionHndeler.getScreen("CourseLevel"+PublishSectionLevelReport.SectionId);
				Thread.sleep(1000);
				BufferedWriter oddWriter = new BufferedWriter(new FileWriter("D:\\SomaiyaSectionIDs.txt"));
	            oddWriter.append("\n"+SectionId);
	            oddWriter.close();
			} catch (Exception e) {
				
				AlertHandling.isAlertPresent();
				ExceptionHndeler.getScreen("SectionLevel"+PublishSectionLevelReport.SectionId);
				AlertHandling.isAlertPresent();
				i--;
				e.printStackTrace();
				continue;
			}
			
		}
		
	}
	public static void PublishReport()
	{
		
		try {
			LaunchApp.Execute("http://testing.inpods.com:88/");
			Thread.sleep(2000);
			Login.LoginD("inpodssa@sar.com","techone");
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		String[][] Data=Read_Data.ReadData("SomaiyaDeptCourse.csv");
		
		for(int i=0;i<Data.length;i++)
		{
			TechChangeSection.TechChangeSection(Data[i][0].trim(), Data[i][1].trim(), Data[i][2].trim());
		}
		
		
	}
	public static void waitForAlert()
	{
	   int i=0;
	   while(i++<5)
	   {
	        try
	        {
	        	Thread.sleep(1000);
	            Alert alert = LaunchApp.driver.switchTo().alert();
	            alert.accept();
	            break;
	        }
	        catch(Exception e)
	        {
	          
	          continue;
	        }
	   }
	}

}
