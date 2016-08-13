package Common;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import Data.ExceptionHndeler;


public class Navigation {
    
     public static Navigation nv=new Navigation();
	public void Execute(String TestData)
	{
		String pagesource=LaunchApp.driver.getPageSource();
		//System.out.println(pagesource);
		String[] SplitedTestData=TestData.split(",");
		for(int i=0;i<SplitedTestData.length;i++)
		{
			if(SplitedTestData[i].compareTo("id")==0)
			{
				
				LaunchApp.driver.findElement(By.id(SplitedTestData[i+1])).click();
				i++;
			}
			if(SplitedTestData[i].compareTo("link")==0)
			{
			
			LaunchApp.driver.findElement(By.linkText(SplitedTestData[i+1])).click();
			i++;
		   }
		}
		
			
	}
	public static void main(String[] args) throws InterruptedException {
	
		String[] args1=new String[10];
		try
		{
			String[][]data=ReadData("URL.csv");
		 args1[0]=data[0][0];
		 LaunchApp.main(args1);
		 
		 String username[][]=new String[100][100];
		 username=ReadData("Book1.csv");
		 
		 for(int i=0;i<username.length;i++)
		 {
		  args1[1]=username[i][0];
		  Login.main(username[i]);
		  
		      //Login.main(username[i]);
		    //  ReportDownload.main(args1);
		   //  CreateLesson.main(username[i]);
		 
		 //EditAsignment1.main(args);
		// EditAsignmentValidation.main(args1);
		// Attempt_test.main(args1);
		 // GradeAssignment.main(args1);
		  //s    Login.logout();
		     
		     /* try{
			     CreateAssignment.main(args1);
			     //  Thread.sleep(1000);
			      }
			      catch(Exception e){e.printStackTrace();}
		      try{
		    	  String[][] Ass=ReadData("AssignmentEdit.csv");
		    	  EditAssignment.main(Ass[0]);
			      }
			      catch(Exception e){e.printStackTrace();}*/
		      
		      Thread.sleep(1000);
		    }
		}
		catch(Exception e)
		{
			ExceptionHndeler.Log("log IN","nevigation", e);
		}
		
	}
	@SuppressWarnings("resource")
	public static String[][] ReadData(String name)
	{
		String [][] s1=new String[1000][100];
		 BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader("D:\\Policy\\"+name));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		    String input;
		   String splitBy = ",";
	      int count = 0;
	      try {
			while((input = bufferedReader.readLine()) != null)
			    {             
			      String[] b = input.split(splitBy);
			      for(int i=0;i<b.length;i++)
			      {
			         s1[count][i]=b[i];
			      }
			     count++;
			    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return s1;
	}

}
