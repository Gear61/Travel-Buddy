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

    public Note(
            String title, long noteTakenTime, TimeZone noteTakenTimeZone, LatLng location, String description) {
        this.title = title;
        this.noteTakenTime = noteTakenTime;
        this.noteTakenTimeZone = noteTakenTimeZone;
        this.location = location;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public long getNoteTakenTime() {
        return noteTakenTime;
    }

    public TimeZone getNoteTakenTimeZone() {
        return noteTakenTimeZone;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
