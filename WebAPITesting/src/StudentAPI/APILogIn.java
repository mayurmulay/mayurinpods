package StudentAPI;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Parameters;

import APIClasses.News;
import Common.LaunchApp;
import Common.Login;
import Data.CompairObjects;
import Data.Read_Data;
public class APILogIn {
	public static Object[] objOld;
	public static Object[] objNew;
	public static String APItype;
	public static void main(String[] str)
	{
		CallLogIn();
	}
	//@Test
	//@Parameters({"Url","name","password"})
	public static void logIn(String Url,String name,String password,String state)
	{
		LaunchApp.Execute(Url);
		Login.LoginD(name,password);
		GetAPI(name,state);
		
	}
	
	private static void GetAPI(String name,String state) {
		
		String[][] UserPass=Read_Data.ReadData("APIList.csv");
		int i=0;
		try
		{
		while(!(UserPass[i][0].isEmpty()))
		{
			if(UserPass[i][0].trim().equalsIgnoreCase(name.trim()))
			{
            
				try{ APItype=UserPass[i][2].trim();Thread.sleep(5000);} catch (Exception e) {e.printStackTrace();}
				String pageSource="";
				JSON objJson;
				try  {
				LaunchApp.driver.get(UserPass[i][1].trim());
			   pageSource = LaunchApp.driver.getPageSource();
				System.out.println("\n"+pageSource);
				System.out.println("\n pageSource2="+pageSource);
				}catch (Exception e) { System.out.println("Failed to create Event"); }
				if(state.equalsIgnoreCase("Before"))
				  {
				     objJson= new XMLtoJSON().getXMLfromJson(pageSource);
				   objOld=  (Object[]) JSONToPOJO.pojo(objJson);
				   News [] Demo=(News[]) objOld;
				   for(int j=0;j<Demo.length;j++)
				     {
			         News.disp(Demo[j]);
				     }
				     LaunchApp.driver.quit();
				     
				//Code to call Event 
				    try
			         	{
			            	String Xmlpath="D:\\XmlToRun\\"+APItype+".xml";
				            TestNG runner=new TestNG();
				            List<String> suitefiles=new ArrayList<String>();
				            suitefiles.add(Xmlpath);
				            runner.setTestSuites(suitefiles);
				            runner.run();} catch (Exception e) { System.out.println("Failed to create Event");e.printStackTrace();}
				            LaunchApp.driver.quit();
				               //  End of the Code For Createing Event by teacher 
				    }
				
				else
				{
				try  {
				
				objJson= new XMLtoJSON().getXMLfromJson(pageSource);
				 objNew=(Object[]) JSONToPOJO.pojo(objJson);
				
				 News [] Demo=(News[]) objNew;
				   for(int j=0;j<Demo.length;j++)
				     {
			         News.disp(Demo[j]);
				     }
				LaunchApp.driver.quit();
				CompairObjects.Compair(objOld, objNew);
				} catch (Exception e) { System.out.println("Failed to call API");e.printStackTrace(); }
				}
			}
			i++;
		}}catch (Exception e) { System.out.println("All API Are callede");}
		
	}
	@Test
	public static void CallLogIn()
	{
		int i=0;
		String[][] UserPass=Read_Data.ReadData("APIUser.csv");
		
		while(!UserPass[i][0].equals("Null"))
		{
	    logIn(UserPass[i][0].trim(),UserPass[i][1].trim(),UserPass[i][2].trim(),"Before");  //before event 
	   
		logIn(UserPass[i][0].trim(),UserPass[i][1].trim(),UserPass[i][2].trim(),"After");  //After event
		i++;
		}
		 
		
	}


}
