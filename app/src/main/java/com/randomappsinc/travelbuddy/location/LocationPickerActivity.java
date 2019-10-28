package com.randomappsinc.travelbuddy.location;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.randomappsinc.travelbuddy.R;
import com.randomappsinc.travelbuddy.util.LocationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationPickerActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.map_view) MapView mapView;

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sanFrancisco = new LatLng(37.8, 122.4);
        googleMap.addMarker(new MarkerOptions().position(sanFrancisco).title("Marker in SF"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sanFrancisco));

        googleMap.setOnMapClickListener(latLong -> {
            googleMap.clear();
            String locationText = LocationUtil.getLocationText(latLong, this);
            googleMap.addMarker(new MarkerOptions()
                    .position(latLong)
                    .title(getString(R.string.hello_from_location, locationText)));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLong)
                    .zoom(16)
                    .bearing(0)
                    .tilt(0)
                    .build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
