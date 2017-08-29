package Common;

public class GetCurrentURL {

	public static void main(String[] args) {
		

	}
	public static String GetcurrentURL()
	{
		String Url=LaunchApp.driver.getCurrentUrl();
		return Url;
		
	}

}
