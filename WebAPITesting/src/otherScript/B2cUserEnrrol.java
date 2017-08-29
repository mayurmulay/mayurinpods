package otherScript;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import Common.LaunchApp;
import Data.ExceptionHndeler;
import Data.Read_Data;

public class B2cUserEnrrol {
	
	@Test
	public static void Demo()
	{
		String[][] Data=Read_Data.ReadData("Users.csv");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://testing.inpods.com:88");// Maximize the window.
		driver.manage().window().maximize();
		
		for(int i=426;i<1001;i++)
		{
			try
			{
			
		driver.findElement(By.xpath(".//*[.='Refresher on Quantitative Aptitude']")).click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath(".//*[@name='tryIt']")).click();
		
		
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath(".//*[@id='fullname']")).clear();
		driver.findElement(By.xpath(".//*[@id='fullname']")).sendKeys(Data[i][0]);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath(".//*[@id='userName']")).clear();
		driver.findElement(By.xpath(".//*[@id='userName']")).sendKeys(Data[i][1]);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath(".//*[@id='pwd']")).clear();
		driver.findElement(By.xpath(".//*[@id='pwd']")).sendKeys(Data[i][2]);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath(".//*[@id='cnfpwd']")).clear();
		driver.findElement(By.xpath(".//*[@id='cnfpwd']")).sendKeys(Data[i][2]);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath(".//*[@id='frmRegister']/div[5]/div/button")).click();
		try {Thread.sleep(1200);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath(".//*[@id='agreeTermsCbx']")).click();
		try {Thread.sleep(1200);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath(".//*[@id='btnContinue']")).click();
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		ExceptionHndeler.getScreen("Student"+i);
		
		driver.findElement(By.xpath(".//*[@id='topbar']/div[2]/ul[2]")).click();
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		driver.findElement(By.xpath(".//*[@href='/account/SignOut']")).click();
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		
		System.out.println("demo"+i);
			}
			catch (Exception e) {System.out.println("Error"+i);   try {Alert alert = LaunchApp.driver.switchTo().alert();
			 alert.accept();} catch (Exception e1) {}}
			
		
		}
		
	}

}
