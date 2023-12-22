package com.example.emed_projecte_android.models;

import static java.nio.file.Files.find;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("mail")
    @Expose
    private String mail;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("DNI")
    @Expose
    private String DNI;

    @SerializedName("numberSS")
    @Expose
    private Integer numberSS;

    @SerializedName("numberM")
    @Expose
    private Integer numberM;

    @SerializedName("phone_number")
    @Expose
    private String phone_number;

    public User() {
    }

    public User(String name, String surname, String mail, String password, String DNI, Integer numberSS, Integer numberM, String phone_number) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.DNI = DNI;
        this.numberSS = numberSS;
        this.numberM = numberM;
        this.phone_number = phone_number;
    }

}
