package Teacher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.LaunchApp;
import Common.MooveToElement;
import Data.*;


public class ValidateEditQuestion {

	public static void main(String[] args) {
		
		ValidateEditQuestion e=new ValidateEditQuestion();
		e.getQuestinId();
		
	}
	public void getQuestinId()
	{
		//WebElement e=LaunchApp.driver.findElement(By.partialLinkText("Click here to edit this multiple choice question #1"));
	   List <WebElement> li=LaunchApp.driver.findElements(By.xpath("//*[@value='points']"));
	   ArrayList<String> Qid= new ArrayList<String>();;int i=0,count=0;
		String[][] Ass=Read_Data.ReadData(ValidateCreateAssignment.AssignmentQueDoc);
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
		    catch(Exception e)
		    {
		    // e.printStackTrace();
		    }		
	    }
		i=0;
		try
		{
			System.out.println("length="+Qid.size());

		 while(i<count)
				{
			         EditQuestion(Ass[i],Qid.get(i));
			         i++;
				}
		}
		 catch(Exception e) {e.printStackTrace();}
		 
		
	}
	public void EditQuestion(String str[],String Qid)
	{
		int i=0;
		String[] s=new String [2];
		try
		{
		 if(str.length>1)
		while(!str[i].equals("End"))
		{
			System.out.println(str[i]+"   "+Qid);
			try {
				Thread.sleep(5000);
				 s=str[i].split(":");
				 s[0].trim();
				 if(s.length>1)
				 s[1].trim();
			} catch (InterruptedException e) {
				ExceptionHndeler.Log("Data read","Question Editing", e);
			}
			if(s[0].equals("Points"))
			{
				Points(s,Qid);
			}
			if(s[0].equals("Negative Mark Points"))
			{
				NegativeMarks(s,Qid);
			
			}///preceding-sibling::input  name="lblmcqQuestionNegativeMarkPoints"
			if(s[0].equals("Bloom's Category"))
			{
				if(!((ValidateCreateAssignment.assType).equals("Lab")||(ValidateCreateAssignment.assType).equals("External")))
				{
				 MapBloom(s[1],Qid);	
				}
		    }
			if(s[0].equals("Negative Mark Points for Unattempted"))
			{
				NegativeUnattempted(s,Qid);
			}
			
			if(s[0].equals("Course Outcome"))  
			{ 
				if(!AssignmentCreation.IsUniversity.equals("yes"))
				{
					System.out.println("Selecing CO");
				    mapCO(s[1],Qid);	
				    System.out.println("Selecing CO  Selected");
				}
		    }
			if(s[0].equals("Rubric"))
			{ 
				if((ValidateCreateAssignment.assType).equals("Lab"))
				{
				   System.out.println("Selecing Rubric");
				   MapRubric(s[1],Qid);	
				   System.out.println("Selecing Rubric");
				}
		    }
			i++;
		}
		}
		catch(Exception e)
		{
			ExceptionHndeler.Log(str[i],"Question Editing", e);
		}
		
	}
	@SuppressWarnings("deprecation")
	public void mapCO(String co_name,String Qid)
	{
		    String CORubricData[][]=Read_Data.ReadData("CoBloomRubric.csv");
		    MooveToElement.moveToElenment(".//*[@id='"+Qid+"']//*[@name='lblConcept']");
		    ExceptionHndeler.getScreen("ValidateQuestion"+AssignmentCreation.assName);
			String data=LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblConcept']")).getText(); 
			String []hm={"2","0"};
		    hm=co_name.split(";");
		   String str=" ";
		    for(int c=0;c<hm.length;c++)
		    {
		    	if(c<hm.length-1)
		    	 str=str+CORubricData[Integer.parseInt(hm[c])][0].trim()+",";
		    	else
		    	str=str+CORubricData[Integer.parseInt(hm[c])][0].trim();
		    	
		    }
		    System.out.println("Co listed are "+str);
		    if(data.equals(str.trim()))
			{
		    	
				Loger.LogEvent("Co Selected "+data, "-Pass");
				 Assert.assertEquals("Pass- Co Selected "+data, 1, 1);
			}
			else
			{
				String Message="CO Selected "+data+" CO Expected"+str.trim();
			//	//Assert.fail("CO Selected "+data+" CO Expected "+str.trim() + "-Fail");
			}	
			
			
		}
	public void MapBloom(String co_name,String Qid)
	{
		String CORubricData[][]=Read_Data.ReadData("CoBloomRubric.csv");
		String data=LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblObjectives']")).getText(); 
		String []hm={"2","0"};
	    hm=co_name.split(";");
	    String str=" ";
		 for(int c=0;c<hm.length;c++)
		    {
			 str=str+CORubricData[Integer.parseInt(hm[c])][1].trim()+";";
		     }
		    if(data.equals(str.trim()))
			{
				Loger.LogEvent("Bloom Selected "+data, "-Pass");
				Assert.assertEquals("Bloom Selected  "+data, 1, 1);
			}
			else
			{
				String Message="Bloom Selected "+data+" Bloom Expected"+str.trim();
				//	//Assert.fail("Bloom Selected "+data+" Bloom Expected"+str.trim()+ "-Fail");
			}
	}
	
	public void MapRubric(String co_name,String Qid)
	{
		String CORubricData[][]=Read_Data.ReadData("CoBloomRubric.csv");
		String data=LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblRubrics']")).getText(); 
		String []hm={"2","0"};
	    hm=co_name.split(";");
	    String str=" ";
		 for(int c=0;c<hm.length;c++)
		    {
			 str=str+CORubricData[Integer.parseInt(hm[c])][2].trim()+";";
		     }
		    if(data.equals(str.trim()))
			{
				Loger.LogEvent("Rubric Selected "+data, "-Pass");
				Assert.assertEquals("Rubric Selected  "+data, 1, 1);
			}
			else
			{
				String Message="Rubric Selected "+data+" Rubric Expected"+str.trim();
				Loger.LogEvent( Message, "-Fail");
				//	//Assert.fail(Message+ "-Fail");
				
			}
	}
	public static void Points(String [] s,String Qid)
	{
		
		String data=LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lbl"+ValidateCreateAssignment.QuestionType+"QuestionPoints']")).getText();
		    if(data.equals(s[1].trim()))
			{
				Loger.LogEvent("points "+data, "-Pass");
				Assert.assertEquals("points  "+data, 1, 1);
			}
			else
			{
				Loger.LogEvent("points "+data, "-Fail");
				//	//Assert.fail("points "+data+ "-Fail");
			}
		
	}
	public static void NegativeMarks(String [] s,String Qid)
	{
		
		
		String data=LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblmcqQuestionNegativeMarkPoints']")).getText();
	    if(data.equals(s[1].trim()))
		{
			Loger.LogEvent("Negative points "+data, "-Pass");
			Assert.assertEquals("Negative points "+data, 1, 1);
		}
		else
		{
			Loger.LogEvent("negative points "+data, "-Fail");
			//	//Assert.fail("Negative points "+data+ "-Fail");
		}
	}
	public static void NegativeUnattempted(String [] s,String Qid)
	{
			String data=LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblmcqQuestionNegativeMarkPointsforunattempted']")).getText();
		    if(data.equals(s[1].trim()))
			{
				Loger.LogEvent("Negative Unattempted"+data, "-Pass");
				Assert.assertEquals("Negative points "+data, 1, 1);
			}
			else
			{
				Loger.LogEvent("Negative Unattempted"+data, "-Fail");
				//	//Assert.fail("Negative points "+data+ "-Fail");
			}	  
	}
	
	
}

