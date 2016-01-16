package alvarez.oscar.crimedatasf.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import alvarez.oscar.crimedatasf.R;
import alvarez.oscar.crimedatasf.map.MapUtil;
import alvarez.oscar.crimedatasf.models.District;
import alvarez.oscar.crimedatasf.models.Incident;
import alvarez.oscar.crimedatasf.sync.SyncCrimeData;
import alvarez.oscar.crimedatasf.util.Util;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
    Response.Listener<Incident[]>,
    Response.ErrorListener{

    private MapUtil mapUtil;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ArrayList<District> districts;
    private MapsActivity context;
    private String currentDistrict;

    Response.Listener<District[]> districtListener = new Response.Listener<District[]>() {
        @Override
        public void onResponse(District[] response) {
            Menu menu = navigationView.getMenu();
            districts = new ArrayList<>(Arrays.asList(response));
            // 0  have most incidents
            Collections.sort(districts, Util.getDistrictComparator());
            for (int i=0; i < response.length ; i++) {
                menu.add(districts.get(i).getPddistrict());
                menu.getItem(i).setIcon(
                    Util.getTintDrawable(context, R.drawable.ic_room_black_24dp,
                                         Util.getColorByPriority(i)));
            }

            navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        mapUtil.clearMap();
                        Util.setCounterRequest(0);
                        Util.setLastRequestFromSF(false);
                        SyncCrimeData.getIncidentsByDistrict(context, context,
                                                             context, item.toString(), Util.getCounterRequest());
                        currentDistrict = item.toString();
                        drawerLayout = ((DrawerLayout) findViewById(R.id.parent_layout));
                        drawerLayout.closeDrawers();
                        return false;
                    }
                });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        context = this;
        Util.setCounterRequest(0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initDrawer();
        SyncCrimeData.getPoliceDepartments(this, districtListener, this);
    }

    private void initDrawer() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapUtil = new MapUtil(googleMap, this);
        //Util.getFloatingTimestampFormat(System.currentTimeMillis()/1000);
        Util.setLastRequestFromSF(true);
        SyncCrimeData.getIncidentsSF(this, this, this, Util.getCounterRequest());
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //TODO: Set connection error view
        Log.d("PFF", error.getMessage());
    }

    @Override
    public void onResponse(Incident[] response) {
        for (int i = 0; i < response.length ; i++) {
            Incident incident = response[i];
            mapUtil.addItemWithPriority(
                    new LatLng(incident.getLocation().getCoordinates().get(1),
                            incident.getLocation().getCoordinates().get(0))
            , getPriorityByDistrict(incident.getPddistrict()), incident.getCategory(), incident.getDescript());
        }
        Snackbar.make(findViewById(R.id.parent_layout), R.string.snackbar_message, Snackbar.LENGTH_LONG).show();
    }

    private int getPriorityByDistrict(String pddistrict) {
        for (int i = 0 ; i < districts.size(); i++) {
            if (districts.get(i).getPddistrict().equals(pddistrict)) {
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
                SyncCrimeData.getIncidentsByDistrict(context, context,
                                                     context, currentDistrict, Util.getCounterRequest());
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
