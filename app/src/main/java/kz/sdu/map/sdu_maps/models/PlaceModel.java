package kz.sdu.map.sdu_maps.models;

public class PlaceModel {
    private int id;
    private String name;
    private int categoryId;
    private double longitude;
    private double latitude;

    public PlaceModel(int id, String name, int categoryId, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "PlaceModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
