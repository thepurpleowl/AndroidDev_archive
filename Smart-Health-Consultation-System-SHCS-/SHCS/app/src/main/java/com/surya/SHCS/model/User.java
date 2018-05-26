package com.surya.SHCS.model;

/**
 * Created by lalit on 9/12/2016.
 */
public class User {

    private int id;
    private int user_type;
    private String name;
    private String email;
    private String password;
    private String location;
    private String speciality;
    private String hospital;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public int getUserType() {
        return user_type;
    }

    public void setUserType(int id) {
        this.user_type = id;
    }

}
