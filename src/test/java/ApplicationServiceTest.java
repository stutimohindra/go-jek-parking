import com.gojek.parking.model.ParkingServiceResponse;
import com.gojek.parking.service.ParkingServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * this class has test functions for the Impl class
 */
public class ApplicationServiceTest {
    ParkingServiceImpl parkingService ;

    @Before
    public void createObject(){
        parkingService = new ParkingServiceImpl();
    }

    /**
     * valid case
     * @result A hash map with appropriate size is created
     */
    @Test
    public void checkCreateSlotForNotNull(){
        String[] args = {"create", "6"};
        Assert.assertNotNull(parkingService.createSlots(args));
    }

    /**
     * check if slot value is null
     * @result An appropriate error message will be returned
     */
    @Test
    public void checkCreateSlotForNull(){
        String[] args = {"create",""};
        ParkingServiceResponse output = parkingService.createSlots(args);
        Assert.assertTrue(output.getErrorCode() == -1);
    }

    /**
     * check with lesser arguments for creating slots
     * @result An appropriate error message will be returned
     */
    @Test
    public void checkCreateSlotForEmpty(){
        String[] args = {"create"};
        ParkingServiceResponse output = parkingService.createSlots(args);
        Assert.assertTrue(output.getErrorCode() == -1);
    }

    /**
     * test with negative slot value
     * @result An appropriate error message will be returned
     */
    @Test
    public void checkSlotForNegative(){
        String[] args = {"create","-9"};
        ParkingServiceResponse output = (parkingService.createSlots(args));
        Assert.assertTrue(output.getErrorCode() == -1);
    }

    /**
     * check if no slot was created and the status was being accessed
     * @result An appropriate error message will be returned
     */
    @Test
    public void checkStatusForNull(){
        ParkingServiceResponse output = parkingService.getStatus();
        Assert.assertTrue(output.getErrorCode() == -1);
    }

