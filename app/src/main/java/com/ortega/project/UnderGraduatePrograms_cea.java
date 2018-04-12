package com.ortega.project;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.Toast;

public class UnderGraduatePrograms_cea extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_graduate_programs_cea);

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
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

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
        expListView.setOnChildClickListener(new OnChildClickListener() {

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
        listDataHeader.add("Architecture");
        listDataHeader.add("Civil Engineering");
        listDataHeader.add("Electrical Engineering");
        listDataHeader.add("Computer Engineering");
        listDataHeader.add("Electronics Engineering");
        listDataHeader.add("Environmental and Sanitary Engineering");
        listDataHeader.add("Industrial Engineering");
        listDataHeader.add("Mechanical Engineering");

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
                "First school in the National Capital Region with Level IV Accredited Status in Marine Engineering\n" +
                "In recent years, TIP had the privilege to receive international and national recognition for providing " +
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
                "of Conformity was issued by Det Norske Veritas (DNV) Certification Ltd., a certifying body accredited by the " +
                "United Kingdom Accreditation Service (UKAS).\n" +
                "\n" +
                "Autonomous Status. In November 2009, the Commission on Higher Education (CHED) awarded TIP Quezon City with " +
                "Autonomous Status. Autonomous status is the highest possible award given by CHED to a higher education " +
                "institution in recognition of its exemplary performance shown in the provision of quality higher education, " +
                "research and extension work. With TIP Quezon City's autonomous status, it joined the exclusive league of 42 " +
                "autonomous schools from 1,741 higher education institutions nationwide.\n" +
                "\n" +
                "Levels of Accreditation. TIP has various accreditation levels for its different program offerings under the " +
                "Federation of Accrediting Agencies of the Philippines-Philippine Association of Colleges and Universities " +
                "Commission on Accreditation (FAAP-PACUCOA). In 2010, TIPQC was granted Level III Reaccredited Status in Marine " +
                "Engineering. In the same year the school was also recognized by the PACUCOA as the school with the First " +
                "Marine Engineering Program that have been granted Level III Reaccredited Status in the National Capital " +
                "Region (NCR).");

        List<String> archi = new ArrayList<String>();
        archi.add("The Architecture program develops students' skills in architectural design, freehand drawing, surveying, and model making and rendering, " +
                "as well as skills in management related to construction projects. " +
                "It includes subjects in architectural design, construction materials, and construction management."
        + "\n\nThe program is designed to provide a broad foundation for the development of social and environmental " +
                "awareness, problem-solving ability and design creativity." + "\n\nRecognition:\n" +
                "From PACUCOA: Level II First Reaccredited Status in Architecture" + "\n\nProgram Educational Objectives\n" +
                "\n" +
                "Three to five years after graduation, the Architecture alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Architecture and/or other endeavors or advocacies supported by their acquired education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the T.I.P. mission values, pursuing continuing education, and practicing continuous quality improvement (CQI) in their personal lives;\n" +
                "- continuously scanning, adopting and building on the best practices in their field.");

        List<String> ce = new ArrayList<String>();
        ce.add("The Civil Engineering program is a five-year program that provides students the knowledge " +
                "and expertise necessary in planning, design, construction, supervision, and maintenance of " +
                "facilities essential to modern life. These facilities vary widely in nature, size, and scope and include " +
                "offshore structures, bridges, buildings, tunnels, highways, transit systems, dams, airports, ports and harbors, towers, " +
                "and water distribution systems. The program also provides students the fundamental skills in land surveying, highway and " +
                "transportation engineering design, structural design, and construction materials selection and testing. It also includes " +
                "topics in flood controls, " + "landslide, air and water pollution, and the design of facilities to " +
                "withstand earthquakes and natural hazards." + "\n\nAccredited by the Engineering Accreditation Commission (EAC) of ABET (www.abet.org)\n" +
                "\n" +
                "Accredited by Philippine Technological Council (PTC)\n" +
                "\n" +
                "Recognition:\n" +
                "From CHED: Center of Development (COD) in Civil Engineering (BSCE)\n" +
                "From PACUCOA: Level IV Accredited Status in Civil Engineering\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "The Civil Engineering program has adopted the following educational objectives.\n" +
                "\n" +
                "Three to five years after graduation, the Civil Engineering alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Civil Engineering and/or other endeavors or advocacies supported by their acquired civil engineering education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the TIP mission values, pursuing continuing education, and practicing continuous quality improvement in their personal lives\n" +
                "- continuously scanning, adopting, and building on the best practices in their field.");

        List<String> ee = new ArrayList<String>();
        ee.add("The Electrical Engineering program deals with the generation, transmission, distribution and utilization of electricity. It also deals with the design, operation and protection, maintenance and economics of electrical systems with emphasis on ethical values to harness economically and safely the materials, and forces of nature for the benefit of society and the environment.\n" +
                "\n" +
                "Accredited by the Engineering Accreditation Commission (EAC) of ABET (www.abet.org)\n" +
                "\n" +
                "Accredited by Philippine Technological Council (PTC)\n" +
                "\n" +
                "Recognition:\n" +
                "From PACUCOA: Level IV Accredited Status in Electrical Engineering\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "The Electrical Engineering program has adopted the following educational objectives.\n" +
                "\n" +
                "Three to five years after graduation, the Electrical Engineering alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Electrical Engineering and/or other endeavors or advocacies supported by their acquired electrical engineering education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the TIP mission values, pursuing continuing education, and practicing continuous quality improvement in their personal lives;\n" +
                "- continuously scanning, adopting, and building on the best practices in their field.");

        List<String> compe = new ArrayList<String>();
        compe.add("Computer Engineering is a profession that applies engineering principles and methodologies in the analysis, design, implementation and management of hardware, software and the integration of both.\n" +
                "\n" +
                "The program includes courses in computer hardware, system development and design, microelectronics and embedded systems, data communication and network administration, and software development and design.\n" +
                "\n" +
                "Accredited by the Engineering Accreditation Commission (EAC) of ABET (www.abet.org)\n" +
                "\n" +
                "Accredited by Philippine Technological Council (PTC)\n" +
                "\n" +
                "Recognition:\n" +
                "From CHED: Center of Excellence (COE) in Computer Engineering (BSCpE)\n" +
                "From PACUCOA: Level IV Accredited Status in Computer Engineering\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "The Computer Engineering program has adopted the following educational objectives.\n" +
                "\n" +
                "Three to five years after graduation, the Computer Engineering alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Computer Engineering and/or other endeavors or advocacies supported by their acquired computer engineering education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the TIP mission values, pursuing continuing education, and practicing continuous quality improvement in their personal lives;\n" +
                "- continuously scanning, adopting, and building on the best practices in their field.");

        List<String> electe = new ArrayList<String>();
        electe.add("Electronics Engineering is a profession that integrates available and emerging technologies with knowledge of mathematics, natural, social and applied sciences to conceptualize, design, and implement new, improved, or innovative electronic, computer and communication systems, devices, goods, services and processes.\n" +
                "\n" +
                "The Electronics Engineering program focuses on how to design, construct, integrate, operate, and maintain electronic equipment, electronic devices and circuits used in the transmission and processing of information.\n" +
                "\n" +
                "Accredited by the Engineering Accreditation Commission (EAC) of ABET (www.abet.org)\n" +
                "\n" +
                "Accredited by Philippine Technological Council (PTC)\n" +
                "\n" +
                "Recognition:\n" +
                "From PACUCOA: Level III Reaccredited Status in Electronics Engineering\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "Three to five years after graduation, the Electronics Engineering alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Electronics Engineering and/or other endeavors or advocacies supported by their acquired electronics engineering education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the TIP mission values, pursuing continuing education, and practicing continuous quality improvement in their personal lives;\n" +
                "- continuously scanning, adopting, and building on the best practices in their field.");

        List<String> ense = new ArrayList<String>();
        ense.add("Environmental and Sanitary Engineering is a profession that involves planning, design, management, construction, operation, maintenance in the fields of water supply engineering, solid waste management, sewage and wastewater engineering, environmental management and engineering, plumbing, fire protection and public health engineering all in accordance with governing laws, code of ethics and local and global technological trends and developments.\n" +
                "\n" +
                "Accredited by the Engineering Accreditation Commission (EAC) of ABET (www.abet.org)\n" +
                "\n" +
                "Accredited by Philippine Technological Council (PTC)\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "The Environmental and Sanitary Engineering program has adopted the following educational objectives.\n" +
                "\n" +
                "Three to five years after graduation, the Environmental and Sanitary Engineering alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Environmental and Sanitary Engineering and/or other endeavors or advocacies supported by their acquired environmental and sanitary engineering education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the TIP mission values, pursuing continuing education, and practicing continuous quality improvement in their personal lives;\n" +
                "- continuously scanning, adopting, and building on the best practices in their field.");

        List<String> ie = new ArrayList<String>();
        ie.add("The Industrial Engineering program brings together the various sciences concerned with technology, the production of goods, performance of services and the way in which people work.\n" +
                "\n" +
                "Industrial engineers integrate human, information, material, monetary, and technological resources to produce quality and cost-competitive goods and services in a healthy and efficient work environment.\n" +
                "\n" +
                "Industrial Engineering covers a broad spectrum including production planning and control, manufacturing systems and processes, facilities design, human factors, occupational safety, quality control, systems reliability, and systems analysis and design with a strong emphasis on advanced computing.\n" +
                "\n" +
                "Accredited by the Engineering Accreditation Commission (EAC) of ABET (www.abet.org)\n" +
                "\n" +
                "Accredited by Philippine Technological Council (PTC)\n" +
                "\n" +
                "Recognition:\n" +
                "From PACUCOA: Level IV Accredited Status in Industrial Engineering\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "The Industrial Engineering program has adopted the following educational objectives.\n" +
                "\n" +
                "Three to five years after graduation, the Industrial Engineering alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Industrial Engineering and/or other endeavors or advocacies supported by their acquired industrial engineering education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the TIP mission values, pursuing continuing education, and practicing continuous quality improvement in their personal lives;\n" +
                "- continuously scanning, adopting, and building on the best practices in their field.");

        List<String> me = new ArrayList<String>();
        me.add("The Mechanical Engineering program is centered on the principles involved in the generation of power through appropriately designed machines.\n" +
                "\n" +
                "The program emphasizes the various types of power generating machines, their functions, components, construction, and operation and maintenance.\n" +
                "\n" +
                "Specifically, it is concerned with mechanical design, energy conversion, fuel and combustion technologies, heat transfer, materials, noise control and acoustics, manufacturing processes, rail transportation, automatic control, product safety and reliability, solar energy, and their technological impact to society.\n" +
                "\n" +
                "Accredited by the Engineering Accreditation Commission (EAC) of ABET (www.abet.org)\n" +
                "\n" +
                "Accredited by Philippine Technological Council (PTC)\n" +
                "\n" +
                "Recognition:\n" +
                "From PACUCOA: Level II First Reaccredited Status in Mechanical Engineering\n" +
                "\n" +
                "PROGRAM EDUCATIONAL OBJECTIVES\n" +
                "\n" +
                "The Mechanical Engineering program has adopted the following educational objectives.\n" +
                "\n" +
                "Three to five years after graduation, the Mechanical Engineering alumni shall:\n" +
                "\n" +
                "have advanced their practice or achievement in the field of Mechanical Engineering and/or other endeavors or advocacies supported by their acquired mechanical engineering education;\n" +
                "strive to be globally competitive through\n" +
                "- living by the TIP mission values, pursuing continuing education, and practicing continuous quality improvement in their personal lives;\n" +
                "- continuously scanning, adopting, and building on the best practices in their field.");

        listDataChild.put(listDataHeader.get(0), progcert);
        listDataChild.put(listDataHeader.get(1), archi); // Header, Child data
        listDataChild.put(listDataHeader.get(2), ce);
        listDataChild.put(listDataHeader.get(3), ee);
        listDataChild.put(listDataHeader.get(4), compe);
        listDataChild.put(listDataHeader.get(5), electe);
        listDataChild.put(listDataHeader.get(6), ense);
        listDataChild.put(listDataHeader.get(7), ie);
        listDataChild.put(listDataHeader.get(8), me);
    }
}
