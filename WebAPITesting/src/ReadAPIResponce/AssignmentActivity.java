package ReadAPIResponce;
import ReadAPIResponce.*;

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





import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import APIClasses.AssignmentClass;
import APIClasses.Events;
import Data.ExceptionHndeler;

 class AssignmentActivity {

	public static void main(String[] args) {
	
		String AssignmentDta[]=ReadAPI.ReadData("D:\\API_Responce\\name");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		AssignmentClass[] userList;
		int j=0;
		for(int i=0;i<AssignmentDta.length;i++)
			try {
				userList = mapper.readValue(AssignmentDta[i].toString(),AssignmentClass[].class);
		        // System.out.println(userList.PostedOn);
				for(;j<userList.length;j++)
			     {
					
			     }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
}