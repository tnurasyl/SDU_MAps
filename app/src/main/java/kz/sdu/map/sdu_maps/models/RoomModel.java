package kz.sdu.map.sdu_maps.models;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

import kz.sdu.map.sdu_maps.R;

public class RoomModel {
    private int roomId;
    private String roomName;

    private int facultyId;

    private boolean isSelected;

    @DrawableRes
    private int logoId;

    private double latitude;
    private double longitude;

    public RoomModel(int roomId, String roomName, int facultyId, int logoId, double latitude, double longitude, boolean isSelected) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.facultyId = facultyId;
        this.logoId = logoId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isSelected = isSelected;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getLogoId() {
        return logoId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getBackgroundColor() {
        if (isSelected) return R.color.colorGray;
        else return R.color.colorWhite;
    }
}

