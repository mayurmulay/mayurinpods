package Tech;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.LaunchApp;
import Data.Read_Data;

public class ChangeCOPOMapping {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	 @Test
	 @Parameters({ "CO_name", "CO_Description", "Program", "Course", "FileName"})
public static void createCO(String CO_name, String CO_Description, String Program, String Course, String FileName)
		{
    try
			{
				Thread.sleep(3000);
		LaunchApp.driver.findElement(By.xpath("//.//*[@id='side-menu']/li[7]/a")).click();  //OBE link for Tech
			    Thread.sleep(3000);
		WebElement e1=	LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'COs')]"));
		LaunchApp.ClickEvent(e1);
			     Thread.sleep(3000);
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='program_select']"))).selectByVisibleText(Program.trim());
			     Thread.sleep(15000);
	    new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='course_select']"))).selectByVisibleText(Course.trim());
			     Thread.sleep(3000);
		
	    LaunchApp.driver.findElement(By.xpath(".//*[@id='obeData']//*[@ng-click='tab = 2; getObjectives(tab)']")).click();
	    
	    String[] data=FileName.split(":");
	    if(data[0].contains("CO_name"))
	    {
	    	CO_POmapping(data);
	    }
	    if(data[0].contains("File_name"))
	    {
	    String [][] CO_POmappingData=Read_Data.ReadData(data[0].trim());
	    int count=0;
	    while(!CO_POmappingData[count][1].equalsIgnoreCase("End"))
	    {
	    	CO_POmapping(CO_POmappingData[count]);
	    }
	    
	    }
	    // List<WebElement> el=
			     
			     
			}catch (Exception e){e.printStackTrace();}
		}
	 public static void CO_POmapping(String[] Data)
	 {
		// List<WebElement> el=LaunchApp.driver.findElements(By.xpath())
	 }

}
