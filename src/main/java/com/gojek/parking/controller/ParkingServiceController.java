package com.gojek.parking.controller;

import com.gojek.parking.constants.ParkingServiceConstants;
import com.gojek.parking.model.ParkingServiceResponse;
import com.gojek.parking.service.ParkingService;
import com.gojek.parking.service.ParkingServiceImpl;

/**
 * this class will receive request from the main class and will return response from appropriate function
 *
 */
public class ParkingServiceController {

    private ParkingService parkingServiceImpl = new ParkingServiceImpl();

    /**
     * calls function in Impl class and creates slot for the parking
      * @param args is an array of string of length two
     */
    public void createSlots(String[] args){
        ParkingServiceResponse parkingServiceResponse = parkingServiceImpl.createSlots(args);
        if(parkingServiceResponse.getErrorCode() == ParkingServiceConstants.ERROR_CODE_SUCCESS){
            System.out.println("Created a parking lot with "+parkingServiceResponse.getResult()+" slots");
        }else {
            System.out.println(parkingServiceResponse.getErrorMessage());
        }
    }

    /**
     * allocates slot to the vehicle and stores data in the form of object in a hash map
     * @param args is an array of string of length three
     */
    public void parkVehicle(String[] args){
        ParkingServiceResponse parkingServiceResponse = parkingServiceImpl.allocateSlot(args);
        if(parkingServiceResponse.getErrorCode() == ParkingServiceConstants.ERROR_CODE_SUCCESS){
            System.out.println("Allocated slot number: "+parkingServiceResponse.getResult());
        }else{
            System.out.println(parkingServiceResponse.getErrorMessage());
        }
    }

    /**
     * de allocates slot makes the value null in the hash map object against the key
     * @param args  is an array of string of length two
     */
    public void leaveSlot(String[] args){
        ParkingServiceResponse parkingServiceResponse = parkingServiceImpl.leaveSlot(args);
        if(parkingServiceResponse.getErrorCode() == ParkingServiceConstants.ERROR_CODE_SUCCESS) {
            System.out.println("Slot number "+parkingServiceResponse.getResult()+" is free");
        }else {
            System.out.println(parkingServiceResponse.getErrorMessage());
        }
    }

    /**
     * returns the status of all the parked vehicles in tabular form
     */
    public void status(){
        ParkingServiceResponse parkingServiceResponse = parkingServiceImpl.getStatus();
        if(parkingServiceResponse.getErrorCode() == ParkingServiceConstants.ERROR_CODE_SUCCESS){
            System.out.println("Slot No\t\t"+"Registration No.\t"+"Colour");
            System.out.println(parkingServiceResponse.getResult());
        }else{
            System.out.println(parkingServiceResponse.getErrorMessage());
        }

    }

    /**
     * returns registration number of the cars on the color
     * @param args  is an array of string of length two
     */
    public void getRegistrationNumber(String[] args){
        ParkingServiceResponse parkingServiceResponse = parkingServiceImpl.getRegistrationNumber(args);
        if(parkingServiceResponse.getErrorCode() == ParkingServiceConstants.ERROR_CODE_SUCCESS){
            System.out.println(parkingServiceResponse.getResult());
        }else{
            System.out.println(parkingServiceResponse.getErrorMessage());
        }
    }

    /**
     * returns slot number of the car on the color specified
     * @param args  is an array of string of length two
     */
    public void getSloNumberOnColor(String[] args){
        ParkingServiceResponse parkingServiceResponse = parkingServiceImpl.getSlotNumberOnColor(args);
        if(parkingServiceResponse.getErrorCode() == ParkingServiceConstants.ERROR_CODE_SUCCESS){
            System.out.println(parkingServiceResponse.getResult());
        }else{

            System.out.println(parkingServiceResponse.getErrorMessage());
        }
    }

    /**
     * returns slot number of the car on the registration number specified
     * @param args  is an array of string of length two
     */
    public void getSloNumberOnNumberPlate(String[] args){
        ParkingServiceResponse parkingServiceResponse = parkingServiceImpl.getSlotNumberOnNumberPlate(args);
        if(parkingServiceResponse.getErrorCode() == ParkingServiceConstants.ERROR_CODE_SUCCESS){
            System.out.println(parkingServiceResponse.getResult());
        }else{
            System.out.println(parkingServiceResponse.getErrorMessage());
        }
    }
}
