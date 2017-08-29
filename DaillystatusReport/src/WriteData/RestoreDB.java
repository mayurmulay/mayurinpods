package WriteData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class RestoreDB {
	
	static int FirstScriptData_row=0;int FirstScriptData_col=0;
	static int TotalCount1=0;
	public static boolean flag=true;
	public static String[][] FirstScriptData=new String[1000][10];
	 public static void ConnectDB(String DBname,String path)
	  {
		 
		  try {
			 // String url = "jdbc:sqlserver://localhost;databaseName=master";   //Mayur
			  String url = "jdbc:sqlserver://USER_PC\\SQLExpress;databaseName=master";     //Chaitanys System
			  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			  Connection con = DriverManager.getConnection(url, "sa", "p@ssw0rd000");
			  Statement stmt=con.createStatement(); 
			  String sql1="USE master\n"; String sql3="ALTER DATABASE ["+DBname+"] \n";  String sql4="SET offline\n";  String sql5="WITH ROLLBACK IMMEDIATE\n";
			  String str=sql1+sql3+sql4+sql5;
			  stmt.addBatch(str);  stmt.executeBatch();
			  String sql7="\nRESTORE DATABASE ["+DBname+"]\n";  String sql8="FROM DISK = N'"+path+"'\n";   String sql9="WITH FILE = 1,  NOUNLOAD,  REPLACE,  STATS = 10 \n";
			  str=sql7+sql8+sql9;
			  stmt.addBatch(str); stmt.executeBatch();
			  sql3="ALTER DATABASE ["+DBname+"] \n"; sql4="SET online\n"; sql5="WITH ROLLBACK IMMEDIATE\n";
			  str=sql1+sql3+sql4+sql5;
			  stmt.addBatch(str);
			  System.out.println(str);
			  stmt.executeBatch();  
			  System.out.println("Database:"+DBname+"  Restored Successfully...");
			  
			  Runtime.getRuntime().exec("runas /profile /user:Administrator \"cmd.exe net stop mssqlserver\"");
			 // Process p = Runtime.getRuntime().exec("net stop mssqlserver");
			 //  p.wait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	 
	 public static void ExicuteScript1(String DBname,String Semisters,String Date)
	 {
		 try {
			  
			 System.out.println("DBname"+DBname);
			 //String url = "jdbc:sqlserver://localhost;databaseName="+DBname;  //mayur
			 String url = "jdbc:sqlserver://USER_PC\\SQLExpress;databaseName="+DBname;  //chaitanya
			  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			  Connection con = DriverManager.getConnection(url, "sa", "p@ssw0rd000");
			  Statement stmt=con.createStatement(); 
			  String str="SELECT  dbo.Sections.Name AS Section, dbo.Activities.Title AS Assignment, dbo.Activities.StartDate, dbo.Activities.DueDate, COUNT(dbo.ActivityEvalOverviews.ActivityId)  AS TotalStudents FROM dbo.Activities INNER JOIN dbo.ActivityCategories ON dbo.Activities.ActivityCategoryId = dbo.ActivityCategories.ActivityCategoryId INNER JOIN dbo.Sections ON dbo.Activities.ActivityScopeId = dbo.Sections.SectionId INNER JOIN dbo.ActivityEvalOverviews ON dbo.Activities.ActivityId = dbo.ActivityEvalOverviews.ActivityId WHERE     (dbo.Activities.ParentActivityId IS NULL) AND (dbo.ActivityCategories.ActivityCategoryType IN (1, 3)) AND (dbo.Activities.ParentActivityId IS NULL) AND (dbo.Activities.State = 4) GROUP BY dbo.Sections.Name, dbo.Activities.Title, dbo.Activities.StartDate, dbo.Activities.DueDate HAVING      ((dbo.Activities.StartDate BETWEEN '2017-01-01 00:00' AND '"+Date+" 23:59') AND (dbo.Activities.DueDate >= '"+Date+" 00:00'))";
		      ResultSet rs=stmt.executeQuery(str);
		      flag=false;
		      String[][] Data=ReturnArray(rs);
		      
		      Date d=new Date();
	    	    @SuppressWarnings("deprecation")
				String fileName="User Estimation Report_"+d.getDate()+"_"+(d.getMonth()+1)+".xlsx";
	    	    
	    	    WriteData.AddSheetToxlsx(fileName,DBname+"Query1",Data);
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 public static void ExicuteScript2(String DBname,String Semisters,String Date)
	 {
		 try {
			 Semisters=Semisters.replace(";", ",");
			// String url = "jdbc:sqlserver://localhost;databaseName="+DBname;  //mayur
			 String url = "jdbc:sqlserver://USER_PC\\SQLExpress;databaseName="+DBname;  //chaitanya
			  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			  Connection con = DriverManager.getConnection(url, "sa", "p@ssw0rd000");
			  Statement stmt=con.createStatement(); 
			  String str="SELECT    dbo.Sections.Name AS Section_Name, dbo.Activities.Title AS Assignment_Name, dbo.Activities.StartDate, dbo.Activities.DueDate AS EndDate, COUNT(dbo.ActivityEvalOverviews.ActivityId) AS Count FROM         dbo.Activities INNER JOIN dbo.ActivityEvalOverviews ON dbo.Activities.ActivityId = dbo.ActivityEvalOverviews.ActivityId INNER JOIN  dbo.Sections ON dbo.ActivityEvalOverviews.SectionId = dbo.Sections.SectionId INNER JOIN  dbo.Courses ON dbo.Sections.CourseId = dbo.Courses.CourseId INNER JOIN dbo.Semesters ON dbo.Courses.SemesterId = dbo.Semesters.SemesterId WHERE     (dbo.ActivityEvalOverviews.SubmittedDate IS NOT NULL) GROUP BY dbo.Sections.Name, dbo.Activities.Title, dbo.Activities.DueDate, dbo.Activities.StartDate, dbo.Semesters.SemesterId HAVING      (dbo.Activities.StartDate BETWEEN CONVERT(DATETIME, '2017-01-01 00:00:00', 102) AND CONVERT(DATETIME, '"+Date.trim()+"', 102)) AND   (dbo.Activities.DueDate >= CONVERT(DATETIME, '"+Date.trim()+"', 102)) AND (dbo.Semesters.SemesterId IN ("+Semisters.trim()+"))";
		  System.out.println("Query="+str);
			  ResultSet rs=stmt.executeQuery(str);
			  flag=true;
		      String[][] Data=ReturnArray(rs);
		      
		      Date d=new Date();
	    	    @SuppressWarnings("deprecation")
				String fileName="User Estimation Report_"+d.getDate()+"_"+(d.getMonth()+1)+".xlsx";
	    	   
	    	    WriteData.AddSheetToxlsx(fileName,DBname+"Query2",Data);
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }

	public static String[][] ReturnArray(ResultSet rs)
	 {
		 String [][]Data=new String [100][100];
		 int i=0;
		 if(flag)
		 {
			 FirstScriptData_row=0;    //Reset to the balnk
		 try {
			
			while(rs.next())
			 {
				 Data[i][1]=rs.getString(1);
				 Data[i][2]=rs.getString(2);
				 Data[i][3]=rs.getString(3);
				 Data[i][4]=rs.getString(4);
				 Data[i][5]=rs.getString(5);
				 Data[i][6]="";
				 FirstScriptData[FirstScriptData_row][1]=rs.getString(1);
				 FirstScriptData[FirstScriptData_row][2]=rs.getString(2);
				 FirstScriptData[FirstScriptData_row][3]=rs.getString(3);
				 FirstScriptData[FirstScriptData_row][4]=rs.getString(4);
				 FirstScriptData[FirstScriptData_row][5]=rs.getString(5);
				 
				 i++;FirstScriptData_row++;
			 }
			
			return Data;
		    } catch (SQLException e) { e.printStackTrace();
		    return Data;
		    }
		 }
		 else
		 {
			 try {
				while(rs.next())
						 {
							
							 try {
				             Data[i][1]=rs.getString(1);
							 Data[i][2]=rs.getString(2);
							 Data[i][3]=rs.getString(3);
							 Data[i][4]=rs.getString(4);
							 Data[i][5]=rs.getString(5);
							 Data[i][6]=TotalCount(Data[i][1],Data[i][2]);
							 Data[i][7]=Get_Count(Data[i][1],Data[i][2],rs.getString(5));
							 i++;
							   } catch (SQLException e) { e.printStackTrace(); }
						
						}
				return Data;
				} catch (Exception e) {e.printStackTrace();}
		 }
		return Data;
	 }
	 
	 public static String Get_Count(String section_name,String assignment_name,String count)
	 {
		 String data = count;
		 
		 
		for(int i=0;i<FirstScriptData_row;i++)
		{
			try{
			if( FirstScriptData[i][1].trim().equals(section_name.trim()) && FirstScriptData[i][2].trim().equals(assignment_name.trim()))
			{
				int j=Integer.parseInt(count.trim());
				int k=Integer.parseInt(FirstScriptData[i][5]);
				//TotalCount1=TotalCount1+(j-k);
				data=(j-k)+" ";
				break;
				
			}}catch(Exception e){e.printStackTrace();}
			
		}
		TotalCount1=TotalCount1+Integer.parseInt(data.trim());
		return data.trim();
	 }
	 
	 public static String TotalCount(String section_name,String assignment_name)
	 {
		 String data = "0";
		 
		for(int i=0;i<FirstScriptData_row;i++)
		{
			try{
			if( FirstScriptData[i][1].trim().equals(section_name.trim()) && FirstScriptData[i][2].trim().equals(assignment_name.trim()))
			{
				
				data=FirstScriptData[i][5];
				break;
			}}catch(Exception e){e.printStackTrace();}
			
		}
		 
		return data;
	 }


}
