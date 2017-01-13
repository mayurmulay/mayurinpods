
import java.util.ArrayList;
import java.util.List;
 
import org.testng.TestNG;
 
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
	 
	// suitefiles.add("D:\\Xml_to_Run\\OBEsetting.xml");
	 suitefiles.add("D:\\Xml_to_Run\\UploadMarks.xml");

	/*suitefiles.add("D:\\Xml_to_Run\\Disscussion.xml");
    suitefiles.add("D:\\Xml_to_Run\\Announcement_Survey.xml");
    suitefiles.add("D:\\Xml_to_Run\\Create_QB_ProdGroop_Rubric.xml");
    suitefiles.add("D:\\Xml_to_Run\\LessonCreation.xml");
    suitefiles.add("D:\\Xml_to_Run\\Share_Assignment_Lesson.xml");
    suitefiles.add("D:\\Xml_to_Run\\Assignment.xml");
    suitefiles.add("D:\\Xml_to_Run\\AssignmentValidation.xml");
    suitefiles.add("D:\\Xml_to_Run\\UploadMarks.xml");
	suitefiles.add("D:\\Xml_to_Run\\AttemptSurvey.xml");
	suitefiles.add("D:\\Xml_to_Run\\Gradebook_Cat.xml");  */
   // suitefiles.add("D:\\Xml_to_Run\\Student.xml");  
   // suitefiles.add("D:\\Xml_to_Run\\GradeStudent.xml");
   // suitefiles.add("D:\\Xml_to_Run\\Report.xml");
   // suitefiles.add("D:\\Xml_to_Run\\Gradebook_Cat.xml");  
   // suitefiles.add("D:\\Xml_to_Run\\AttemptMCQ100.xml");  
     
     }

runner.setTestSuites(suitefiles);

runner.run();
}
 
}