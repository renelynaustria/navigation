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

public class UnderGraduatePrograms_cite extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_graduate_programs_cite);

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
        listDataHeader.add("Associate in Computer Technology");
        listDataHeader.add("Computer Science");
        listDataHeader.add("Information Systems");
        listDataHeader.add("Information Technology");

        // Adding child data
        List<String> progcert = new ArrayList<String>();
        progcert.add("NATIONAL AND INTERNATIONAL RECOGNITION\n" +
                "\n" +
                "International Certification\n" +
                "\n" +
                "Quality Management System certified compliant to the international standard ISO 9001:2008 by Det Norske Veritas\n" +
                "\n" +
                "CHED Recognitions\n" +
                "\n" +
                "Center of Excellence (COE) in Information Technology Education (ITE) which includes the programs:\n" +
                "Computer Science\n" +
                "Information Technology\n" +
                "Information Systems\n" +
                "PACUCOA Accreditation Levels\n" +
                "\n" +
                "Level IV Accredited Status in Computer Science and Information Technology\n" +
                "Level III Reaccredited Status in Information Systems\n" +
                "First school in the Philippines with Level IV Accredited Status in Computer Science\n" +
                "In recent years, TIP had the privilege to receive international and national recognitions for " +
                "providing quality education to students. The said recognitions are the following:\n" +
                "\n" +
                "ISO Certification. TIP is one among a select number of schools in the Philippines with a Quality " +
                "Management System (QMS) certified compliant to the international standard ISO 9001:2008.\n" +
                "\n" +
                "ISO 9001:2008 specifies requirements for a quality management system where an organization 1) needs to " +
                "demonstrate its ability to consistently provide product or service that meets customer and applicable " +
                "regulatory requirements, and 2) aims to enhance customer satisfaction through the effective application " +
                "of the system, including processes for continual improvement of the system and the assurance of conformity " +
                "to customer and applicable regulatory requirements.\n" +
                "\n" +
                "The certification covers the entire operation and services of the institution’s provision of tertiary " +
                "education in engineering, architecture, business, information and communications technology, and maritime " +
                "education including training for the national and international industries. TIP’s ISO 9001:2008 Certificate " +
                "of Conformity was issued by Det Norske Veritas (DNV) Certification Ltd., a certifying body accredited by the " +
                "United Kingdom Accreditation Service (UKAS).\n" +
                "\n" +
                "Autonomous Status. In November 2009, the Commission on Higher Education (CHED) awarded TIP Quezon City with " +
                "Autonomous Status. Autonomous status is the highest possible award given by CHED to a higher education " +
                "institution in recognition of its exemplary performance shown in the provision of quality higher education, " +
                "research and extension work. With TIP Quezon City's autonomous status, it joined the exclusive league of 42 " +
                "autonomous schools from 1,741 higher education institutions nationwide.\n" +
                "\n" +
                "Center of Excellence. TIP Quezon City was recognized as CHED Center of Excellence (COE) in Information " +
                "Technology, Computer Science and Information Systems.\n" +
                "\n" +
                "Levels of Accreditation. TIP Quezon City has various accreditation levels for its different program " +
                "offerings under the Federation of Accrediting Agencies of the Philippines--Philippine Association of " +
                "Colleges and Universities Commission on Accreditation (FAAP-PACUCOA). TIP Quezon City has two (2) Level III " +
                "accredited programs--Computer Science and Information Technology. Recently, Level II Reaccredited Status was " +
                "granted to TIPQC for its Information Systems program.");

        List<String> assocct = new ArrayList<String>();
        assocct.add("The Associate in Computer Technology (ACT) is a two-year program that teaches students analysis, " +
                "design, implementation and maintenance of software applications for business or scientific purposes.\n" +
                "\n" +
                "The program includes comprehensive lectures on different programming languages and computer systems. " +
                "The program allows for flexibility in one’s career be it towards the actual software applications design " +
                "or computer systems development and maintenance.");

        List<String> compsci = new ArrayList<String>();
        compsci.add("The Computer Science program is focused on the concepts and theories, algorithmic foundations, implementation and application of information and computing solutions.\n" +
                "The program prepares students to be IT professionals and researchers, and to be proficient in designing and developing computing solutions.\n" +
                "\n" +
                "Accredited by the Computing Accreditation Commission (CAC) of ABET (www.abet.org)\n" +
                "\n" +
                "Accredited by Philippine Information and Computing Accreditation Board (PICAB)\n" +
                "\n" +
                "Recognition:\n" +
                "From CHED: Center of Excellence (COE) for Information Technology Education (ITE)\n" +
                "From PACUCOA: Level IV Accredited Status in Computer Science\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "The Computer Science program has adopted the following educational objectives.\n" +
                "\n" +
                "Three to five years after graduation, the Computer Science alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Computer Science and/or other endeavors or advocacies supported by their acquired computer science education;\n" +
                "be globally competitive through\n" +
                "- living by the TIP mission values, pursuing continuing education, and practicing continuous quality improvement in their personal lives;\n" +
                "- continuously scanning, adopting, and building on the best practices in their field.");

        List<String> finmgt = new ArrayList<String>();
        finmgt.add("The BSBA-FMA is a non-board accounting degree program designed for those who want to take accounting as their major field of study. The program focuses on relevant topics within two of the functional areas in accounting: financial and management accounting.\n" +
                "\n" +
                "The program incorporates courses in management, e-commerce, business law, taxation, " +
                "financial and management accounting, designed to equip students, as future members of the workforce, " +
                "with the necessary technical working knowledge of the business environment by exposing them to actual work " +
                "situations through the Practicum Program where they work for a minimum of 200 hours " +
                "(during their last term) in a company earlier qualified and approved by the department.");

        List<String> is = new ArrayList<String>();
        is.add("The Information Systems program is focused on the design and implementation of solutions that integrate " +
                "information technology with business processes.\n" +
                "The program prepares students to be IT professionals and experts on design and implementation of IS " +
                "for business processes.\n" +
                "\n" +
                "Accredited by the Computing Accreditation Commission (CAC) of ABET (www.abet.org)\n" +
                "\n" +
                "Accredited by Philippine Information and Computing Accreditation Board (PICAB)\n" +
                "\n" +
                "Recognition:\n" +
                "From CHED: Center of Excellence (COE) for Information Technology Education (ITE)\n" +
                "From PACUCOA: Level III Reaccredited Status in Information Systems\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "The Information Systems program has adopted the following educational objectives.\n" +
                "\n" +
                "Three to five years after graduation, the Information Systems alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Information Systems and/or other endeavors or " +
                "advocacies supported by their acquired information systems education;\n" +
                "be globally competitive through\n" +
                "- living by the TIP mission values, pursuing continuing education, and practicing continuous quality " +
                "improvement in their personal lives;\n" +
                "- continuously scanning, adopting, and building on the best practices in their field.");

        List<String> it = new ArrayList<String>();
        it.add("The Information Technology program is focused on the utilization of computers and computer software to " +
                "plan, install, customize, operate, manage, administer and maintain information technology systems.\n" +
                "\n" +
                "The program prepares students to be IT professionals who are well versed in application installation, operation, development, maintenance and administration; as well as familiar with hardware installation, operation, and maintenance.\n" +
                "\n" +
                "Accredited by the Computing Accreditation Commission (CAC) of ABET (www.abet.org)\n" +
                "\n" +
                "Accredited by Philippine Information and Computing Accreditation Board (PICAB)\n" +
                "\n" +
                "Recognition:\n" +
                "From CHED: Center of Excellence (COE) for Information Technology Education (ITE)\n" +
                "From PACUCOA: Level IV Accredited Status in Information Technology\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "The Information Technology program has adopted the following educational objectives.\n" +
                "\n" +
                "Three to five years after graduation, the Information Technology alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Information Technology and/or other " +
                "endeavors or advocacies supported by their acquired information technology education;\n" +
                "be globally competitive through\n" +
                "- living by the TIP mission values, pursuing continuing education, and practicing continuous quality " +
                "improvement in their personal lives;\n" +
                "- continuously scanning, adopting, and building on the best practices in their field.");

        listDataChild.put(listDataHeader.get(0), progcert);
        listDataChild.put(listDataHeader.get(1), assocct); // Header, Child data
        listDataChild.put(listDataHeader.get(2), compsci);
        listDataChild.put(listDataHeader.get(3), is);
        listDataChild.put(listDataHeader.get(4), it);
    }
}
