package Tech;

import java.util.List;









import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ChangeSection;
import Common.Gototab;
import Common.LaunchApp;
import Common.Login;
import Data.Loger;
import Data.Read_Data;

public class ErrorpageForAssignment {
 public static String Sectionname=" ";
 public static String tabname=" ";
	public static void main(String[] args) {
		String[] args1=new String[10];
		String[][]data1=Read_Data.ReadData("URL.csv");
		 args1[0]=data1[0][0];
		 LaunchApp.main(args1);
		String[][] data=Read_Data.ReadData("ErrorPage.csv");
		int count=0;
		while(!(data[count][0].equals("End")))
		{
			 Login.main(data[count]);
			 tabname=data[count][2].trim();
			 int SectionCount=3;
			 while(!(data[count][SectionCount].equals("End")))
			 {
				 Sectionname=data[count][SectionCount].trim();
				ChangeSection.selectSection(data[count][SectionCount].trim());//select section
				 Gototab.execute(tabname);//Go to assgnment tab
				 CheckForErrorPage();
				 SectionCount++;
				 
			 }
			 Login.logout();
			 count++;
		}
		

	}
	public static void CheckForErrorPage()
	{
		List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[.='Delete']"));
		if(!(tabname.equals("Manage Course")))
		{
		  for(int i=1;i<li.size();i++)
		  {
			  LaunchApp.driver.findElement(By.xpath(".//*[@id='sectionAssignmentDetails']//tr["+i+"]//td[2]//a")).click();
			  try
			  {
				  Thread.sleep(100);
				  if((LaunchApp.driver.findElements(By.xpath(".//*[@name='lblHours']"))).size()==1)
				  {
					  System.out.println("Error page at Section"+Sectionname+"Assignment number = "+i);
					  Loger.LogEvent("Error page test","Error page at Section"+Sectionname+"Assignment number = "+i);
				  }
			  }
			  catch(Exception e){System.out.println("Error page Exception");}
			  Gototab.execute(tabname);
		  }
		 }
		else
		{
			for(int i=1;i<li.size();i++)
			  {
				  LaunchApp.driver.findElement(By.xpath(".//*[@id='teacherNewAssignmentTable']//tr["+i+"]//td[2]//a")).click();
				  try
				  {
					  Thread.sleep(100);
					  if((LaunchApp.driver.findElements(By.xpath(".//*[.='Oops, something went wrong. If the error persists, please send a mail to support@inpods.com']"))).size()>0)
					  {
						  System.out.println("Error page at Section"+Sectionname+"Assignment number = "+i);
						  Loger.LogEvent("Error page test","Error page at Section"+Sectionname+"Assignment number = "+i);
					  }
				  }
				  catch(Exception e){System.out.println("Error page Exception");}
				  Gototab.execute(tabname);
			  }
		}
	}
	

}
