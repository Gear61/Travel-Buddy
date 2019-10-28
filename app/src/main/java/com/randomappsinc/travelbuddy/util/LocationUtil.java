package com.randomappsinc.travelbuddy.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationUtil {

    public static String getLocationText(LatLng latLng, Context context) {
        // Try to get the location from Geocoder
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
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
}
