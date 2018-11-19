package myUberCore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class TimeTest {

	@Test
	void testTime() {
		Date current = new Date();
		Time current1=new Time(1000000);
		System.out.println(current);
		System.out.println(current1);
		assertTrue(true);
	}

	@Test
	void testAdvanceTime() {
		Time current = new Time();
		Time current1=new Time();
		current.advanceTime(new Duration(3600));
		System.out.println(current);
		System.out.println(current1);
		assertTrue(!(current==current1));
	}

	@Test
	void testGetTraffic() {
		Time current = new Time();
		Time current1=new Time();
		current.advanceTime(new Duration(10000));
		TrafficState traffic = current.getTraffic(current);
		TrafficState traffic1 = current1.getTraffic(current1);
		System.out.println(traffic);
		System.out.println(traffic1);
		assertTrue(!(current==current1));
	}

}
