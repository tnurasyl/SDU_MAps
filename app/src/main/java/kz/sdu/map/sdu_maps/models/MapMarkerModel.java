package kz.sdu.map.sdu_maps.models;

import androidx.annotation.DrawableRes;

public class MapMarkerModel {
    private String title;
    private double latitude;
    private double longitude;

    @DrawableRes
    private int markerIcon;

    public MapMarkerModel(String title, double latitude, double longitude, int markerIcon) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.markerIcon = markerIcon;
    }

    public String getTitle() {
        return title;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getMarkerIcon() {
        return markerIcon;
    }
}
