package otherScript;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Gototab;
import Common.LaunchApp;
import Common.Login;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Read_Data;

public class CreateLesson {
	public static void main(String str[])
	{
		
	
		LaunchApp.Execute("http://testing.inpods.com:88/");
		Login.LoginD("sd@inpods.com","t1t1t1");
		
		String[][] Lesson=Read_Data.ReadData("lesson.csv");
		
		for(int i=0;i<1000;i++)
		{
			String Chapter[]=Lesson[i];
			String[] args=new String[10]; 
		   args[0]=(String) "Lessons";
		    Gototab.main(args);
		    
			int j=0;
			while(true)	
			{
				String[] data=Chapter[j].split("@");
				if(data[0].equals("Lesson"))
				{
					CreateLesson(data[1].trim(),"link","http://demo.inpods.com/");
					break;
				}
				else
				{
					CreateChapter(data[0].trim());
				}
				j++;
			}
			
		}
	}
	
	public static void CreateChapter(String Chapter)
	{
		
		
		if(LaunchApp.driver.findElements(By.linkText(Chapter.trim())).size() == 0)
		{
			try {
		         LaunchApp.driver.findElement(By.linkText("Add New Chapter")).click();
		         LaunchApp.driver.findElement(By.xpath(".//*[@id='Name']")).sendKeys(Chapter);
	             Thread.sleep(2000);
		        } catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			ExceptionHndeler.Log("Alert","Create lesson", e1);
		           }
			MooveToElement.moveToElenment(".//*[@id='Name']");
		ExceptionHndeler.getScreen("Creating Chapter"+Chapter);
	      	LaunchApp.driver.findElement(By.xpath("html/body/div[4]/div[3]/div/button[1]")).click();   
	//	LaunchApp.driver.findElement(By.linkText("Enable")).click();
		}
		try
		{
			Thread.sleep(1000);
			//LaunchApp.driver.switchTo().alert().accept();
			LaunchApp.driver.findElement(By.linkText(Chapter.trim())).click();
		}
		catch(Exception e)
		{
			ExceptionHndeler.Log("Alert","Create lesson", e);
		}
	}
	@Test
	@Parameters({ "lesson", "type", "data"})
	public static void CreateLesson(String lesson,String type,String data)
	{
		try
		{
			String[] args=new String[10]; 
		 /*   args[0]=(String) "Lessons";
			Gototab.main(args);
		if(LaunchApp.driver.findElements(By.linkText(Chapter.trim())).size() == 0)
		{
			System.out.println("Lesson is not present");
		  CreateChapter(Chapter.trim());
		   Thread.sleep(3000);
	     	if(level.trim().equals("Chapter->Chapter->Lesson"))
	    	{
	     		System.out.println("Creating Chapter Under Chapter");
			LaunchApp.driver.findElement(By.linkText(Chapter.trim())).click();
			Chapter=Chapter.trim()+"1";
			CreateChapter(Chapter.trim());
			 Thread.sleep(2000);
		    }
		}
		LaunchApp.driver.findElement(By.linkText(Chapter.trim())).click();
		Thread.sleep(1000);*/
		LaunchApp.driver.findElement(By.linkText("Add New Lesson")).click();
		Thread.sleep(3000);
		LaunchApp.driver.findElement(By.xpath(".//*[@id='Name']")).clear();
		Thread.sleep(3000);
		LaunchApp.driver.findElement(By.xpath(".//*[@id='Name']")).sendKeys(lesson.trim());
		Thread.sleep(3000);
		if(type.equals("link"))
		{
		   LaunchApp.driver.findElement(By.xpath(".//*[@id='LinkAddress1']")).sendKeys(data.trim());
		   Thread.sleep(3000);
		//   try { if(!(Cos.equals("null1")))  {  Secect_Co(Cos);  }} catch(Exception e) {	ExceptionHndeler.Log("Alert","Create lesson Co mapping ", e);}
		   MooveToElement.moveToElenment(".//*[@id='LinkAddress1']");
		   ExceptionHndeler.getScreen("Creating lesson"+lesson);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='frmSaveLesson']/input[15]")).click();
			Thread.sleep(3000);
		   
	    }
		if(type.equals("Upload"))
		{
		// try { if(!(Cos.equals("null1")))  {  Secect_Co(Cos);  }} catch(Exception e) {	ExceptionHndeler.Log("Alert","Create lesson Co mapping ", e);}
			
	      new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='SelectedLessonPartType1']"))).selectByVisibleText(type);
		  Thread.sleep(5000);
		  WebElement element= LaunchApp.driver.findElement(By.xpath(".//*[@name='UploadFile1']")); 
		  element.sendKeys(data.trim()); 
		 // Runtime.getRuntime().exec(data.trim());
		 
			Thread.sleep(5000);
			System.out.println("lesson saving");
			Thread.sleep(300);
			MooveToElement.moveToElenment(".//*[@name='UploadFile1']");
			ExceptionHndeler.getScreen("Creating lesson"+lesson);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='frmSaveLesson']/input[15]")).sendKeys(Keys.ENTER);
			//LaunchApp.driver.findElement(By.xpath(".//*[@id='frmSaveLesson']/input[15]")).click();
			System.out.println("Uploading");
			Thread.sleep(10000);
			System.out.println("Uploading2");
		}
		if(type.equals("Auther"))
		{
			//not aplicable 
		}
	//	try { if(!(Cos.equals("null1")))  {  Secect_Co(Cos);  }} catch(Exception e) {	ExceptionHndeler.Log("Alert","Create lesson Co mapping ", e);}
		
		    while(LaunchApp.driver.findElements(By.linkText(lesson.trim())).size() == 0)
		     {
		       Thread.sleep(3000);
		        System.out.println("Uploading data ");
		     }
			WebDriverWait wait = new WebDriverWait(LaunchApp.driver, 6000);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(lesson.trim())));
			MooveToElement.moveToElenment(".//*[.='"+lesson.trim()+"']");
			ExceptionHndeler.getScreen("Lesson_Created"+lesson);
			if(LaunchApp.driver.findElements(By.linkText(lesson.trim())).size() > 0)
			{
				Assert.assertTrue(true, "User able to create");
			}
		  // LaunchApp.driver.findElement(By.linkText("Enable")).click();
		   try
	     	{
			//LaunchApp.driver.switchTo().alert().accept();
		    }
		    catch(Exception e)
	     	{
			ExceptionHndeler.Log("Alert","Create lesson", e);
			//e.printStackTrace();
	    	}
		}
		catch(Exception e)
		{
			ExceptionHndeler.Log("Alert","Create lesson", e);
			Assert.fail("Not able to create the lesson "+lesson);
		//	e.printStackTrace();
		}
		
	}
	public static void Secect_Co(String Cos)
	{
		String CoSelected = null;
		try
		{
			Thread.sleep(10000);
			try{
		 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblConcept']")).click();}catch(Exception e){}
		 LaunchApp.driver.findElement(By.xpath("//*[@class='dynatree-expander']")).click();
		 String []hm={"2","0"};
		 hm=Cos.split(";");
		  
		    for(int c=0;c<hm.length;c++)
		    {
		    	if(c==(hm.length-1))
		    	{CoSelected=CoSelected+hm[c]+",";}
		    	else{CoSelected=CoSelected+hm[c];}
		    	 System.out.println("Co="+hm[c]);
		    	LaunchApp.driver.findElement(By.xpath(".//*[.='"+hm[c]+"']")).click();  
		    }
		 
		}
		catch(Exception e)
		{
			ExceptionHndeler.Log("Alert","Create lesson", e);
			//Assert.fail("Not able to Map CO to create the lesson ");
			e.printStackTrace();
		}
		//Assert.assertEquals(CoSelected,LaunchApp.driver.findElement(By.xpath(".//*[@name='lblConcept']")).getText());
	}
		
	

}