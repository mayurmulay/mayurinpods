package Common;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Clickevent {

	public static void ClickEvent(WebElement element){
		try
		{
			element.click();
		}
		catch(Exception e){JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
		jse.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
			e.printStackTrace();element.click();}
	}
	public static void clickLinkByHref(String href,String Xpath) {
	    List<WebElement> anchors = LaunchApp.driver.findElements(By.xpath(Xpath));
	    Iterator<WebElement> i = anchors.iterator();
	  int j=0;
	    while(i.hasNext()) {
	        WebElement anchor = i.next();
	        try
	        {
	        	String str=anchor.getAttribute("href");
	        	System.out.println("j="+str);
	        	 if(str.contains("/Report/Gradebook?sectionId")) {
	    	         anchor.click();
	    	         break;
	    	        }
	        }
	        catch(Exception e)
	        {
	        	//e.printStackTrace();
	        	continue;
	        }
	    
	    }
	}
}
