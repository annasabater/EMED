package com.example.emed_projecte_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messages {

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("sender")
    @Expose
    private User sender;

    @SerializedName("doctorI")
    @Expose
    private DoctorI doctorI;

    @SerializedName("doctorSS")
    @Expose
    private DoctorSS doctorSS;

    @SerializedName("doctorM")
    @Expose
    private DoctorM doctorM;

    public Messages(String content, User sender, DoctorI doctorI, DoctorSS doctorSS, DoctorM doctorM) {
        this.content = content;
        this.sender = sender;
        this.doctorI = doctorI;
        this.doctorSS = doctorSS;
        this.doctorM = doctorM;
    }

    public Messages() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public DoctorI getDoctorI() {
        return doctorI;
    }

    public void setDoctorI(DoctorI doctorI) {
        this.doctorI = doctorI;
    }

    public DoctorSS getDoctorSS() {
        return doctorSS;
    }

    public void setDoctorSS(DoctorSS doctorSS) {
        this.doctorSS = doctorSS;
    }

    public DoctorM getDoctorM() {
        return doctorM;
    }

    public void setDoctorM(DoctorM doctorM) {
        this.doctorM = doctorM;
    }
}
