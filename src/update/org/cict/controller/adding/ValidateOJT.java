/**
 * CAPSTONE PROJECT.
 * BSIT 4A-G1.
 * MONOSYNC TECHNOLOGIES.
 * MONOSYNC FRAMEWORK VERSION 1.0.0 TEACUP RICE ROLL.
 * THIS PROJECT IS PROPRIETARY AND CONFIDENTIAL ANY PART THEREOF.
 * COPYING AND DISTRIBUTION WITHOUT PERMISSION ARE NOT ALLOWED.
 *
 * COLLEGE OF INFORMATION AND COMMUNICATIONS TECHNOLOGY.
 * LINKED SYSTEM.
 *
 * PROJECT MANAGER: JHON MELVIN N. PERELLO
 * DEVELOPERS:
 * JOEMAR N. DELA CRUZ
 * GRETHEL EINSTEIN BERNARDINO
 *
 * OTHER LIBRARIES THAT ARE USED BELONGS TO THEIR RESPECTFUL OWNERS AND AUTHORS.
 * NO COPYRIGHT ARE INTENTIONAL OR INTENDED.
 * THIS PROJECT IS NOT PROFITABLE HENCE FOR EDUCATIONAL PURPOSES ONLY.
 * THIS PROJECT IS ONLY FOR COMPLIANCE TO OUR REQUIREMENTS.
 * THIS PROJECT DOES NOT INCLUDE DISTRIBUTION FOR OTHER PURPOSES.
 *
 */
package update.org.cict.controller.adding;

import app.lazy.models.StudentMapping;
import app.lazy.models.SubjectMapping;
import java.util.ArrayList;
import org.cict.SubjectClassification;
import org.cict.evaluation.assessment.AssessmentResults;
import org.cict.evaluation.assessment.CurricularLevelAssesor;
import org.cict.evaluation.assessment.SubjectAssessmentDetials;

/**
 *
 * @author Joemar
 */
public class ValidateOJT {

    /**
     * static variables are stateless they do not need to be instantiated its
     * value will remain on runtime. values will be release until restart of
     * program. can be used as A Global Variable if set to public. Class
     * Variables on the other hand are variables bound by the instance of the
     * class. if Object A has a variable aVariable the variable will be release
     * until new A() was called.
     */
    private static StudentMapping currentStudent;

    /**
     * Static classes do not need constructors they are not constructed anyway.
     * they have no instance. we can refer to instance as the soul and class is
     * the body. A body can exist without a soul but a soul can not exist if not
     * born with a body. instantiation is like birth you give the body as the
     * class and its instance using the 'new' keyword as the soul.
     */
    public ValidateOJT() {

    }

    public static boolean isValidForOJT(StudentMapping currentStudent) {
        /**
         * You cannot instantiate an inner-class without the instance of the
         * parent class.
         */
        InternshipValidation iv = new ValidateOJT().new InternshipValidation();
        iv.currentStudent = currentStudent;
        return iv.check();
    }

    /**
     * This is an inner-class the body can have living organisms in it like
     * bacteria or tape worms or anything else. you can think inner-classes in
     * that way a Class is the human being and it's inner-classes are living
     * organisms inside it. using static methods of classes and inner classes
     * are great combination for programming it will be a combination of
     * state-full (with instance) and state-less (without instance), this will
     * increase flexibility of code usages. just imagine having a function
     * inside a function inside a function etc.
     */
    private class InternshipValidation {

        public StudentMapping currentStudent;

        /**
         * This method can be dismantle to different pieces on future updates.
         *
         * @return
         */
        public boolean check() {
            // check if null
            if (currentStudent == null) {
                System.out.println("STUDENT IS NULL");
                return false;
            }
            // run assessor.
            CurricularLevelAssesor assessor = new CurricularLevelAssesor(currentStudent);
            assessor.assess();

            for (int yrCtr = 1; yrCtr <= currentStudent.getYear_level(); yrCtr++) {
                AssessmentResults annualAsses = assessor.getAnnualAssessment(yrCtr);
                ArrayList<SubjectAssessmentDetials> temp_array = annualAsses.getUnacquiredSubjects();
                if (temp_array == null) {
                    continue;
                }

                int count = 0;
                for (SubjectAssessmentDetials temp_value : temp_array) {
                    SubjectMapping subject = temp_value.getSubjectDetails();
                    if (SubjectClassification.isMajor(subject.getType())) {
                        if (!subject.getType().equalsIgnoreCase(SubjectClassification.TYPE_INTERNSHIP)) {
                            System.out.println("A MAJOR SUBJECT HAS NO GRADE");
                            return false;
                        }
                    } else {
                        if (count > 1) {
                            System.out.println("MISSING GRADE EXCEED TO 1");
                            return false;
                        } else {
                            if (subject.getType().equalsIgnoreCase(SubjectClassification.TYPE_ELECTIVE)
                                    || subject.getType().equalsIgnoreCase(SubjectClassification.TYPE_MINOR)) {
                                count++;
                            } else {
                                System.out.println("MISSING GRADE OF A NONE ELECTIVE OR MINOR TYPE");
                                return false;
                            }
                        }
                    }
                }

            }

            return true; // default return.
        }
    }

}