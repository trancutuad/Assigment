package vn.edu.assigment;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
private SearchView svMap;
    private GoogleMap mMap;
    private EditText edtLatitude, edtLongtitude;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        edtLatitude = findViewById(R.id.edtLatitude);
        edtLongtitude = findViewById(R.id.edtLongtitude);
        btnSearch = findViewById(R.id.btnSearch);
        svMap=findViewById(R.id.svMap);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        svMap.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                String location = svMap.getQuery().toString();
                List<Address> addresses=null;
                try{
                    if(location!=null || location.equals("")){
                        Geocoder geocoder=new Geocoder(MapsActivity.this);
                        try{
                            addresses=geocoder.getFromLocationName(location,1);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        Address address=addresses.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));
                    }
                }catch (Exception e){
                    Toast.makeText(MapsActivity.this,"Địa chỉ cần chính sác", Toast.LENGTH_SHORT).show();
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                double longtitude = Double.parseDouble(edtLongtitude.getText().toString().trim());
                double latitude = Double.parseDouble(edtLatitude.getText().toString().trim());

                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(longtitude, latitude);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in search"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }
        });

    }

    private static final int REQUEST_CODE_GPS_PERMISSION = 100;
    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //TODO: Get current location
            getCurrentLocation();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_GPS_PERMISSION);
            }
        }
    }

    private void getCurrentLocation() {
        com.google.android.gms.location.FusedLocationProviderClient mFusedLocationClient =
                LocationServices.getFusedLocationProviderClient(MapsActivity.this);
        ((com.google.android.gms.location.FusedLocationProviderClient) mFusedLocationClient).getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location == null) {
                            return;
                        }
                        LatLng currentLocation =
                                new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Marker in my"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                    }
                });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_GPS_PERMISSION:
                for (int grantResult : grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {

                    }
                }
                break;
        }
    }



}
