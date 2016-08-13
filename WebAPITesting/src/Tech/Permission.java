package Tech;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import Common.LaunchApp;
import Data.Read_Data;

public class Permission {

	public static void main(String[] args) {
	String[][] Data=Read_Data.ReadData("PermissionsSpmaiya.csv");
	WebDriver driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("http://somaiya.inpods.com/");// Maximize the window.
	driver.manage().window().maximize();
	
  try
  {Thread.sleep(2000);
	driver.findElement(By.xpath(".//*[@id='topbar']/div[2]/ul/li/a")).click();
  } catch (Exception e) {
		 e.printStackTrace();
		}
  
	driver.findElement(By.xpath(".//*[@id='UserName']")).sendKeys("inpodssa@somaiya.edu");
	driver.findElement(By.xpath(".//*[@id='Password']")).sendKeys("somaiyapeo");
	driver.findElement(By.xpath(".//*[@id='page-login']//button")).click();
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	
	// Click on 'Sign In' button
	
	driver.findElement(By.xpath(".//*[@id='side-menu']/li[8]/a")).click();
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	driver.findElement(By.xpath(".//*[@id='side-menu']/li[8]/ul/li[2]/a")).click();
	
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try
	{
	//loop start here
	for(int i=130;i<1000;i++)
	{
		String count=i+"";
	 Data[i][1]=Data[i][1].trim();
	 Data[i][0]=Data[i][0].trim();
	 Data[i][2]=Data[i][2].trim();
	driver.findElement(By.xpath(".//*[@ng-click='createPermission()']")).click();
	int loop=0;
	while(loop==0)
	{
	new Select(driver.findElement(By.xpath(".//*[@name='objtype']"))).selectByValue("5");
	
	String counter=i+"";
	new Select(driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[2]/div/div[1]/div/select"))).selectByVisibleText(Data[i][0].trim());//Select program
	try {
	 Thread.sleep(1000);
	 try {Thread.sleep(5000);} catch (InterruptedException e) {	e.printStackTrace();}
	} catch (InterruptedException e) {
	 e.printStackTrace();
	}
	new Select(driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[2]/div/div[2]/div/div/select"))).selectByVisibleText(Data[i][1].trim());// Select Course 
	try {
		Thread.sleep(800);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  // new Select(driver.findElement(By.xpath(".//*[@name='staff']"))).selectByIndex(1);
    try {
    	new Select(driver.findElement(By.xpath(".//*[@name='EmployeeType']"))).selectByVisibleText("Teacher");
    	 try {Thread.sleep(5000);} catch (InterruptedException e) {	e.printStackTrace();}
		Thread.sleep(1500);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		new Select(driver.findElement(By.xpath(".//*[@class='selectpicker ng-pristine ng-valid'][@name='EmployeeType']"))).selectByVisibleText("Teacher");
    	
	}
    
	List<WebElement> list = driver.findElements(By.tagName("option"));
	Iterator<WebElement> i1 = list.iterator();
	while(i1.hasNext()) {
	    WebElement wel = i1.next();    
	    //System.out.println(wel.getText()+"       done        "+Data[i][2]);
	    if(wel.getText().contains(Data[i][2].trim()))
	    {
	       System.out.println(wel.getText()+"     in if   done        "+Data[i][2]);
	       driver.findElement(By.xpath(".//*[@name='staff']")).click();
	       new Select(driver.findElement(By.xpath(".//*[@name='staff']"))).selectByVisibleText(wel.getText());
	       try {Thread.sleep(5000);} catch (InterruptedException e) {	e.printStackTrace();}
	       driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[4]/table/tbody/tr[2]/td[3]/input")).click();
	    	driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[4]/table/tbody/tr[3]/td[3]/input")).click();
	  //     driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[4]/table/tbody/tr[5]/td[3]/input")).click();
	   	driver.findElement(By.className("icon-save")).click();
	   	System.out.println("option is selected="+i);
	   	try {
			Thread.sleep(8000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   	Alert alert = driver.switchTo().alert();
    	String text1=alert.getText();
   	    alert.accept();
   	    try
   	    {
   	   // alert = driver.switchTo().alert();
    	// text1=alert.getText();
   	   // alert.accept();
   	    }catch(Exception e){}
   	    if(!text1.contains("Permissions data saved properly"))
   	    {
   	    	System.out.println(text1+"option is selected="+i);	
   	    }
	   	loop=1;
	   	break;
	    }
	} 
	}
	
	
	}//end here
	
	}catch(Exception e){
		e.printStackTrace();
	}
	
	}

}
