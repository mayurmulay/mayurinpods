package WriteData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Read_Data {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@SuppressWarnings("resource")
	public static String[][] ReadData(String name)
	{
		String [][] s1=new String[10000][100];
		 BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader("D:\\"+name));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		    String input;
		   String splitBy = ",";
	      int count = 0;
	      try {
			while((input = bufferedReader.readLine()) != null)
			    {             
			      String[] b = input.split(splitBy);
			      for(int i=0;i<b.length;i++)
			      {
			         s1[count][i]=b[i];
			      }
			     count++;
			    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
	     return s1;
	}
}
	
	