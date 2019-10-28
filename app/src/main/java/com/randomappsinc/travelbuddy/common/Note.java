package com.randomappsinc.travelbuddy.common;

import com.google.android.gms.maps.model.LatLng;

import java.util.TimeZone;

public class Note {

    private String title;

    // UNIX time of when the note was taken (milliseconds)
    private long noteTakenTime;
    private TimeZone noteTakenTimeZone;

    private LatLng location;
    private String description;
    private String imagePath;

    public Note(
            String title,
            long noteTakenTime,
            TimeZone noteTakenTimeZone,
            LatLng location,
            String description,
            String imagePath) {
        this.title = title;
        this.noteTakenTime = noteTakenTime;
        this.noteTakenTimeZone = noteTakenTimeZone;
        this.location = location;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public long getTime() {
        return noteTakenTime;
    }

    public TimeZone getTimeZone() {
        return noteTakenTimeZone;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
