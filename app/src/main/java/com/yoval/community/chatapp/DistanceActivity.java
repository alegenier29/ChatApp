package com.yoval.community.chatapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.nearby.messages.Distance;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.yoval.community.localisation.CustomizedLocationListener;
import com.yoval.community.model.ChatMessage;

public class DistanceActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int SIGN_IN_REQUEST_CODE = 10;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        if (googleServicesAvalaible())
        {
            //Intent intent = new Intent(this,DistanceActivity.class);
            //startActivity(intent);

         Toast.makeText(this, "Perfect", Toast.LENGTH_LONG).show();
         initMap();
        }else
        {
            Toast.makeText(this, "Play services problem", Toast.LENGTH_LONG).show();
        }

        LocationManager locationManager;
        CustomizedLocationListener locationListener  = new CustomizedLocationListener(getApplicationContext());
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.MAPS_RECEIVE)
                != PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5 * 60 * 1000, 10, locationListener);
        }


        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this,
                    R.string.Bienvenue + " " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();
        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SeekBar distanceBar = (SeekBar)findViewById(R.id.seekBarDistance); // make seekbar object
        distanceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                TextView textViewDistance = (TextView)findViewById(R.id.textViewDistance);
                textViewDistance.setText(progress + " KM");
            }
        });



        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.distNext);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               goToDetailView();
            }
        });



    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    public boolean googleServicesAvalaible()
    {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvalaible = api.isGooglePlayServicesAvailable(this);
        if(isAvalaible == ConnectionResult.SUCCESS)
        {
            return true;
        }else if (api.isUserResolvableError(isAvalaible))
        {
            Dialog dialog = api.getErrorDialog(this, isAvalaible,0);
            dialog.show();
        }else
        {
            Toast.makeText(this, "Can't connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void goToDetailView() {
        Intent intent = new Intent(this, Services.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Successfully signed in. Welcome!",
                        Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();

                // Close the app
                finish();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(),
                                    "You have been signed out.",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // Close activity
                            finish();
                        }
                    });
        }
        return true;
    }


    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        goToLocationZoom(45.3746323,-71.903891, 17);
    }

    private void goToLocation(double lat, double lon) {
        LatLng latLon = new LatLng(lat, lon);
        CameraUpdate camUpdate = CameraUpdateFactory.newLatLng(latLon);
        googleMap.moveCamera(camUpdate);
    }

    private void goToLocationZoom(double lat, double lon, float zoom) {
        LatLng latLon = new LatLng(lat, lon);
        CameraUpdate camUpdate = CameraUpdateFactory.newLatLngZoom(latLon, zoom);
        googleMap.moveCamera(camUpdate);
    }
}