    /**
     *  check if the slot was created but no car was parked
     *  @result status will be returned as not found
     */
    @Test
    public void checkStatusForNotNull(){
        String[] args = {"create", "6"};
        parkingService.createSlots(args);
        ParkingServiceResponse output = parkingService.getStatus();
        Assert.assertTrue(output.getErrorMessage().equals("no status found"));
    }
    /**
     * checks status valid case
     *  @result status will be returned
     */
    @Test
    public void checkStatusForAllocated(){
        String[] args = {"create", "6"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-08-07","white"};
        parkingService.allocateSlot(park);
        ParkingServiceResponse output = parkingService.getStatus();
        Assert.assertTrue(output.getErrorCode() == 0);
    }
    /**
     * checks slot number in case valid arguments are being passed to the function
     *  @result An slot number will be returned
     */
    @Test
    public void checkSlotNumberOnColorValid(){
        String[] args = {"create", "6"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-08-07","white"};
        parkingService.allocateSlot(park);
        String[] color = {"slot_numbers_for_cars_with_colour","white"};
        ParkingServiceResponse output = parkingService.getSlotNumberOnColor(color);
        Assert.assertTrue(output.getErrorCode() == 0);
    }
    /**
     * checks slot number in case invalid arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkSlotNumberOnColorInValid(){
        String[] args = {"create", "6"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-08-07","white"};
        parkingService.allocateSlot(park);
        String[] color = {"slot_numbers_for_cars_with_colour","red"};
        ParkingServiceResponse output = parkingService.getSlotNumberOnColor(color);
        Assert.assertTrue(output.getErrorMessage().equals("Not found"));
    }
    /**
     * checks slot number in case empty arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkSlotNumberOnColorNull(){
        String[] args = {"create", "1"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-08-07","white"};
        parkingService.allocateSlot(park);
        String[] color = {"slot_numbers_for_cars_with_colour",""};
        ParkingServiceResponse output = parkingService.getSlotNumberOnColor(color);
        Assert.assertTrue(output.getErrorCode() == -1);
    }

    /**
     * checks slot number in case lesser arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkSlotNumberOnColorEmpty(){
        String[] args = {"create", "1"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-08-07","white"};
        parkingService.allocateSlot(park);
        String[] color = {"slot_numbers_for_cars_with_colour"};
        ParkingServiceResponse output = parkingService.getSlotNumberOnColor(color);
        Assert.assertTrue(output.getErrorCode() == -1);
    }
    /**
     * check slot number valid case
     * @result An appropriate slot number will be returned
     */
    @Test
    public void checkSlotNumberOnRegistrationNumberValid(){
        String[] args = {"create", "6"};
        parkingService.createSlots(args);
        String[] park = {"park","KA-01-HH-3141","white"};
        parkingService.allocateSlot(park);
        String[] numberPlate = {"slot_number_for_registration_number","KA-01-HH-3141"};
        ParkingServiceResponse output = parkingService.getSlotNumberOnNumberPlate(numberPlate);
        Assert.assertTrue(output.getErrorCode() == 0);
    }

    /**
     * check registration number if parking slots on registration dont exist
     * @result An appropriate error message will be returned
     */
    @Test
    public void checkSlotNumberOnRegistrationNumberInValid(){
        String[] args = {"create", "6"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-08-07","white"};
        parkingService.allocateSlot(park);
        String[] numberPlate = {"slot_number_for_registration_number","PJ-05-0987"};
        ParkingServiceResponse output = parkingService.getSlotNumberOnNumberPlate(numberPlate);
        Assert.assertTrue(output.getErrorMessage().equals("Not found"));
    }
    /**
     * checks leave slot in case no slots were created
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkSlotNumberOnRegistrationNumberNull(){
        String[] args = {"create", "1"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-08-07","white"};
        parkingService.allocateSlot(park);
        String[] color = {"slot_number_for_registration_number",""};
        ParkingServiceResponse output = parkingService.getSlotNumberOnNumberPlate(color);
        Assert.assertTrue(output.getErrorCode() == -1);
    }
    /**
     * checks slot number in case empty arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkSlotNumberOnRegistrationNumberEmpty(){
        String[] args = {"create", "1"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-08-07","white"};
        parkingService.allocateSlot(park);
        String[] color = {"slot_number_for_registration_number"};
        ParkingServiceResponse output = parkingService.getSlotNumberOnNumberPlate(color);
        Assert.assertTrue(output.getErrorCode() == -1);
    }
    /**
     * checks registration number in case invalid arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkRegistrationNumberOnColorValid(){
        String[] args = {"create", "6"};
        parkingService.createSlots(args);
        String[] park = {"park","KA-01-HH-3141","white"};
        parkingService.allocateSlot(park);
        String[] numberPlate = {"registration_numbers_for_cars_with_colour","white"};
        ParkingServiceResponse output = parkingService.getRegistrationNumber(numberPlate);
        Assert.assertTrue(output.getErrorCode() == 0);
    }
    /**
     * checks registration number in case invalid arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkRegistrationNumberOnColorInValid(){
        String[] args = {"create", "6"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-08-07","white"};
        parkingService.allocateSlot(park);
        String[] numberPlate = {"registration_numbers_for_cars_with_colour","blue"};
        ParkingServiceResponse output = parkingService.getRegistrationNumber(numberPlate);
        Assert.assertTrue(output.getErrorMessage().equals("Not found"));
    }
    /**
     * checks registration number in case empty arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkRegistrationNumberOnColorNull(){
        String[] args = {"create", "1"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-08-07","white"};
        parkingService.allocateSlot(park);
        String[] numberPlate = {"registration_numbers_for_cars_with_colour",""};
        ParkingServiceResponse output = parkingService.getSlotNumberOnNumberPlate(numberPlate);
        Assert.assertTrue(output.getErrorCode() == -1);
    }
    /**
     * checks registration number in case lesser arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkRegistrationNumberOnColorEmpty(){
        String[] args = {"create", "1"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-08-07","white"};
        parkingService.allocateSlot(park);
        String[] numberPlate = {"registration_numbers_for_cars_with_colour"};
        ParkingServiceResponse output = parkingService.getSlotNumberOnNumberPlate(numberPlate);
        Assert.assertTrue(output.getErrorCode() == -1);
    }
    /**
     * handles null pointer when slots are not allocated and registration number function is being invoked
     * @result An appropriate error message will be returned
     */
    @Test
    public void checkRegistrationNumberForEmptySlot(){
        String[] numberPlate = {"registration_numbers_for_cars_with_colour","blue"};
        ParkingServiceResponse output = parkingService.getSlotNumberOnNumberPlate(numberPlate);
        Assert.assertTrue(output.getErrorCode() == -1);
    }
    /**
     * handles null pointer when slots are not allocated and slot number function is being invoked
     * @result An appropriate error message will be returned
     */
    @Test
    public void checkSlotNumberColorOnEmptySlot(){
        String[] numberPlate = {"slot_number_for_registration_number","blue"};
        ParkingServiceResponse output = parkingService.getRegistrationNumber(numberPlate);
        Assert.assertTrue(output.getErrorCode() == -1);
    }

    /**
     * handles null pointer when slots are not allocated and slot number function is being invoked
     * @result An appropriate error message will be returned
     */
    @Test
    public void checkSlotNumberRegistrationOnEmptySlot(){
        String[] numberPlate = {"slot_number_for_registration_number","red"};
        ParkingServiceResponse output = parkingService.getSlotNumberOnColor(numberPlate);
        Assert.assertTrue(output.getErrorCode() == -1);
    }
    /**
     * checks slot allocation in case lesser arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkAllocateSlotWithLesserArgs(){
        String[] args = {"create", "1"};
        parkingService.createSlots(args);
        String[] park = {"park"};
        ParkingServiceResponse output = parkingService.allocateSlot(park);
        Assert.assertTrue(output.getErrorCode() == -1);
    }
    /**
     * checks slot allocation  in case empty arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkAllocateSlotForEmpty(){
        String[] args = {"create", "1"};
        parkingService.createSlots(args);
        String[] park = {"park", "", ""};
        ParkingServiceResponse output = parkingService.allocateSlot(park);
        Assert.assertTrue(output.getErrorCode() == -1);
    }
    /**
     * checks leave slot in case empty arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkLeaveSlotForEmpty(){
        String[] args = {"create","1"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-09-0863","white"};
        parkingService.allocateSlot(park);
        String[] leave = {"leave",""};
        ParkingServiceResponse output = parkingService.leaveSlot(leave);
        Assert.assertTrue(output.getErrorCode() == -1);
    }

    /**
     * valid test case
     * @result The hash map at key will be null
     */
    @Test
    public void checkLeaveSlot(){
        String[] args = {"create","1"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-09-0863","white"};
        parkingService.allocateSlot(park);
        String[] leave = {"leave","1"};
        ParkingServiceResponse output = parkingService.leaveSlot(leave);
        Assert.assertTrue(output.getErrorCode() == 0);
    }

    /**
     * checks leave slot in case invalid arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkLeaveSlotForFalse(){
        String[] args = {"create","1"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-09-0863","white"};
        parkingService.allocateSlot(park);
        String[] leave = {"leave","3"};
        ParkingServiceResponse output = parkingService.leaveSlot(leave);
        Assert.assertTrue(output.getErrorCode() == -1);
    }

    /**
     * checks leave slot in case lesser arguments are being passed to the function
     *  @result An appropriate error message will be returned
     */
    @Test
    public void checkLeaveSlotForNull(){
        String[] args = {"create","1"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-09-0863","white"};
        parkingService.allocateSlot(park);
        String[] leave = {"leave"};
        ParkingServiceResponse output = parkingService.leaveSlot(leave);
        Assert.assertTrue(output.getErrorCode() == -1);
    }

    /**
     * this function checks if an error is returned on
     * trying to allocate more than one spot to the same registration number and car
     * @result An error will be returned
     */
    @Test
    public void checkForParkingSameVehicle(){
        String[] args = {"create","1"};
        parkingService.createSlots(args);
        String[] park = {"park","ph-09-0863","white"};
        parkingService.allocateSlot(park);
        String[] park1 = {"park","ph-09-0863","white"};
        ParkingServiceResponse output = parkingService.allocateSlot(park1);
        Assert.assertTrue(output.getErrorCode() == -1);
    }

}
