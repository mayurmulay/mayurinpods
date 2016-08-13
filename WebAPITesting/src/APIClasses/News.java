
package APIClasses;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.james.mime4j.field.datetime.DateTime;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.GetDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class News
{
	
	public static News object;
	@JsonIgnoreProperties(ignoreUnknown = true)
    public String Ack;
    public String Description;
    public String Importance;
    public String NewsId;
    public String PostedOn;
    public String SectionId;
    public String SectionName;
    public String Sender;
    public String SenderId;
    public String Title;
    public String count;
    public String isRead;
    public PhotoUrl photoUrl;
    
    public static void disp(News n)
    {
    	System.out.println("***********************************************************************************************************************************************************************************");
    	System.out.println(n.Ack);
    	System.out.println(n.Description);
    	System.out.println(n.Importance);
    	System.out.println(n.NewsId);
    	System.out.println(n.PostedOn);
    	System.out.println(n.SectionId);
        System.out.println(n.SectionName);
        System.out.println(n.Sender);
    	System.out.println(n.Sender);
    	System.out.println(n.Title);
    	System.out.println(n.count);
    	System.out.println(n.isRead);
    //	System.out.println(n.photoUrl.i);
    //	System.out.println(n.photoUrl.nil);
    	System.out.println("***********************************************************************************************************************************************************************************");
    	    	
    }
    public static void Compair(Object objNew) 
    {
    	 object= (News) objNew; 
    	System.out.println("Last Object Printing ");
    	disp(object);
    	Date date = new Date();
    	String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
    	try
    	{
        	String Xmlpath="D:\\XmlToRun\\newsValidate.xml";
            TestNG runner=new TestNG();
            List<String> suitefiles=new ArrayList<String>();
            suitefiles.add(Xmlpath);
            runner.setTestSuites(suitefiles);
            runner.run();} catch (Exception e) { System.out.println("Failed to create Event");e.printStackTrace();}	
       }
    @Test
	@Parameters({ "type", "title", "message", "pubDate", "Enddate","Important","Ack","AssertString"})
    public static void TestngCompair(String type,String title,String message,int pubDate,int Enddate,String Important,String Ack,String AssertString) 
    {
    	String Ack1="1";
    	if(!Ack.equalsIgnoreCase("yes"))
    	{Ack1="0";}
    	String imp1="1";
    	if(!Important.equalsIgnoreCase("yes")){imp1="0";}
        String pubdate1=GetDate.GetNextdate(pubDate);
        String Enddate1=GetDate.GetNextdate(Enddate);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String PostedOn=dateFormat.format(date);
        
        System.out.println(object.Ack+"   "+Ack1);
        System.out.println(object.Description+"   "+message);
        System.out.println(object.Importance+"   "+imp1);
        System.out.println(object.PostedOn+"   "+PostedOn);
        System.out.println(object.isRead+"   "+AssertString);
        System.out.println(object.Title+"   "+title);
        
        
        
    	if(object.Ack.equals(Ack1)){  Assert.assertEquals(true, true, "Ack is matched is matching"); System.out.println("Ack  is matching");}
    	            else{Assert.assertEquals(true, true, "Ack is  not matched ");System.out.println("Ack  is not matching");}
    	if(object.Description.equals(message)){  Assert.assertEquals(true, true, "message is matched is matching"); System.out.println("message is matching");}
    	            else{Assert.assertEquals(true, true, "message is  not matched ");System.out.println("message is  not matching");}
    	if(object.Importance.equals(imp1)){  Assert.assertEquals(true, true, "Importance is matched is matching") ;System.out.println("Importance is matching");}
    	            else{Assert.assertEquals(true, true, "Importance is  not matched ");System.out.println("Importance is not matching");}
    	if(object.PostedOn.contains(PostedOn)){  Assert.assertEquals(true, true, "Start date is matched is matching"); System.out.println("Start date is matching");}
    	            else{Assert.assertEquals(true, true, "Start date is not matching ");System.out.println("Start date is not matching");}
    	if(object.isRead.equals("false")){  Assert.assertEquals(true, true, "IsRead is matched is matching"); System.out.println("IsRead is matching");}
    	            else{Assert.assertEquals(true, true, "IsRead is not matching ");System.out.println("IsRead is not matching");}
    	if(object.Title.equals(title)){  Assert.assertEquals(true, true, "Title is matched is matching");System.out.println("Title is  matching");}
    	            else{Assert.assertEquals(true, true, "Importance is  not matched ");System.out.println("Title is not matching");}
    	

    }
    
}
