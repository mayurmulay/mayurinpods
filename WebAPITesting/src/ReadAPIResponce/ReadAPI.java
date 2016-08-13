package ReadAPIResponce;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
		String[] Data=ReadData("D:\\API_Responce\\CO-PO_MappingDataSomaiya");
		
	}

	public static String[] ReadData(String name)
	{
		String [] s1=new String[10000];
		
		 Charset charset = Charset.forName("ISO-8859-1");
		 List<String> lines = null ;
		try {
			 lines = Files.readAllLines(Paths.get(name),charset);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExceptionHndeler.Log("Read Data","Read Data", e);
		}
		
  String[] Assignments= new String[1000];
  int AssCount=-1;
  int flag=0;
	  for(int i=0;i<lines.size();i++)
	  {
		if((lines.get(i).toString().trim().startsWith("<responseData class="))||(lines.get(i).toString().trim().startsWith("<java.net.URL>")))
		  {
			String Str=lines.get(i).toString().trim();
			int start=Str.indexOf('{');
			int end=Str.lastIndexOf('}');   
			int CorseId=Str.indexOf("courseId=");
			int CourseEnd=Str.lastIndexOf('<'); 
			int Original=Str.indexOf("parentObjectiveId");
			if(start>1 && end>1 && Original>1)
			{
				 AssCount++;
			Str=Str.substring(start, (end+1));                //Get only JSON Part and Remove other XML    parentObjectiveId
			Str=Str.replace("&quot;", "\"");
		    Assignments[AssCount]=Str;
		    flag=2;
		   // System.out.println("\n\n"+Assignments[AssCount]);
		   
			}
			if(CorseId>1 && CourseEnd>1 && flag>1 )
			{
				flag=0;
			Str=Str.substring(CorseId, (CourseEnd));                //Get only JSON Part and Remove other XML 
			Str=Str.replace("&quot;", "\"");
			//System.out.println("\n\nFlag set to two"+Assignments[AssCount]);
		    Assignments[AssCount]= Str.concat(Assignments[AssCount]);
		  
		    
			}
		  }
	  }
	  int AssCountOld=AssCount-3;
	  for(int i=0;i<AssCount;i++)
	  {
		   // System.out.println("\n\n new assignment = "+Assignments[i]);  
		    String CourseId=Assignments[i].substring((Assignments[i].indexOf("courseId=")+9),Assignments[i].indexOf(";programId"));
		    String programId=Assignments[i].substring((Assignments[i].indexOf("programId=")+10),Assignments[i].indexOf(";objType"));
		   
		    String COPOmapping=Assignments[i].substring(Assignments[i].indexOf("["), (Assignments[i].indexOf("]"))+1);
		    System.out.println(COPOmapping);
		    String []COPO=COPOmapping.split("parent");
		   for(int j=1;j<COPO.length;j++)
		   {
			   
			   String parentObjectiveId=COPO[j].substring(13,COPO[j].indexOf(",\"objectiveId"));
			   String objectiveId=COPO[j].substring((COPO[j].indexOf("objectiveId\":")+13),COPO[j].indexOf(",\"affinity"));
			   String affinity=COPO[j].substring((COPO[j].indexOf("affinity\":")+10),COPO[j].indexOf("}"));
			   System.out.println(COPO[j]+"   Exteracted Data"+programId+";"+CourseId+";"+parentObjectiveId+";"+objectiveId+";"+affinity);
			   
		   }

		   System.out.println("\n\n******************************************************************************");
		    
	  }
	     return s1;
	}
}