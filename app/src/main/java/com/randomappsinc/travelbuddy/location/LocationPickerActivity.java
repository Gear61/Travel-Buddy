package com.randomappsinc.travelbuddy.location;

/* import androidx.fragment.app.FragmentActivity;

import android.location.Address;

import android.location.Geocoder;

import android.os.Bundle;

import android.view.View;

import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.util.List;

import java.util.Locale;

import java.util.StringJoiner; */

import androidx.fragment.app.FragmentActivity;

public class LocationPickerActivity extends FragmentActivity { // implements OnMapReadyCallback {

    /* private GoogleMap mMap;

    private TextView locationTextView;

    private TextView latLongTextView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()

                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        locationTextView = findViewById(R.id.location_name);

        latLongTextView = findViewById(R.id.lat_long);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng khalilLatLong = new LatLng(31.52935, 35.0938);

        mMap.addMarker(new MarkerOptions().position(khalilLatLong).title("My hometown Al-Khalil"));

        setLocationName(khalilLatLong);

        latLongTextView.setText(round(khalilLatLong.latitude,2) + ", " + round(khalilLatLong.longitude, 2));

        mMap.animateCamera(CameraUpdateFactory.newLatLng(khalilLatLong));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override

            public void onMapClick(LatLng latLng) {

                mMap.clear();

                mMap.addMarker(new MarkerOptions().position(latLng).title("Hello from " + getLocationText(latLng) + "!"));

                setLocationName(latLng);

                latLongTextView.setText(round(latLng.latitude,2) + ", " + round(latLng.longitude, 2));

                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

            }

        });

    }

    private void setLocationName(LatLng latLng) {

        locationTextView.setText(getLocationText(latLng));

    }

    private String getLocationText(LatLng latLng) {

        // Try to get the location from Geocoder

        Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());

        List<Address> addresses = null;

        try {

            addresses = gcd.getFromLocation(latLng.latitude, latLng.longitude, 1);

        } catch (IOException e) {

            e.printStackTrace();

        }

        // Put together the proper string using a string builder.

        // We start off with "Unknown" in case there is no address

        StringBuilder builder = new StringBuilder("Unknown");

        if (addresses != null && !addresses.isEmpty()) {

            Address address = addresses.get(0);

            if (address.getLocality() != null) {

                // Clear "Unknown" from the StringBuilder since we will add some info

                builder.setLength(0);

                builder.append(address.getLocality());

                if (address.getCountryName() != null) {

                    builder.append(", " + getProperCountryName(address.getCountryName()));

                }

            } else if (address.getCountryName() != null) {

                // Clear "Unknown" from the StringBuilder since we will add some info

                builder.setLength(0);

                builder.append(getProperCountryName(address.getCountryName()));

            }

        }

        return builder.toString();

    }

    private static String getProperCountryName(String countryName) {

        return countryName.equals("Israel") ? "Palestine" : countryName;

    }

    private static double round(double value, int places) {

        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);

        bd = bd.setScale(places, RoundingMode.HALF_UP);

        return bd.doubleValue();

    } */
}
