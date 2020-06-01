package kz.sdu.map.sdu_maps.models;

import androidx.annotation.DrawableRes;

import kz.sdu.map.sdu_maps.R;

public class CategoryModel {
    private int id;
    private String name;

    @DrawableRes
    private int markerIcon;
    private boolean isSelected;

    public CategoryModel(int id, String name, int markerIcon, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.markerIcon = markerIcon;
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMarkerIcon() {
        return markerIcon;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getColor() {
        if (isSelected) {
            return R.color.colorSelectedCategory;
        } else {
            return R.color.colorUnselectedCategory;
        }
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + markerIcon + '\'' +
                '}';
    }
}
