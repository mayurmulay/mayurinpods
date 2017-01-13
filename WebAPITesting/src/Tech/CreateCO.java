package Tech;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;



import Data.Read_Data;
public class CreateCO {
 static int coCount=0;
	public static void main(String[] args) {
		
		String[][] Data=Read_Data.ReadData("COcreation.csv");
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://klu.inpods.com/");// Maximize the window.
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.id("UserName")).sendKeys("inpodssa@klu.in");
		driver.findElement(By.id("Password")).sendKeys("klucopo");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// Click on 'Sign In' button
		driver.findElement(By.id("signin_submit")).click();
		driver.findElement(By.xpath(".//*[@id='menu']/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='cssmenu']/ul/li[2]/a/span")).click();
		driver.findElement(By.xpath("//*[contains(text(),'COs')]")).click();
		//loop start here
		int m=0;
		 System.out.println(Data.length);
		for(int i=130;i<Data.length;i++)       
		{
			System.out.println("********************************************************************************************************i  ="+i);
			//String ProgramName=Data[i][0].trim();
			String CourseName=Data[i][0].trim();
			try
			{
				String pgname=getProgramname(CourseName).trim();
				pgname=pgname.trim();
			    new Select(driver.findElement(By.xpath(".//*[@id='program_select']"))).selectByVisibleText(pgname);
				//new Select(driver.findElement(By.xpath(".//*[@id='program_select']"))).selectByVisibleText("BTech-Electronics and Computer Engineering-2014");
		     Thread.sleep(50);
		     new Select(driver.findElement(By.xpath(".//*[@id='course_select']"))).selectByVisibleText(CourseName);
		     int j=1;
		     while(!(Data[i][j].trim().equals("End")))
		     {
		    	 Thread.sleep(5000);
		    	 String[] s=Data[i][j].split(":");
		    	 s[0]=s[0].trim();s[1]=s[1].trim();s[2]=s[2].trim();
		    	 System.out.println("type= "+s[0]+"name= "+s[1]+"Descr="+s[2]);
		    	 if(s[0].equals("CO")){System.out.println("type= "+s[0]+"name= "+s[1]+"Descr="+s[2]); m=CreateCo(s[1],s[2],driver); }
		    	 if(s[0].equals("TLO")){System.out.println("type= "+s[0]+"name= "+s[1]+"TLOname= "+s[2]+"Descr="+s[3]);  m=CreateTLO(s[1],s[2],s[3],driver);}
		    	 j=j+m;
		    	 System.out.println("Error in data  ="+CourseName);
		     }
		     Thread.sleep(1000);
		     
		    
		     
			}
			
		     catch(Exception e){
		    	 /*try
					{
					
		    	 System.out.println("publishing ");
			     driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div[1]/div[4]/div/input")).click();
			     Thread.sleep(1000);
			     System.out.println("publishing 2 ");
			     driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div[1]/div[4]/span/button")).click();
			     Thread.sleep(1000);
			     driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/fieldset/span[2]/button[1]")).click();
			     Thread.sleep(5000);*/
		    	 System.out.println("Error in data  ="+CourseName);}
		    	 // catch(Exception e1){e1.printStackTrace();}}
		}

	}
	public static int CreateCo(String COName,String CODescr,WebDriver driver)
	{
		try
		{
	     Thread.sleep(50);
	     driver.findElement(By.xpath(".//*[@class='fa fa-plus-square']")).click();
	     Thread.sleep(50);
	     driver.findElement(By.xpath(".//*[@id='courseObj_name']")).sendKeys(COName);
	     Thread.sleep(50);
	     driver.findElement(By.xpath(".//*[@id='courseObj_content']")).sendKeys(CODescr);
	     Thread.sleep(50);
	    // driver.findElement(By.xpath(".//*[@class='icon-save']")).click();
	     Thread.sleep(50);
	     driver.findElement(By.xpath(".//*[@class='btn btn-primary']//*[@class='icon-save']")).click();
	   //  System.out.println("size="+li.size());
	  //   li.get(2).click();
	    
		 coCount++;
		 System.out.println("Co created no="+coCount+"  name"+COName);
		 return 1;
		}
		catch(Exception e){System.out.print("Error   ="+COName);
		//e.printStackTrace();  
		return 0;}
		
	}
	public static int CreateTLO(String COName,String TLOname,String CODescr,WebDriver driver)
	{
		try
		{
		 
	     driver.findElement(By.xpath(".//*[contains(text(),'"+COName+"')]/following-sibling::*[2]/*[1]/div[3]/span/a")).click();
	     Thread.sleep(5000);
	     driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div[4]/div/div/div[2]/div[1]/div/input[2]")).sendKeys(COName+"_"+TLOname);
	     Thread.sleep(50);
	     driver.findElement(By.xpath(".//*[@id='obj_content']")).sendKeys(CODescr);
	     Thread.sleep(50);
	     new Select(driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div[4]/div/div/div[2]/div[3]/div/div/select"))).selectByIndex(2);
	    // driver.findElement(By.xpath(".//*[@class='icon-save']")).click(); .//*[@name='rubric']
	     Thread.sleep(50);
	     driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div[4]/div/div/div[3]/button[2]")).click();//save button
	 
	     //  System.out.println("size="+li.size());
	  //   li.get(2).click();
	    
		 coCount++;
		 System.out.println("TLO created no="+coCount+"  name"+TLOname);
		 return 1;
		}
		catch(Exception e){System.out.print("Error   ="+COName);
		//e.printStackTrace(); 
		return 0;}
	}
	
	public static String getProgramname(String str)
	{
		String pgname="mm ";
		 System.out.println("pgname="+pgname+"   course="+str);
		int m=0;
		String[][] ProCO=Read_Data.ReadData("Programnamesandcoursesassociated.csv");
		for(int i=1;i<600;i++)
		{
			//System.out.println(i);
			ProCO[i][1]=ProCO[i][1].trim();
		    if(ProCO[i][1].equals(str))
		    {
		    	m=1;	
		    	pgname=ProCO[i][0].trim();
		    	break;
		    }
	     }
		 System.out.println("pgname="+pgname+"   course="+str);
		return pgname;
	}

}
