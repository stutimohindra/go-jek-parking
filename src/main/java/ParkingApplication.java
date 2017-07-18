import com.gojek.parking.controller.ParkingServiceController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ParkingApplication {
    private static String FILENAME;

    public static void main(String[] args) {
        ParkingServiceController parkingServiceController = new ParkingServiceController();
        if(args.length == 0){
            Scanner sc = new Scanner(System.in);
            do{
                String[] inputArray = sc.nextLine().split(" ");
                switchCase(inputArray, parkingServiceController);
            } while (true);
        }else {
            FILENAME = args[0];
            try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    String file = sCurrentLine;
                    String[] arrayString = file.split(" ");
                    switchCase(arrayString, parkingServiceController);
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * common function if the input is a string or file
     * @param arrayString
     * @param parkingServiceController
     */
    private static void switchCase( String[] arrayString,ParkingServiceController parkingServiceController){
        switch (arrayString[0]) {
            case "create_parking_lot":
                parkingServiceController.createSlots(arrayString);
                break;
            case "park":
                parkingServiceController.parkVehicle(arrayString);
                break;
            case "leave":
                parkingServiceController.leaveSlot(arrayString);
                break;
            case "status":
                parkingServiceController.status();
                break;
            case "registration_numbers_for_cars_with_colour":
                parkingServiceController.getRegistrationNumber(arrayString);
                break;
            case "slot_numbers_for_cars_with_colour":
                parkingServiceController.getSloNumberOnColor(arrayString);
                break;
            case "slot_number_for_registration_number":
                parkingServiceController.getSloNumberOnNumberPlate(arrayString);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
}
