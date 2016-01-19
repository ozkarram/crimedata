package com.globant.crimedatasf.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.globant.crimedatasf.map.MapUtil;
import com.globant.crimedatasf.models.District;
import com.globant.crimedatasf.models.Incident;
import com.globant.crimedatasf.sync.SyncCrimeData;
import com.globant.crimedatasf.util.Util;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.Arrays;

import alvarez.oscar.crimedatasf.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
    Response.Listener<Incident[]>,
    Response.ErrorListener {

    private MapUtil mapUtil;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private District[] districts;
    private Incident[] incidents;
    private Bundle savedInstance;
    private MapsActivity context;
    private String currentDistrict;
    private ActionBar supportActionBar;

    Response.Listener<District[]> districtListener = new Response.Listener<District[]>() {
        @Override
        public void onResponse(District[] response) {
            Menu menu = navigationView.getMenu();
            districts = response;
            // 0  have most incidents
            Arrays.sort(districts, Util.getDistrictComparator());
            fillMenu(response, menu);
            navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        supportActionBar.setTitle(         getResources().getString(R.string.app_name)
                                                  + ": " + item.getTitle());
                        mapUtil.clearMap();
                        Util.setCounterRequest(0);

                        if (item.toString()
                            .equals(getResources().getString(R.string.all_districts))) {
                            Util.setLastRequestFromSF(true);
                            SyncCrimeData.getIncidentsSF(context, context,
                                                         context, Util.getCounterRequest());
                        } else {
                            Util.setLastRequestFromSF(false);
                            SyncCrimeData.getIncidentsByDistrict(context, context,
                                                                 context, item.toString(),
                                                                 Util.getCounterRequest());
                        }

                        currentDistrict = item.toString();
                        drawerLayout.closeDrawers();
                        return false;
                    }
                });
        }
    };

    private void fillMenu(District[] response, Menu menu) {
        menu.add(R.string.all_districts);
        menu.getItem(0).setIcon(
            Util.getTintDrawable(context, R.drawable.ic_lens_black_24dp,
                                 Util.getColorByPriority(10)));
        for (int i = 0; i < response.length; i++) {
            menu.add(districts[i].getPddistrict());
            menu.getItem(i + 1).setIcon(
                Util.getTintDrawable(context, R.drawable.ic_room_black_24dp,
                                     Util.getColorByPriority(i)));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstance = savedInstanceState;
        setContentView(R.layout.activity_maps);
        context = this;
        Util.setCounterRequest(0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initDrawer();
        if (savedInstanceState != null && savedInstanceState.containsKey("districts")) {
            District[] arr = ((District[]) savedInstanceState.getParcelableArray("districts"));
            districtListener.onResponse(arr);
        } else {
            SyncCrimeData.getPoliceDepartments(this, districtListener, this);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArray("incidents", incidents);
        outState.putParcelableArray("districts", districts);

        super.onSaveInstanceState(outState);
    }

    private void initDrawer() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = ((DrawerLayout) findViewById(R.id.parent_layout));

        supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeButtonEnabled(true);

        }
    }


    /**
     * Manipulates the map once available. This callback is triggered when the map is ready to be
     * used. This is where we can add markers or lines, add listeners or move the camera. In this
     * case, we just add a marker near Sydney, Australia. If Google Play services is not installed
     * on the device, the user will be prompted to install it inside the SupportMapFragment. This
     * method will only be triggered once the user has installed Google Play services and returned
     * to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapUtil = new MapUtil(googleMap, this);
        Util.setLastRequestFromSF(true);
        if (savedInstance != null && savedInstance.containsKey("incidents")) {
            Incident[] arr = ((Incident[]) savedInstance.getParcelableArray("incidents"));
            onResponse(arr);
        } else {
            SyncCrimeData.getIncidentsSF(this, this, this, Util.getCounterRequest());
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, R.string.message_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Incident[] response) {
        incidents = response;
        for (int i = 0; i < response.length; i++) {
            Incident incident = response[i];
            mapUtil.addItemWithPriority(
                new LatLng(incident.getLocation().getCoordinates().get(1),
                           incident.getLocation().getCoordinates().get(0))
                , getPriorityByDistrict(incident.getPddistrict()),
                incident.getCategory(), incident.getDescript());
        }
        Snackbar.make(findViewById(R.id.parent_layout), R.string.snackbar_message,
                      Snackbar.LENGTH_LONG).show();
    }

    private int getPriorityByDistrict(String pddistrict) {
        for (int i = 0; i < districts.length; i++) {
            if (districts[i].getPddistrict().equals(pddistrict)) {
                return i;
            }
        }
        return Util.DEFAULT_COLOR;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.load_data) {
            Util.setCounterRequest(Util.getCounterRequest() + 1);
            if (Util.getLastRequestFromSF()) {
                SyncCrimeData.getIncidentsSF(this, this, this, Util.getCounterRequest());
            } else {
                SyncCrimeData.getIncidentsByDistrict(context, context, context,
                                                     currentDistrict, Util.getCounterRequest());
            }
            return true;
        }
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
        return super.onOptionsItemSelected(item);
    }
}
