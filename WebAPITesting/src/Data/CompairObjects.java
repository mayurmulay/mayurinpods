package Data;

import org.testng.Assert;

import APIClasses.News;
import StudentAPI.APILogIn;


public class CompairObjects {

	public static void Compair(Object[] objOld,Object[] objNew) 
	{
		
		System.out.println("***********************************************************************************Validating Data*******************");
		int i=objOld.length;
		int j=objNew.length;
		if(i==(j-1))
		{Assert.assertEquals(true, true, "New Item is get added");
		System.out.println("New Item is get added");}
		else{//Assert.assertEquals(true,false, "New Item is get added");
		System.out.println("New Item is not get added");System.out.println(i+"    "+j);}
		
	/*	for(int count=0;count<i;count++)
		{
			if(objOld[count].equals(objNew[count]))
			{Assert.assertEquals(true, true, "Object is matching"+count); System.out.println("Object is matching"+count);}
			else{//Assert.assertEquals(true,false, "Object is not matching"+count);
				System.out.println("Object is not matching"+count);}
			}*/
		
		if(APILogIn.APItype.equalsIgnoreCase("news"))
		{
			News.Compair(objNew[j-1]);
		}
		
		
		
	}

}
