package com.example.alfhan.reminder;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alfhan.reminder.Popup.ShowPopup;
import com.example.alfhan.reminder.Position.Juanda;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;

import butterknife.BindView;

public class MainActivity extends ShowPopup
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks,OnMapReadyCallback , GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    public GoogleApiClient.Builder googleApiClient;
    private static final int REQUEST_LOCATION_PERMISSION_CODE = 101;
    Dialog myDialog;

//    @BindView(R.id.input_search) AutoCompleteTextView Input_Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION_CODE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final AutoCompleteTextView InputSearch = (AutoCompleteTextView) findViewById(R.id.input_search);
        final String[] countries = getResources().getStringArray(R.array.countries_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        InputSearch.setAdapter(adapter);




        InputSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                hidekey();
                String kmtjakartakota = "KMT | Juanda";
                String kmtjayakarta = "KMT | Jayakarta";
                String kmtmanggabesar = "KMT | Mangga Besar";
                String kmtsawahbesar = "KMT | Sawah Besar";
                String kmtjuanda = "KMT | Juanda";
                String kmtgondangdia = "KMT | Gondangdia";
                String kmtcikini = "KMT | Cikini";
                String kmtmanggarai = "KMT | Manggarai";
                String kmttebet = "KMT | Tebet";
                String kmtcawang = "KMT | Cawang";
                String kmtdurenkalibata = "KMT | Duren Kalibata";
                String kmtpasarminggubaru = "KMT | Pasar Minggu Baru";
                String kmtpasarminggu = "KMT | Pasar Minggu";
                String kmttanjungbarat = "KMT | Tanjung Barat";
                String kmtlentengagung = "KMT | Lenteng Agung";
                String kmtuniversitaspancasila = "KMT | Universitas Pancasila";
                String kmtuniversitasindonesia = "KMT | Universitas Indonesia";
                String kmtpondokcina = "KMT | Pondok Cina";
                String kmtdepokbaru = "KMT | Depok Baru";
                String kmtdepok = "KMT | Depok";
                String kmtcitayam = "KMT | Citayam";
                String kmtbojonggede = "KMT | Bojong Gede";
                String kmtcilebut = "KMT | Cilebut";
                String kmtbogor = "KMT | Bogor";


                String bsypasarbaru = "BSY | Pasar Baru";
                String bsyjuanda = "BSY | Juanda";
                String bsypecenongan = "BSY | Pecenongan";
                String bsyharmoni = "BSY | Harmoni Central";
                String bsyrssumberwaras = "BSY | RS Sumber Waras";
                String bsygrogol1 = "BSY | Grogol 1";
                String bsyjelembar = "BSY | Jelembar";
                String bsyindosiar = "BSY | Indosiar";
                String bsytamankota = "BSY | Taman Kota";
                String bsyjembatangantung = "BSY | Jembatan Gantung";
                String bsydispenda = "BSY | Dispenda";
                String bsyjembatanbaru = "BSY | Jembatan Baru";
                String bsyrawabuaya = "BSY | Rawa Buaya";
                String bsysumurbor = "BSY | Sumur Bor";
                String bsypesakih = "BSY | Pesakih";
                String bsykalideres = "BSY | Kalideres";

                String mrtsawahbesar = "MRT | Sawah Besar";
                String mrtmonas = "MRT | Monas";
                String mrtharmoni = "MRT | Harmoni";
                String mrtmanggabesar = "MRT | Mangga Besar";
                String mrtglodok = "MRT | Glodok";
                String mrtkota = "MRT | Kota";
                String mrtkampungbandan = "MRT | Kampung Bandan";


                String text = InputSearch.getText().toString();
                if (text.equals(kmtjakartakota)) {
                    mMap.clear();
                    KMTjakartakota();
                }
                if (text.equals(kmtjayakarta)) {
                    mMap.clear();
                    KMTjayakarta();
                }
                if (text.equals(kmtmanggabesar)) {
                    mMap.clear();
                    KMTmanggabesar();
                }
                if (text.equals(kmtsawahbesar)) {
                    mMap.clear();
                    KMTsawahbesar();
                }
                if (text.equals(kmtjuanda)) {
                    mMap.clear();
                    KMTjuanda();
                }
                if (text.equals(kmtgondangdia)) {
                    mMap.clear();
                    KMTgondangdia();
                }
                if (text.equals(kmtcikini)) {
                    mMap.clear();
                    KMTcikini();
                }
                if (text.equals(kmtmanggarai)) {
                    mMap.clear();
                    KMTmanggarai();
                }
                if (text.equals(kmttebet)) {
                    mMap.clear();
                    KMTtebet();
                }
                if (text.equals(kmtcawang)) {
                    mMap.clear();
                    KMTcawang();
                }
                if (text.equals(kmtdurenkalibata)) {
                    mMap.clear();
                    KMTdurenkalibata();
                }
                if (text.equals(kmtpasarminggubaru)) {
                    mMap.clear();
                    KMTpasarminggubaru();
                }
                if (text.equals(kmtpasarminggu)) {
                    mMap.clear();
                    KMTpasarminggu();
                }
                if (text.equals(kmttanjungbarat)) {
                    mMap.clear();
                    KMTtanjungbarat();
                }
                if (text.equals(kmtlentengagung)) {
                    mMap.clear();
                    KMTlentengagung();
                }
                if (text.equals(kmtuniversitaspancasila)) {
                    mMap.clear();
                    KMTuniversitaspancasila();
                }
                if (text.equals(kmtuniversitasindonesia)) {
                    mMap.clear();
                    KMTuniveritasindonesia();
                }
                if (text.equals(kmtpondokcina)) {
                    mMap.clear();
                    KMTpondokcina();
                }
                if (text.equals(kmtdepokbaru)) {
                    mMap.clear();
                    KMTdepokbaru();
                }
                if (text.equals(kmtdepok)) {
                    mMap.clear();
                    KMTdepok();
                }
                if (text.equals(kmtcitayam)) {
                    mMap.clear();
                    KMTcitayam();
                }
                if (text.equals(kmtbojonggede)) {
                    mMap.clear();
                    KMTbojonggede();
                }
                if (text.equals(kmtcilebut)) {
                    mMap.clear();
                    KMTcilebut();
                }
                if (text.equals(kmtbogor)) {
                    mMap.clear();
                    KMTbogor();
                    //Toast.makeText(MainActivity.this, "Tester", Toast.LENGTH_LONG).show();
                }


                if (text.equals(bsypasarbaru)) {
                    mMap.clear();
                    BSYpasarbaru();
                }
                if (text.equals(bsyjuanda)) {
                    mMap.clear();
                    BSYjuanda();
                }
                if (text.equals(bsypecenongan)) {
                    mMap.clear();
                    BSYpecenongan();
                }
                if (text.equals(bsyharmoni)) {
                    mMap.clear();
                    BSYharmonicentral();
                }
                if (text.equals(bsyrssumberwaras)) {
                    mMap.clear();
                    BSYrssumberwaras();
                }
                if (text.equals(bsygrogol1)) {
                    mMap.clear();
                    BSYgrogol1();
                }
                if (text.equals(bsyjelembar)) {
                    mMap.clear();
                    BSYjelambar();
                }
                if (text.equals(bsyindosiar)) {
                    mMap.clear();
                    BSYindosiar();
                }
                if (text.equals(bsytamankota)) {
                    mMap.clear();
                    BSYtamankota();
                }
                if (text.equals(bsyjembatangantung)) {
                    mMap.clear();
                    BSYjembangantung();
                }
                if (text.equals(bsydispenda)) {
                    mMap.clear();
                    BSYdispenda();
                }
                if (text.equals(bsyjembatanbaru)) {
                    mMap.clear();
                    BSYjembatanbaru();
                }
                if (text.equals(bsyrawabuaya)) {
                    mMap.clear();
                    BSYrawabuaya();
                }
                if (text.equals(bsysumurbor)) {
                    mMap.clear();
                    BSYsumurbor();
                }
                if (text.equals(bsypesakih)) {
                    mMap.clear();
                    BSYpesakih();
                }
                if (text.equals(bsykalideres)) {
                    mMap.clear();
                    BSYkalideres();
                }
// MRT ---------------------------------------------------------------------------------------------
                if (text.equals(mrtglodok)) {
                    mMap.clear();
                    MRTglodok();
                }
                if (text.equals(mrtharmoni)) {
                    mMap.clear();
                    MRTharmoni();
                }
                if (text.equals(mrtkampungbandan)) {
                    mMap.clear();
                    MRTKampungBandan();
                }
                if (text.equals(mrtkota)) {
                    mMap.clear();
                    MRTkota();
                }
                if (text.equals(mrtmanggabesar)) {
                    mMap.clear();
                    MRTmanggabesar();
                }
                if (text.equals(mrtmonas)) {
                    mMap.clear();
                    MRTmonas();
                }
                if (text.equals(mrtsawahbesar)) {
                    mMap.clear();
                    MRTSawahBesar();
                }
            }

        });

    }

    public void hidekey() {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    public void KMTjakartakota() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.137394, 106.814689))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.137394, 106.814689))
                .title("Stasiun Jakarta Kota")
        );
    }
    public void KMTjayakarta() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.141336, 106.823069))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.141336, 106.823069))
                .title("Stasiun Jayakarta")
        );
    }
    public void KMTmanggabesar() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.149823, 106.826976))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.149823, 106.826976))
                .title("Stasiun Mangga Besar")
        );
    }
    public void KMTsawahbesar() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.160670, 106.827625))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.160670, 106.827625))
                .title("Stasiun Sawah Besar")
        );
    }

    private void KMTjuanda() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.166709, 106.830474))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.166709, 106.830474))
                .title("Stasiun Juanda")
        );
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                JuandaInfo();
            }
        });

    }

    public void KMTgondangdia() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.185946, 106.832584))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.185946, 106.832584))
                .title("Stasiun Gondangdia")
        );
    }

    public void KMTcikini() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.198519, 106.841264))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.198519, 106.841264))
                .title("Stasiun Cikini")
        );
    }

    public void KMTmanggarai() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.209849, 106.850216))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.209849, 106.850216))
                .title("Stasiun Manggarai")
        );
    }

    public void KMTtebet() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.226058, 106.858285))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.226058, 106.858285))
                .title("Stasiun Tebet")
        );
    }

    public void KMTcawang() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.242665, 106.858810))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.242665, 106.858810))
                .title("Stasiun Cawang")
        );
    }

    public void KMTdurenkalibata() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.255350, 106.855016))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.255350, 106.855016))
                .title("Stasiun Duren Kalibata")
        );
    }

    public void KMTpasarminggubaru() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.262744, 106.851860))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.262744, 106.851860))
                .title("Stasiun Pasar Minggu Baru")
        );
    }

    public void KMTpasarminggu() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.284254, 106.844557))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.284254, 106.844557))
                .title("Stasiun Pasar Minggu")
        );
    }

    public void KMTtanjungbarat() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.307807, 106.838849))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.307807, 106.838849))
                .title("Stasiun Tanjung Barat")
        );
    }

    public void KMTlentengagung() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.330665, 106.834978))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.330665, 106.834978))
                .title("Stasiun Lenteng Agung")
        );
    }

    public void KMTuniversitaspancasila() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.338946, 106.834430))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.338946, 106.834430))
                .title("Stasiun Universitas Pancasila")
        );
    }

    public void KMTuniveritasindonesia() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.360757, 106.831746))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.360757, 106.831746))
                .title("Stasiun Universitas Indonesia")
        );
    }

    public void KMTpondokcina() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.369037, 106.832209))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.171280, 106.858290))
                .title("Stasiun Pondok Cina")
        );
    }

    public void KMTdepokbaru() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.391131, 106.821702))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.171280, 106.858290))
                .title("Stasiun Depok Baru")
        );
    }

    public void KMTdepok() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.404941, 106.817254))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.404941, 106.817254))
                .title("Stasiun Depok")
        );
    }

    public void KMTcitayam() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.448795, 106.802460))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.448795, 106.802460))
                .title("Stasiun Citayam")
        );
    }

    public void KMTbojonggede() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.493246, 106.794891))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.493246, 106.794891))
                .title("Stasiun Bojong Gede")
        );
    }

    public void KMTcilebut() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-6.530536, 106.800569))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.530536, 106.800569))
                .title("Stasiun Cilebut")
        );
    }

    public void KMTbogor() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Stasiun Bogor")
        );
    }

