package assignment_Student;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.LaunchApp;
import Data.Loger;


public class Student_MCQ_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	public static void MCQ_Test(String[] str )
	{
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        int i=1;
		String[] Qid=new String[100];
		int Qcount=0;
		while(!(str[i].equals("End")))
		{
			
			String []s={"m","n"};
			s=str[i].split(":");
			if(s[0].startsWith("End"))
			{
				break;
			}
         	if(s[0].startsWith("Q"))
	         {
         		Qcount++;
         		System.out.println("in question="+(Qcount));
	         	if(!s[1].equals("NA"))
	         	{
	     		try
		    	{
				try {
					Thread.sleep(3000);
					Qid=getQid();
					if(Qcount>Qid.length)
					{
						break;
					}
					else
					{
					String m=Qid[(Qcount-1)]+"_"+"0";
					System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
					Common.LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
					Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
					Assert.assertEquals(1, 1);
					}
					} catch (InterruptedException e) {e.printStackTrace();}
				
		/*if(s[0].equals("Q1"))
		{
			try {
				Thread.sleep(3000);
			
			Qid=getQid();
			String m=Qid[0]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			Common.LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q2"))
		{
			try {
				Thread.sleep( 5000);
			
			String m=Qid[1]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			Common.LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q3"))
		{
			try {
				Thread.sleep( 5000);
			
			Qid=getQid();
			String m=Qid[2]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			Common.LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q4"))
		{
			try {
				Thread.sleep( 10000);
			
			Qid=getQid();
			String m=Qid[3]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			Common.LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q5"))
		{
			try {
				Thread.sleep(5000);
			
			Qid=getQid();
			String m=Qid[4]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q6"))
		{
			try {
				Thread.sleep(5000);
			
			Qid=getQid();
			String m=Qid[5]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q7"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[6]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			Common.LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q8"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[7]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q9"))
		{
			try {
				Thread.sleep(5000);
		
			Qid=getQid();
			String m=Qid[8]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q10"))
		{
			
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[9]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q11"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[10]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			Common.LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q12"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[11]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q13"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[12]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q14"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[13]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q15"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[14]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			Common.LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q16"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[15]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q17"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[16]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q18"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[17]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q19"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[18]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			Common.LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q20"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[19]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q21"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[20]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		if(s[0].equals("Q22"))
		{
			try {
				Thread.sleep(5000);
			Qid=getQid();
			String m=Qid[21]+"_"+s[1];
			System.out.println("option selected id"+m+"time"+sdf.format(cal.getTime()));
			LaunchApp.driver.findElement(By.xpath(".//*[@id='"+m+"']")).click();
			Loger.LogEvent("Abale to attempt SQ Question="+m, "-Pass");
			 Assert.assertEquals(1, 1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}*/
				} catch (Exception e) {e.printStackTrace();
			Loger.LogEvent("Abale to attempt MCQ Question=", "-Fail");
			Assert.fail("Unable attempt MCQ Question");
			}
	}
	}
	if(Qcount==25)
	{
		
		try {
			LaunchApp.driver.findElement(By.xpath(".//*[@id='paginationBottom']/div[2]/ul/li[2]/a")).click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			LaunchApp.driver.findElement(By.xpath(".//*[@id='paginationBottom']/div[2]/ul/li[2]/a")).click();
			e.printStackTrace();
		}
	}
	i++;
	}
	}
	public static String[] getQid()
	{
		
		int i=0;
		List <WebElement> li=Common.LaunchApp.driver.findElements(By.xpath(".//*[@value='0']"));
		if(li.size()==0)
		{
			li=Common.LaunchApp.driver.findElements(By.xpath("//*[contains(text(),'Option 1')]"));
		}
		System.out.println("Count of question="+li.size());
		String[] Qid=new String [li.size()];
		for(WebElement element : li){
			try
			{
				Qid[i]=element.getAttribute("name");
				i++;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		Arrays.sort(Qid);
		return Qid;
		
	}
	
}
