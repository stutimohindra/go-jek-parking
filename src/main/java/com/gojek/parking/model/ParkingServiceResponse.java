package com.gojek.parking.model;

/**
 * Response class to return error code ,status and result
 */
public class ParkingServiceResponse {

    private String result;
    private String errorMessage;
    private int errorCode;

    public void setResult(String result){
        this.result = result;
    }
    public String getResult(){
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode(){
        return errorCode;
    }

    public void setErrorCode(int errorCode){
        this.errorCode = errorCode;
    }
}
