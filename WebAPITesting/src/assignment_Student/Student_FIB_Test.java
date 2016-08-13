package assignment_Student;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.LaunchApp;



public class Student_FIB_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	public static void MCQ_Test(String[] str )
	{
		int i=1;
		String[] Qid=new String[100];
		while(!(str[i].equals("End")))
		{
			String []s={"m","n"};
			s=str[i].split(":");
	if(s[0].startsWith("Q"))
	{
		if(!s[1].equals("NA"))
		{
		if(s[0].equals("Q1"))
		{
			Qid=getQid();
			String m=Qid[0];
			System.out.println("id"+m);
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).sendKeys(s[1]);
		}
		if(s[0].equals("Q2"))
		{
			String m=Qid[1];
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).sendKeys(s[1]);
		}
		if(s[0].equals("Q3"))
		{
			Qid=getQid();
			String m=Qid[2];
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).sendKeys(s[1]);
		}
		if(s[0].equals("Q4"))
		{
			Qid=getQid();
			String m=Qid[3];
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).sendKeys(s[1]);
		}
		if(s[0].equals("Q5"))
		{
			Qid=getQid();
			String m=Qid[4];
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).sendKeys(s[1]);
		}
	}
	}
	i++;
	}
	}
	public static String[] getQid()
	{
		String[] Qid=new String [100];
		int i=0;
		List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[@class='ui-autocomplete-input']"));
		for(WebElement element : li){
			try
			{
				Qid[i]=element.getAttribute("id");
				i++;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return Qid;
		
	}
	
}
