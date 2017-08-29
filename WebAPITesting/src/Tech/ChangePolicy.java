package Tech;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Clickevent;
import Common.LaunchApp;
import Data.Loger;

public class ChangePolicy {
	
	@Test
	@Parameters({"PolicyName","Value"})
	public static void ChangePolicyByName(String PolicyName,String Value)
	{
	   try
	   {
		    Thread.sleep(2000);
			TechChangeSection.TechChangeSectionById("1");
			Thread.sleep(2000);
		    Clickevent.clickLinkByHref("/Admin/Index", ".//*[@id='side-menu']/li/a");
		    Thread.sleep(2000);
		    Clickevent.clickLinkByHref("#tabsManage-5", ".//*[@id='ui-id-5']");
		    Thread.sleep(2000);
		    Clickevent.clickLinkByHref("/Policy", ".//*[@id='tabsManage-5']/a");
		    Thread.sleep(2000);
		    
		   List<WebElement> lists1=LaunchApp.driver.findElements(By.xpath("//*[@for='PolicyName']"));
		   List<WebElement> lists2=LaunchApp.driver.findElements(By.xpath("//*[@id='PolicyValue']"));
		 for(int i=0;i<lists1.size()-1;i++)
			{
				String[] arr={lists1.get(i).getText(),lists2.get(i).getAttribute("value")};
				if(arr[0].trim().equals(PolicyName.trim()))
				{
					System.out.println(PolicyName.trim()+" "+arr[0]+" value  "+arr[1]);
					lists2.get(i).clear();
					lists2.get(i).sendKeys(Value);
					
					WebElement e1=lists2.get(i).findElement(By.xpath(".."));
					WebElement e2=e1.findElement(By.xpath(".."));
					String id=e2.getAttribute("id");
				//	LaunchApp.driver.findElement(By.xpath(".//*[@id='"+(i+1)+"']//*[@id='PolicyValue']")).clear();
				//	LaunchApp.driver.findElement(By.xpath(".//*[@id='"+(i+1)+"']//*[@id='PolicyValue']")).sendKeys(Value);
					Thread.sleep(2000);
					System.out.println("ID="+id);
					LaunchApp.driver.findElement(By.xpath(".//*[@id='"+id+"']/td[4]/input")).click();
					Thread.sleep(5000);
					Alert alert = LaunchApp.driver.switchTo().alert();
				    String Text=alert.getText();
				    Thread.sleep(2000);
				    alert.accept();
					Thread.sleep(100);
					
					System.out.println(Text);
					System.out.println(PolicyName.trim()+" "+arr[0]+" value  "+arr[1]);
					break;
				}                       
			}
	      }
	   catch(Exception e){e.printStackTrace();Loger.LogEvent(e.getMessage(),"Error"); }
	}

}
