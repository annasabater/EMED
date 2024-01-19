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

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("hospital")
    @Expose
    private String hospital;

    public DoctorI(String name, String office, String speciality, String phone, String hospital) {
        this.name = name;
        this.office = office;
        this.speciality = speciality;
        this.phone = phone;
        this.hospital = hospital;
    }

    public DoctorI() {
    }
}
