import com.gojek.parking.controller.ParkingServiceController;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * this class tests all the controllers and matches the expected and actual STDOUT
 */

public class ApplicationControllerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    ParkingServiceController parkingServiceController;

    /**
     * This function initialises the ByteArrayOutputStream before the each test executes
     * and creates the object for the controller
     */
    @Before
    public void createObject(){
        parkingServiceController = new ParkingServiceController();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * this function tests create slot for a valid test case
     * @result A valid hashmap will be created
     */
    @Test
    public void createSlot(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        assertEquals("Created a parking lot with "+args[1]+" slots", outContent.toString().trim());
    }

    /**
     * this function tests the create slot with less number of expected arguments
     * @result An appropriate error message will be returned
     */
    @Test
    public void createSlotLessArgs(){
        String[] args = {"create"};
        parkingServiceController.createSlots(args);
        assertEquals("Insufficient data", outContent.toString().trim());
    }

    /**
     * this function tests the case where an empty string is passed in the argument to create slot
     * @result An appropriate error message will be returned
     */
    @Test
    public void createSlotEmpty(){
        String[] args = {"create",""};
        parkingServiceController.createSlots(args);
        assertEquals("Empty string", outContent.toString().trim());
    }

    /**
     * this function tests with a negative slot number
     * @result An appropriate error message will be returned
     */
    @Test
    public void createSlotNegative(){
        String[] args = {"create","-8"};
        parkingServiceController.createSlots(args);
        assertEquals("No slots created", outContent.toString().trim());
    }

    /**
     * this function tests for a valid test case for leave slots
     *  @result The specified slot will be empty
     */
    @Test
    public void leaveSlot(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] park = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(park);
        String[] leave = {"leave","1"};
        parkingServiceController.leaveSlot(leave);
        assertEquals("Created a parking lot with "+args[1]+" slots\n" +
                "Allocated slot number: "+leave[1]+"\n" +
                "Slot number "+leave[1]+" is free",outContent.toString().trim());
    }

    /**
     * this function tests for a case where lesser number of arguments are passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void leaveSlotLessArgs(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] park = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(park);
        String[] leave = {"leave"};
        parkingServiceController.leaveSlot(leave);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Insufficient data",outContent.toString().trim());
    }

    /**
     * This function tests the case where the slot to be left is an empty string
     *  @result An appropriate error message will be returned
     */
    @Test
    public void leaveSlotEmpty(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] park = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(park);
        String[] leave = {"leave",""};
        parkingServiceController.leaveSlot(leave);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Empty string",outContent.toString().trim());
    }
    /**
     * This function tests the case where the slot to be left does not exist
     *  @result An appropriate error message will be returned
     */
    @Test
    public void leaveSlotInvalid(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] park = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(park);
        String[] leave = {"leave","20"};
        parkingServiceController.leaveSlot(leave);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Invalid Slot",outContent.toString().trim());
    }

    /**
     * this function tests when the same vehicle is being parked more than once
     *  @result An appropriate error message will be returned
     */
    @Test
    public void parkSameVehicle(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] park = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(park);
        String[] park1 = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(park1);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Already allocated slot to this vehicle" ,outContent.toString().trim());
    }

    /**
     * this function tests for a valid test case for allocating slot to a vehicle
     *  @result The slot will be allocated to the vehicle
     */
    @Test
    public void park(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] park = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(park);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1",outContent.toString().trim());
    }

    /**
     * this function tests for an empty args being passed to the function to allocate slot
     *  @result An appropriate error message will be returned
     */
    @Test
    public void parkEmpty(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] park = {"park","",""};
        parkingServiceController.parkVehicle(park);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Empty string",outContent.toString().trim());
    }

    /**
     * this function tests for allocating slot when lesser args are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void parkLessArgs(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] park = {"park"};
        parkingServiceController.parkVehicle(park);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Insufficient data",outContent.toString().trim());
    }

    /**
     * this function tests the status of the parked vehicles for a valid scenario
     *  @result A valid status is returned
     */
   @Test
   public void status(){
       String[] args = {"create","6"};
       parkingServiceController.createSlots(args);
       String[] parkOne = {"park","pb-10-0909","white"};
       parkingServiceController.parkVehicle(parkOne);
       String[] parkTwo = {"park","pb-10-1212","red"};
       parkingServiceController.parkVehicle(parkTwo);
       String[] parkThree = {"park","pb-10-1213","red"};
       parkingServiceController.parkVehicle(parkThree);
       parkingServiceController.status();
       assertEquals("Created a parking lot with 6 slots\n" +
               "Allocated slot number: 1\n" +
               "Allocated slot number: 2\n" +
               "Allocated slot number: 3\n" +
               "Slot No\t\tRegistration No.\tColour\n" +
               "1\t\tpb-10-0909\t\twhite\n" +
               "2\t\tpb-10-1212\t\tred\n" +
               "3\t\tpb-10-1213\t\tred",outContent.toString().trim());
   }

    /**
     * this function tests the registration number of all the vehicles of the color specified for a valid scenario
     *  @result Valid registration numbers are returned
     */
   @Test
   public void getRegistrationNumber(){
       String[] args = {"create","6"};
       parkingServiceController.createSlots(args);
       String[] parkOne = {"park","pb-10-0909","white"};
       parkingServiceController.parkVehicle(parkOne);
       String[] parkTwo = {"park","pb-10-1212","red"};
       parkingServiceController.parkVehicle(parkTwo);
       String[] parkThree = {"park","pb-10-1213","red"};
       parkingServiceController.parkVehicle(parkThree);
       String[] registration = {"registration_numbers_for_cars_with_colour", "red"};
       parkingServiceController.getRegistrationNumber(registration);
       assertEquals("Created a parking lot with 6 slots\n" +
               "Allocated slot number: 1\n" +
               "Allocated slot number: 2\n" +
               "Allocated slot number: 3\n" +
               "pb-10-1212,pb-10-1213",outContent.toString().trim());
   }

    /**
     * this function checks the case where lesser number of args are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getRegistrationNumberOnLessArgs(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] parkOne = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(parkOne);
        String[] parkTwo = {"park","pb-10-1212","red"};
        parkingServiceController.parkVehicle(parkTwo);
        String[] parkThree = {"park","pb-10-1213","red"};
        parkingServiceController.parkVehicle(parkThree);
        String[] registration = {"registration_numbers_for_cars_with_colour" };
        parkingServiceController.getRegistrationNumber(registration);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Allocated slot number: 3\n" +
                "Insufficient data",outContent.toString().trim());
    }
    /**
     * this function checks the case where empty args are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getRegistrationNumberOnEmpty(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] parkOne = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(parkOne);
        String[] parkTwo = {"park","pb-10-1212","red"};
        parkingServiceController.parkVehicle(parkTwo);
        String[] parkThree = {"park","pb-10-1213","red"};
        parkingServiceController.parkVehicle(parkThree);
        String[] registration = {"registration_numbers_for_cars_with_colour",""};
        parkingServiceController.getRegistrationNumber(registration);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Allocated slot number: 3\n" +
                "Empty string",outContent.toString().trim());
    }

    /**
     * this function tests the slot number of color for a valid scenario
     *  @result An appropriate error message will be returned
     */
   @Test
   public void getSlotOnColor(){
       String[] args = {"create","6"};
       parkingServiceController.createSlots(args);
       String[] parkOne = {"park","pb-10-0909","white"};
       parkingServiceController.parkVehicle(parkOne);
       String[] parkTwo = {"park","pb-10-1212","red"};
       parkingServiceController.parkVehicle(parkTwo);
       String[] parkThree = {"park","pb-10-1213","red"};
       parkingServiceController.parkVehicle(parkThree);
       String[] slotNumber = {"slot_numbers_for_cars_with_colour", "red"};
       parkingServiceController.getSloNumberOnColor(slotNumber);
       assertEquals("Created a parking lot with 6 slots\n" +
               "Allocated slot number: 1\n" +
               "Allocated slot number: 2\n" +
               "Allocated slot number: 3\n" +
               "2,3",outContent.toString().trim());
   }

    /**
     * this function checks the output for lesser arguments being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getSlotOnColorLessArgs(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] parkOne = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(parkOne);
        String[] parkTwo = {"park","pb-10-1212","red"};
        parkingServiceController.parkVehicle(parkTwo);
        String[] parkThree = {"park","pb-10-1213","red"};
        parkingServiceController.parkVehicle(parkThree);
        String[] slotNumber = {"slot_numbers_for_cars_with_colour"};
        parkingServiceController.getSloNumberOnColor(slotNumber);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Allocated slot number: 3\n" +
                "Insufficient data",outContent.toString().trim());
    }

    /**
     * this function checks the output for slot number when lesser arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getSlotOnColorEmpty(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] parkOne = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(parkOne);
        String[] parkTwo = {"park","pb-10-1212","red"};
        parkingServiceController.parkVehicle(parkTwo);
        String[] parkThree = {"park","pb-10-1213","red"};
        parkingServiceController.parkVehicle(parkThree);
        String[] slotNumber = {"slot_numbers_for_cars_with_colour",""};
        parkingServiceController.getSloNumberOnColor(slotNumber);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Allocated slot number: 3\n" +
                "Empty string",outContent.toString().trim());
    }

    /**
     * this function checks where color has not been allocated to any slot
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getSlotOnColorNotFound(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] parkOne = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(parkOne);
        String[] parkTwo = {"park","pb-10-1212","red"};
        parkingServiceController.parkVehicle(parkTwo);
        String[] parkThree = {"park","pb-10-1213","red"};
        parkingServiceController.parkVehicle(parkThree);
        String[] slotNumber = {"slot_numbers_for_cars_with_colour", "blue"};
        parkingServiceController.getSloNumberOnNumberPlate(slotNumber);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Allocated slot number: 3\n" +
                "Not found",outContent.toString().trim());
    }

    /**
     * this function checks the slot on the number plate for a valid scenario
     *  @result Valid slot numbers are returned
     */
   @Test
   public void getSlotOnNumberPlate(){
       String[] args = {"create","6"};
       parkingServiceController.createSlots(args);
       String[] parkOne = {"park","pb-10-0909","white"};
       parkingServiceController.parkVehicle(parkOne);
       String[] parkTwo = {"park","pb-10-1212","red"};
       parkingServiceController.parkVehicle(parkTwo);
       String[] parkThree = {"park","pb-10-1213","red"};
       parkingServiceController.parkVehicle(parkThree);
       String[] slotNumber = {"slot_numbers_for_cars_with_colour", "pb-10-1212"};
       parkingServiceController.getSloNumberOnNumberPlate(slotNumber);
       assertEquals("Created a parking lot with 6 slots\n" +
               "Allocated slot number: 1\n" +
               "Allocated slot number: 2\n" +
               "Allocated slot number: 3\n" +
               "2",outContent.toString().trim());
   }

    /**
     * this function checks slot number on lesser arguments being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getSlotOnNumberPlateLessArgs(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] parkOne = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(parkOne);
        String[] parkTwo = {"park","pb-10-1212","red"};
        parkingServiceController.parkVehicle(parkTwo);
        String[] parkThree = {"park","pb-10-1213","red"};
        parkingServiceController.parkVehicle(parkThree);
        String[] slotNumber = {"slot_numbers_for_cars_with_colour"};
        parkingServiceController.getSloNumberOnNumberPlate(slotNumber);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Allocated slot number: 3\n" +
                "Insufficient data",outContent.toString().trim());
    }

    /**
     * this function checks the slot on empty arguments being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getSlotOnNumberPlateEmpty(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] parkOne = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(parkOne);
        String[] parkTwo = {"park","pb-10-1212","red"};
        parkingServiceController.parkVehicle(parkTwo);
        String[] parkThree = {"park","pb-10-1213","red"};
        parkingServiceController.parkVehicle(parkThree);
        String[] slotNumber = {"slot_numbers_for_cars_with_colour",""};
        parkingServiceController.getSloNumberOnNumberPlate(slotNumber);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Allocated slot number: 3\n" +
                "Empty string",outContent.toString().trim());
    }

    /**
     * this function checks the slot on invalid arguments being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getSlotOnNumberPlateNotFound(){
        String[] args = {"create","6"};
        parkingServiceController.createSlots(args);
        String[] parkOne = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(parkOne);
        String[] parkTwo = {"park","pb-10-1212","red"};
        parkingServiceController.parkVehicle(parkTwo);
        String[] parkThree = {"park","pb-10-1213","red"};
        parkingServiceController.parkVehicle(parkThree);
        String[] slotNumber = {"slot_numbers_for_cars_with_colour", "pb-10-1211"};
        parkingServiceController.getSloNumberOnNumberPlate(slotNumber);
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Allocated slot number: 3\n" +
                "Not found",outContent.toString().trim());
    }

    /**
     * this function checks null pointer exception in a case where no
     * hash map was created but data is being allocated to null
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getSlotOnNumberPlateNoSlot(){
        String[] slotNumber = {"slot_numbers_for_cars_with_colour", "pb-10-1211"};
        parkingServiceController.getSloNumberOnNumberPlate(slotNumber);
        assertEquals("No slots created",outContent.toString().trim());
    }
    /**
     * this function checks null pointer exception in a case where no
     * hash map was created but data is being allocated to null
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getSlotOnColorNoSlot(){
        String[] slotNumber = {"slot_numbers_for_cars_with_colour", "red"};
        parkingServiceController.getSloNumberOnNumberPlate(slotNumber);
        assertEquals("No slots created",outContent.toString().trim());
    }
    /**
     * this function checks null pointer exception in a case where no
     * hash map was created but data is being allocated to null
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getRegitrationNoSlot(){
        String[] number = {"registration_numbers_for_cars_with_colour","red"};
        parkingServiceController.getRegistrationNumber(number);
        assertEquals("No slots created",outContent.toString().trim());
    }
    /**
     * this function checks null pointer exception in a case where no
     * hash map was created but data is being allocated to null
     *  @result An appropriate error message will be returned
     */
    @Test
    public void leaveNoSlot(){
        String[] number = {"leave","1"};
        parkingServiceController.leaveSlot(number);
        assertEquals("No slots created",outContent.toString().trim());
    }
    /**
     * this function checks null pointer exception in a case where no
     * hash map was created but data is being allocated to null
     *  @result An appropriate error message will be returned
     */
    @Test
    public void parkNoSlot(){
        String[] park = {"park","pb-10-0909","white"};
        parkingServiceController.parkVehicle(park);
        assertEquals("No slots created",outContent.toString().trim());
    }
    /**
     * this function checks null pointer exception in a case where no
     * hash map was created but data is being allocated to null
     *  @result An appropriate error message will be returned
     */
    @Test
    public void getStatusNoSlot(){
        String[] status = {"status"};
        parkingServiceController.status();
        assertEquals("No slots created",outContent.toString().trim());
    }
    /**
     * This function cleans the ByteArrayOutputStream after the each test executes
     */
    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }


}