package Teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.AlertHandling;
import Common.Clickevent;
import Common.Gototab;
import Common.LaunchApp;
import Data.ExceptionHndeler;
import Data.Loger;
import Data.Read_Data;

public class CreateOptionalAssignment {
	
	public static void main(String str[])
	{
		
	}
	
	public static void CreateAssignemnt(String[] Data)
	{
	try{
		Gototab.execute("Manage Course");
		Thread.sleep(4000);
		Thread.sleep(4000);
		int counter=0;
		while((Data[counter]!=null))
		{
			String [] AssData=Data[counter].split(":");
			if(AssData[0].trim().equals("AssType"))
			{
				CreateAssignment.execute(AssData[1].trim()); 
				AssignmentCreation.assType=AssData[1].trim();
				if(!AssignmentCreation.assType.equals("External"))
				{AssignmentCreation.AssignmentType(AssData);}
				
			}
			if(AssData[0].trim().equals("AssName"))
			{
				AssignmentCreation.Name(AssData);
				
			}
			
			if(AssData[0].trim().equals("SetCount"))
			{
				int SetCount=Integer.parseInt(AssData[1].trim());
				for(int i=0;i<SetCount;i++)
				{
					EditActivity.createActivity("Discussion Questions");
					
					Thread.sleep(5000);
					try {LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click(); }catch(Exception e2){}Thread.sleep(5000);
				}
			}
			if(AssData[0].trim().equals("Set"))
			{
			  List <WebElement> li= LaunchApp.driver.findElements(By.xpath(".//*[@name='addQuestion']"));
			  int elementCounter=0;
			  while(li.size()>elementCounter)
			  {
			   try{
					 
					  WebElement e=li.get(elementCounter);
					  Thread.sleep(3000);
					  e.findElement(By.xpath(".//*[@name='lblActivitySubTypeName']")).click();
					  counter++;
					  Thread.sleep(3000);
					   String[] SetData=Data[counter].split(":");
					   String optionalSet=SetData[0].trim();
					   String optionalSetGroup=SetData[1].trim();
					   String optionalSetGroupSetCount=SetData[2].trim();
					   String optionalQuestion=SetData[3].trim();
					   String OptionalQuestionCount=SetData[4].trim();
					   String Marks=SetData[5].trim();
					  if(optionalSet.equalsIgnoreCase("y"))
					  {
						 e.findElement(By.xpath(".//*[@name='chkOptionalQuestionSets']")).click();
						  Thread.sleep(5000);
						  
						 e.findElement(By.xpath(".//*[@name='lblOptionalQuestionSetNumber' and @value='Click to edit']")).click();
						  Thread.sleep(5000);
						  
						 e.findElement(By.xpath(".//*[@name='lblOptionalQuestionSetNumber'and @value='Click to edit']/form/input")).sendKeys(optionalSetGroup);
						  Thread.sleep(5000);
						  
						  
						 e.findElement(By.xpath(".//*[@name='tdOptionalQuestionSetNumber']")).click();
						  Thread.sleep(5000);
						  
						 e.findElement(By.xpath(".//*[@name='lblOptionalQuestionSetCount' and @value='Click to edit']")).click();
						  Thread.sleep(5000);
						  String Count="0";
						  Count= e.findElement(By.xpath(".//*[@name='lblOptionalQuestionSetCount'and @value='Click to edit']/form/input")).getText();
						   System.out.println("Count="+Count);
						  if(Count!="" || Count!=null)
						  { 
							  e.findElement(By.xpath(".//*[@name='lblOptionalQuestionSetCount'and @value='Click to edit']/form/input")).clear();
						      e.findElement(By.xpath(".//*[@name='lblOptionalQuestionSetCount'and @value='Click to edit']/form/input")).sendKeys(optionalSetGroupSetCount);
						      Thread.sleep(5000);
						  }
					   } 
						 
					   if(optionalQuestion.equalsIgnoreCase("y"))
						{
							 e.findElement(By.xpath(".//*[@name='chkOptionalQuestions']")).click();
							  Thread.sleep(5000);
							  
							 e.findElement(By.xpath(".//*[@name='lblOptionalQuestionsCount' and @value='Click to edit']")).click();
							  Thread.sleep(5000);
							  
							 e.findElement(By.xpath(".//*[@name='lblOptionalQuestionsCount'and @value='Click to edit']/form/input")).sendKeys(OptionalQuestionCount);
							  Thread.sleep(5000);
						}
						  
						 Setmarks(Marks,e);
						 Thread.sleep(5000);
						 EditQuestionforCurrentActivity(e);
						 Thread.sleep(3000);
						try{ e.findElement(By.xpath(".//*[@name='lblActivitySubTypeName']")).click();}catch(Exception e1){ExceptionHndeler.Log("create optional assignment", "externanl Ass", e1);}
					  //}
				}
			   catch(Exception e){ExceptionHndeler.Log("create optional assignment", "Edit Activity", e);e.printStackTrace();}
			   elementCounter++;
			  }
				
			}
			
			
			counter++;
		}
		
		JavascriptExecutor js = ((JavascriptExecutor) LaunchApp.driver);
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
	     System.out.println("save6");
	     Thread.sleep(3000);
		}
		catch(Exception e){ExceptionHndeler.Log("create optional assignment", "externanl Ass", e);}
		
		
	}
    
