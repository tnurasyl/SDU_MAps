package kz.sdu.map.sdu_maps.listeners;

import java.util.List;

import kz.sdu.map.sdu_maps.models.MapMarkerModel;

//
// Created by  on 04.06.20.
//
public interface OnMarkersShowListener {
    void showMarkers(List<MapMarkerModel> places);
}
