package Tech;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import Common.LaunchApp;
import Common.Login;
import Data.Read_Data;

public class ValidateUsername {
	static String Path="";
	static String name="";
	static String password="";
	public static void main(String[] str)
	{
		Readdata();
	}
	public static void Readdata()
	{
	String[][] URL=new String[1000][1000];
	//URL=Read_Data.ReadData("URL.csv");
	
	//Path=URL[0][0].trim();
	Path="http://demo.inpods.com/";
	 URL[0][0]=Path;
	String[][] Data=Read_Data.ReadData("PasswordChangeInBulk\\Usernamechange.csv");
	LaunchApp.main(URL[0]);
	Login.main(Data[0]);
	try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
	//LaunchApp.driver.findElement(By.xpath("//*[contains(text(),'Admin')]")).click();
	try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
	//LaunchApp.driver.findElement(By.xpath("//*[contains(text(),'ResetPassword')]")).click();
	try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
   //	LaunchApp.driver.findElement(By.xpath("//*[contains(text(),'Search')]")).click();
	for(int i=1;i<Data.length;i++)
	{
		try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
		
			String Usernamename=Data[i][0].trim();
			String Email=Data[i][1].trim();
			String name=Data[i][2].trim();
			try {
				changeUsername(Usernamename,Email,name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
	}
    public static void changeUsername(String Usernamename,String Email,String name) throws Exception
    {
    	try {
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='userName']")).clear();
    	
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='userName']")).sendKeys(Usernamename.trim());
    
    	LaunchApp.driver.findElement(By.xpath(".//*[@ng-disabled='searchUserForm.userName.$error.required']")).click();
    	try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
    	
    	String  Email1=LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper2']/div[1]/div/div/div[2]/div/div/div/table[1]/tbody/tr/td[2]")).getText();
    	

    	String  name1=LaunchApp.driver.findElement(By.xpath(".//*[@id='page-wrapper2']/div[1]/div/div/div[2]/div/div/div/table[1]/tbody/tr/td[3]")).getText();
    	
    	try
    	{
    	Alert alert = LaunchApp.driver.switchTo().alert();
    	String text=alert.getText();
   	    alert.accept();
    	} catch (Exception e2) {
   		    //exception handling left as an exercise for the reader
   		}
    	
   	    if(Email1.contains(Email.trim()) && name1.contains(name.trim()))
   	    {
   	    	try(FileWriter fw = new FileWriter("D:\\Username.txt", true);
   	    		    BufferedWriter bw = new BufferedWriter(fw);
   	    		    PrintWriter out = new PrintWriter(bw))
   	    		{
   	    		    out.println("\nUsername Changed successfully: "+name);
   	    		 
   	    		} catch (IOException e) {
   	    		    //exception handling left as an exercise for the reader
   	    		}
   	    }
   	    else
   	    {
   	    	try(FileWriter fw = new FileWriter("D:\\userNotfound.txt", true);
   	    		    BufferedWriter bw = new BufferedWriter(fw);
   	    		    PrintWriter out = new PrintWriter(bw))
   	    		{
   	    		    out.println("\nError: User not Found "+Usernamename+"  "+name);
   	    		 
   	    		} catch (IOException e) {
   	    		    //exception handling left as an exercise for the reader
   	    		}
   	    }
   	    
   	   
    	} catch (Exception e) {
    		String text="";
    		try{
    		Alert alert = LaunchApp.driver.switchTo().alert();
        	text=alert.getText();
        	 alert.accept();}catch (Exception e1) {
	    		    //exception handling left as an exercise for the reader
	    		}
    		try(FileWriter fw = new FileWriter("D:\\ErrorUsername.txt", true);
   	    		    BufferedWriter bw = new BufferedWriter(fw);
   	    		    PrintWriter out = new PrintWriter(bw))
   	    		{
   	    		    out.println("\nError: "+text+" "+name);
   	    		 
   	    		} catch (IOException e2) {
   	    		    //exception handling left as an exercise for the reader
   	    		}
    	}
    }
}
