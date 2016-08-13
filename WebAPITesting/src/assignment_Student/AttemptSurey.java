package assignment_Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

 public class AttemptSurey {
	 public static void main(String[] args) throws Exception
	{
	WebDriver driver = new FirefoxDriver();
	BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\file1.csv"));
    String input;
    String[] b = null; 
    
    String data[][] = new String[1000][9];
    String splitBy = ",";
    int count = 0;
    input = bufferedReader.readLine();
    driver.get("http://ec2-54-147-244-126.compute-1.amazonaws.com//");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
    
    while((input = bufferedReader.readLine()) != null)
    {             
      b  = input.split(splitBy);
      data[count][0] = b[0];
      data[count][1] = b[1];
      data[count][2] = b[2];
      data[count][3] = b[3];
      data[count][4] = b[4];
      data[count][5] = b[5];
      data[count][6] = b[6];
      data[count][7] = b[7];
      data[count][8] = b[8];
      
      
      count++;
     
        }
    
   bufferedReader.close();
   
   String path ;
   for(int i = 0; i< 56; i++)
   {
	   System.out.println(data[i][0]+" " + data[i][1]+" "+data[i][2] + " "+data[i][3]+" "+data[i][5]);
	driver.findElement(By.cssSelector("a.signin > span")).click();
    driver.findElement(By.id("UserName")).clear();
    driver.findElement(By.id("UserName")).sendKeys(data[i][0]);
    driver.findElement(By.id("Password")).clear();
    driver.findElement(By.id("Password")).sendKeys("s1s1s1");
    driver.findElement(By.id("signin_submit")).click();
    //new Select(driver.findElement(By.id("sectionId"))).selectByVisibleText("Test1");
      
    driver.findElement(By.linkText("Click here to take survey")).click();
    driver.findElement(By.linkText("Survey")).click(); 
   
    
    path = "//input[@id='"+data[i][1]+"\']";
    driver.findElement(By.xpath(path)).click();
    
    path = "//input[@id='"+data[i][2]+"\']";
    driver.findElement(By.xpath(path)).click();
   
    
    path = "//input[@id='"+data[i][3]+"\']";
    driver.findElement(By.xpath(path)).click();
    
    path = "//input[@id='"+data[i][4]+"\']";
    driver.findElement(By.xpath(path)).click();
    
    
    path = "//input[@id='"+data[i][5]+"\']";
    driver.findElement(By.xpath(path)).click();
    
    path = "//input[@id='"+data[i][6]+"\']";
    driver.findElement(By.xpath(path)).click();
    
    path = "//input[@id='"+data[i][7]+"\']";
    driver.findElement(By.xpath(path)).click();
    
    path = "//input[@id='"+data[i][8]+"\']";
    driver.findElement(By.xpath(path)).click();
    

   // driver.findElement(By.id("submitSurvey")).click();;
    
    //driver.switchTo().alert().accept();
    
   // driver.findElement(By.xpath("//div[@id='topnav']/span[2]/a/span/b")).click();
   // driver.findElement(By.xpath("//a[@id='logOut']")).click();
    
  
    
   }
	}
}
