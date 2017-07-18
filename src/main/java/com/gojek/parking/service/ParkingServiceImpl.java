package com.gojek.parking.service;

import com.gojek.parking.constants.ParkingServiceConstants;
import com.gojek.parking.model.ParkingServiceModel;
import com.gojek.parking.data.ParkingServiceData;
import com.gojek.parking.model.ParkingServiceResponse;
import java.util.HashMap;

/**
 * this class implements the interface and returns the response to the controller.
 * Each function in the class checks the length of the args and also if the params are valid
 */

public class ParkingServiceImpl implements ParkingService{

     ParkingServiceData parkingServiceData = new ParkingServiceData();

    /**
     * create hashmap with the given length and returns response to the controller
     * @param slots
     * @return
     */
    public ParkingServiceResponse createSlots(String[] slots){
        ParkingServiceResponse parkingServiceResponse = new ParkingServiceResponse();
        if(slots.length == 2) {
            if (slots[1].length() == 0) {
               ParkingServiceResponse response = returnEmptyString(parkingServiceResponse);
               return response;
            }
            try {
                String response = parkingServiceData.createParkingPlace(Integer.parseInt(slots[1]));
                if (response.equals(ParkingServiceConstants.success)) {
                    parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_SUCCESS);
                    parkingServiceResponse.setErrorMessage(ParkingServiceConstants.success);
                    parkingServiceResponse.setResult(slots[1].toString());
                    return parkingServiceResponse;
                } else {
                    parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                    parkingServiceResponse.setErrorMessage(ParkingServiceConstants.failed);
                    parkingServiceResponse.setResult("");
                    return parkingServiceResponse;
                }
            } catch (Exception e) {
                parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                parkingServiceResponse.setErrorMessage(ParkingServiceConstants.SLOT_NOT_CREATED);
                parkingServiceResponse.setResult("");
                return parkingServiceResponse;
            }
        }else {
            parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
            parkingServiceResponse.setErrorMessage(ParkingServiceConstants.INSUFFICIENT_DATA);
            parkingServiceResponse.setResult("");
            return parkingServiceResponse;
        }
    }

    /**
     * this function returns the status of the parked vehicles in a tabular form to the controller
     * @return
     */
    public ParkingServiceResponse getStatus() {
        ParkingServiceResponse parkingServiceResponse = new ParkingServiceResponse();
        StringBuilder result = new StringBuilder("");
        try {
            HashMap<Integer, ParkingServiceModel> hashMap = parkingServiceData.getParkingHashMap();
            for (HashMap.Entry<Integer, ParkingServiceModel> entry : hashMap.entrySet()) {
                if (entry.getValue() != null) {
                    ParkingServiceModel parkingServiceModel = hashMap.get(entry.getKey());
                    result.append(entry.getKey() + "\t\t" + parkingServiceModel.getPlateNumber() + "\t\t" + parkingServiceModel.getColor() + "\n");
                }
            }
            if (result.length() == 0) {
                parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                parkingServiceResponse.setErrorMessage(ParkingServiceConstants.NO_STATUS_FOUND);
                parkingServiceResponse.setResult(result.toString());
                return parkingServiceResponse;
            } else {
                parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_SUCCESS);
                parkingServiceResponse.setErrorMessage(ParkingServiceConstants.success);
                parkingServiceResponse.setResult(result.toString());
                return parkingServiceResponse;
            }
        }catch (Exception e){
            parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
            parkingServiceResponse.setErrorMessage(ParkingServiceConstants.SLOT_NOT_CREATED);
            parkingServiceResponse.setResult("");
            return parkingServiceResponse;
        }

    }

    /**
     * this function returns the slot number of the vehicle on the color specified and returns response to the controller
     * @param args
     * @return
     */
    public ParkingServiceResponse getSlotNumberOnColor(String[] args) {
        ParkingServiceResponse parkingServiceResponse = new ParkingServiceResponse();
         StringBuilder slotNumbers = new StringBuilder("");
        if(args.length == 2) {
            if (args[1].length() == 0) {
               ParkingServiceResponse response = returnEmptyString(parkingServiceResponse);
               return response;
            }
            try {
                HashMap<Integer, ParkingServiceModel> hashMap = parkingServiceData.getParkingHashMap();
                for (HashMap.Entry<Integer, ParkingServiceModel> entry : hashMap.entrySet()) {
                    if (entry.getValue() != null && entry.getValue().getColor().equalsIgnoreCase(args[1])) {
                        slotNumbers.append(entry.getKey());
                        slotNumbers.append(",");
                    }
                }
                parkingServiceResponse = checkResponse(slotNumbers.toString());
                return parkingServiceResponse;
            } catch (Exception e) {
                parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                parkingServiceResponse.setErrorMessage(ParkingServiceConstants.SLOT_NOT_CREATED);
                parkingServiceResponse.setResult("");
                return parkingServiceResponse;
            }
        }else{
            parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
            parkingServiceResponse.setErrorMessage(ParkingServiceConstants.INSUFFICIENT_DATA);
            parkingServiceResponse.setResult("");
            return parkingServiceResponse;
        }
    }

    /**
     * this function returns the slot number of the vehicle on the number plate specified
     * and returns response to the controller
     * @param args
     * @return
     */

    public ParkingServiceResponse getSlotNumberOnNumberPlate(String[] args) {
        ParkingServiceResponse parkingServiceResponse = new ParkingServiceResponse();
         StringBuilder slotNumbers = new StringBuilder("");
        if(args.length == 2) {
            if (args[1].length() == 0) {
                ParkingServiceResponse response = returnEmptyString(parkingServiceResponse);
                return response;
            }
            try {
                HashMap<Integer, ParkingServiceModel> hashMap = parkingServiceData.getParkingHashMap();
                for (HashMap.Entry<Integer, ParkingServiceModel> entry : hashMap.entrySet()) {
                    if (entry.getValue() != null && entry.getValue().getPlateNumber().equalsIgnoreCase(args[1])) {
                        slotNumbers.append(entry.getKey());
                        slotNumbers.append(",");
                    }
                }
                parkingServiceResponse = checkResponse(slotNumbers.toString());
                return parkingServiceResponse;
            } catch (Exception e) {
                parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                parkingServiceResponse.setErrorMessage(ParkingServiceConstants.SLOT_NOT_CREATED);
                parkingServiceResponse.setResult("");
                return parkingServiceResponse;
            }
        }else{
            parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
            parkingServiceResponse.setErrorMessage(ParkingServiceConstants.INSUFFICIENT_DATA);
            parkingServiceResponse.setResult("");
            return parkingServiceResponse;
        }
    }

    /**
     * this function returns the registration number of the color specified and returns it to the controller
     * @param args
     * @return
     */
    public ParkingServiceResponse getRegistrationNumber(String[] args) {
        ParkingServiceResponse parkingServiceResponse = new ParkingServiceResponse();
        StringBuilder registrationNumbers=new StringBuilder("");
        if(args.length == 2) {
            if(args[1].length() == 0){
                ParkingServiceResponse response = returnEmptyString(parkingServiceResponse);
                return response;
            }
            try {
                HashMap<Integer, ParkingServiceModel> hashMap = parkingServiceData.getParkingHashMap();
                    for (HashMap.Entry<Integer, ParkingServiceModel> entry : hashMap.entrySet()) {
                        if (entry.getValue() != null && entry.getValue().getColor().equalsIgnoreCase(args[1])) {
                            registrationNumbers.append(entry.getValue().getPlateNumber());
                            registrationNumbers.append(",");
                        }
                    }
                    parkingServiceResponse = checkResponse(registrationNumbers.toString());
                    return parkingServiceResponse;
            } catch (Exception e) {
                parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                parkingServiceResponse.setErrorMessage(ParkingServiceConstants.SLOT_NOT_CREATED);
                parkingServiceResponse.setResult("");
                return parkingServiceResponse;
            }
        }else{
            parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
            parkingServiceResponse.setErrorMessage(ParkingServiceConstants.INSUFFICIENT_DATA);
            parkingServiceResponse.setResult("");
            return parkingServiceResponse;
        }
    }

    /**
     * this function allocates slot to a vehicle in increasing order and returns response to controller
     * @param args
     * @return
     */

    public ParkingServiceResponse allocateSlot(String[] args){
        ParkingServiceResponse parkingServiceResponse = new ParkingServiceResponse();
        ParkingServiceModel parkingServiceModel = new ParkingServiceModel();
        try {
            HashMap<Integer,ParkingServiceModel> hashMap = parkingServiceData.getParkingHashMap();
            if(args.length == 3) {
                if((args[1].length() > 0) || (args[2].length() > 0) ) {
                    parkingServiceModel.setPlateNumber(args[1]);
                    parkingServiceModel.setColor(args[2]);

                } else {
                    parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                    parkingServiceResponse.setErrorMessage(ParkingServiceConstants.EMPTY_STRING);
                    parkingServiceResponse.setResult("");
                    return parkingServiceResponse;
                }
            }else{
                parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                parkingServiceResponse.setErrorMessage(ParkingServiceConstants.INSUFFICIENT_DATA);
                parkingServiceResponse.setResult("");
                return parkingServiceResponse;
            }

            for (HashMap.Entry<Integer, ParkingServiceModel> entry : hashMap.entrySet()) {
                if(entry.getValue() != null && entry.getValue().getColor().equals(args[2]) && entry.getValue().getPlateNumber().equals(args[1])){
                    parkingServiceResponse.setResult(entry.getKey().toString());
                    parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                    parkingServiceResponse.setErrorMessage(ParkingServiceConstants.ALREADY_ALLOCATED);
                    return parkingServiceResponse;
                } else if (entry.getValue() == null) {
                    hashMap.put(entry.getKey(), parkingServiceModel);
                    parkingServiceResponse.setResult(entry.getKey().toString());
                    parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_SUCCESS);
                    parkingServiceResponse.setErrorMessage(ParkingServiceConstants.success);
                    return parkingServiceResponse;
                }
            }
            parkingServiceResponse.setResult("");
            parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
            parkingServiceResponse.setErrorMessage(ParkingServiceConstants.PARKING_LOT_FULL);
            return parkingServiceResponse;
        }catch (Exception e){
            parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
            parkingServiceResponse.setErrorMessage(ParkingServiceConstants.SLOT_NOT_CREATED);
            parkingServiceResponse.setResult("");
            return parkingServiceResponse;
        }
    }

    /**
     * this function replaces value of hashmap at the key ie the slot with null
     * ie the slot will now be empty and can be allocated to the first incoming parking request
     * @param args
     * @return
     */
    public ParkingServiceResponse leaveSlot(String[] args){
        ParkingServiceResponse parkingServiceResponse = new ParkingServiceResponse();
        HashMap<Integer,ParkingServiceModel> hashMap = parkingServiceData.getParkingHashMap();
        try {
            if (args.length == 2) {
                if (args[1].length() == 0) {
                    ParkingServiceResponse response = returnEmptyString(parkingServiceResponse);
                    return response;
                }
                if (hashMap.get(Integer.parseInt(args[1])) == null) {
                    parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                    parkingServiceResponse.setErrorMessage(ParkingServiceConstants.INVALID_SLOT);
                    parkingServiceResponse.setResult("");
                    return parkingServiceResponse;
                }
                hashMap.put(Integer.parseInt(args[1]), null);
                if (hashMap.get(Integer.parseInt(args[1])) == null) {
                    parkingServiceResponse.setResult(args[1].toString());
                    parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_SUCCESS);
                    parkingServiceResponse.setErrorMessage(ParkingServiceConstants.success);
                    return parkingServiceResponse;
                }
                parkingServiceResponse.setResult("");
                parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                parkingServiceResponse.setErrorMessage(ParkingServiceConstants.failed);
                return parkingServiceResponse;
            } else {
                parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
                parkingServiceResponse.setErrorMessage(ParkingServiceConstants.INSUFFICIENT_DATA);
                parkingServiceResponse.setResult("");
                return parkingServiceResponse;
            }
        }catch (Exception e){
            parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
            parkingServiceResponse.setErrorMessage(ParkingServiceConstants.SLOT_NOT_CREATED);
            parkingServiceResponse.setResult("");
            return parkingServiceResponse;
        }
    }

    /**
     * common function to return response for string builder
     * for returning slot number on color and registration number
     * @param parkingString
     * @return
     */
    private ParkingServiceResponse checkResponse(String parkingString){
        ParkingServiceResponse parkingServiceResponse = new ParkingServiceResponse();
        if(parkingString.length() == 0){
            parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
            parkingServiceResponse.setErrorMessage(ParkingServiceConstants.NOT_FOUND);
            parkingServiceResponse.setResult(parkingString);
            return parkingServiceResponse;
        }else{
            parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_SUCCESS);
            parkingServiceResponse.setErrorMessage(ParkingServiceConstants.success);
            parkingServiceResponse.setResult(parkingString.substring(0,parkingString.length() -1));
            return parkingServiceResponse;
        }
    }

    /**
     * common function to return response in case args are empty
     * @param parkingServiceResponse
     * @return
     */
    private ParkingServiceResponse returnEmptyString(ParkingServiceResponse parkingServiceResponse){
        parkingServiceResponse.setErrorCode(ParkingServiceConstants.ERROR_CODE_FAILURE);
        parkingServiceResponse.setErrorMessage(ParkingServiceConstants.EMPTY_STRING);
        parkingServiceResponse.setResult("");
        return parkingServiceResponse;
    }

}
