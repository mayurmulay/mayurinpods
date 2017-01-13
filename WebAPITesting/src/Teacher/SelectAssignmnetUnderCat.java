package Teacher;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Clickevent;
import Common.Gototab;
import Common.LaunchApp;
import Common.MooveToElement;
import Data.ExceptionHndeler;

public class SelectAssignmnetUnderCat {
	
	static String Sectionname1;
	@Test
	@Parameters({ "Sectionname", "CatgoriName", "Asignment_name","Generate Report"})
	
	public static void exicute(String Sectionname,String CatgoriName,String Asignment_name,String Generate_Report )
	{
	    String args1[]={"Reports"," "};
		Gototab.main(args1);
		JavascriptExecutor jse = (JavascriptExecutor)LaunchApp.driver;
		jse.executeScript("window.scrollTo(50, 50)", "");
		LaunchApp.driver.findElement(By.xpath(".//*[contains(text(),'Reports')]")).click();
		try{Thread.sleep(15000);
		
	    Clickevent.clickLinkByHref("/Report/Gradebook?sectionId",".//*[.='"+Sectionname.trim()+"']");
	    MapAssCat(Asignment_name,CatgoriName,Generate_Report);
		}catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
		
		
	}
	public static void MapAssCat(String ass_Name, String CatgoriName,String Generate_Report)
	{
		LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-id-2']")).click();
		LaunchApp.driver.findElement(By.xpath(".//*[@name='cmbCategory']")).click();
	  new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='cmbCategory']"))).selectByVisibleText(CatgoriName.trim());
	  try {Thread.sleep(2000);}catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
		List <WebElement> li=Common.LaunchApp.driver.findElements(By.xpath(".//*[@name='asgnId']/../.."));
		String [] AssName1=ass_Name.split(";");
		for(int i=0;i<AssName1.length;i++)
		{
			try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		for(WebElement element : li)
		{
			try
			{
			    String AssNamefromUI=element.getText();
			    System.out.println("\n\n Text="+AssNamefromUI);
				if(AssNamefromUI.contains(AssName1[i].trim()))
				{
					try
					{
						LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-id-2']")).click();
						Thread.sleep(2000);
					}catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
					Clickevent.ClickEvent(element.findElement(By.xpath(".//*[@name='asgnId']")));
					
				}
			}
			catch(Exception e){e.printStackTrace(); ExceptionHndeler.Log("Error","CreateCategories", e);}
	    }
	   }
		if(Generate_Report.contains("YES"))
		{
			 MooveToElement.moveToElenment(".//*[@name='btnSubmit']");
			ExceptionHndeler.getScreen("Greadbook_Cat_"+CatgoriName);
			LaunchApp.driver.findElement(By.xpath(".//*[@name='btnSubmit']")).click();
		    ExceptionHndeler.getScreen("Greadbook_Report_Finle_After_Mapping");
		    MooveToElement.moveToElenment(".//*[@id='chkShowDetails']");
		    try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
		    LaunchApp.driver.findElement(By.xpath(".//*[@id='chkShowDetails']")).click();
		    ExceptionHndeler.getScreen("Greadbook_Report_unchkShowDetails");
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}