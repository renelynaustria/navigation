package com.ortega.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TipLife_Admission extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_life__admission);

        backbtn = (ImageView) findViewById(R.id.back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Requirements");
        listDataHeader.add("Guidelines");
        listDataHeader.add("Registration");
        listDataHeader.add("Enrollment");

        // Adding child data
        List<String> requirements = new ArrayList<String>();
        requirements.add("ENROLLMENT REQUIREMENTS" + "\n\nAs a general policy, a student applicant who complies with the requirements and passes the " +
                "entrance examination and/or interview may enroll." + "\n\nSENIOR HIGH SCHOOL\n" +
                "       > Original High School Card (Form 138)\n" +
                "       > Four (4) pcs. 2”x2” recent ID picture\n" +
                "       > Two (2) pcs. 1”x1” recent ID picture\n" +
                "       > Original copy of NSO Birth Certificate\n" +
                "       > Certificate of Good Moral Character\n" +
                "       > Grade 10 Certificate of Completion" + "\n\nFRESHMEN\n" +
                "       > Original Form 138 (Report Card)/Alternative Learning System (ALS) Examinee Report Form or " +
                "Certificate of Rating of the Philippine Educational Placement Test (PEPT)\n" +
                "       > Four (4) pcs. of 2”x2” recent picture\n" +
                "       > Two (2) pcs. 1”x1” recent ID picture\n" +
                "       > Original Copy of NSO Birth Certificate\n" +
                "       > Certificate of Good Moral Character" +
                "      \n\nADDITIONAL REQUIREMENTS\n" +
                "         > For Marine Engineering Applicants (CMO No. 13s. 2005)\n" +
                "               > Physical Examination\n" +
                "               > Eyesight Examination (Lantern or any Color Perception Test)\n" +
                "               > Audiometric Test\n" +
                "               > Medical requirements such as Urine, Stool, CBC, X-ray, Psychological Test" +
                "           \n\n> For ALS/PEPT Passers\n" +
                "               > Diploma or Completion Certificate for Secondary Level\n" +
                "               > For Valedictorians, Salutatorians, Third to Tenth Rank\n" +
                "               > Certification of Honors signed by the high school principal stating of the honor received.\n" +
                "\n\n           > For Previous Graduates\n" +
                "               > Certification from the high school principal stating that the Form 137A is still in the school file and has not been forwarded to another tertiary school.\n" +
                "               > For Filipino Applicants From Schools Abroad (Except for Filipino Schools Under the Department of Education)\n" +
                "               > Certificate of Completion of Secondary Education or its equivalent\n" +
                "\n\n           > For Foreign Nationals\n" +
                "               > who graduated from high school in the Philippines\n" +
                "                   > Alien Certificate of Registration or I-Card or Special Study Permit (SSP)\n" +
                "                   > Original and photocopy of passport\n" +
                "               > who graduated from high school abroad\n" +
                "                   > Certificate of Completion of Secondary Education\n" +
                "                   > Alien Certificate of Registration or I-Card or Special Study Permit (SSP)\n" +
                "                   > Original and photocopy of passport" +
                "\n\nTRANSFEREES\n" +
                "       > Original copy of the Transfer Credential\n" +
                "       > Transcript of Records or Certification of grades from the last school attended\n" +
                "       > Four (4) pcs. 2”x2” recent/identical pictures\n" +
                "       > Two (2) pcs. 1”x1” recent ID picture\n" +
                "       > Photocopy of Birth Certificate from the National Statistics Office (NSO)\n" +
                "       > Certificate of Good Moral Character" + "\n\nCROSS-ENROLLEES" +
                "       > Original copy of Cross-Enrolment Permit from the school of origin\n" +
                "       > Four (4) pcs. 2”x2” recent ID picture" + "\n\nGRADUATE STUDENTS\n" +
                "       > Please see the Dean, Graduate Programs or the Registrar’s Office");

        List<String> guidelines = new ArrayList<String>();
        guidelines.add("As a general policy, a student applicant who complies with the requirements " +
                "and passes the entrance examination and/or interview may enroll." + "\n\nFRESHMEN\n" +
        "A student who graduated from the Secondary Level of Education from the Department of Education (DepEd) is eligible for " +
                "admission. However, a student who has not completed his secondary level of education but has passed the " +
                "Philippine Educational Placement Test (PEPT) or the Alternative Learning System Test (ALS) from the Bureau " +
                "of Alternative Learning System (BALS) is also eligible for admission (CHED Manual of Regulations for Private " +
                "Higher Education of 2008).\n" +
                "\n" +
                "High school graduates with General Weighted Average (GWA) of 80% or above are exempted from taking the " +
                "entrance examination. Student must use the name that appears on his admission credentials and birth " +
                "certificate issued by the National Statistics Office (NSO). If there is a discrepancy in the name of the " +
                "student as it appeared in the birth certificate and that which is stated in the admission credentials, the " +
                "student shall be required to apply for correction of name to the Office of the Registrar. In the case of a " +
                "married female student, an authenticated copy of the marriage certificate must be submitted to the " +
                "Registrar’s Office for record purposes." + "\n\nTRANFEREES\n" +
                "       1. An applicant transferee must have obtained in the previous school a Grade Point Average (GPA) of at least 2.75 or its equivalent excluding Physical Education and National Service Training Program (NSTP).\n" +
                "       2. He/she has to comply with the residency requirement in TIP of at least one (1) school year or at least forty two (42) units.\n" +
                "       3. His/her application for admission must first be pre-evaluated and endorsed by the Department Chair or College Dean to the Office of the Registrar. The Office of the Registrar will have to endorse the qualified applicant to the Guidance and Counseling Center then to the Security Office for interview.\n" +
                "       4. He/she should pass the entrance examination and interview administered by the Guidance and Counseling Center and the interview administered by the Security Office. Transferees for Marine Engineering and Marine Transportation should also pass the medical screening to be conducted by the Medical and Dental Services department.\n" +
                "       5. He/she should not have incurred failing grades in the previous school. If the applicant transferee had only one(1) failing grade in the previous school, he/she should undergo a second entrance qualifying exam to be admitted to the Board Program. He/she shall be under academic probation however. He/she can also be encouraged to pursue instead a non-Board Program.\n" +
                "       6. He/she should not have been dropped from the rolls of the previous school or of other previous schools due to disciplinary reasons. Exclusion is the penalty if the applicant transferee misrepresents during the interview, even if this is found out only later while the applicant is already enrolled as a student.\n" +
                "       7. Any admitted transferee shall be placed on academic probation until such time that any of his/her program required course/s* taken from his/her former school are validated in TIP.\n" +
                "       (*) Validation/accreditation shall be recommended by the Department Chair upon student’s request for accreditation of course/s taken from the former school and submission of Official Transcript of Records.\n" +
                "       8. Any or all of the above rules may, for exceptional cases, be set aside upon the recommendation of the College Dean and upon the approval of the Assistant Vice President for Academic Affairs/Vice President for Academic Affairs.\n" +
                "       9. A separate policy for admission of transferees applies to the program BS in Accountancy.\n" +
                "       10. This is for implementation for incoming transferees for the First Semester SY 2008 – 2009.");

        List<String> registration = new ArrayList<String>();
        registration.add("\nFRESHMEN\n" +
                "       1. Present requirements for admission at the Admissions Office/Guidance and Counseling Center.\n" +
                "       2. Fill out the application for Admission and Pre-registration.\n" +
                "           > For qualified students, fill out the scholarship and discount form\n" +
                "           > For students whose general average is below 80%, take an entrance exam at the Guidance Center\n" +
                "           > For Marine Engineering Program, proceed to Medical and Dental Services for physical exam\n" +
                "       3. Proceed to the Admissions and Marketing Office for the Registration of Courses and Assessment of Fees.\n" +
                "       4. Pay the assessed school fees at the Tellering Section.\n" +
                "       5. Apply for a school ID at the Office of Student Affairs and library card at the Library.\n" +
                "       6. Secure schedule for orientation at the Guidance and Counseling Center." + "\n\nOLD STUDENTS\n" +
                "       1. Present latest grade slip and registration card.\n" +
                "       2. Fill out the Pre-registration Form.\n" +
                "           > For returnees, secure a re-admission clearance from the Office of Student Affairs.\n" +
                "           > For under probation, proceed to the Guidance and Counseling Center.\n" +
                "           > For scholars, accomplish a Scholarship Eligibility Form.\n" +
                "           >  For MarE/Athlete, proceed to the Medical and Dental Services.\n" +
                "       3. Get course schedules at the Schedule Board.\n" +
                "       4. Proceed to the Program Window at the Registrar's Office for evaluation and registration of courses, assessment of fees, issuance of registration card and scholarship discounts.\n" +
                "       5. Pay the assessed school fees at the Tellering Section.\n" +
                "       6. Proceed to the Office of Student Affairs for ID validation." + "\n\nTRANSFEREES\n" +
                "       1. Present requirements for admission at the Admissions Office/Guidance and Counseling Center.\n" +
                "       2. Fill out the application for Admission and Pre-registration.\n" +
                "           > For qualified students, fill out the scholarship and discount form\n" +
                "           > For Marine Engineering Program, proceed to Medical and Dental Services for physical exam\n" +
                "       3. Proceed to the Program Chair for Evaluation and Interview\n" +
                "       4. Take the Entrance Exam\n" +
                "       5. Secure Schedule of Courses\n" +
                "       6. Registration adn Assessment of Fees at the Admissions and Marketing Office\n" +
                "       7. Payment of Fees\n" +
                "       8. Apply for school ID, Library Card, medical check up and get schedule for orientation" +
                "\n\nCROSS-ENROLLEES\n" +
                "       1. Present requirements for Admission.\n" +
                "       2. Fill out the Admission and Pre-registration Form at the Registrar's Office.\n" +
                "       3. Get schedule of courses at the Bulletin Boards.\n" +
                "       4. Proceed to the Registrar's Office for registration of courses, assessment of fees and issuance of Registration Card.\n" +
                "       5. Pay the assessed school fees at the Tellering Section.\n" +
                "       6. Apply for a school ID at the Office of Student Affairs and library card at the Library.");

        List<String> enrollment = new ArrayList<String>();
        enrollment.add("A student who has complied with all the admission requirements is qualified to enrol. " +
                "His name must appear the same on birth certificate and admission credentials. In the case of married female " +
                "students, an authenticated copy of the marriage certificate must be submitted to the Registrar's Office." +
                "\n\nOLD STUDENTS\n" +
                "       > Proper sequencing of courses, i.e., taking prerequisite courses before the next higher courses, should be strictly observed; otherwise, the registration maybe invalidated and no credit will be applied despite the grades obtained.\n" +
                "       >  A grade of 5.00 (Failed) in any prerequisite course disqualifies a student from enroling in the next higher course.\n" +
                "       > If the grade obtained is 4.00 (No Credit), a student shall retake the course within the prescribed one-year period or the mark automatically becomes 5.00 (Failed).\n" +
                "       > A student who has not completed the required PE and NSTP units after five (5) semesters will not be allowed to carry an 18-unit load including PE and NSTP, unless justifiable reasons are presented.\n" +
                "       > A student who has been dropped from the roll or expelled due to infractions of school rules and regulations shall not be readmitted.\n" +
                "       > The guidelines on academic probation shall apply to students who fail in fifty percent (50%) or more of the total units enroled in during the semester." +
                "\n\nTRANSFER STUDENT\n" +
                "       > A student who wishes to transfer must submit all requirements to the Registrar's Office.\n" +
                "       > If a student wishes his courses to be credited, he has to write a letter of request to the Dean with an attached course description document from the former school." +
                "\n\nWITHDRAWAL/CANCELLATION OF COURSE/DISCONTINUATATION OF STUDIES\n" +
                "As a general rule, any student who wishes to discontinue his studies during the semester must notify " +
                "in writing the Registrar, through the Head of Students Affairs/Department Chair, and copy furnish the " +
                "Student Accounting Section Head. The parents or guardian of the student must note the letter if the student " +
                "is a minor. Failure to submit the letter is tantamount to forfeiture of the student's rights to any refund " +
                "of fees.\n" +"\nA student who registers his withdrawal within two weeks after the beginning of classes and " +
                "who has fully paid the pertinent fees may apply for a refund. With his application for refund stamped and " +
                "received by the Student Accounting Section within the same week, the student will be charged as follows:\n" +
                "\n     1. Ten percent (10%) of the total amount due for the term if he withdraws within the first week of classes whether he has attended classes or not.\n" +
                "       2. Twenty percent (20%) of the total amount due for the term if he withdraws within the second week of classes whether he has attended classes or not.\n" +
                "       3. The full fee if he withdraws any time after the second week of classes.\n\n" +
                "Because of the heavy workload during the registration period, the Student Accounting Section can accommodate refund claims usually one month after registration.");

        listDataChild.put(listDataHeader.get(0), requirements);
        listDataChild.put(listDataHeader.get(1), guidelines); // Header, Child data
        listDataChild.put(listDataHeader.get(2), registration);
        listDataChild.put(listDataHeader.get(3), enrollment);
    }
}
