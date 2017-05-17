package Tech;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.LaunchApp;
import Common.Login;
import Data.Read_Data;




public class ResetPasswordByAdmin {
	static String Path="";
	static String name="";
	static String password="";
	public static void main(String[] str)
	{
		Readdata();
	}
	public static void Readdata()
	{
	String[][] URL=new String[1000][1000];
	//URL=Read_Data.ReadData("URL.csv");
	
	//Path=URL[0][0].trim();
	Path="http://localhost/";
	String[][] Data=Read_Data.ReadData("PasswordChangeInBulk\\Usernamechange.csv");
	LaunchApp.main(URL[0]);
	Login.main(Data[0]);
	//LaunchApp.driver.findElement(By.xpath("//*[contains(text(),'Admin')]")).click();
	try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
	//LaunchApp.driver.findElement(By.xpath("//*[contains(text(),'ResetPassword')]")).click();
	try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
	//LaunchApp.driver.findElement(By.xpath("//*[contains(text(),'Search')]")).click();
	for(int i=1;i<Data.length;i++)
	{
		try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
			name=Data[i][0].trim();
			password=Data[i][1].trim();
			changePassword(name,password);
			
			
	}
	}
	@Test
	@Parameters({"name","password"})
    public static void changePassword(String name,String password)
    {
    	try {
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='userName']")).clear();
    	
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='userName']")).sendKeys(name.trim());
    
    	LaunchApp.driver.findElement(By.xpath(".//*[@ng-disabled='searchUserForm.userName.$error.required']")).click();
    	
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='password']")).clear();
    	
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='password']")).sendKeys(password.trim());
    
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='password_confirm']")).clear();
    	
    	LaunchApp.driver.findElement(By.xpath(".//*[@name='password_confirm']")).sendKeys(password.trim());
    	
    	LaunchApp.driver.findElement(By.xpath(".//*[@ng-disabled='updateUserForm.password.$error.required || updateUserForm.password_confirm.$error.required || updateUserForm.password.$error.minlength']")).click();
    	Thread.sleep(5000);
    	Alert alert = LaunchApp.driver.switchTo().alert();
   	    alert.accept();
   	 System.out.println("password changed ="+name);
    	} catch (Exception e) {System.out.println("user not fount ="+name);}
    }
}
