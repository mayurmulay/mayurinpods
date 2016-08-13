package Teacher;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import Common.LaunchApp;
import Data.ExceptionHndeler;
import Data.Loger;
import Data.Read_Data;


public class ValidateEditActivity {

	
	public static void main(String[] args) {
		
		String[][] str=Read_Data.ReadData(ValidateCreateAssignment.AssignmentTypeDoc);
		try{
		(new ValidateEditActivity()).editActivity(str[0]);}catch(Exception e){e.printStackTrace();}
		ValidateEditQuestion.main(args);
	}
	
	public void editActivity(String [] str)
	{
		System.out.println("in editActivity");
		int i=0;
		String [] s={"m","mk"};
		str[0].trim();
		
		try {
			Thread.sleep(100);
			LaunchApp.driver.findElement(By.name("lblActivitySubTypeName")).click();
		} catch (Exception e) {
			System.out.println("in catche model dilog box");
			LaunchApp.driver.findElement(By.name("lblActivitySubTypeName")).click();
			ExceptionHndeler.Log("Sub Activity Adding","Assignment Creation", e);
		}
		
		System.out.println("Assignment type="+ValidateCreateAssignment.assType);
        if(!(ValidateCreateAssignment.assType.equals("External")) && !(ValidateCreateAssignment.assType.equals("Lab")))
		while(!str[i].equals("End"))
		{
			try{
			System.out.println("mayur"+str[i]);
			try {
				Thread.sleep(100);
				 s=str[i].split(":");
				 s[0].trim();
				 if(s[1].equals(null))
				 s[1].trim();
			} catch (Exception e) {
				ExceptionHndeler.Log("String reading","Activity Editing", e);
				i++;
			}
			if(s[0].equals("Assessment Mode"))
			{
				AssessmentMode(s);
			}
		
			if(s[0].equals("Display order of right column"))
			{
				DisplayOrder(s);
							}
			
			if(s[0].equals("Sharing"))
			{
				Sharing(s);
			}
			i++;
			}
			catch(Exception e){e.printStackTrace();}
		}
	}
	public static void AssessmentMode(String []s)
	{
		String data=LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssessmentMode']")).getText();
		if(data.equals(s[1].trim()))
		{
			Loger.LogEvent("Assessment Mode"+data, "-Pass");
		}
		else
		{
			Loger.LogEvent("Assessment Mode"+data, "-Fail");
		}	
	}
	public static void DisplayOrder(String [] s)
	{
		String []hm=s[1].split(";");
		String  data="";
	    for(int m=0;m<hm.length;m++)
	    {
	    	if(m==0)
	    	{
	    	data=hm[m]; //lblDisplayOrder
	    	continue;
	    	}
	    	if(m<hm.length-1 && m>0)
	    	{
	    	data=data+","+hm[m]; //lblDisplayOrder
	    	}
	    	if(m==hm.length-1)
	    	{
	    		data=data+","+hm[m];
	    	}
	    }
	    String data1= LaunchApp.driver.findElement(By.xpath(".//*[@name='lblDisplayOrder']")).getText();
	    if(data1.equals(data.trim()))
		{
			Loger.LogEvent("Sequince"+data, "-Pass");
		}
		else
		{
			Loger.LogEvent("Sequince"+data, "-Fail");
		}	
	}
	public static void Sharing(String [] s)
	{
		String data=LaunchApp.driver.findElement(By.xpath(".//*[@name='lblSharing']")).getText();
		if(data.equals(s[1].trim()))
		{
			Loger.LogEvent("Sharing"+data, "-Pass");
		}
		else
		{
			Loger.LogEvent("Sharing"+data, "-Fail");
		}
		
	}
	

}


