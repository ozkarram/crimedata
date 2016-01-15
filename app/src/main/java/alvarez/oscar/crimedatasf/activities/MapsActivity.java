package alvarez.oscar.crimedatasf.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import alvarez.oscar.crimedatasf.R;
import alvarez.oscar.crimedatasf.map.MapUtil;
import alvarez.oscar.crimedatasf.models.Incident;
import alvarez.oscar.crimedatasf.sync.SyncCrimeData;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
    Response.Listener<Incident[]>,
    Response.ErrorListener{

    private GoogleMap mMap;
    private MapUtil mapUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initDrawer();
    }

    private void initDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        mMap = googleMap;
        mapUtil = new MapUtil(mMap, this);
        //Util.getFloatingTimestampFormat(System.currentTimeMillis()/1000);
        SyncCrimeData.getIncidentsSF(this, this, this, 0);
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
            , 5, "Example", "Description");
        }
        Snackbar.make(findViewById(R.id.parent_layout), "50 Elements Loaded", Snackbar.LENGTH_LONG).show();

    }
}
