package Teacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

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
import Data.Loger;
import Data.Read_Data;



public class AssignmentCreation {
	public static String AssignmentTypeDoc;
	public static String QuestionType;
	public static String AssignmentQueDoc;
	public static String assType="mayur";
	
	@Test
	@Parameters("AssignmentListFile")
	public static void AssignmentCreationDataCreation(String AssignmentListFile)
	{
		String[] args1 = new String[10];
		  args1[0]=(String) "Manage Course";
		 Gototab.main(args1);
			 AssignmentCreation e=new AssignmentCreation();
           e.create(AssignmentListFile);
	}
	public static void main(String[] args) {
		String[] args1 = new String[10];
		  args1[0]=(String) "Manage Course";
			// Gototab.main(args1);
			 AssignmentCreation e=new AssignmentCreation();
			 String AssignmentListFile="AssignmentCreation.csv";
             e.create(AssignmentListFile);
	}
	public void create(String AssignmentListFile)
	{
	
       String[][] Ass=Read_Data.ReadData(AssignmentListFile);
	       int j=0;
		//  System.out.println("Enter number of lesson you want to create");
		//  Scanner sc=new Scanner(System.in);
		//  int no=sc.nextInt();
		  int no=18;
		 while(!Ass[j][0].equals("End") && j<no)//Code for creating assignment and open it in edit mode 
		  {
			 try
			 {
			  System.out.println("Creating assignment ="+j+" of type"+Ass[j][0]);
			  String []args1=new String[12];
			  args1[0]=(String) "Manage Course";
				 Gototab.main(args1);
				 System.out.println("Creating assignment ="+j+" of type"+Ass[j][0]);
			  CreateAssignment.main(Ass[j]);
			  try {
				Thread.sleep(50);
			      } catch (InterruptedException e) {
			    	  ExceptionHndeler.Log("String reading","Assignment Editing", e);
			      }
//**************************code bellow is to find and open the newlly created assignment which commented due to new feature *************************************** 
	    /*  List <WebElement> li=LaunchApp.driver.findElements(By.name("assignmentEdit"));
		  for(int i=0;i<li.size();i++)
		  {
			  id[i]=Integer.parseInt(li.get(i).getAttribute("id"));//getting all assignment ID 
		  }
		  Arrays.sort(id);
		 
		  int m=(id.length)-1;//picking most recent Id
			 str=Integer.toString((id[m]));
			 try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					//e.printStackTrace();
					}
			 LaunchApp.driver.findElement(By.xpath(".//*[@id='"+str+"']//*[contains(text(),'Edit')]")).click();
			 try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}*/
//***********************************************************************end*********************************************************************************			  
			 execute(Ass[j]);
		     
		     
		  }
			 catch(Exception e){
				 System.out.println("Error="+j);
				 ExceptionHndeler.Log("Assignment Creation","Assignment Editing", e);
				 e.printStackTrace();
			 }
			 j++;
		  }
	}
	void execute(String str[])
	{
		
		System.out.println("in exicute=");
		int i=0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			ExceptionHndeler.Log("String reading","Assignment Editing", e);
		}
		while(!str[i].equals("End"))
		{
			
			try {
				Thread.sleep(500);
				 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
				 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
				// LaunchApp.driver.findElement(By.xpath(".//*[@id='middle']/div[3]/h3")).click();
				// LaunchApp.driver.findElement(By.xpath(".//*[@id='middle']/div[3]/h3")).click();
				
			} catch (InterruptedException e) {
				ExceptionHndeler.Log("String reading","Assignment Editing", e);
			}
			System.out.println("in while="+i+" "+str[i]);
		  try
		  {
			String[] s=str[i].split(":");
			 s[0].trim();
			 s[1].trim();
			 System.out.println("in sdfkswhile="+i+" "+s[1]);
			 System.out.println("insdkl while="+i+" "+s[0]);
			if(s[0].equals("Name"))
			{
				Name(s);
		     
			}
			if(s[0].equals("AddQuestion"))
			{
				AddQuestion(s);
		     
			}
			if(s[0].equals("Assignment Type"))
			{
				 assType=s[1];
				if(!(s[1].equals("External")))
				{System.out.println("assignmenttypeIN=");AssignmentType(s);}
			}
			
			if(s[0].equals("Start Date"))
			{
				 String []hm=s[1].split(";");
				 Loger.LogEvent("Start Date","Start date Selecting");   
				 System.out.println("in start date date="+s[1]);
				 if(AssignmentCreation.assType.equals("Test")||AssignmentCreation.assType.equals("Exam"))
				 {
				  LaunchApp.driver.findElement(By.xpath(".//*[@name='startDate']")).click();
				  Thread.sleep(1000);
				 // LaunchApp.driver.findElement(By.xpath(".//*[@class='datepicker']")).click();
				  try
				  {
				   setStartdate(hm[0]);
				  
				  }catch(Exception e){ExceptionHndeler.Log("Satrt Date","Assignment Editing", e);e.printStackTrace();}
				  String[] tim=hm[1].split("-");
				  System.out.println("print data "+hm[1]);
				 
				  try
				  {
					  System.out.println("print data hr "+tim[0]+" Min "+tim[1]);
					 SetTime(Integer.parseInt(tim[0]),Integer.parseInt(tim[1]));
				  }catch(Exception e){ExceptionHndeler.Log("Set time","Assignment Editing", e);e.printStackTrace();}
				 
				  System.out.println("print data "+hm[0]);
				 }
				 else
				 {
					 LaunchApp.driver.findElement(By.xpath(".//*[@name='assignedDate']")).click();
					// LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']")).click();
				//	 setStartdate(s[1]);
				 }
			    System.out.println("date="+s[1]);
			    Loger.LogEvent("Start Date ","Start Date Saved="+s[1]);
			     
			}
			if(s[0].equals("Due Date"))
			{
				 String []hm=s[1].split(";");
				 Loger.LogEvent("Due date","Due date Selecting");   
				 System.out.println("in start date date="+s[1]);
				 if(AssignmentCreation.assType.equals("Test")||AssignmentCreation.assType.equals("Exam"))
				 {
				  LaunchApp.driver.findElement(By.xpath(".//*[@name='dueDate']")).click();
				  //LaunchApp.driver.findElement(By.xpath(".//*[@class='datepicker-days']")).click();
				  try
				  {
				    setStartdate(hm[0]);
				  
				  }catch(Exception e){ExceptionHndeler.Log("Due date","Assignment Editing", e);e.printStackTrace();}
				  String[] tim=hm[1].split("-");
				  System.out.println("print data "+hm[1]);
				 
				  try
				  {
					  System.out.println("print data hr "+tim[0]+" Min "+tim[1]);
					 SetTime(Integer.parseInt(tim[0]),Integer.parseInt(tim[1]));
				  }catch(Exception e){ExceptionHndeler.Log("Due date","Assignment Editing", e);e.printStackTrace();}
				 
				  System.out.println("print data "+hm[0]);
				 }
				 else
				 {
					 LaunchApp.driver.findElement(By.xpath(".//*[@name='dueDate']")).click();
					//; LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-datepicker']")).click();
					 setStartdate(s[1]);
				 }
			    System.out.println("date="+s[1]);
			    Loger.LogEvent("Due Date ","Due Date Saved="+s[1]);
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
			if(s[0].equals("Points for question"))
			{
				PointsForQuestion(s);
			}
			if(s[0].equals("Show answers after due date"))
			{
				ShowAnswerAfterDue(s);
			}
			if(s[0].equals("Additional Information"))
			{
				
				AdditionalInformation(s);
			}
			if(s[0].equals("Group Assignment/project"))
			{
				GroupAssignment(s);
				
			}
			if(s[0].equals("Accept late"))
			{
				AcceptLate(s);
				
			}
			if(s[0].equals("AssignmentTypeDoc"))
			{
				Loger.LogEvent("Doc","AssignmentTypeDoc Selecting"); 
				
				AssignmentCreation.AssignmentTypeDoc=s[1].trim();
				Loger.LogEvent("AssignmentTypeDoc","AssignmentTypeDoc Selected"+s[1]); 
				
			}
			if(s[0].equals("AssignmentQueDoc"))
			{
				Loger.LogEvent("AssignmentQueDoc","AssignmentQueDoc Selecting"); 
				AssignmentCreation.AssignmentQueDoc=s[1].trim();
				
				Loger.LogEvent("AssignmentQueDoc","AssignmentQueDoc Selected"+s[1]); 
			}
			if(s[0].equals("Question Type"))
			{
				Loger.LogEvent("Question Type","Question Type Selecting"); 
				AssignmentCreation.QuestionType=s[1].trim();
				Loger.LogEvent("Question Type","Question Type Selected"+s[1]); 
			}
			
		  }
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		  i++;
		}
		EditActivity.main(str);
		
	}
	public void mapCO(String co_name)
	{
		List <WebElement> li=LaunchApp.driver.findElements(By.name("lblConcept"));
		 System.out.println("mayur1"+li.size());
		for(WebElement element : li){
			try
			{
			element.click(); 
			LaunchApp.driver.findElement(By.xpath("//*[@class='dynatree-expander']")).click(); 
			LaunchApp.driver.findElement(By.xpath("//*[@title='"+co_name+"']")).click();                                                         //title='co2'
			}catch (Exception e) {
				ExceptionHndeler.Log("String reading","Assignment Editing", e);
			}
		}
	}
	public static void setStartdate(String Mdate)
	{
		try
		{
		System.out.println("in date creation mayur"+Mdate);
		 GregorianCalendar date = new GregorianCalendar();
	     int  month = date.get(Calendar.MONTH);
	     int  year = date.get(Calendar.YEAR);
	     int day1= date.get(Calendar.DAY_OF_MONTH);
	     String todayDate=(month+1)+"/"+day1+"/"+year;
	     String[] div1=Mdate.split("/");
	     int MD=Integer.parseInt(div1[0]);
	     
	     Mdate=GetNextdate(todayDate,MD);
	     System.out.println("dayte="+Mdate);
	      div1=Mdate.split("/");
		 month=(Integer.parseInt(div1[0]))-(month);
		 String  day =div1[1];
		 year=(Integer.parseInt(div1[2])-(year));
		 month=month+(year*12);
		 System.out.println("End date= "+Mdate);
	//	 LaunchApp.driver.switchTo().frame(LaunchApp.driver.findElement(By.name("iFrameTitle")));
		 
		// List <WebElement>li=LaunchApp.driver.findElements(By.xpath("html/body/div[8]/ul/li[1]/div/div[1]/table/tbody/tr/td"));
		 //LaunchApp.driver.findElement(By.xpath(".//*[@class='next']")).click();
		 if(month>0)
		 {
		 for(int i=0;i<month-1;i++)
		 {
			 LaunchApp.driver.findElement(By.xpath("html/body/div[8]/ul/li[1]/div/div[1]/table/thead/tr[1]/th[3]")).click();
		 }
		 }
		 if(month<0)
		 {
		 for(int i=0;i<month;i++)
		 {
			 LaunchApp.driver.findElement(By.xpath("html/body/div[6]/ul/li[1]/div/div[1]/table/thead/tr[1]/th[1]")).click();
		 }
		 }                                                           
		// List <WebElement>li=LaunchApp.driver.findElements(By.xpath("html/body/div[8]/ul/li[1]/div/div[1]/table/tbody/tr/td"));
       //   int i=0;	
         // System.out.println("size"+li.size());
		 int tempDate=Integer.parseInt(day.trim());
		 StringBuilder day3=new StringBuilder("0");
		 if(tempDate<10)
		 {
			 day3.setCharAt(0,day.charAt(1));
		 }
          System.out.println(".//*[@class='day' and .='"+day.trim()+"']");
        List  <WebElement> datedemo = LaunchApp.driver.findElements(By.xpath(".//*[@class='day' and .='"+day3+"']"));
        
        System.out.println(datedemo.size());
        try
        {
        	datedemo.get(0).click();
        }
        catch(Exception e){try{datedemo.get(1).click();}catch(Exception e1){System.out.println("in cache demo");  datedemo.get(2).click();}}
         
          
		/* for(WebElement element : li)  
		 {
			 //System.out.println("text= "+i+"   "+element.getText().trim());
			
				try
				{
					System.out.println("Before if text= "+i+"   "+(li.get(i)).getText().trim());
					String Tdate=li.get(i).getText().trim();
					if(i==10)
					{
						li.get(i).click();
					}
					if(!Tdate.equals(""))
					{
						System.out.println("text= "+i+"   "+li.get(i).getText().trim());
					  int tempDate=Integer.parseInt(Tdate);
				    	/*if(tempDate<10)
					    {
						Tdate="0"+Tdate;       the Code is removed becouse of new UI which do not appent singile characto with "0"
					    }/
					
					   if(Tdate.equals(day.trim()))
					   {
						String str=li.getClass().toString();
						System.out.println("class="+ str);
						
						if(str.trim().equals("day"))
						{
						li.get(i).click();
						System.out.println("mayur date set"+day);
						break;
						}
					   }
					}
					
				}
				catch(Exception e)
				{
					ExceptionHndeler.Log("String reading","Assignment Editing", e);
					e.printStackTrace();
				}
				i++;
		 }*/  // Code is commented becouse of new UI Date time picker is changed 
		 Thread.sleep(1000);
		 
	}
	catch(Exception e){e.printStackTrace();}
	}
	public void SetTime(int hr,int min)
	{
		/*System.out.println("time is settting  hr"+hr+"min"+min);  //
		LaunchApp.driver.findElement(By.xpath(".//*[@class='glyphicon glyphicon-time']")).click();
		
		
		WebElement slider = LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/dl/dd[2]/div"));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions moveSlider = new Actions(LaunchApp.driver);
		Action action = moveSlider.dragAndDropBy(slider, hr, 5).build();
		action.perform();
		WebElement slider1 = LaunchApp.driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/dl/dd[3]/div"));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions moveSlider1 = new Actions(LaunchApp.driver);
		Action action1 = moveSlider1.dragAndDropBy(slider1, min, 5).build();
		action1.perform();
		System.out.println("time is set ");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LaunchApp.driver.findElement(By.xpath("html/body/div[6]/div[3]/button[2]")).click();*/  //Old code before changing new UI
		List <WebElement> timebtn=LaunchApp.driver.findElements(By.xpath(".//*[@class='picker-switch accordion-toggle']"));
		try{timebtn.get(0).click();}catch(Exception e){timebtn.get(1).click();}
		
		List <WebElement> Hrbtn=LaunchApp.driver.findElements(By.xpath(".//*[@data-action='showHours' and .='00']"));
		try{Hrbtn.get(0).click();}catch(Exception e){Hrbtn.get(1).click();}
		
		LaunchApp.driver.findElement(By.xpath("html/body/div[7]/ul/li[3]/div/div[2]/table/tbody/tr[1]/td[1]")).click();//change 
		
		try{List <WebElement> Hrbtn1=LaunchApp.driver.findElements(By.xpath(".//*[@class='timepicker-hour' and .='00']"));
		try{Hrbtn1.get(0).click();}catch(Exception e){Hrbtn1.get(1).click();}
		}catch(Exception e)
		       { List <WebElement> Hrbtn1=LaunchApp.driver.findElements(By.xpath(".//*[@class='timepicker-hour' and .='23']"));
		    		   try{Hrbtn1.get(0).click();}catch(Exception e1){Hrbtn1.get(1).click();}
		       }
		 List <WebElement> Hr=LaunchApp.driver.findElements(By.xpath(".//*[@class='hour' and .='01']"));
		try{Hr.get(0).click();}
		catch(Exception e){try{Hr.get(1).click();}
		catch(Exception e1){try{Hr.get(2).click();}
		catch(Exception e2){Hr.get(3).click();}}}
		
		//Code has to 
		
		
		
		
	}
	public static void Name(String[] s)
	{
		Loger.LogEvent("Title","Editing Title");
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='title']")).click();
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='value']")).clear();
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='value']")).sendKeys(s[1]);  
	     Loger.LogEvent("Title","Title Saved="+s[1]);
	}
	public static void AssignmentType(String[] s)
	{
		System.out.println("Assignment type="+AssignmentCreation.assType);
		 Loger.LogEvent("Assignment Type","Assignment Type Selecting ");
		 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblActivityCategoryType']")).click();
		 try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='value']"))).selectByValue(s[1]);
		 assType=s[1];
		 System.out.println("Assignment type="+AssignmentCreation.assType);
		 Loger.LogEvent("Assignment Type","Assignment Type Selected="+AssignmentCreation.assType);
	}

	public static void SecurityControl(String [] s) 
	{
		String []hm=s[1].split(";");
		 Loger.LogEvent("Security / Control","Security / Control Selecting"); 
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='lblexamControlType']")).click();
	     try {
	    	
				Thread.sleep(100);
				new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='lblexamControlType']//*[@name='value']"))).selectByVisibleText(hm[0]);
				Thread.sleep(100);	
	     } catch (Exception e) {
				System.out.println("in catch"+hm[0]);
				 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblexamControlType']")).click();
				LaunchApp.driver.findElement(By.xpath(".//*[@name='lblexamControlType']//*[@name='value']")).sendKeys(hm[0]);
			}
	     if(hm[0].equals("Group access codes"))
        {
	    	 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
    		 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
	    	 try {
				Thread.sleep(30);
			} catch (InterruptedException e1) {
				ExceptionHndeler.Log("Group access codes","Assignment Editing", e1);
			}
	    	 System.out.println("in grop assignment in="+hm[1]);
	    	 try
	    	 {
	    		
	    		 LaunchApp.driver.findElement(By.xpath(".//*[@name='secretGACode']")).click();
       	 LaunchApp.driver.findElement(By.xpath(".//*[@name='secretGACode']")).sendKeys(hm[1]);
	    	 }
	    	 catch(Exception e)
	    	 {
	    		 ExceptionHndeler.Log("Group access codes","Assignment Editing", e);
	    		 Loger.LogEvent("Security / Control ","Security / Control  not Saved="+s[1]);
	    		 e.printStackTrace();
	    	 }
        }	
	     Loger.LogEvent("Security / Control ","Security / Control Saved="+s[1]);
	}
	public static void Duration(String [] s)
	{
		String []hm={"2","0"}; hm=s[1].split(";");
		 Loger.LogEvent("Duration","Duration Selecting"); 
	 System.out.println("duration="+s[1]);
	 if(!(hm[1].equals("0")))
	 {
		 try {
	 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblMinutes']")).click();
	 Thread.sleep(100);
    new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='lblMinutes']//*[@name='value']"))).selectByVisibleText(hm[1].trim());
	 Thread.sleep(100);}
	 catch (InterruptedException e) {    ExceptionHndeler.Log("Duration","Assignment Editing", e);  }
	}
   
    try {
		Thread.sleep(200);
		 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblHours']")).click();
		 new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='value']"))).selectByValue("0");
		 System.out.println(" going in while time  setting time ");
		 while((LaunchApp.driver.findElements(By.xpath(".//*[@name='lblHours']"))).size()!=1)
		 {
			 try {
				 System.out.println("time  setting time ");
				 new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='value']"))).selectByValue("0");
		 
			 }
			 catch (Exception e) { System.out.println("time not set");}
		 }
		    Loger.LogEvent("Duration","Duration Selected"+s[1]);
	} catch (InterruptedException e) {
		System.out.println("time not set 1");
		 Loger.LogEvent("Duration","Duration  not Selected"+s[1]);
		 ExceptionHndeler.Log("Duration-hour","Assignment Editing", e);
	}
   
	}
	public static void Association(String [] s)
	{
		String []hm=s[1].split(";");
		 hm[0].trim();
		 Loger.LogEvent("Association","Association Selecting"); 
		 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssociationType']")).click();
		 try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			 ExceptionHndeler.Log("Association","Assignment Editing", e1);
		}
		 
		 try {
			 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssociationType']")).click();
			 new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssociationType']//*[@name='value']"))).selectByVisibleText(hm[0].trim());
			
			 // new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='value']"))).selectByVisibleText(hm[0]);
			} catch (Exception e) {
				System.out.println("in catch"+hm[0]);
				LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssociationType']//*[@name='value']")).sendKeys(hm[0]);
			}
	     if(hm[0].equals("Chapter"))
	     { try
	     {
	    System.out.println("in data"); 
	      LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
	      LaunchApp.driver.findElement(By.xpath(".//*[@name='moduleName']")).click();
	     new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='moduleName'].//*[@name='value']"))).selectByVisibleText(hm[1]);
	    
	     }
	     catch(Exception e)
	     {
	    	 
		     LaunchApp.driver.findElement(By.xpath(".//*[@name='moduleName'].//*[@name='value']")).sendKeys(hm[1]);
		     LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
		     LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
		     ExceptionHndeler.Log("Chapter","Assignment Editing", e);
		    
	     }
	     }
	     if(hm[0].equals("lesson"))
	     {
	    	 LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion']")).click();
	     LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion'].//*[@name='lesson']")).click();
	     new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='lblAssignmentVersion'].//*[@name='value']"))).selectByVisibleText(hm[1]);
	     }
	     Loger.LogEvent("Association","Association Selected"+s[1]); 
	}
	public static void PointsForQuestion(String [] s)
	{
		 Loger.LogEvent("Points for question","Points for question Selecting"); 
		 if(s[1].equals("yes"))
		 {
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='Yes']")).click();
		 }
		 else
		{
		   LaunchApp.driver.findElement(By.xpath(".//*[@id='No']")).click();
		}
		 Loger.LogEvent("Points for question","Points for question Selected"+s[1]); 
	}
	public static void ShowAnswerAfterDue(String [] s)
	{
		 Loger.LogEvent("Show answers after due date","Show answers after due date Selecting"); 	
		 if(s[1].equals("no"))
		 {
	     LaunchApp.driver.findElement(By.xpath(".//*[@id='showAfterDueDateRow']/td[2]/input")).click();
		 }
		 Loger.LogEvent("Show answers after due date","Show answers after due date Selected"+s[1]);
	}
	public static void AdditionalInformation(String [] s)
	{
		String []hm=s[1].split(";");
		Loger.LogEvent("Additional Information","Additional Information Selecting"); 
		if(hm[0].equals("yes"))
		{
        LaunchApp.driver.findElement(By.xpath(".//*[@name='cbxAdditionalInformation']")).click();
        LaunchApp.driver.findElement(By.xpath(".//*[@name='cbxAdditionalInfoType']")).click();
        new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='value']"))).selectByVisibleText(hm[1]);
		}
		 Loger.LogEvent("Additional Information","Additional Information Selected"+s[1]);
	}
	public static void GroupAssignment(String [] s)
	{
		String []hm=s[1].split(";");
		Loger.LogEvent("Group Assignment/project","Group Assignment/project Selecting"); 
		if(hm[0].equals("yes"))
		{
			LaunchApp.driver.findElement(By.xpath(".//*[@name='groupActivity']")).click();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 ExceptionHndeler.Log("GroupAssignment","Assignment Editing", e);
			}	
		
			new Select(LaunchApp.driver.findElement(By.xpath(".//*[@name='cbxPodGroup']"))).selectByVisibleText(hm[1]);
		
		}
		 Loger.LogEvent("Group Assignment/project","Group Assignment/project Selected"+s[1]);
	}
	public static void AcceptLate(String [] s)
	{
		Loger.LogEvent("Accept late","Accept late Selecting"); 
		
		if(s[1].equals("yes"))
		{
			LaunchApp.driver.findElement(By.xpath(".//*[@name='accpetLate']")).click();
		}
		Loger.LogEvent("Accept late","Accept late Selected "+s[1]); 
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
	public static void AddQuestion(String [] s)
	{
		
	}
}

