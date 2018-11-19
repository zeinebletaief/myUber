package myUberCore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerTest {

	@Test
	void testRideFactory() {
		fail("Not yet implemented");
	}

	@Test
	void testBookaRide() {
		Time time=new Time();
		Customer moez = new Customer("Moez","Dbira",13215);
		moez.setCusPosition(new GPS(4,5));
		RideBooking request = moez.bookaRide(new GPS(9,3), time, 2);
		request.estimateFares();
		assertTrue(request.getDistance()==moez.getCusPosition().distance(new GPS(9,3)));
	}

	@Test
	void testChooseRideType() {
		fail("Not yet implemented");
	}

}
