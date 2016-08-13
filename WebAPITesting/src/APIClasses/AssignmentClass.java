package APIClasses;

import java.util.Date;
import java.util.List;


class Acivity
{
    public int activityId;
    public int activityAssociation;
    public int associationId;
    public String parentActivityId;
    public String partnerActivityId;
    public int state;
    public int version;
    public Date creationDate;
    public Date modificationDate;
    public int activityCategoryId;
    public int activityType;
    public int activitySubType;
    public int number;
    public String displayNumber;
    public String title;
    public Date assignedDate;
    public Date dueDate;
    public int totalItems;
    public double maxPoints;
    public int weight;
    public int activityGrading;
    public boolean acceptLate;
    public int latePenalty;
    public int numberOfAttempts;
    public int activityAttemptPolicy;
    public int activitySharingStyle;
    public int activitySharing;
    public boolean isShared;
    public String secret;
    public boolean isTimed;
    public int minutes;
    public int assessmentMode;
    public boolean isRequired;
    public boolean shuffle;
    public boolean showAnswersAfterDueDate;
    public int activityTextType;
    public String stem;
    public String instructions;
    public String helpUrl;
    public String activityCommonData;
    public String ancestorActivityId;
    public String authorName;
    public int displayMode;
    public boolean isGroupActivity;
    public Date startDate;
    public int activityScopeId;
    public int activityScopeType;
    public String useForOBE;
    public String bloomsDist;
    public boolean isOfficeIntegrated;
    public boolean isUniversity;
    public int subActivityCount;
    public boolean shuffleOptions;
    public int activityControlType;
}

 class StudentActivity
{
    public int studentActivityId;
    public String parentStudentActivityId;
    public int studentActivityAssocType;
    public int associationId;
    public int studentActivityState;
    public String dueDate;
    public String submittedDate;
    public String returnedDate;
    public String startDate;
    public String endDate;
    public String duration;
    public String studentActivityFileName;
    public String studentActivityFileUrl;
    public String studentActivityFileUpdated;
    public boolean isTeacherReviewed;
    public int attemptNumber;
    public int activityId;
    public String code;
}
 class StudentActivityEval
{
    public int activityEvalId;
    public int studentActivityId;
    public int studentId;
    public String isCorrect;
    public double points;
    public String grade;
    public List<String> feedback;
    public int schoolUserId;
    public String numberOfCorrectAnswers;
    public String numberOfIncorrectAnswers;
    public String numberOfUnattemptedQuestions;
    public int attemptNumber;
    public int ancestorActivityIndex;
    public String ancestorActivityId;
    public String bloomsEvalDist;
}

 class StudentActivityEvalOverview
{
    public String extraPoints;
    public String extraPointsRemark;
}

class SubActivityId
{
    public int number;
    public int activityId;
    public int activityTypeId;
    public String activityType;
}
public class AssignmentClass
{
    public Acivity activity;
    public StudentActivity studentActivity;
    public StudentActivityEval studentActivityEval;
    public StudentActivityEvalOverview studentActivityEvalOverview;
    public String studentActivityState;
    public boolean isEditable;
    public boolean isNew;
    public boolean isEvaluated;
    public boolean isReady;
    public boolean isExpired;
    public String dueDate;
    public List<SubActivityId> subActivityIds;
}
