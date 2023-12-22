package com.example.emed_projecte_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hospital {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("ubication")
    @Expose
    private String ubication;

    public Hospital(String name, String ubication) {
        this.name = name;
        this.ubication = ubication;
    }

    public Hospital() {
    }

}
