package myUberCore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarSearchTest {

	@Test
	void testSortCars() {
		    Time time=new Time();
			Customer moez = new Customer("Moez", "Dbira", 153136);
			RideBooking request = new RideBooking(moez, GPS(1,4), time, time.getTraffic(time), 2);
			
	}

	@Test
	void testFindCar() {
		fail("Not yet implemented");
	}

}
