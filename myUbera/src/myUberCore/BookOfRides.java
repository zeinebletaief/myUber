package myUberCore;

import java.util.ArrayList;

public class BookOfRides {
	public static ArrayList<Ride> book =new ArrayList<Ride>();
	
	public static void addRide(Ride ride) {
		BookOfRides.book.add(ride);
	}

}
