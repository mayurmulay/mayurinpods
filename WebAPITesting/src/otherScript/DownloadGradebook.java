package otherScript;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.ChangeSection;
import Common.Gototab;
import Common.LaunchApp;
import Common.Login;
import Data.Read_Data;

public class DownloadGradebook {
	public static void main(String str[])
	{
		
	String[][] Data=Read_Data.ReadData("AssgmentAprove.csv");
	int i=1;
	while(Data[i][0]!=null)
	{
		try
		{
		 LaunchApp.Execute("Data is present in URL file");
		 Thread.sleep(3000);
		 Login.LoginD(Data[i][0].trim(),Data[i][1].trim());
		 Thread.sleep(3000);
		 ChangeSection.selectSection(Data[i][2].trim());
		 Thread.sleep(3000);
		 Gototab.execute("Manage Course");
		 Thread.sleep(3000);
		 DownloadGradebook();
		 
		 LaunchApp.driver.close();
		 i++;
		}catch(Exception e){e.printStackTrace();}
		
	}
	}
	public static void DownloadGradebook()
	{
		try
		{
		LaunchApp.driver.findElement(By.xpath(".//*[@name='uploadAssignmentMarks']")).click();
		Thread.sleep(3000);
		downloadgradebook("Mid Test 2");
		
		downloadgradebook("End Sem Exam");
		
		downloadgradebook("Mid Test 1");
		
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static void downloadgradebook(String assignmnetname)
	{
		try{
		
 		List<WebElement> list = LaunchApp.driver.findElements(By.tagName("option"));
 		Iterator<WebElement> i1 = list.iterator();
 		String id=" ";
 		while(i1.hasNext()) {
 		    WebElement wel = i1.next(); 
 		    if(wel.getText().trim().equals(assignmnetname.trim()))
 		    {
 		    	id=wel.getAttribute("value");
 		    }
 		    	
 		    }
 		System.out.println("value="+id);
 	new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='asgnName']"))).selectByValue(id);; 
 	Thread.sleep(5000);
 	LaunchApp.driver.findElement(By.xpath(".//*[@id='downloadGradeBook']")).click();
 	Thread.sleep(10000);
		}
		
		catch(Exception e){}
	}
	
}
