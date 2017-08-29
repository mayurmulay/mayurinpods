package NBAReportTesting;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;






import Data.ExceptionHndeler;

 class ReadAPI {

	public static void main(String[] args) {
	
		try
		{
			
			 File directory = new File("D:\\API_Responce");
		        //get all the files from a directory
		        File[] fList = directory.listFiles();
		        for (File file : fList){
		        	String filename=file.getName();
		        	System.out.println("filename="+filename);
		        	if(file.isFile())
		        	{
		        		System.out.println("in if filename="+filename);
		        		 String[] CourseLevelAttainmentReport =ReadData(file.toString());
				   		    Write(CourseLevelAttainmentReport,"D:\\API_Responce\\JSON\\"+file.getName()+".txt");
		        	}
		        
		        }
		        
		 }catch (Exception e){}
	/*try{
		 String[] Semesters =ReadData("D:\\API_Responce\\Semesters");
		 Write(Semesters,"Semesters_JSON");
		}catch (Exception e){}
		try{
		String[] POAttainment =ReadData("D:\\API_Responce\\POAttainment");
	    Write(POAttainment,"POAttainment_JSON");
		}catch (Exception e){}
		try{
		 String[] pgmCOMappings =ReadData("D:\\API_Responce\\pgmCOMappings");
		 Write(pgmCOMappings,"pgmCOMappings_JSON");
		}catch (Exception e){}
		try{
		 String[] CoursePOAffinityMatrix =ReadData("D:\\API_Responce\\CoursePOAffinityMatrix");
		 Write(CoursePOAffinityMatrix,"CoursePOAffinityMatrix_JSON");
		}catch (Exception e){}
		try{
			 String[] COPOSemester =ReadData("D:\\API_Responce\\COPOSemester");
			 Write(COPOSemester,"COPOSemester_JSON");
			}catch (Exception e){}
		try{
			 String[] COPOSemester =ReadData("D:\\API_Responce\\Objective");//Permissions
			 Write(COPOSemester,"Objective_JSON");
			}catch (Exception e){}
		try{
			 String[] COPOSemester =ReadData("D:\\API_Responce\\Permissions");//Permissions
			 Write(COPOSemester,"Permissions_JSON");
			}catch (Exception e){}
		try{
			 String[] COPOSemester =ReadData("D:\\API_Responce\\rubrics");//Permissions
			 Write(COPOSemester,"rubrics_JSON");
			}catch (Exception e){}*/
		
		
	}

	public static String[] ReadData(String file)
	{
		String [] s1=new String[10000];
		
		 Charset charset = Charset.forName("ISO-8859-1");
		 List<String> lines = null ;
		try {
			 lines = Files.readAllLines(Paths.get(file),charset);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	ExceptionHndeler.Log("Read Data","Read Data", e);
		}
		
  String[] Assignments= new String[1000];
  int AssCount=0;
	  for(int i=0;i<lines.size();i++)
	  {
		if(lines.get(i).toString().trim().startsWith("<responseData class="))
			
		  {
			String Str="";
			try
			{
			 Str=lines.get(i).toString().trim();
			// System.out.println("Demo"+Str);
			int start=Str.indexOf('{');
			int end=Str.lastIndexOf('}');     
			Str=Str.substring(start, (end+1));                //Get only JSON Part and Remove other XML 
			Str=Str.replace("&quot;", "\"");
		    Assignments[AssCount]=Str;
		   
		   // System.out.println("\n\n"+Assignments[AssCount]);
		    AssCount++;
			}
		    catch(Exception e )
		    {
		    	System.out.println(e);
		    	System.out.println(Str);
		    }
		  }
	  }
	  int AssCountOld=AssCount;
	  for(int i=AssCountOld;i<AssCount;i++)
	  {
		   // System.out.println("\n\n new assignment = "+Assignments[i]);  
	  }
	     return Assignments;
	}
	public static void Write(String [] observation,String name)
	{
	for(int i = 0; i < observation.length; i++) {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(name, true))) {
	        String s=" ";
	        s = observation[i];
	      //  System.out.print("Demo"+s);
	        bw.write(s+"\n");
	        bw.flush();
	    } catch(IOException ex) {}  
	}
	}
}