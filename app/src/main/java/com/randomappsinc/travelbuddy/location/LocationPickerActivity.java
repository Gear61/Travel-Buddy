package com.randomappsinc.travelbuddy.location;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.randomappsinc.travelbuddy.R;
import com.randomappsinc.travelbuddy.common.StandardActivity;
import com.randomappsinc.travelbuddy.util.LocationUtil;
import com.randomappsinc.travelbuddy.util.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationPickerActivity extends StandardActivity
        implements OnMapReadyCallback, LocationManager.Listener {

    @BindView(R.id.map_view) MapView mapView;

    private GoogleMap googleMap;
    private LocationManager locationManager;
    private boolean denialLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        locationManager = new LocationManager(this, this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.setOnMapClickListener(this::zoomToLocation);
    }

    private void zoomToLocation(LatLng location) {
        googleMap.clear();
        String locationText = LocationUtil.getLocationText(location, this);
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .title(getString(R.string.hello_from_location, locationText)));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(location)
                .zoom(16)
                .bearing(0)
                .tilt(0)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onServicesOrPermissionChoice() {
        denialLock = false;
    }

    @Override
    public void onLocationFound(double latitude, double longitude) {
        zoomToLocation(new LatLng(latitude, longitude));
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        if (requestCode != LocationManager.LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        // No need to check if the location permission has been granted because of the onResume() block
        if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            denialLock = true;
            locationManager.showLocationPermissionDialog();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LocationManager.LOCATION_SERVICES_CODE) {
            if (resultCode == RESULT_OK) {
                UIUtil.showLongToast(R.string.location_services_on, this);
                locationManager.fetchAutomaticLocation();
            } else {
                denialLock = true;
                locationManager.showLocationDenialDialog();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();

        // Run this here instead of onCreate() to cover the case where they return from turning on location
        if (!denialLock) {
            locationManager.fetchCurrentLocation();
        }
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
