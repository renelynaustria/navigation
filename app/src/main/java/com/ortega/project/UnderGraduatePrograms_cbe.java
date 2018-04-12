package com.ortega.project;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnderGraduatePrograms_cbe extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_graduate_programs_cbe);

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
        listDataHeader.add("Program Certification");
        listDataHeader.add("Accountancy");
        listDataHeader.add("Entrepreneurship");
        listDataHeader.add("Financial Management Accounting");
        listDataHeader.add("Human Resources Development Management");
        listDataHeader.add("Logistic and Supply Chain Management");
        listDataHeader.add("Marketing Management");

        // Adding child data
        List<String> progcert = new ArrayList<String>();
        progcert.add("NATIONAL AND INTERNATIONAL RECOGNITION\n" +
                "\n" +
                "International Certification\n" +
                "\n" +
                "Quality Management System certified compliant to the international standard ISO 9001:2008 by Det " +
                "Norske Veritas\n" +
                "\n" +
                "PACUCOA Accreditation Levels\n" +
                "\n" +
                "Level IV Accredited Status in Business Administration\n" +
                "\n" +
                "In the recent years, TIP had the privilege to receive recognition for exemplary performance in the " +
                "provision of quality education such as:\n" +
                "\n" +
                "ISO Certification. TIP is one among a select number of schools in the Philippines with a Quality Management " +
                "System (QMS) certified compliant to the international standard ISO 9001:2008.\n" +
                "\n" +
                "ISO 9001:2008 specifies requirements for a quality management system where an organization 1) needs to " +
                "demonstrate its ability to consistently provide product or service that meets customer and applicable " +
                "regulatory requirements, and 2) aims to enhance customer satisfaction through the effective application of " +
                "the system, including processes for continual improvement of the system and the assurance of conformity to " +
                "customer and applicable regulatory requirements.\n" +
                "\n" +
                "The certification covers the entire operation and services of the institution’s provision of tertiary " +
                "education in engineering, architecture, business, information and communications technology, and maritime " +
                "education including training for the national and international industries. TIP’s ISO 9001:2008 Certificate " +
                "of Conformity was issued by Det Norske Veritas (DNV) Certification Ltd., a certifying body accredited by the " +
                "United Kingdom Accreditation Service (UKAS).\n" +
                "\n" +
                "Autonomous Status. In November 2009, the Commission on Higher Education (CHED) awarded TIP Quezon City " +
                "with Autonomous Status. Autonomous status is the highest possible award given by CHED to a higher education " +
                "" +
                "institution in recognition of its exemplary performance shown in the provision of quality higher education, " +
                "research and extension work. With TIP Quezon City's autonomous status, it joined the exclusive league of 42 " +
                "autonomous schools from 1,741 higher education institutions nationwide.\n" +
                "\n" +
                "Levels of Accreditation. TIP has various accreditation levels for its different program offerings " +
                "under the Federation of Accrediting Agencies of the Philippines-Philippine Association of Colleges and " +
                "Universities Commission on Accreditation (FAAP-PACUCOA). TIP Quezon City was granted Level III Reaccredited " +
                "Status in Business Administration.");

        List<String> acctncy = new ArrayList<String>();
        acctncy.add("The BSA program is a five-year accounting education program that deals with the study of financial, cost and management accounting, and auditing integrated with the study of the liberal arts and general business curriculum degree program. The program is designed towards preparing the students to be adept with international accounting and auditing standards to make them globally competitive thereby transforming them into graduates with full competence and at the same time equipping them with Filipino values, industry-desired values and global citizen values.\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "Three to five years after graduation, the Accountancy alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Accountancy and/or other endeavors or advocacies supported by their acquired business education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the T.I.P. mission values, pursuing continuing education, and practicing continuous quality improvement (CQI) in their personal lives;\n" +
                "- continuously scanning, adopting and building on the best practices in their field.");

        List<String> entrep = new ArrayList<String>();
        entrep.add("The BS-EN program aims to develop students into business entrepreneurs. It equips students " +
                "with the knowledge and skills necessary for effective research, analysis, and project implementation " +
                "for small-scale businesses. The program also includes subjects in personnel management and labor relations, " +
                "purchasing management, taxation, merchandising, and marketing.");

        List<String> finmgt = new ArrayList<String>();
        finmgt.add("The BSBA-FMA is a non-board accounting degree program designed for those who want to take accounting as their major field of study. The program focuses on relevant topics within two of the functional areas in accounting: financial and management accounting.\n" +
                "\n" +
                "The program incorporates courses in management, e-commerce, business law, taxation, " +
                "financial and management accounting, designed to equip students, as future members of the workforce, " +
                "with the necessary technical working knowledge of the business environment by exposing them to actual work " +
                "situations through the Practicum Program where they work for a minimum of 200 hours " +
                "(during their last term) in a company earlier qualified and approved by the department.");

        List<String> humres = new ArrayList<String>();
        humres.add("The BSBA-HRDM Program aims to prepare the graduates for a career in the field of Human Resources " +
                "Management in various corporations whether in the manufacturing, marketing and service sectors, or in the " +
                "different types of industries such as pharmaceutical, semi-conductor, food and beverage, banking industries " +
                "and other types of organizations.\n" +
                "\n" +
                "Built into the program is the evolution of the Human Resources (HR) profession at the various stages of " +
                "development of an enterprise.\n" +
                "\n" +
                "Students are able to integrate all their learning in the major courses in the Practicum Program where " +
                "they work for a minimum of 200 hours (during their last term) in a company earlier qualified and approved " +
                "by the department.");

        List<String> logist = new ArrayList<String>();
        logist.add("The BSBA-LSCM program provides a comprehensive and integrated mix of logistics science core courses such " +
                "as transport and physical distribution, purchasing management, risk analysis and assessment, supply chain " +
                "distribution, warehousing, inventory control, quality management, customer service and others combined with " +
                "people management, communications, finance, quantitative methods, accounting, information systems, law, " +
                "economics and strategic planning.\n" +
                "\n" +
                "The program aims to impart the theories and concepts in the classroom, and then gives the students training " +
                "on the practical aspects of the various areas by exposing them to actual work situations through practicum " +
                "assignment in organizations where they work for a minimum of 200 hours. Likewise, students are assigned to " +
                "do research on specific logistics issues and concerns, and their findings presented in class.");

        List<String> markmgt = new ArrayList<String>();
        markmgt.add("The BSBA-MM is a four-year degree program. It is designed for those who want to develop or enter a " +
                "career in marketing, either for entrepreneurial or corporate work in marketing management. It also aims " +
                "to develop the students' knowledge, skills and attitude to ensure they cope effectively with the changing " +
                "nature of market demands. The program provides a comprehensive, integrated program of marketing courses " +
                "combined with people management, communications, finance, quantitative methods, accounting, information " +
                "systems, law, economics and strategic management.\n" +
                "\n" +
                "The program aims to impart the theories and concepts in the classroom, and the students are given training " +
                "on the practical aspects of the various areas of marketing by exposing them to actual work situations " +
                "through assignments in organizations (either for profit or non-profit). Students are assigned to research " +
                "on specific marketing topics, and their findings are presented in class. Finally, the students are able to " +
                "integrate all their learning in the major courses in the Practicum Program where they work for a minimum of 200 hours (during their last term) in a company earlier qualified and approved by the department.");

        listDataChild.put(listDataHeader.get(0), progcert);
        listDataChild.put(listDataHeader.get(1), acctncy); // Header, Child data
        listDataChild.put(listDataHeader.get(2), entrep);
        listDataChild.put(listDataHeader.get(3), finmgt);
        listDataChild.put(listDataHeader.get(4), humres);
        listDataChild.put(listDataHeader.get(5), logist);
        listDataChild.put(listDataHeader.get(6), markmgt);
    }
}
