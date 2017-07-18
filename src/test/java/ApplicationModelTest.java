import com.gojek.parking.model.ParkingServiceModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * this class checks the getters if they are returning valid response
 * @result Data will be persisted without error
 */

public class ApplicationModelTest {
    ParkingServiceModel parkingServiceModel;

    /**
     * create object before each test
     */
    @Before
    public void createObject(){
        parkingServiceModel = new ParkingServiceModel();
    }

    @Test
    public void getterColor(){
        parkingServiceModel.setColor("white");
        assertTrue(parkingServiceModel.getColor().equals("white"));
    }

    @Test
    public void getterRegistrationNumber(){
        parkingServiceModel.setPlateNumber("ph-09-0987");
        assertTrue(parkingServiceModel.getPlateNumber().equals("ph-09-0987"));
    }
}