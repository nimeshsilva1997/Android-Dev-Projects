package com.example.lab9silva;

//"POJOs" (Plain Old Java Objects)
public class Accel {
    //Protect the Data
    private float AccelX, AccelY, AccelZ;
    //Setters and Getters
    public float getX() { return AccelX; }
    public void setX(float _x) {this.AccelX = _x;}
    public float getY() {return AccelY;}
    public void setY(float _y) {this.AccelY = _y;}
    public float getZ() { return AccelZ; }
    public void setZ(float _z) {this.AccelZ = _z;}
}
