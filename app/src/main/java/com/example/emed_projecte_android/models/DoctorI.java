package com.example.emed_projecte_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorI {

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

    public DoctorI(String name, String office, String speciality, boolean disponibility) {
        this.name = name;
        this.office = office;
        this.speciality = this.speciality;
        this.disponibility = disponibility;
    }

    public DoctorI() {
    }
}
