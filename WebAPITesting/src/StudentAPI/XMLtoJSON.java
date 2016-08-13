package StudentAPI;


import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;


public class XMLtoJSON {
  
	    public JSON getXMLfromJson(String xml) {
	    	JSON objJson =null;
	        try{
	             objJson = new XMLSerializer().read(xml);
	            System.out.println("JSON data : " + objJson);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
			return objJson;
	    
	    }
	    public static void main(String[] args) {
	    	JSON objJson= new XMLtoJSON().getXMLfromJson("{&quot;activity&quot;:{&quot;activityId&quot;:102,&quot;activityAssociation&quot;:1,&quot;associationId&quot;:2,&quot;parentActivityId&quot;:null,&quot;partnerActivityId&quot;:null,&quot;state&quot;:4,&quot;version&quot;:1,&quot;creationDate&quot;:&quot;2016-06-01T10:36:45.493&quot;,&quot;modificationDate&quot;:&quot;2016-06-01T10:36:45.493&quot;,&quot;activityCategoryId&quot;:32,&quot;activityType&quot;:2,&quot;activitySubType&quot;:0,&quot;number&quot;:21,&quot;displayNumber&quot;:&quot;1.21&quot;,&quot;title&quot;:&quot;TestForDemo&quot;,&quot;assignedDate&quot;:&quot;2016-06-01T10:37:04.47&quot;,&quot;dueDate&quot;:&quot;2016-06-15T18:29:00&quot;,&quot;totalItems&quot;:11,&quot;maxPoints&quot;:11.0,&quot;weight&quot;:0,&quot;activityGrading&quot;:2,&quot;acceptLate&quot;:false,&quot;latePenalty&quot;:0,&quot;numberOfAttempts&quot;:0,&quot;activityAttemptPolicy&quot;:0,&quot;activitySharingStyle&quot;:0,&quot;activitySharing&quot;:1,&quot;isShared&quot;:false,&quot;secret&quot;:&quot;&quot;,&quot;isTimed&quot;:true,&quot;minutes&quot;:60,&quot;assessmentMode&quot;:0,&quot;isRequired&quot;:false,&quot;shuffle&quot;:false,&quot;showAnswersAfterDueDate&quot;:true,&quot;activityTextType&quot;:0,&quot;stem&quot;:null,&quot;instructions&quot;:null,&quot;helpUrl&quot;:null,&quot;activityCommonData&quot;:null,&quot;ancestorActivityId&quot;:null,&quot;authorName&quot;:&quot;teacher1@auto.com&quot;,&quot;displayMode&quot;:0,&quot;isGroupActivity&quot;:false,&quot;startDate&quot;:&quot;2016-06-01T00:00:00&quot;,&quot;activityScopeId&quot;:4,&quot;activityScopeType&quot;:1,&quot;useForOBE&quot;:null,&quot;bloomsDist&quot;:&quot;7,4,4,8,0,0&quot;,&quot;isOfficeIntegrated&quot;:false,&quot;isUniversity&quot;:false,&quot;subActivityCount&quot;:0,&quot;shuffleOptions&quot;:false},&quot;studentActivity&quot;:{&quot;studentActivityId&quot;:1385,&quot;parentStudentActivityId&quot;:null,&quot;studentActivityAssocType&quot;:1,&quot;associationId&quot;:15,&quot;studentActivityState&quot;:3,&quot;dueDate&quot;:null,&quot;submittedDate&quot;:&quot;2016-06-02T10:37:01.57&quot;,&quot;returnedDate&quot;:null,&quot;startDate&quot;:&quot;2016-06-02T05:06:02.697&quot;,&quot;endDate&quot;:&quot;2016-06-02T06:06:02.697&quot;,&quot;duration&quot;:&quot;01:00:00&quot;,&quot;studentActivityFileName&quot;:null,&quot;studentActivityFileUrl&quot;:null,&quot;studentActivityFileUpdated&quot;:null,&quot;isTeacherReviewed&quot;:false,&quot;attemptNumber&quot;:0,&quot;activityId&quot;:102},&quot;studentActivityEval&quot;:{&quot;activityEvalId&quot;:1396,&quot;studentActivityId&quot;:1385,&quot;studentId&quot;:15,&quot;isCorrect&quot;:null,&quot;points&quot;:0.0,&quot;grade&quot;:null,&quot;feedback&quot;:[],&quot;schoolUserId&quot;:3,&quot;numberOfCorrectAnswers&quot;:null,&quot;numberOfIncorrectAnswers&quot;:null,&quot;numberOfUnattemptedQuestions&quot;:null,&quot;attemptNumber&quot;:0,&quot;ancestorActivityIndex&quot;:0,&quot;ancestorActivityId&quot;:null,&quot;bloomsEvalDist&quot;:null},&quot;studentActivityEvalOverview&quot;:{&quot;extraPoints&quot;:null,&quot;extraPointsRemark&quot;:null},&quot;studentActivityState&quot;:&quot;Submitted&quot;,&quot;isEditable&quot;:true,&quot;isNew&quot;:false,&quot;isEvaluated&quot;:true,&quot;isReady&quot;:true,&quot;isExpired&quot;:false,&quot;subActivityIds&quot;:[{&quot;number&quot;:1,&quot;activityId&quot;:103,&quot;activityTypeId&quot;:7,&quot;activityType&quot;:&quot;MultipleChoice&quot;},{&quot;number&quot;:2,&quot;activityId&quot;:104,&quot;activityTypeId&quot;:10,&quot;activityType&quot;:&quot;DiscussionQuestions&quot;},{&quot;number&quot;:3,&quot;activityId&quot;:105,&quot;activityTypeId&quot;:10,&quot;activityType&quot;:&quot;DiscussionQuestions&quot;},{&quot;number&quot;:4,&quot;activityId&quot;:106,&quot;activityTypeId&quot;:1,&quot;activityType&quot;:&quot;FillInTheBlank&quot;}],&quot;dueDate&quot;:&quot;6/15/2016&quot}");

	    	System.out.println("\n\n\n\n"+objJson);
	    }
}

	

//<!--?xml version="1.0" encoding="UTF-8"?--><catalog> <book id="bk01">    <author>Gambardella, Matthew</author> <title>XML Developer's Guide</title><genre>Computer</genre> <price>44.95</price> <publish_date>2000-10-01</publish_date> <description>An in-depth look at creating applications   with XML.</description> </book> <book id="bk02"><author>Ralls, Kim</author><title>Midnight Rain</title>  <genre>Fantasy</genre><price>5.95</price><publish_date>2000-12-16</publish_date> <description>A former architect battles corporate zombies,an evil sorceress, and her own childhood to become queen of the world.</description>   </book></catalog>");