package com.roberterrera.glutenfreeplaces;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.roberterrera.glutenfreeplaces.model.FoursquareResponse;
import com.roberterrera.glutenfreeplaces.model.FoursquareResults;
import com.roberterrera.glutenfreeplaces.model.Venues;
import com.roberterrera.glutenfreeplaces.service.FoursquareAPIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private String[] locationPerms = {"android.permission.ACCESS_COURSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private final int locationRequestCode = 200;
    private String provider;
    public String name, icon;
    public double distance;
    private double mLatitude, mLongitude;
    public ArrayList<Venues> venuesList;
    private NetworkInfo networkInfo;
    private Location mLastLocation;
    private LocationManager locationManager;
    private GoogleApiClient mGoogleApiClient;
    public ListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Gluten-Free Places");

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient
                    .Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        getUserLocation();

        // Load a list of places near the user's location.
        loadList();
    }

    private void loadList() {
        requestLocationPermissions();

        // Populate the data into the template view using the data object
        String latlon = mLatitude + "," + mLongitude;


        FoursquareAPIService.Factory.getInstance().loadVenues(
                latlon, "gluten-free", "BDDBUFQEBDW1YCOZZRDXBZMXPGM3YA3GGAMUOHLJUAQ00FOM",
                "NP52CW1YEULLQBSCUYIAZO00VOH2RWDLLY03W10HA0X4U1P5", 20160617, "foursquare")

//                "latlon, "gluten-free", @value/foursquare_api_client", "@value/foursquare_api_secret", 20160617, "foursquare")
                .enqueue(new Callback<FoursquareResults>() {
                    @Override
                    public void onResponse(Call<FoursquareResults> call, Response<FoursquareResults> response) {
                        venuesList = response.body().getResponse().getVenuesList();
                        // Create the adapter to convert the array to views
                        adapter = new ListItemAdapter(MainActivity.this, venuesList);

                        // Attach the adapter to a ListView
                        ListView listView = (ListView) findViewById(R.id.listview_places_main);

                        listView.setAdapter(adapter);

                        // Loop through the results and add their locations to the list.
                        for (int j = 0; j <= venuesList.size() - 1; j++) {

                            name = venuesList.get(j).getName();
                            distance = venuesList.get(j).getLocation().getDistance();
//                            distance = String.valueOf(Math.round(getDistance*.01)/10.0)+" miles away";

//                            if (venuesList.get(j).getCategories() != null) {
//                                icon = venuesList.get(j).getCategories().getIcon().getPrefix()
//                                        + venuesList.get(j).getCategories().getIcon().getSuffix();
//                            }

                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<FoursquareResults> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Could not load list.", Toast.LENGTH_SHORT).show();
                        Log.d("ONFAILURE", t.toString());
                    }
                });

    }

    public void getUserLocation() {

        if (networkInfo != null && networkInfo.isConnected()) {

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            provider = locationManager.getBestProvider(new Criteria(), true);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                return;
            }

            String locationProvider = LocationManager.NETWORK_PROVIDER;
            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
            mLatitude = lastKnownLocation.getLatitude();
            mLongitude = lastKnownLocation.getLongitude();
        }
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
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                }
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
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
//                Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
//                startActivity(mapIntent);
                Toast.makeText(MainActivity.this, "Tapped map button", Toast.LENGTH_SHORT).show();
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

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(locationPerms, 200);
            }
            return;
        }
        locationManager.removeUpdates(this);
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(locationPerms, 200);
            }
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (networkInfo != null && networkInfo.isConnected()) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(locationPerms, 200);
                }
                return;
            }
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                mLatitude = mLastLocation.getLatitude();
                mLongitude = mLastLocation.getLongitude();
            }
        }
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
