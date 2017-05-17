package Teacher;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Loger;
import Data.Read_Data;


public class EditActivity {
	
	
	public static void main(String[] args) {
		
		String[][] str=Read_Data.ReadData(AssignmentCreation.AssignmentTypeDoc);
		(new EditActivity()).editActivity(str[0]);
		EditQuestion.main(args);
	}
	public static void createActivity(String type)
	{
		LaunchApp.driver.findElement(By.partialLinkText("+Add question set")).click();
		try {
			Thread.sleep(100);
			new Select(LaunchApp.driver.findElement(By.name("cbxActivityType"))).selectByVisibleText(type);
		}
		catch (Exception e) {
			System.out.println("in catche model dilog box");
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='cbxActivityType']"))).selectByVisibleText(type);
			MooveToElement.moveToElenment(".//*[@name='cbxActivityType']");
			ExceptionHndeler.Log("Activity Adding","Assignment Creation", e);
		}
			
	}
	public void editActivity(String [] str)
	{
		System.out.println("in editActivity");
		int i=0;
		String [] s={"m","mk"};
		str[0].trim();
		(new EditActivity()).createActivity(str[0]);
		
		try {
			Thread.sleep(100);
			LaunchApp.driver.findElement(By.name("lblActivitySubTypeName")).click();
		} catch (Exception e) {
			System.out.println("in catche model dilog box");
			LaunchApp.driver.findElement(By.name("lblActivitySubTypeName")).click();
			ExceptionHndeler.Log("Sub Activity Adding","Assignment Creation", e);
		}
		
		System.out.println("Assignment type="+AssignmentCreation.assType);
        if(!(AssignmentCreation.assType.equals("External")) && !(AssignmentCreation.assType.equals("Lab")))
		while(!str[i].equals("End"))
		{
			try{
			
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
		/*	if(s[0].equals("Stem"))
			{
				LaunchApp.driver.findElement(By.xpath(".//*[@name='lblStem']")).click();
				try {
					Thread.sleep(10);
				LaunchApp.driver.findElement(By.xpath(".//*[@name='value']")).sendKeys(s[1]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(s[0].equals("Instructions"))
			{
				LaunchApp.driver.findElement(By.xpath(".//*[@name='lblInstuctions']")).click();
				try {
					Thread.sleep(10);
				LaunchApp.driver.findElement(By.xpath(".//*[@class='nicEdit-main']")).sendKeys(s[1]);
				LaunchApp.driver.findElement(By.xpath(".//*[@type='submit']")).click();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  Display order of right column
			}*/
			if(s[0].equals("Display order of right column"))
			{
				DisplayOrder(s);
							}
			
			if(s[0].equals("Sharing"))
			{
				Sharing(s);
			}
			if(s[0].equals("Shuffle Questions"))
			{
				ShuffleQuesions(s);
			}
			if(s[0].equals("Shuffle Options"))
			{
				ShuffleOptions(s);
			}
			i++;
			}
			catch(Exception e){e.printStackTrace();}
		}
	}
	public static void AssessmentMode(String []s)
	{
		Loger.LogEvent("Assessment Mode","Assessment Mode selecting");
		LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssessmentMode']")).click();
		try {
			Thread.sleep(100);
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='value']"))).selectByVisibleText(s[1]);
		} catch (InterruptedException e) {
			LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssessmentMode']")).sendKeys(s[1]);
		}
		Loger.LogEvent("Assessment Mode","Assessment Mode selected "+s[1]);
	}
	public static void DisplayOrder(String [] s)
	{
		String []hm=s[1].split(";");
		String  data="";
		Loger.LogEvent("Display order of right column","Display order of right column selecting");
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
	    LaunchApp.driver.findElement(By.xpath(".//*[@name='lblDisplayOrder']")).click();
	    LaunchApp.driver.findElement(By.xpath(".//*[@name='lblDisplayOrder']//*[@name='value']")).clear();
	    LaunchApp.driver.findElement(By.xpath(".//*[@name='lblDisplayOrder']//*[@name='value']")).sendKeys(data);
	    Loger.LogEvent("Display order of right column","Display order of right column selected"+s[1]);
	}
	public static void Sharing(String [] s)
	{
		Loger.LogEvent("Sharing","Sharing selecting");
		LaunchApp.driver.findElement(By.xpath(".//*[@name='lblSharing']")).click();
		try {
			Thread.sleep(100);
			LaunchApp.driver.findElement(By.xpath(".//*[@name='value']")).click();
		new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='value']"))).selectByVisibleText(s[1]);
		} catch (Exception e) {
			LaunchApp.driver.findElement(By.xpath(".//*[@name='lblSharing']")).sendKeys(s[1]);
           e.printStackTrace();
		}
		Loger.LogEvent("Sharing","Sharing selected "+s[1]);
	}
	public static void ShuffleQuesions(String [] s)
	{
		Loger.LogEvent("Shuffle Questions","Shuffle Questions selecting");
		if(s[1].equals("yes"))
		{
		try {
			Thread.sleep(50);
		LaunchApp.driver.findElement(By.xpath(".//*[@name='chkShuffleQuestions']")).click();
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		Loger.LogEvent("Shuffle Questions","Shuffle Questions selected "+s[1]);
	}
	public static void ShuffleOptions(String [] s)
	{
		Loger.LogEvent("Shuffle Options","Shuffle Options selecting");
		if(s[1].equals("yes"))
		{
		try {
			Thread.sleep(10);
		LaunchApp.driver.findElement(By.xpath(".//*[@name='chkShuffleOptions']")).click();
		
		} catch (InterruptedException e) {
			ExceptionHndeler.Log("Shuffle Question","Activity Editing", e);
			 e.printStackTrace();
			
		}
		}
		Loger.LogEvent("Shuffle Options","Shuffle Options selected"+s[1]);
	}

}