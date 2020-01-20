package com.mj.android_note.base.serializable_parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class School implements Parcelable {

    public int cityCode;
    public String name;
    public transient String location;

    public School(int cityCode, String name, String location) {
        this.cityCode = cityCode;
        this.name = name;
        this.location = location;
    }

    private School(Parcel in) {
        cityCode = in.readInt();
        name = in.readString();
        location = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cityCode);
        dest.writeString(name);
        dest.writeString(location);
    }

    public static final Creator<School> CREATOR = new Creator<School>() {
        @Override
        public School createFromParcel(Parcel in) {
            return new School(in);
        }

        @Override
        public School[] newArray(int size) {
            return new School[size];
        }
    };


    @Override
    public String toString() {
        return "School{" +
                "cityCode=" + cityCode +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
