package com.randomappsinc.travelbuddy.common;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.TimeZone;

public class Note implements Parcelable {

    private String title;

    // UNIX time of when the note was taken (milliseconds)
    private long noteTakenTime;
    private TimeZone noteTakenTimeZone;

    private String description;

    public Note(String title, long noteTakenTime, TimeZone noteTakenTimeZone, String description) {
        this.title = title;
        this.noteTakenTime = noteTakenTime;
        this.noteTakenTimeZone = noteTakenTimeZone;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getNoteTakenTime() {
        return noteTakenTime;
    }

    public void setNoteTakenTime(long noteTakenTime) {
        this.noteTakenTime = noteTakenTime;
    }

    public TimeZone getNoteTakenTimeZone() {
        return noteTakenTimeZone;
    }

    public void setNoteTakenTimeZone(TimeZone noteTakenTimeZone) {
        this.noteTakenTimeZone = noteTakenTimeZone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected Note(Parcel in) {
        title = in.readString();
        noteTakenTime = in.readLong();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeLong(noteTakenTime);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