//    BUSWAY GAN -----------------------------------------------------------------------------------

    public void BSYpasarbaru() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Pasar Baru")
        );
    }
    public void BSYjuanda() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Juanda")
        );
    }
    public void BSYpecenongan() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Pecenongan")
        );
    }
    public void BSYharmonicentral() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Harmoni Central")
        );
    }
    public void BSYrssumberwaras() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte RS. Sumber Waras")
        );
    }
    public void BSYgrogol1() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Grogol 1")
        );
    }
    public void BSYjelambar() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Jelambar")
        );
    }
    public void BSYindosiar() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Indosiar")
        );
    }
    public void BSYtamankota() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Taman Kota")
        );
    }
    public void BSYjembangantung() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Jembatan Gantung")
        );
    }
    public void BSYdispenda() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Dispenda")
        );
    }
    public void BSYjembatanbaru() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Jembatan Baru")
        );
    }
    public void BSYrawabuaya() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Rawa Buaya")
        );
    }
    public void BSYsumurbor() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Sumur Bor")
        );
    }
    public void BSYpesakih() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Pesakih")
        );
    }
    public void BSYkalideres() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Kalideres")
        );
    }
//MRT ----------------------------------------------------------------------------------------------

    public void MRTglodok() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Kalideres")
        );
    }
    public void MRTharmoni() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Kalideres")
        );
    }
    public void MRTKampungBandan() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Kalideres")
        );
    }
    public void MRTkota() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Kalideres")
        );
    }
    public void MRTmanggabesar() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Kalideres")
        );
    }
    public void MRTmonas() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Kalideres")
        );
    }
    public void MRTSawahBesar() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(6.595452, 106.790436))
                .zoom(15)
                .bearing(0)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.595452, 106.790436))
                .title("Halte Kalideres")
        );
    }



}