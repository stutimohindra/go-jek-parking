package com.gojek.parking.data;

import com.gojek.parking.model.ParkingServiceModel;
import java.util.HashMap;

/**
 * class to create singleton object to be used to create slot in the form of hashmap
 * initialised to null
 */

public class ParkingServiceData {

    public HashMap<Integer,ParkingServiceModel> parkingHashMap;

    public static ParkingServiceData parkingServiceData ;

    public ParkingServiceData() {

    }
    public static ParkingServiceData createInstance() {
        if(parkingServiceData == null) {
            parkingServiceData = new ParkingServiceData();
        }
        return parkingServiceData;
    }

    public String createParkingPlace(int slots) {
        parkingHashMap = new HashMap<>(slots);
        while(slots !=0){
            parkingHashMap.put(slots,null);
            slots --;
        }
        return "Success";
    }

    public HashMap<Integer, ParkingServiceModel> getParkingHashMap() {
        return parkingHashMap;
    }

    public void setParkingHashMap(HashMap<Integer, ParkingServiceModel> parkingHashMap) {
        this.parkingHashMap = parkingHashMap;
    }
}
