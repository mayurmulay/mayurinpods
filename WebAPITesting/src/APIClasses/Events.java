package APIClasses;

import java.util.Date;

import org.apache.james.mime4j.field.datetime.DateTime;

class PhotoUrl
{
    public String i;
    public String nil;
}

 class Sender
{
    public String i;
    public String nil;
}

public class Events
{
   // @JsonProperty("Description")
    public String description;
   /// @JsonProperty("PhotoUrl")
    public PhotoUrl photoUrl;
   // @JsonProperty("SchoolUserId")
    public String schoolUserId;
  //  @JsonProperty("SectionId")
    public String sectionId;
  //  @JsonProperty("SectionName")
    public String sectionName;
  //  @JsonProperty("Sender")
    public Sender sender;
   // @JsonProperty("SenderId")
    public String senderId;
   // @JsonProperty("Title")
    public String title;
  //  @JsonProperty("Viewed")
    public String viewed;
  //  @JsonProperty("ViewedOn")
   // public Date viewedOn;
    public String count;
   // public Date end;
    public String id;
   // public Date start;
	public static void Disp(Events n) {
		
		System.out.println("***********************************************************************************************************************************************************************************");
    	
    	System.out.println(n.description);
    	System.out.println(n.schoolUserId);
    	System.out.println(n.viewed);
    	System.out.println(n.count);
    	System.out.println(n.sectionId);
        System.out.println(n.sectionName);
        System.out.println(n.sender);
    	System.out.println(n.senderId);
    	System.out.println(n.title);
    	System.out.println(n.count);
    	System.out.println(n.photoUrl.i);
    	System.out.println(n.photoUrl.nil);
    	System.out.println("********************************************************************************************************************************************************************************");
		
	}
}

