package Teacher;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Read_Data;


public class SurveyCreation {
	public static String CORubricData[][]=Read_Data.ReadData("CoBloomRubric.csv");
	@SuppressWarnings("deprecation")
	@Test
	@Parameters({ "name", "type","CountofQuestion","COmapping"})
	public static void main(String name, String type, String CountofQuestion,String COmapping)
	{
		
		String[] args=new String[10]; 
	    args[0]=(String) "Manage Course";
		Gototab.main(args);
		
		 try {
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Click here to create/edit survey')]")).click();
			} catch (InterruptedException e) {
				e.printStackTrace();
				if(AlertHandling.isAlertPresent())
				{
					LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Click here to create/edit survey')]")).click();
				}
			}
		 
		 try {
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[@name='surveyCreate']")).click();
				Thread.sleep(2000);
				Alert alert = LaunchApp.driver.switchTo().alert();
				 String Data=alert.getText();
				 if(Data.contains("Create?"))
				 {
					 alert.accept();
					 ExceptionHndeler.getScreen(name);
					 
				 }
				 else
				 {
					 System.out.println("Survey to edt ="+Data);
					 Assert.fail("Unable to create the Survey");
				 }
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				if(AlertHandling.isAlertPresent())
				{
					LaunchApp.driver.findElement(By.xpath(".//*[@name='surveyCreate']")).click();
				}
			}
		 
		 
		 
		 
		 int id=GetLettestId();
		 String idtring= String.valueOf(id);
		 System.out.println("Survey to edt ="+idtring);
		 try {    //Selecting the survey type 
				Thread.sleep(1000);
				//LaunchApp.driver.findElement(By.xpath(".//*[@id='"+idtring.trim()+"' and @class='surveyCategory']")).click();
				new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='"+idtring.trim()+"' and @class='surveyCategory']"))).selectByValue(type);
		 } catch (InterruptedException e) {
				e.printStackTrace();
				if(AlertHandling.isAlertPresent())
				{
					LaunchApp.driver.findElement(By.xpath(".//*[@id='"+idtring.trim()+"' and @class='surveyCategory']")).click();
					new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='"+idtring.trim()+"' and @class='surveyCategory']"))).selectByValue(type);
				}
			}
		 
		 if(type.equals("CourseFeedback"))
		 {
		 
		 try {    //Selecting the survey type 
				Thread.sleep(5000);
				//LaunchApp.driver.findElement(By.xpath(".//*[@id='"+idtring.trim()+"' and @class='mapWith']")).click();
				new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='"+idtring.trim()+"' and @class='mapWith']"))).selectByValue("CO");
				Thread.sleep(5000);
		 } catch (InterruptedException e) {
				e.printStackTrace();
				if(AlertHandling.isAlertPresent())
				{
					LaunchApp.driver.findElement(By.xpath(".//*[@id='"+idtring.trim()+"' and @class='mapWith']")).click();
					new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='"+idtring.trim()+"' and @class='mapWith']"))).selectByValue("CO");
				}
			}
		 }
		 
		
		 
		 try {   // Edit survey 
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath(".//*[@href='/Survey/EditSurvey?surveyId="+idtring.trim()+"']")).click();
			} catch (InterruptedException e) {
				e.printStackTrace();
				if(AlertHandling.isAlertPresent())
				{
					LaunchApp.driver.findElement(By.xpath(".//*[@href='/Survey/EditSurvey?surveyId="+idtring.trim()+"']")).click();
				}
			}
		 
		
		 int CountofQuestion1=Integer.parseInt(CountofQuestion);
		 System.out.println("Survey to edt Question="+CountofQuestion1);
		
		for(int i=5;i<=CountofQuestion1;i++)    //Adding Question to survey 
		 {
			 
			 try {   // Add Question to survey 
					Thread.sleep(10000);
					JavascriptExecutor js = ((JavascriptExecutor) LaunchApp.driver);

					js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
					Thread.sleep(5000);
					LaunchApp.driver.findElement(By.xpath(".//*[@id='btnSurveyQuestion']")).click();
					Thread.sleep(2000);
					//LaunchApp.driver.findElement(By.xpath(".//*[@id='selectQueType']")).click();
					Thread.sleep(2000);
					new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='selectQueType']"))).selectByValue("0");
					Thread.sleep(3000);
					LaunchApp.driver.findElement(By.xpath("html/body/div[4]/div[3]/div/button[2]")).click();
					
					Alert alert1 = LaunchApp.driver.switchTo().alert();
					String Data=alert1.getText();
					 if(Data.contains("Question added successfully"))
					 {
						 alert1.accept();
					 }
					 else
					 {
						 Assert.fail("Unable to Add Question to Survey");
					 }
					
				} catch (InterruptedException e) {
					e.printStackTrace();
					if(AlertHandling.isAlertPresent())
					{

						LaunchApp.driver.findElement(By.xpath(".//*[@id='btnSurveyQuestion']")).click();
						
						//LaunchApp.driver.findElement(By.xpath(".//*[@id='selectQueType']")).click();
						
						new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='selectQueType']"))).selectByValue("0");
						
						LaunchApp.driver.findElement(By.xpath("html/body/div[4]/div[3]/div/button[2]")).click();
					}
				}
		 }
		
		try {   // Edit survey Name
			Thread.sleep(10000);
			JavascriptExecutor js = ((JavascriptExecutor) LaunchApp.driver);

			js.executeScript("window.scrollTo(0, 0)");
			Thread.sleep(2000);

			LaunchApp.driver.findElement(By.xpath(".//*[@id='surveyTitle']")).click();
			LaunchApp.driver.findElement(By.xpath(".//*[@id='surveyTitle']/form/input")).clear();
			LaunchApp.driver.findElement(By.xpath(".//*[@id='surveyTitle']/form/input")).sendKeys(name);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			if(AlertHandling.isAlertPresent())
			{
				LaunchApp.driver.findElement(By.xpath(".//*[@id='surveyTitle']")).click();
				LaunchApp.driver.findElement(By.xpath(".//*[@id='surveyTitle']/form/input")).clear();
				LaunchApp.driver.findElement(By.xpath(".//*[@id='surveyTitle']/form/input")).sendKeys(name);
			}
		}
		
		
			try {
			String[] QB=COmapping.split(";");
			String [] Qid = new String[CountofQuestion1];
			List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[@name='surveyQuestionEdit']"));
			  for(int i=0;i<li.size() && i<(CountofQuestion1);i++)
			  { {try { Qid[i]=(li.get(i).getAttribute("id"));} catch (Exception e1) {e1.printStackTrace();}}
			  System.out.println("Demo mayur:"+Qid[i]);
			  }
			 // Arrays.sort(Qid);
			  System.out.println(QB.length+"Demo mayur"+COmapping);
			   for(int i=0;i<QB.length;i++)
			   {
				   Thread.sleep(3000);
				   System.out.println("Demo mayur"+QB[i]);
				   String[] data=QB[i].split(",");
				   System.out.println("Demo mayur:"+data[0]);
				   switch(data[0].trim())
				   {
				   case "Q1": mapCO(data[1],Qid[0]);
					      break;
				   case "Q2":mapCO(data[1],Qid[1]);
					      break;
				   case "Q3":mapCO(data[1],Qid[2]);
					      break;
				   case "Q4":mapCO(data[1],Qid[3]);
					      break;
				   case "Q5":mapCO(data[1],Qid[4]);
				          break;
				   case "Q6":mapCO(data[1],Qid[5]);
						  break;
				   case "Q7":mapCO(data[1],Qid[6]);
						  break;}
			      }}catch (Exception e) {e.printStackTrace();}
		
		try {
			Thread.sleep(10000);
			MooveToElement.moveToElenment(".//*[@id='btnSurveyDone']");
			ExceptionHndeler.getScreen(name+"survay Save");
			Thread.sleep(1000);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='btnSurveyDone']")).click();
		} catch (InterruptedException e) {
			e.printStackTrace();
			if(AlertHandling.isAlertPresent())
			{
				LaunchApp.driver.findElement(By.xpath(".//*[@id='btnSurveyDone']")).click();
			}
		}
		
		try {    //Enabling Survey
			
			Thread.sleep(10000);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+idtring.trim()+"' and @class='surveyActive']")).click();
			Thread.sleep(10000);
		     Alert alert = LaunchApp.driver.switchTo().alert();
			 String Data=alert.getText();
			 if(Data.contains("Survey is active now"))
			 {
				 alert.accept();
				 Thread.sleep(3000);
				 Assert.assertEquals("survey is availabled to student", 1,1);
			 }
			 else
			 {
				 Assert.fail("Unable to create the Survey");
			 }
			} catch (InterruptedException e) {
			e.printStackTrace();
			if(AlertHandling.isAlertPresent())
			{
				LaunchApp.driver.findElement(By.xpath(".//*[@id='"+idtring.trim()+"' and @class='surveyActive']")).click();
			}
		}
		 
		 
		
		
	}
	
	public static int GetLettestId()
	{
	int [] id = new int[100];
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[@class='surveyActive']"));
		System.out.println("Count of survey"+li.size());
		  for(int i=0;i<li.size();i++)
		  {
			  try { 
				  id[i]=Integer.parseInt(li.get(i).getAttribute("id"));
			 
			    } catch (Exception e1) {
					e1.printStackTrace();
				}//getting all Survey  ID 
		  }
		  Arrays.sort(id);
		  int m=(id.length)-1;//picking most recent Id
			 System.out.println("Count "+id[m]);
			 return id[m];
	 }
	
	public static void mapCO(String co_name,String Qid)
	{
		
		try{
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblConcept']")).click();
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@class='dynatree-expander']")).click(); 
			String []hm={"2","0"};                                                
		    hm=co_name.split(":");
		   
		    for(int c=0;c<hm.length;c++)
		    {
		    	int i=Integer.parseInt(hm[c]);
		    	 System.out.println("Co="+hm[c]);
		    	LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@title='"+CORubricData[i][0].trim()+"']")).click();  
		    }
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@class='dynatree-expander']")).click(); 
			}catch (Exception e) {
				ExceptionHndeler.Log("Map Co","Question Editing", e);
			}
			
		}
	

}