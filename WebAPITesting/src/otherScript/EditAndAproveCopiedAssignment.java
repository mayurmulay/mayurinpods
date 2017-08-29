package otherScript;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Teacher.AssignmentCreation;
import Common.ChangeSection;
import Common.Gototab;
import Common.LaunchApp;
import Common.Login;
import Data.ExceptionHndeler;
import Data.Read_Data;

public class EditAndAproveCopiedAssignment {
	static String Teachername="maym@inpods.com";
	public static void lunchappAndLogIn(String username,String pass)
	{
		try
		{
		
	     LaunchApp.Execute("Data is present in URL file");
		Thread.sleep(3000);
		Login.LoginD(username,pass);
		}catch(Exception e){};
	}
	public static void Exicute()
	{
		String[][] Data=Read_Data.ReadData("AssgmentAprove.csv");
		int i=0;
		while(Data[i][0]!=null)
		{
			lunchappAndLogIn(Data[i][0].trim(),Data[i][1].trim());
			//lunchappAndLogIn("maym@inpods.com","password");
			
			ChangeSection.selectSection(Data[i][2].trim());
			
			//Teachername=Data[i][0].trim();
			
			OpenAssignmentTOEdit();
			//Gototab.execute("Manage Course");
			    
			i++;
		}
		
	}
	public static void OpenAssignmentTOEdit()
	{
		 Gototab.execute("Manage Course");
		 int []id=new int[10];
		 
		  for(int i=0;i<3;i++)
		  {
			  LaunchApp.driver.navigate().refresh();
			  Gototab.execute("Manage Course");
			  List <WebElement> li=LaunchApp.driver.findElements(By.name("assignmentEdit"));
			  try{
				  Thread.sleep(10000);
			  id[i]=Integer.parseInt(li.get(i).getAttribute("id"));//getting all assignment ID 
			  
			  li.get(i).click();
			  Thread.sleep(3000);
			  try {
					Thread.sleep(100);
					LaunchApp.driver.findElement(By.name("lblActivitySubTypeName")).click();
				} catch (Exception e) {LaunchApp.driver.findElement(By.name("lblActivitySubTypeName")).click();}
			  EditQuestion();
			  Thread.sleep(3000);
			  }catch(Exception e){System.out.println("error while editing assignment"+id[i]+" "+Teachername);
			                      System.out.println("Error"+e.getMessage());
			                      e.printStackTrace();}
		  }
		  LaunchApp.driver.close();
	}
	public static void EditQuestion()
	{
		 List <WebElement> li=LaunchApp.driver.findElements(By.xpath("//*[@value='points']"));
			ArrayList<String> Qid= new ArrayList<String>();
			int i=0,count=0;
			for(WebElement element : li){
				try
				{
					String s=element.getAttribute("id");
		            if(!s.equals(""))
		            {
		            	Qid.add(s);
			           count++;
		            }
		            
				}
			    catch(Exception e)
			    {
			    	 e.printStackTrace();
			    }		
		    }
			i=0;
			try
			{
			Collections.sort(Qid);
			 while(i<count)
					{
				           mapCO(i%5,Qid.get(i));
				         i++;
					}
			}
			 catch(Exception e) {e.printStackTrace();}
			try{  // save and aprove assignment 
				JavascriptExecutor js = ((JavascriptExecutor) LaunchApp.driver);
	            js.executeScript("window.scrollTo(0,0)");
	            Thread.sleep(1000);
				ExceptionHndeler.getScreen("Creating Assignment MetaData"+AssignmentCreation.assName);
				Thread.sleep(1000);
				
	            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	            Thread.sleep(1000);
				
			    LaunchApp.driver.findElement(By.name("btnSave")).click();
			    Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[@id='cbxEnablePublish']")).click();
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath("//*[@class='okButtonClass']")).click();
				  Thread.sleep(1000);
				   Alert alert = LaunchApp.driver.switchTo().alert();
				   alert.accept();
			     Thread.sleep(3000);
			}catch(Exception e) {e.printStackTrace();}
			
	}
	public static void mapCO(int i,String Qid)
	{
		
		try{
			Thread.sleep(300);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblConcept']")).click();
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@class='dynatree-expander']")).click(); 
		//	System.out.println(".//*[@id='"+Qid+"']/ul/li/ul/li["+(i+1)+"]/span/span[2]");
		  	LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']/ul/li/ul/li["+(i+1)+"]/span/span[2]")).click();  
		    }catch (Exception e) {
		    	LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblConcept']")).click();
				LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@class='dynatree-expander']")).click();
				System.out.println("In error .//*[@id='"+Qid+"']/ul/li/ul/li["+(i+1)+"]/span/span[2]");
			  	LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']/ul/li/ul/li["+(i+1)+"]/span/span[2]")).click(); 
				ExceptionHndeler.Log("Map Co","Question Editing", e);
			}
	}
	public static void main(String str[])
	{
		Exicute();
	}

}
