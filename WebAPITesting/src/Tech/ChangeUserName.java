
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

public class ChangeUserName {
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
	String[][] Data=Read_Data.ReadData("PasswordChangeInBulk\\Usernamechangekbl.csv");
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
			String new_username=Data[i][1].trim();
			
			try {
				changeUsername(Usernamename,new_username);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
	}
    public static void changeUsername(String Usernamename,String new_username) throws Exception
    {
    	try {
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='userName']")).clear();
    	Thread.sleep(2000);
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='userName']")).sendKeys(Usernamename.trim());
    	
    	Thread.sleep(2000);
    	LaunchApp.driver.findElement(By.xpath(".//*[@ng-disabled='searchUserForm.userName.$error.required']")).click();
    	
    	Thread.sleep(3000);
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='newUsername']")).sendKeys(new_username.trim());
    	
    	Thread.sleep(2000);
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='confirmUsername']")).sendKeys(new_username.trim());
    	
    	Thread.sleep(2000);
    	LaunchApp.driver.findElement(By.xpath(".//*[@ng-disabled='updateUserForm.newUsername.$error.required || updateUserForm.confirmUsername.$error.required']")).click();
    	Thread.sleep(2000);
    	Alert alert = LaunchApp.driver.switchTo().alert();
    	String text=alert.getText();
    	 alert.accept();
     alert = LaunchApp.driver.switchTo().alert();
     	 text=alert.getText();
     	 alert.accept();
    	}
    	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = LaunchApp.driver.switchTo().alert();
        	String text=alert.getText();
        	 alert.accept();
         
		}
    	try{
    		}
    	    catch (Exception e1) {
	    		    //exception handling left as an exercise for the reader
	    		}
    	
    }
}