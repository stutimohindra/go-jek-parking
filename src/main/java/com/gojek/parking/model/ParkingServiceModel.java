package com.gojek.parking.model;

/**
 * Data for vehicle to be parked
 */
public class ParkingServiceModel {

    private String plateNumber;
    private String color;


    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateNumber(){
        return plateNumber;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }
}
