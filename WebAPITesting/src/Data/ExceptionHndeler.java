package Data;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Common.LaunchApp;
import Data.Loger;

public class ExceptionHndeler {

	public static void main(String[] args) {
	
	}
	public static void Log(String type,String Location,Exception e1)
	{
		//
		
		String e=e1.getMessage();
		getScreenLOg(type+ " "+Location);
		Loger.LogException(type,Location,e);
		
	}
	public static void getScreenLOg(String s)
	{
		 try {
	            Robot robot = new Robot();
	           // String Title=LaunchApp.driver.getTitle();
	            String Title="LaunchApp.driver.getTitle()";
	            String format = "jpg";
	            String fileName=" ";
	            int Flag=0;
	            if(s.startsWith("Error")){ fileName = "D:\\Log\\Error_Snap\\"+s+".jpg";  System.out.println(fileName);Flag=1;}
	            if(s.startsWith("Alert")){ fileName = "D:\\Log\\Error_Snap\\"+s+".jpg";  System.out.println(fileName);Flag=1;}
	            if(Flag==0){ fileName = "D:\\Log\\Snap\\"+s+".jpg";  System.out.println(fileName);Flag=1;}
	           // Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	           // BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
	           // ImageIO.write(screenFullImage,format, new File(fileName));
	             JavascriptExecutor js=(JavascriptExecutor)LaunchApp.driver;
	            js.executeScript("document.body.style.zoom=(top.window.screen.height-70)/Math.max(document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight);");


	            Thread.sleep(1000);
               File scrFile = ((TakesScreenshot)LaunchApp.driver).getScreenshotAs(OutputType.FILE);
               FileUtils.copyFile(scrFile, new File(fileName));
               Thread.sleep(1000);
               String str="document.body.style.zoom=\"100%\"";
               js.executeScript(str);
               Thread.sleep(1000);
              
	             
	            System.out.println("A full screenshot saved!");
	        } catch (Exception ex) {
	            System.err.println(ex);
	            ex.printStackTrace();
	        }
	}
	public static void getScreen(String s)
	{
		 try {
	            Robot robot = new Robot();
	           // String Title=LaunchApp.driver.getTitle();
	            String Title="LaunchApp.driver.getTitle()";
	            String format = "jpg";
	            String fileName = "D:\\AutomationSnap\\"+s+".jpg";
	             System.out.println(fileName);
	           Thread.sleep(1000);
               File scrFile = ((TakesScreenshot)LaunchApp.driver).getScreenshotAs(OutputType.FILE);
               FileUtils.copyFile(scrFile, new File(fileName));
               Thread.sleep(1000);
              
               Thread.sleep(1000);
              
	             
	            System.out.println("A full screenshot saved!");
	        } catch (Exception ex) {
	            System.err.println(ex);
	            ex.printStackTrace();
	        }
	}

}
