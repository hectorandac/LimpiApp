package com.limpiapp.hecto.limpiapp;

import com.google.android.gms.maps.model.LatLng;

class Sector {

    String name;
    LatLng location;
    Integer level = 0;
    String[] time = {
            "No hoy",
            "No hoy",
            "No hoy",
            "No hoy",
            "No hoy",
            "No hoy",
            "no hoy"
    };

    public Sector(String name, LatLng location, Integer level, String[] time) {
        this.name = name;
        this.location = location;
        this.level = level;
        this.time = time;
    }
}
