package Tech;

import java.util.List;

import org.apache.bcel.verifier.structurals.ExceptionHandler;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Teacher.Publish_Section_level_Report;
import Common.AlertHandling;
import Common.CloseBroser;
import Common.Gototab;
import Common.LaunchApp;
import Common.Login;
import Common.MooveToElement;
import Data.ExceptionHndeler;
import Data.Read_Data;

public class ChangeCOattainemntSetting {
	static String username;
	static String password;
	static String pgmName;
	static String CourseName;
	static String type;
	static String Section_id;
	
	@Test
	@Parameters("filename")
	public static void exicute(String filename)
	{
		try
		{
			
			String[][] data=Read_Data.ReadData(filename);
			System.out.println("size="+data.length);
			int i=0;
			while(data[i][0]!=null)
			{
				int j=0;
				try{
				while(data[i][j]!=null)
				{
					String[] datasplit=data[i][j].split(":");
					if(datasplit[0].contains("Username")){username=datasplit[1].trim();}
					if(datasplit[0].contains("password")){password=datasplit[1].trim();}
					if(datasplit[0].contains("programname")){pgmName=datasplit[1].trim();}
					if(datasplit[0].contains("Coursename")){CourseName=datasplit[1].trim();}
					if(datasplit[0].contains("Type")){type=datasplit[1].trim();}
					if(datasplit[0].contains("SectionId")){Section_id=datasplit[1].trim();}
					j++;
				}
				
				LaunchApp.Execute("URL is plced in URL.csv in somaiya folder");
				Thread.sleep(4000);
				Login.LoginD(username, password);
				Thread.sleep(5000);
				settingpage(pgmName,CourseName);
				Thread.sleep(5000);
				Editsetting(type);
				Thread.sleep(3000);
				TechChangeSection.TechChangeSectionById(Section_id);
				Thread.sleep(1000);
				String[] args = new String[10];
				args[0]=(String) "Reports";
				Gototab.main(args);
				LaunchApp.driver.findElement(By.xpath(".//*[@href='/Report/SectionConceptsReport']")).click();
				
				Thread.sleep(1000);
				Publish_Section_level_Report.Unpublish();
				Thread.sleep(1000);
				Publish_Section_level_Report.Recompute();
				Thread.sleep(5000);
				LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-id-5']")).click();
				Thread.sleep(1000);
				ExceptionHndeler.getScreen(type+"SectionLevelReport");
				Publish_Section_level_Report.publish();
				Thread.sleep(30000);
				ExceptionHndeler.getScreen(type+"CourseLevelReport");
				CloseBroser.quit1();
				Assert.assertEquals(1, 1);
				i++;
				}catch(Exception e){e.printStackTrace();}
			}
		}
		catch(Exception e)
		{
			
		}
	}
	public static void settingpage(String pgmName, String CourseName)
	{
		try
		{
			LaunchApp.driver.findElement(By.xpath(".//*[@id='side-menu']//*[.='OBE Config']")).click();
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[@href='/#/config/nbasettings']")).click();
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='tab = 2;getCourses();']")).click();
			Thread.sleep(3000);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='program_select']")).click();
			Thread.sleep(3000);
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='program_select']"))).selectByVisibleText(pgmName.trim());
			Thread.sleep(4000);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='course_select']")).click();
			Thread.sleep(5000);
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@id='course_select']"))).selectByVisibleText(CourseName.trim());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void Editsetting(String type)
	{
		try
		{
		if(type.contains("Somaiya"))
		{
			LaunchApp.driver.findElement(By.xpath(".//*[@name='lnkCOLevelAttainmentSettings']")).click();
			Thread.sleep(5000);
			LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='editMapping()']")).click();
			Thread.sleep(5000);Alert alert = LaunchApp.driver.switchTo().alert();alert.accept();Thread.sleep(5000);
			EnableSepCO_Unifalse();}
		
		if(type.contains("Amrita"))
		{
			LaunchApp.driver.findElement(By.xpath(".//*[@name='lnkCOLevelAttainmentSettings']")).click();
			Thread.sleep(5000);
			LaunchApp.driver.findElement(By.xpath(".//*[@ng-click='editMapping()']")).click();
			Thread.sleep(5000);Alert alert = LaunchApp.driver.switchTo().alert();alert.accept();Thread.sleep(5000);
		EnableSepCO_UniTrue("normal");
		Thread.sleep(2000);
		try
		{LaunchApp.driver.findElement(By.xpath(".//*[@name='lnkTab2']")).click();Thread.sleep(3000);}catch(Exception e){LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-id-6']")).click();}
		EnableSepCO_UniTrue("Uni");
		Thread.sleep(2000);
		EnableSepCO_UniTrueIndirect();
		Thread.sleep(2000);
		EnableSepCO_UniTrueWeightage();
		Thread.sleep(2000);
		}
		
		}catch(Exception e){e.printStackTrace();}
		
		
		
	}
	public static void EnableSepCO_Unifalse()
	{
		try
		{
			
	   	    List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[@name='attainmentThr']"));
	   	for(int j=0;j<li.size();j++)
		  {
			  WebElement slider = li.get(j);
			  Actions move = new Actions(LaunchApp.driver);
			  org.openqa.selenium.interactions.Action action = move.dragAndDropBy(slider,15, 0).build(); action.perform();
			  System.out.println(li.get(j).getAttribute("id"));
		  }
	   	MooveToElement.moveToElenment(".//*[@name='attainmentThr']");
	   	ExceptionHndeler.getScreen(type+"SPA");
	   	//target of attainment 
	   	LaunchApp.driver.findElement(By.xpath(".//*[@name='lnkTab6']")).click();Thread.sleep(3000);
	   	List <WebElement> li3=LaunchApp.driver.findElements(By.xpath(".//*[@name='targetAttainment']"));
	   	for(int j=0;j<li3.size();j++)
		  {
			  WebElement slider = li3.get(j);
			  Actions move = new Actions(LaunchApp.driver);
			  org.openqa.selenium.interactions.Action action = move.dragAndDropBy(slider,15, 0).build();
			  action.perform();
			  System.out.println(li3.get(j).getAttribute("id"));
		  }
	   	MooveToElement.moveToElenment(".//*[@name='targetAttainment']");
	   	ExceptionHndeler.getScreen(type+"TOA");
	   	List <WebElement> li1=LaunchApp.driver.findElements(By.xpath(".//*[@name='COAttainmentThresholdRange']")); 
	   	List <WebElement> li2=LaunchApp.driver.findElements(By.xpath(".//*[.='Set']")); 
	   
		try{Thread.sleep(2000);}catch(Exception e){e.printStackTrace();}      
		for(int j=0;j<li1.size();j++)
		  {
				try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}
			     li1.get(j).clear();
			     li1.get(j).sendKeys("50,70");
		  }
		try{Thread.sleep(5000);}catch(Exception e1){e1.printStackTrace();}
		for(int j=0;j<li2.size();j++)
		  {
				try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}
			     li2.get(j).click();
		  }
		MooveToElement.moveToElenment(".//*[.='Set']");
		ExceptionHndeler.getScreen(type+"bucket");
		Assert.assertEquals(1, 1);
		}catch(Exception e){e.printStackTrace();Assert.fail("Not able Edit Internal attainment "+e.getMessage());}
	}
	public static void EnableSepCO_UniTrue(String tp)
	{
		try
		{
			
	   	    List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[@name='attainmentThr']"));
	   	for(int j=0;j<li.size();j++)
		  {
			  WebElement slider = li.get(j);
			  Actions move = new Actions(LaunchApp.driver);
			  org.openqa.selenium.interactions.Action action = move.dragAndDropBy(slider,15, 0).build(); action.perform();
			  System.out.println(li.get(j).getAttribute("id"));
		  }
	   	List <WebElement> li3=LaunchApp.driver.findElements(By.xpath(".//*[@name='targetAttainment']"));
	   	for(int j=0;j<li3.size();j++)
		  {
			  WebElement slider = li3.get(j);
			  Actions move = new Actions(LaunchApp.driver);
			  org.openqa.selenium.interactions.Action action = move.dragAndDropBy(slider,15, 0).build();
			  action.perform();
			  System.out.println(li3.get(j).getAttribute("id"));
		  }
	   
	   	List <WebElement> li1=LaunchApp.driver.findElements(By.xpath(".//*[@name='COAttainmentThresholdRange']")); 
	   	List <WebElement> li2=LaunchApp.driver.findElements(By.xpath(".//*[.='Set']")); 
	   
		try{Thread.sleep(2000);}catch(Exception e){e.printStackTrace();}      
		for(int j=0;j<li1.size();j++)
		  {
				try{Thread.sleep(1000);
				li1.get(j).click();
			     li1.get(j).clear();
			     li1.get(j).sendKeys("50,70");}catch(Exception e){}
		  }
		try{Thread.sleep(5000);}catch(Exception e1){e1.printStackTrace();}
		for(int j=0;j<li2.size();j++)
		  {
				try{Thread.sleep(1000);
			     li2.get(j).click();
			     li2.get(j).click();
		  }catch(Exception e){}
		  }
		MooveToElement.moveToElenment(".//*[.='Set']");
		ExceptionHndeler.getScreen(type+"_"+tp);
		Assert.assertEquals(1, 1);
		}catch(Exception e){e.printStackTrace();Assert.fail("Not able Edit University Attainment "+e.getMessage());}
	}

	public static void EnableSepCO_UniTrueIndirect()
	{
		try
		{
		LaunchApp.driver.findElement(By.xpath(".//*[@name='lnkTab3']")).click();
		Thread.sleep(4000);
	    WebElement slider = LaunchApp.driver.findElement(By.xpath(".//*[@name='targetAttainment']"));
	    Actions move = new Actions(LaunchApp.driver);
	    org.openqa.selenium.interactions.Action action = move.dragAndDropBy(slider,30, 0).build(); action.perform();
	    AlertHandling.waitForAlert();
	    LaunchApp.driver.findElement(By.xpath(".//*[@name='indirectLevel']")).click();
	    AlertHandling.waitForAlert();
	    //Alert alert = LaunchApp.driver.switchTo().alert();alert.accept();Thread.sleep(5000);
	    LaunchApp.driver.findElement(By.xpath(".//*[@name='indirectLevel']")).clear();
	    LaunchApp.driver.findElement(By.xpath(".//*[@name='indirectLevel']")).sendKeys("50,70");
	    LaunchApp.driver.findElement(By.xpath(".//*[@data-ng-click='updateNBAIndirectThresholdsLevels(indirectLevel)']")).click();
	    Thread.sleep(2000);
	    AlertHandling.waitForAlert();
	    MooveToElement.moveToElenment(".//*[@name='indirectLevel']");
	    ExceptionHndeler.getScreen(type+"_Indirect");
	    Assert.assertEquals(1, 1);
		}catch(Exception e){e.printStackTrace();Assert.fail("Not able Edit Idirect Attainment"+e.getMessage());}
	}
	public static void EnableSepCO_UniTrueWeightage()
	{
		try
		{
		LaunchApp.driver.findElement(By.xpath(".//*[@name='lnkTab4']")).click();
		Thread.sleep(2000);
		WebElement slider = LaunchApp.driver.findElement(By.xpath(".//*[@name='InternalExamWeightage']"));
	    Actions move = new Actions(LaunchApp.driver);
	    org.openqa.selenium.interactions.Action action = move.dragAndDropBy(slider,30, 0).build(); action.perform();
	    Thread.sleep(2000);
	    AlertHandling.waitForAlert();
	    MooveToElement.moveToElenment(".//*[@name='InternalExamWeightage']");
	    ExceptionHndeler.getScreen(type+"_Weightage");
	    Assert.assertEquals(1, 1);
		}catch(Exception e){e.printStackTrace();Assert.fail("Not able Edit University Weightage "+e.getMessage());}
	}
	@Test
	@Parameters({"Pgmname","CourseName","type"})
	public static void changeOBEsetting(String Pgmname,String CourseName, String type)
	{
		try{
		Thread.sleep(5000);
		settingpage(Pgmname,CourseName);
		Thread.sleep(5000);
		Editsetting(type);
		Assert.assertEquals(1, 1);
		}catch(Exception e){e.printStackTrace();}
	}
    
}
