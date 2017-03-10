
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
 





import org.testng.TestNG;

import otherScript.RestoreDB;
 
public class RunTestNG {
 
public static void main(String[] args) {
 

TestNG runner=new TestNG();

List<String> suitefiles=new ArrayList<String>();

 if(args.length>0)
 {
	 for(int i=0;i<args.length;i++)
	 suitefiles.add(args[i]);
 }
 else{
	 try {
		BufferedReader in = new BufferedReader(new FileReader("D:\\Xml_to_Run\\Testing.xml"));
		String line;
		while((line = in.readLine()) != null)
		{
		    if(!(line.toLowerCase().contains("//")))
		    {
		    	System.out.println(line);
		    	suitefiles.add(line.trim());
		    }
		}
		in.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
     
     }

runner.setTestSuites(suitefiles);

runner.run();
}
 
}