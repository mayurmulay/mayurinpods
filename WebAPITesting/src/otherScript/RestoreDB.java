package otherScript;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RestoreDB {

	public static void main(String[] args) {
		
		//ConnectDB("SARTierII_ProdDB","E:\\SARTierII_ProdDB.bak");
		//ConnectDB("inpodsdb","E:\\inpodsdb.bak");
		//ConnectDB("aspnetdb","E:\\aspnetdb.bak");
		DropDB("Mayur24012017_TestDB");
	}
  public static void ConnectDB(String DBname,String path)
  {
	 
	  try {
		  String url = "jdbc:sqlserver://USER-PC;databaseName=master";
		  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		  Connection con = DriverManager.getConnection(url, "sa", "p@ssw0rd000");
		  Statement stmt=con.createStatement(); 
		  String sql1="USE master\n"; String sql3="ALTER DATABASE ["+DBname+"] \n";  String sql4="SET offline\n";  String sql5="WITH ROLLBACK IMMEDIATE\n";
		  String str=sql1+sql3+sql4+sql5;
		  stmt.addBatch(str);  stmt.executeBatch();
		  String sql7="\nRESTORE DATABASE ["+DBname+"]\n";  String sql8="FROM DISK = N'"+path+"'\n";   String sql9="WITH FILE = 1,  NOUNLOAD,  REPLACE,  STATS = 10 \n";
		  str=sql7+sql8+sql9;
		  stmt.addBatch(str); stmt.executeBatch();
		  sql3="ALTER DATABASE [SARTierII_ProdDB] \n"; sql4="SET online\n"; sql5="WITH ROLLBACK IMMEDIATE\n";
		  str=sql1+sql3+sql4+sql5;
		  stmt.addBatch(str);
		  System.out.println(str);
		  stmt.executeBatch();  
		  System.out.println(DBname+"Database Restored Successfully...");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  public static void DropDB(String DBname)
  {
	  try
	  {
	  String url = "jdbc:sqlserver://USER-PC;databaseName=master";
	  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	  Connection con = DriverManager.getConnection(url, "sa", "p@ssw0rd000");
	  String sql1="USE master\n"; String sql3="ALTER DATABASE ["+DBname+"] \n";  String sql4="SET SINGLE_USER\n";  String sql5="WITH ROLLBACK IMMEDIATE\n";
	  Statement stmt=con.createStatement(); 
	   stmt.addBatch("DECLARE @kill varchar(8000) = '';");
	  stmt.addBatch("SELECT @kill = @kill + 'kill ' + CONVERT(varchar(5), spid) + ';'");
	  stmt.addBatch(" FROM master..sysprocesses ");
	  stmt.addBatch("WHERE dbid = db_id('"+DBname+"')");
	  stmt.addBatch("EXEC(@kill);");
	  stmt.addBatch("Drop Database "+DBname);
	  stmt.executeBatch();
	  System.out.println(DBname+"Database Dropped Successfully...");
	  } catch (Exception e) {
		e.printStackTrace();
	}
  }
}
