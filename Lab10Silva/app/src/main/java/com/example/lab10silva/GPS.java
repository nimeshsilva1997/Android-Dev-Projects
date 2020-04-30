package com.example.lab10silva;

public class GPS {
    //Protect the Data
    private float Latitude, Longitude, Altitude;
    //Setters and Getters

    public float getLatitude() {return Latitude;}
    public void setLatitude(float _Lat) {this.Latitude = _Lat;}
    public float getLongitude() {return Longitude;}
    public void setLongitude(float _Long){this.Longitude = _Long;}
    public float getAltitude() {return Altitude; }
    public void setAltitude(float _Alt) {this.Altitude = _Alt;}
}