	@Test
	@Parameters({ "filename", "Testcasename"})
	public static void exicute(String filename,String Testcasename)
    {
    	String[][] Data=Read_Data.ReadData(filename);
    	int counter=0;
    	while(Data[counter][0]!=null)
    	{
    		try{
    	  if(Data[counter][0].trim().equals(Testcasename))
    	  {
    		  CreateAssignemnt(Data[counter]);
    		  break;
    	  }
    	  counter++;
    	} catch(Exception e){continue;}  }
    }
 
    public static void Setmarks(String Marks,WebElement e1)
    {
    	try
		{
		Loger.LogEvent("Points","Points selecting");
		WebElement e=e1.findElement(By.xpath(".//*[@name='lblDqQuestionPoints']"));
		Clickevent.ClickEvent(e);
		System.out.println(".//*[@name='lblDqQuestionPoints']//*[@name='value']");
		e1.findElement(By.xpath(".//*[@name='lblDqQuestionPoints']//*[@name='value']")).clear();
		e1.findElement(By.xpath(".//*[@name='lblDqQuestionPoints']//*[@name='value']")).sendKeys(Marks);
		try{Thread.sleep(3000);
		e1.findElement(By.xpath(".//*[@name='lblDqQuestionPoints']//*[@name='value']")).sendKeys(Keys.ENTER);}catch(Exception e2){}
        Loger.LogEvent("Points","Points selected "+Marks);//Keys.ENTER
       // AlertHandling.isAlertPresent();
    	//try {LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click(); }catch(Exception e2){}
    	Thread.sleep(3000);
        AlertHandling.isAlertPresent();
		}catch(Exception e){e.printStackTrace();AlertHandling.isAlertPresent();}
    }

    public static void EditQuestionforCurrentActivity(WebElement e1)
    {
    	String id=e1.getAttribute("id");
    	System.out.println("Editing Activity="+id);
    	e1=LaunchApp.driver.findElement(By.xpath(".//*[@id='"+id+"']"));
    	List <WebElement> li=e1.findElements(By.xpath(".//*[@name='lblDqQuestionPoints']"));
		ArrayList<String> Qid= new ArrayList<String>();
		int i=0,count=0;
		String[][] Ass=Read_Data.ReadData("DQQuesOptional.csv");
		System.out.println(li.size());
		for(WebElement element : li){
			try
			{
			
	            String s=element.getAttribute("id");
	            if(!s.equals(""))
	            {
	            	Qid.add(s);
		           System.out.println("id="+s);
		           count++;
	            }
	        }
		    catch(Exception e) { e.printStackTrace();}		
	    }
		Collections.sort(Qid);
		 while(i<count)
				{
			      try{
			      (new EditQuestion()).EditQuestion(Ass[i],Qid.get(i));
			      i++;
			      } catch(Exception e) {AlertHandling.isAlertPresent();}	
			       
				}
    }

}
