package Common;

import java.io.Console;
import java.util.List;

import junit.framework.Assert;

import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;

import Data.ExceptionHndeler;

@SuppressWarnings({ "deprecation", "unused" })
public class ValidateAndSnap {
	
	
	
	@Test
	@Parameters({"Picname","text"})
	public static void ValidateString(@Optional("type1") String Picname,String text)
	{
		
		try{
		
			 JavascriptExecutor js=(JavascriptExecutor)LaunchApp.driver;
	        // js.executeScript("document.body.style.zoom=(top.window.screen.height-70)/Math.max(document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight);");
			 String str1="document.body.style.zoom=\"50%\"";
			 js.executeScript(str1);

			ExceptionHndeler.getScreen(Picname);
			
			String str="document.body.style.zoom=\"100%\"";
            js.executeScript(str);
			
			String[] Data=text.split(":");
					if(Data[0].equals("String"))
					{
						String bodyText =  LaunchApp.driver.findElement(By.tagName("body")).getText();
						
						if(bodyText.contains(Data[1]))
						{
							Assert.fail(Picname);
						}
						else
						{
							 Assert.assertTrue(Picname, true);
						}
					}
					if(Data[0].trim().equals("Xpath"))
					{
						 List<WebElement> adminPermissions = LaunchApp.driver.findElements(By.xpath(Data[1].trim()));
						    if (adminPermissions.size()== 0)
						    {
						        System.out.println("policy Working");
						        Assert.assertTrue(Picname, true);
						    }
						    else
						    {
						    	System.out.println("Policy Not working");
						    	Assert.fail(Picname);
						    }
						    
					}
		
		
		}catch (Exception e){}
	}

	@Test
	@Parameters({"Picname","text","type1"})
	public static void ValidateStringYes(String Picname,String text,String type1)
	{
		
		try{
			
			String[] Data=text.split(":");
			try{
			WebElement e=LaunchApp.driver.findElement(By.xpath(".//*[.='"+Data[1].trim()+"']"));
			Actions actions = new Actions(LaunchApp.driver);
			actions.moveToElement(e);
			Thread.sleep(2000);
			}catch (Exception e){e.printStackTrace();}
			
			ExceptionHndeler.getScreen(Picname);
					if(Data[0].equals("String"))
					{
						String bodyText =  LaunchApp.driver.findElement(By.tagName("body")).getText();
						
						if(bodyText.contains(Data[1]))
						{
							Assert.assertTrue(Picname, true);
						}
						else
						{
							Assert.fail(Picname);
							 
						}
					}
					if(Data[0].trim().equals("Xpath"))
					{
						 List<WebElement> adminPermissions = LaunchApp.driver.findElements(By.xpath(Data[1].trim()));
						    if (adminPermissions.size()== 0)
						    {
						        System.out.println("policy Working");
						        Assert.fail(Picname);
						        
						    }
						    else
						    {
						    	System.out.println("Policy Not working");
						    	Assert.assertTrue(Picname, true);
						    }
						    
					}
		
		
		}catch (Exception e){e.printStackTrace();}
	}

}
