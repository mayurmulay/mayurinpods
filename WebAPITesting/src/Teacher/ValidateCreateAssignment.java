package Teacher;

import Data.Loger;
import Data.Read_Data;

	import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Gototab;
import Common.LaunchApp;
import Data.ExceptionHndeler;


	public class ValidateCreateAssignment {
		public static String AssignmentTypeDoc;
		public static String QuestionType;
		public static String AssignmentQueDoc;
		public static String assType="mayur";
		@Test
		@Parameters("AssignmentListFile")
		public static void Validate(String AssignmentListFile) {
			String[] args1 = new String[10];
			
			  args1[0]=(String) "Manage Course";
				 Gototab.main(args1);
				 ValidateCreateAssignment e=new ValidateCreateAssignment();
	             e.create(AssignmentListFile);
			

		}

		public static void main(String[] args) {
			String[] args1 = new String[10];
			
			  args1[0]=(String) "Manage Course";
				 Gototab.main(args1);
				 ValidateCreateAssignment e=new ValidateCreateAssignment();
	             e.create("AssignmentValidation.csv");
		}
		public void create(String filename)
		{
		
			String[][] Ass=Read_Data.ReadData(filename);
			 int j=1;
			  int no=10;
			  int []id=null;
			 while(!Ass[j][0].equals("End") && j<no)//Code for creating assignment and open it in edit mode 
			  {
				 String[] args1 = new String[10];
				 args1[0]=(String) "Manage Course";
				 Gototab.main(args1);
				 System.out.println("number of assignment  "+Ass[j][0]);
				 System.out.println(".//*[.='"+Ass[j][0].trim()+"' and contains(@href,'/AssignmentList/StudentListForAssignment')]/../..");
				 String str=" ";
				 
				 try {
					 Thread.sleep(5000);
					 List <WebElement> li=LaunchApp.driver.findElements(By.xpath(".//*[.='"+Ass[j][0].trim()+"' and contains(@href,'/AssignmentList/StudentListForAssignment')]/../.."));
					 System.out.println("number of assignment  "+li.size());
					 int IdByName= Integer.parseInt(li.get(0).getAttribute("id"));
				     str=Integer.toString(IdByName).trim();
				//manage course testing
				 if(LaunchApp.driver.findElement(By.xpath(".//*[@id='"+str+"']/td[3]")).getText().equals(Ass[j][1].trim()))
				  {
					  Loger.LogEvent("Assignment state change after publish-", "Pass");
					  
				  }
				  else
				  {
					  Loger.LogEvent("Assignment state change after publish-", "Fail");
					 
				  }
				 System.out.println("assignment id "+IdByName);
				  if(LaunchApp.driver.findElement(By.xpath(".//*[@id='"+str+"']/td[4]")).getText().equals(Ass[j][3].trim()))
				  {
					  Loger.LogEvent("Access-Control-"+Ass[j][3], "-Pass");
				  }
				  else
				  {
					  Loger.LogEvent("Access-Control-"+Ass[j][3], "-Fail");
				  }
				  if(LaunchApp.driver.findElement(By.xpath(".//*[@id='"+str+"']/td[1]")).getText().equals(Ass[j][2].trim()))
				  {
					  Loger.LogEvent("Assignment type"+Ass[j][2], "-Pass");
				  }
				  else
				  {
					  Loger.LogEvent("Assignment type"+Ass[j][2], "-Fail");
				  }
				  if(LaunchApp.driver.findElement(By.xpath(".//*[@id='"+str+"']/td[5]")).getText().equals(Ass[j][5].trim()))
				  {
					  Loger.LogEvent("Assignment Versoin"+Ass[j][5], "-Pass");
				  }
				  else
				  {
					  Loger.LogEvent("Assignment Versoin"+Ass[j][5], "-Fail");
				  }
				  if(LaunchApp.driver.findElement(By.xpath(".//*[@id='"+str+"']/td[10]")).getText().equals(Ass[j][4].trim()))
				  {
					  Loger.LogEvent("Delete option available "+Ass[j][4], "-Pass");
				  }
				  else
				  {
					  Loger.LogEvent("Delete option available "+Ass[j][4], "-Fail");
				  }
				  LaunchApp.driver.findElement(By.xpath(".//*[@id='"+str+"']//*[contains(text(),'Edit')]")).click();
					Thread.sleep(50);
					try
					{
					execute(Ass[j]);}catch (Exception e) {
						ExceptionHndeler.Log("execute","Verifying data", e);
					}
				  
			   } catch (Exception e) {
				  e.printStackTrace();
				 
				 System.out.println("mm"+e.getMessage());
		    	  Loger.LogEvent("Assignment Creation", "new Assignment with name Get created- case failed");
		    	  ExceptionHndeler.Log("Assignment Creation","Assignment Editing", e);
		    	  
		     }
			
				 j++;
			  }
		}
		void execute(String str[])
		{
			
			System.out.println("in exicute=");
			int i=0;
			try {
				Thread.sleep(100);
				Alert alert = LaunchApp.driver.switchTo().alert();
				   alert.accept();
				Thread.sleep(500);
			} catch (Exception e) {
				ExceptionHndeler.Log("String reading","Assignment Editing", e);
			}
			while(!str[i].equals("End"))
			{
			  try
			  
			  {
				  System.out.println("in while of execute"+i);
				  LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
				String[] s=str[i].split(":");
				 s[0].trim();
				 s[1].trim();
				 System.out.println("in sdfkswhile="+i+" "+s[1]);
				 System.out.println("insdkl while="+i+" "+s[0]);
				if(s[0].equals("Name"))
				{	
		         	Name(s);
				}
				if(s[0].equals("Assignment Type"))
				{
					 assType=s[1];
					if(!(s[1].equals("External")))
					{AssignmentType(s);}
				}
				
				if(s[0].equals("Start Date"))
				{
					 String []hm=s[1].split(";");
					 if( ValidateCreateAssignment.assType.equals("Test")|| ValidateCreateAssignment.assType.equals("Exam"))
					 {
					 
					  try
					  {
					 // setStartdate(hm[0],"Start");
					  }catch(Exception e){ExceptionHndeler.Log("Satrt Date","Assignment Editing", e);}
					  String[] tim=hm[1].split("-");
					 }
					 else
					 {
						// setStartdate(s[1],"Start");
					 }
				   
				}
				if(s[0].equals("Due Date"))
				{
					 String []hm=s[1].split(";");
					 if( ValidateCreateAssignment.assType.equals("Test")|| ValidateCreateAssignment.assType.equals("Exam"))
					 {
					  try
					  {
					 // setStartdate(hm[0],"End");
					  }catch(Exception e){ExceptionHndeler.Log("Due date","Assignment Editing", e);}
					  String[] tim=hm[1].split("-");
					  System.out.println("print data "+hm[0]);
					 }
					 else
					 {
					//	 setStartdate(s[1],"End");
					 }
				    System.out.println("date="+s[1]);
				   
				}
				if(s[0].equals("Security / Control"))
				{
					SecurityControl(s);
				}
				
				if(s[0].equals("Duration"))
				{
					 Duration(s);
				}
				if(s[0].equals("Association"))
				{
				  Association(s);
				}
				if(s[0].equals("AssignmentTypeDoc"))
				{
					
					
					 ValidateCreateAssignment.AssignmentTypeDoc=s[1].trim();
					
				}
				if(s[0].equals("AssignmentQueDoc"))
				{
					 ValidateCreateAssignment.AssignmentQueDoc=s[1].trim();
					
				}
				if(s[0].equals("Question Type"))
				{
					 ValidateCreateAssignment.QuestionType=s[1].trim();
				}
				
			  }
			  catch(Exception e)
			  {
				//  e.printStackTrace();
			  }
			  i++;
			}
			ValidateEditActivity.main(str);
			
		}
		
		public void setStartdate(String Mdate,String date1 )
		{
			try
			{
			System.out.println("in date creation mayur"+Mdate);
			 GregorianCalendar date = new GregorianCalendar();
		     int  month = date.get(Calendar.MONTH);
		     int  year = date.get(Calendar.YEAR);
		     int day1= date.get(Calendar.DAY_OF_MONTH);
		     String todayDate=month+"/"+day1+"/"+year;
		     String[] div1=Mdate.split(";");
		     int MD=Integer.parseInt(div1[0]);
		     Mdate=GetNextdate(todayDate,MD);
		     System.out.println("dayte="+Mdate);
		      div1=Mdate.split("/");
			 month=(Integer.parseInt(div1[0]))-(month);
			 String  day =div1[1];
			 year=(Integer.parseInt(div1[2])-(year));
			 month=month+(year*12);
			 System.out.println("End date= "+Mdate);
			 String date2="";
			 if(date1.equals("Start"))
			 {
			   date2= LaunchApp.driver.findElement(By.xpath(".//*[@name='startDateRow']/td[2]/input")).getText();
			 }
			 else
			 {
				 date2= LaunchApp.driver.findElement(By.xpath(".//*[@name='dueDateTimeRow']/td[2]/input")).getText();
			 }
			 System.out.println("Date1="+Mdate+"Date2="+ date2);
			String  dt=(String) date2.subSequence(0,9);
			System.out.println("Date1="+Mdate+"Date2="+dt);
			if(Mdate.equals(dt.trim()))
			{
				Loger.LogEvent("Start date"+Mdate, "-Pass");
			}
			else
			{
				Loger.LogEvent("Start date"+Mdate, "-Fail");
			}	
		  }
		catch(Exception e){e.printStackTrace();}
		}
		
		public static void Name(String[] s)
		{
		     String s1=LaunchApp.driver.findElement(By.xpath(".//*[@name='title']")).getText().trim();
		     if(s1.equals(s[1]))
		     {
		    	  Loger.LogEvent("Name"+s[1], "-Pass");
		     }
		  else
		     {
			  Loger.LogEvent("Name"+s[1],"-Fail");
		     }
		   
		}
		public static void AssignmentType(String[] s)
		{
			 String s1=LaunchApp.driver.findElement(By.xpath(".//*[@name='lblActivityCategoryType']")).getText();
					 if(s1.equals(s[1]))
				     {
				    	  Loger.LogEvent("Assignmnet type"+s[1], "-Pass");
				     }
				  else
				     {
					  Loger.LogEvent("Assignmnet type"+s[1],"-Fail");
				     }
		}

		public static void SecurityControl(String [] s) 
		{
			String []hm=s[1].split(";");
			 
		    String s1= LaunchApp.driver.findElement(By.xpath(".//*[@name='lblexamControlType']")).getText().trim();
		    if(s1.equals(hm[0]))
		     {
		    	  Loger.LogEvent("SecurityControl"+s[1], "-Pass");
		     }
		  else
		     {
			  Loger.LogEvent("SecurityControl"+s[1],"-Fail");
		     }
		}
		public static void Duration(String [] s)
		{
			String []hm={"2","0"}; hm=s[1].split(";");
			try {
		String s1= LaunchApp.driver.findElement(By.xpath(".//*[@name='lblMinutes']")).getText().trim();
				 if(s1.equals(hm[1]))
			     {
			    	  Loger.LogEvent("Duration min"+s[1], "-Pass");
			     }
			  else
			     {
				  Loger.LogEvent("Duration Min"+s[1],"-Fail");
			     }

			 s1=LaunchApp.driver.findElement(By.xpath(".//*[@name='lblHours']")).getText().trim();
			 if(s1.equals(hm[0]))
		     {
		    	  Loger.LogEvent("Duration hour"+s[1], "-Pass");
		     }
		  else
		     {
			  Loger.LogEvent("Duration hour"+s[1],"-Fail");
		     }
			    
		} catch (Exception e) {
			 ExceptionHndeler.Log("Duration-hour","Assignment Editing", e);
		}
	   
		}
		public static void Association(String [] s)
		{
			String []hm=s[1].split(";");
			 hm[0].trim();
			 String s1=LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssociationType']")).getText().trim();
			 if(s1.equals(hm[0]))
		     {
		    	  Loger.LogEvent("Association"+s[1], "-Pass");
		     }
		  else
		     {
			  Loger.LogEvent("Association"+s[1],"-Fail");
		     }
		     if(hm[0].equals("Chapter"))
		     {
		      s1=LaunchApp.driver.findElement(By.xpath(".//*[@name='moduleName']")).getText().trim();
		      if(s1.equals(hm[1].trim()))
			     {
			    	  Loger.LogEvent("Association 2"+hm[1], "-Pass");
			     }
			  else
			     {
				  Loger.LogEvent("Association 2"+hm[1],"-Fail");
			     }
		     }
		     if(hm[0].equals("lesson"))
		     {
		    	 s1=LaunchApp.driver.findElement(By.xpath(".//*[@name='lesson']")).getText().trim();
			      if(s1.equals(hm[1].trim()))
				     {
				    	  Loger.LogEvent("Association 2"+hm[1], "-Pass");
				     }
				  else
				     {
					  Loger.LogEvent("Association 2"+hm[1],"-Fail");
				     }
		     } 
		}
	public static String GetNextdate(String dt,int days)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			System.out.println("next date to before set ="+dt);
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf.parse(dt));
				c.add(Calendar.DATE, days);  // number of days to add
				dt = sdf.format(c.getTime());  // dt is now the new date
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				ExceptionHndeler.Log("GroupAssignment","Assignment Editing", e);
			}
			
			System.out.println("next date to set ="+dt);
			return dt;
			
		}
	}

