package Common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Data.ExceptionHndeler;

import com.steadystate.css.parser.ParseException;

public class GetDate {
	public static void main(String []str)
	{
		System.out.println(GetNextdate(2));
		System.out.println(GetNextdate(0));
		System.out.println(GetNextdate(10));
		System.out.println(GetNextdate(-2));
		System.out.println(GetNextdate(-50));
	}
	public static String GetNextdate(int days)
	{
		GregorianCalendar date = new GregorianCalendar();
	     int  month = date.get(Calendar.MONTH);
	     int  year = date.get(Calendar.YEAR);
	     int day1= date.get(Calendar.DAY_OF_MONTH);
	     String dt=(month+1)+"/"+day1+"/"+year;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println("next date to before set ="+dt);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dt));
			c.add(Calendar.DATE, days);  // number of days to add
			dt = sdf.format(c.getTime());  // dt is now the new date
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExceptionHndeler.Log("GroupAssignment","Assignment Editing", e);
		}
		
		System.out.println("next date to set ="+dt);
		return dt;
		
	}

}
