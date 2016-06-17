package com.roberterrera.glutenfreeplaces;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private String[] locationPerms = {"android.permission.ACCESS_COURSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private final int locationRequestCode = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Gluten-Free Locations");

        // Construct the data source
        ArrayList<Place> arrayOfUsers = new ArrayList<>();

        // Create the adapter to convert the array to views
        ListItemAdapter adapter = new ListItemAdapter(this, arrayOfUsers);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listview_places_main);
        listView.setAdapter(adapter);

        // Load a list of places near the user's location.
        loadList();

    }

    private void loadList(){
        requestLocationPermissions();

        // Loop through arraylist and add database items to it.

    }

    private void requestLocationPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(locationPerms, locationRequestCode);
        } else if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, locationPerms, locationRequestCode);
        }
    }

    // Check permissions
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (permsRequestCode) {
            case locationRequestCode:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {}
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.menu_map) {
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                requestLocationPermissions();
                // Open the map view
                Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapIntent);
            } else {
                Toast.makeText(MainActivity.this, "Map unavailable without an internet connection.", Toast.LENGTH_SHORT).show();
            }
        }
        if (id == R.id.menu_search) {
            // TODO: Add menu method for filtering search.
            Toast.makeText(MainActivity.this, "You tapped the search button", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
