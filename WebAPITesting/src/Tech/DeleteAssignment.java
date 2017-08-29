package Tech;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.ChangeSection;
import Common.Gototab;
import Common.LaunchApp;
import Common.Login;
import Data.Read_Data;

public class DeleteAssignment {
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
	Path="http://somaiya.inpods.com/";
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
	ChangeSection.selectSection("Mech - Sem VII - 2016-17 - Operations");
	try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
	String[] args1={"a","s"};
	 args1[0]=(String) "Manage Course";
		 Gototab.main(args1);
		List<WebElement>li=LaunchApp.driver.findElements(By.xpath(".//*[contains(text(),'Delete')]"));
		for(int i=1;i<500;i++)
		{
		
		li.get(i).click();
		
		try
    	{
    	Alert alert = LaunchApp.driver.switchTo().alert();
    	String text=alert.getText();
    	Thread.sleep(5000);
   	    alert.accept();
    	} catch (Exception e2) {
   		    //exception handling left as an exercise for the reader
   		}
	}
	}
}
