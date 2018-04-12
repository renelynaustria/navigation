package com.ortega.project;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Locale;

public class TipDirectory extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback {

    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    private LatLng mLatLng;
    private static GoogleMap mMap;

    private double mLatitue = 0;
    private double mLongitude = 0;

    private static ArrayList<LatLng> points;
    private static ArrayList<String> pointStrings;

    private static Marker marker;
    private static Polyline polyline;

    private static int pointCounter = 0;

    private Spinner from_building;
    private Spinner to_building;

    public static final int REQUEST_LOCATION_CODE = 99;

    private static Location[] buildingLocation = new Location[36];
    private static int[] radius = new int[36];
    private static LatLng buildingCenters[] = {
            new LatLng(14.625054, 121.061716), //1
            new LatLng(14.625142, 121.062617), //2
            new LatLng(14.624665, 121.062814), //3
            new LatLng(14.625288, 121.062801), //4
            new LatLng(14.626338, 121.062544), //5
            new LatLng(14.626397, 121.061528), //6
            new LatLng(14.625604, 121.062841), //7
            new LatLng(14.624983, 121.062410), //8
            new LatLng(14.626234, 121.062081)  //9
    };

    private static TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_directory);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        buildGoogleApiClient();

        initializeBuildings();

        /*mMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigate_campus_map)).getMap();*/
        SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigate_campus_map));
        mapFragment.getMapAsync(this);

        //btnFindPath = (Button) findViewById(R.id.btnFindPath);
        from_building = (Spinner) findViewById(R.id.etOrigin);
        from_building.getBackground().setColorFilter(Color.parseColor("#727272"), PorterDuff.Mode.SRC_ATOP);

        to_building = (Spinner) findViewById(R.id.etDestination);
        to_building.getBackground().setColorFilter(Color.parseColor("#727272"), PorterDuff.Mode.SRC_ATOP);

        /*Spinner spinner = (Spinner) findViewById(R.id.navigate_campus_spinner);
        spinner.getBackground().setColorFilter(Color.parseColor("ff2b2b2b"), PorterDuff.Mode.SRC_ATOP);*/

       /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.maptype_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        ArrayAdapter<CharSequence> from_adapter = ArrayAdapter.createFromResource(this,
                R.array.from_building, android.R.layout.simple_spinner_item);
        from_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from_building.setAdapter(from_adapter);

        ArrayAdapter<CharSequence> to_adapter = ArrayAdapter.createFromResource(this,
                R.array.to_building, android.R.layout.simple_spinner_item);
        to_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to_building.setAdapter(to_adapter);

       /* spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                    case 1:
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    case 2:
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    case 3:
                        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        mTts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                mTts.setLanguage(Locale.ENGLISH);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission is granted

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else { //permission is denied
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_LONG).show();
                }
                return;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(14.625846028617033, 121.06218315660952), 18));

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        if (!mGoogleApiClient.isConnected())
            mGoogleApiClient.connect();
    }

    private void checkPermission(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
    }

    public void initializeBuildings(){

        //-----Dela Costa Homes 2, Caloocan City------

        buildingLocation[0] = new Location("Developers' house");
        buildingLocation[0].setLatitude(14.7484655);
        buildingLocation[0].setLongitude(121.0724946);
        radius[0] = 8;

        //-----Building 1-------

        buildingLocation[1] = new Location("Building 1");
        buildingLocation[1].setLatitude(14.62512275947705);
        buildingLocation[1].setLongitude(121.06190487742424);
        radius[1] = 12;

        buildingLocation[2] = new Location("Building 1");
        buildingLocation[2].setLatitude(14.625082532338839);
        buildingLocation[2].setLongitude(121.06173992156982);
        radius[2] = 8;


        buildingLocation[3] = new Location("Building 1");
        buildingLocation[3].setLatitude(14.625038412199839);
        buildingLocation[3].setLongitude(121.06164872646332);
        radius[3] = 7;

        buildingLocation[4] = new Location("Building 1");
        buildingLocation[4].setLatitude(14.624946451957452);
        buildingLocation[4].setLongitude(121.06152802705765);
        radius[4] = 12;

        //-----Building 2-------

        buildingLocation[5] = new Location("Building 2");
        buildingLocation[5].setLatitude(14.625018947429826);
        buildingLocation[5].setLongitude(121.06265991926193);
        radius[5] = 12;

        buildingLocation[6] = new Location("Building 2");
        buildingLocation[6].setLatitude(14.625177260842587);
        buildingLocation[6].setLongitude(121.0625995695591);
        radius[7] = 12;

        buildingLocation[7] = new Location("Building 2");
        buildingLocation[7].setLatitude(14.6252265715544);
        buildingLocation[7].setLongitude(121.06268674135208);
        radius[7] = 10;

        buildingLocation[8] = new Location("Building 2");
        buildingLocation[8].setLatitude(14.624946451957452);
        buildingLocation[8].setLongitude(121.06152802705765);
        radius[8] = 12;


        //-----Building 3-------

        buildingLocation[9] = new Location("Building 3");
        buildingLocation[9].setLatitude(14.62487880103474);
        buildingLocation[9].setLongitude(121.06277123093605);
        radius[9] = 9;

        buildingLocation[10] = new Location("Building 3");
        buildingLocation[10].setLatitude(14.62471010618127);
        buildingLocation[10].setLongitude(121.06278665363789);
        radius[10] = 14;

        buildingLocation[11] = new Location("Building 3");
        buildingLocation[11].setLatitude(14.624559578316866);
        buildingLocation[11].setLongitude(121.06286711990833);
        radius[11] = 16;

        //-----Building 4-------

        buildingLocation[12] = new Location("Building 4");
        buildingLocation[12].setLatitude(14.625317407047197);
        buildingLocation[12].setLongitude(121.06280945241451);
        radius[12] = 7;

        buildingLocation[13] = new Location("Building 4");
        buildingLocation[13].setLatitude(14.62524214335584);
        buildingLocation[13].setLongitude(121.06282621622086);
        radius[13] = 5;

        //-----Building 5-------

        buildingLocation[14] = new Location("Building 5");
        buildingLocation[14].setLatitude(14.626001267296672);
        buildingLocation[14].setLongitude(121.06272295117378);
        radius[14] = 13;

        buildingLocation[15] = new Location("Building 5");
        buildingLocation[15].setLatitude(14.62613492479099);
        buildingLocation[15].setLongitude(121.06264248490334);
        radius[15] = 11;

        buildingLocation[16] = new Location("Building 5");
        buildingLocation[16].setLatitude(14.626280260920497);
        buildingLocation[16].setLongitude(121.06257542967796);
        radius[16] = 11;

        buildingLocation[17] = new Location("Building 5");
        buildingLocation[17].setLatitude(14.62642170406415);
        buildingLocation[17].setLongitude(121.06250166893005);
        radius[17] = 11;

        buildingLocation[18] = new Location("Building 5");
        buildingLocation[18].setLatitude(14.626555361262893);
        buildingLocation[18].setLongitude(121.06242656707764);
        radius[18] = 11;

        buildingLocation[19] = new Location("Building 5");
        buildingLocation[19].setLatitude(14.626655279698141);
        buildingLocation[19].setLongitude(121.06232598423958);
        radius[19] = 6;

        buildingLocation[20] = new Location("Building 5");
        buildingLocation[20].setLatitude(14.626694208946336);
        buildingLocation[20].setLongitude(121.062401086092);
        radius[20] = 6;

        //-----Building 6-------

        buildingLocation[21] = new Location("Building 6");
        buildingLocation[21].setLatitude(14.626562974139905);
        buildingLocation[21].setLongitude(121.06145158410072);
        radius[21] = 7;

        buildingLocation[22] = new Location("Building 6");
        buildingLocation[22].setLatitude(14.626474734448045);
        buildingLocation[22].setLongitude(121.06149718165398);
        radius[22] = 7;

        buildingLocation[23] = new Location("Building 6");
        buildingLocation[23].setLatitude(14.62639557822371);
        buildingLocation[23].setLongitude(121.06154009699821);
        radius[23] = 7;

        buildingLocation[24] = new Location("Building 6");
        buildingLocation[24].setLatitude(14.626313826683354);
        buildingLocation[24].setLongitude(121.06158435344696);
        radius[24] = 7;

        buildingLocation[25] = new Location("Building 6");
        buildingLocation[25].setLatitude(14.626232075112595);
        buildingLocation[25].setLongitude(121.06162190437317);
        radius[25] = 7;

        buildingLocation[26] = new Location("Building 6");
        buildingLocation[26].setLatitude(14.62614123999822);
        buildingLocation[26].setLongitude(121.06166884303093);
        radius[26] = 7;

        //-----Building 7-------

        buildingLocation[27] = new Location("Building 7");
        buildingLocation[27].setLatitude(14.625580829724083);
        buildingLocation[27].setLongitude(121.062763184309);
        radius[27] = 8;

        buildingLocation[28] = new Location("Building 7");
        buildingLocation[28].setLatitude(14.625635330936332);
        buildingLocation[28].setLongitude(121.06286779046059);
        radius[28] = 8;

        buildingLocation[29] = new Location("Building 7");
        buildingLocation[29].setLatitude(14.625530221443476);
        buildingLocation[29].setLongitude(121.0628704726696);
        radius[29] = 5;

        buildingLocation[30] = new Location("Building 7");
        buildingLocation[30].setLatitude(14.625557472057565);
        buildingLocation[30].setLongitude(121.06293216347694);
        radius[30] = 5;

        //-----Building 8-------

        buildingLocation[31] = new Location("Building 8");
        buildingLocation[31].setLatitude(14.625095508809006);
        buildingLocation[31].setLongitude(121.06235817074776);
        radius[31] = 9;

        buildingLocation[32] = new Location("Building 8");
        buildingLocation[32].setLatitude(14.624980017845001);
        buildingLocation[32].setLongitude(121.06240645051003);
        radius[32] = 8;

        buildingLocation[33] = new Location("Building 8");
        buildingLocation[33].setLatitude(14.624890479864817);
        buildingLocation[33].setLongitude(121.06246545910835);
        radius[33] = 11;

        //-----Building 9-------

        buildingLocation[34] = new Location("Building 9");
        buildingLocation[34].setLatitude(14.626207419870973);
        buildingLocation[34].setLongitude(121.06193572282791);
        radius[34] = 18;

        buildingLocation[35] = new Location("Building 9");
        buildingLocation[35].setLatitude(14.626326803120369);
        buildingLocation[35].setLongitude(121.06220126152039);
        radius[35] = 16;

    }

    private int getCurrentLocation() {
        checkPermission();

        int selection = 0;

        mCurrentLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        mLatitue = mCurrentLocation.getLatitude();
        mLongitude = mCurrentLocation.getLongitude();

        mLatLng = new LatLng(mLatitue, mLongitude);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 17));

        for (int i = 0; i < buildingLocation.length; i++) {
            if (mCurrentLocation.distanceTo(buildingLocation[i]) <= radius[i]) {
                String name = buildingLocation[i].toString();
                if (name.contains("[Building 1"))
                    selection = 0;
                else if (name.contains("[Building 2"))
                    selection = 1;
                else if (name.contains("[Building 3"))
                    selection = 2;
                else if (name.contains("[Building 4"))
                    selection = 3;
                else if (name.contains("[Building 5"))
                    selection = 4;
                else if (name.contains("[Building 6"))
                    selection = 5;
                else if (name.contains("[Building 7"))
                    selection = 6;
                else if (name.contains("[Building 8"))
                    selection = 7;
                else if (name.contains("[Building 9"))
                    selection = 8;
                else if (name.contains("[Developers' house"))
                    selection = 9;
                else
                    selection = 10;
            }
        }
        int building = selection + 1;
        return building;
    }

    public void navigateBuildings(View view) {
        int from = from_building.getSelectedItemPosition();
        int to = to_building.getSelectedItemPosition() + 1;

        if (from == 0) {
            from = getCurrentLocation();
            if (from >= 1 && from <= 9) {
                if (from != to) {

                } else {
                    // Toast.makeText(NavigateCampusActivity.this, "Cannot navigate from the same building", Toast.LENGTH_SHORT).show();
                    Snackbar.make(view, "Cannot navigate from the same building", Snackbar.LENGTH_SHORT).show();
                }
            } else {
//                Toast.makeText(NavigateCampusActivity.this, "You are not inside the campus", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "You are not inside the campus", Snackbar.LENGTH_SHORT).show();
            }
        } else if (from != to) {
            navigateToBuilding(from, to);
        } else {
            // Toast.makeText(NavigateCampusActivity.this, "Cannot navigate from the same building", Toast.LENGTH_SHORT).show();
            Snackbar.make(view, "Cannot navigate from the same building", Snackbar.LENGTH_SHORT).show();
        }
    }

    public static void navigateToBuilding(final int fromBuilding, final int toBuilding) {
        final PolylineOptions lines = new PolylineOptions()
                .width(15).color(Color.parseColor("#FFEB3B"));

        points = new ArrayList<LatLng>();
        pointStrings = new ArrayList<>();

        if (fromBuilding == 1) {
            switch (toBuilding) {
                case 2:
                    points.add(new LatLng(14.624853, 121.061643));
                    pointStrings.add("Start here. Walk straight near the study area.");
                    points.add(new LatLng(14.625289, 121.062593));
                    pointStrings.add("The entrance is here.");
                    break;
                case 3:
                    points.add(new LatLng(14.624853, 121.061643));
                    pointStrings.add("Start here. Walk straight to building 2.");
                    points.add(new LatLng(14.625289, 121.062593));
                    pointStrings.add("Turn to your right and walk straight ahead.");
                    points.add(new LatLng(14.624933, 121.062744));
                    pointStrings.add("The entrance is here.");
                    break;
                case 4:
                    points.add(new LatLng(14.624853, 121.061643));
                    pointStrings.add("Start here. Walk straight near the congregating area.");
                    points.add(new LatLng(14.625343, 121.062730));
                    pointStrings.add("The entrance is here.");
                    break;
                case 5:
                    points.add(new LatLng(14.624853, 121.061643));//gooutside
                    pointStrings.add("Start here. Walk straight near the congregating area.");
                    points.add(new LatLng(14.625356, 121.062722));
                    pointStrings.add("Turn to your left and walk straight.");
                    points.add(new LatLng(14.625667, 121.062652));
                    pointStrings.add("Turn to your right and walk diagonally.");
                    points.add(new LatLng(14.625903, 121.062771));
                    pointStrings.add("The entrance is here.");
                    break;
                case 6:
                    points.add(new LatLng(14.624853, 121.061643));// outside
                    pointStrings.add("Start here. Walk straight near the study area.");
                    points.add(new LatLng(14.625196, 121.062371));// go straight
                    pointStrings.add("Turn to your left and walk straight.");
                    points.add(new LatLng(14.625440, 121.062252));// turn left please
                    pointStrings.add("Turn to your left and walk straight to P.E. hall.");
                    points.add(new LatLng(14.625336, 121.062034));//  now turn right and go straight
                    pointStrings.add("Turn to your right and walk straight near the P.E. hall.");
                    points.add(new LatLng(14.626053, 121.061656));// you are now on building 6
                    pointStrings.add("The entrance is here.");
                    break;
                case 7:
                    points.add(new LatLng(14.624853, 121.061643));//gooutside
                    pointStrings.add("Start here. Walk straight near the congregating area.");
                    points.add(new LatLng(14.625376, 121.062755));//walk straight
                    pointStrings.add("Turn to your left and walk straight.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your right and take a few steps.");
                    points.add(new LatLng(14.625657, 121.062746));//outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 8:
                    points.add(new LatLng(14.624853, 121.061643));// outside
                    pointStrings.add("Start here. Walk straight near the study area.");
                    points.add(new LatLng(14.625167, 121.062301));// walkstraight building 8 is outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 9:
                    points.add(new LatLng(14.624853, 121.061643));// outside
                    pointStrings.add("Start here. Walk straight to study area.");
                    points.add(new LatLng(14.625223, 121.062413)); // turn left
                    pointStrings.add("Turn to your left and walk straight ahead.");
                    points.add(new LatLng(14.625469, 121.062302)); // sa may SA to
                    pointStrings.add("Turn to your left and walk straight to P.E. hall.");
                    points.add(new LatLng(14.625343, 121.062034)); // left turn to PE hall
                    pointStrings.add("Turn to your right and walk straight near the P.E. hall.");
                    points.add(new LatLng(14.626057, 121.061653)); // sa may bldg6
                    pointStrings.add("Turn to your right and take a few steps.");
                    points.add(new LatLng(14.626114, 121.061822)); // building 9 na
                    pointStrings.add("The entrance is here.");
                    break;
            }
        } else if (fromBuilding == 2) {
            switch (toBuilding) {
                case 1:
                    points.add(new LatLng(14.625289, 121.062593));
                    pointStrings.add("Start here. Walk straight ahead near the study area.");
                    points.add(new LatLng(14.624853, 121.061643));
                    pointStrings.add("The entrance is here.");
                    break;
                case 3:
                    points.add(new LatLng(14.625289, 121.062593));
                    pointStrings.add("Start here. Walk straight ahead.");
                    points.add(new LatLng(14.624933, 121.062744));
                    pointStrings.add("The entrance is here.");
                    break;
                case 4:
                    points.add(new LatLng(14.625289, 121.062593));
                    pointStrings.add("Start here. Walk straight near the congregating area.");
                    points.add(new LatLng(14.625343, 121.062730));
                    pointStrings.add("The entrance is here.");
                    break;
                case 5:
                    points.add(new LatLng(14.625309, 121.062613));//
                    pointStrings.add("Start here. Walk straight near the congregating area.");
                    points.add(new LatLng(14.625376, 121.062755));//walk straight
                    pointStrings.add("Turn to your left and walk straight.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your right and walk diagonally.");
                    points.add(new LatLng(14.625903, 121.062771));//outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 6:
                    points.add(new LatLng(14.625311, 121.062601));// outside
                    pointStrings.add("Start here. Walk straight to study area.");
                    points.add(new LatLng(14.625220, 121.062400));// go straight
                    pointStrings.add("Turn to your right and walk straight.");
                    points.add(new LatLng(14.625477, 121.062323));// turn left please
                    pointStrings.add("Turn to your left and walk straight to P.E. hall.");
                    points.add(new LatLng(14.625336, 121.062034));//  now turn right and go straight
                    pointStrings.add("Turn to your left and walk straight near the P.E. hall.");
                    points.add(new LatLng(14.626053, 121.061656));// you are now on building 6
                    pointStrings.add("The entrance is here.");
                    break;
                case 7:
                    points.add(new LatLng(14.625309, 121.062613));//
                    pointStrings.add("Start here. Walk a few steps.");
                    points.add(new LatLng(14.625376, 121.062755));//walk straight
                    pointStrings.add("Turn to your left and walk straight.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625657, 121.062746));//outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 8:
                    points.add(new LatLng(14.625289, 121.062593));
                    pointStrings.add("Start here. Walk straight near the congregating area.");
                    points.add(new LatLng(14.625182, 121.062336));
                    pointStrings.add("The entrance is here.");
                    break;
                case 9:
                    points.add(new LatLng(14.625309, 121.062613));//
                    pointStrings.add("Start here. Take a few steps.");
                    points.add(new LatLng(14.625376, 121.062755));//walk straight
                    pointStrings.add("Turn to your left and walk straight.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your right and take a few steps.");
                    points.add(new LatLng(14.625657, 121.062746));//outside
                    pointStrings.add("Turn to your left and walk straight ahead.");
                    points.add(new LatLng(14.626312, 121.062395));
                    pointStrings.add("The entrance is here.");
                    break;
            }
        } else if (fromBuilding == 3) {
            switch (toBuilding) {
                case 1:
                    points.add(new LatLng(14.624933, 121.062744));
                    pointStrings.add("Start here. Walk pass through the building 2.");
                    points.add(new LatLng(14.625289, 121.062593));
                    pointStrings.add("Turn to your left and walk straight ahead near the study area.");
                    points.add(new LatLng(14.624853, 121.061643));
                    pointStrings.add("The entrance is here.");
                    break;
                case 2:
                    points.add(new LatLng(14.624835, 121.062748));
                    pointStrings.add("Start here. Walk straight ahead.");
                    points.add(new LatLng(14.625289, 121.062593));
                    pointStrings.add("The entrance is here.");
                    break;
                case 4:
                    points.add(new LatLng(14.624835, 121.062748));
                    pointStrings.add("Start here. Walk pass through the building 2.");
                    points.add(new LatLng(14.625289, 121.062593));
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625343, 121.062730));
                    pointStrings.add("The entrance is here.");
                    break;
                case 5:
                    points.add(new LatLng(14.624835, 121.062748));//
                    pointStrings.add("Start here. Walk pass through the building 2.");
                    points.add(new LatLng(14.625309, 121.062613));//
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625376, 121.062755));//walk straight
                    pointStrings.add("Turn to your left and walk straight.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your right and walk diagonally.");
                    points.add(new LatLng(14.625903, 121.062771));//outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 6:
                    points.add(new LatLng(14.624902, 121.062717));// outside
                    pointStrings.add("Start here. Walk a few steps.");
                    points.add(new LatLng(14.624867, 121.062561));// go straight
                    pointStrings.add("Turn to your right and walk straight to study area.");
                    points.add(new LatLng(14.625477, 121.062323));// turn left please
                    pointStrings.add("Turn to your left and walk straight to P.E. hall.");
                    points.add(new LatLng(14.625336, 121.062034));//  now turn right and go straight
                    pointStrings.add("Turn to your right and walk straight near the P.E. hall.");
                    points.add(new LatLng(14.626053, 121.061656));// you are now on building 6
                    pointStrings.add("The entrance is here.");
                    break;
                case 7:
                    points.add(new LatLng(14.624835, 121.062748));//
                    pointStrings.add("Start here. Walk pass through the building 2.");
                    points.add(new LatLng(14.625309, 121.062613));//
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625376, 121.062755));//walk straight
                    pointStrings.add("Turn to your left and walk straight.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625657, 121.062746));//outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 8:
                    points.add(new LatLng(14.624933, 121.062744));
                    pointStrings.add("Start here. Walk pass through the building 2.");
                    points.add(new LatLng(14.625289, 121.062593));
                    pointStrings.add("Turn to your left and walk straight to study area.");
                    points.add(new LatLng(14.625182, 121.062336));
                    pointStrings.add("The entrance is here.");
                    break;
                case 9:
                    points.add(new LatLng(14.624835, 121.062748));//
                    pointStrings.add("Start here. Walk pass through the building 2.");
                    points.add(new LatLng(14.625309, 121.062613));//
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625376, 121.062755));//walk straight
                    pointStrings.add("Turn to your left and walk straight.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625657, 121.062746));//outside
                    pointStrings.add("Turn to you left and walk straight ahead.");
                    points.add(new LatLng(14.626312, 121.062395));
                    pointStrings.add("The entrance is here.");
                    break;
            }
        } else if (fromBuilding == 4) {
            switch (toBuilding) {
                case 1:
                    points.add(new LatLng(14.625363, 121.062744));
                    //the app will draw a line starting from this point
                    pointStrings.add("Start here. Walk straight ahead near the study area.");
                    //and say this with TTS
                    points.add(new LatLng(14.624878, 121.061659));
                    //then draw another line to this point
                    pointStrings.add("The entrance is here.");
                    //and say this.
                    break;
                case 2:
                    points.add(new LatLng(14.625363, 121.062744));
                    //the app will draw a line starting from this point
                    pointStrings.add("Start here. Walk a few steps.");
                    //and say this with TTS
                    points.add(new LatLng(14.625300, 121.062595));
                    //then draw another line to this point
                    pointStrings.add("The entrance is here.");
                    //and say this.
                    break;
                case 3:
                    points.add(new LatLng(14.625363, 121.062744));
                    //the app will draw a line starting from this point
                    pointStrings.add("Start here. Walk a few steps.");
                    //and say this with TTS
                    points.add(new LatLng(14.625300, 121.062595));
                    //then draw another line to this point
                    pointStrings.add("Turn left and walk pass through the building 2.");
                    points.add(new LatLng(14.624868, 121.062770));
                    //then draw another line to this point
                    pointStrings.add("The entrance is here.");
                    //and say this.
                    break;
                case 5:
                    points.add(new LatLng(14.625363, 121.062744));//gooutside
                    pointStrings.add("Start here. Walk straight ahead.");
                    points.add(new LatLng(14.625667, 121.062652));
                    pointStrings.add("Turn to your right and walk diagonally.");
                    points.add(new LatLng(14.625903, 121.062771));
                    pointStrings.add("The entrance is here.");
                    break;
                case 6:
                    points.add(new LatLng(14.625363, 121.062744));// outside
                    pointStrings.add("Start here. Walk straight ahead to study area.");
                    points.add(new LatLng(14.625223, 121.062413)); // turn left
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625469, 121.062302));
                    pointStrings.add("Turn to your left and walk straight to P.E. hall");
                    points.add(new LatLng(14.625336, 121.062034));//  now turn right and go straight
                    pointStrings.add("Turn to your right and walk straight near the P.E. hall.");
                    points.add(new LatLng(14.626053, 121.061656));// you are now on building 6
                    pointStrings.add("The entrance is here.");
                    break;
                case 7:
                    points.add(new LatLng(14.625363, 121.062744));
                    pointStrings.add("Start here. Walk straight ahead.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625657, 121.062746));//outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 8:
                    points.add(new LatLng(14.625363, 121.062744));
                    pointStrings.add("Start here. Walk straight near the congregating area.");
                    points.add(new LatLng(14.625182, 121.062336));
                    pointStrings.add("The entrance is here.");
                    break;
                case 9:
                    points.add(new LatLng(14.625363, 121.062744));// outside
                    pointStrings.add("Start here. Walk straight ahead to study area.");
                    points.add(new LatLng(14.625223, 121.062413)); // turn left
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625469, 121.062302)); // sa may SA to
                    pointStrings.add("Turn to your left and walk straight to P.E. hall");
                    points.add(new LatLng(14.625343, 121.062034)); // left turn to PE hall
                    pointStrings.add("Turn to your right and walk straight near the P.E. hall.");
                    points.add(new LatLng(14.626057, 121.061653)); // sa may bldg6
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.626114, 121.061822)); // building 9 na
                    pointStrings.add("The entrance is here.");
                    break;
            }
        } else if (fromBuilding == 5) {
            switch (toBuilding) {
                case 1:
                    points.add(new LatLng(14.625903, 121.062779));
                    pointStrings.add("Start here. Turn to your right and walk diagonally.");
                    points.add(new LatLng(14.625674, 121.062751));
                    pointStrings.add("Turn to your right and walk straight near the congregating area.");
                    points.add(new LatLng(14.625481, 121.062323));
                    pointStrings.add("Turn to your left and walk a few steps at the study area.");
                    points.add(new LatLng(14.625209, 121.062408));
                    pointStrings.add("Turn to your right and walk straight near the study area.");
                    points.add(new LatLng(14.624947, 121.061834));
                    pointStrings.add("The entrance is here");
                    break;
                case 2:
                    points.add(new LatLng(14.625903, 121.062771));//outside
                    pointStrings.add("Start here. Turn to your right and walk diagonally.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your left and walk a few steps at the congregating area.");
                    points.add(new LatLng(14.625376, 121.062755));//walk straight
                    pointStrings.add("Turn to your right and walk straight near the congregating area.");
                    points.add(new LatLng(14.625309, 121.062613));//
                    pointStrings.add("The entrance is here");
                    break;
                case 3:
                    points.add(new LatLng(14.625903, 121.062771));//outside
                    pointStrings.add("Start here. Turn to your right and walk diagonally.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your left and walk a few steps at the congregating area.");
                    points.add(new LatLng(14.625376, 121.062755));//walk straight
                    pointStrings.add("Turn to your right and walk straight near the congregating area.");
                    points.add(new LatLng(14.625309, 121.062613));//
                    pointStrings.add("Turn to your left and walk pass through the building 2.");
                    points.add(new LatLng(14.624835, 121.062748));//
                    pointStrings.add("The entrance is here");
                    break;
                case 4:
                    points.add(new LatLng(14.625903, 121.062771));//outside
                    pointStrings.add("Start here. Turn to your right and walk diagonally.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your left and walk a few steps at the congregating area.");
                    points.add(new LatLng(14.625343, 121.062730));
                    pointStrings.add("The entrance is here.");
                    break;
                case 6:
                    points.add(new LatLng(14.626267, 121.062460));
                    pointStrings.add("Start here. Walk straight near the building 9.");
                    points.add(new LatLng(14.626002, 121.061861)); //end of building 9
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.626130, 121.061798)); //turn right
                    pointStrings.add("Turn to your left and walk a few steps.");
                    points.add(new LatLng(14.626113, 121.061745)); //
                    pointStrings.add("The entrance is here.");
                    break;
                case 7:
                    points.add(new LatLng(14.625903, 121.062771));//outside
                    pointStrings.add("Start here. Walk a few steps.");
                    points.add(new LatLng(14.625704, 121.062849));//its just here
                    pointStrings.add("The entrance is here.");
                    break;
                case 8:
                    points.add(new LatLng(14.625903, 121.062771));//outside
                    pointStrings.add("Start here. Turn to your right and walk diagonally.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your left and walk a few steps at the congregating area.");
                    points.add(new LatLng(14.625309, 121.062613));//
                    pointStrings.add("Turn to your right and walk straight near the congregating area.");
                    points.add(new LatLng(14.625178, 121.062328));//
                    pointStrings.add("The entrance is here.");
                    break;
                case 9:
                    points.add(new LatLng(14.626267, 121.062460));
                    pointStrings.add("Start here. Walk a few steps");
                    points.add(new LatLng(14.626227, 121.062330));
                    pointStrings.add("The entrance is here.");
                    break;
            }
        } else if (fromBuilding == 6) {
            switch (toBuilding) {
                case 1:
                    points.add(new LatLng(14.626053, 121.061656));// you are now on building 6
                    pointStrings.add("Start here. Walk straight near the P.E. hall.");
                    points.add(new LatLng(14.625336, 121.062034));//  now turn right and go straight
                    pointStrings.add("Turn to your left and walk straight near the study area.");
                    points.add(new LatLng(14.625477, 121.062323));// turn left please
                    pointStrings.add("Turn to your right and walk straight near the congregating area.");
                    points.add(new LatLng(14.625196, 121.062371));// go straight
                    pointStrings.add("Turn to your right and walk straight near the study area.");
                    points.add(new LatLng(14.624853, 121.061643));// outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 2:
                    points.add(new LatLng(14.626053, 121.061656));// you are now on building 6
                    pointStrings.add("Start here. Walk straight near the P.E. hall.");
                    points.add(new LatLng(14.625336, 121.062034));//  now turn right and go straight
                    pointStrings.add("Turn to your left and walk straight near the study area.");
                    points.add(new LatLng(14.625477, 121.062323));// turn left please
                    pointStrings.add("Turn to your right and walk straight near the congregating area.");
                    points.add(new LatLng(14.625220, 121.062400));// go straight
                    pointStrings.add("Turn to your left and walk a few steps.");
                    points.add(new LatLng(14.625311, 121.062601));// outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 3:
                    points.add(new LatLng(14.626053, 121.061656));// you are now on building 6
                    pointStrings.add("Start here. Walk straight near the P.E. hall.");
                    points.add(new LatLng(14.625336, 121.062034));//  now turn right and go straight
                    pointStrings.add("Turn to your left and walk straight near the study area.");
                    points.add(new LatLng(14.625477, 121.062323));// turn left please
                    pointStrings.add("Turn to your right and walk straight near the congregating area.");
                    points.add(new LatLng(14.625220, 121.062400));// go straight
                    pointStrings.add("Turn to your left and walk straight to building 2.");
                    points.add(new LatLng(14.625311, 121.062601));// outside
                    pointStrings.add("Turn to your right and walk straight near the building 2.");
                    points.add(new LatLng(14.624835, 121.062748));//
                    pointStrings.add("The entrance is here.");
                    break;
                case 4:
                    points.add(new LatLng(14.626053, 121.061656));// you are now on building 6
                    pointStrings.add("Start here. Walk straight near the P.E. hall.");
                    points.add(new LatLng(14.625336, 121.062034));//  now turn right and go straight
                    pointStrings.add("Turn to your left and walk straight near the study area.");
                    points.add(new LatLng(14.625477, 121.062323));// turn left please
                    pointStrings.add("Turn to your right and walk straight near the congregating area.");
                    points.add(new LatLng(14.625220, 121.062400));// go straight
                    pointStrings.add("Turn to you left and walk straight near the building 2.");
                    points.add(new LatLng(14.625376, 121.062764));// outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 5:
                    points.add(new LatLng(14.626078, 121.061681));// you are now on building 6
                    pointStrings.add("Start here. Walk a few steps to building 9.");
                    points.add(new LatLng(14.626122, 121.061817));//  now turn right and go straight
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.626008, 121.061860));// turn left please
                    pointStrings.add("Turn to your left and walk straight near the building 9.");
                    points.add(new LatLng(14.626280, 121.062450));// go straight
                    pointStrings.add("The entrance is here.");
                    break;
                case 7:
                    points.add(new LatLng(14.626078, 121.061681));// you are now on building 6
                    pointStrings.add("Start here. Walk a few steps to building 9.");
                    points.add(new LatLng(14.626122, 121.061817));//  now turn right and go straight
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.626008, 121.061860));// turn left please
                    pointStrings.add("Turn to your left and walk straight to building 5.");
                    points.add(new LatLng(14.626280, 121.062450));// go straight
                    pointStrings.add("Turn to your right and walk straight near the building 5.");
                    points.add(new LatLng(14.625684, 121.062767));// go straight
                    pointStrings.add("The entrance is here.");
                    break;
                case 8:
                    points.add(new LatLng(14.626053, 121.061656));// you are now on building 6
                    pointStrings.add("Start here. Walk straight near the P.E. hall.");
                    points.add(new LatLng(14.625336, 121.062034));//  now turn right and go straight
                    pointStrings.add("Turn to your left and walk straight near the study area.");
                    points.add(new LatLng(14.625477, 121.062323));// turn left please
                    pointStrings.add("Turn to your right and walk straight near the congregating area.");
                    points.add(new LatLng(14.625220, 121.062400));// go straight
                    pointStrings.add("Turn to you right and walk a few steps.");
                    points.add(new LatLng(14.625191, 121.062330));// outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 9:
                    points.add(new LatLng(14.626053, 121.061656));// you are now on building 6
                    pointStrings.add("Start here. Walk a few steps.");
                    points.add(new LatLng(14.626115, 121.061806));//  now turn right and go straight
                    pointStrings.add("The entrance is here.");
            }
        } else if (fromBuilding == 7) {
            switch (toBuilding) {
                case 1:
                    points.add(new LatLng(14.625601, 121.062664));
                    pointStrings.add("Start here. Walk a few steps to congregating area.");
                    points.add(new LatLng(14.625380, 121.062756));
                    pointStrings.add("Turn to your right and walk straight near the study area.");
                    points.add(new LatLng(14.624860, 121.061663));
                    pointStrings.add("The entrance is here.");
                    break;
                case 2:
                    points.add(new LatLng(14.625601, 121.062664));
                    pointStrings.add("Start here. Walk a few steps to congregating area.");
                    points.add(new LatLng(14.625380, 121.062756));
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625300, 121.062595));
                    pointStrings.add("The entrance is here.");
                    break;
                case 3:
                    points.add(new LatLng(14.625601, 121.062664));
                    pointStrings.add("Start here. Walk a few steps to congregating area.");
                    points.add(new LatLng(14.625380, 121.062756));
                    pointStrings.add("Turn to your right and walk a few steps to building 2.");
                    points.add(new LatLng(14.625300, 121.062595));
                    pointStrings.add("Turn to your left and walk pass through the building 2.");
                    points.add(new LatLng(14.624847, 121.062770));
                    pointStrings.add("The entrance is here.");
                    break;
                case 4:
                    points.add(new LatLng(14.625595, 121.062667));
                    pointStrings.add("Start here. Walk a few steps to congregating area.");
                    points.add(new LatLng(14.625400, 121.062778));
                    pointStrings.add("The entrance is here.");
                    break;
                case 5:
                    points.add(new LatLng(14.625697, 121.062837));
                    pointStrings.add("Start here. Walk a few steps.");
                    points.add(new LatLng(14.625905, 121.062780));
                    pointStrings.add("The entrance is here.");
                    break;
                case 6:
                    points.add(new LatLng(14.625595, 121.062667));
                    pointStrings.add("Start here. Walk straight to study area.");
                    points.add(new LatLng(14.625358, 121.062041));
                    pointStrings.add("Turn to your right and walk straight near the P.E. hall.");
                    points.add(new LatLng(14.626073, 121.061641));
                    pointStrings.add("The entrance is here.");
                    break;
                case 8:
                    points.add(new LatLng(14.625601, 121.062664));
                    pointStrings.add("Start here. Walk a few steps to congregating area.");
                    points.add(new LatLng(14.625380, 121.062756));
                    pointStrings.add("Turn to your right and walk straight near the congregating area.");
                    points.add(new LatLng(14.625179, 121.062326));
                    pointStrings.add("The entrance is here.");
                    break;
                case 9:
                    points.add(new LatLng(14.625676, 121.062780));
                    pointStrings.add("Start here. Walk straight near the building 5.");
                    points.add(new LatLng(14.626315, 121.062391));
                    pointStrings.add("The entrance is here.");
                    break;
            }
        } else if (fromBuilding == 8) {
            switch (toBuilding) {
                case 1:
                    points.add(new LatLng(14.625177, 121.062302));
                    //the app will draw a line starting from this point
                    pointStrings.add("Start here. Walk straight near the study area.");
                    //and say this with TTS
                    points.add(new LatLng(14.624855, 121.061652));
                    //then draw another line to this point
                    pointStrings.add("The entrance is here.");
                    //and say this.
                    break;
                case 2:
                    points.add(new LatLng(14.625177, 121.062302));
                    //the app will draw a line starting from this point
                    pointStrings.add("Start here. Walk straight near the congregating area.");
                    //and say this with TTS
                    points.add(new LatLng(14.625300, 121.062595));
                    //then draw another line to this point
                    pointStrings.add("The entrance is here.");
                    //and say this.
                    break;
                case 3:
                    points.add(new LatLng(14.625177, 121.062302));
                    //the app will draw a line starting from this point
                    pointStrings.add("Start here. Walk straight to building 2.");
                    //and say this with TTS
                    points.add(new LatLng(14.625300, 121.062595));
                    //then draw another line to this point
                    pointStrings.add("Turn to your right and walk pass through the building 2.");
                    points.add(new LatLng(14.624868, 121.062770));
                    //then draw another line to this point
                    pointStrings.add("The entrance is here.");
                    //and say this.
                    break;
                case 4:
                    points.add(new LatLng(14.625177, 121.062302));
                    pointStrings.add("Start here. Walk straight near the congregating area.");
                    points.add(new LatLng(14.625358, 121.062732));
                    pointStrings.add("The entrance is here.");
                    break;
                case 5:
                    points.add(new LatLng(14.625177, 121.062302));//gooutside
                    pointStrings.add("Start here. Walk straight near the congregating area.");
                    points.add(new LatLng(14.625356, 121.062722));
                    pointStrings.add("Turn to your left and walk straight.");
                    points.add(new LatLng(14.625667, 121.062652));
                    pointStrings.add("Turn to your right and walk diagonally.");
                    points.add(new LatLng(14.625903, 121.062771));
                    pointStrings.add("The entrance is here.");
                    break;
                case 6:
                    points.add(new LatLng(14.625177, 121.062302));// outside
                    pointStrings.add("Start here. Walk straight to study area.");
                    points.add(new LatLng(14.625440, 121.062252));// turn left please
                    pointStrings.add("Turn to your left here and walk straight to P.E. hall.");
                    points.add(new LatLng(14.625336, 121.062034));//  now turn right and go straight
                    pointStrings.add("Turn to your right and walk straight near the P.E. hall.");
                    points.add(new LatLng(14.626053, 121.061656));// you are now on building 6
                    pointStrings.add("The entrance is here.");
                    break;
                case 7:
                    points.add(new LatLng(14.625177, 121.062302));
                    //the app will draw a line starting from this point
                    pointStrings.add("Start here. Walk straight near the congregating area.");
                    //and say this with TTS
                    points.add(new LatLng(14.625376, 121.062755));//walk straight
                    pointStrings.add("Turn to your left and walk straight.");
                    points.add(new LatLng(14.625620, 121.062637));//go to congre
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.625657, 121.062746));//outside
                    pointStrings.add("The entrance is here.");
                    break;
                case 9:
                    points.add(new LatLng(14.625223, 121.062413)); // turn left
                    pointStrings.add("Start here. Walk straight to study area.");
                    points.add(new LatLng(14.625469, 121.062302)); // sa may SA to
                    pointStrings.add("Turn to your left here and walk straight to P.E. hall.");
                    points.add(new LatLng(14.625343, 121.062034)); // left turn to PE hall
                    pointStrings.add("Turn to your right and walk straight near the P.E. hall.");
                    points.add(new LatLng(14.626057, 121.061653)); // sa may bldg6
                    pointStrings.add("Turn to your right and walk a few steps.");
                    points.add(new LatLng(14.626114, 121.061822)); // building 9 na
                    pointStrings.add("The entrance is here.");
                    break;
            }
        } else if (fromBuilding == 9) {
            switch (toBuilding) {
                case 1:
                    points.add(new LatLng(14.62636, 121.0623));//
                    pointStrings.add("Start here. Walk straight to congregating area.");
                    points.add(new LatLng(14.62569, 121.06265));//
                    pointStrings.add("Turn to your right and walk straight to study area.");
                    points.add(new LatLng(14.625493, 121.0622));//
                    pointStrings.add("Turn to your left and walk a few steps to building 8.");
                    points.add(new LatLng(14.625261, 121.06230));//
                    pointStrings.add("Turn to your right and walk straight near the study area.");
                    points.add(new LatLng(14.624868, 121.061660));
                    pointStrings.add("The entrance is here.");
                    break;
                case 2:
                    points.add(new LatLng(14.62636, 121.0623));//
                    pointStrings.add("Start here. Walk straight to congregating area.");
                    points.add(new LatLng(14.62569, 121.06265));//
                    pointStrings.add("Turn to your right and walk straight to study area.");
                    points.add(new LatLng(14.625493, 121.0622));//
                    pointStrings.add("Turn to your left and walk a few steps to building 8.");
                    points.add(new LatLng(14.625261, 121.06230));//
                    pointStrings.add("Turn to your left and walk a few steps.");
                    points.add(new LatLng(14.62530, 121.0626));
                    pointStrings.add("The entrance is here.");
                    break;
                case 3:
                    points.add(new LatLng(14.62636, 121.0623));//
                    pointStrings.add("Start here. Walk straight to congregating area.");
                    points.add(new LatLng(14.62569, 121.06265));//
                    pointStrings.add("Turn to your right and walk straight to study area.");
                    points.add(new LatLng(14.625493, 121.0622));//
                    pointStrings.add("Turn to your left and walk a few steps to building 8.");
                    points.add(new LatLng(14.625261, 121.06230));//
                    pointStrings.add("Turn to your left and walk a few steps.");
                    points.add(new LatLng(14.62530, 121.0626));
                    pointStrings.add("Turn to your right and walk pass through the building 2.");
                    points.add(new LatLng(14.624836, 121.062774));
                    pointStrings.add("The entrance is here.");
                    break;
                case 4:
                    points.add(new LatLng(14.626114, 121.061822));
                    pointStrings.add("Start here. Walk a few steps to P.E. hall.");
                    points.add(new LatLng(14.626057, 121.061653));
                    pointStrings.add("Turn to your left and walk straight near the P.E. hall.");
                    points.add(new LatLng(14.625343, 121.062034));
                    pointStrings.add("Turn to your left and walk straight near the study area.");
                    points.add(new LatLng(14.625469, 121.062302));
                    pointStrings.add("Turn to your right and walk straight to building 8.");
                    points.add(new LatLng(14.625223, 121.062413));
                    pointStrings.add("Turn to your left and walk straight near the building 2.");
                    points.add(new LatLng(14.625363, 121.062744));
                    pointStrings.add("The entrance is here.");
                    break;
                case 5:
                    points.add(new LatLng(14.626227, 121.062330));
                    pointStrings.add("Start here. Walk a few steps.");
                    points.add(new LatLng(14.626267, 121.062460));
                    pointStrings.add("The entrance is here.");
                    break;
                case 6:
                    points.add(new LatLng(14.626115, 121.061806));
                    pointStrings.add("Start here. Walk a few steps.");
                    points.add(new LatLng(14.626053, 121.061656));
                    pointStrings.add("The entrance is here.");
                    break;
                case 7:
                    points.add(new LatLng(14.62636, 121.0623));//
                    pointStrings.add("Start here. Walk straight near the building 5.");
                    points.add(new LatLng(14.625660, 121.062778));//
                    pointStrings.add("The entrance is here.");
                    break;
                case 8:
                    points.add(new LatLng(14.62636, 121.0623));//
                    pointStrings.add("Start here. Walk straight to congregating area.");
                    points.add(new LatLng(14.62569, 121.06265));//
                    pointStrings.add("Turn to your right and walk straight to study area.");
                    points.add(new LatLng(14.625493, 121.0622));//
                    pointStrings.add("Turn to your left and walk a few steps near the study area.");
                    points.add(new LatLng(14.625179, 121.062334));
                    pointStrings.add("The entrance is here.");
                    break;
            }
        }

        mMap.clear();

        pointCounter = 0;

        new CountDownTimer(points.size() * 3500 + 1500, 500) {
            public void onTick(long millisUntilFinished) {
                if (pointCounter < points.size()) {
                    lines.add(points.get(pointCounter));
                    polyline = mMap.addPolyline(lines);

                    if (!mTts.isSpeaking()) {
                        mMap.addMarker(new MarkerOptions()
                                .position(points.get(pointCounter))
                                .title(pointStrings.get(pointCounter)));

                        mMap.animateCamera(CameraUpdateFactory.newLatLng(points.get(pointCounter)), 1500, null);

                        speak(pointStrings.get(pointCounter));
                        pointCounter++;
                    }
                }
            }

            @Override
            public void onFinish() {
                // NavigationFragment.navigationStart.setEnabled(true);
                speak("You are now at building " + toBuilding + ".");
                goToBuilding(toBuilding);
            }
        }.start();


    }

    public static void goToBuilding(int buildingNo) {
        if (marker != null)
            if (marker.isVisible()) marker.remove();

        marker = mMap.addMarker(new MarkerOptions().position(buildingCenters[buildingNo - 1]).title("You are now at building " + buildingNo + "."));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(buildingCenters[buildingNo - 1], 18));
    }

    public static void speak(String text) {
        if (mTts != null) {
            mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
