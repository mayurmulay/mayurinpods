package Tech;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import Common.LaunchApp;
import Common.Navigation;
import Data.ExceptionHndeler;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ReportDownload {
	
  public static String s;
	public static void main(String[] args) throws InterruptedException {
		
		ReportDownload d=new ReportDownload();
		Navigation nv=new Navigation();
		//System.out.println(nv.s1[0]);
		s=args[1].substring(args[1].indexOf("@"),args[1].indexOf("."));
		LaunchApp.driver.get(args[0]+"/OBE#/NBAReport/0/");
		nv.Execute("link,Course Level Assessment");
		for(int j=1;j<=4;j++)
		{
			String id=Integer.toString(j);
		new Select(LaunchApp.driver.findElement(By.id("program_select"))).selectByValue(id);
		for(int i=1;i<4;i++)
		{
			Thread.sleep(1000);
			int coloumn=0;
			s=args[1].substring(args[1].indexOf("@"),args[1].indexOf("."));
			if(s.equals("@jspm"))
			{
				coloumn=6;
			}else coloumn=4;
	      for(int co=3;co<coloumn;co++){
		LaunchApp.driver.findElement(By.xpath("//table//tr["+i+"]//td["+co+"]//span/a/i")).click();
		try
		{
			Thread.sleep(20000);
			JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
			jse.executeScript("window.scrollTo(0, 0)", "");
			Thread.sleep(1000);
			s="D:\\Log\\"+args[1].substring(args[1].indexOf("@"),args[1].indexOf("."))+i+j+co;
			d.getScreen(s);
			Thread.sleep(100);
		LaunchApp.driver.findElement(By.linkText("Download Report")).click();
		if(i==1)
		{Thread.sleep(5000);}
		System.out.println("Report ge download");
		LaunchApp.driver.findElement(By.className("btn")).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		}}
		d.POattainmentReport(args);
}
	public void getScreen(String s)
	{
		 try {
	            Robot robot = new Robot();
	            String Title=LaunchApp.driver.getTitle();
	            String format = "jpg";
	            String fileName = s+".jpg";
	             System.out.println(fileName);
	            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
	            ImageIO.write(screenFullImage,format, new File(fileName));
	             
	            System.out.println("A full screenshot saved!");
	        } catch (AWTException | IOException ex) {
	            System.err.println(ex);
	        }
	}
	public void POattainmentReport(String[] args)
	{
		ReportDownload d=new ReportDownload();
		Navigation nv=new Navigation();
		nv.Execute("link,Program Level Assessment");
		for(int i=1;i<4;i++)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int coloumn=0;
			s=args[1].substring(args[1].indexOf("@"),args[1].indexOf("."));
			if(s.equals("@jspm"))
			{
				coloumn=9;
			}else coloumn=4;
	      for(int co=2;co<coloumn;co++){
		LaunchApp.driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+co+"]/a/i")).click();
		try
		{
			Thread.sleep(20000);
			JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
			jse.executeScript("window.scrollTo(0, 0)", "");
			Thread.sleep(1000);
			s=args[1].substring(args[1].indexOf("@"),args[1].indexOf("."))+i+co+"program";
			d.getScreen(s);
			Thread.sleep(100);
		LaunchApp.driver.findElement(By.linkText("Download Report")).click();
		if(i==1)
		{Thread.sleep(5000);}
		System.out.println("PROGRAM Report get download");
		LaunchApp.driver.findElement(By.className("btn")).click();
		}
		catch(Exception e)
		{
			ExceptionHndeler.Log("Report Download","Report Download", e);
		}
		}
		
	}
}}