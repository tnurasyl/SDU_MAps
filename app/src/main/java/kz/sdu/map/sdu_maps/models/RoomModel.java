package kz.sdu.map.sdu_maps.models;

import androidx.annotation.DrawableRes;

public class RoomModel {
    private int roomId;
    private String roomName;

    private int facultyId;

    @DrawableRes
    private int logoId;

    private double latitude;
    private double longitude;

    public RoomModel(int roomId, String roomName, int facultyId, int logoId, double latitude, double longitude) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.facultyId = facultyId;
        this.logoId = logoId;
        this.latitude = latitude;
        this.longitude = longitude;
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
}

