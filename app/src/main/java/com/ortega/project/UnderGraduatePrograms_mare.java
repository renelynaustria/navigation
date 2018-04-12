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

public class UnderGraduatePrograms_mare extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_graduate_programs_mare);

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
        listDataHeader.add("College of Marine Engineering");

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
                "Level IV Accredited Status in Marine Engineering\n" +
                "In recent years, TIP had the privilege to receive international and national recognitions for providing " +
                "quality education to students. The said recognitions are the following:\n" +
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
                "of Conformity was issued by Det Norske Veritas (DNV) Certification Ltd., a certifying body accredited by " +
                "the United Kingdom Accreditation Service (UKAS).\n" +
                "\n" +
                "Deregulated Status. In December 2009, the CHED awarded TIP Manila with a Deregulated Status, a prestigious " +
                "award for a higher education institution, in recognition of TIP Manila's exemplary performance shown in the " +
                "provision of quality higher education, research and extension work.\n" +
                "\n" +
                "Levels of Accreditation. TIP has various accreditation levels for its different program offerings under the " +
                "Federation of Accreditation Agencies of the Philippines--Philippine Association of Colleges and Universities " +
                "Commission on Accreditation (FAAP--PACUCOA). TIP Manila was granted Level II Reaccredited Status for its " +
                "Marine Engineering and Marine Transportation program.");

        List<String> mare = new ArrayList<String>();
        mare.add("The Marine Engineering program is a 4-year degree program intended to equip students with the competence to operate, maintain, repair, and reassemble ship board plant and equipment and its associated control systems. In addition, graduates should have the competence to maintain a safe engineering watch, ensuring compliance with pollution-prevention requirements and able to respond effectively during emergencies on board ships.\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "The BSMarE program aims to produce graduates who:\n" +
                "\n" +
                "are competent to carry out safely the watchkeeping duties of an Officer-in-charge (OIC) of an engineering watch in a manned engine-room or designated duty engineer officer in a periodically unmanned engine-room on a seagoing ship powered by main propulsion machinery of 750 kW propulsion power or more, both at sea and in port;\n" +
                "are fully conversant with the basic principles to be observed in keeping an engineering watch as per STCW Regulation VIII/2 and Chapter VIII of the STCW Code; and\n" +
                "have advanced their practice or achievement in Marine Engineering and/or other endeavors or advocacies supported by their acquired maritime education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the TIP mission values, pursuing continuing education, and practicing continuous quality improvement (CQI) in their personal lives;\n" +
                "- continuously scanning, adopting and building on the best practices in their field");

        listDataChild.put(listDataHeader.get(0), progcert);
        listDataChild.put(listDataHeader.get(1), mare); // Header, Child data

    }
}

