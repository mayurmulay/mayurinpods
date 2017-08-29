package assignment_Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import Common.AlertHandling;
import Common.ChangeSection;
import Common.Gototab;
import Common.LaunchApp;
import Common.Login;
import Data.ExceptionHndeler;
import Data.Read_Data;

import com.thoughtworks.selenium.SeleneseTestCase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Attempt_test extends Thread  {
	public static int i=0;
	public static String URL1=" ";
	public static String data[][]=Read_Data.ReadData("studentAttendAuto.csv");
	// @Test(threadPoolSize = 2, invocationCount = 2, timeOut = 1000000000)
	 public void testMethod()
	 {
		 String data[][]=Read_Data.ReadData("studentAttendAuto.csv");
		   String []s={"m","n"};
		   String[] b=new String[10];
		   s=data[i][0].split(":");
		   String[][] Data=Read_Data.ReadData("URL.csv");
		 b[0]=Data[0][0];
		  LaunchApp.main(b);
		  Attempt_test t= new Attempt_test();
		  t.start();
		  i++;
		((Attempt_test) t).attemptTest(data[i]);
		
	 }
	@Test
	@Parameters({"TestData","URL"})
	public static void Exicute(String TestData,String URL) 
	{
		System.out.println("TestData="+TestData);
		data=Read_Data.ReadData(TestData.trim());
		 URL1=URL.trim();
    String[] b=new String[10]; 
    Attempt_test t= new Attempt_test();
	  t.start();
	
   for( i = 0; i<data.length; i++)
   {
	   Calendar cal = Calendar.getInstance();
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	   String []s={"m","n"};
	   if(data[i][0].equals("End"))
	   {
		   break;
	   }
	   s=data[i][0].split(":");
	   if(!s[1].equals("Not Started"))
	   {
		   try{
		  (new Attempt_test()).attemptTest(data[i]);
		   if(s[1].startsWith("Submitted"))
		   {
			   String[]m=s[1].split(";");
			   Double time=Double.parseDouble(m[1]);
                System.out.println("time to sleep is"+time);
			  try
			   {
			   Thread.sleep((long) (time*60000)); 
			   String Assname=data[i][1].substring(10, 20)+"_"+data[i][3].substring(10, 15)+"After";
			   ExceptionHndeler.getScreen(Assname);
			   System.out.println("Test Submit time"+sdf.format(cal.getTime()));
			   Thread.sleep((long) (5000));
			   try
			   {
				   JavascriptExecutor js = ((JavascriptExecutor) LaunchApp.driver);
                  js.executeScript("window.scrollTo(0, 0)");
				   LaunchApp.driver.findElement(By.xpath(".//*[@id='bottomPageNavigationContainer']/table/tbody/tr/td[4]/a")).click();
			   }catch(Exception e){System.out.println("Error in submit"+e.getMessage());e.printStackTrace();LaunchApp.driver.findElement(By.xpath(".//*[@id='bottomPageNavigationContainer']/table/tbody/tr/td[4]/a")).click();}
			   Thread.sleep(9000);
			
			   AlertHandling.isAlertPresent();  //This line has added due to bug nymber IN-4192 should be removed once bug get fix 
			   Thread.sleep(4000);
			   LaunchApp.driver.findElement(By.xpath(".//*[@class='ajs-button ajs-ok']")).click();   
			   
			 
		        System.out.println("after constructor");
		        
		   
		        Thread.sleep(5000);
		        LaunchApp.driver.quit();
			   }
			   catch(Exception e){ExceptionHndeler.Log("Submit","Student Attempt", e);}
		   }
		   if(s[1].startsWith("Auto Submitted"))
		   {
			   String[]m=s[1].split(";");
			   Double time=Double.parseDouble(m[1]);
			   ExceptionHndeler.getScreen(data[i][1]);
               System.out.println("time to sleep is"+time);
			  try
			   {
				Thread.sleep((long) (time*60000));
			   //LaunchApp.driver.findElement(By.xpath(".//*[.='Submit']")).click();
			  // Alert alert = LaunchApp.driver.switchTo().alert();
		        System.out.println("after constructor");
		       // alert.accept();
		        System.out.println("Test started time"+sdf.format(cal.getTime()));
		     //   Login.logout();
		        Thread.sleep(5000);
		        LaunchApp.driver.quit();
			   }
			   catch(Exception e){ExceptionHndeler.Log("Attempt ","Student Attempt", e);}
		   }
	   }catch(Exception e){ExceptionHndeler.Log("Attempt ","Student Attempt", e);}
	   }
	   
   }
	}
	public void attemptTest(String[] str)
	{
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try
		{
		String str1;
		int i=1;
		String[] Qid=new String[100];
		while(!(str[i].equals("End")))
		{
			
			try
			{
				Thread.sleep(100);
			String []s={"m","n"};
			s=str[i].split(":");
			System.out.println("attemptTest"+s[0]);
			System.out.println("attemptTest"+s[1]);
			if(s[0].equals("Username"))
			{
				String[]m=s[1].split(";");
				LaunchApp.Execute(URL1);
				Login.main(m);
			}
		if(s[0].equals("Section"))
			{
				ChangeSection.selectSection(s[1].trim());
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.linkText("Dashboard")).click();
				try
				{
					
				//	LaunchApp.driver.findElement(By.xpath("html/body/div[5]/div/div/div[3]/div/button"));
				}catch(Exception e){}
			}
			if(s[0].equals("Test name"))
			{
				Thread.sleep(5000);
				Gototab.execute("Assignments");
				Thread.sleep(5000);
				LaunchApp.driver.findElement(By.linkText(s[1])).click();
			}
			if(s[0].equals("Access Code"))
			{
				try
				{
				if(s[1].equalsIgnoreCase("no"))
				{
					try
					{
						Thread.sleep(5000);
						LaunchApp.driver.findElement(By.xpath(".//*[@id='StartTestLink']")).click();
						Thread.sleep(1000);
					}catch(Exception e){//e.printStackTrace();
						}
				}
				else
				{
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.xpath("//body")).sendKeys(Keys.F5);
				Thread.sleep(5000);
				LaunchApp.driver.findElement(By.xpath(".//*[@id='secretEntry']")).sendKeys(s[1]);
				Thread.sleep(5000);
				LaunchApp.driver.findElement(By.xpath(".//*[@id='UnlockTestWithSecret']")).click();
				Thread.sleep(5000);
				}
				System.out.println("Test started time Enterd key"+sdf.format(cal.getTime()));
				System.out.println("Data"+" "+str[1]+""+str[1].substring(10, 20));
				System.out.println("Data"+" "+str[3]+""+str[3].substring(10, str[3].length()));
				String Assname=str[1].substring(10, 20)+"_"+str[3].substring(15,str[3].length()-1)+"Before";
				
				ExceptionHndeler.getScreen(Assname);
				}catch(Exception e){e.printStackTrace();}
				
			}
			if(s[0].equals("Activity type"))
			{
			if(s[1].equals("Multiple Choice"))  
			{
				System.out.println("In student Attempt "+sdf.format(cal.getTime()));
				Thread.sleep(20000);
				Student_MCQ_Test.MCQ_Test(str);
			}
			if(s[1].equals("SQ"))  
			{
				Thread.sleep(20000);
				Student_SQ_Test.SQ_Test(str);
			}
			if(s[1].equals("DQ"))  
			{
				Thread.sleep(20000);
				Student_SQ_Test.SQ_Test(str);
			}
			break;
			}
			i++;
			}
			catch(Exception e){e.printStackTrace();}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		}catch(Exception e){ExceptionHndeler.Log("Error","Attempt test", e);}
		}
			
	public static void main(String k[]) 
	{
		//Exicute("http://ec2-54-234-84-132.compute-1.amazonaws.com/");
	}
	
}

