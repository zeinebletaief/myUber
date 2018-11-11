package myUberCore;

public class CarSearch { //It is a car search "factory"
	//we use the class in order to separate the instantiation logic from the RideBooking class
	
	public void createCarSearch(String ridePreference, RideBooking booking) {
		
		if (ridePreference == null){
			//return null
		}

		if (ridePreference.equalsIgnoreCase("X")) {
			UberX X = new UberX(booking.getUser());
			X.rideSearch();
		}
		if (ridePreference.equalsIgnoreCase("BLACK")) {
			UberBlack black = new UberBlack(booking.getUser());
			black.rideSearch();
		}
		if (ridePreference.equalsIgnoreCase("VAN")) {
			UberVan van = new UberVan(booking.getUser());
			van.rideSearch();
		}
		if (ridePreference.equalsIgnoreCase("POOL")) {
			UberPool pool = new UberPool(booking.getUser());
			pool.rideSearch();
		}
	}

}
