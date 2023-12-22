package com.example.emed_projecte_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorSS {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("office")
    @Expose
    private String office;

    @SerializedName("speciality")
    @Expose
    private String speciality;

    @SerializedName("disponibility")
    @Expose
    private boolean disponibility;

    public DoctorSS(String name, String office, String speciality, boolean disponibility) {
        this.name = name;
        this.office = office;
        this.speciality = speciality;
        this.disponibility = disponibility;
    }

    public DoctorSS() {
    }

}
