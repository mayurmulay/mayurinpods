package Tech;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import Common.LaunchApp;
import Data.ExceptionHndeler;
import Data.Read_Data;

public class Permission {

	public static void main(String[] args) throws InterruptedException {
	String[][] Data=Read_Data.ReadData("Amritaettimadai.csv");
	//WebDriver driver = new FirefoxDriver();
	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	System.setProperty("webdriver.chrome.driver","D:\\InpodsAutomation\\WebAPITesting\\JAR\\chromedriver.exe.");
	//System.out.println(System.getProperty("user.dir")+"/AllDrivers/chromedriver_v_2.21.exe");
	WebDriver driver = new ChromeDriver();
	driver.get("http://autraining.inpods.com:8000");// Maximize the window.
	driver.manage().window().maximize();
	
  try
  {Thread.sleep(2000);
	driver.findElement(By.xpath(".//*[@id='topbar']/div[2]/ul/li/a")).click();
  } catch (Exception e) {
		 e.printStackTrace();
		}
  Thread.sleep(2000);
	driver.findElement(By.xpath(".//*[@id='UserName']")).sendKeys("inpodssa@amritatraining.edu");
	Thread.sleep(2000);
	driver.findElement(By.xpath(".//*[@id='Password']")).sendKeys("techone");
	Thread.sleep(2000);
	driver.findElement(By.xpath(".//*[@id='page-login']//button")).click();
	Thread.sleep(2000);
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
	for(int i=292;i<301;i++)
	{
		String count=i+"";
	 Data[i][1]=Data[i][1].trim();
	 Data[i][0]=Data[i][0].trim();
	 Data[i][2]=Data[i][2].trim();
	 Data[i][3]=Data[i][3].trim();
	driver.findElement(By.xpath(".//*[@ng-click='createPermission()']")).click();
	int loop=0;
	while(loop==0)
	{
	new Select(driver.findElement(By.xpath(".//*[@name='objtype']"))).selectByValue("0");
	Thread.sleep(2000);
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
		Thread.sleep(2000);
		Thread.sleep(800);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  // new Select(driver.findElement(By.xpath(".//*[@name='staff']"))).selectByIndex(1);
    try {
    	new Select(driver.findElement(By.xpath(".//*[@name='EmployeeType']"))).selectByVisibleText(Data[i][2].trim());
    	 try {Thread.sleep(5000);} catch (InterruptedException e) {	e.printStackTrace();}
		Thread.sleep(1500);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		new Select(driver.findElement(By.xpath(".//*[@class='selectpicker ng-pristine ng-valid'][@name='EmployeeType']"))).selectByVisibleText(Data[i][2].trim());
    	
	}
    
	List<WebElement> list = driver.findElements(By.tagName("option"));
	Iterator<WebElement> i1 = list.iterator();
	while(i1.hasNext()) {
	    WebElement wel = i1.next();    
	    //System.out.println(wel.getText()+"       done        "+Data[i][2]);
	    if(wel.getText().contains(Data[i][3].trim()))
	    {
	       System.out.println(wel.getText()+"     in if   done        "+Data[i][3]);
	       driver.findElement(By.xpath(".//*[@name='staff']")).click();
	       driver.findElement(By.xpath(".//*[@name='staff']")).sendKeys(wel.getText());
	       new Select(driver.findElement(By.xpath(".//*[@name='staff']"))).selectByVisibleText(wel.getText());
	       try {Thread.sleep(5000);} catch (InterruptedException e) {	e.printStackTrace();}
	       driver.findElement(By.xpath(".//*[@name='staff']")).sendKeys(wel.getText());
	       try {Thread.sleep(5000);} catch (InterruptedException e) {	e.printStackTrace();}
	       driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[4]/table/tbody/tr[2]/td[3]/input")).click();
	    	driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[4]/table/tbody/tr[3]/td[3]/input")).click();
	      driver.findElement(By.xpath(".//*[@id='obeData']/div/form/div[4]/table/tbody/tr[5]/td[3]/input")).click();
	      System.out.println("Before snap");
	      ExceptionHndeler.getScreen(Data[i][1].trim());
	      ExceptionHndeler.getScreen("permission");
	      System.out.println("After snap");
	   	driver.findElement(By.className("icon-save")).click();
	   	System.out.println("option is selected="+i);
	   	try {
			Thread.sleep(20000);
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
