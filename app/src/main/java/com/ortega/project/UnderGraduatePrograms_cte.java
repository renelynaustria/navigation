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

public class UnderGraduatePrograms_cte extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_graduate_programs_cte);

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
        listDataHeader.add("Secondary Education Major in English");
        listDataHeader.add("Secondary Education Major in Mathematics");
        listDataHeader.add("Secondary Education Major in Physical Sciences");

        // Adding child data
        List<String> progcert = new ArrayList<String>();
        progcert.add("INTERNATIONAL RECOGNITION\n" +
                "\n" +
                "International Certification\n" +
                "\n" +
                "Quality Management System certified compliant to the international standard ISO 9001:2008 by Det " +
                "Norske Veritas\n" +
                "\n" +
                "In the recent years, TIP had the privilege to receive recognition for exemplary performance in the provision " +
                "of quality education such as:\n" +
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
                "Autonomous Status. In November 2009, the Commission on Higher Education (CHED) awarded TIP Quezon City with " +
                "Autonomous Status. Autonomous status is the highest possible award given by CHED to a higher education " +
                "institution. This is in recognition of its exemplary performance shown in the provision of quality higher " +
                "education, research and extension work. With TIP Quezon City’s autonomous status, it joined the exclusive " +
                "league of 42 autonomous schools from 1,741 higher education institutions nationwide.");

        List<String> english = new ArrayList<String>();
        english.add("The College of Education aspires to produce knowledgeable, caring, competent and responsible " +
                "teachers who will excite the interest of every high school student towards exploration of the path to " +
                "lifelong learning.\n" +
                "\n" +
                "It offers Bachelor of Secondary Education with three majors, namely English, Mathematics and Physical " +
                "Sciences.\n" +
                "\n" +
                "The College conforms to the New Teacher Education Curriculum where students are provided with actual " +
                "learning experiences and training as early as in the second year gradually immersing them to the real world " +
                "of teaching.\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "Three to five years after graduation, the T.I.P. Bachelor of Secondary Education alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Secondary Education and/or other endeavors " +
                "or advocacies supported by their acquired education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the T.I.P. mission values, pursuing continuing education, and practicing continuous quality " +
                "improvement (CQI) in their personal lives;\n" +
                "- continuously scanning, adopting and building on the best practices in their field.");

        List<String> math = new ArrayList<String>();
        math.add("The College of Education aspires to produce knowledgeable, caring, competent and responsible teachers who " +
                "will excite the interest of every high school student towards exploration of the path to lifelong learning.\n" +
                "\n" +
                "It offers Bachelor of Secondary Education with three majors, namely English, Mathematics and Physical " +
                "Sciences.\n" +
                "\n" +
                "The College conforms to the New Teacher Education Curriculum where students are provided with actual " +
                "learning experiences and trainings as early as in the second year gradually immersing them to the real " +
                "world of teaching.\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "Three to five years after graduation, the T.I.P. Bachelor of Secondary Education alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Secondary Education and/or other endeavors or " +
                "advocacies supported by their acquired education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the T.I.P. mission values, pursuing continuing education, and practicing continuous quality " +
                "improvement (CQI) in their personal lives;\n" +
                "- continuously scanning, adopting and building on the best practices in their field.");

        List<String> physicals = new ArrayList<String>();
        physicals.add("The College of Education aspires to produce knowledgeable, caring, competent and responsible teachers " +
                "who will excite the interest of every high school student towards exploration of the path to lifelong " +
                "learning.\n" +
                "\n" +
                "It offers Bachelor of Secondary Education with three majors, namely English, Mathematics and Physical " +
                "Sciences.\n" +
                "\n" +
                "The College conforms to the New Teacher Education Curriculum where students are provided with actual " +
                "learning experiences and trainings as early as in the second year gradually immersing them to the real " +
                "world of teaching.\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "Three to five years after graduation, the T.I.P. Bachelor of Secondary Education alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Secondary Education and/or other endeavors " +
                "or advocacies supported by their acquired education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the T.I.P. mission values, pursuing continuing education, and practicing continuous quality " +
                "improvement (CQI) in their personal lives;\n" +
                "- continuously scanning, adopting and building on the best practices in their field.");

        listDataChild.put(listDataHeader.get(0), progcert);
        listDataChild.put(listDataHeader.get(1), english); // Header, Child data
        listDataChild.put(listDataHeader.get(2), math);
        listDataChild.put(listDataHeader.get(3), physicals);
    }
}
