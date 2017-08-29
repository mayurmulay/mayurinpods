package otherScript;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 public class GetAnswerforQuestion{
 
	public static void main(String args[]){  
		
	try{  
		
		//Connection con = DriverManager.getConnection("jdbc:sqlserver://INPOD;user=sa;password=p@ssw0rd000;database=aspnetdb");
		System.out.println("test");
		String url = "jdbc:sqlserver://USER-PC;databaseName=SARTierII_ProdDB";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		 String [] Section_name={"AutoTestingMM","IT - Sem V - 2016-17 - Advance Database Management Systems - A3","IT - Sem V - 2016-17 - Advance Database Management Systems - A4","IT - Sem V - 2016-17 - Advance Database Management Systems - B2","IT - Sem V - 2016-17 - Advance Database Management Systems - A2","IT - Sem V - 2016-17 - Advance Database Management Systems - B3","IT - Sem V - 2016-17 - Advance Database Management Systems - B4"};
		for(int i=0;i<Section_name.length;i++)
	   {
		   
			Connection con = DriverManager.getConnection(url, "sa", "p@ssw0rd000");
		   Statement stmt=con.createStatement(); 
	    String str="SELECT dbo.Sections.Name, Activities_1.Title AS Expr1, dbo.SchoolUsers.UserName, dbo.ActivityItems.Question, dbo.ActivityItemSubmissions.StudentResponse FROM  dbo.Activities INNER JOIN dbo.StudentActivities ON dbo.Activities.ActivityId = dbo.StudentActivities.ActivityId INNER JOIN  dbo.ActivityItemSubmissions ON dbo.StudentActivities.StudentActivityId = dbo.ActivityItemSubmissions.StudentActivityId INNER JOIN   dbo.Sections ON dbo.Activities.ActivityScopeId = dbo.Sections.SectionId INNER JOIN  dbo.SchoolUsers ON dbo.StudentActivities.AssociationId = dbo.SchoolUsers.SchoolUserId INNER JOIN dbo.Activities AS Activities_1 ON dbo.Activities.ParentActivityId = Activities_1.ActivityId INNER JOIN dbo.Activity_ActivityItem ON dbo.ActivityItemSubmissions.Activity_ActivityItemId = dbo.Activity_ActivityItem.Activity_ActivityItemId INNER JOIN                      dbo.ActivityItems ON dbo.Activity_ActivityItem.ActivityItemId = dbo.ActivityItems.ActivityItemId WHERE      (dbo.ActivityItemSubmissions.StudentResponse is not null) and (dbo.ActivityItemSubmissions.StudentResponse <> '') GROUP BY dbo.Sections.Name, Activities_1.Title, dbo.SchoolUsers.UserName, dbo.ActivityItems.Question, dbo.ActivityItemSubmissions.StudentResponse HAVING (dbo.Sections.Name = N'"+Section_name[i]+"')";
	    ResultSet rs=stmt.executeQuery(str);  
	    System.out.println("test");
	    while(rs.next())
	    {
	    	//Crete folder of section name
	    	createFolder("D:\\Answer\\"+rs.getString(1).trim().toLowerCase());
	    	//Crete folder of Test name
	    	Thread.sleep(2000);
	    	createFolder("D:\\Answer\\"+rs.getString(1).trim().toLowerCase()+"\\"+rs.getString(2).trim().toLowerCase());
	    	String Fname="D:\\Answer\\"+rs.getString(1).trim().toLowerCase()+"\\"+rs.getString(2).trim().toLowerCase()+"\\"+rs.getString(3).trim().toLowerCase()+".doc";
	    	String answer=rs.getString(5).replaceAll("<p>", " ");
	          answer=answer.replaceAll("</p>", " ");
	          answer=answer.replaceAll("<ul>", " ");
	          answer=answer.replaceAll("</ul>", " ");
	          answer=answer.replaceAll("&nbsp;", " ");
	          answer=answer.replaceAll("<strong>", " ");
	          answer=answer.replaceAll("</strong>", " ");
	          answer=answer.replaceAll("<li>", " ");
	          answer=answer.replaceAll("</li>", " ");
	          answer=answer.replaceAll("-<br />", " ");
	          answer=answer.replaceAll("</strong>", " ");
	          answer=answer.replaceAll("<a", " ");
	          answer=answer.replaceAll("</a>", " ");
	          answer=answer.replaceAll("<br>", " ");
	          answer=answer.replaceAll("&lt;", "[");
	          answer=answer.replaceAll("&gt;", "]");
	          answer=answer.replaceAll("p&gt;", "[");
	          answer=answer.replaceAll("/p&gt;", "]");
	          String Question=rs.getString(4).replaceAll("&nbsp;"," ");
	          Question=Question.replaceAll("<br />"," ");
	          
	    	try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Fname, true)))) {
	    	    out.println("\nQuestion= "+Question+" \nAnswer=\n"+answer);
	    	    out.println("***************************************************************************************************************************************************************************");
	    	    
	    	}catch (IOException e) {
	    	    e.printStackTrace();
	    	}
	    //System.out.println("***************************************************************************************************************************************************************************");
	    System.out.println("Section_name="+rs.getString(1)+" \nTest_name= "+rs.getString(2)+" \n Sttudent_name= "+rs.getString(3)+" \nQuestion= "+Question);  
	   // System.out.println("***************************************************************************************************************************************************************************");
	    }
	    con.close();  
	   }
	}catch(Exception e){ System.out.println(e);}  
	
	}  
	public static void createFolder(String name)
	{
		File directory = new File(String.valueOf(name));
		if(!directory.exists()){

            directory.mkdir();
           
           }
}
	
	

}
