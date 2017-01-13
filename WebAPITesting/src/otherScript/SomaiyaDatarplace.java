package otherScript;

import Data.Read_Data;

public class SomaiyaDatarplace {
	
	public static void main(String str[])
	{
		Readdata();
	}
	@SuppressWarnings("unused")
	public static void Readdata()
	{
	String[][] COPO_mapping=Read_Data.ReadData("JSPMCO_pomapping.csv");
	String[][] JSPMReplce=Read_Data.ReadData("JSPMReplce.csv");
	
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for(int i=0;i<COPO_mapping.length;i++)
	{
		
		for(int j=0;j<JSPMReplce.length;j++)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(COPO_mapping[i][2].trim().equals((JSPMReplce[j][0]).trim())==true)
			{
				System.out.println(i+"   "+j+"   "+COPO_mapping[i][0]+"  "+COPO_mapping[i][1]+"  "+JSPMReplce[j][1]+" "+COPO_mapping[i][3]+" "+COPO_mapping[i][4]);
				break;
			}
			
		}
	}
	}

}
