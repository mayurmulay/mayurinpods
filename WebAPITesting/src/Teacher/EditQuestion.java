package Teacher;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Clickevent;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Loger;
import Data.Read_Data;


public class EditQuestion {
	public String CORubricData[][]=Read_Data.ReadData("CoBloomRubric.csv");

	public static void main(String[] args) {
		
		EditQuestion e=new EditQuestion();
		e.getQuestinId();
		
	}
	public void getQuestinId()
	{
		//WebElement e=LaunchApp.driver.findElement(By.partialLinkText("Click here to edit this multiple choice question #1"));
	   if(AssignmentCreation.AssignmentQueDoc.equals("DQ_Question.csv") ||AssignmentCreation.AssignmentQueDoc.equals("MCQ_Question.csv"))
	   {
		   LaunchApp.driver.findElement(By.xpath(".//*[@name='btnAddQuestion']")).click();
		   LaunchApp.driver.findElement(By.xpath(".//*[@name='btnAddQuestion']")).click();
		}
	   List <WebElement> li=LaunchApp.driver.findElements(By.xpath("//*[@value='points']"));
		ArrayList<String> Qid= new ArrayList<String>();
		int i=0,count=0;
		String[][] Ass=Read_Data.ReadData(AssignmentCreation.AssignmentQueDoc);
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
		    	 e.printStackTrace();
		    }		
	    }
		i=0;
		try
		{
			System.out.println("length="+Qid.size());
			Collections.sort(Qid);
		 while(i<count)
				{
			         EditQuestion(Ass[i],Qid.get(i));
			         i++;
				}
		}
		 catch(Exception e) {e.printStackTrace();}
		 try {
				Thread.sleep(1000);
				System.out.println("page getting refresh");
				//try {LaunchApp.driver.navigate().refresh(); }catch(Exception e) {e.printStackTrace();}
			Thread.sleep(3000);
			
			ExceptionHndeler.getScreen("Creating Assignment Save"+AssignmentCreation.assName);
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
		     System.out.println("save6");
		 }
		 catch(Exception e)
		 {ExceptionHndeler.Log("Save Button","Question Editing", e); e.printStackTrace();}
		 try
		 {
			 Alert alert = LaunchApp.driver.switchTo().alert();
			 String msg=alert.getText();
			   alert.accept();
			   Loger.LogException("Error while creating assignmetnt","Saving assignment", msg);
		 }
		 catch(Exception e)
		 { //ExceptionHndeler.Log("Save Button","Question Editing", e);}
		 }
		
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
				Thread.sleep(2000);
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
				if(!((AssignmentCreation.assType).equals("Lab")||(AssignmentCreation.assType).equals("External")))
				{
				 MapBloom(s[1],Qid);	
				}
		    }
			if(s[0].equals("Negative Mark Points for Unattempted"))
			{
				NegativeUnattempted(s,Qid);
			}
			if(s[0].equals("CorrectAnswer"))
			{
				CorrectAns(s,Qid);
		
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
			if(s[0].equals("Select Rubrics"))
			{ 
				if((AssignmentCreation.assType).equals("Lab"))
				{
				   MapRubric(s[1],Qid);	
				}
		    }
			i++;
		}
		}
		catch(Exception e)
		{
			ExceptionHndeler.Log(str[i],"Question Editing", e); 
			e.printStackTrace();
		}
		
	}
	public void mapCO(String co_name,String Qid)
	{
		
		try{
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblConcept']")).click();
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@class='dynatree-expander']")).click(); 
			String []hm={"2","0"};
		    hm=co_name.split(";");
		   
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
	public void MapBloom(String co_name,String Qid)
	{
		//*[@id='2275']//*[@class='ui-multiselect ui-widget ui-state-default ui-corner-all' ]
		try{
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@class='ui-multiselect ui-widget ui-state-default ui-corner-all' ]")).click();
			String []hm={"2","0"};
		    hm=co_name.split(";");
		    for(int c=0;c<hm.length;c++)
		    {
		    	int i=Integer.parseInt(hm[c]);
		    	LaunchApp.driver.findElement(By.xpath("//*[@name='multiselect_"+Qid+"' and @title='"+CORubricData[i][1].trim()+"']")).click();  
		    	LaunchApp.driver.findElement(By.xpath("//*[@id='"+Qid+"']/tr[3]/td[2]/button")).click();  
		    	
		    }
		 
			}catch (Exception e) {
				ExceptionHndeler.Log("Map Bloom","Question Editing", e);
			}
		//LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@class='ui-multiselect ui-widget ui-state-default ui-corner-all ui-state-active ui-state-focus' ]")).click();
		}
	
	public void MapRubric(String co_name,String Qid)
	{
		
		try{
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']/tr[3]/td[2]/button")).click();
			String []hm={"2","0"};
		    hm=co_name.split(";");
		    for(int c=0;c<hm.length;c++)
		    {
		    	int i=Integer.parseInt(hm[c]);
		    	LaunchApp.driver.findElement(By.xpath("//*[@name='multiselect_"+Qid+"' and @title='"+CORubricData[i][2].trim()+"']")).click();  
		    }
		 
			}catch (Exception e) {
				ExceptionHndeler.Log("Map Rubric","Question Editing", e);
			}
		LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']/tr[3]/td[2]/button")).click();
		}
	public static void Points(String [] s,String Qid)
	{
		try
		{
		Loger.LogEvent("Points","Points selecting");
		System.out.println(".//*[@id='"+Qid+"']//*[@name='lbl"+AssignmentCreation.QuestionType+"QuestionPoints']");
		WebElement e=LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lbl"+AssignmentCreation.QuestionType+"QuestionPoints']"));
		Clickevent.ClickEvent(e);
		System.out.println(".//*[@id='"+Qid+"']//*[@name='value']");
		LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='value']")).clear();
		System.out.println(".//*[@id='"+Qid+"']//*[@name='lbl"+AssignmentCreation.QuestionType+"QuestionPoints']="+s[1]);
        LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='value']")).sendKeys(s[1]);
        Loger.LogEvent("Points","Points selected "+s[1]);
		}catch(Exception e){e.printStackTrace();}
	}
	public static void NegativeMarks(String [] s,String Qid)
	{
		
		Loger.LogEvent("Negative Mark Points","Negative Mark Points selecting");
		LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblmcqQuestionNegativeMarkPoints']")).click();
		LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblmcqQuestionNegativeMarkPoints']//*[@name='value']")).clear();
		//LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblmcqQuestionNegativeMarkPoints']/child::input")).clear();
		LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblmcqQuestionNegativeMarkPoints']//*[@name='value']")).sendKeys(s[1]);
		Loger.LogEvent("Negative Mark Points","Negative Mark Points selected "+s[1]);
	}
	public static void NegativeUnattempted(String [] s,String Qid)
	{
		
		Loger.LogEvent("Negative Mark Points for Unattempted","Negative Mark Points for Unattempted selecting");
		  LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblmcqQuestionNegativeMarkPointsforunattempted']")).click();
		  LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblmcqQuestionNegativeMarkPointsforunattempted']//*[@name='value']")).clear();
		  LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@name='lblmcqQuestionNegativeMarkPointsforunattempted']//*[@name='value']")).sendKeys(s[1]);
		  Loger.LogEvent("Negative Mark Points for Unattempted","Negative Mark Points for Unattempted selected"+s[1]);
	}
	public static void CorrectAns(String [] s,String Qid)
	{
		
		Loger.LogEvent("CorrectAnswer","CorrectAnswer selecting");
		 LaunchApp.driver.findElement(By.xpath(".//*[@id='"+Qid+"']//*[@choice='"+s[1]+"']")).click();
		 Loger.LogEvent("CorrectAnswer","CorrectAnswer selected"+s[1]);
	}
	
	
}
 class ViewComparator implements Comparator {

	

	@Override
	public int compare(Object o1, Object o2) {
		String m=((Alert) o1).getText();
	      String n=((Alert) o2).getText();
	      return(m.compareTo(n));
	}


	

	
	
}
