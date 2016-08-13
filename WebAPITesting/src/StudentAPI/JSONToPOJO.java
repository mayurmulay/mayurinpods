package StudentAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;



















import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import APIClasses.Events;
import APIClasses.News;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONToPOJO {
	
	public static Object pojo(JSON objJson)
	{
		JSONObject obj=(JSONObject) objJson;
		System.out.println("\n objJson in in pojo):"+objJson);
		Gson gson = new Gson();
		int start=objJson.toString().indexOf("[");
		if(start==-1)
		{
			start=objJson.toString().indexOf(":{");
			start=start+1;
		}
		int end =objJson.toString().lastIndexOf("}");
		String JSONEString=objJson.toString().substring(start,(end));
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("\n JSONEString= "+JSONEString); 
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//*******************************************************    For Event   *******************************************************************************
		if(APILogIn.APItype.equalsIgnoreCase("Event"))
		{
		    Events[] userList;
			try {
				userList = mapper.readValue(JSONEString,Events[].class);
		        // System.out.println(userList.PostedOn);
				for(int i=0;i<userList.length;i++)
			     {
					Events.Disp(userList[i]);
			     }
				return userList;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	     }
		//*******************************************************    For Event   *******************************************************************************
				if(APILogIn.APItype.equalsIgnoreCase("News"))
				{
					@SuppressWarnings("unchecked")
					News[] userList;
					try {
						userList = mapper.readValue(JSONEString,News[].class);
				        // System.out.println(userList.PostedOn);
						
						return userList;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			     }
				return mapper;
		
		
	
	}
	public static void main(String str[])
	{
		FileInputStream fisTargetFile;
		try {
			fisTargetFile = new FileInputStream(new File("D:\\Soap.txt"));
			String targetFileStr = IOUtils.toString(fisTargetFile, "UTF-8");
			JSOndemo(targetFileStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
		
	//	String km="{"@xmlns":"http://schemas.datacontract.org/2004/07/RedMerc.API","@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","EventsController.EventViewModel":[{"Description":"Homework Assigned Date: 8/7 DueDate: 6/17","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"DemoRubric (Updated)","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"72","start":"2016-06-17T23:59:00"},{"Description":"Homework Assigned Date: 8/7 DueDate: 6/17","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"DemoRubric","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"71","start":"2016-06-17T23:59:00"},{"Description":"Homework Assigned Date: 6/3 DueDate: 6/17","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"70","start":"2016-06-17T23:59:00"},{"Description":"Homework Assigned Date: 6/3 DueDate: 6/17","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"69","start":"2016-06-17T23:59:00"},{"Description":"Homework Assigned Date: 8/6 DueDate: 6/17","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"68","start":"2016-06-17T23:59:00"},{"Description":"Exam Start Date: 8/6 12:58P DueDate: 6/18 12:58P Duration: 0:5 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"VarshDemo-1","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"67","start":"2016-06-18T12:58:47.323"},{"Description":"Homework Assigned Date: 6/3 DueDate: 6/17","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Math Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"66","start":"2016-06-17T23:59:00"},{"Description":"Exam Start Date: 6/3 5:30A DueDate: 6/17 11:59P Duration: 0:5 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"3","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment (Updated)","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"65","start":"2016-06-17T23:59:00"},{"Description":"Exam Start Date: 6/3 5:30A DueDate: 6/17 11:59P Duration: 0:5 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"3","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"64","start":"2016-06-17T23:59:00"},{"Description":"Exam Start Date: 6/2 5:30A DueDate: 6/17 12:00A Duration: 1:0 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Demo","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"63","start":"2016-06-17T00:00:00"},{"Description":"Homework Assigned Date: 6/2 DueDate: 6/17","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"62","start":"2016-06-17T00:00:00"},{"Description":"Homework Assigned Date: 6/2 DueDate: 6/17","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"61","start":"2016-06-17T00:00:00"},{"Description":"Homework Assigned Date: 6/2 DueDate: 6/17","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"60","start":"2016-06-17T00:00:00"},{"Description":"Homework Assigned Date: 6/2 DueDate: 6/16","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"3","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment (Updated)","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"59","start":"2016-06-16T23:59:00"},{"Description":"Homework Assigned Date: 6/2 DueDate: 6/16","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"3","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"58","start":"2016-06-16T23:59:00"},{"Description":"Homework Assigned Date: 6/2 DueDate: 6/16","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"57","start":"2016-06-16T23:59:00"},{"Description":"Exam Start Date: 6/2 5:30A DueDate: 6/16 11:59P Duration: 0:5 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"DemoQWPD","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"56","start":"2016-06-16T23:59:00"},{"Description":"Exam Start Date: 6/1 5:30A DueDate: 6/15 11:59P Duration: 1:0 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"TestForDemo","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"55","start":"2016-06-15T23:59:00"},{"Description":"DemoEvent","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"DemoEvent","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"54","start":"2017-06-01T00:00:00"},{"Description":"Exam Start Date: 4/14 5:30A DueDate: 4/15 11:59P Duration: 0:10 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Exam1 (Updated)","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"53","start":"2016-04-15T23:59:00"},{"Description":"Homework Assigned Date: 4/13 DueDate: 4/27","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"test123","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"52","start":"2016-04-27T23:59:00"},{"Description":"Homework Assigned Date: 4/13 DueDate: 4/28","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment (Updated)","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"51","start":"2016-04-28T00:00:00"},{"Description":"Homework Assigned Date: 4/13 DueDate: 4/28","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment (Updated)","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"50","start":"2016-04-28T00:00:00"},{"Description":"Homework Assigned Date: 4/13 DueDate: 4/28","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment (Updated)","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"49","start":"2016-04-28T00:00:00"},{"Description":"Homework Assigned Date: 4/13 DueDate: 4/28","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"48","start":"2016-04-28T00:00:00"},{"Description":"Exam Start Date: 4/15 5:30A DueDate: 4/15 11:59P Duration: 0:10 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Exam1","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"47","start":"2016-04-15T23:59:00"},{"Description":"Exam Start Date: 4/12 5:30A DueDate: 4/26 11:59P Duration: 1:0 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"DemoQWR","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"46","start":"2016-04-26T23:59:00"},{"Description":"Exam Start Date: 4/12 5:30A DueDate: 4/26 11:59P Duration: 1:0 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Demotest","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"45","start":"2016-04-26T23:59:00"},{"Description":"Exam Start Date: 4/11 5:30A DueDate: 4/25 11:59P Duration: 0:10 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Exam1 (Updated)","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"35","start":"2016-04-25T23:59:00"},{"Description":"Exam Start Date: 4/11 5:30A DueDate: 4/25 11:59P Duration: 0:5 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Test5 (Updated)","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"34","start":"2016-04-25T23:59:00"},{"Description":"Homework Assigned Date: 4/11 DueDate: 4/25","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"2","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"3PolicyTestExternal (Updated)","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"29","start":"2016-04-25T23:59:00"},{"Description":"Homework Assigned Date: 4/11 DueDate: 4/25","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"2","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"3PolicyTestExternal (Updated)","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"28","start":"2016-04-25T23:59:00"},{"Description":"Exam Start Date: 4/11 5:30A DueDate: 4/25 11:59P Duration: 0:5 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"2","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"3PolicyMcq","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"19","start":"2016-04-25T23:59:00"},{"Description":"Exam Start Date: 4/11 5:30A DueDate: 4/25 11:59P Duration: 0:5 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Test5","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"18","start":"2016-04-25T23:59:00"},{"Description":"Homework Assigned Date: 4/11 DueDate: 4/25","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"2","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"3PolicyTestExternal","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"17","start":"2016-04-25T23:59:00"},{"Description":"Homework Assigned Date: 4/11 DueDate: 4/26","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Lab1","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"15","start":"2016-04-26T00:00:00"},{"Description":"Homework Assigned Date: 4/11 DueDate: 4/26","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Lab1","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"14","start":"2016-04-26T00:00:00"},{"Description":"Homework Assigned Date: 4/11 DueDate: 4/26","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Lab","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"13","start":"2016-04-26T00:00:00"},{"Description":"Exam Start Date: 4/11 5:30A DueDate: 4/26 12:00A Duration: 1:0 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"External1","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"12","start":"2016-04-26T00:00:00"},{"Description":"Homework Assigned Date: 4/11 DueDate: 4/26","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"External","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"11","start":"2016-04-26T00:00:00"},{"Description":"Exam Start Date: 4/11 5:30A DueDate: 4/25 11:59P Duration: 0:10 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Exam1","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"10","start":"2016-04-25T23:59:00"},{"Description":"Homework Assigned Date: 4/6 DueDate: 4/8","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Project","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"9","start":"2016-04-08T23:59:00"},{"Description":"Homework Assigned Date: 4/4 DueDate: 4/8","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Lab1","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"8","start":"2016-04-08T23:59:00"},{"Description":"Homework Assigned Date: 4/7 DueDate: 4/8","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Lab","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"7","start":"2016-04-08T23:59:00"},{"Description":"Homework Assigned Date: 4/7 DueDate: 4/8","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"External1","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"6","start":"2016-04-08T23:59:00"},{"Description":"Homework Assigned Date: 4/7 DueDate: 4/10","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"External","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"5","start":"2016-04-10T23:59:00"},{"Description":"Exam Start Date: 4/5 5:30A DueDate: 4/7 11:59P Duration: 0:10 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Exam1","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"4","start":"2016-04-07T23:59:00"},{"Description":"Exam Start Date: 4/7 5:30A DueDate: 4/19 11:59P Duration: 0:10 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Draft Assignment","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"3","start":"2016-04-19T23:59:00"},{"Description":"Homework Assigned Date: 4/5 DueDate: 4/20","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"External","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"2","start":"2016-04-20T00:00:00"},{"Description":"Exam Start Date: 4/7 5:30A DueDate: 4/7 11:59P Duration: 0:10 hrs","PhotoUrl":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SchoolUserId":"3","SectionId":"4","SectionName":"B1","Sender":{"@xmlns:i":"http://www.w3.org/2001/XMLSchema-instance","@i:nil":"true"},"SenderId":"3","Title":"Exam1","Viewed":"false","ViewedOn":"2016-06-03T15:38:45.9383222+00:00","count":"0","end":"0001-01-01T00:00:00","id":"1","start":"2016-04-07T23:59:00"}]}";
	}
	
	public static void JSOndemo(String s)
	{
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		@SuppressWarnings("unchecked")
		News[] userList;
		try {
			userList = mapper.readValue(s,News[].class);
	        // System.out.println(userList.PostedOn);
			for(int i=0;i<userList.length;i++)
		     {
		    	
	         News.disp(userList[i]);
		     }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
