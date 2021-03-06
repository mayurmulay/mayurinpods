package assignment_Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import Common.ChangeSection;
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
	public static String URL=" ";
	public static String data[][]=Read_Data.ReadData("studentAttend.csv");
	 @Test(threadPoolSize = 2, invocationCount = 2, timeOut = 1000000000)
	 public void testMethod()
	 {
		 String data[][]=Read_Data.ReadData("studentAttend.csv");
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
	@Parameters("TestData")
	public static void Exicute(String TestData) 
	{
		data=Read_Data.ReadData("studentAttend.csv");
		 URL=TestData;
    String[] b=new String[10]; 
    String[][] Url=Read_Data.ReadData("URL.csv");
	 b[0]=Url[0][0];
    Attempt_test t= new Attempt_test();
	  t.start();
   for( i = 0; i<data.length; i++)
   {
	   // ;;LaunchApp.main(b);
	   String []s={"m","n"};
	   s=data[i][0].split(":");
	   if(!s[1].equals("Not Started"))
	   {
		  (new Attempt_test()).attemptTest(data[i]);
		   if(s[1].startsWith("Submitted"))
		   {
			   String[]m=s[1].split(";");
			   Double time=Double.parseDouble(m[1]);
                System.out.println("time to sleep is"+time);
			  try
			   {
			   Thread.sleep((long) (time*60000));
			   LaunchApp.driver.findElement(By.linkText("Submit")).click();
			   Alert alert = LaunchApp.driver.switchTo().alert();
		        System.out.println("after constructor");
		        alert.accept();
		        Login.logout();
		        LaunchApp.driver.quit();
			   }
			   catch(Exception e){ExceptionHndeler.Log("Submit","Student Attempt", e);}
		   }
		   if(s[1].startsWith("Auto Submitted"))
		   {
			   String[]m=s[1].split(";");
			   Double time=Double.parseDouble(m[1]);
               System.out.println("time to sleep is"+time);
			  try
			   {
				Thread.sleep((long) (time*60000));
			   //LaunchApp.driver.findElement(By.xpath(".//*[.='Submit']")).click();
			  // Alert alert = LaunchApp.driver.switchTo().alert();
		        System.out.println("after constructor");
		       // alert.accept();
		        Login.logout();
		        LaunchApp.driver.quit();
			   }
			   catch(Exception e){ExceptionHndeler.Log("Attempt ","Student Attempt", e);}
		   }
	   }
	   
	   
   }
	}
	public void attemptTest(String[] str)
	{
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
			System.out.println("attemptTest1"+s[0]);
			System.out.println("attemptTest1"+s[1]);
			if(s[0].equals("Username"))
			{
				String[]m=s[1].split(";");
				LaunchApp.Execute(URL);
				Login.main(m);
			}
		if(s[0].equals("Section"))
			{
				ChangeSection.selectSection(s[1].trim());
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.linkText("Dashboard")).click();
			}
			if(s[0].equals("Test name"))
			{
				Thread.sleep(1000);
				LaunchApp.driver.findElement(By.linkText(s[1])).click();
			}
			if(s[0].equals("Access Code"))
			{
				if(s[1].equals("no"))
				{
					try
					{
						LaunchApp.driver.findElement(By.xpath(".//*[@id='StartTestLink']")).click();
					}catch(Exception e){//e.printStackTrace();
						}
				}
				else
				{
				Thread.sleep(100);
				LaunchApp.driver.findElement(By.xpath("//body")).sendKeys(Keys.F5);
				Thread.sleep(500);
				LaunchApp.driver.findElement(By.xpath(".//*[@id='secretEntry']")).sendKeys(s[1]);
				LaunchApp.driver.findElement(By.xpath(".//*[@id='UnlockTestWithSecret']")).click();
				}
			}
			if(s[0].equals("Activity type"))
			{
			if(s[1].equals("Multiple Choice"))  
			{
				System.out.println("In student Attempt ");
				Student_MCQ_Test.MCQ_Test(str);
			}
			if(s[1].equals("SQ"))  
			{
				Student_SQ_Test.SQ_Test(str);
			}
			if(s[1].equals("DQ"))  
			{
				//Student_MCQ_Test.MCQ_Test(str);
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
		}catch(Exception e){ExceptionHndeler.Log("Upload","Upload", e);}
		}
			
	public static void main(String k[]) 
	{
		Exicute("http://testing.inpods.com");
	}
	
}

