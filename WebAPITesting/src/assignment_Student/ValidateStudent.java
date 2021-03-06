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

import Common.Gototab;
import Common.LaunchApp;
import Common.Login;
import Data.ExceptionHndeler;
import Data.Loger;
import Data.Read_Data;

import com.thoughtworks.selenium.SeleneseTestCase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ValidateStudent extends Thread  {
	public static int i=0;
	public static String data[][]=Read_Data.ReadData("studentValidate.csv");
	 public static String[] b=new String[10]; 
	 public static String AssignmentName=" ";
	 public static  String Username="Blank";
	public static void main(String k[]) 
	{
    
    String[][] Data=Read_Data.ReadData("URL.csv");
	 b[0]=Data[0][0];
    ValidateStudent t= new ValidateStudent();
	  t.start();
   //int Count=Integer.parseInt(data[0][1]);
   for( i = 0; i<data.length; i++)
   {
	  
	   String []s={"m","n"};
	   s=data[i][0].split(":");
	   if(s[1].trim().equals("NotAvailable"))
	   {
		  ValidateStudent.ValidateTest(data[i]);
		 
	   }
	   
	   
   }
	}
	public static void ValidateTest(String[] str)
	{
		try
		{
			
		
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
				{
					if(!(m[0].trim().equals(Username)))
					{
						 LaunchApp.main(b);
						   Username=m[0];
				          Login.main(m);
					}
				}
			}
		if(s[0].equals("Section"))
			{
				new Select(LaunchApp.driver.findElement(By.name("sectionId"))).selectByVisibleText(s[1]);
			}
			if(s[0].equals("Test name"))
			{
				Thread.sleep(1000);
				AssignmentName=s[1];
				if(LaunchApp.driver.findElements(By.linkText(s[1])).size()>0)
				{
			    Loger.LogEvent(s[1]+" available assignment "+AssignmentName, "-Pass");
				LaunchApp.driver.findElement(By.linkText(s[1])).click();
				}
			}
			if(s[0].equals("Assert1"))
			{
			  if((LaunchApp.driver.findElements(By.xpath(".//*[contains(text(),'"+s[1].trim()+"')]"))).size()>0)  
				  {
				  System.out.println("in asssert");
					Loger.LogEvent(s[2]+"  "+AssignmentName, "-Pass");
					System.out.println("in asssert pass");
					String [] args1=new String[10]; 
					 args1[0]=(String) "Home";
					 Gototab.main(args1);
					}
					else
					{
						Loger.LogEvent(s[2]+"  "+AssignmentName, "-Fail");
						String [] args1=new String[10]; 
						System.out.println("in asssert fail");
						args1[0]=(String) "Home";
						Gototab.main(args1);
					}
			}
			i++;
			}
			catch(Exception e){e.printStackTrace();}
		}
		
		}catch(Exception e){ExceptionHndeler.Log("Upload","Upload", e);}
		}
			
	
}

