package com.gojek.parking.service;

import com.gojek.parking.model.ParkingServiceResponse;

/**
 * interface that defines all the functionality of the parking system
 */
public interface ParkingService {

    ParkingServiceResponse getStatus();
    ParkingServiceResponse getSlotNumberOnColor(String[] args);
    ParkingServiceResponse getSlotNumberOnNumberPlate(String[] args);
    ParkingServiceResponse getRegistrationNumber(String[] args);
    ParkingServiceResponse allocateSlot(String[] args);
    ParkingServiceResponse leaveSlot(String[] args);
    ParkingServiceResponse createSlots(String[] args);

}

