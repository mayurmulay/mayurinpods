package Data;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Common.LaunchApp;
import Data.Loger;

public class ExceptionHndeler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m=0;

	}
	public static void Log(String type,String Location,Exception e1)
	{
		//
		int m=0;
		String e=e1.getMessage();
		getScreen(type+ " "+Location);
		Loger.LogException(type,Location,e);
	}
	public static void getScreen(String s)
	{
		 try {
	            Robot robot = new Robot();
	           // String Title=LaunchApp.driver.getTitle();
	            String Title="LaunchApp.driver.getTitle()";
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

}
