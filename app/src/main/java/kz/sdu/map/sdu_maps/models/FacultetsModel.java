package kz.sdu.map.sdu_maps.models;

import androidx.annotation.DrawableRes;

public class FacultetsModel {
    private int facultetId;
    private String facultetName;

    @DrawableRes
    private int logoId;

    public FacultetsModel(int facultetId, String facultetName, int logoId) {
        this.facultetId = facultetId;
        this.facultetName = facultetName;
        this.logoId = logoId;
    }

    public int getFacultetId() {
        return facultetId;
    }

    public String getFacultetName() {
        return facultetName;
    }

    public int getLogoId() {
        return logoId;
    }


}
