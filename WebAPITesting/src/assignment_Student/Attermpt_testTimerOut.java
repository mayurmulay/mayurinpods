package assignment_Student;
import Common.ChangeSection;
import Common.LaunchApp;
import Common.Login;
import Data.ExceptionHndeler;
import Data.Read_Data;







import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;
import com.thoughtworks.selenium.SeleneseTestCase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Attermpt_testTimerOut extends Thread  {
	public static int i=0;
	String URL="http://somaiya.inpods.com/";
	public static String data[][]=Read_Data.ReadData("studentAttendSomiayDemo.csv");
	public static String username;
	 @Test(threadPoolSize = 2, invocationCount = 2, timeOut = 1000000000)
	 public void testMethod()
	 {
		// String data[][]=Read_Data.ReadData("studentAttend.csv");
		   String []s={"m","n"};
		   String[] b=new String[10];
		   s=data[i][0].split(":");
		   String data1[][]=Read_Data.ReadData("URL.csv");
		   b[0]=data1[0][0];
		  LaunchApp.main(b);
		  Attermpt_testTimerOut t= new Attermpt_testTimerOut();
		  t.start();
		  i++;
		((Attermpt_testTimerOut) t).attemptTest(data[i]);
		
	 }
	public static void main(String k[]) 
	{
		
    String[] b=new String[10]; 
    String data1[][]=Read_Data.ReadData("URL.csv");
	b[0]=data1[0][0];
    Attermpt_testTimerOut t= new Attermpt_testTimerOut();
	  //t.start();
  // int Count=Integer.parseInt(data[0][1]);
   for( i =0; i<data.length; i++)
   {
	  // LaunchApp.main(b);
	   String []s={"m","n"};
	   System.out.println("mayur"+data[i][0]);
	   s=data[i][0].split(":");
	   if(!s[1].equals("Not Started"))
	   {
		  (new Attermpt_testTimerOut()).attemptTest(data[i]);
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
		         LaunchApp.driver.close();
			   }
			   catch(Exception e){e.printStackTrace();}
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
				/*username=data[GetStudentCount()][1];
				System.out.print("In Username 0");
				
				System.out.print("GetStudentCount"+username);*/
				String user=username;
				String[]m=s[1].split(";");
				//m=user.split(";");
				//m=m[0].split(":");
				System.out.print("\nUsername1="+m[0]);
				m[0]="kjsstudent"+(114+GetStudentCount());
				
				m[1]="s1s1s1";
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
				try
				{
					LaunchApp.driver.findElement(By.xpath("html/body/div[5]/div/div/div[3]/div/button")).click();
				}
				catch (Exception e) {
   	    		   
   	    		}
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
			if(s[0].startsWith("Q"))
			{
				
				Student_MCQ_Test.MCQ_Test(str);
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
		}catch(Exception e){ExceptionHndeler.Log("GetStudentCount","GetStudentCount", e);}
		}
	
	public int GetStudentCount()
	{
		   
	        int num=0;
	        try {
	            BufferedReader sourceReader = new BufferedReader(new FileReader("D:\\Input.txt"));
	            int number;
	            number = sourceReader.read();
	            System.out.println("number="+number);
	            num=number;
	            sourceReader.close();
	            BufferedWriter oddWriter = new BufferedWriter(new FileWriter("D:\\Input.txt"));
	            number++;
	            oddWriter.write(number);
	            oddWriter.close();
	           
	            }
	        catch(Exception e)
	        {
	        	ExceptionHndeler.Log("GetStudentCount","GetStudentCount", e);
	        }
	          num=num-48;
		return num;
	}
			
	
}
